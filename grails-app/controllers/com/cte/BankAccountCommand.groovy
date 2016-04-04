package com.cte

import grails.validation.Validateable

class BankAccountCommand implements Validateable {
  String accountNumber
  String branchNumber
  String clabe
  String bank

  BankAccount createBankAccount(){
    new BankAccount(
      accountNumber:this.accountNumber,
      branchNumber:this.branchNumber,
      clabe:this.clabe
    )
  }
}
