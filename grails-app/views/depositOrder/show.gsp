<%! import com.cte.DepositOrderStatus %>
<%! import com.cte.RejectReason %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'depositOrder.label', default: 'Orden de Depósito')}" />
        <title><g:message code="depositOrder.show" args="[entityName]" /></title>
    </head>
    <body>
      <div class="page-title">
       <h1><g:message code="depositOrder.show" args="[entityName]" /></h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
          <li class="active">Orden de Depósito</li>
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
          <div id="horizontalFormExample" class="panel-collapse collapse in">
            <div class="portlet-body">
              <g:if test="${flash.message}">
                <div class="alert alert-danger" role="alert">${flash.message}</div>
              </g:if>
              <ol class="property-list depositOrder">
                <li class="form-group">
                  <label class="control-label">Monto</label>
                  <div class="form-control" aria-labelledby="${label}-label">
                    ${integradora.formatPrice(number:depositOrder.amount)}
                  </div>
                </li>
                <li class="form-group">
                  <label class="control-label">Estatus</label>
                  <div class="form-control" aria-labelledby="${label}-label">
                    <g:message code="depositOrder.status.${depositOrder.status}"/>
                  </div>
                </li>
                <li class="form-group">
                  <label class="control-label">Compañía</label>
                  <div class="form-control" aria-labelledby="${label}-label">
                    ${depositOrder.company}
                  </div>
                </li>
              </ol>

              <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE, ROLE_INTEGRADO, ROLE_INTEGRADO_OPERADOR, ROLE_INTEGRADO_AUTORIZADOR">
                <g:if test="${depositOrder.status != DepositOrderStatus.CREATED}">
                  <iframe id="frameView" src="${depositOrder.documents?.first().localUrl}" width="100%" height="400px"> </iframe>
                </g:if>
              </sec:ifAnyGranted>

              <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
                <div class="col-sm-12 col-md-12">
                  <g:if test="${depositOrder.status == DepositOrderStatus.VALIDATE}">
                   <g:link action="authorizeDepositOrder" id="${depositOrder.id}" params="[status:'VALIDATE']" class="btn btn-warning"> Autorizar Depósito</g:link>
                     <a data-toggle="collapse" role="button" href="#inputReasonCancellation" class="btn btn-danger" aria-expanded="false" aria-controls="inputReasonCancellation">Cancelar Depósito</a>

                     <div class="collapse" id="inputReasonCancellation">
                      <div class="well">
                        <g:form action="cancelDepositOrder">
                        <div class="form-group">
                        <input type="hidden" name="id" value="${depositOrder.id}"/>
                        <g:select name="rejectReason" from="${RejectReason.values()}" noSelection="['':'- Elija el motivo de cancelación...']" required="" class="form-control" valueMessagePrefix="rejectReason" />
                        <g:textArea name="rejectComment" placeholder="Ingrese un comentario de la cancelación" rows="3" cols="60" maxLength="255" class="form-control"/>
                        <button class="btn btn-danger">Ejecutar Cancelación</button>
                        </div>
                        </g:form>
                      </div>
                     </div>
                 </g:if>
                  <br /><br />
                </div>
              </sec:ifAnyGranted>


              <div class="col-sm-12 col-md-12">
              <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE, ROLE_INTEGRADO, ROLE_INTEGRADO_OPERADOR">
                <g:if test="${depositOrder.status == DepositOrderStatus.CANCELED || depositOrder.status == DepositOrderStatus.REJECTED}">
                  <div class="alert alert-danger" role="alert">
                    <label class="control-label">Motivo ${message(code:'depositOrder.rejectReason.'+depositOrder.status)}:</label>
                  <g:message code="rejectReason.${depositOrder.rejectReason}" default="${depositOrder.rejectReason}" />
                  <g:if test="${depositOrder.rejectComment}">
                  <textarea class="form-control" rows="3" cols="60" readonly>${depositOrder.rejectComment}</textarea>
                  </div>
                  </g:if>
                </g:if>
              </sec:ifAnyGranted>

              <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
                <g:if test="${depositOrder.status == DepositOrderStatus.AUTHORIZED}">
                  <g:link action="executeDepositOrder" id="${depositOrder.id}" params="[status:'AUTHORIZED']" class="btn btn-primary"> Ejecutar Depósito</g:link>
                  <a data-toggle="collapse" role="button" href="#inputReasonRejected" class="btn btn-danger" aria-expanded="false" aria-controls="inputReasonRejected">Rechazar Depósito</a>
                 <div class="collapse" id="inputReasonRejected">
                  <div class="well">
                    <g:form action="rejectDepositOrder">
                    <div class="form-group">
                    <input type="hidden" name="id" value="${depositOrder.id}"/>
                        <g:select name="rejectReason" from="${RejectReason.values()}" noSelection="['':'- Elija el motivo de rechazo...']" required="" class="form-control" valueMessagePrefix="rejectReason" />
                        <g:textArea name="rejectComment" placeholder="Ingrese un comentario del rechazo" rows="3" cols="60" maxLength="255" class="form-control"/>
                    <button class="btn btn-danger">Ejecutar Rechazo</button>
                    </div>
                    </g:form>
                  </div>
                 </div>
                </g:if>

             </sec:ifAnyGranted>

             <sec:ifAnyGranted roles="ROLE_INTEGRADO, ROLE_INTEGRADO_OPERADOR">
                <g:if test="${depositOrder.status == DepositOrderStatus.CREATED}">
                 <g:link controller="depositOrder" action="confirmDepositOrder" id="${depositOrder.id}" class="btn btn-success"><i class="fa fa-check"></i> Solicitar Autorización de Depósito</g:link>
                </g:if>
              </sec:ifAnyGranted>
              <br /><br />
              </div>

              <g:if test="${params.status}">
                <g:link action="list" params="[status:"${params.status}"]" class="list btn btn-default"><i class="fa fa-reply"></i> Regresar</g:link>
              </g:if>
              <g:else>
                <g:link action="list" class="list btn btn-default"><i class="fa fa-reply"></i> Regresar</g:link>
              </g:else>

            </div>
          </div>
        </div>
      </div>
  </body>
</html>
