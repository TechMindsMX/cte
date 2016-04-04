<%! import com.cte.CompanyStatus %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'company.label', default: 'Company')}" />
    <asset:stylesheet src="company/show.css" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="company.show"/></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active">Informacion de la Empresas</li>
      </ol>
    </div>
    <div class="row">
      <div class="col-lg-12">
        <div class="portlet portlet-default">
          <div class="portlet-heading">
            <div class="portlet-title">
              <h4>Datos de la Empresa</h4>
            </div>
            <div class="clearfix"></div>
          </div>
          <div id="defaultPortlet" class="panel-collapse collapse in">
            <div class="portlet-body">
              <form class="form-horizontal" role="form">
                <f:display bean="company" property="bussinessName" wrapper="describe" /></b>
              <f:display bean="company" property="webSite"  wrapper="describe" />
              <f:display bean="company" property="employeeNumbers" wrapper="describe" />
              <f:display bean="company" property="numberOfAuthorizations" wrapper="describe" />
              <div class="form-group">
                <label id="grossAnnualBilling-label" class="col-sm-5 control-label">Facturacion Anual Bruta</label>
                <div class="col-sm-4">
                  ${integradora.formatPrice(number:company.grossAnnualBilling)}
                </div>
              </div>
              <g:if test="${balance != null}">
              <div class="form-group">
                <label id="grossAnnualBilling-label" class="col-sm-5 control-label">Balance de la Compañia en MXN</label>
                <div class="col-sm-4">
                  ${integradora.formatPrice(number:balance)}
                </div>
              </div>
              </g:if>
              <g:if test="${usd != null}">
              <div class="form-group">
                <label id="grossAnnualBilling-label" class="col-sm-5 control-label">Balance de la Compañia en USD</label>
                <div class="col-sm-4">
                  ${integradora.formatPrice(number:usd)}
                </div>
              </div>
              </g:if>

              <f:display bean="company" property="rfc" wrapper="describe" />
              </form>
              <div class="property-value">
                <sec:ifAnyGranted roles="ROLE_INTEGRADO">
                  <g:if test="${available && legalRepresentativesAvilableWithDocuments}">
                    <g:link controller="requestCompany" action="create" class="btn btn-success btn-block" params="[companyId:company.id]">Enviar mi Solicitud</g:link>
                  </g:if>
                  <g:if test="${company.status == CompanyStatus.REJECTED}">
                    <g:link action="rejected" class="btn btn-default" id="${company.id}">Revisar Razones de Rechazo</g:link>
                  </g:if>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles="ROLE_ADMIN">
                  <g:if test="${company.status == CompanyStatus.VALIDATE}">
                    <g:link controller="managerApplication" action="accepted" class="btn btn-success" params="[companyId:company.id]">Aprobar Solicitud</g:link>
                    <g:link controller="managerApplication" action="invalid" class="btn btn-danger" params="[companyId:company.id]">Rechazar Solicitud</g:link>
                  </g:if>
                </sec:ifAnyGranted>
              </div>
            </div>
          </div>
          <div class="portlet-footer">
            <sec:ifAnyGranted roles="ROLE_INTEGRADO">
            <g:link class="btn btn-default" action="edit" resource="${this.company}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            </sec:ifAnyGranted>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-6">
          <div class="portlet portlet-blue">
            <div class="portlet-heading">
              <div class="portlet-title">
                <h4>Representantes Legales</h4>
              </div>
              <div class="clearfix"></div>
            </div>
            <div id="defaultPortlet" class="panel-collapse collapse in">
              <div class="portlet-body">
                <form class="form-horizontal" role="form">
                  <g:render template="legalRepresentatives" />
                </form>
              </div>
            </div>
            <div class="portlet-footer">
            </div>
          </div>
        </div>
        <div class="col-lg-6">
          <div class="portlet portlet-blue">
            <div class="portlet-heading">
              <div class="portlet-title">
                <h4>Direcciones</h4>
              </div>
              <div class="clearfix"></div>
            </div>
            <div id="defaultPortlet" class="panel-collapse collapse in">
              <div class="portlet-body">
                <g:if test="${!company.addresses}">
                  <h4><span class="label label-warning">Debe agregar al menos una dirección</span></h4>
                </g:if>
                <g:render template="addresses" />
              </div>
            </div>
            <div class="portlet-footer">
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-6">
          <div class="portlet portlet-blue">
            <div class="portlet-heading">
              <div class="portlet-title">
                <h4>Teléfonos</h4>
              </div>
              <div class="clearfix"></div>
            </div>
            <div id="defaultPortlet" class="panel-collapse collapse in">
              <div class="portlet-body">
                <g:render template="telephones" />
              </div>
            </div>
            <div class="portlet-footer">
            </div>
          </div>
        </div>
        <div class="col-lg-6">
          <div class="portlet portlet-blue">
            <div class="portlet-heading">
              <div class="portlet-title">
                <h4>Cuentas Bancarias</h4>
              </div>
              <div class="clearfix"></div>
            </div>
            <div id="defaultPortlet" class="panel-collapse collapse in">
              <div class="portlet-body">
                <g:render template="bankAccounts" />
              </div>
            </div>
            <div class="portlet-footer">
            </div>
          </div>
        </div>
      </div>
  </body>
</html>
