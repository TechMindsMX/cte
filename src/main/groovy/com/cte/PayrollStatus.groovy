package com.cte

enum PayrollStatus {

  CALENDARIZED("payroll.calendarized"),
  ACCEPTED("payroll.accepted")

  private final String code

  PayrollStatus(String code) {
    this.code = code
  }

  String getCode(){ return this.code }
}
