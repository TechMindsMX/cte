package com.cte

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional(readOnly = false)
class PayrollController {

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def readDocumentService
  def payrollService
  def processorPayrollService
  def documentService

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    respond Payroll.list(params), model:[payrollCount: Payroll.count()]
  }

  def show(Payroll payroll) {
    respond payroll
  }

  def processorPayroll() {
    render view:"showProcessorPayroll", model:[processorPayrolls:ProcessorPayroll.getAll()]
  }

  def generetExcel() {
    def processorPayroll = ProcessorPayroll.findById(params.id)
    response.setHeader("Content-disposition", "attachment; filename=${processorPayroll.nameFile}")
    response.setHeader("Content-Type", "application/vnd.ms-excel")
    response.outputStream << documentService.writeXlsFromProcessorPayroll(processorPayroll)
  }

  def create() {
    log.debug "create"
  }

  def save() {
    def company = Company.findById(session.company.toLong())
    def rowListOfDocument = readDocumentService.readExcel(params.documentExcel)
    def payrollList = payrollService.schedulingPayrolls(rowListOfDocument)
    processorPayrollService.createRelationIntoPayrolls(payrollList,params.documentExcel.getOriginalFilename(),company)
    redirect (action: "index")
  }

  def edit(Payroll payroll) {
    respond payroll
  }

  @Transactional
  def update(Payroll payroll) {
    if (payroll == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    if (payroll.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond payroll.errors, view:'edit'
      return
    }

    payroll.save flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.updated.message', args: [message(code: 'payroll.label', default: 'Payroll'), payroll.id])
        redirect payroll
      }
      '*'{ respond payroll, [status: OK] }
    }
  }

  @Transactional
  def delete(Payroll payroll) {

    if (payroll == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    payroll.delete flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'payroll.label', default: 'Payroll'), payroll.id])
        redirect action:"index", method:"GET"
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'payroll.label', default: 'Payroll'), params.id])
        redirect action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }
}
