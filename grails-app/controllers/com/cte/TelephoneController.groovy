package com.cte

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class TelephoneController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def telephoneService

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    respond Telephone.list(params), model:[telephoneCount: Telephone.count()]
  }

  def show(Telephone telephone) {
    respond telephone
  }

  def create(User user) {
    respond new Telephone(params),model:[userId : user.id,company:session.company]
  }

  def createForCompany(){
    respond new Telephone()
  }

  def save(Telephone telephone) {
    if (telephone == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    if (telephone.hasErrors()) {
      respond telephone.errors, view:'createForCompany'
      return
    }

    User user = User.findById(params.userId)
    telephoneService.save(telephone, user)

    render view:"/user/show", model:[user:user,company:session.company]
  }

  def saveForCompany(Telephone telephone) {
    if (telephone == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    if (telephone.hasErrors()) {
      respond telephone.errors, view:'create'
      return
    }

    Company company = Company.findById(session.company.toLong())
    telephoneService.saveForCompany(telephone, company)

    redirect(action:"show", controller:"company", id:"${session.company}")

  }

  def edit(Telephone telephone) {
    respond telephone
  }

  def editForCompany(Telephone telephone) {
    respond telephone
  }

  @Transactional
  def update(Telephone telephone) {
    if (telephone == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    if (telephone.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond telephone.errors, view:'edit'
      return
    }

    telephone.save flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.updated.message', args: [message(code: 'telephone.label', default: 'Telephone'), telephone.id])
        redirect telephone
      }
      '*'{ respond telephone, [status: OK] }
    }
  }

  @Transactional
  def updateForCompany(Telephone telephone) {
    if (telephone == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    if (telephone.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond telephone.errors, view:'editForCompany'
      return
    }

    telephone.save flush:true

    redirect(action:"show",controller:"company",id:"${session.company}")
  }

  @Transactional
  def delete(Telephone telephone) {

    if (telephone == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    telephone.delete flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'telephone.label', default: 'Telephone'), telephone.id])
        redirect action:"index", method:"GET"
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'telephone.label', default: 'Telephone'), params.id])
        redirect action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }
}
