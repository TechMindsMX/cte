package com.cte

import grails.transaction.Transactional

@Transactional
class ProcessorPayrollService {

    def createRelationIntoPayrolls(List payRolls,String nameFile,Company company) {
      def processorPayroll = new ProcessorPayroll()
      processorPayroll.nameFile = nameFile
      processorPayroll.company = company
      payRolls.each { payroll ->
        processorPayroll.addToPayrolls(payroll)
      }
      processorPayroll.save()
    }
}
