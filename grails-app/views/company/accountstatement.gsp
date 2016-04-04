<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'accountStatement.label', default: 'AccountStatement')}" />
    <title><g:message code="default.accountStatement.label" args="[entityName]" /></title>
 </head>
 <body>
   <div class="page-title">
     <h1><g:message code="Estado de Cuenta de la Compañía"/></h1>
     <ol class="breadcrumb">
       <li><i class="fa fa-caret-square-o-up"></i>Compañia</li>
       <li class="active">Estado de Cuenta</li>
     </ol>
   </div>
   <div class="panel panel-primary">
     <div class="panel-heading">${company.bussinessName} - ${company.rfc}</div>
   </div>
   <div class="table-responsive">
      <table class="table table-condensed">
        <tr>
          <th style="text-align:center">Moneda / Saldo</th>
          <th style="text-align:center">En Tránsito</th>
          <th style="text-align:center">Disponible</th>
          <th style="text-align:center">Total</th>
        </tr>
        <tr>
          <th>Pesos</th>
          <td style="text-align:right"><h3><span class="label label-warning">${integradora.formatPrice(number: balanceTransiting)}</span></h3></td>
          <td style="text-align:right"><h3><span class="label label-primary">${integradora.formatPrice(number: (balance-balanceTransiting))}</span></h3></td>
          <td style="text-align:right"><h3><span class="label label-default">${integradora.formatPrice(number: balance)}</span></h3></td>
        </tr>
        <tr>
          <th>Dólares</th>
          <td style="text-align:right"><h3><span class="label label-warning">${integradora.formatPrice(number: 0.00)}</span></h3></td>
          <td style="text-align:right"><h3><span class="label label-primary">${integradora.formatPrice(number: 0.00)}</span></h3></td>
          <td style="text-align:right"><h3><span class="label label-default">${integradora.formatPrice(number: usd)}</span></h3></td>
        </tr>
      </table>
   </div>

   <div class="panel panel-info">
   <div class="table-responsive">
     <g:form controller="Company" action="accountstatement" class="form-horizontal">
        <g:hiddenField name="company" value="${company.id}"/>
        <g:hiddenField name="query" value="1"/>
        <g:set var="sYears" value="${'2016..'+endyear}"/>
        <g:if test="${flash.message}">
          <div class="error alert alert-danger" role="alert">${flash.message}</div>
        </g:if>
        <table class="table">
          <tr>
            <td><label>Del:</label><g:datePicker id="startDate" name="startDate" value="${startdate}" precision="day" years="${2016..endyear}" required=""/></td>
            <td><label>Al:</label><g:datePicker id="endDate" name="endDate" value="${enddate}" precision="day" years="${2016..endyear}" required=""/></td>
            <td><g:actionSubmit class="btn btn-primary" value="Consultar" action="accountstatement"/></td>
          </tr>
        </table>
     </g:form>
   </div>
   </div>
   <table class="table">
     <tr>
       <th>Fecha</th>
       <th>Tipo</th>
       <th>Id de Transacción</th>
       <th>Abono</th>
       <th>Cargo</th>
       <th></th>
       <th>Saldo</th>
     </tr>
     <g:each in="${transactions}" var="mov">
      <tr>
        <td><g:formatDate format="dd-MM-yyyy hh:mm:ss" date="${new Date(mov.timestamp)}"/></td>
        <td>
          ${mov.transactionType.equals("BANK_CASHIN")?"DEPÓSITO EN EFECTIVO":mov.transactionType.equals("CASH_IN")?"DEPÓSITO SPEI":mov.transactionType.equals("CASH_OUT")?"RETIRO SPEI":mov.transactionType.equals("TRANSFER")?"TRANSFERENCIA":"NO DEFINIDO"}
        </td>
        <td>${mov.reference?:""}</td>
        <td>
          <g:if test="${mov.type.equals('CREDIT')}">
            ${integradora.formatPrice(number: mov.amount)}
          </g:if>
        </td>
        <td>
          <g:if test="${mov.type.equals('DEBIT')}">
            ${integradora.formatPrice(number: mov.amount)}
          </g:if>
        </td>
        <td>
          <g:if test="${mov.type.equals('CREDIT')}">
              <span class="label label-success">
                <span class="glyphicon glyphicon-chevron-up" aria-hidden="true"></span>
              </span>
          </g:if>
          <g:else>
              <span class="label label-danger">
                <span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
              </span>
         </g:else>
        </td>
        <td>${integradora.formatPrice(number: mov.balance)}</td>
      </tr>
     </g:each>
   </table>
</body>
</html>


