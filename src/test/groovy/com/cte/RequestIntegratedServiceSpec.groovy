package com.cte

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock

@TestFor(RequestIntegratedService)
@Mock([Profile,User,Company])
class RequestCompanyToIntegrateServiceSpec extends Specification {

  def restService = Mock(RestService)

  def setup() {
    service.restService = restService
  }

  void "send notification of company want to Integrated"() {
    given:"create a user and profile"
      def profile = new Profile(email:"sergio@makingdevs.com")
      def user = new User(profile:profile).save(validate:false)
    and:"create a company"
      def company = new Company(name:"making devs").save(validate:false)
    and:"mock of variables of grailsApplication"
      grailsApplication.config.emailer.notificationIntegrated = "template1"
      grailsApplication.config.emailer.emailAdmin = "admin@iecce.mx"
    when:
      def companyResult = service.sendNotificationsOFCompanyToIntegrated(company,user)
    then:
      companyResult.status == CompanyStatus.VALIDATE
  }

}
