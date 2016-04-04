<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="home" />
    <asset:stylesheet src="third-party/modulus-uno-theme/css/style.css"/>
    <asset:stylesheet src="third-party/modulus-uno-theme/css/plugins.css"/>
    <asset:stylesheet src="third-party/modulus-uno-theme/css/demo.css"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
  </head>
  <body>

    <div class="container">
      <div class="row">
        <div class="col-md-6 col-md-offset-3">
          <div class="login-banner text-center">
            <h1>
              <asset:image src="logo-iecce-blank.png" class="shadow" />
            </h1>
          </div>
          <div class="portlet portlet-green">
            <div class="portlet-heading login-heading">
              <div class="portlet-title">
                <h4><g:message code="user.view.show.label" args="[entityName]" /></h4>
              </div>
              <div class="clearfix"></div>
            </div>
            <div class="portlet-body">
              <g:if test="${flash.message}">
              <div class="message" role="status">${flash.message}</div>
              </g:if>
              <f:display bean="user" property="username" wrapper="show" />
              <f:display bean="user" property="profile.fullName" wrapper="show" />
              <f:display bean="user" property="profile.email" wrapper="show" />
              <g:if test="${user.profile.telephones}" >
              <li class="fieldcontain">
              <span id="telefono-label" class="property-label">Telefonos</span>
              <div class="property-value" aria-labelledby="telefono-label">
                <ul>
                  <g:each var="telefono" in="${user.profile.telephones}">
                  <li class="subList">Número: ${telefono.number} <g:if test="${telefono.extension}">Extensión: ${telefono.extension}</g:if> Tipo: ${telefono.type.value}</li>
                  </g:each>
                  <g:link action="create" controller="telephone" id="${user.id}" params='[company:"${company}"]' class="btn btn-default">Agregar Teléfono</g:link>
                </ul>
              </div>
              </li>
              </g:if>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
