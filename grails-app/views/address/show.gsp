<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'address.label', default: 'Address')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="company.show"/></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active">Edicion de Direcciones</b></li>
      </ol>
    </div>
    <div id="edit-address" class="content scaffold-edit" role="main">
      <div class="portlet portlet-blue">
        <div class="portlet-heading">
          <div class="portlet-title">
            <br />
            <br />
          </div>
          <div class="clearfix"></div>
        </div>
        <div id="horizontalFormExample" class="panel-collapse collapse in">
          <div class="portlet-body">
            <div id="show-address" class="content scaffold-show" role="main">
              <h1><g:message code="default.show.label" args="[entityName]" /></h1>
              <g:if test="${flash.message}">
              <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list company">
              <f:display bean="address" property="street" wrapper="show" />
              <f:display bean="address" property="streetNumber" wrapper="show" />
              <f:display bean="address" property="suite" wrapper="show" />
              <f:display bean="address" property="zipCode" wrapper="show" />
              <f:display bean="address" property="colony" wrapper="show" />
              <f:display bean="address" property="country" wrapper="show" />
              <f:display bean="address" property="city" wrapper="show" />
              <f:display bean="address" property="federalEntity" wrapper="show" />
              <li class="fieldcontain">
                <div class="property-value" aria-labelledby="company-label">
                  <a href='${createLink(controller:"company",action:"show", id:"${company}")}' class="btn btn-default">Regresar</a>
                </div>
              </li>
            </ol>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
