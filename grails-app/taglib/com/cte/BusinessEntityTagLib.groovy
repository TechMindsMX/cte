package com.cte

class BusinessEntityTagLib {
  static namespace = "my"
  static defaultEncodeAs = [taglib:'html']

  def clientService
  def providerService
  def employeeService

  def whatIsThisBusinessEntity = { attrs ->
    out <<  (clientService.isClient(attrs.be) ? "Cliente" : " ")
    out <<  (providerService.isProvider(attrs.be) ? "Proveedor" : "")
    out <<  (employeeService.isEmployee(attrs.be) ? "Empleado" : "")
  }

}
