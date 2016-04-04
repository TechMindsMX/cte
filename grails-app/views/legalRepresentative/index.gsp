<!DOCTYPE html>
<html lang="en">
<head>
  <meta name="layout" content="main" />
  <g:set var="entityName" value="${message(code: 'legalRepresentative.label', default: 'Representante Legal')}" />
  <title></title>
</head>
<body>
  <div class="page-title">
    <h1><g:message code="legalRepresentative.search" /></h1>
    <ol class="breadcrumb">
      <li><i class="fa fa-caret-square-o-up"></i><g:link controller="company" action="show" id="${session.company}"> Compañia</g:link></li>
      <li class="active">Representantes Legales</li>
    </ol>
  </div>
  <g:if test="${!isCurrentLegalRepresentative}">
    <g:link controller="legalRepresentative" params="[user:currentUser.id]" action="addRepresentative" class="btn btn-default">
      <g:message code="legalRepresentative.myself" />
    </g:link>
  </g:if>
  <g:link class="create" controller="user" action="legalRepresentative" params="[legal:true,company:companyId]" class="btn btn-default" >
    <g:message code="legalRepresentative.create" />
  </g:link>
        <g:link controller="company" action="show" id="${companyId}" class="home btn btn-default">
        <g:message code="company.show"/>
        </g:link>
  <br />
  <div class="content scaffold-create">
    <div class="row-fluid">
      <div class="col-md-12">
        <br /><br />
        <g:form controller="legalRepresentative" action="search" class="form-horizontal" role="form">
          <div class="form-group">
            <div class="col-sm-3">
              <input type="text" name="rfc" class="form-control" placeholder="RFC" maxlength="20"/>
              <input type="hidden" name="company" value="${companyId}" />
            </div>
            <div class="col-sm-2">
              <button class="btn btn-default">Buscar</button>
            </div>
          </div>
        </g:form>
      </div>
    </div>

    <div class="row-fluid">
      <div class="col-lg-12">
        <g:if test="${user}">
        <table class="table">
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Apellido</th>
              <th>Apellido Materno</th>
              <th>Email</th>
              <th>RFC</th>
              <th>CURP</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>${user?.profile?.name}</td>
              <td>${user?.profile?.lastName}</td>
              <td>${user?.profile?.motherLastName}</td>
              <td>${user?.profile?.email}</td>
              <td>${user?.profile?.rfc}</td>
              <td>${user?.profile?.curp}</td>
              <td>
                <g:link controller="legalRepresentative" id="${companyId}" params="[user:user.id]" action="addRepresentative" class="btn btn-default">
                  Asignar Representante Legal
                </g:link>
              </td>
            </tr>
          </tbody>
        </table>
        </g:if>
        <g:if test="${flash.message}">
          <div class="alert alert-warning">
            <p>${flash.message}</p>
          </div>
        </g:if>
      </div>
    </div>


  </div>
</body>
</html>
