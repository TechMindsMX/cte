<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <g:pageTitle icon="user.icon.title.show" title="user.label.title" title2="user.label.title.edit" subtitle="user.view.show.label"/>
    </div>
    <p>
    <g:link controller="company" action="show" id="${company}" class="btn btn-default">
      <g:message code="company.show"/>
    </g:link>
    </p>
      <div class="row">
          <div class="portlet portlet-blue">
            <div class="portlet-heading login-heading">
              <div class="portlet-title">
                <br />
              </div>
              <div class="clearfix"></div>
            </div>
            <div class="portlet-body">
              <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
              </g:if>
              <g:hasErrors bean="${this.user}">
                <ul class="errors" role="alert">
                  <g:eachError bean="${this.user}" var="error">
                  <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                  </g:eachError>
                </ul>
              </g:hasErrors>
              <g:form action="update">
                <fieldset class="form">
                  <input type="hidden" name="company" value="${company}" />
                  <input type="hidden" name="user" value="${user.id}" />
                  <f:with bean="user">
                    <f:field property="profile.name" label="${message(code:'user.name')}" wrapper="home"/>
                    <f:field property="profile.lastName" label="${message(code:'user.lastName')}" wrapper="home"/>
                    <f:field property="profile.motherLastName" label="${message(code:'user.motherLastName')}"wrapper="home"/>
                    <f:field property="profile.email" label="${message(code:'user.email')}" wrapper="home"/>
                    <f:field property="profile.rfc" label="${message(code:'user.rfc')}" required="true" wrapper="home"/>
                    <f:field property="profile.curp" label="${message(code:'user.curp')}" required="true" wrapper="home"/>
                    <f:field property="profile.birthDate" label="${message(code:'user.birthdate')}" value="${new Date()}"/>
                    <div class="form-group">
                      <label class="control-label"><g:message code="user.gender" /><span class="required-indicator">*</span></label>
                      <g:select name="banco" from="${com.cte.Gender.values()}" class="form-control" aria-controls="example-table"/>
                    </div>
                    <div class="form-group">
                      <label class="control-label"><g:message code="user.nationality" /><span class="required-indicator">*</span></label>
                      <g:select name="banco" from="${com.cte.Nationality.values()}" class="form-control" aria-controls="example-table"/>
                    </div>

                      <div class="form-group">
                        <label><g:message code="user.number"/></label>
                        <input type="tel" name="number" class="form-control" pattern=".{10,}" required="true"/>
                      </div>
                      <div class="form-group">
                        <label><g:message code="user.extension"/></label>
                        <input type="text" name="extension" class="form-control" />
                      </div>
                       <div class="form-group">
                        <label>${message(code:'user.telephoneType')}</label>
                        <g:select name="type" class="form-control" from="${com.cte.TelephoneType.values()}" />
                      </div>

                  </f:with>
                </fieldset>
                <fieldset class="buttons">
                  <input class="save btn btn-default" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
              </g:form>
            </div>
          </div>
      </div>
  </body>
</html>
