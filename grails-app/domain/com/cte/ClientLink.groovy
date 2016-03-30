package com.cte

class ClientLink {

  String type
  String clientRef

  static belongsTo = [company:Company]

  static constraints = {
  }

}
