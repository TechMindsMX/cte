package com.cte

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

@TestFor(FeesReceiptService)
class FeesReceiptServiceSpec extends Specification {

  DocumentService documentService = Mock(DocumentService)

  def setup(){
    service.documentService = documentService
  }

  void "should add a document to the fees receipt"() {
    given:"An feesReceipt"
      FeesReceipt feesReceipt = new FeesReceipt(amount:100)
    and:"A document"
      Object document = new Object()
    when:"We add document"
      service.addDocumentToFeesReceipt(document, feesReceipt, 'feesReceipt')
    then:"We expect to call to document service"
    1 * documentService.uploadDocumentForOrder(document, 'feesReceipt', feesReceipt)
  }
}
