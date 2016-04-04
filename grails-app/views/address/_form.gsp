<asset:javascript src="sepomex.js" />
<f:with bean="address">
  <f:field property="street" label="${message(code:"address.street")}" wrapper="home"/>
  <f:field property="streetNumber" label="${message(code:"address.streetNumber")}" wrapper="home"/>
  <f:field property="suite" label="${message(code:"address.suite")}" wrapper="home"/>
  <f:field property="zipCode" label="${message(code:"address.zipCode")}" wrapper="home"/>
  <f:field property="colony" label="${message(code:"address.colony")}">
    <g:select name="colony" from="${[]}" class="form-control" noSelection="['':'-Selecciona tu colonia-']"/>
  </f:field>
  <f:field property="town" label="${message(code:"address.town")}" wrapper="home"/>
  <f:field property="country" wrapper="home" label="${message(code:"address.country")}" value="${message(code:"address.country.value")}"/>
  <f:field property="city" label="${message(code:"address.city")}" wrapper="home"/>
  <f:field property="federalEntity" label="${message(code:"address.federalEntity")}" wrapper="home"/>
</f:with>
  <div class="fieldcontain required">
    <label for="">${message(code:"address.type")}</label>
    <g:select name="addressType" from="${addressTypes}" class="form-control" optionKey="key" optionValue="value" />
  </div>

<input type="hidden" value="${businessEntity?.id}" name="businessEntityId" />
<input type="hidden" value="${session.sepomexUrl}" id="sepomexUrl"/>
