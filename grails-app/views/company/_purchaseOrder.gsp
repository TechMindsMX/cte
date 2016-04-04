<b><span id="address-label" class="property-label">Órdenes de Pago a Proveedores</span></b>
<br>
<div class="property-value" aria-labelledby="${label}-label">
  <sec:ifAnyGranted roles="ROLE_INTEGRADO">
    <g:link controller="purchaseOrder" action="create" class="btn btn-default" params="[companyId:company.id]">
  Nueva orden de compra
    </g:link>
  </sec:ifAnyGranted>
  <g:link controller="purchaseOrder" action="index" class="btn btn-default" params="[companyId:company.id]">
  Listar órdenes de pago a proveedores
  </g:link>
</div>
