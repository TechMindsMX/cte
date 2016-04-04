package com.cte

class BankTagLib {
  static namespace = "bankLib"
  static defaultEncodeAs = "raw"

  def bankService

  def getBankFromClabe = { attrs ->
    out << bankService.computeClabeAndGetBank(attrs.clabe)
  }

}
