package com.cte

class Bank {

  String bankingCode
  String name

  static constraints = {
    bankingCode blank:false
    name blank:false
  }

  String toString(){
    name
  }
}
