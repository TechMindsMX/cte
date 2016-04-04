<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Subir Documentos del Representante Legal</title>
    </head>
    <body>
      <div class="page-title">
        <h1><g:message code="document.create"/></h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
          <li class="active">Documentos del Representante</li>
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
              <div >
                <h5 class="text-primary">Instrucciones</h5>
                <p class="text-primary">Se deberán subir los 4 archivos requeridos para poder terminar el proceso de solictud de integración</p>
                <ul>
                  <li class="text-primary">1.- Identificación
                    <g:if test="${documents*.type.contains('ife')}">
                      <span class="label label-success">
                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                      </span>
                    </g:if><g:else>
                      <span class="label label-danger">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                      </span>
                    </g:else>
                  </li>
                  <li class="text-primary">2.- RFC
                    <g:if test="${documents*.type.contains('rfc')}">
                      <span class="label label-success">
                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                      </span>
                    </g:if><g:else>
                      <span class="label label-danger">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                      </span>
                    </g:else>
                 </li>
                  <li class="text-primary">3.- Comprobante de Domicilio
                    <g:if test="${documents*.type.contains('domicilio')}">
                      <span class="label label-success">
                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                      </span>
                    </g:if><g:else>
                      <span class="label label-danger">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                      </span>
                    </g:else>
                  </li>
                  <li class="text-primary">4.- Poder
                    <g:if test="${documents*.type.contains('poderRepresentante')}">
                      <span class="label label-success">
                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                      </span>
                    </g:if><g:else>
                      <span class="label label-danger">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                      </span>
                    </g:else>
                 </li>
                </ul>
              </div>
                  <div>
                    <ul class="nav nav-tabs nav nav-pills" role="tablist">
                      <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Identificación</a></li>
                      <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">RFC</a></li>
                      <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Comprobante de Domicilio</a></li>
                      <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Poder</a></li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="home">
                          <g:form class="form-horizontal" action="saveLegalRepresentativeDocuments" name="documentsOfLegalRepresentative" method="POST" enctype="multipart/form-data" >
                            <input type="hidden" name="companyId" value="${company}" />
                            <input type="hidden" name="userId" value="${user}" />
                            <input type="hidden" name="type" value="ife" />
                              <div class="form-group">
                                <input type="file" required="" class="form-control" name="ife" accept="application/pdf,image/png,image/jpg" maxlength="5000000" />
                                <g:if test="${documents*.type.contains('ife')}">
                                  <div align="center">
                                    <p class="btn btn-success"><i class="fa fa-check-circle"></i>
                                      ${documents.find{ doc -> doc.type == "ife"} }
                                    </p>
                                  </div>
                                </g:if>
                              </div>
                            <input type="submit" class="btn btn-green btn-lg" value="Subir Identificación" />
                          </g:form>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="profile">
                          <g:form class="form-horizontal" action="saveLegalRepresentativeDocuments" name="documentsOfLegalRepresentative" method="POST" enctype="multipart/form-data" >
                            <input type="hidden" name="companyId" value="${company}" />
                            <input type="hidden" name="userId" value="${user}" />
                            <input type="hidden" name="type" value="rfc" />
                              <div class="form-group">
                                  <input type="file" required="" class="form-control" name="rfc" accept="application/pdf,image/png,image/jpg" />
                                  <g:if test="${documents*.type.contains('rfc')}">
                                    <div align="center">
                                      <p class="btn btn-success"><i class="fa fa-check-circle"></i> ${documents.find{ doc -> doc.type == "rfc"} }
                                      </p>
                                    </div>
                                  </g:if>
                              </div>
                            <input type="submit" class="btn btn-green btn-lg" value="Subir RFC" />
                          </g:form>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="messages">
                          <g:form class="form-horizontal" action="saveLegalRepresentativeDocuments" name="documentsOfLegalRepresentative" method="POST" enctype="multipart/form-data" >
                            <input type="hidden" name="companyId" value="${company}" />
                            <input type="hidden" name="userId" value="${user}" />
                            <input type="hidden" name="type" value="domicilio" />
                            <div class="form-group">
                              <input type="file" required="" class="form-control" name="domicilio" accept="application/pdf,image/png,image/jpg" />
                              <g:if test="${documents*.type.contains('domicilio')}">
                                <div align="center">
                                  <p class="btn btn-success"><i class="fa fa-check-circle"></i>
                                    ${documents.find{ doc -> doc.type == "domicilio"} }
                                  </p>
                                </div>
                              </g:if>
                            </div>
                            <input type="submit" class="btn btn-green btn-lg" value="Subir Comprobante de Domicilio" />
                          </g:form>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="settings">
                          <g:form class="form-horizontal" action="saveLegalRepresentativeDocuments" name="documentsOfLegalRepresentative" method="POST" enctype="multipart/form-data" >
                            <input type="hidden" name="companyId" value="${company}" />
                            <input type="hidden" name="userId" value="${user}" />
                            <input type="hidden" name="type" value="poderRepresentante" />
                            <div class="form-group">
                              <input type="file" required="true" class="form-control" name="poderRepresentante" accept="application/pdf,image/png,image/jpg" />
                              <g:if test="${documents*.type.contains('poderRepresentante')}">
                                <div align="center">
                                  <p class="btn btn-success"><i class="fa fa-check-circle"></i>
                                    ${documents.find{ doc -> doc.type == "poderRepresentante"} }
                                  </p>
                                </div>
                              </g:if>
                            </div>
                            <input type="submit" class="btn btn-green btn-lg" value="Subir Poder" />
                          </g:form>
                        </div>
                    </div>
                  </div>
                  <p>
                    <br />
                    <a href='${createLink(controller:"company",action:"show",id:"${company}")}' class="btn btn-default">Regresar</a>
                  </p>
            </div>
          </div>
        </div>
      </div>
    </body>
</html>
