<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'feesReceipt.label', default: 'FeesReceipt')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="commission.list.label" /></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active">Comisiones</li>
      </ol>
    </div>
    <div id="list-feesReceipt" class="content scaffold-list" role="main">
      <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
      </g:if>
      <div class="table-responsive">
        <table class="table">
        <tr>
          <th><g:message code="feesReceipt.amount" /></th>
          <th><g:message code="feesReceipt.iva" /></th>
          <th><g:message code="feesReceipt.isr" /></th>
          <th><g:message code="feesReceipt.ivaWithHolding" /></th>
          <th><g:message code="feesReceipt.businessEntity" /></th>
          <th><g:message code="feesReceipt.status" /></th>
        </tr>
        <g:each in="${feesReceiptList}" var="feesReceipt">
        <tr>
          <td>
            <g:link action="show" id="${feesReceipt.id}" params="[businessEntity:"${feesReceipt.businessEntity.id}"]">
              ${integradora.formatPrice(number: feesReceipt.amount)}
            </g:link>
          </td>
          <td>${integradora.formatPrice(number: feesReceipt.iva)}</td>
          <td>${integradora.formatPrice(number: feesReceipt.isr)}</td>
          <td>${integradora.formatPrice(number: feesReceipt.ivaWithHolding)}</td>
          <td>${feesReceipt.businessEntity}</td>
          <td>${feesReceipt.status}</td>
          </tr>
          </g:each>
        </table>
      </div>

      <div class="pagination">
        <g:paginate total="${feesReceiptCount ?: 0}" />
      </div>
    </div>
    <g:link class="create btn btn-default" action="create" params='[businessEntity:"${params.businessEntity}"]'><g:message code="feesReceipt.create.label" args="[entityName]" /></g:link>
  </body>
</html>
