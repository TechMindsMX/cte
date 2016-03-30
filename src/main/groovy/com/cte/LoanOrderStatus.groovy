package com.cte

enum LoanOrderStatus {
  CREATED("loanorder.created"),
  VALIDATE("loanorder.validate"),
  AUTHORIZED("loanorder.authorized"),
  REJECTED("loanorder.rejected"),
  EXECUTED("loanorder.executed")

  private final String code

  LoanOrderStatus(String code){
    this.code = code
  }

  String getCode(){ return this.code }
}
