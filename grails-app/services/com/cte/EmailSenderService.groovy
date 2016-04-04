package com.cte

import grails.transaction.Transactional

@Transactional
class EmailSenderService {

  def restService
  def grailsApplication
  def companyService
  def userService

  def emailIntegratedCommand(String message, String url, String nameCompany, def email){
    def emailCommand = new EmailNotificationToIntegratedCommand()
    emailCommand.emailResponse = email
    emailCommand.nameCompany = nameCompany
    emailCommand.message = message
    emailCommand.url = url
    emailCommand
  }

  def sendNewClientProviderNotificaton(Company company, String name, EmailerMessageType type) {
    company.legalRepresentatives.each { legal ->
      def message = new NameCommand(email:legal.profile.email, name:name, company:company.bussinessName, type:type)
      restService.sendCommand(message, grailsApplication.config.emailer.clientProvider)
    }
  }

}
