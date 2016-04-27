<%! import com.cte.DepositOrderStatus %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'depositOrder.label', default: 'Order de Depósito')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
      <div class="page-title">
       <h1><g:message code="depositOrder.document" args="[entityName]" /></h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
          <li class="active">Orden de Depósito</li>
        </ol>
      </div>
      <div id="edit-address" class="content scaffold-edit" role="main">
        <div class="portlet portlet-blue">
          <div class="portlet-heading">
            <div class="portlet-title">
              <br />
              <br />
            </div>
            <div class="clearfix"></div>
          </div>
          <div id="horizontalFormExample" class="panel-collapse collapse in">
            <div class="portlet-body">
              <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
              </g:if>
              <div class="form-group">
                <label id="amount-label" class="col-sm-5 control-label"><g:message code="depositOrder.amount" /></label>
                <div class="col-sm-4">
                  ${integradora.formatPrice(number:depositOrder.amount)}
                </div>
              </div>
              <br /><br />
              <g:form action="documentDepositOrder" id="documentDepositOrder" method="POST" enctype="multipart/form-data" class="form-horizontal">
                <input type="hidden" name="depositOrderId" value="${depositOrder.id}" />
                <div class="form-group">
                  <div aria-labelledby="${label}-label">
                    <input type="file" name="depositSlip" required="required" accept="application/pdf,image/png,image/jpg" class="form-control" />
                  </div>
                </div>
                <p>
                  <input type="submit" value="Enviar Ficha de Depósito" class="btn btn-default">
                </p>
              </g:form>
              <fieldset class="buttons">
                <g:link action="list" class="list btn btn-default">Regresar</g:link>
              </fieldset>
            </div>
          </div>
        </div>
      </div>
    </body>
</html>
