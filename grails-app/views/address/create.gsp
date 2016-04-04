<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'address.label', default: 'Address')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="address.create"/></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active">Creación de Direcciones</li>
      </ol>
    </div>
    <g:link controller="company" action="show" id="${company?.id}" class="btn btn-default">
      <g:message code="company.show"/>
    </g:link>
    <br /><br />
    <div id="create-address" class="content scaffold-create" role="main">
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
            <div id="create-address" class="content scaffold-create" role="main">
              <g:if test="${flash.message}">
              <div class="message" role="status">${flash.message}</div>
              </g:if>
              <g:hasErrors bean="${this.address}">
              <ul class="errors alert alert-danger alert-dismissable" role="alert">
                <g:eachError bean="${this.address}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
              </ul>
              </g:hasErrors>
              <g:form action="save">
              <fieldset class="form">
                <g:render template="form" bean="${address}" />
              </fieldset>
              <br />
              <g:submitButton name="create" class="save btn btn-default" value="${message(code: 'default.button.create.label', default: 'Create')}" />
              </g:form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
