package com.cte

class ProcessorPayroll {

  String nameFile

  Company company

  Date dateCreated
  Date lastUpdated

  static hasMany = [payrolls:Payroll]

  static constraints = {
    nameFile nullable:false
  }
}
