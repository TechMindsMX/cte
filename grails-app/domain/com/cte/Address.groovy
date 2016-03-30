package com.cte

class Address {
  String street
  String streetNumber
  String suite
  String zipCode
  String colony
  String country
  String city
  String town
  String federalEntity
  AddressType addressType

  static belongsTo = [Company, BusinessEntity]

  static constraints = {
    street blank:false,nullable:false
    streetNumber blank:false,nullable:false
    suite nullable:true
    zipCode blank:false,nullable:false
    colony blank:false,nullable:false
    country blank:false,nullable:false
    city blank:false,nullable:false
    federalEntity blank:false,nullable:false
    town blank:false,nullable:false
  }

  String toString(){
    "$street #$streetNumber C.P $zipCode $federalEntity"
  }

  String info(){
    "$street  $streetNumber Col. $colony  $country  $city  $town C.P $zipCode  $federalEntity Tipo:$addressType"
  }
}
