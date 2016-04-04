<div>
  <h5 class="text-primary">Instrucciones</h5>
  <p class="text-primary">Se deberán subir los 4 archivos requeridos para poder terminar el proceso de solictud de integración</p>
  <ul>
    <li class="text-primary">1.- Encabezado Estado de CTA Bancario
      <g:if test="${documents*.type.contains('ctaBank')}">
        <span class="label label-success">
          <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
        </span>
      </g:if><g:else>
        <span class="label label-danger">
          <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
        </span>
      </g:else>
    </li>
    <li class="text-primary">2.- 1er Testimonio de la Empresa
      <g:if test="${documents*.type.contains('firstTestimony')}">
        <span class="label label-success">
          <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
        </span>
      </g:if><g:else>
        <span class="label label-danger">
          <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
        </span>
      </g:else>
    </li>
    <li class="text-primary">3.- Último testimonio de la Empresa
      <g:if test="${documents*.type.contains('lastTestimony')}">
        <span class="label label-success">
          <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
        </span>
      </g:if><g:else>
        <span class="label label-danger">
          <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
        </span>
      </g:else>
    </li>
    <li class="text-primary">4.- Registro público de la propiedad
      <g:if test="${documents*.type.contains('rowProperty')}">
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
    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Cuenta Bancaria</a></li>
    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">1er. Testimonio</a></li>
    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">Último Testimonio</a></li>
    <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab">Registro de Propiedad</a></li>
  </ul>
  <div class="tab-content">
      <div role="tabpanel" class="tab-pane active" id="home">
        <g:form class="form-horizontal" action="save" name="documentsOfCompany" method="POST" enctype="multipart/form-data" >
          <input type="hidden" name="type" value="ctaBank" />
            <div class="form-group">
              <input type="file" required="" class="form-control" name="ctaBank" accept="application/pdf,image/png,image/jpg" maxlength="5000000" />
              <g:if test="${company.documents*.type.contains('ctaBank')}">
                <div align="center">
                  <p class="btn btn-success"><i class="fa fa-check-circle"></i>
                    ${company.documents.find{ doc -> doc.type == "ctaBank"} }
                  </p>
                </div>
              </g:if>
            </div>
          <input type="submit" class="btn btn-green btn-lg" value="Subir Estado de CTA" />
        </g:form>
      </div>
      <div role="tabpanel" class="tab-pane" id="profile">
        <g:form class="form-horizontal" action="save" name="documentsOfCompany" method="POST" enctype="multipart/form-data" >
          <input type="hidden" name="type" value="firstTestimony" />
            <div class="form-group">
              <input type="file" required="" class="form-control" name="firstTestimony" accept="application/pdf,image/png,image/jpg"/>
              <g:if test="${company.documents*.type.contains('firstTestimony')}">
                <div align="center">
                  <p class="btn btn-success"><i class="fa fa-check-circle"></i>
                    ${company.documents.find{ doc -> doc.type == "firstTestimony"} }
                  </p>
                </div>
              </g:if>
            </div>
          <input type="submit" class="btn btn-green btn-lg" value="Subir 1er. Testimonio" />
        </g:form>
      </div>
      <div role="tabpanel" class="tab-pane" id="messages">
        <g:form class="form-horizontal" action="save" name="documentsOfCompany" method="POST" enctype="multipart/form-data" >
          <input type="hidden" name="type" value="lastTestimony" />
          <div class="form-group">
            <input type="file" required="" class="form-control" name="lastTestimony" accept="application/pdf,image/png,image/jpg" />
            <g:if test="${company.documents*.type.contains('lastTestimony')}">
              <div align="center">
                <p class="btn btn-success"><i class="fa fa-check-circle"></i>
                  ${company.documents.find{ doc -> doc.type == "lastTestimony"} }
                </p>
              </div>
            </g:if>
          </div>
          <input type="submit" class="btn btn-green btn-lg" value="Subir Último Testimonio" />
        </g:form>
      </div>
      <div role="tabpanel" class="tab-pane" id="settings">
        <g:form class="form-horizontal" action="save" name="documentsOfCompany" method="POST" enctype="multipart/form-data" >
          <input type="hidden" name="type" value="rowProperty" />
          <div class="form-group">
            <input type="file" required="true" class="form-control" name="rowProperty" accept="application/pdf,image/png,image/jpg" />
            <g:if test="${company.documents*.type.contains('rowProperty')}">
              <div align="center">
                <p class="btn btn-success"><i class="fa fa-check-circle"></i>
                  ${company.documents.find{ doc -> doc.type == "rowProperty"} }
                </p>
              </div>
            </g:if>
          </div>
          <input type="submit" class="btn btn-green btn-lg" value="Subir Registro de Propiedad" />
        </g:form>
      </div>
  </div>
</div>

