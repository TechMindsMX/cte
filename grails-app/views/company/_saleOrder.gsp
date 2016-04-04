<b><span id="address-label" class="property-label">Ordenes de Facturación y Cobranza</span></b>
<br>
<div class="property-value" aria-labelledby="${label}-label">
  <sec:ifAnyGranted roles="ROLE_INTEGRADO">
    <g:link controller="saleOrder" action="create" class="btn btn-default" params="[companyId:company.id]">
  Nueva orden de venta
    </g:link>
  </sec:ifAnyGranted>
  <g:link controller="saleOrder" action="index" class="btn btn-default" params="[companyId:company.id]">
  Listar órdenes de venta
  </g:link>
</div>
