<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'company.label', default: 'Company')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
     <h1><g:message code="company.list.label" args="[entityName]" /></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active">Listado de tus Empresas</li>
      </ol>
    </div>
    <div align="center">
      <p class="text-primary">Selecciona una de las empresar para poder operar</p>
      <p class="text-primary">Al seleccionar una empresa encontraras en ella las actividades que puedes efectuar para esa empresa</p>
      <br />
    </div>
    <div id="list-company" role="main">
      <g:if test="${flash.message}">
          <div class="message alert alert-info" align="center" role="status">${flash.message}</div>
      </g:if>
      <g:render template="/managerApplication/companyList" model="[companies:companyList]"/>
    </div>
  </body>
</html>
