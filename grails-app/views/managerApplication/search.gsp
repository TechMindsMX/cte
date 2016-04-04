<%! import com.cte.CompanyStatus %>
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
          <li class="active">Seccion de busqueda de integrados</li>
        </ol>
      </div>
      <div class="row">
        <div class="col-md-12">
          <g:form name="searchIntegrated" action="obtainCompanyByFilters" class="form-inline">
            <div class="form-group">
              <label for="rfc">RFC</label>
              <input type="text" class="form-control" name="rfc" />
            </div>
           <div class="form-group">
              <label for="bussinessName">Nombre</label>
              <input type="text" class="form-control" name="bussinessName" />
            </div>
            <div class="form-group">
              <label for="status">Estatus</label>
              <g:select name="status" from="${CompanyStatus.values().findAll{ it != CompanyStatus.CREATED}}"  noSelection="['':'Seleccionar uno']" class="form-control"/>
            </div>
            <button type="submit" class="btn btn-default">Buscar</button>
          </g:form>
        </div>
      </div>
      <g:render template="/managerApplication/companyList" model="[companies:companies]"/>
    </body>
</html>
