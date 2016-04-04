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
        <li class="active">Seccion de compañias</li>
      </ol>
    </div>
    <div align="center">
      <p class="text-primary">Selecciona una de las empresar para poder operar</p>
      <br />
    </div>
    <div id="list-company" class=" portlet-blue" role="main">
      <g:if test="${flash.message}">
        <div class="message alert alert-info" align="center" role="status">${flash.message}</div>
      </g:if>
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
            <div id="companies" class="content scaffold-create" role="in">
              <g:form class="form-group" id="company-selection" url="[action:'setCompanyInSession',controller:'company']" >
                <g:select class="form-control" name="company" from="${companies}" optionKey="id" noSelection="${['':'Seleccione una Empresa']}" required="required"/>
                <input type="submit" class="btn btn-default" value="ir" />
              </g:form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
