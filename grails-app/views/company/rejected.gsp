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
        <div class="col-lg-6">
          <div class="portlet portlet-default">
            <div class="portlet-heading">
              <div class="portlet-title">
              </div>
              <div class="clearfix"></div>
            </div>
            <div id="defaultPortlet" class="panel-collapse collapse in">
              <div class="portlet-body">
      <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
      </g:if>

      <ol class="property-list company">
        <b><f:display bean="company" property="bussinessName" wrapper="show" /></b>
        <f:display bean="company" property="webSite"  wrapper="show" />
        <f:display bean="company" property="employeeNumbers" wrapper="show" />
        <li class="fieldcontain">
          <span id="address-label" class="property-label">Facturacion Anual Bruta</span>
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
                      <button type="button" class="btn btn-primary document" path="${document.localUrl}" data-toggle="modal" data-target="#myModal">${document.type}</button>
                    </div>
                    <div class="col-md-6">
                      <b>${reasonRepresentatives.find{ row -> row.assetId == document.id} }</b>
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
              <g:each var="document" in="${company.documents}" >
                <div class="col-md-12">
                  <div class="col-md-2">
                    <button type="button" class="btn btn-primary document" path="${document.localUrl}" data-toggle="modal" data-target="#myModal">${document.type}</button>
                  </div>
                  <div class="col-md-6">
                    <b>${reasonDocuments.find{ row -> row.assetId == document.id} }</b>
                  </div>
                </div>
                <p><br /><br /></p>
              </g:each>
            </div>
          </li>
          <g:if test="${reasonCompany}">
            <li class="fieldcontain">
              <span id="address-label" class="property-label">Otra Razon del Rechazo</span>
              <div class="property-value" aria-labelledby="${label}-label">
                <b>${reasonCompany?.reason}</b>
              </div>
            </li>
          </g:if>
          <li class="fieldcontain">
            <span id="address-label" class="property-label"></span>
            <div class="property-value" aria-labelledby="${label}-label">
              <a href='${createLink(controller:"company", action:"show", id:"${company.id}")}' class="btn btn-default"><g:message code="company.show.return"/></a>
            </div>
          </li>
          <input type="hidden" value="${company.id}" name="companyId" />
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
                <iframe id="frameView" src="" width="100%" height="400px"></iframe>
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
    </g:javascript>
  </body>
</html>
