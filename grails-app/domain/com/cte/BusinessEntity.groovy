package com.cte

class BusinessEntity implements ClientBusinessEntity, ProviderBusinessEntity, EmployeeBusinessEntity {

  String rfc
  String website

  BusinessEntityType type

  static hasMany = [
  addresses:Address,
  names:ComposeName,
  banksAccounts:BankAccount,
  feesReceipts:FeesReceipt
  ]

  static fetchMode = [names: 'eager']

  static constraints = {
    website nullable:true,blank:false,size:5..50,url:true
    rfc(blank:false,size:10..50,validator: { val, obj ->
      if(obj.type == BusinessEntityType.FISICA && !(val ==~ /^[A-Z]{4}([0-9]{2})(1[0-2]|0[1-9])([0-3][0-9])([A-Z0-9]{3})$/) ) {
        return false
      } else if (obj.type == BusinessEntityType.MORAL && !(val ==~ /^[A-Z]{3}([0-9]{2})(1[0-2]|0[1-9])([0-3][0-9])([A-Z0-9]{3})$/) ) {
        return false
      }
    })
  }

  String toString(){
    String name
    String lastName
    String motherLastName
    String razonSocial
    String fullName

    if(type == BusinessEntityType.FISICA){
      names.each {
        name = (it.type == NameType.NOMBRE) ? it.value : name
      }
      names.each {
        lastName  = (it.type == NameType.APELLIDO_PATERNO) ? it.value : lastName
      }
      names.each {
        motherLastName = (it.type == NameType.APELLIDO_MATERNO) ? it.value : motherLastName
      }
      fullName = "${name} ${lastName} ${motherLastName}"
    }
    if(type == BusinessEntityType.MORAL){
      names.each {
        razonSocial = (it.type == NameType.RAZON_SOCIAL) ? it.value : razonSocial
      }
      fullName = razonSocial
    }
    fullName
  }

}
