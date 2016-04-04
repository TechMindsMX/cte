package com.cte

import grails.transaction.Transactional

@Transactional
class BusinessEntityService {

  def clientService
  def providerService
  def employeeService

  def appendNamesToBusinessEntity(BusinessEntity businessEntity, String[] properties) {
    println properties.dump()
    def name = new ComposeName(value:properties[0], type:NameType.NOMBRE)
    def lastName = new ComposeName(value:properties[1], type:NameType.APELLIDO_PATERNO)
    def motherLastName = new ComposeName(value:properties[2], type:NameType.APELLIDO_MATERNO)
    if (properties.length > 3) {
        if (properties[3])
          businessEntity.addToNames(new ComposeName(value:properties[3], type:NameType.NUMERO_EMPLEADO))
        if (properties[4])
          businessEntity.addToNames(new ComposeName(value:properties[4], type:NameType.BANCO))
        if (properties[5])
          businessEntity.addToNames(new ComposeName(value:properties[5], type:NameType.CUENTA))
        if (properties[6])
          businessEntity.addToNames(new ComposeName(value:properties[6], type:NameType.CLABE))
    }
    businessEntity.addToNames(name)
    businessEntity.addToNames(lastName)
    businessEntity.addToNames(motherLastName)
    businessEntity.save()
    businessEntity
  }

  def appendDataToBusinessEntity(BusinessEntity businessEntity, String businessName) {
    businessEntity.addToNames(new ComposeName(value:businessName, type:NameType.RAZON_SOCIAL))
    businessEntity.save()
    businessEntity
  }

  def createAddressForBusinessEntity(Address address, Long businessEntityId) {
    def businessEntity = BusinessEntity.get(businessEntityId)
    businessEntity.addToAddresses(address)
    businessEntity.save()
    businessEntity
  }

  def deleteLinksForRfc(String rfc){
    def clientLink = ClientLink.findByClientRef(rfc)
    clientLink?.delete()
    def providerLink = ProviderLink.findByProviderRef(rfc)
    providerLink?.delete()
  }

  def findBusinessEntityByKeyword(String keyword, String entity, Company company){
    if (!entity)
      entity=""
    def criteria = BusinessEntity.createCriteria()
    def list = criteria.listDistinct {
      or{
        like 'rfc', "%${keyword}%"
        names{
          like 'value', "%${keyword}%"
        }
      }

    }
    list.collect {
      if ((entity.equals("CLIENT") && clientService.isClientOfThisCompany(it, company))
          || (entity.equals("PROVIDER") && providerService.isProviderOfThisCompany(it, company))
          || (entity.equals("EMPLOYEE") && employeeService.isEmployeeOfThisCompany(it, company))
          || (entity.equals("") && (clientService.isClientOfThisCompany(it, company) || providerService.isProviderOfThisCompany(it, company) || employeeService.isEmployeeOfThisCompany(it, company)))
      )
        it
    }.findResults{it}.unique()
  }

  def getClientProviderType(String rfc){
    def providerLink = ProviderLink.findByProviderRef(rfc)
    def clientLink = ClientLink.findByClientRef(rfc)
    def employeeLink = EmployeeLink.findByEmployeeRef(rfc)
    if(providerLink && clientLink) return LeadType.CLIENTE_PROVEEDOR
      if(clientLink) return LeadType.CLIENTE
        if(providerLink) return LeadType.PROVEEDOR
          if (employeeLink) return LeadType.EMPLEADO
  }

  def updateNamesToBusinessEntity(BusinessEntity businessEntity, String[] names) {
    businessEntity.names.each{
      if(it.type == NameType.NOMBRE){
        it.value = names[0]
      }
    }
    businessEntity.names.each{
      if(it.type == NameType.APELLIDO_PATERNO){
        it.value = names[1]
      }
    }
    businessEntity.names.each{
      if(it.type == NameType.APELLIDO_MATERNO){
        it.value = names[2]
      }
    }
    businessEntity.save()
    businessEntity
  }

  def updateDataToBusinessEntity(BusinessEntity businessEntity, String businessName) {
    businessEntity.names.each{
      if(it.type == NameType.RAZON_SOCIAL){
        it.value = businessName
      }
    }
    businessEntity.save()
    businessEntity
  }

}
