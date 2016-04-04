package com.cte

import grails.test.mixin.TestFor
import spock.lang.Specification
import java.lang.Void as Should
import grails.test.mixin.Mock

@TestFor(AddressService)
@Mock([Company,Address])
class AddressServiceSpec extends Specification {

  Should "get the address types for the organization without addresses"(){
    given:"a company"
      def company = createCompany()
    when:
      def addressTypes = service.findAddressTypeForOrganization(company.id)
    then:
      addressTypes.size() == 3
  }

  Should "get the address types for the organization with a legal Address"(){
    given:"a company"
      def company = createCompany()
    and: "the legal address"
      def address = new Address(street:"Bellas Artes",
                                streetNumber:205,
                                addressType:AddressType.FISCAL).save(validate:false)
      company.addToAddresses(address)
      company.save(validate:false)
    when:
      def addressTypes = service.findAddressTypeForOrganization(company.id)
    then:
      addressTypes.size() == 2
  }

  Should "Should get the address types map"(){
    given:"a company"
      def company = new Company(bussinessName:"MakingDevs").save(validate:false)
    when:
      def customAddressTypes = service.getAddresTypesForOrganization(company.id) 
    then:
      customAddressTypes.first().key == "FISCAL"
  }

  private def createCompany(){
    new Company(rfc:"ROS861224NHA",
                bussinessName:"MakingDevs",
                webSite:"",
                employeeNumbers:40,
                grossAnnualBilling:2000,
                status:CompanyStatus.CREATED).save()
  }

}
