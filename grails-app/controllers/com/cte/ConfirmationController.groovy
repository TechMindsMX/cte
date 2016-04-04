package com.cte

class ConfirmationController {

  def recoveryService

  def index() {
    User user = recoveryService.confirmAccountForToken(params.token)
    session.message = g.message(code: 'login.confirm')
    session.username = user.username
    redirect controller:'login', action: 'auth'
  }
}

