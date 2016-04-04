<div>
  <h5 class="text-primary">Instrucciones</h5>
  <p class="text-primary">Se deberán subir los 5 archivos requeridos para poder terminar el proceso de solictud de integración</p>
  <ul>
    <li class="text-primary">1.- Identificación
      <g:if test="${documents*.type.contains('identification')}">
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
    <li class="text-primary">3.- CURP
      <g:if test="${documents*.type.contains('curp')}">
        <span class="label label-success">
          <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
        </span>
      </g:if><g:else>
        <span class="label label-danger">
          <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
        </span>
      </g:else>
   </li>
    <li class="text-primary">4.- Comprobante de Domicilio
      <g:if test="${documents*.type.contains('addressProof')}">
        <span class="label label-success">
          <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
        </span>
      </g:if><g:else>
        <span class="label label-danger">
          <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
        </span>
      </g:else>
   </li>
    <li class="text-primary">5.- Estado de Cuenta
      <g:if test="${documents*.type.contains('accountStatement')}">
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
  <ul class="nav nav-tabs nav-pills" role="tablist">
    <li role="presentation" class="active">
      <a href="#home" aria-controls="home" role="tab" data-toggle="tab">Identificación</a>
    </li>
    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">RFC</a></li>
    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">CURP</a></li>
    <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Comprobante de domicilio</a></li>
     <li role="presentation"><a href="#bank" aria-controls="bank" role="tab" data-toggle="tab">Estado de Cuenta</a></li>
 </ul>
  <div class="tab-content">
      <div role="tabpanel" class="tab-pane active" id="home">
        <g:form class="form-horizontal" action="save" name="documentsOfCompany" method="POST" enctype="multipart/form-data" >
          <input type="hidden" name="type" value="identification" />
            <div class="form-group">
              <input type="file" required="" class="form-control" name="identification" accept="application/pdf,image/png,image/jpg" maxlength="5000000" />
              <g:if test="${documents*.type.contains('identification')}">
                <div align="center">
                  <p class="btn btn-success"><i class="fa fa-check-circle"></i>
                    ${documents.find{ doc -> doc.type == "identification"} }
                  </p>
                </div>
              </g:if><g:else>
                <input type="submit" class="btn btn-green btn-lg" value="Subir Identificación" />
              </g:else>
            </div>
        </g:form>
      </div>
      <div role="tabpanel" class="tab-pane" id="profile">
        <g:form class="form-horizontal" action="save" name="documentsOfCompany" method="POST" enctype="multipart/form-data" >
          <input type="hidden" name="type" value="rfc" />
            <div class="form-group">
              <input type="file" required="" class="form-control" name="rfc" accept="application/pdf,image/png,image/jpg"/>
              <g:if test="${documents*.type.contains('rfc')}">
                <div align="center">
                  <p class="btn btn-success"><i class="fa fa-check-circle"></i>
                    ${documents.find{ doc -> doc.type == "rfc"} }
                  </p>
                </div>
              </g:if><g:else>
                <input type="submit" class="btn btn-green btn-lg" value="Subir RFC" />
              </g:else>
            </div>
        </g:form>
      </div>
      <div role="tabpanel" class="tab-pane" id="messages">
        <g:form class="form-horizontal" action="save" name="documentsOfCompany" method="POST" enctype="multipart/form-data" >
          <input type="hidden" name="type" value="curp" />
          <div class="form-group">
            <input type="file" required="" class="form-control" name="curp" accept="application/pdf,image/png,image/jpg" />
            <g:if test="${documents*.type.contains('curp')}">
              <div align="center">
                <p class="btn btn-success"><i class="fa fa-check-circle"></i>
                  ${documents.find{ doc -> doc.type == "curp"} }
                </p>
              </div>
            </g:if><g:else>
              <input type="submit" class="btn btn-green btn-lg" value="Subir CURP" />
            </g:else>
          </div>
        </g:form>
      </div>
      <div role="tabpanel" class="tab-pane" id="settings">
        <g:form class="form-horizontal" action="save" name="documentsOfCompany" method="POST" enctype="multipart/form-data" >
          <input type="hidden" name="type" value="addressProof" />
          <div class="form-group">
            <input type="file" required="true" class="form-control" name="addressProof" accept="application/pdf,image/png,image/jpg" />
            <g:if test="${documents*.type.contains('addressProof')}">
              <div align="center">
                <p class="btn btn-success"><i class="fa fa-check-circle"></i>
                  ${documents.find{ doc -> doc.type == "addressProof"} }
                </p>
              </div>
            </g:if><g:else>
              <input type="submit" class="btn btn-green btn-lg" value="Subir Comprobante de Domicilio" />
            </g:else>
          </div>
        </g:form>
      </div>
      <div role="tabpanel" class="tab-pane" id="bank">
        <g:form class="form-horizontal" action="save" name="documentsOfCompany" method="POST" enctype="multipart/form-data" >
          <input type="hidden" name="type" value="accountStatement" />
          <div class="form-group">
            <input type="file" required="true" class="form-control" name="accountStatement" accept="application/pdf,image/png,image/jpg" />
            <g:if test="${documents*.type.contains('accountStatement')}">
              <div align="center">
                <p class="btn btn-success"><i class="fa fa-check-circle"></i>
                  ${documents.find{ doc -> doc.type == "accountStatement"} }
                </p>
              </div>
            </g:if><g:else>
              <input type="submit" class="btn btn-green btn-lg" value="Subir Estado de Cuenta" />
            </g:else>
          </div>
        </g:form>
      </div>
  </div>
</div>

