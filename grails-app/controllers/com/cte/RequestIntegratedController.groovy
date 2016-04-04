package com.cte

class RequestCompanyController {

  def springSecurityService
  def requestIntegratedService

  def create() {
    def company = Company.findById(session.company.toLong())
    def currentUser = springSecurityService.currentUser
    def companyWithNotification = requestIntegratedService.sendNotificationsOFCompanyToIntegrated(company, currentUser)
    redirect controller:'logout'
  }

}
