package com.cte

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {

  def userService
  def companyService

  static defaultAction = "create"
  static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    respond User.list(params), model:[userCount: User.count()]
  }

  def show(User user) {
    respond user
  }

  def profile(User user) {
    render view:"profile", model:[user:user]
  }

  def create() {
    render view:"create", model:[user:new UserCommand(),legal:params.legal,company:session.company]
  }

  def legalRepresentative() {
    [user:new UserCommand(),legal:params.legal,company:session.company]
  }

  def authorizer() {
    [user:new UserCommand(),authorize:true,company:session.company, authorities:Role.findAllByAuthorityInList(['ROLE_INTEGRADO_OPERADOR', 'ROLE_INTEGRADO_AUTORIZADOR','ROLE_CONTABILIDAD'])]
  }

  @Transactional
  def save(UserCommand command) {
    command.legal = params.legal
    if (params.authorize){
      if (command.hasErrors()){
        render(view:'authorizer', model:[user:command, authorize:true, company:session.company, authorities:Role.findAllByAuthorityInList(['ROLE_INTEGRADO_OPERADOR', 'ROLE_INTEGRADO_AUTORIZADOR'])])
        return
      }
    }

    if (command.hasErrors()) {
      render(view:params.legal?'legalRepresentative':'create', model:[user:command, legal:params.legal])
      return
    }

    Company company = Company.get(session.company)

    def user = new User(username: command.username, password:command.password)
    def profile = command.getProfile()
    def telephone = command.getTelephone()
    if(telephone)profile.addToTelephones(telephone)

    user.profile = profile
    def userRole = Role.findWhere(authority: 'ROLE_INTEGRADO')

    if(params.legal){
      userRole = Role.findWhere(authority: 'ROLE_LEGAL_REPRESENTATIVE')
      companyService.addingLegalRepresentativeToCompany(company, user)
    }
    if (params.authorize)
      userRole = Role.get(params.roleId)

    userService.save(user, userRole)
    if (params.authorize)
      companyService.addingActorToCompany(company, user)

    flash.message = g.message(code: 'login.create')
    if(params.legal || params.authorize) {
      render view:"show", model:[user:user,company:session.company]
      return
    }
    render view:"detail", model:[user:user,company:session.company]
  }

  def edit(User user) {
    respond user,model:[company:session.company]
  }

  @Transactional
  def update() {
    def user = User.findById(params.user)
    userService.addInformationToLegalRepresentative(user,params)
    render view:"show", model:[user:user,company:session.company]
  }

  def delete(User user) {

    if (user == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    user.delete flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), user.id])
        redirect action:"index", method:"GET"
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  def manageusers() {
    Company company = Company.get(session.company)
    [users: company.actors]
  }

  @Transactional
  def updateauthorities(){
    Company company = Company.get(session.company)
    userService.updateAuthoritiesUsers(company, params)
    flash.message = "Usuarios actualizados"
    redirect (action:'manageusers')
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
        redirect action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }
}
