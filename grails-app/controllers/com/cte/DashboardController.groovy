package com.cte

class DashboardController {

  def companyService
  def springSecurityService

  def index() {
    def user = springSecurityService.currentUser
    def companyList = companyService.allCompaniesByUser(user)
    [companies:companyList, companiesCount: companyList.size()]
  }

}
