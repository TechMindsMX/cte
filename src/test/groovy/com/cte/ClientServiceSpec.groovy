package com.cte

import org.springframework.context.i18n.LocaleContextHolder as LCH

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

import com.cte.BusinessException

@TestFor(ClientService)
@Mock([Address,Company,BusinessEntity,ClientLink])
class ClientServiceSpec extends Specification {

  def emailSenderService = Mock(EmailSenderService)

  def setup(){
    service.emailSenderService = emailSenderService
    messageSource.addMessage('exception.client.already.exist', LCH.getLocale(), 'A client with same RFC already exist for this company')
  }

  void "Add client to a given company"(){
  given:"An existing business entity and a given company"
    def be = new BusinessEntity(rfc:'XXX010101XXX',website:'http://iecce.com.mx',type:BusinessEntityType.MORAL).save(validate:false)
    def company = new Company(rfc:'ROS861224NHA', bussinessName:'businessName', employeeNumbers:10, grossAnnualBilling:100000).save()
  when:"Assign the client behvaior"
    service.addClientToCompany(be, company)
    def isClient = service.isClientOfThisCompany(be, company)
    def clientLink = ClientLink.findByTypeAndClientRef("BusinessEntity", 'XXX010101XXX')
  then:"I should demonstrate the BE is a client of the company"
    clientLink
    clientLink.type == "BusinessEntity"
    clientLink.clientRef == be.rfc
    clientLink.company == company
    isClient
    1 * emailSenderService.sendNewClientProviderNotificaton(company, _ , EmailerMessageType.CLIENTE_MODULUS)
  }

  void "should not save a be since already exist"(){
  given:"An existing business entity and a given company"
    def be1 = new BusinessEntity(rfc:'XXX010101XXX',website:'http://iecce.com.mx',type:BusinessEntityType.FISICA).save(validate:false)
    def company = new Company(rfc:'ROS861224NHA', bussinessName:'businessName', employeeNumbers:10, grossAnnualBilling:100000).save()
    service.addClientToCompany(be1, company)
    assert service.isClientOfThisCompany(be1, company)
  and:"Another business entity"
    def be2 = new BusinessEntity(rfc:'XXX010101XXX',website:'http://iecce.com.mx',type:BusinessEntityType.FISICA).save(validate:false)
  when:"We save another business entity with same RFC"
    service.addClientToCompany(be2, company)
  then:"We expect Exception"
    thrown BusinessException
  }


  void "should get clients by company"(){
    given:"A business entity and given a company"
      def be = new BusinessEntity(rfc:'XXX010101XXX',website:'http://iecce.com.mx',type:BusinessEntityType.FISICA).save(validate:false)
      def company = new Company(rfc:'ROS861224NHA', bussinessName:'businessName', employeeNumbers:10, grossAnnualBilling:100000).save()
    and:"We assign client to company"
      service.addClientToCompany(be, company)
    when:"We get clients"
      def result = service.getClientsFromCompany(company)
    then:"We expect our client"
      result.size() == 1
      result.contains(be)
   }
}
