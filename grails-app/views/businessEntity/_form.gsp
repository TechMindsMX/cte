<%! import com.cte.BusinessEntityType %>
<%! import com.cte.NameType %>
<%! import com.cte.LeadType %>
<f:with bean="businessEntity">
 <input type="hidden" name="clientProviderType" value="${clientProviderType}" />

<div id="person">
<f:field property="type" label="${message(code:"businessEntity.type")}" wrapper="edit">
  <g:if test="${businessEntity?.id}">
    <g:select name="type" from="${BusinessEntityType.values()}" value="${businessEntity.type}" disabled="disabled" class="form-control" />
  </g:if>
  <g:else>
    <g:select name="type" from="${BusinessEntityType.values()}" class="form-control" value="${businessEntity.type}"/>
  </g:else>
</f:field>
</div>
<p></p>

<label id="rfcLabel"><g:message code="businessEntity.rfc" /><span class="required-indicator">*</span></label>
<input id="rfc" name="rfc" value="${businessEntity.rfc}" class="form-control" style="text-transform:uppercase"required="" />

<div id="website">
<f:field property="website" label="${message(code:"businessEntity.website")}" wrapper="home">
  <g:textField name="website" value="${businessEntity.website}"/>
</f:field>
</div>

<div class="fieldcontain fisica">
  <label id="nameLabel"><g:message code="businessEntity.name" /><span class="required-indicator">*</span></label>
  <g:if test="${businessEntity?.id}">
    <g:each var="name" in="${businessEntity.names}">
      <g:if test="${name.type == NameType.NOMBRE}">
        <input id="name" name="name" value="${name.value}" class="form-control" required=""/>
      </g:if>
    </g:each>
  </g:if>
  <g:else>
    <input id="name" name="name" class="form-control" value="${params.name}"/>
  </g:else>
</div>
<div class="fieldcontain fisica">
  <label id="lastNameLabel"><g:message code="businessEntity.lastName" /><span class="required-indicator">*</span></label>
  <g:if test="${businessEntity?.id}">
  <g:each var="name" in="${businessEntity.names}">
  <g:if test="${name.type == NameType.APELLIDO_PATERNO}">
  <input id="lastName" name="lastName" value="${name.value}" class="form-control"/>
  </g:if>
  </g:each>
  </g:if>
  <g:else>
  <input id="lastName" name="lastName" class="form-control" value="${params.lastName}"/>
  </g:else>
</div>
<div class="fieldcontain fisica">
  <label id="motherLastNameLabel"><g:message code="businessEntity.motherLastName" /><span class="required-indicator">*</span></label>
  <g:if test="${businessEntity?.id}">
  <g:each var="name" in="${businessEntity.names}">
  <g:if test="${name.type == NameType.APELLIDO_MATERNO}">
  <input id="motherLastName" name="motherLastName" value="${name.value}" class="form-control"/>
  </g:if>
  </g:each>
  </g:if>
  <g:else>
  <input id="motherLastName" name="motherLastName" class="form-control" value="${params.motherLastName}"/>
  </g:else>
</div>

<g:if test="${clientProviderType == LeadType.EMPLEADO.toString()}" >
  <div class="fieldcontain fisica">
    <label id="numeroEmpleadoLabel">
      <g:message code="businessEntity.numeroEmpleado" /><span class="required-indicator">*</span>
    </label>
    <g:if test="${businessEntity.names.find{ it -> it['type'] == NameType.NUMERO_EMPLEADO}}">
      <input id="numeroEmpleado" name="numeroEmpleado" value="${businessEntity.names.find{ val -> val['type'] == NameType.NUMERO_EMPLEADO}.value}" class="form-control" />
    </g:if>
    <g:else>
      <input id="numeroEmpleado" name="numeroEmpleado" class="form-control" value="${params.numeroEmpleado}" />
    </g:else>
  </div>
</g:if>

<g:if test="${clientProviderType == LeadType.EMPLEADO.toString()}" >
<g:if test="${!businessEntity.names.find{ val -> val['type'] == NameType.NUMERO_EMPLEADO}?.value}" >
  <div class="fieldcontain fisica">
    <label id="clabeLabel">
      <g:message code="businessEntity.clabe" /><span class="required-indicator">*</span>
    </label>
      <input id="clabe" name="clabe" class="form-control" value="${params.clabe}" />
  </div>

  <div class="fieldcontain fisica">
    <label id="bancoLabel">
      <g:message code="businessEntity.banco" /><span class="required-indicator">*</span>
    </label>
      <g:select name="banco" from="${banks}" optionValue="name" optionKey="bankingCode" class="form-control" aria-controls="example-table" required="" noSelection="['':'']" value="${bankAccount?.banco?.bankingCode}"/>
  </div>

  <div class="fieldcontain fisica">
    <label id="cuentaLabel">
      <g:message code="businessEntity.cuenta" /><span class="required-indicator">*</span>
    </label>
      <input id="accountNumber" name="cuenta" class="form-control" value="${params.cuenta}" />
  </div>
  </g:if>
</g:if>

<div class="fieldcontain moral">
  <label id="businessNameLabel"><g:message code="businessEntity.businessName" /><span class="required-indicator">*</span></label>
  <g:if test="${businessEntity?.id}">
  <g:each var="name" in="${businessEntity.names}">
  <g:if test="${name.type == NameType.RAZON_SOCIAL}">
  <input id="businessName" name="businessName" value="${name.value}" class="form-control"/>
  </g:if>
  </g:each>
  </g:if>
  <g:else>
  <input id="businessName" name="businessName" class="form-control" value="${params.businessName}"/>
  </g:else>
</div>
</f:with>

<input type="hidden" id="company" name="company" value="${session.company}" />
<input type="hidden" id="persona" name="persona"/>

<asset:javascript src="selector.js" />
