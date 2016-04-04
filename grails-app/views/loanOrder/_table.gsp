<div class="table-responsive">
  <table class="table">
    <tr>
      <sec:ifAnyGranted roles="ROLE_ADMIN_IECCE,ROLE_OPERADOR_IECCE,ROLE_ADMIN">
      <th>Empresa</th>
      </sec:ifAnyGranted>
      <th>Monto</th>
      <th>Tipo</th>
      <th>Fecha de solicitud</th>
      <th>Estado</th>
      <th>Acciones</th>
    </tr>
    <g:each in="${loanOrderList}" var="loanOrder">
    <tr>
      <sec:ifAnyGranted roles="ROLE_ADMIN_IECCE,ROLE_OPERADOR_IECCE,ROLE_ADMIN">
      <td>${loanOrder.company}</td>
      </sec:ifAnyGranted>
      <td><integradora:formatPrice number="${loanOrder.amount}"/></td>
      <td>
        <g:set var="type" value="danger"/>
        <g:if test="${loanOrder.type == com.cte.LoanOrderType.CREDITOR}">
        <g:set var="type" value="warning"/>
        </g:if>
        <span class="label label-${type}">
          <g:message code="${loanOrder.type.code}"/>
        </span>
      </td>
      <td><g:formatDate format="dd-MM-yyyy hh:mm:ss" date="${loanOrder.dateCreated}"/></td>
      <td><g:message code="${loanOrder.status.code}"/></td>
      <td>
        <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
        <g:if test="${loanOrder.status == com.cte.LoanOrderStatus.VALIDATE}">
        <g:link controller="loanOrder" action="authorize" id="${loanOrder.id}" class="btn btn-default btn-xs">
          Autorizar el prestamo
        </g:link>
        </g:if>
        </sec:ifAnyGranted>
      </td>
    </tr>
    </g:each>
  </table>
</div>
