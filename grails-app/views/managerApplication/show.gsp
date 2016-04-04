<%! import com.modulus.uno.CompanyStatus %>

<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'company.label', default: 'Company')}" />
    <asset:stylesheet src="company/show.css" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
    <style type="text/css">
      .modal-dialog {
        width: 90%;
        height: 90%;
        padding: 0;
      }

      .modal-content {
        height: 90%;
        border-radius: 0;
      }
    </style>
  </head>
  <body>
    <div class="page-title">
     <h1><g:message code="admin.list" /></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Administración</li>
        <li class="active">Datos de la Compañía <b>${company.bussinessName}</b></li>
      </ol>
    </div>
    <div class="portlet portlet-default">
      <div class="portlet-heading">
        <div class="portlet-title">
          <h4>Datos de la Empresa</h4>
        </div>
        <div class="clearfix"></div>
      </div>
      <div id="defaultPortlet" class="panel-collapse collapse in">
        <div class="portlet-body">
          <div id="show-company" class="content scaffold-show" role="main">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>

            <ol class="property-list company">
              <b><f:display bean="company" property="bussinessName" wrapper="show" /></b>
              <f:display bean="company" property="webSite"  wrapper="show" />
              <f:display bean="company" property="employeeNumbers" wrapper="show" />
              <li class="fieldcontain">
                <span id="address-label" class="property-label">Facturación Anual Bruta</span>
                <div class="property-value" aria-labelledby="${label}-label">
                  <g:formatNumber number="${company.grossAnnualBilling}" type="currency" currencyCode="MXN" />
                </div>
              </li>
              <li class="fieldcontain">
                <span id="address-label" class="property-label">Direcciones</span>
                <div class="property-value" aria-labelledby="${label}-label">
                  <ul>
                    <g:each var="address" in="${company.addresses}">
                      <li class="subList">${address.info()}</li>
                    </g:each>
                  </ul>
                </div>
              </li>
              <li class="fieldcontain">
                <span id="address-label" class="property-label">Cuentas Bancarias</span>
                <div class="property-value" aria-labelledby="${label}-label">
                  <ul>
                    <g:each var="bankAccount" in="${company.banksAccounts}">
                      <li class="subList">Numero de cuenta: ${bankAccount.accountNumber} sucursal: ${bankAccount.branchNumber} Clabe: ${bankAccount.clabe}  Banco: ${bankAccount.banco}</li>
                    </g:each>
                  </ul>
                </div>
              </li>
              <g:form name="companyVerify" action="rejected">
                <li class="fieldcontain">
                  <span id="address-label" class="property-label">Representantes Legales</span>
                  <input type="hidden" name="legalRepresentatives" value="${company.legalRepresentatives*.id.join(',')}" />
                  <div class="property-value" aria-labelledby="${label}-label">
                    <g:each in="${legalRepresentatives}" var="user,documents">
                      ${user.profile.fullName} <i>${user.profile.info()}</i>
                      <input type="hidden" name="legalRepresentativeDocuments-${user.id}" value="${documents*.id.join(',')}" />
                      <br />
                      <g:each var="document" in="${documents}">
                        <div class="col-md-12">
                          <div class="col-md-3">
                            <button type="button" class="btn btn-primary document" path="${document.localUrl}" data-toggle="modal" data-target="#myModal"><g:message code="document.type.${document.type}"/></button>
                          </div>
                          <div class="col-md-2">
                            <input type="checkbox" class="document-rejected" id="${document.id}" value="${document.id}"> Rechazado
                          </div>
                          <div class="col-md-5">
                            <textarea rows="2" cols="50" placeholder="Razón de rechazo" name="legalRepresentative-${document.id}" class="form-control reason-${document.id}" style="overflow:auto;resize:none;display:none;"></textarea>
                          </div>
                        </div>
                        <p><br /><br /></p>
                      </g:each>
                      <p><br /><br /></p>
                      <p><br /><hr style="width: 100%; color: black; height: 1px; background-color:black;"></p>
                    </g:each>
                  </div>
                </li>
                <li class="fieldcontain">
                  <span id="address-label" class="property-label">Documentos</span>
                  <div class="property-value" aria-labelledby="${label}-label">
                    <input type="hidden" name="documents" value="${company.documents*.id.join(',')}" />
                    <g:each var="document" in="${company.documents.sort{it.type}}" >
                      <div class="col-md-12">
                        <div class="col-md-3">
                          <button type="button" class="btn btn-primary document" path="${document.localUrl}" data-toggle="modal" data-target="#myModal"><g:message code="document.type.${document.type}"/></button>
                        </div>
                        <div class="col-md-3">
                          <input type="checkbox" class="document-rejected" id="${document.id}" value="${document.id}"> Rechazado
                        </div>
                        <div class="col-md-5">
                          <textarea rows="2" cols="50" placeholder="Razon de rechazo" name="document-${document.id}" class="form-control reason-${document.id}" style="overflow:auto;resize:none;display:none;"></textarea>
                        </div>
                      </div>
                      <p><br /><br /></p>
                    </g:each>
                  </div>
                </li>
                <li class="fieldcontain">
                  <span id="address-label" class="property-label">Otra Razón del Rechazo</span>
                  <div class="property-value" aria-labelledby="${label}-label">
                    <textarea name="reason" style="overflow:auto;resize:none" class="form-control" rows="3"></textarea>
                  </div>
                </li>
                <input type="hidden" value="${company.id}" name="companyId" />
                <br />
                <li class="fieldcontain">
                  <div class="property-value">
                    <g:link controller="commission" action="create" class="btn btn-success" params="[companyId:company.id]">Continuar y dar de alta comisiones</g:link>
                    <input class="btn btn-danger" type="submit" value="Rechazar Solicitud" />
                  </div>
                </li>
              </g:form>
            </ol>
            <div class="nav" role="navigation">
            </div>
            <div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
              <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  </div>
                  <div class="modal-body">
                    <div class="row">
                      <div class="col-md-4">
                        Información:
                        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                          <g:render template="infoCompany" bean="${company}" />
                          <g:render template="infoUsers" bean="${company}" />
                        </div>
                      </div>
                      <div class="col-md-7">
                        <iframe id="frameView" src="" width="100%" height="400px"></iframe>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    <g:javascript>
      $(".document").on("click",function(){
        var url = $(this).attr("path");
        $("#frameView").attr("src",url);
      });
      $(".document-rejected").on("click",function(){
        var id = this.id
        if($("#"+id).is(':checked')) {
          $(".reason-"+id).show();
        } else {
          $(".reason-"+id).hide();
        }
      });
    </g:javascript>
  </body>
</html>
