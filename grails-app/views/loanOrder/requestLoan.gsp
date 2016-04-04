<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'loanOrder.label', default: 'LoanOrder')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1>Solicitar un préstamo</h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active">Préstamos</li>
      </ol>
    </div>
    <div class="row">

    <div class="col-lg-12">
      <g:form controller="loanOrder" action="createRequestLoan">
      <div class="portlet portlet-default">
        <div class="portlet-heading">
          <div class="portlet-title">
            <h4>Datos del préstamo a solicitar</h4>
          </div>
          <div class="clearfix"></div>
        </div>
        <div id="defaultPortlet" class="panel-collapse collapse in">
          <div class="portlet-body">
            <div class="form-inline">
              <div class="form-group">
                <label class="sr-only" for="inputAmount">Monto</label>
                <div class="input-group">
                  <div class="input-group-addon input-lg">$</div>
                  <input type="number" class="form-control input-lg" id="amount" name="amount" placeholder="Monto">
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="portlet-footer">
          <button type="submit" class="btn btn-default">
            Solicitar el préstamo
          </button>
        </div>
      </div>
      </g:form>
    </div>

    </div>
  </body>
</html>
