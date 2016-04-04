package com.cte

import grails.transaction.Transactional

@Transactional
class PayrollService {

  def schedulingPayrolls(def rowListOfDocument) {
    def payrollList = []
    rowListOfDocument.each { pay ->
      if (pay[0]) {
        def businessEntity = findBusinessEntityByComposeNameOfNumeroEmpleado(pay[0])
        if (businessEntity) {
          def amount = pay[1]
          def applyDate = pay[2] ? new Date(pay[2]): new Date()
          def payRoll = new Payroll(employee:businessEntity, amount:amount,applyDate:applyDate).save()
          payrollList.add(payRoll)
        }
      }
    }
    payrollList
  }

  private BusinessEntity findBusinessEntityByComposeNameOfNumeroEmpleado(def numeroEmpleado) {
    def composeName = ComposeName.findByValueAndType(numeroEmpleado,NameType.NUMERO_EMPLEADO)
    BusinessEntity.findById(composeName?.businessEntity?.id)
  }

}
