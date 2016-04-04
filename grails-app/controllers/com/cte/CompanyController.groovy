package com.cte


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import java.text.SimpleDateFormat
import java.util.Calendar
import static java.util.Calendar.*

@Transactional(readOnly = true)
class CompanyController {

  def springSecurityService
  def companyService
  def userService
  def documentService
  def clientService
  def providerService
  def managerApplicationService
  def modulusUnoService

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    def user = springSecurityService.currentUser
    def companies = companyService.allCompaniesByUser(user)
    respond companies, model:[companiesCount: companies?.size()]
  }

  def show(Company company) {
    // TODO: Posible error - Cannot invoke method refresh() on null object
    company.refresh()
    def balance
    def usd
    if (company.status != CompanyStatus.VALIDATE) {
      params.sepomexUrl = grails.util.Holders.grailsApplication.config.sepomex.url
      if (company.status == CompanyStatus.ACCEPTED) {
        if (company.accounts){
          (balance, usd) = modulusUnoService.consultBalanceOfAccount(company?.accounts?.first()?.timoneUuid)
        }
      }
      def isAvailable = companyService.isEnableToSendNotificationIntegrated(company)
        def legalRepresentativesWithDocuments = true
        if (company.taxRegime == CompanyTaxRegime.MORAL)
          legalRepresentativesWithDocuments = userService.containsUsersWithDocumentsByCompany(company.legalRepresentatives,company)

          respond company, model:[legalRepresentativesAvilableWithDocuments:legalRepresentativesWithDocuments, clients:clientService.getClientsFromCompany(company),providers:providerService.getProvidersFromCompany(company),available:isAvailable,balance:balance,usd:usd]
    } else {
      flash.message = message(code: 'company.blocket.validate')
      redirect(action:"index")
    }
  }

  def create() {
    respond new Company(params)
  }

  def uploadDocumentsUser() {
    def company = Company.findById(session.company.toLong())
    render view:"/uploadDocuments/uploadDocumentsUser",model:[company:company]
  }

  @Transactional
  def save(Company company) {
    if (company == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    if(company.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond company.errors, view:'create'
      return
    }
    def user = springSecurityService.currentUser
    company.addToActors(user)

    if (!company.save(flush:true)){
      company.errors.each(){
        println it
      }
    }

    session['company'] = company.id


    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.created.message', args: [message(code: 'company.label', default: 'Company'), company.id])
        redirect company
      }
      '*' { respond company, [status: CREATED] }
    }
  }

  def edit(Company company) {
    respond company,model:[edit:true]
  }

  @Transactional
  def update(Company company) {
    if (company == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    if (company.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond company.errors, view:'edit'
      return
    }

    company.save flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.updated.message', args: [message(code: 'company.label', default: 'Company'), company.id])
        redirect company
      }
      '*'{ respond company, [status: OK] }
    }
  }

  def setCompanyInSession() {
    def company = params.company
    session['company'] = company
    def companyInfo = Company.findById(company.toLong())
    if (companyInfo.status != CompanyStatus.ACCEPTED) {
      redirect(action:"show",id:"${company}")
      return
    }
    redirect(action:"accountstatement")
  }

  def rejected(Company company) {
    def rowsOfReasons = managerApplicationService.obtainReasonOfRejectedCompanyRequestByStatus(company,true)
    def rowDocuments = rowsOfReasons.findAll {row -> row.typeClass == "document"}
    def rowRepresentatives = rowsOfReasons.findAll {row -> row.typeClass == "legalRepresentative"}
    def rowCompany = rowsOfReasons.find {row -> row.typeClass == "company"}
    def documentsByUser = managerApplicationService.getMapOfUsersWithDocuments(company.legalRepresentatives,company.id)
    [company:company,legalRepresentatives:documentsByUser,reasonDocuments:rowDocuments,reasonRepresentatives:rowRepresentatives,reasonCompany:rowCompany]
  }

  @Transactional
  def delete(Company company) {

    if (company == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    company.delete flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'company.label', default: 'Company'), company.id])
        redirect action:"index", method:"GET"
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'company.label', default: 'Company'), params.id])
        redirect action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }
}
