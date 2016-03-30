package com.cte

class ProviderLink {

  String type
  String providerRef

  static belongsTo = [company:Company]

  static constraints = {
  }

}
