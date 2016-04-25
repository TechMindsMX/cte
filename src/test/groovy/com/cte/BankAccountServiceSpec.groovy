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

  Should "create a bank accoutn command with a clabe"() {
    given:
      Map properties = ["clabe": "002115016003269411"]
      new Bank(bankingCode:"40002",name:"BANAMEX").save()
      new Bank(bankingCode:"40003",name:"BANCOMER").save()
    when:
      def commandResult = service.createABankAccountCommandByParams(properties)
    then:
      commandResult.clabe == properties.clabe
      commandResult.bank.contains(properties.clabe.substring(0,3))

  }

  Should "creatre a Bank account by command"() {
    given:
      new Bank(bankingCode:"40002",name:"BANAMEX").save()
      new Bank(bankingCode:"40003",name:"BANCOMER").save()
    and:
      def clabe = "002115016003269411"
      def command = new BankAccountCommand()
      command.accountNumber = clabe.substring(6,17)
      command.branchNumber = clabe.substring(3,6)
      command.bank = Bank.findByBankingCodeLike("%${clabe.substring(0,3)}").bankingCode
      command.clabe = clabe
    when:
      def bankAccount = service.createABankAccount(command)
    then:
      bankAccount.save()
      bankAccount.id == 1
  }

}
