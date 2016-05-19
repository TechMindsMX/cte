<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'payroll.label', default: 'Payroll')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
      <div class="page-title">
        <g:pageTitle icon="payroll.icon.title.list" title="payroll.label.title" title2="payroll.label.title.create" subtitle="payroll.view.create.label"/>
      </div>
      <div class="portlet portlet-blue">
          <div class="portlet-heading">
              <div class="portlet-title">
                <br /><br />
              </div>
              <div class="portlet-widgets">
              </div>
              <div class="clearfix"></div>
          </div>
          <div id="bluePortlet" class="panel-collapse collapse in">
              <div class="portlet-body">
                <div id="create-payroll" class="content scaffold-create" role="main">
                  <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                  </g:if>
                  <g:hasErrors bean="${this.payroll}">
                    <ul class="errors" role="alert">
                      <g:eachError bean="${this.payroll}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                      </g:eachError>
                    </ul>
                  </g:hasErrors>
                    <g:form action="save" enctype="multipart/form-data" method="POST">
                      <div class="form-group">
                        <input type="file" required="" class="form-control" name="documentExcel" accept="application/vnd.ms-excel" maxlength="5000000" />
                      </div>
                      <fieldset class="buttons">
                        <g:submitButton name="create" class="save btn btn-green btn-lg" value="${message(code: 'payroll.create.button', default: 'Create')}" />
                      </fieldset>
                    </g:form>
                </div>
              </div>
          </div>
      </div>
    </body>
</html>
