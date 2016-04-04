package com.cte

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AddressController {

  def companyService
  def addressService
  def businessEntityService

  static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

  def index(Integer max) {
    params.max = Math.min(max ?: 10, 100)
    respond Address.list(params), model:[addressCount: Address.count()]
  }

  def show(Address address) {
    respond address
  }

  def create() {
    def businessEntity = BusinessEntity.get(params.long('businessEntity'))
    respond new Address(params),model:[businessEntity:businessEntity, addressTypes:addressService.getAddresTypesForOrganization(session.company.toLong())]
  }

  @Transactional
  def save(Address address) {
    if (address == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    if (address.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond address.errors, view:'create', model:[company:session.company]
      return
    }


    if(params.long('businessEntityId')){
      def businessEntity = businessEntityService.createAddressForBusinessEntity(address, params.long('businessEntityId'))
      redirect(controller:"businessEntity",action:"show",id:businessEntity.id)
      return
    }
    if(session.company){
      def company = companyService.createAddressForCompany(address, session.company.toLong())
      redirect(controller:"company",action:"show",id:company.id)
      return
    }

    notFound()
  }

  def edit(Address address) {
    respond address,model:[addressTypes:addressService.getAddresTypesForOrganization(params.long("company"))]

  }

  @Transactional
  def update(Address address) {
    if (address == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    if (address.hasErrors()) {
      transactionStatus.setRollbackOnly()
      respond address.errors, view:'edit'
      return
    }

    address.save flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.updated.message', args: [message(code: 'address.label', default: 'Address'), address.id])
        redirect address
      }
      '*'{ respond address, [status: OK] }
    }
  }

  @Transactional
  def delete(Address address) {

    if (address == null) {
      transactionStatus.setRollbackOnly()
      notFound()
      return
    }

    address.delete flush:true

    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'address.label', default: 'Address'), address.id])
        redirect action:"index", method:"GET"
      }
      '*'{ render status: NO_CONTENT }
    }
  }

  protected void notFound() {
    request.withFormat {
      form multipartForm {
        flash.message = message(code: 'default.not.found.message', args: [message(code: 'address.label', default: 'Address'), params.id])
        redirect action: "index", method: "GET"
      }
      '*'{ render status: NOT_FOUND }
    }
  }

}
