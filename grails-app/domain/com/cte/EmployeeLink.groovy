package com.cte

class EmployeeLink {

  String type
  String employeeRef

  static belongsTo = [company:Company]

  static constraints = {
  }

}
