package com.cte

import grails.transaction.Transactional

@Transactional
class ProcessorPayrollService {

    def createRelationIntoPayrolls(List payRolls,String nameFile) {
      def processorPayroll = new ProcessorPayroll()
      processorPayroll.nameFile = nameFile
      payRolls.each { payroll ->
        processorPayroll.addToPayrolls(payroll)
      }
      processorPayroll.save()
    }
}
