<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="user.view.show.label" args="[entityName]" /></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active">Informacion de Usuario</li>
      </ol>
    </div>
      <div class="portlet portlet-blue">
        <div class="portlet-heading">
          <div class="portlet-title">
            <br /><br />
          </div>
          <div class="clearfix"></div>
        </div>
        <div class="portlet-body">
          <div id="show-user" class="content scaffold-show" role="main">
            <g:if test="${flash.message}">
              <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list user">
              <f:display bean="user" property="username" wrapper="show" />
              <f:display bean="user" property="profile.fullName" wrapper="show" />
              <f:display bean="user" property="profile.email" wrapper="show" />
              <g:if test="${user.profile.telephones}" >
              <li class="form-group">
                <span id="telefono-label" class="property-label">Telefonos</span>
                <div class="property-value " aria-labelledby="telefono-label">
                  <ul>
                  <g:each var="telefono" in="${user.profile.telephones}">
                  <li class="subList ">Número: ${telefono.number} <g:if test="${telefono.extension}">Extensión: ${telefono.extension}</g:if> Tipo: ${telefono.type.value}</li>
                  </g:each>
                  <g:link action="create" controller="telephone" id="${user.id}" params='[company:"${company}"]' class="btn btn-default">Agregar Teléfono</g:link>
                </ul>
                </div>
              </li>
            </g:if>
            </ol>
            <g:link controller="company" action="show" id="${company}" class="home btn btn-default">
              <g:message code="company.show"/>
            </g:link>
        </div>
      </div>


  </body>
</html>
