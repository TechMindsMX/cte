package com.cte

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional(readOnly = false)
class BusinessEntityController {

  def businessEntityService
  def clientService
  def providerService
  def restService
  def springSecurityService
  def employeeService

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def create() {
    respond new BusinessEntity(params), model:[clientProviderType:params.type,banks:Bank.list().sort{ it.name }]
  }

  def createMultiEmployees() {}

  @Transactional
  def delete(BusinessEntity businessEntity) {

    if (businessEntity == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    businessEntity.delete flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'businessEntity.label', default: 'BusinessEntity'), businessEntity.id])
        redirect action:"index", method:"GET"
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  def edit(BusinessEntity businessEntity) {
    String clientProviderType = businessEntityService.getClientProviderType(businessEntity.rfc)
    respond businessEntity, model:[clientProviderType:clientProviderType]
  }

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    def roles = springSecurityService.getPrincipal().getAuthorities()
    def company
    if (Role.findByAuthority(roles.first()) == Role.findByAuthority("ROLE_ADMIN"))
      company = Company.findById(params.company)
    else
      company = Company.findById(session.company.toLong())
      max = Math.min(max ?: 10, 100)
      def offset = params.offset? params.offset.toInteger() : 0
      def total = company.businessEntities.size()
      def allBusinessEntitiesCompany = company.businessEntities.toList().sort{it.id}
    def businessEntityList = allBusinessEntitiesCompany.subList(Math.min(offset, total), Math.min(offset+max,total))

    respond businessEntityList, model:[businessEntityList:businessEntityList, businessEntityCount: total]
  }

  @Transactional
  def save(BusinessEntityCommand command) {
    command.clientProviderType = params.clientProviderType
    if (params.clientProviderType.equals("EMPLEADO")){
      command.website="http://www.employee.com"
      command.type = BusinessEntityType.FISICA
      params.persona = 'fisica'
    }

    if (command.hasErrors()) {
      BusinessEntity businessEntity = new BusinessEntity(command.properties)
      render(view:'create', model:[command:command, businessEntity:businessEntity, clientProviderType:params.clientProviderType], params:params,banks:Bank.list().sort{ it.name })
      return
    }

    BusinessEntity businessEntity = new BusinessEntity(command.properties)
    def company = Company.findById(session.company.toLong())
    LeadType leadType = LeadType."${params.clientProviderType}"
    if(params.persona == 'fisica'){
      businessEntityService.appendNamesToBusinessEntity(businessEntity, (String[])[params.name, params.lastName, params.motherLastName,params.numeroEmpleado])
    } else {
      businessEntityService.appendDataToBusinessEntity(businessEntity, params.businessName)
    }

    if (params.numeroEmpleado && params.banco && params.clabe && params.cuenta){
      Map propertie = ["clabe":params.clabe]
      businessEntityService.createBankAccountAndAddToBusinesEntity(propertie,businessEntity)
    }

    if(leadType == LeadType.CLIENTE || leadType == LeadType.CLIENTE_PROVEEDOR){
      clientService.addClientToCompany(businessEntity, company)
    }
    if(leadType == LeadType.PROVEEDOR || leadType == LeadType.CLIENTE_PROVEEDOR){
      providerService.addProviderToCompany(businessEntity, company)
    }
    if(leadType == LeadType.EMPLEADO){
      businessEntity.website = null
      employeeService.addEmployeeToCompany(businessEntity, company)
    }


    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'businessEntity.created', args: [message(code: 'businessEntity.label', default: 'BusinessEntity'), businessEntity.id])
        redirect action: 'show', id:businessEntity.id
      }
      '*' { respond businessEntity, [status: CREATED] }
    }
  }

  def saveMultiEmployees() {
    def mapResultOfEmployeesCreate = employeeService.createAMultiEmployees(params.documentExcel,session.company.toLong())
    render view:"createMultiEmployees", model:[employeesSucces:mapResultOfEmployeesCreate[0],employeeFail:mapResultOfEmployeesCreate[1]]
  }

  def search(){
    def company = Company.get(params.long("company"))
    def businessEntityList = businessEntityService.findBusinessEntityByKeyword(params.rfc, null, company)
    if(businessEntityList.isEmpty()){
      flash.message = "No se encontr\u00F3 cliente o proveedor."
    }

    render view:'index',model:[companyId:company.id, businessEntityList:businessEntityList]
  }

  def show(BusinessEntity businessEntity) {
    params.sepomexUrl = grails.util.Holders.grailsApplication.config.sepomex.url
    LeadType relation = businessEntityService.getClientProviderType(businessEntity.rfc)
    respond businessEntity, model:[relation:relation.toString(),businessEntity:businessEntity]
  }

  @Transactional
  def update(BusinessEntity businessEntity) {
    if (businessEntity == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    if (businessEntity.hasErrors()) {
      transactionStatus.setRollbackOnly()
      params.edit = true
      String clientProviderType = businessEntityService.getClientProviderType(businessEntity.rfc)
      render  view:'edit', model:[businessEntity:businessEntity, clientProviderType:clientProviderType, params:params]
      return
    }

    def company = Company.findById(session.company.toLong())
    LeadType leadType = LeadType."${params.clientProviderType}"
    if(businessEntity.type == BusinessEntityType.FISICA){
      businessEntityService.updateNamesToBusinessEntity(businessEntity, (String[])[params.name, params.lastName, params.motherLastName])
    } else {
      businessEntityService.updateDataToBusinessEntity(businessEntity, params.businessName)
    }
    businessEntityService.deleteLinksForRfc(businessEntity.rfc)
    if(leadType == LeadType.CLIENTE || leadType == LeadType.CLIENTE_PROVEEDOR){
      clientService.addClientToCompany(businessEntity, company)
    }
    if(leadType == LeadType.PROVEEDOR || leadType == LeadType.CLIENTE_PROVEEDOR){
      providerService.addProviderToCompany(businessEntity, company)
    }

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'businessEntity.updated', args: [message(code: 'businessEntity.label', default: 'BusinessEntity'), businessEntity.id])
        redirect action: 'show', id:businessEntity.id
      }
      '*'{ respond businessEntity, [status: OK] }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'businessEntity.label', default: 'BusinessEntity'), params.id])
        redirect action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }
}
