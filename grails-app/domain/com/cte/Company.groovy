package com.cte

class Company {

  String rfc
  String bussinessName
  String webSite
  Integer employeeNumbers
  BigDecimal grossAnnualBilling
  CompanyStatus status = CompanyStatus.ACCEPTED
  String uuid = UUID.randomUUID().toString().replace('-','')[0..15]
  Integer numberOfAuthorizations = 1
  CompanyTaxRegime taxRegime = CompanyTaxRegime.MORAL

  static hasMany = [banksAccounts:BankAccount,
  documents:S3Asset,
  legalRepresentatives:User,
  addresses:Address,
  actors:User,
  businessEntities:BusinessEntity,
  loanOrders: LoanOrder,
  commissions:Commission,
  telephones:Telephone]


  static constraints = {
    bussinessName blank:false,size:1..100
    webSite nullable:true,size:5..50
    employeeNumbers max: 50
    grossAnnualBilling max:250000000.00
    rfc unique:true,blank:false,size:10..50,matches:/^[A-Z]{3,4}([0-9]{2})(1[0-2]|0[1-9])([0-3][0-9])([A-Z0-9]{3})$/
    numberOfAuthorizations nullable:false
  }

  String toString(){
    bussinessName
  }

}
