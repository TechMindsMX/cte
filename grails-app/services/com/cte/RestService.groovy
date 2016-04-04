package com.cte
import groovyx.net.http.RESTClient
import com.cte.RestException
import groovy.json.JsonSlurper

import static groovyx.net.http.ContentType.URLENC

class RestService {

  def grailsApplication
  def restClientBean

  def getOnModulus(MessageCommand message, String template) {
    try {
      log.info "Calling Service : ${template}"
      restClientBean.uri = grailsApplication.config.modulus.url
      def resultGet = restClientBean.get(
        path: "${template}/${message.uuid}"
      )
      resultGet
    } catch(Exception ex) {
      log.warn "Error ${ex.message}"
      throw new RestException(ex.message)
    }
  }

  def sendCommand(MessageCommand message, String template){
    try{
      log.info "CALLING Email service: ${template}"
      log.info message.dump()
      restClientBean.uri = grailsApplication.config.emailer.url
      restClientBean.post(
        path: template,
        body: message,
        requestContentType: 'application/json' )
    } catch(Exception ex) {
      log.warn "Error: ${ex.message}"
      throw new RestException(ex.message)
    }
  }

  def sendCommandWithAuth(MessageCommand message, String template){
    try{
      log.info "CALLING Modulusuno service: ${template}"
      String token = obtainingTokenForCreateAccountOfModulusUno()
      def modulusResponse = generatingAccountOfModulusUno(message,template,token)
      log.info "Return Information of modulus uno account to service"
      modulusResponse
    } catch(Exception ex) {
      log.warn "Error: ${ex.message}"
      throw new RestException(ex.message)
    }
  }


  private def getAuthMap(){
    [
      username:grailsApplication.config.modulus.username,
      password:grailsApplication.config.modulus.password,
      client_id:grailsApplication.config.modulus.clientId,
      client_secret:grailsApplication.config.modulus.secret,
      grant_type:grailsApplication.config.modulus.grant
    ]
  }

  private def obtainingTokenForCreateAccountOfModulusUno() {
    log.info "Creating RestTemplate Object for obtain token"
    restClientBean.uri = grailsApplication.config.modulus.url
    log.info "Calling Modulus Uno service"
    def tokenResponse = restClientBean.post(
      path: grailsApplication.config.modulus.token,
      body: getAuthMap(),
      requestContentType: URLENC
      )
    log.info "Return token obtained ${tokenResponse.dump()}"
    tokenResponse.responseData.access_token
  }

  private def generatingAccountOfModulusUno(MessageCommand message,String template,String token) {
    log.info "Creating RestTemplate object for create Modulus Uno Account"
    restClientBean.uri = grailsApplication.config.modulus.url
    log.info "Calling service for create account of Modulus Uno"
    def modulusAccountResponse = restClientBean.post(
      path: template,
      headers: [Authorization:"Bearer ${token}"],
      body:message,
      requestContentType: 'application/json')
    log.info "Return of account and properties of Modulus Uno"
    modulusAccountResponse.responseData
  }

  //Metodos de consulta de facturacion
  def sendFacturaCommandWithAuth(MessageCommand message, String template){
    try{
      log.info "CALLING Modulusuno facturacion service: ${template}"
      String token = obtainingFacturaToken()
      def modulusResponse = callingFacturaService(message,template,token)
      log.info "Return Information of modulus uno"
      modulusResponse
    } catch(Exception ex) {
      log.warn "Error: ${ex.message}"
      throw new RestException(ex.message)
    }
  }

  private def obtainingFacturaToken() {
    log.info "Creating RestTemplate Object for obtain token"
    restClientBean.uri = grailsApplication.config.modulus.facturacionUrl
    log.info "Calling Modulus Uno service for token"
    def tokenResponse = restClientBean.post(
      path: grailsApplication.config.modulus.token,
      body: getAuthMap(),
      requestContentType: URLENC
      )
    log.info "Return token obtained ${tokenResponse.dump()}"
    tokenResponse.responseData.access_token
  }

  private def callingFacturaService(MessageCommand message,String template,String token) {
    log.info "Creating RestTemplate object for create Modulus Uno facturacion"
    restClientBean.uri = grailsApplication.config.modulus.facturacionUrl
    log.info "Calling service for creating factura"
    def modulusAccountResponse = restClientBean.post(
      path: template,
      headers: [Authorization:"Bearer ${token}"],
      body:message,
      requestContentType: 'application/json')
    log.info "Returning factura properties of Modulus Uno"
    modulusAccountResponse.responseData
  }

  def getTransactionsAccount(MessageCommand command){
    try {
      log.info "Calling Service : services/integra/tx/getTransactions"
      //String token = obtainingTokenForCreateAccountOfModulusUno()
      restClientBean.uri = grailsApplication.config.modulus.url
      def resultGet = restClientBean.get(
        path: "services/integra/tx/getTransactions/${command.uuid}/${command.begin}/${command.end}"
      )
      resultGet.responseData
    } catch(Exception ex) {
      log.warn "Error ${ex.message}"
      throw new RestException(ex.message)
    }
  }
}
