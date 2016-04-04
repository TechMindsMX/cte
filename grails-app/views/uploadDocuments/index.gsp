<%! import com.cte.CompanyTaxRegime %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Subir Documentos de la Empresa</title>
    </head>
    <body>
      <g:if test="${company.taxRegime==CompanyTaxRegime.MORAL}">
        <g:set var="msgRegimeCompany" value="de la Empresa"/>
      </g:if><g:else>
        <g:set var="msgRegimeCompany" value="del Empresario"/>
      </g:else>
      <div class="page-title">
        <h1>Documentos ${msgRegimeCompany}</h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
          <li class="active">Documentos ${msgRegimeCompany}</li>
        </ol>
      </div>
      <div id="create-address" class="content scaffold-create" role="main">
        <div class="portlet portlet-blue">
          <div class="portlet-heading">
            <div class="portlet-title">
              <br />
              <br />
            </div>
            <div class="clearfix"></div>
          </div>
          <div id="horizontalFormExample" class="panel-collapse collapse in">
            <div class="portlet-body">
              <g:if test="${company.taxRegime==CompanyTaxRegime.MORAL}">
                <g:render template="moralDocuments"/>
              </g:if><g:else>
                 <g:render template="physicalDocuments"/>
              </g:else>

             <p>
                <br />
                <a href='${createLink(controller:"company",action:"show",id:"${company.id}")}' class="btn btn-default">Regresar</a>
              </p>
            </div>
          </div>
        </div>
      </div>
    </body>
</html>
