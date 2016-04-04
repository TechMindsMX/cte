<b><span id="address-label" class="property-label">Ordenes de Retiro</span></b>
<br>
<div class="property-value" aria-labelledby="${label}-label">
  <sec:ifAnyGranted roles="ROLE_INTEGRADO">
    <g:link controller="removalOrder" action="create" class="btn btn-default disabled" params="[companyId:company.id]">Crear Orden de Retiro</g:link>
  </sec:ifAnyGranted>
  <g:link controller="removalOrder" action="index" class="btn btn-default disabled" params="[companyId:company.id]">Listar Ordenes de Retiro</g:link>
</div>
