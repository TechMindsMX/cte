<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
      <div class="page-title">
        <h1><g:message code="product.show.label" /></h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i> Compañía</li>
          <li class="active">Información del Producto</b></li>
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
                <div class="alert alert-info" role="alert">${flash.message}</div>
              </g:if>

              <g:render template="show"/>

              <g:form resource="${this.product}" method="DELETE">
                  <fieldset class="buttons">
                      <g:link class="edit btn btn-default" action="edit" resource="${this.product}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                  </fieldset>
              </g:form>
            </div>
          </div>
        </div>
      </div>
        <g:link class="list btn btn-default" action="index"><g:message code="product.view.list.label" args="[entityName]" /></g:link>
        <g:link class="create btn btn-default" action="create"><g:message code="product.view.create.label" args="[entityName]"/></g:link>
    </body>
</html>
