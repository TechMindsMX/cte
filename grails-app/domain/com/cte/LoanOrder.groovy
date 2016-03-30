package com.cte

class LoanOrder {

  BigDecimal amount = new BigDecimal(0)
  LoanOrderType type
  LoanOrderStatus status = LoanOrderStatus.CREATED

  static belongsTo = [company:Company]
  static hasMany = [authorizations: Authorization]

  Date dateCreated
  Date lastUpdated

  static constraints = {
    amount min:new BigDecimal(0)
  }
}
