package com.cte

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional
class FeesReceiptController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  FeesReceiptService feesReceiptService

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    respond FeesReceipt.findAllByBusinessEntity(BusinessEntity.get(params.businessEntity)), model:[businessEntity: params.businessEntity]
  }

  def authorizeFeesReceipt(FeesReceipt feesReceipt){
    feesReceipt.status = FeesReceiptStatus.AUTORIZADA
    feesReceipt.save()
    render view:"list", model:[feesReceiptList:FeesReceipt.findAllByStatus(FeesReceiptStatus.CREADA)]
  }

  def executeFeesReceipt(FeesReceipt feesReceipt){
    feesReceipt.status = FeesReceiptStatus.EJECUTADA
    feesReceipt.save()
    render view:"list", model:[feesReceiptList:FeesReceipt.findAllByStatus(FeesReceiptStatus.AUTORIZADA)]
  }

  def cancelFeesReceipt(FeesReceipt feesReceipt){
    feesReceipt.status = FeesReceiptStatus.CANCELADA
    feesReceipt.save()
    render view:"list", model:[feesReceiptList:FeesReceipt.findAllByStatus(FeesReceiptStatus.CREADA)]
  }

  def rejectFeesReceipt(FeesReceipt feesReceipt){
    feesReceipt.status = FeesReceiptStatus.RECHAZADA
    feesReceipt.save()
    render view:"list", model:[feesReceiptList:FeesReceipt.findAllByStatus(FeesReceiptStatus.AUTORIZADA)]
  }

  def addDocumentToFeesReceipt(FeesReceipt feesReceipt){
    feesReceiptService.addDocumentToFeesReceipt(params.feesReceiptDocument, feesReceipt, 'feesReceipt')
    redirect action:"show", id:feesReceipt.id, params:params
  }

  def list(Integer max){
    params.max = Math.min(max ?: 10, 100)
    if(params.status)
      respond FeesReceipt.findAllByStatus(FeesReceiptStatus."${params.status}")
    else
      respond FeesReceipt.findAll()
  }

  def show(FeesReceipt feesReceipt) {
    respond feesReceipt
  }

  def create() {
    respond new FeesReceipt(params)
  }

  @Transactional
  def save(FeesReceiptCommand command) {
    def feesReceipt = command.createFeesReceipt()
    feesReceipt.businessEntity = BusinessEntity.get(params.businessEntity)

    if (!feesReceipt.validate()){
      render view:'create', model:[feesReceipt:feesReceipt]
      return
    }

    if (feesReceipt.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond feesReceipt.errors, view:'create'
      return
    }

    feesReceipt.save flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.created.message', args: [message(code: 'feesReceipt.label', default: 'FeesReceipt'), feesReceipt.id])
        redirect feesReceipt
      }
      '*' { respond feesReceipt, [status: CREATED] }
    }
  }

  def edit(FeesReceipt feesReceipt) {
    respond feesReceipt
  }

  @Transactional
  def update(FeesReceiptCommand command) {
    def feesReceipt = FeesReceipt.get(params.id)
    def businessEntity = feesReceipt.businessEntity

    feesReceipt.properties = command.createFeesReceipt().properties
    feesReceipt.businessEntity = businessEntity

    if (feesReceipt.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond feesReceipt.errors, view:'edit'
      return
    }

    feesReceipt.save flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.updated.message', args: [message(code: 'feesReceipt.label', default: 'FeesReceipt'), feesReceipt.id])
        redirect feesReceipt
      }
      '*'{ respond feesReceipt, [status: OK] }
    }
  }

  @Transactional
  def delete(FeesReceipt feesReceipt) {

    if (feesReceipt == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    feesReceipt.delete flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'feesReceipt.label', default: 'FeesReceipt'), feesReceipt.id])
        redirect action:"index", method:"GET"
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'feesReceipt.label', default: 'FeesReceipt'), params.id])
        redirect action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }
}
