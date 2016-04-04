package com.cte

import org.springframework.context.i18n.LocaleContextHolder as LCH

import grails.transaction.Transactional

@Transactional(readOnly=true)
class EmployeeService {

  def messageSource
  def emailSenderService
  def businessEntityService
  def readDocumentService

  def addEmployeeToCompany(EmployeeBusinessEntity employee, Company company){
    if(isEmployeeOfThisCompany(employee, company))throw new BusinessException(messageSource.getMessage('exception.employee.already.exist', null, LCH.getLocale()))
    def employeeLink = new EmployeeLink(type:employee.class.simpleName, employeeRef: employee.rfc, company: company).save()
    company.addToBusinessEntities(employee)
    emailSenderService.sendNewClientProviderNotificaton(company, employee.toString(), EmailerMessageType.EMPLOYEE_MODULUS)
    employeeLink
  }

  def createAMultiEmployees(def file, def companyId) {
    def rowListOfDocument = readDocumentService.readExcel(file)
    def employeesSucces = []
    def employeeFail = []
    rowListOfDocument.each { employee ->
      if (!ComposeName.findByValueAndType(employee[0],NameType.NUMERO_EMPLEADO)){
        def businessEntity = generateEmployee(employee)
        addEmployeeToCompany(businessEntity,Company.findById(companyId))
        employeesSucces.add(businessEntity)
      }
      else
        employeeFail << employee
    }
    [employeesSucces,employeeFail]
  }

  def isEmployeeOfThisCompany(EmployeeBusinessEntity employee, Company company){
    EmployeeLink.countByTypeAndEmployeeRefAndCompany(employee?.class?.simpleName,employee?.rfc,company)
  }

  def isEmployee(instance){
    EmployeeLink.countByTypeAndEmployeeRef(instance.class.simpleName, instance.rfc)
  }

  def generateEmployee(def employee) {
    def businessEntity = new BusinessEntity(rfc:employee[1],website:"http://www.employee.com",type:BusinessEntityType.FISICA)
    def names = (String[])[employee[2],employee[3],employee[4],employee[0],employee[5],employee[6],employee[7]]
    businessEntityService.appendNamesToBusinessEntity(businessEntity,names)
  }

}
