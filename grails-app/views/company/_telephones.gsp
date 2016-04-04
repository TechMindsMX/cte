<div class="property-value" aria-labelledby="${label}-label">
  <ul>
    <g:each var="telephone" in="${company.telephones}">
      <li class="subList"><g:link controller="telephone" action="editForCompany" id="${telephone.id}" >${telephone.toString()}</g:link></li>
    </g:each>
  </ul>
</div>
<sec:ifAnyGranted roles="ROLE_INTEGRADO">
  <g:link action="createForCompany" controller="telephone" class="btn btn-default">Agregar Telefono</g:link>
</sec:ifAnyGranted>
