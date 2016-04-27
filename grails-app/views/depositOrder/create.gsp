<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'depositOrder.label', default: 'DepositOrder')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
      <div class="page-title">
       <h1><g:message code="depositOrder.create" args="[entityName]" /></h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
          <li class="active">Orden de Depósito</li>
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
                <div class="alert alert-danger" role="alert">${flash.message}</div>
              </g:if>
              <g:hasErrors bean="${this.depositOrder}">
                <ul class="alert alert-danger" role="alert">
                  <g:eachError bean="${this.depositOrder}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                  </g:eachError>
                </ul>
              </g:hasErrors>
              <g:form action="save">
                  <f:with bean="depositOrder">
                    <f:field property="amount"  label="${message(code:"depositOrder.amount")}" wrapper="currencyRequired"/>
                  </f:with>
                  <fieldset class="buttons">
                    <g:submitButton name="create" class="save btn btn-default" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                  </fieldset>
              </g:form>
            </div>
          </div>
        </div>
      </div>
    </body>
</html>
