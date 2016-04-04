<b><span id="address-label" class="property-label">Productos</span></b>
<br>
<div class="property-value" aria-labelledby="${label}-label">
  <g:each var="product" in="${products}">
  <g:link controller="product" action="edit" id="${product?.id}" params="[company:company.id,edit:true]">${product}</g:link>
  </g:each>
  <sec:ifAnyGranted roles="ROLE_INTEGRADO">
  <g:link action="create" controller="product" params="[company:company.id]" class="btn btn-default">Agregar Producto</g:link>
  <g:link controller="product" action="index" params="[company:company.id]" class="btn btn-default">Ver productos</g:link>
  </sec:ifAnyGranted>
</div>
