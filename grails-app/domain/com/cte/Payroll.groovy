package com.cte

class Payroll {

  BusinessEntity employee
  BigDecimal amount
  Date applyDate
  PayrollStatus status = PayrollStatus.CALENDARIZED

  Date dateCreated
  Date lastUpdated

  static constraints = {
    amount nullable:false
    applyDate nullable:false
  }

  String toString(){
    employee.toString()
  }

}
