package com.cte

import grails.transaction.Transactional

@Transactional
class ManagerApplicationService {

  def restService
  def grailsApplication
  def documentService
  def modulusUnoService

  def acceptingCompanyToIntegrate(Long companyId) {
    def company = Company.findById(companyId)
    company.status = CompanyStatus.ACCEPTED
    log.info company.dump()
    // TODO: Mandarlo a un servicio para quitarle el flush, no deberíamos tener flush y si debería persistir
    company.save(flush:true)
    // TODO: Mandar la notificación a un método privado
    def url = "${grailsApplication.config.accepting.integrated}${company.id}"
    def messageNotification = createEmailNotification(company,"La Empresa ${company}, ha sido aprovada para formar parte de la Integradora",url)
    createNewPasswordForLegalRepresentatives(company)
    restService.sendCommand(messageNotification,grailsApplication.config.emailer.notificationIntegrated)
    company
  }

  def rejectedCompanyToIntegrate(Company company, Map params) {
    def listOfRowsRejected = findCompanyRejectedLogsByStatus(company.id,true)
    changeStatusInCompanyRejectrLogRows(listOfRowsRejected)
    createCompanyRejectedLogsForDocuments(company,params)
    createCompanyRejectedLogsForLegalRepresentatives(company,params)
    createCompanyRejectedLogsForOtherReason(company,params.reason)
    def url = "${grailsApplication.config.rejected.integrated}${company.id}"
    def messageNotification = createEmailNotification(company,"La Empresa ${company}, ha sido rechazada por diversos motivos",url)
    restService.sendCommand(messageNotification,grailsApplication.config.emailer.notificationIntegrated)
    company.status = CompanyStatus.REJECTED
    company.save()
    company
  }

  def getMapOfUsersWithDocuments(def listOfLegalRepresentatives,def companyId) {
    def mapUserWithDocuments =  [:]
    listOfLegalRepresentatives.each { user ->
      def documents = user.profile.documents
      def documentsOfUserByCompany = documentService.getDocumentsByCompanyForLegalRepresentative(documents,companyId)
      mapUserWithDocuments.put(user,documentsOfUserByCompany)
    }
    mapUserWithDocuments
  }

  def obtainReasonOfRejectedCompanyRequestByStatus(Company company,Boolean status) {
    def rowOfReasonsOfRejected = CompanyRejectedLog.withCriteria {
      eq "companyId", company.id
      eq "status", status
    }
    rowOfReasonsOfRejected
  }

  //TODO:realizar refactor de la cracion de log
  private def createCompanyRejectedLogsForLegalRepresentatives(Company company,def params) {
    def idsLegalRepresentatives = params.legalRepresentatives.tokenize(",")
    idsLegalRepresentatives.each{ idUser ->
      def idsDocumentsLegalRepresentative = params."legalRepresentativeDocuments-${idUser}".tokenize(",")
      idsDocumentsLegalRepresentative.each{ idDoc ->
        if (params?."legalRepresentative-${idDoc}") {
          def companyRejectedLog = new CompanyRejectedLog()
          companyRejectedLog.companyId = company.id
          companyRejectedLog.reason = params?."legalRepresentative-${idDoc}"
          companyRejectedLog.typeClass = "legalRepresentative"
          companyRejectedLog.status = true
          companyRejectedLog.assetId = idDoc.toLong()
          companyRejectedLog.save()
        }
      }
    }
  }

  private def createCompanyRejectedLogsForDocuments(Company company, def params) {
    def listOfIdDocuments = params.documents.tokenize(",")
    listOfIdDocuments.each{ id ->
      if (params?."document-${id}") {
        def companyRejectedLog = new CompanyRejectedLog()
        companyRejectedLog.companyId = company.id
        companyRejectedLog.reason = params?."document-${id}"
        companyRejectedLog.typeClass = "document"
        companyRejectedLog.status = true
        companyRejectedLog.assetId = id.toLong()
        companyRejectedLog.save()
      }
    }
  }

  private def createCompanyRejectedLogsForOtherReason(Company company,String reason) {
    if(reason) {
      def companyRejectedLog = new CompanyRejectedLog()
      companyRejectedLog.companyId = company.id
      companyRejectedLog.reason = reason
      companyRejectedLog.typeClass = "company"
      companyRejectedLog.status = true
      companyRejectedLog.save()
    }
  }

  private def createNewPasswordForLegalRepresentatives(Company company) {
    def legalRepresentatives = company.legalRepresentatives
    def bussinesName = company.bussinessName
    legalRepresentatives.each{ user ->
      def rfcUser =  user.profile.rfc
      def newPassword = "${bussinesName}${rfcUser}".replaceAll("\\s","")
      createAccessToLegalRepresentative(company,user)
      user.password = newPassword
      user.accountLocked = true
      user.save()
    }

  }

  private def createAccessToLegalRepresentative(company, user) {
    def token = UUID.randomUUID().toString().replaceAll('-','')
    def baseUrl = grailsApplication.config.first.access.register
    def firstAccess = new FirstAccessToLegalRepresentatives()
    firstAccess.urlVerification = "${baseUrl}${token}"
    firstAccess.user = user
    firstAccess.company = company
    firstAccess.token = token
    firstAccess.enabled = true
    firstAccess.save()
  }


  private def createEmailNotification(company,message,url) {
    def userToNotify = company.actors.first()
    def emailNotificationCommand = new EmailNotificationToIntegratedCommand()
    emailNotificationCommand.emailResponse = userToNotify.profile.email
    emailNotificationCommand.nameCompany = company.toString()
    emailNotificationCommand.message = message
    emailNotificationCommand.url = url
    emailNotificationCommand
  }

  private def findCompanyRejectedLogsByStatus(companyId,status) {
    CompanyRejectedLog.withCriteria {
      eq 'companyId',companyId
      eq 'status',status
    }
  }

  //TODO:ajustar falta de ortografia
  private def changeStatusInCompanyRejectrLogRows(listOfRows) {
    listOfRows.each { companyRejected ->
      companyRejected.status = false
      companyRejected.save()
    }
  }

}
