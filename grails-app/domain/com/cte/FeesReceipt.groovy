package com.cte

class FeesReceipt {
  BigDecimal amount = 0.00
  BigDecimal iva = 0.00
  BigDecimal isr = 0.00
  BigDecimal ivaWithHolding = 0.00

  FeesReceiptStatus status = FeesReceiptStatus.CREADA
  RejectReason rejectReason
  String comments

  static belongsTo = [businessEntity:BusinessEntity]
  static hasMany = [documents:S3Asset]

  static constraints = {
    amount min:0.1,max:250000000.00
    iva min:0.0,max:250000000.00
    isr min:0.0,max:250000000.00
    ivaWithHolding min:0.0,max:250000000.00
    rejectReason nullable:true
    comments nullable:true
  }

  void setAmount(BigDecimal amount){
    this.amount = amount
    this.iva = amount * 0.16
    this.ivaWithHolding = iva * 2 / 3
    this.isr = amount * 0.1
  }

}
