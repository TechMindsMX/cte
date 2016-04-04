<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'businessEntity.label', default: 'BusinessEntity')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="businessEntity.view.list.label" /></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active"><g:message code="businessEntity.view.list.label" /></li>
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
            <div class="row-fluid">
              <div class="col-md-12">
                <g:form controller="businessEntity" action="search" class="form-horizontal">
                  <div class="form-group">
                    <div class="col-sm-3">
                      <input type="text" name="rfc" class="form-control" placeholder="RFC ó Nombre" maxlength="20"/>
                      <input type="hidden" name="company" value="${session.company}" />
                    </div>
                    <div class="col-sm-2">
                      <button class="btn btn-primary">Buscar</button>
                    </div>
                  </div>
                </g:form>
              </div>
            </div>
            <div id="list-businessEntity" class="content scaffold-list" role="main">
              <g:if test="${flash.message}">
              <div class="message" role="status">${flash.message}</div>
              </g:if>
              <table class="table">
                <tr>
                  <th>RFC</th>
                  <th>Nombre/Razón Social</th>
                  <th>Sitio web</th>
                  <th>Persona</th>
                  <th>Tipo de Relación</th>
                </tr>
                <g:each in="${businessEntityList.sort{it.id}}" var="be">
                  <g:if test="${be?.rfc}">
                  <tr>
                    <td>
                      <g:link controller="businessEntity" action="show" id="${be.id}">${be.rfc}</g:link></td>
                    <td>${be}</td>
                    <td>${be.website}</td>
                    <td>${be.type}</td>
                    <td><my:whatIsThisBusinessEntity be="${be}" /></td>
                  </tr>
                  </g:if>
                </g:each>
              </table>
              <div class="pagination">
                <g:paginate total="${businessEntityCount ?: 0}" />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html
