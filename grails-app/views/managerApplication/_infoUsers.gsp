
<g:each in="${company.legalRepresentatives}" var="user">
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#legalRepresentative${user.id}" aria-expanded="false" aria-controls="legalRepresentative${user.id}">
          ${user.profile.fullName}
        </a>
      </h4>
    </div>
    <div id="legalRepresentative${user.id}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
        <ul>
          <li>
            <span id="user-label" class="property-label">Nombre</span>
            <div class="property-value" aria-labelledby="${label}-label">
            <b>${user.profile.fullName}</b>
            </div>
          </li>
          <li>
            <span id="user-label" class="property-label">RFC</span>
            <div class="property-value" aria-labelledby="${label}-label">
            <b>${user.profile.rfc}</b>
            </div>
          </li>
          <li>
            <span id="user-label" class="property-label">CURP</span>
            <div class="property-value" aria-labelledby="${label}-label">
            <b>${user.profile.curp}</b>
            </div>
          </li>
          <li>
            <span id="user-label" class="property-label">Nacionalidad</span>
            <div class="property-value" aria-labelledby="${label}-label">
            <b>${user.profile.nationality}</b>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</g:each>
