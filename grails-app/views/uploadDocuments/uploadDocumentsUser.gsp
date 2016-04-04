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
    <div class="portlet portlet-blue">
      <div class="portlet-heading">
        <div class="portlet-title">
          <h4>Representantes Legales</h4>
        </div>
        <div class="clearfix"></div>
      </div>
      <div id="defaultPortlet" class="panel-collapse collapse in">
        <div class="portlet-body">
          <form class="form-horizontal" role="form">
            <g:render template="legalRepresentatives" />
          </form>
        </div>
      </div>
      <div class="portlet-footer">
      </div>
    </div>
  </div>
  </body>
</html>
