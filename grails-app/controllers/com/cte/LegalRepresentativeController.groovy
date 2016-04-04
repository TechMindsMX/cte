package com.cte

import grails.transaction.Transactional

@Transactional(readOnly = true)
class LegalRepresentativeController {

  def legalRepresentativeService
  def springSecurityService

  def index(){
    [currentUser:springSecurityService.currentUser,
    isCurrentLegalRepresentative:legalRepresentativeService.isCurrentUserLegalRepresentativeOfCompany(Company.get(session.company.toLong()))]
  }

  @Transactional
  def addRepresentative(){
    Company company = Company.get(session.company)
    User user = springSecurityService.currentUser
    if (company.taxRegime == CompanyTaxRegime.MORAL)
      user = User.get(params.long("user"))

      def legal = legalRepresentativeService.assignLegalRepresentativeToCompany(company.id,user.id)
      redirect(controller:"user",action:"edit",id:legal.id, params:["company":"${company.id}"])
  }

  def search(){
    def company = Company.get(session.company)
    def user = legalRepresentativeService.findUserByRFC(params.rfc,company)
    if(!user)
      flash.message = "El representante legal no se encuentra o ya ha sido asignado a esta empresa."

      render view:'index',model:[companyId:company.id,
      currentUser:springSecurityService.currentUser,
      isCurrentLegalRepresentative:legalRepresentativeService.isCurrentUserLegalRepresentativeOfCompany(company),
      user:user]
  }
}
