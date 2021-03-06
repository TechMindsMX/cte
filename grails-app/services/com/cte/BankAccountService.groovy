package com.cte

import grails.transaction.Transactional

@Transactional
class BankAccountService {

  def createABankAccount(BankAccountCommand command){
    def bankAccount = command.createBankAccount()
    bankAccount.banco = Bank.findByBankingCode(command.bank)
    bankAccount
  }


  def createABankAccountCommandByParams(Map properties){
    def command = new BankAccountCommand()
    command.accountNumber = properties.clabe.substring(6,17)
    command.branchNumber = properties.clabe.substring(3,6)
    command.bank = Bank.findByBankingCodeLike("%${properties.clabe.substring(0,3)}").bankingCode
    command.clabe = properties.clabe
    command
  }

  def repeatedBankAccountCompany(BankAccount bankAccount, Company company){
    def repeatedAccount = company.banksAccounts.find{ cuenta ->
      cuenta.banco.id == bankAccount.banco.id && cuenta.accountNumber == bankAccount.accountNumber && cuenta.id != bankAccount.id
    }
  }

  def addBankAccountToCompany(BankAccount bankAccount, company){
    company = Company.get(company)
    if(repeatedBankAccountCompany(bankAccount, company))
      throw new Exception("La cuenta indicada ya existe")

    bankAccount.save()

    company.addToBanksAccounts(bankAccount)
    company.save flush:true

    bankAccount
  }

  def updateBankAccountCompany(BankAccount bankAccount, company){
    company = Company.get(company)
    if(repeatedBankAccountCompany(bankAccount, company))
      throw new Exception("La cuenta indicada ya existe")
    bankAccount.save()
    bankAccount
  }

  def repeatedBankAccountBusinessEntity(BankAccount bankAccount, BusinessEntity businessEntity){
    def repeatedAccount = businessEntity.banksAccounts.find{ cuenta ->
      cuenta.banco.id == bankAccount.banco.id && cuenta.accountNumber == bankAccount.accountNumber && cuenta.id != bankAccount.id
    }
  }

  def addBankAccountToBusinessEntity(BankAccount bankAccount, businessEntity){
    if(repeatedBankAccountBusinessEntity(bankAccount, businessEntity)){
      throw new Exception("La cuenta indicada ya existe")
    }

    bankAccount.save()

    businessEntity.addToBanksAccounts(bankAccount)
    businessEntity.save flush:true

    bankAccount
  }

  def updateBankAccountBusinessEntity(BankAccount bankAccount, BusinessEntity businessEntity){
    if(repeatedBankAccountBusinessEntity(bankAccount, businessEntity))
      throw new Exception("La cuenta indicada ya existe")

    bankAccount.save flush:true

    bankAccount
  }

}
