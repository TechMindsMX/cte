package com.cte

import grails.transaction.Transactional
import jxl.*
import java.io.*
import org.springframework.web.multipart.MultipartFile

@Transactional
class ReadDocumentService {

    def readExcel(MultipartFile file) {
      try {
        Workbook fileExcel = Workbook.getWorkbook(file.getInputStream())
        List rowListOfFile = []
        int numRows
        (0..fileExcel.numberOfSheets-1).each{ sheetNo ->
          Sheet page = fileExcel.getSheet(sheetNo)
          int numColumns = page.columns
          numRows = page.rows
          String data
          (0..numRows-1).each { row ->
            def listElementInRow = []
            (0..numColumns-1).each { column ->
              data = page.getCell(column, row).contents
              listElementInRow.add(data)
            }
            rowListOfFile << listElementInRow
          }
        }
        rowListOfFile
      } catch (Exception ioe) {
        log.info ioe.printStackTrace()
      }
    }
}
