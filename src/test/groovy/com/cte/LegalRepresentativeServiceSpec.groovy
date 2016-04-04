package com.cte

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
import java.lang.Void as Should
import grails.plugin.springsecurity.SpringSecurityService
import groovy.mock.interceptor.MockFor

@TestFor(LegalRepresentativeService)
@Mock([User,Profile,Role,UserRole,Company])
class LegalRepresentativeServiceSpec extends Specification {

  def setup(){
    service.springSecurityService = Mock(SpringSecurityService)
  }

  Should "get the users with the legal representative role"(){
    given:"the roles"
      def roles = [new Role(authority:"ROLE_LEGAL_REPRESENTATIVE"),
                   new Role(authority:"ROLE_INTEGRADO")]
      roles*.save()

    and:"the users"
      def users = [new User(username:"egjimenezg@gmail.com",
                            password:"1234567890"),
                   new User(username:"gamaliel@makingdevs.com",
                            password:"1234567890")]
      users*.save(validate:false)

      users.eachWithIndex{ user, i ->
        UserRole.create(user,roles[i])
      }
      
      setCurrentUser(users[0])

    when:
      def legalUsers = service.findUsersWithLegalRepresentativeRole()
    then:
      legalUsers.size() == 1
  }

  Should "assign another user as legal representative of the company"(){
    given:"the company"
      def company = createCompany()

    and:"the current user and the legal representatives"
      def users = [new User(username:"egjimenezg@gmail.com").save(validate:false),
                   new User(username:"legal@gmail.com").save(validate:false)]

      def roles = [new Role(authority:"ROLE_USER"),
                   new Role(authority:"ROLE_LEGAL_REPRESENTATIVE")]

      roles*.save()

      users.eachWithIndex{ user, i ->
        UserRole.create(user,roles[i])
      }

      SpringSecurityService.metaClass.static.getCurrentUser = {
        users[0]
      }
    when:
      service.assignLegalRepresentativeToCompany(company.id,users[1].id)

    then:
      company.legalRepresentatives.first().username == users[1].username
  }

  Should "assign the current user as legal representative"(){
    given:"a user and the company"
      def user = new User(username:"egjimenezg@gmail.com",
                          password:"310893").save(validate:false)

      def role = new Role(authority:'ROLE_LEGAL_REPRESENTATIVE').save(validate:false)

      def company = createCompany()

      SpringSecurityService.metaClass.static.getCurrentUser = {
        user
      }
    when:
      service.assignLegalRepresentativeToCompany(company.id,user.id)
    then:
      company.legalRepresentatives.first().username == user.username
  }

  Should "add the legal representative role to a user"(){
    given:"a user with the user role"

      def user = new User(username:"egjimenezg@gmail.com",
                          password:"",
                          profile:new Profile(name:"Gamaliel",
                                              lastName:"Jiménez",
                                              email:"egjimenezg@gmail.com")).save(validate:false)

      def roles= [new Role(authority:'ROLE_USER'),
                  new Role(authority:'ROLE_LEGAL_REPRESENTATIVE')]

      roles*.save()

      UserRole.create user,roles[0]
    when:
      service.addLegalRepresentativeRoleToUser(user)
    then:
      user.authorities.find{ it.authority == "ROLE_LEGAL_REPRESENTATIVE" }
      user.authorities.size() == 2
  }

  Should "get the avaibale legal representatives for company"(){
    given:"many legal representatives"
      def legalUsers = [new User(username:"egjimenezg"),
                        new User(username:"legal1"),
                        new User(username:"legal2"),
                        new User(username:"legal3"),
                        new User(username:"legal4")]

      legalUsers*.save(validate:false)

      def role = new Role(authority:"ROLE_LEGAL_REPRESENTATIVE").save()

      legalUsers.eachWithIndex{ user, i ->
        UserRole.create(user,role)
      }

    and:"the current user"
      def currentUser = new User(username:"gamaliel@makingdevs.com").save(validate:false)
      UserRole.create(currentUser,role)

      SpringSecurityService.metaClass.static.getCurrentUser = {
        currentUser
      }

    and:"the company with a legal representative"
      def company = createCompany()
      service.assignLegalRepresentativeToCompany(company.id,legalUsers[0].id)
      company.save()

    when:
      def legalRepresentatives = service.getAvailableLegalRepresentativesForCompany(company)
    then:
      legalRepresentatives.size() == 4
  }
  
  Should "search the legal representative given the rfc"(){
    given:"legal representatives"
      def role = new Role(authority:'ROLE_LEGAL_REPRESENTATIVE').save()
      def users = [new User(username:"egjimenezg",
                            profile:new Profile(rfc:"JIGE9308312T6").save(validate:false)),
                   new User(username:"josdem",
                            profile:new Profile(rfc:"MOCS801001ABC").save(validate:false))]

      users*.save(validate:false)

      users.each{ user ->
        UserRole.create(user,role)
      }
      def rfc = "JIGE9308312T6"
    and:"the company"
      def company = new Company(name:"Apple Computers").save(validate:false)
    when:
      def user = service.findUserByRFC(rfc,company)
    then:
      user.username == "egjimenezg"
      user.profile.rfc == rfc
  }

  private def createCompany(){
    new Company(bussinessName:"MakingDevs").save(validate:false)
  }

  private def setCurrentUser(user){
    SpringSecurityService.metaClass.static.getCurrentUser = {
      user
    }
  }

}
