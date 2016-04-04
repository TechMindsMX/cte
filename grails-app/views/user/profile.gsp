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
              <div class="portlet-widgets">
              </div>
              <div class="clearfix"></div>
          </div>
          <div id="bluePortlet" class="panel-collapse collapse in">
              <div class="portlet-body">
                <div id="create-address" class="content scaffold-create" role="main">
                  <g:if test="${flash.message}">
                  <div class="message" role="status">${flash.message}</div>
                  </g:if>
                  <ol class="property-list user">
                    <f:display bean="user" property="username" wrapper="show" />
                    <f:display bean="user" property="profile.fullName" wrapper="show" />
                    <f:display bean="user" property="profile.email" wrapper="show" />
                  </ol>
                  <g:link controller="dashboard"  class="home btn btn-default">
                    Regresar
                  </g:link>
                </div>
              </div>
          </div>
        </div>
  </body>
</html>
