package com.cte

import grails.test.mixin.TestFor
import spock.lang.Specification
import java.lang.Void as Should
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PayrollService)
@Mock([BusinessEntity,ComposeName,Payroll])
class PayrollServiceSpec extends Specification {

  Should "find business entity en base of employee number"() {
    given:
      def businessEntitys = createTwoBusinessEntity()
    when:
      def result = service.findBusinessEntityByComposeNameOfNumeroEmpleado(123)
    then:
      result == businessEntitys[0]
  }

  Should "create 1 payroll"() {
    given:
      def businessEntitys = createTwoBusinessEntity()
    and:
      def rowExampleExcel = [[123,100.9,"17/03/2016"]]
    when:
      def payrollListResult = service.schedulingPayrolls(rowExampleExcel)
    then:
      payrollListResult.first().amount == 100.9
      payrollListResult.first().employee == businessEntitys[0]
      payrollListResult.first().status == PayrollStatus.CALENDARIZED
  }

  Should "create 2 payroll with 3 row, first row with data and second row empty"() {
    given:
      def businessEntitys = createTwoBusinessEntity()
    and:
      def rowExampleExcel = [[123,100.9,"17/05/2016"],["","",""],[232,890.50,""]]
    when:
      def payrollListResult = service.schedulingPayrolls(rowExampleExcel)
    then:
      payrollListResult.first().amount == 100.9
      payrollListResult.first().employee == businessEntitys[0]
      payrollListResult.first().status == PayrollStatus.CALENDARIZED
      payrollListResult[1].amount == 890.50
      payrollListResult[1].employee == businessEntitys[1]
      Payroll.getAll().size() == 2
  }

  Should "Not create a payroll but not find a businessEntity"() {
    given:
      def rowExampleExcel = [[123,100.9,"17/05/2016"],["","",""],[232,890.50,""]]
    when:
      def payrollListResult = service.schedulingPayrolls(rowExampleExcel)
    then:
      payrollListResult.isEmpty()
  }

  private def createTwoBusinessEntity() {
    def businessEntity1 = new BusinessEntity(rfc:'XXXX010101XXX', website:'http://www.iecce.mx',type:BusinessEntityType.FISICA)
    businessEntity1.save(validate:false)
    def businessEntity2 = new BusinessEntity(rfc:'XXXX010101YYY', website:'http://www.iecce.mx',type:BusinessEntityType.FISICA)
    businessEntity2.save(validate:false)
    def name = new ComposeName(value:'sergio',type:NameType.NOMBRE,businessEntity:businessEntity1)
    def lastName = new ComposeName(value:'rodriguez',type:NameType.APELLIDO_PATERNO,businessEntity:businessEntity1)
    def motherLastName = new ComposeName(value:'duran',type:NameType.APELLIDO_MATERNO,businessEntity:businessEntity1)
    def numeroEmpleado = new ComposeName(value:'123',type:NameType.NUMERO_EMPLEADO,businessEntity:businessEntity1)
    def name2 = new ComposeName(value:'arturo',type:NameType.NOMBRE,businessEntity:businessEntity2)
    def lastName2 = new ComposeName(value:'rodriguez',type:NameType.APELLIDO_PATERNO,businessEntity:businessEntity2)
    def motherLastName2 = new ComposeName(value:'mendez',type:NameType.APELLIDO_MATERNO,businessEntity:businessEntity2)
    def numeroEmpleado2 = new ComposeName(value:'232',type:NameType.NUMERO_EMPLEADO,businessEntity:businessEntity2)
    businessEntity1.names = [name, lastName, motherLastName, numeroEmpleado]
    businessEntity1.save(validate:false)
    businessEntity2.names = [name2, lastName2, motherLastName2, numeroEmpleado2]
    businessEntity2.save(validate:false)
    [businessEntity1,businessEntity2]
  }

}
