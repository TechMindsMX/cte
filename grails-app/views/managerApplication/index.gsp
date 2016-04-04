<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <title><g:message code="manager.list.label" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="admin.list" /></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Administracion</li>
        <li class="active">Seccion de solicitudes de integrados</li>
      </ol>
    </div>
    <ul class="nav nav-tabs nav-pills" role="tablist">
      <li role="presentation"  class="active"><a href="#validate" aria-controls="validate" role="tab" data-toggle="tab">Para Validar</a></li>
      <li role="presentation"><a href="#acepted" aria-controls="acepted" role="tab" data-toggle="tab">Aceptadas</a></li>
      <li role="presentation"><a href="#rejected" aria-controls="rejected" role="tab" data-toggle="tab">Rechazadas</a></li>
    </ul>
    <div class="tab-content">
      <div role="tabpanel" class="tab-pane active" id="validate">
        <div id="list-company" class="col-lg-12" role="main">
          <g:if test="${flash.message}">
          <div class="message" role="status">${flash.message}</div>
          </g:if>
          <g:render template="/managerApplication/companyList" model="[companies:companiesForValidation]"/>
          <div class="pagination">
            <g:paginate total="${pagination1 ?: 0}" />
          </div>
        </div>
      </div>
      <div role="tabpanel" class="tab-pane" id="acepted">
        <div id="list-company" class="col-lg-12" role="main">
          <g:if test="${flash.message}">
          <div class="message" role="status">${flash.message}</div>
          </g:if>
          <g:render template="/managerApplication/companyList" model="[companies:companiesAccepted]"/>
        </div>
      </div>
      <div role="tabpanel" class="tab-pane" id="rejected">
        <div id="list-company" class="col-lg-12" role="main">
          <g:if test="${flash.message}">
          <div class="message" role="status">${flash.message}</div>
          </g:if>
          <g:render template="/managerApplication/companyList" model="[companies:companiesRejected]"/>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
