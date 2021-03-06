package com.cte

import spock.lang.Specification

class RecoveryServiceSpec extends Specification {

  def service = new RecoveryService()

  def restService = Mock(RestService)
  def recoveryCollaboratorService = Mock(RecoveryCollaboratorService)
  def registrationService = Mock(RegistrationService)
  def messageSource = new MessageSourceMock()
  def grailsApplication = new GrailsApplicationMock()

  def setup() {
    service.restService = restService
    service.recoveryCollaboratorService = recoveryCollaboratorService
    service.registrationService = registrationService
    service.grailsApplication = grailsApplication
    service.messageSource = messageSource
  }

  void "should send confirmation account token"(){
  given:"An email"
  def email = 'josdem@email.com'
  and:"A MessageCommand"
  def message = Mock(MessageCommand)
  when:"We generate token"
  service.sendConfirmationAccountToken(email)
  then:"We expect generate token and send email"
  recoveryCollaboratorService.generateToken('register', email) >> message
  }

  void "should confirm account for token"(){
  given: "Token and password"
  def token = 'token'
  def email = 'josdem@email.com'
  and: "user"
  def user = Mock(User)
  def profile = new Profile(email:email, firsName:'firsName', motherLastName:'motherLastName', lastName:'lastName')
  and: "mock values"
  user.enabled >> false
  user.profile >> profile
  registrationService.findEmailByToken(token) >> email
  Profile.metaClass.static.findByEmail = { profile }
  User.metaClass.static.findByProfile = { user }
  when: "We confirm account for token"
  service.confirmAccountForToken(token)
  then: "We expect user enabled"
  1 * user.setProperty('enabled',true)
  1 * user.save()
  }

  void "should generate registration code for email"() {
  given: "An email"
  def email = 'josdem@email.com'
  and: "User mock"
  def user = Mock(User)
  def profile = new Profile(email:email, firsName:'firsName', motherLastName:'motherLastName', lastName:'lastName')
  def message = Mock(TokenCommand)
  user.profile >> profile
  Profile.metaClass.static.findByEmail = { profile }
  User.metaClass.static.findByProfile = { user }
  when: "We find user by email"
  recoveryCollaboratorService.generateToken('forgot', email) >> message
  user.enabled >> true
  service.generateRegistrationCodeForEmail(email)
  then: "We expect send message to the email service"
  1 * restService.sendCommand(message, 'forgot')
  }


  void "should not generate registration code for email since user not found"() {
  given: "An email"
  def email = 'josdem@email.com'
  and: "User mock"
  def profile = new Profile(email:email, firsName:'firsName', motherLastName:'motherLastName', lastName:'lastName')
  def message = Mock(TokenCommand)
  Profile.metaClass.static.findByEmail = { profile }
  User.metaClass.static.findByProfile = { null }
  when: "We find user by email"
  service.generateRegistrationCodeForEmail(email)
  then: "We expect get an exception since user not found"
  thrown UserNotFoundException
  }

  void "should not generate registration code for email since account is not activated"() {
  given: "An email"
  def email = 'josdem@email.com'
  and: "User mock"
  def user = Mock(User)
  def profile = new Profile(email:email, firsName:'firsName', motherLastName:'motherLastName', lastName:'lastName')
  def message = Mock(TokenCommand)
  Profile.metaClass.static.findByEmail = { profile }
  User.metaClass.static.findByProfile = { user }
  when: "We find user by email"
  service.generateRegistrationCodeForEmail(email)
  then: "We expect get an exception since account is not activated"
  thrown AccountNoActivatedException
  }

  void "should change password for token"(){
  given: "Token and password"
  def token = 'token'
  def password = 'password'
  def email = 'josdem@email.com'
  and: "user"
  def user = Mock(User)
  def profile = new Profile(email:email, firsName:'firsName', motherLastName:'motherLastName', lastName:'lastName')
  Profile.metaClass.static.findByEmail = { profile }
  User.metaClass.static.findByProfile = { user }
  when: "We send change password for token"
  service.changePasswordForToken(token, password)
  then: "We expect save new password"
  registrationService.isValidToken(token) >> true
  registrationService.findEmailByToken(token) >> email
  1 * user.setProperty('password','password')
  1 * user.save()
  }

  void "should not change password for token since user not exist"(){
  given: "Token and password"
  def token = 'token'
  def password = 'password'
  def email = 'josdem@email.com'
  and: "user"
  def profile = new Profile(email:email, firsName:'firsName', motherLastName:'motherLastName', lastName:'lastName')
  Profile.metaClass.static.findByEmail = { profile }
  User.metaClass.static.findByProfile = { null }
  registrationService.isValidToken(token) >> true
  when: "We send change password for token"
  service.changePasswordForToken(token, password)
  then: "We expect get an exception since user not found"
  thrown UserNotFoundException
  }

  void "should not change password for token since user not exist"(){
  given: "Token and password"
  def token = 'token'
  def password = 'password'
  when: "We send change password for token"
  service.changePasswordForToken(token, password)
  then: "We expect get an exception since is not a valid token"
  thrown BusinessException
  }
}
