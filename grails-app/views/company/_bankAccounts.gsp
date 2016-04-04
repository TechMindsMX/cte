<div class="property-value" aria-labelledby="${label}-label">
  <ul>
    <g:each var="bankAccount" in="${company.banksAccounts.sort{it.banco.name}}">
    <li class="subList"> <g:link action="edit" controller="bankAccount" params="[company:company.id, companyBankAccount:true]" id="${bankAccount.id}">${bankAccount.accountNumber} - ${bankAccount.banco}</g:link></li>
    </g:each>
  </ul>
</div>
<sec:ifAnyGranted roles="ROLE_INTEGRADO">
  <g:link action="create" controller="bankAccount" params="[company:company.id, companyBankAccount:true]" class="btn btn-default">Agregar cuenta bancaria</g:link>
</sec:ifAnyGranted>
