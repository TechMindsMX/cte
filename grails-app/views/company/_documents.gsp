 <div class="property-value" aria-labelledby="${label}-label">
  <g:if test="${company.documents}">
    <div class="col-md-5">
      <g:link action="show" controller="uploadDocuments" params="[companyId:company.id]" class="btn btn-default">Visor de Documentos</g:link>
    </div>
  </g:if>
  <sec:ifAnyGranted roles="ROLE_INTEGRADO">
    <div class="col-md-6">
      <g:link  controller="uploadDocuments" params="[company:company.id]" class="btn btn-default">Agregar Documentos</g:link>
    </div>
  </sec:ifAnyGranted>
</div>
<br />
<br />
