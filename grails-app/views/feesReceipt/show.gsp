<%! import com.cte.FeesReceiptStatus %>
<%! import com.cte.RejectReason %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'feesReceipt.label', default: 'FeesReceipt')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <h1><g:message code="feesReceipt.show.label" /></h1>
      <ol class="breadcrumb">
        <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
        <li class="active"><g:message code="feesReceipt.active.label" /></b></li>
      </ol>
    </div>
    <div id="edit-address" class="content scaffold-edit" role="main">
      <div class="portlet portlet-blue">
        <div class="portlet-heading">
          <div class="portlet-title">
            <br />
            <br />
          </div>
          <div class="clearfix"></div>
        </div>

        <div id="defaultPortlet" class="panel-collapse collapse in">
          <form class="form-horizontal" role="form">
            <br/>
            <f:display bean="feesReceipt" property="amount"  wrapper="describePrice"/>
            <f:display bean="feesReceipt" property="iva"  wrapper="describePrice"/>
            <f:display bean="feesReceipt" property="isr"  wrapper="describePrice"/>
            <f:display bean="feesReceipt" property="ivaWithHolding"  wrapper="describePrice"/>
            <g:link controller="businessEntity" action="show" id="${feesReceipt.businessEntity.id}">
            <f:display bean="feesReceipt" property="businessEntity"  wrapper="describe"/>
            </g:link>
            <f:display bean="feesReceipt" property="status"  wrapper="describe"/>
          </form>
          <sec:ifAnyGranted roles="ROLE_INTEGRADO, ROLE_INTEGRADO_OPERADOR">
          <hr/>
          <h4>Agregar imágen del recibo</h4>
          <g:form class="form-horizontal" action="addDocumentToFeesReceipt" name="documentForFeesReceipt" method="POST" enctype="multipart/form-data" id="${feesReceipt.id}">
          <input type="file" required="" class="form-control" name="feesReceiptDocument" accept="image/*" maxlength="5000000" />
          <br/>
          <input type="submit" class="btn btn-default" value="Subir documento de facturación" />
          <g:if test="${feesReceipt.documents}">
          <h4>Documentos adjuntos</h4>
          <ul>
            <g:each in="${feesReceipt.documents}" var="document">
            <li>
              <a href="${document.localUrl}"><i class="glyphicon glyphicon-download-alt"></i> ${document}</a>
            </li>
            </g:each>
          </ul>
          </g:if>
          </g:form>
          </sec:ifAnyGranted>
          <fieldset class="buttons">
            <g:if test="${feesReceipt.status == FeesReceiptStatus.CREADA}">
            <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
            <div class="container-fluid">
              <div class="row">
                <div class="col-md-6">
                  <g:link action="authorizeFeesReceipt" class="btn btn-warning btn-block" id="${feesReceipt.id}">Autorizar Recibo de Honorarios</g:link>
                </div>
                <div class="col-md-6">
                  <a data-toggle="collapse" role="button" href="#inputReasonCancellation" class="btn btn-danger btn-block" aria-expanded="false" aria-controls="inputReasonCancellation">Cancelar el Recibo de Honorarios</a>
                </div>
                <div class="row">
                  <div class="col-md-12">
                    <div class="collapse" id="inputReasonCancellation">
                      <div class="well">
                        <g:form action="cancelFeesReceipt" id="${feesReceipt.id}">
                        <div class="form-group">
                          <g:select name="rejectReason" from="${RejectReason.values()}" value="${feesReceipt.rejectReason}" class="form-control" />
                          <br/>
                          <g:textArea name="comments" placeholder="Comentarios opcionales" rows="3" cols="60" maxLength="255" class="form-control"/>
                          <br/>
                          <button type="submit" class="btn btn-danger">Ejecutar Cancelación</button>
                        </div>
                          </g:form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <br/>
            </sec:ifAnyGranted>
                          </g:if>
          </fieldset>
          <fieldset class="buttons">
            <g:if test="${feesReceipt.status == FeesReceiptStatus.AUTORIZADA}">
            <sec:ifAnyGranted roles="ROLE_ADMIN_IECCE">
            <div class="container-fluid">
              <div class="row">
                <div class="col-md-6">
                  <g:link action="executeFeesReceipt" class="btn btn-success btn-block" id="${feesReceipt.id}">Ejecutar Recibo de Honorarios</g:link>
                </div>
                <div class="col-md-6">
                  <a data-toggle="collapse" role="button" href="#inputReasonCancellation" class="btn btn-danger btn-block" aria-expanded="false" aria-controls="inputReasonCancellation">Rechazar el Recibo de Honorarios</a>
                </div>
                <div class="row">
                  <div class="col-md-12">
                    <div class="collapse" id="inputReasonCancellation">
                      <div class="well">
                        <g:form action="rejectFeesReceipt" id="${feesReceipt.id}">
                        <div class="form-group">
                          <g:select name="rejectReason" from="${RejectReason.values()}" value="${feesReceipt.rejectReason}" class="form-control" />
                          <br/>
                          <g:textArea name="comments" placeholder="Comentarios opcionales" rows="3" cols="60" maxLength="255" class="form-control"/>
                          <br/>
                          <button type="submit" class="btn btn-danger">Rechazar Recibo de Honorarios</button>
                        </div>
                          </g:form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <br/>
            </sec:ifAnyGranted>
                          </g:if>
          </fieldset>
        </div>
      </div>
    </div>
    <g:link class="list btn btn-default" action="index" params='[businessEntity:feesReceipt.businessEntity.id]'><g:message code="feesReceipt.list.label" args="[entityName]" /></g:link>
    <g:link class="create btn btn-default" action="create" params='[businessEntity:feesReceipt.businessEntity.id]'><g:message code="feesReceipt.create.label" args="[entityName]"/></g:link>
  </body>
</html>
