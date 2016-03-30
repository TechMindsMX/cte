package com.cte

class ComposeName {
  String value
  NameType type

  static belongsTo = [businessEntity:BusinessEntity]

  static constraints = {
    value blank:false,size:1..100
  }
}
