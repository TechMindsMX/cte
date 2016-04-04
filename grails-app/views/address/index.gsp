<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'address.label', default: 'Address')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
     <h1><g:message code="address.list.label" args="[entityName]" /></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active">Listado de Direcciones</li>
      </ol>
    </div>
    <div id="list-address" class="content scaffold-list" role="main">
      <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
      </g:if>
      <f:table collection="${addressList}" properties="['id','street','streetNumber','suite','zipCode','colony','country','city','federalEntity']" />

      <div class="pagination">
        <g:paginate total="${addressCount ?: 0}" />
      </div>
    </div>
  </body>
</html>
