<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1>Configurar Usuarios</h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active">Configurar Usuarios</li>
      </ol>
    </div>
      <div class="row">
          <div class="portlet portlet-default">
            <div class="portlet-heading">
              <div class="portlet-title">
                <h4>Usuarios registrados de la compañía</h4>
              </div>
              <div class="clearfix"></div>
            </div>
            <div class="portlet-body">
              <g:if test="${flash.message}">
              <div class="alert alert-success" role="alert">${flash.message}</div>
              </g:if>
              <g:link class="btn btn-default" action="authorizer">Nuevo</g:link><br/><br/>
              <g:form action="updateauthorities">
              <table class="table">
               <tr>
                 <th style="text-align:center; width:600px">Usuario</th>
                 <th style="text-align:center">Operador</th>
                 <th style="text-align:center">Autorizador</th>
               </tr>
               <g:each in="${users}" var="user">
                <tr>
                  <td>${user.profile.fullName}</td>
                  <td style="text-align:center">
                    <g:set var="isoperador" value="false"/>
                    <g:if test="${user.authorities.find{it.authority.equals('ROLE_INTEGRADO_OPERADOR')}}">
                      <g:set var="isoperador" value="true"/>
                    </g:if>
                    <span class="well well-sm">
                      <g:checkBox name="operator${user.id}" checked="${isoperador}"/>
                    </span>
                  </td>
                  <td style="text-align:center">
                    <g:set var="isautorizador" value="false"/>
                    <g:if test="${user.authorities.find{it.authority.equals('ROLE_INTEGRADO_AUTORIZADOR')}}">
                      <g:set var="isautorizador" value="true"/>
                    </g:if>
                   <span class="well well-sm">
                     <g:checkBox name="authorizer${user.id}" checked="${isautorizador}"/>
                    </span>
                  </td>
                </tr>
               </g:each>
                 <tr>
                  <td></td>
                  <td></td>
                  <td style="text-align:right"><button class="btn btn-primary">Actualizar</button></td>
                </tr>
              </table>
              </g:form>
          </div>
        </div>
      </div>
 </body>
</html>
