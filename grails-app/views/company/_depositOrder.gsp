<b><span id="address-label" class="property-label">Ordenes de Depósito</span></b>
<br>
<div class="property-value" aria-labelledby="${label}-label">
  <sec:ifAnyGranted roles="ROLE_INTEGRADO">
    <g:link controller="depositOrder" action="create" class="btn btn-default" params="[companyId:company.id]">Crear Orden de Deposito</g:link>
  </sec:ifAnyGranted>
  <g:link controller="depositOrder" action="index" class="btn btn-default" params="[companyId:company.id]">Listar Ordenes de Depósito</g:link>
</div>