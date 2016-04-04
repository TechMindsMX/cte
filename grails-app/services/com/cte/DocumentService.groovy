package com.cte

import grails.transaction.Transactional
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.usermodel.CreationHelper
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.xssf.usermodel.XSSFCell
import java.io.FileOutputStream

@Transactional
class DocumentService {

  def grailsApplication
  def companyService
  def s3AssetService

  def uploadDocumentCompany(def document,def type,def companyId) {
    def asset = s3AssetService.createTempFilesOfMultipartsFiles(document,type)
    def company = Company.findById(companyId)
    def assetExisting = searchAssetByTypeIfExist(type,company.documents)
    if (assetExisting)
      deleteAssetOfRelationship(company,assetExisting.first())
    createRelationShipCompanyToAssets(asset,companyId)
  }

  def uploadDocumentForLegalRepresentative(def document,def type,def legalRepsentativeId,def companyId) {
    def asset = s3AssetService.createTempFilesOfMultipartsFiles(document,type)
    def user = User.findById(legalRepsentativeId)
    def assetExisting = searchAssetByTypeIfExist(type,user.profile.documents)
    if (assetExisting){
      def assetExistingByCompany = getDocumentsByCompanyForLegalRepresentative(assetExisting,companyId.toLong())
      if (assetExistingByCompany) {
        deleteAssetOfRelationship(user.profile,assetExistingByCompany.first())
        deleteRelationshipLegalRepresentativeWithCompany(assetExistingByCompany.first(),companyId.toLong())
      }
    }
    createRelationshipIntoLegalRepresentativeAndAsset(asset,legalRepsentativeId,companyId)
  }

  def uploadDocumentForOrder(def document,def type,def clazz) {
    def asset = s3AssetService.createTempFilesOfMultipartsFiles(document,type)
    clazz.addToDocuments(asset)
    clazz.save()
  }

  def getDocumentsByCompanyForLegalRepresentative(def listDocumentsOfLegal, def companyId) {
    if (listDocumentsOfLegal.isEmpty())
      return []
    def idListOfasset = LegalRepresentativesAssets.withCriteria {
      'in'("asset",listDocumentsOfLegal*.id)
      and {
        'eq' ('company', companyId.toLong())
      }
    }
    if (idListOfasset)
      return S3Asset.findAllByIdInList(idListOfasset*.asset)
    else
      return []
  }

  def deleteRelationshipLegalRepresentativeWithCompany(asset,companyId) {
    def legalRepresentativesAsset = LegalRepresentativesAssets.findByAssetAndCompany(asset.id,companyId)
    legalRepresentativesAsset.delete()
  }

  private def createRelationshipIntoLegalRepresentativeAndAsset(assetList, legalRepsentativeId, companyId) {
    def user = User.findById(legalRepsentativeId)
    assetList.each {asset ->
      def legal = new LegalRepresentativesAssets()
      legal.asset = asset.id
      legal.company = Company.findById(companyId).id
      legal.save()
      user.profile.addToDocuments(asset)
      user.profile.save()
    }
  }

  def writeXlsFromProcessorPayroll(def processorPayroll) {
    XSSFWorkbook workbook = generateWorkbookWithAllInvoices(processorPayroll)
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try {
      workbook.write(bos);
      } finally {
        bos.close();
      }
      bos.toByteArray()
  }

  XSSFWorkbook generateWorkbookWithAllInvoices(ProcessorPayroll processorPayroll){
    XSSFWorkbook workbook = generateExcelWorkbook()
    addHeadersToWorkbook(workbook,getHeadersForDetailReport())
    processorPayroll.payrolls.each{ payroll ->
      addInvoiceDetailToWorkbook(payroll,workbook)
    }
    workbook
  }

  XSSFWorkbook generateExcelWorkbook(){
    XSSFWorkbook workbook = new XSSFWorkbook()
    XSSFSheet sheet=workbook.createSheet("Página_1")
    workbook
  }

  private void addHeadersToWorkbook(XSSFWorkbook workbook,headers){
    XSSFSheet sheet = workbook.getSheetAt(0)

    Row headerRow = sheet.createRow(sheet.getPhysicalNumberOfRows())
    XSSFCellStyle headerStyle = workbook.createCellStyle()
    headerStyle.fillForegroundColor = IndexedColors.GREY_25_PERCENT.index
    headerStyle.fillPattern = CellStyle.SOLID_FOREGROUND

    headers.eachWithIndex{ header,index ->
      Cell headerCell = headerRow.createCell(index)
      headerCell.cellStyle = headerStyle
      headerCell.cellValue = header
    }
  }

  private def getHeadersForDetailReport() {
    ["Num. Empleado", "Nombre", "Monto de Dispercion", "Banco", "Cuenta", "Clabe"]
  }

  private def addInvoiceDetailToWorkbook(def payroll,def workbook) {
    def fields = [
                    payroll.employee.names.find{ val -> val['type'] == NameType.NUMERO_EMPLEADO}?.value,
                    payroll.employee.toString(),
                    payroll.amount,
                    payroll.employee.names.find{ val -> val['type'] == NameType.BANCO}?.value,
                    payroll.employee.names.find{ val -> val['type'] == NameType.CUENTA}?.value,
                    payroll.employee.names.find{ val -> val['type'] == NameType.CLABE}?.value
                  ]

    addRecordToWorkbook(workbook,fields)
  }

  private def addRecordToWorkbook(workbook,fields) {
    XSSFSheet sheet = workbook.getSheetAt(0)
    Row row = sheet.createRow(sheet.getPhysicalNumberOfRows())
    Cell cell = row.createCell(row.lastCellNum+1)
    CreationHelper createHelper = workbook.getCreationHelper()
    XSSFCellStyle dateStyle = workbook.createCellStyle()
    dateStyle.dataFormat = createHelper.createDataFormat().getFormat("dd-MM-YYYY HH:mm")

    fields.each{ field ->
      if(field?.class?.simpleName == BigDecimal.class.simpleName)
        cell.cellType = XSSFCell.CELL_TYPE_NUMERIC
      else if(field?.class?.simpleName == Date.class.simpleName)
        cell.cellStyle = dateStyle

      cell.cellValue = field
      cell = row.createCell(row.lastCellNum)
    }
  }

  private def searchAssetByTypeIfExist(def type,def elementWithContent) {
    elementWithContent.findAll{ asset -> asset.type == type && asset.status == true}
  }

  private def deleteAssetOfRelationship(clazz,asset) {
    clazz.removeFromDocuments(asset)
    asset.delete()
  }

  private def createRelationShipCompanyToAssets(asset,companyId) {
    companyService.createS3AssetForCompany(asset, companyId)
  }

}
