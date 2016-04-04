package com.cte

class SaleOrderTagLib {

  static namespace = "integradora"
  static defaultEncodeAs = [taglib:'html']
  //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

  def formatPrice = { attrs, body ->
    out << g.formatNumber(number:attrs.number, type:"currency", maxFractionDigits:"2", locale:"es_MX")
  }

  def invoiceUrl = { attrs, body ->
    out << "${grailsApplication.config.modulus.facturacionUrl}${grailsApplication.config.modulus.showFactura}/${attrs.saleOrder.uuid}.${attrs.format}"
  }
}
