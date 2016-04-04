package com.cte

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

@TestFor(InvoiceService)
class InvoiceServiceSpec extends Specification {

  void "create an invoice from sale order"(){
    given:"A address"
      def address = new Address(street:"Poniente 3",
                                streetNumber:"266",
                                suite:"S/N",
                                zipCode:"57840",
                                colony:"Reforma",
                                town:"Gustavo A.Madero",
                                city:"México",
                                country:"México",
                                federalEntity:"México")
    and:"Sale Order item"
      def saleOrderItem = new SaleOrderItem(sku:'sku1',name:'name', price:100, ieps:0, iva:16, quantity:1, unitType:UnitType.UNIDADES)
    and:"An sale order"
      def saleOrder = new SaleOrder(rfc:'XXXX010101XXX',clientName:'clientName',addresses:[address], items:[saleOrderItem])
    when:"We create an invoice from sale order"
      def result = service.createInvoiceFromSaleOrder(saleOrder)
    then:"We expect factura data"
      result.datosDeFacturacion.formaDePago == 'PAGO EN UNA SOLA EXHIBICION'
      result.datosDeFacturacion.tipoDeComprobante == 'ingreso'
      result.datosDeFacturacion.lugarDeExpedicion == 'Distrito Federal'
      result.datosDeFacturacion.metodoDePago == 'TRANSFERENCIA ELECTRONICA'
      result.datosDeFacturacion.numeroDeCuentaDePago == 'DESCONOCIDO'
      result.datosDeFacturacion.moneda == 'MXN'

      result.emisor.datosFiscales.razonSocial == 'Integradora de Emprendimientos Culturales S.A. de C.V.'
      result.emisor.datosFiscales.rfc == 'AAD990814BP7'
      result.emisor.datosFiscales.codigoPostal == '11850'
      result.emisor.datosFiscales.pais == 'México'
      result.emisor.datosFiscales.ciudad == 'Ciudad de México'
      result.emisor.datosFiscales.delegacion == 'Miguel Hidalgo'
      result.emisor.datosFiscales.calle == 'Tiburcio Montiel'
      result.emisor.datosFiscales.regimen == 'PERSONA MORAL'
      result.conceptos.size() == 1
      result.impuestos.size() == 1

      result.conceptos[0].cantidad == 1
      result.conceptos[0].valorUnitario == 100
      result.conceptos[0].descripcion == 'name'
      result.conceptos[0].unidad == 'UNIDADES'

      result.impuestos[0].importe == 16
      result.impuestos[0].tasa == 16
      result.impuestos[0].impuesto == 'IVA'
  }

}
