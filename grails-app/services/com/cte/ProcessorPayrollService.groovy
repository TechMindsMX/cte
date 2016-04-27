package com.cte

import grails.transaction.Transactional

@Transactional
class ProcessorPayrollService {

  def restService
  def grailsApplication

    def createRelationIntoPayrolls(List payRolls,String nameFile,Company company) {
      def processorPayroll = new ProcessorPayroll()
      processorPayroll.nameFile = nameFile
      processorPayroll.company = company
      payRolls.each { payroll ->
        processorPayroll.addToPayrolls(payroll)
      }
      processorPayroll.save()
      notificationToAdminTheNewProcessorPayRoll(processorPayroll)
      processorPayroll
    }

    def notificationToAdminTheNewProcessorPayRoll(ProcessorPayroll processorPayroll) {
      def message = createEmailNotification(processorPayroll,"la Empresa ${processorPayroll.company}, ha generado un proceso de dispercion de fondos")
      restService.sendCommand(message,grailsApplication.config.emailer.notificationProcessorPayroll)
    }

    private def createEmailNotification(ProcessorPayroll processor, String message) {
      def emailNotificationCommand = new EmailNotificationToIntegratedCommand()
      emailNotificationCommand.emailResponse = grailsApplication.config.emailer.emailAdmin
      emailNotificationCommand.message = message
      emailNotificationCommand.url = grailsApplication.config.url.processorPayrolls
      emailNotificationCommand
    }
}
