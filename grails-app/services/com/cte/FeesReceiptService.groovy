package com.cte

import grails.transaction.Transactional

@Transactional
class FeesReceiptService {

  DocumentService documentService

  def addDocumentToFeesReceipt(def document, FeesReceipt feesReceipt, String type){
    documentService.uploadDocumentForOrder(document,type,feesReceipt)
  }

}
