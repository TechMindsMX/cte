package com.cte

class ManagerApplicationController {

  def companyService
  def managerApplicationService
  def documentService

  // TODO: Refactor, se está obteniendo todas las compañias sin paginación, se tienen que hacer conteos por consulta
  // no por tamaño de lista obtenida
  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    def companiesForValidation = companyService.allCompaniesByStatus(CompanyStatus.VALIDATE)
    def companiesRejected = companyService.allCompaniesByStatus(CompanyStatus.REJECTED)
    def companiesAccepted = companyService.allCompaniesByStatus(CompanyStatus.ACCEPTED)
    [
    companiesForValidation:companiesForValidation,
    companiesRejected:companiesRejected,
    companiesAccepted:companiesAccepted
    ]
  }

  def show(Company company) {
    def documentsByUser = managerApplicationService.getMapOfUsersWithDocuments(company.legalRepresentatives,company.id)
    [company:company,legalRepresentatives:documentsByUser]
  }

  def accepted() {
    def company = managerApplicationService.acceptingCompanyToIntegrate(params.long('companyId'))
    redirect(action:"index")
  }

  def list() {
    def companies = companyService.allCompaniesByStatus(CompanyStatus.ACCEPTED)
    render view:"/company/index", model:[companyList:companies,companyCout:companies.size()]
  }

  def invalid() {
    [company:params.companyId]
  }

  def rejected() {
    def company = Company.findById(params.companyId)
    managerApplicationService.rejectedCompanyToIntegrate(company,params)
    redirect action:'index'
  }

  def genereDocumentOfAccount(Company company) {
    def listOfFirstAccess = FirstAccessToLegalRepresentatives.findAllByCompany(company)
    renderPdf(template: "/documentTemplates/accountActivation", model: [accountsLegalReporesentatives: listOfFirstAccess])
  }

  def search() {
    def companyList = Company.findAllByStatusNotEquals(CompanyStatus.CREATED)
    [companies: companyList]
  }

  def obtainCompanyByFilters() {
    def companies = companyService.listCompanyByFilters(params)
    render view:"search", model:[companies:companies]
  }

}
