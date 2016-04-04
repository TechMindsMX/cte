package com.cte

import static org.springframework.http.HttpStatus.*

class RecoveryController {

  static allowedMethods = [actionPassword:"POST"]

  def recoveryService

  def index() {}

  def show() {
    recoveryService.obtainRegistrationCodeForToken(params.token)
    respond OK
  }

  def forgotPassword() {}

  def save() {
    def email = params.email
    recoveryService.generateRegistrationCodeForEmail(email)
    flash.message = g.message(code: 'login.email')
    redirect controller:'login', action:'auth'
  }

  def update(ChangePasswordCommand command) {
    if(command.hasErrors()) {
      respond command.errors, view:'show', id:params.id
      return
    }
    recoveryService.changePasswordForToken(params.id, command.password)
    flash.message = g.message(code: 'login.change.password')
    redirect controller:'login', action:'auth'
  }

  def activeAccountOfLegalRepresentative() {
    def statusToken = recoveryService.activateAccountByToken(params.token)
    if (statusToken) {
      flash.message = g.message(code: 'active.account.false')
      redirect controller:'login', action:'auth'
    }
    render view:"activePassword", model:['token':params.token]
  }

  def activePassword(ChangePasswordCommand command) {
    if (command.hasErrors()) {
      render view:"activePassword",model:['token':params.id,'errors':command.errors]
    }
    recoveryService.activeAccountAndChangePassword(params.id,command.password)
    redirect controller:'login', action:'auth'
  }

}
