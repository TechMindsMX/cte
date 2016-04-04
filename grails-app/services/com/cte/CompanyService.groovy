package com.cte

import grails.transaction.Transactional

@Transactional
class CompanyService {

  def addingActorToCompany(Company company, User user) {
    company.addToActors(user)
    company.save()
  }

  def addingLegalRepresentativeToCompany(Company company, User user){
    company.addToLegalRepresentatives(user)
    company.save()
  }

  def allCompaniesByUser(User user){
    Company.createCriteria().list {
      actors {
        eq 'username', user.username
      }
    }
  }

  // TODO: Se tienen que aceptar los parámetros de limit y offset para que se ejecute la consulta paginada
  def allCompaniesByStatus(status) {
    Company.createCriteria().list {
     eq 'status', status
    }
  }

  def createAddressForCompany(Address address, Long companyId){
    def company = Company.get(companyId)
    company.addToAddresses(address)
    company.save()
    company
  }

  def createLegalRepresentativeOfCompanyPhysicalRegime(Company company){
    def tks = company.bussinessName.tokenize(' ')
    String username = tks[0]
    if (tks.size()>1)
      username += "_"+tks[1]
    String mail = tks[0]+"@"+tks[0]+"mail.com"
    Profile profile = new Profile(name:tks[0], lastName:tks[1] ?: "SA", motherLastName:tks[2] ?: "SA", email:mail, rfc: company.rfc)

    User user = new User(username:username, password:"Temporal12345", enabled:false, accountExpired:false, accountLocked:false, passwordExpired:false, profile:profile)

    if (!user.validate()){
      user.errors.each(){
        println it
      }
    }
    user.save flush:true

    company.addToLegalRepresentatives(user)
    company
  }

  def createS3AssetForCompany(S3Asset asset, def companyId) {
    def company = Company.findById(companyId)
    company.addToDocuments(asset)
    company.save()
    company
  }

  def findCompaniesForThisUser(User user) {
    def companiesLegalRepresentatives =  Company.withCriteria {
      legalRepresentatives {
        eq 'username', user.username
      }
    }
    def companiesActors = Company.withCriteria {
      actors {
        eq 'username',user.username
      }
    }
    def companies = []
    companies << companiesLegalRepresentatives ?: ""
    companies << companiesActors ?: ""
    companies.flatten().unique()
  }

  def getAuthorizersByCompany(Company company) {
  	company.actors.findAll { user ->
      if (user.authorities.any { it.authority == "ROLE_INTEGRADO_AUTORIZADOR" })
        return user
    }
  }

  def getNumberOfAuthorizersMissingForThisCompany(Company company) {
    def numberOfAuthorizations = company.numberOfAuthorizations
    def authorizers = getAuthorizersByCompany(company)
    if (authorizers.size() >= numberOfAuthorizations)
      return 0
    else
      numberOfAuthorizations - authorizers.size()
  }

  boolean isEnableToSendNotificationIntegrated(Company company) {
    int docsMin = 4
    if (company.taxRegime == CompanyTaxRegime.FISICA_EMPRESARIAL)
      docsMin = 5
    if (company.banksAccounts && company.addresses && company.documents.size() == docsMin && (company.status == CompanyStatus.CREATED || company.status == CompanyStatus.REJECTED ))
      return true
    false
  }

  def listCompanyByFilters(def queryFilters) {
    def companies = Company.where {
        status != CompanyStatus.CREATED
    }.list()

    if (queryFilters.status)
        companies = companies.findAll { it.status == CompanyStatus."${queryFilters.status}" }
    if (queryFilters.bussinessName)
        companies = companies.findAll { it.bussinessName == queryFilters.bussinessName }
    if (queryFilters.rfc)
        companies = companies.findAll { it.rfc == queryFilters.rfc }

    companies
  }

}
