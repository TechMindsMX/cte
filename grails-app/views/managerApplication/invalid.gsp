<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="manager.list.label" /></title>
    </head>
    <body>
        <a href="#list-company" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
        </div>
        <div id="list-company" class="col-lg-12" role="main">
          <g:form name="rejectedCompany" action="rejected">
            <div class="form-group">
              <input type="hidden" name="companyId" value="${company}"
              <label for="reason">Motivo del rechazo</label>
              <textarea name="reason" style="overflow:auto;resize:none" class="form-control" rows="3" required="required"></textarea>
            </div>
            <button type="submit" class="btn btn-default">Enviar</button>
          </g:form>
        </div>
    </body>
</html>
