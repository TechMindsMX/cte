package com.cte

class LoanOrderController {

  LoanOrderService loanOrderService
  def springSecurityService

  def index() {
    respond LoanOrder.list()
  }

  def list(){
    def company = Company.get(session.company ?: params.companyId)
    respond LoanOrder.findAllByCompany(company)
  }

  def requestLoan() {
  }

  def createRequestLoan() {
    LoanOrder loanOrder = loanOrderService.createRequestLoanOrder(session.company, new BigDecimal(params.int('amount')))
    redirect action:'list'
  }

  def offerLoan(){
  }

  def createOfferLoan(){
    LoanOrder loanOrder = loanOrderService.createOfferLoanOrder(session.company, new BigDecimal(params.int('amount')))
    redirect action:'list'
  }

  def authorize(){
    loanOrderService.addAuthorizationToLoanOrder(params.long('id'), springSecurityService.currentUser)
    redirect action:'index'
  }

}
