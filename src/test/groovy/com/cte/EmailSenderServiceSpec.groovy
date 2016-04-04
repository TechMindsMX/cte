package com.cte

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

@TestFor(EmailSenderService)
@Mock(Company)
class EmailSenderServiceSpec extends Specification {

  def restService = Mock(RestService)
  def grailsApplication = new GrailsApplicationMock()

  def setup() {
    service.restService = restService
    service.grailsApplication = grailsApplication
  }

  void "should send new client notification"() {
    given:"A legal representative"
      String email = 'user@email.com'
      def legalRepresentative = Mock(User)
      def profile = Mock(Profile)
      legalRepresentative.profile >> profile
      profile.email >> email
    and:"A company"
      def company = new Company(rfc:'ROS861224NHA', bussinessName:'businessName', employeeNumbers:10, grossAnnualBilling:100000, legalRepresentatives:[legalRepresentative]).save()
    when:"We send notification"
      service.sendNewClientProviderNotificaton(company, 'josdem', EmailerMessageType.CLIENTE_MODULUS)
    then:"We expect notification sent"
    1 * restService.sendCommand(_ as NameCommand, 'clientProvider')
  }

  void "should send sale order to authorize"(){
    given:"A approver"
      String email = 'user@email.com'
      def approver = Mock(User)
      def profile = Mock(Profile)
      approver.profile >> profile
      profile.email >> email
    and:"A company"
      def company = new Company(rfc:'ROS861224NHA', bussinessName:'businessName', employeeNumbers:10, grossAnnualBilling:100000).save()
    and:"A sale order"
      def saleOrder = new SaleOrder(rfc:'rfc', clientName:'clientName', status:SaleOrderStatus.POR_AUTORIZAR, company:company)
    when:"We send notification"
      service.sendSaleOrderToAuthorize(saleOrder, [approver])
    then:"We expect notification sent"
    1 * restService.sendCommand(_ as SaleOrderCommand, 'authorizeSaleOrder')

  }
}
