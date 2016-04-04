
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#company" aria-expanded="true" aria-controls="company">
          ${company.bussinessName}
        </a>
      </h4>
    </div>
    <div id="company" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
        <ul>
          <li>
            <span id="company-label" class="property-label">Empresa</span>
            <div class="property-value" aria-labelledby="${label}-label">
              <b>${company.bussinessName}</b>
            </div>
          </li>
          <li>
            <span id="company-label" class="property-label">RFC</span>
            <div class="property-value" aria-labelledby="${label}-label">
              <b>${company.rfc}</b>
            </div>
          </li>
          <li>
            <span id="company-label" class="property-label">Estatus</span>
            <div class="property-value" aria-labelledby="${label}-label">
              <b>${company.status}</b>
            </div>
          </li>
          <li>
            <span id="company-label" class="property-label">Facturación Bruta Anual</span>
            <div class="property-value" aria-labelledby="${label}-label">
              <b>$
              <g:formatNumber number="${company.grossAnnualBilling}" type="currency" currencyCode="MXN" /></b>
            </div>
          </li>
          <li>
            <span id="company-label" class="property-label">Cuentas Bancarias</span>
            <div class="property-value" aria-labelledby="${label}-label">
              <ul>
                <g:each in="${company.banksAccounts}" var="account">
                  <li><i>${account.accountNumber} - ${account.banco}</i></li>
                </g:each>
              </ul>
            </div>
          </li>
          <li>
            <span id="company-label" class="property-label">Direcciones</span>
            <div class="property-value" aria-labelledby="${label}-label">
              <ul>
                <g:each in="${company.addresses}" var="address">
                  <li><i>${address}</i></li>
                </g:each>
              </ul>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>