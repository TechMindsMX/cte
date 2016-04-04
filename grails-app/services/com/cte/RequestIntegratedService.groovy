package com.cte

import grails.transaction.Transactional

@Transactional
class RequestIntegratedService {

  def restService
  def grailsApplication

  def sendNotificationsOFCompanyToIntegrated(Company company,User user) {
    def urlAdmin = "${grailsApplication.config.rejected.manager.integrated}${company.id}"
    def messageUser = createEmailNotificationToUser(user,company)
    def messageAdmin = createEmailNotificacionToAdmin(company,urlAdmin)
    restService.sendCommand(messageUser, grailsApplication.config.emailer.notificationIntegrated)
    restService.sendCommand(messageAdmin, grailsApplication.config.emailer.notificationIntegrated)
    company.status = CompanyStatus.VALIDATE
    company.save()
    company
  }

  private def createEmailNotificationToUser(user,company) {
    def emailNotificationCommand = new EmailNotificationToIntegratedCommand()
    emailNotificationCommand.emailResponse = user.profile.email
    emailNotificationCommand.nameCompany = company.toString()
    emailNotificationCommand.message = "Tu Empresa se encuentra en proceso de validacion para ser integrada"
    emailNotificationCommand
  }

  private def createEmailNotificacionToAdmin(company,url) {
    def emailNotificationAdminCommand = new EmailNotificationToIntegratedCommand()
    emailNotificationAdminCommand.emailResponse = grailsApplication.config.emailer.emailAdmin
    emailNotificationAdminCommand.nameCompany = company.toString()
    emailNotificationAdminCommand.message = "La Empresa ${company.toString()}, desea ingregar como empresa integrada"
    emailNotificationAdminCommand.url = url
    emailNotificationAdminCommand
  }

}
