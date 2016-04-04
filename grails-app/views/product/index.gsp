<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
      <div class="page-title">
         <h1><g:message code="product.view.list.label" /></h1>
          <ol class="breadcrumb">
            <li><i class="fa fa-caret-square-o-up"></i> Compañía</li>
            <li class="active">Productos/Servicios</li>
          </ol>
      </div>
      <div class="portlet portlet-blue">
        <div class="portlet-heading">
          <div class="portlet-title">
            <br />
            <br />
          </div>
          <div class="clearfix"></div>
        </div>
        <div id="list-product" class="panel-collapse collapse in">
          <div class="portlet-body">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <div class="table-responsive">
             <table class="table">
               <tr>
                 <th><g:message code="product.sku.label" /></th>
                 <th><g:message code="product.name.label" /></th>
                 <th><g:message code="product.price.label" /></th>
                 <th><g:message code="product.ieps.label" /></th>
                 <th><g:message code="product.iva.label" /></th>
                 <th><g:message code="product.unitType.label" /></th>
                 <th><g:message code="product.currencyType.label" /></th>
               </tr>
               <g:each in="${productList.sort{it.name}}" var="product">
               <tr>
                 <td>
                  <g:link action="show" id="${product.id}">
                     ${product.sku}
                  </g:link>
                 </td>
                 <td>${product.name}</td>
                 <td>${integradora.formatPrice(number:product.price)}</td>
                 <td>${product.ieps}</td>
                 <td>${product.iva}</td>
                 <td>${product.unitType}</td>
                 <td>${product.currencyType}</td>
               </tr>
               </g:each>
             </table>
            </div>
            <div class="pagination">
                <g:paginate total="${productCount ?: 0}" />
            </div>
          </div>
        </div>
        </div>
        <g:link controller="company" action="show" id="${session.company}" class="home btn btn-default">
          <g:message code="company.show"/>
        </g:link>
        <g:link class="create btn btn-default" action="create" params='[company:"${session.company}"]'><g:message code="product.view.create.label" args="[entityName]" /></g:link>
      </div>
    </body>
</html>
