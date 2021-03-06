<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'feesReceipt.label', default: 'FeesReceipt')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="feesReceipt.edit.label" args="[entityName]" /></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active"><g:message code="feesReceipt.active.label" /></b></li>
      </ol>
    </div>
    <div id="edit-feesReceipt" class="content scaffold-edit" role="main">
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
              <g:hasErrors bean="${this.feesReceipt}">
              <ul class="errors" role="alert">
                <g:eachError bean="${this.feesReceipt}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                  </g:eachError>
              </ul>
                </g:hasErrors>
                <g:form resource="${this.feesReceipt}" method="PUT">
                <g:hiddenField name="version" value="${this.feesReceipt?.version}" />
                <fieldset class="form">
                  <g:render template="form" bean="${feesReceipt}" />
                </fieldset>
                <fieldset class="buttons">
                  <input class="save btn btn-default" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
                </g:form>
            </div></div>
        </div>
      </div>
  </body>
</html>
