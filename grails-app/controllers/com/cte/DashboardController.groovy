package com.cte

class DashboardController {

  def companyService
  def springSecurityService

  def index() {
    def user = springSecurityService.currentUser
    def companyList = companyService.allCompaniesByUser(user)
    if (companyList.isEmpty()) {
      [companies:companyList, companiesCount: companyList.size()]
      return
    } else {
      redirect action:"iecce"
    }
  }

}
