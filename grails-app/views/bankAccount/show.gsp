<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'bankAccount.label', default: 'BankAccount')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
      <div class="page-title">
        <h1><g:message code="bankAccount.show" args='["${companyInfo.companyInfo()}"]' /></h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i><g:link controller="company" action="show" id="${session.company}"> Compañia</g:link></li>
          <li class="active">Cuentas Bancarias</li>
        </ol>
      </div>
      <div id="create-bankAccount" class="content scaffold-create" role="main">
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
              <g:hasErrors bean="${this.bankAccount}">
                <ul class="alert alert-danger alert-dismissable" role="alert">
                  <g:eachError bean="${this.bankAccount}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                  </g:eachError>
                </ul>
              </g:hasErrors>
              <div id="show-bankAccount" class="content scaffold-show" role="main">
                <g:if test="${flash.message}">
                  <div class="message" role="status">${flash.message}</div>
                </g:if>
                <ol class="property-list company">
                  <f:display bean="bankAccount" property="accountNumber" wrapper="show"/>
                  <f:display bean="bankAccount" property="branchNumber" wrapper="show"/>
                  <f:display bean="bankAccount" property="clabe" wrapper="show"/>
                  <f:display bean="bankAccount" property="banco" wrapper="show"/>
                  <li class="fieldcontain">
                    <div class="property-value" aria-labelledby="company-label">
                      <a href='${createLink(controller:"company", action:"show", id:"${session.company}")}' class="btn btn-default"> regresar</a>
                    </div>
                  </li>
                </ol>
              </div>
            </div>
          </div>
        </div>
      </div>
    </body>
</html>
