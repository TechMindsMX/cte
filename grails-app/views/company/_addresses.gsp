<div class="property-value" aria-labelledby="${label}-label">
  <ul>
    <g:each var="address" in="${company.addresses}">
      <li class="subList"><g:link controller="address" action="edit" id="${address.id}" >${address}</g:link></li>
    </g:each>
  </ul>
</div>
<sec:ifAnyGranted roles="ROLE_INTEGRADO">
  <g:link action="create" controller="address" class="btn btn-default">Agregar Dirección</g:link>
</sec:ifAnyGranted>