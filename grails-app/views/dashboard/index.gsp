<!doctype html>
<html>
  <head>
    <title>Integradora de Emprendimientos Culturales | Servicios Financieros</title>
    <meta name="layout" content="main">
  </head>
  <body>

    <div class="page-title">
      <g:pageTitle icon="dashboard.icon.title" title="dashboard.label.title" title2="dashboard.label.title2" subtitle="dashboard.label.subtitle"/>
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
