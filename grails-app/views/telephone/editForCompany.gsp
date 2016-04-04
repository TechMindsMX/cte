<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'telephone.label', default: 'Telephone')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
      <div class="page-title">
        <h1><g:message code="company.new" /></h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
          <li class="active">Agregar Telefono</li>
        </ol>
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
                  <div id="edit-telephone" class="content scaffold-edit" role="main">
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <g:hasErrors bean="${this.telephone}">
                    <ul class="errors" role="alert">
                      <g:eachError bean="${this.telephone}" var="error">
                      <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                      </g:hasErrors>
                      <g:form action="updateForCompany" id="${session.company}" method="PUT">
                      <g:hiddenField name="version" value="${this.telephone?.version}" />
                      <fieldset class="form">
                        <f:all bean="telephone" except="type"/>
                      </fieldset>
                      <fieldset class="buttons">
                        <input class="save btn btn-default" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                      </fieldset>
                      </g:form>
                  </div>
                </div>
              </div>
            </div>
          </div>
       </div>
    </body>
</html>
