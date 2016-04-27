package com.cte

class DepositOrder {

  BigDecimal amount
  DepositOrderStatus status = DepositOrderStatus.CREATED
  String uuidTransaction
  RejectReason rejectReason
  String rejectComment

  Date dateCreated
  Date lastUpdated

  static belongsTo = [company: Company]
  static hasMany = [documents:S3Asset, authorizations: Authorization]

  static constraints = {
    amount nullable:false
    uuidTransaction nullable:true,blank:true
    rejectReason nullable:true
    rejectComment nullable:true, blank:true
  }

}
