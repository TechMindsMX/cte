<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'commission.label', default: 'Commission')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="commission.edit.label" args="[entityName]" /></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active">Información de la Comisión</b></li>
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
            <g:hasErrors bean="${this.commission}">
            <ul class="errors" role="alert">
              <g:eachError bean="${this.commission}" var="error">
              <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
              </g:hasErrors>
              <g:form resource="${this.commission}" method="PUT">
              <g:hiddenField name="version" value="${this.commission?.version}" />
              <fieldset class="form">
                <g:render template="form" bean="commission"/>
              </fieldset>
              <fieldset class="buttons">
                <input class="save btn btn-default" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
              </fieldset>
              </g:form>
          </div>
        </div>
      </div>
    </div>
    <g:link class="list btn btn-default" action="index" params='[company:"${commission.company.id}"]'><g:message code="commission.list.label" args="[entityName]" /></g:link>
    <g:link class="create btn btn-default" action="create"  params='[company:"${commission.company.id}"]' ><g:message code="commission.create.label" args="[entityName]" /></g:link>
  </body>
</html>
