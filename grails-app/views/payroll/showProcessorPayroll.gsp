<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'payroll.label', default: 'Payroll')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
      <div class="page-title">
        <h1><g:message code="payroll.new" /></h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i> Pagos</li>
          <li class="active">Listado de Propagacion de nomina y pagos</li>
        </ol>
      </div>
      <div class="portlet portlet-blue">
          <div class="portlet-heading">
              <div class="portlet-title">
                <br /><br />
              </div>
              <div class="portlet-widgets">
              </div>
              <div class="clearfix"></div>
          </div>
          <div id="bluePortlet" class="panel-collapse collapse in">
            <div class="portlet-body">
              <div id="list-payroll" class="content scaffold-list" role="main">
                  <g:if test="${flash.message}">
                      <div class="message" role="status">${flash.message}</div>
                  </g:if>
                  <div class="table-responsive">
                    <table class="table">
                      <thead>
                        <tr>
                          <th>Id</th>
                          <th><g:message code="payroll.amount" /></th>
                          <th><g:message code="payroll.applyDate" /></th>
                          <th><g:message code="payroll.company" /></th>
                        </tr>
                      </thead>
                      <tbody>
                        <g:each in="${processorPayrolls}" var="processorPayroll">
                          <tr>
                            <td>${processorPayroll.id}</td>
                            <td>${processorPayroll.nameFile}</td>
                            <td>${processorPayroll.dateCreated.format("dd-MMMM-yyyy")}</td>
                            <td>${processorPayroll.company}</td>
                            <td><g:link action="generetExcel" id="${processorPayroll.id}" class="btn btn-default"><i class="fa fa-file-excel-o"></i></g:link>
                          </tr>
                        </g:each>
                      </tbody>
                    </table>
                  </div>
                  <div class="pagination">
                    <g:paginate total="${payrollCount ?: 0}" />
                  </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </body>
</html>
