package com.cte

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
import java.lang.Void as Should

@TestFor(BankAccountService)
@Mock([Bank,BankAccount,Company,BusinessEntity])
class BankAccountServiceSpec extends Specification {

  Should "add an account to a company"(){
    given:"a bank account"
      BankAccount bankAccount = createBankAccount()
      def company = createCompany()
    when:
      def savedBankAccount = service.addBankAccountToCompany(bankAccount, company.id)
    then:
      savedBankAccount.id
  }

  Should "add an account to a businessEntity"(){
    given:"add an account to a businessEntity"
      BankAccount bankAccount = createBankAccount()
      def businessEntity = createBusinessEntity()
    when:
      def savedBankAccount = service.addBankAccountToBusinessEntity(bankAccount, businessEntity)
    then:
      savedBankAccount.id
  }

  private def createCompany(){
    def company = new Company(rfc:"JIGE930831NZ1",
                  bussinessName:"ABCD",
                  address:new Address(street:"Bellas Artes",
                                        streetNumber:"232",
                                        suite:"S/N",
                                        zipCode:"57730"),
                  webSite:"http.//www.somecompany.com",
                  employeeNumbers:5,
                  grossAnnualBilling:4000).save(validate:false)
    company
  }

  private def createBankAccount(){
    def bank = new Bank(bankingCode:"90902",name:"INDEVAL").save()

    new BankAccount(accountNumber:"12345678901",
                    branchNumber:"201590",
                    clabe:"002115016003269411",
                    banco:bank)
  }

  private def createBusinessEntity(){
    def businessEntity = new BusinessEntity(rfc:"PRV950621001",
                         website:"www.prvuno.com",
                         type:BusinessEntityType.MORAL)

  }

}
