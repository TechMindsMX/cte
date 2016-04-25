<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'businessEntity.label', default: 'BusinessEntity')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <asset:javascript src="clabe.js" />
    </head>
    <body>
      <div class="page-title">
       <h1><g:message code="businessEntity.view.create.multi.employees.label" /></h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
          <li class="active"><g:message code="businessEntity.view.create.multi.employees.label" /></li>
        </ol>
      </div>
      <div id="edit-address" class="content scaffold-edit" role="main">
        <div class="portlet portlet-blue">
          <div class="portlet-heading">
            <div class="portlet-title">
              <h4>Creación de Multiples Empleados</h4>
            </div>
            <div class="clearfix"></div>
          </div>
          <div id="horizontalFormExample" class="panel-collapse collapse in">
            <div class="portlet-body">
              <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
              </g:if>

              <g:hasErrors bean="${command}">
                <ul class="error alert alert-danger" role="alert">
                    <g:eachError bean="${command}" var="error">
                    <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                    </g:eachError>
                </ul>
              </g:hasErrors>
              <div class="row">
                <div class="col-md-12">
                  <g:if test="${employeesSucces}">
                    <div class="alert alert-success" role="alert">
                      Se crearon ${employeesSucces.size()} Empleados
                    </div>
                  </g:if>
                  <g:if test="${employeeFail}" >
                    <g:each in="${employeeFail}" var="employee">
                      <div class="alert alert-danger" role="alert">
                        El registro con datos numero de Empleado y nombre ${employee[0]} ${employee[2]} ${employee[3]} ${employee[4]} no fue generado por errores en el archivo
                      </div>
                    </g:each>
                  </g:if>
                  <g:form class="form-horizontal" action="saveMultiEmployees" name="multiEmployeesUpload" method="POST" enctype="multipart/form-data" >
                      <div class="form-group">
                        <input type="file" required="" class="form-control" name="documentExcel" accept="application/vnd.ms-excel" maxlength="5000000" />
                      </div>
                    <input type="submit" class="btn btn-green btn-lg" value="${g.message(code:'businessEntity.multi.employees.button')}" />
                  </g:form>
                </div>
              </div>
          </div>
    </body>
</html>
