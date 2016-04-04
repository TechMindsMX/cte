<b><span id="client-label" class="property-label">Clientes/Proveedores</span></b>
<div class="property-value" aria-labelledby="${label}-label">
  <sec:ifAnyGranted roles="ROLE_INTEGRADO">
    <g:link action="create" controller="businessEntity" params="[company:company.id]" class="btn btn-default">Agregar Cliente ó Proveedor</g:link>
  </sec:ifAnyGranted>
  <g:link controller="businessEntity" class="btn btn-default" action="index" params="[company:company.id]">Ver lista clientes proveedores</g:link>
</div>
