<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'depositOrder.label', default: 'DepositOrder')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
      <div class="page-title">
       <h1><g:message code="depositOrder.list" args="[entityName]" /></h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
          <li class="active">Orden de Depósito</li>
        </ol>
      </div>
      <div id="edit-address" class="content scaffold-edit" role="main">
        <div class="portlet portlet-blue">
          <div class="portlet-heading">
            <div class="portlet-title">
              <br />
              <br />
            </div>
            <div class="clearfix"></div>
          </div>
          <div id="horizontalFormExample" class="panel-collapse collapse in">
            <div class="portlet-body">
              <g:if test="${flash.message}">
                  <div class="message" role="status">${flash.message}</div>
              </g:if>
              <div class="table-responsive">
                <f:table collection="${depositOrderList}" properties="['status','amount','dateCreated']"/>
                <div class="paginateButtons">
                  <g:paginate class="pagination" total="${depositOrderCount ?: 0}" />
                </div>
              </div>
              <sec:ifAnyGranted roles="ROLE_INTEGRADO,ROLE_LEGAL_REPRESENTATIVE">
                <g:link class="create btn btn-default" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
              </sec:ifAnyGranted>
            </div>
          </div>
        </div>
      </div>
    </body>
</html>
