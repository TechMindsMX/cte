<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="home">
    <asset:stylesheet src="third-party/modulus-uno-theme/css/style.css"/>
    <asset:stylesheet src="third-party/modulus-uno-theme/css/plugins.css"/>
    <asset:stylesheet src="third-party/modulus-uno-theme/css/demo.css"/>
  </head>
  <body class="login">

    <div class="container">
      <div class="row">
        <div class="col-md-4 col-md-offset-4">
          <div class="login-banner text-center">
            <h1>
              <asset:image width="35%" src="Logo-ModulusUno-vFINAL-big.png"/>
            </h1>
          </div>
          <div class="portlet portlet-green">
            <div class="portlet-heading login-heading">
              <div class="portlet-title">
                <h4>Bienvenido a ${message(code: 'project.name.label')}</h4>
              </div>
              <div class="clearfix"></div>
            </div>
            <div class="portlet-body">
              <g:if test="${flash.message || session.message}">
              <p class="text-info">${flash.message ?: session.message}</p>
              </g:if>
              <form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>
                <fieldset>
                  <label for="j_username">Nombre de usuario</label>
                  <input type="text" name='j_username' class="form-control" placeholder="Nombre de usuario" id='username' value="${flash.username ?: session.username}" >
                  <label for="j_password">Contraseña</label>
                  <input type="password" name='j_password' class="form-control" placeholder="Contraseña" id='password' >
                  <br/>
                  <button id="btn-success" type="submit" class="btn btn-lg btn-primary btn-block">Accede ahora</button>
                  <hr>
                  <g:link controller="user" action="create" class="btn btn-block btn-default">Registar nueva cuenta</g:link>
                </fieldset>
                <br>
                <p class="small">
                <g:link controller="recovery" action="forgotPassword">Recupera tu contraseña</g:link>
                </p>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
