package com.cte

class ProcessorPayroll {

  String nameFile

  Date dateCreated
  Date lastUpdated

  static hasMany = [payrolls:Payroll]

  static constraints = {
    nameFile nullable:false
  }
}
