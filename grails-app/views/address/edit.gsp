<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'address.label', default: 'Address')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
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
            <g:form resource="${this.address}" method="PUT">
              <g:hiddenField name="version" value="${this.address?.version}" />
              <fieldset class="form">
                <g:render template="form" bean="${address}" />
              </fieldset>
              <br />
              <input class="save btn btn-default" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
            </g:form>
          </div>
          <g:link controller="company" action="show" id="${session.company.toLong()}" class="btn btn-default">
            <g:message code="company.show.return"/>
          </g:link>
        </div>
      </div>
    </div>
  </body>
</html>
