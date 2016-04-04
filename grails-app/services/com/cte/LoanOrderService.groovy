package com.cte

import grails.transaction.Transactional

@Transactional
class LoanOrderService {

  def springSecurityService

  // TODO: Refactor: código duplicado
  def createRequestLoanOrder(Long companyId, BigDecimal amount) {
    Company company = Company.get(companyId)
    LoanOrder loanOrder = new LoanOrder(amount:amount,type:LoanOrderType.DEBTOR)
    loanOrder.status = LoanOrderStatus.VALIDATE
    company.addToLoanOrders(loanOrder)
    company.save()
    loanOrder
  }

  def createOfferLoanOrder(Long companyId, BigDecimal amount) {
    Company company = Company.get(companyId)
    LoanOrder loanOrder = new LoanOrder(amount:amount,type:LoanOrderType.CREDITOR)
    loanOrder.status = LoanOrderStatus.VALIDATE
    company.addToLoanOrders(loanOrder)
    company.save()
    loanOrder
  }

  // TODO: Refactor
  def addAuthorizationToLoanOrder(Long loanOrderId, User user){
    LoanOrder order = LoanOrder.get(loanOrderId)
    def authorization = new Authorization(user:user)
    order.addToAuthorizations(authorization)
    order.save()
    if (isFullAuthorized(order)){
      order.status = LoanOrderStatus.AUTHORIZED
      order.save()
      //depositOrderService.notifyAuthorizationDepositOrder(order)
      // TODO: Notificar
    }
    authorization
  }

  private def isFullAuthorized(order){
    (order.authorizations?.size() ?: 0) >= order.company.numberOfAuthorizations
  }
}
