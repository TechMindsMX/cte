<li>
	<span id="company-label" class="property-label">Empresa</span>
	<div class="property-value" aria-labelledby="${label}-label">
		<b>${infoCompany.bussinessName}</b>
	</div>
</li>
<li>
	<span id="company-label" class="property-label">RFC</span>
	<div class="property-value" aria-labelledby="${label}-label">
	 	<b>${infoCompany.rfc}</b>
	</div>
</li>
<li>
	<span id="company-label" class="property-label">Estatus</span>
	<div class="property-value" aria-labelledby="${label}-label">
	 	<b>${infoCompany.status}</b>
	</div>
</li>
<li>
	<span id="company-label" class="property-label">Facturación Bruta Anual</span>
	<div class="property-value" aria-labelledby="${label}-label">
	 	<b>$
	 	<g:formatNumber number="${infoCompany.grossAnnualBilling}" type="currency" currencyCode="MXN" /></b>
	</div>
</li>
<li>
	<span id="company-label" class="property-label">Cuentas Bancarias</span>
	<div class="property-value" aria-labelledby="${label}-label">
	 	<ul>
	 		<g:each in="${infoCompany.banksAccounts}" var="account">
	 			<li><i>${account.accountNumber} - ${account.banco}</i></li>
	 		</g:each>
	 	</ul>
	</div>
</li>
<li>
	<span id="company-label" class="property-label">Direcciones</span>
	<div class="property-value" aria-labelledby="${label}-label">
	 	<ul>
	 		<g:each in="${infoCompany.addresses}" var="address">
	 			<li><i>${address}</i></li>
	 		</g:each>
	 	</ul>
	</div>
</li>