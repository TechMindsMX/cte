<!doctype html>
<html>
  <head>
    <title>Integradora de Emprendimientos Culturales | Servicios Financieros</title>
    <meta name="layout" content="main">
  </head>
  <body>

    <div class="page-title">
      <h1>
        Tablero principal
        <small>Administración total</small>
      </h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-dashboard"></i>  <a href="index.html">Tablero principal</a>
        </li>
        <li class="active">Bienvenido, puedes empezar por aquí... </li>
      </ol>
    </div>

    <sec:ifAnyGranted roles="ROLE_LEGAL_REPRESENTATIVE,ROLE_INTEGRADO_AUTORIZADOR,ROLE_INTEGRADO_OPERADOR">
      <div class="row">
        <div class="col-md-12">
          <g:if test="${companies.isEmpty()}">
          <div class="alert alert-info">
            <strong>Atención:</strong> Vemos que aún no tienes empresas registradas en la integradora, comienza...
          </div>
          <p>
            <g:link class="btn btn-default btn-lg" controller="company" action="create">
            Registra tu primera empresa
          </g:link>
          </g:if>
          </p>
        </div>
      </div>
    </sec:ifAnyGranted>

  </body>
</html>
