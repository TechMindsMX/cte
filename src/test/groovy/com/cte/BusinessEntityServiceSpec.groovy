package com.cte

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
import grails.orm.HibernateCriteriaBuilder

@TestFor(BusinessEntityService)
@Mock([BusinessEntity, ClientLink, ProviderLink, EmployeeLink, Company])
class BusinessEntityServiceSpec extends Specification {

  def names = []
  def types = []

  def clientService = Mock(ClientService)
  def providerService = Mock(ProviderService)
  def employeeService = Mock(EmployeeService)

  def setup(){
    service.clientService = clientService
    service.providerService = providerService
  }

  void "should create a client and append names"() {
  given:"A business entity"
    BusinessEntity.metaClass.addToNames = {
      composeName ->
        names.add(composeName.value)
        types.add(composeName.type)
    }
    def businessEntity = new BusinessEntity(rfc:'XXX010101XXX', website:'http://www.iecce.mx',type:BusinessEntityType.FISICA).save(validate:false)
  and:"Names"
    def name = 'Jose Luis'
    def lastName = 'De la Cruz'
    def motherLastName = 'Morales'
  when:"We compose values"
    def result = service.appendNamesToBusinessEntity(businessEntity, (String[])[name, lastName, motherLastName])
  then:"We expect a client"
    result.rfc == 'XXX010101XXX'
    result.website == 'http://www.iecce.mx'
    names.get(0) == name
    names.get(1) == lastName
    names.get(2) == motherLastName
    types.get(0) == NameType.NOMBRE
    types.get(1) == NameType.APELLIDO_PATERNO
    types.get(2) == NameType.APELLIDO_MATERNO
    businessEntity.id == 1
  }

void "should create a provider and append business name"() {
  given:"A business entity"
    BusinessEntity.metaClass.addToNames = {
      composeName ->
        names.add(composeName.value)
        types.add(composeName.type)
    }
    def businessEntity = new BusinessEntity(rfc:'XX010101XXX', website:'http://www.iecce.mx',type:BusinessEntityType.MORAL).save(validate:false)
  and:"Data"
    def businessName = 'Jmetadata'
    def employeeNumbers = 10
    def grossAnnualBilling = 100000
  when:"We compose values"
    def result = service.appendDataToBusinessEntity(businessEntity, businessName)
  then:"We expect a client"
    result.rfc == 'XX010101XXX'
    result.website == 'http://www.iecce.mx'
    names.get(0) == businessName
    types.get(0) == NameType.RAZON_SOCIAL
    businessEntity.id == 1
}

void "should update lastName"(){
  given:"An business entity"
    def businessEntity = new BusinessEntity(rfc:'XXXX010101XXX', website:'http://www.iecce.mx',type:BusinessEntityType.FISICA)
  and:"A names"
    def name = new ComposeName(value:'Jose Luis',type:NameType.NOMBRE)
    def lastName = new ComposeName(value:'De la Cruz',type:NameType.APELLIDO_PATERNO)
    def motherLastName = new ComposeName(value:'Rodriguez',type:NameType.APELLIDO_MATERNO)
  and:"We assign values to business entity"
    businessEntity.names = [name, lastName, motherLastName]
    businessEntity.save(validate:false)
  when:"We update names"
    def result = service.updateNamesToBusinessEntity(businessEntity,(String[]) ['Jose Luis','De la Cruz','Morales'])
  then:"We expect lastName updated"
    result.names.each{
      if(it.type == NameType.APELLIDO_MATERNO){
        assert it.value == 'Morales'
      }
    }
}

  void "should update business name"(){
  given:"An business entity"
    def businessEntity = new BusinessEntity(rfc:'XXX010101XXX', website:'http://www.iecce.mx',type:BusinessEntityType.MORAL)
  and:"A business name"
    def businessName = new ComposeName(value:'Imaginn',type:NameType.RAZON_SOCIAL)
    def providerLink = Mock(ProviderLink)
  and:"We assign values to business entity"
    businessEntity.names = [businessName]
    businessEntity.save(validate:false)
    ProviderLink.metaClass.static.findByProviderRef = { providerLink }
  when:"We update names"
    def result = service.updateDataToBusinessEntity(businessEntity,'Jmetadata')
  then:"We expect lastName updated"
    result.names.each{
      if(it.type == NameType.RAZON_SOCIAL){
        assert it.value == 'Jmetadata'
      }
    }
  }

  void "should get client type"(){
  given:"An RFC"
    String rfc = 'rfc'
  and:"A client link"
    def clientLink = Mock(ClientLink)
  when:"We get client or provider type"
    ClientLink.metaClass.static.findByClientRef = { clientLink }
    ProviderLink.metaClass.static.findByProviderRef = { null }
    def result = service.getClientProviderType(rfc)
  then:"We expect client type"
    result == LeadType.CLIENTE
  }

  void "should get provider type"(){
  given:"An RFC"
    String rfc = 'rfc'
  and:"A provider link"
    def providerLink = Mock(ProviderLink)
  when:"We get client or provider type"
    ClientLink.metaClass.static.findByClientRef = { null }
    ProviderLink.metaClass.static.findByProviderRef = { providerLink }
    def result = service.getClientProviderType(rfc)
  then:"We expect provider type"
    result == LeadType.PROVEEDOR
  }

  void "should get client provider type"(){
  given:"An RFC"
    String rfc = 'rfc'
  and:"A client and provider link"
    def clientLink = Mock(ClientLink)
    def providerLink = Mock(ProviderLink)
  when:"We get client or provider type"
    ClientLink.metaClass.static.findByClientRef = { clientLink }
    ProviderLink.metaClass.static.findByProviderRef = { providerLink }
    def result = service.getClientProviderType(rfc)
  then:"We expect provider type"
    result == LeadType.CLIENTE_PROVEEDOR
  }

  void "should delete links for rfc"(){
  given:"A rfc"
    String rfc = 'rfc'
  and:"A client and provider link"
    def clientLink = Mock(ClientLink)
    def providerLink = Mock(ProviderLink)
  when:"We delete links for rfc"
    ClientLink.metaClass.static.findByClientRef = { clientLink }
    ProviderLink.metaClass.static.findByProviderRef = { providerLink }
    service.deleteLinksForRfc(rfc)
  then:"We expect no clients and providers"
    1 * providerLink.delete()
    1 * clientLink.delete()
  }

  void "should not delete client if not exist"(){
  given:"A rfc"
    String rfc = 'rfc'
 and:"A client and provider link"
    def providerLink = Mock(ProviderLink)
  when:"We delete links for rfc"
    ClientLink.metaClass.static.findByClientRef = { null }
    ProviderLink.metaClass.static.findByProviderRef = { providerLink }
    service.deleteLinksForRfc(rfc)
  then:"We expect no clients and providers"
    1 * providerLink.delete()
  }

}

