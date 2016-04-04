<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'commission.label', default: 'Commission')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="commission.list.label" /></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active">Comisiones</li>
      </ol>
    </div>
    <div id="list-commission" class="content scaffold-list" role="main">
      <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
      </g:if>

      <f:table collection="${commissionList}" />

      <div class="pagination">
        <g:paginate total="${commissionCount ?: 0}" />
      </div>
    </div>
    <g:link controller="company" action="show" id="${session?.company ?: company?.id}" class="home btn btn-default">
      <g:message code="company.show"/>
    </g:link>
    <g:if test="${commissionList?.size() < 3}">
      <g:link class="create btn btn-default" action="create" params="[companyId:company.id]">
        <g:message code="commission.create.label" args="[entityName]" />
      </g:link>
    </g:if>
    <g:link controller="managerApplication" action="accepted" class="btn btn-success" params="[companyId:company.id]">
      Aprobar Solicitud
    </g:link>
  </body>
</html>
