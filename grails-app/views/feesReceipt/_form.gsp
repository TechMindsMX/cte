<f:with bean="feesReceipt">
  <div class="form-group">
    <label for="">${message(code:"feesReceipt.amount")}</label>
    <input type="text" class="text" value="${formatNumber(number:feesReceipt?.amount, locale: 'es_MX', format:'##0.00')}" min="0.1" pattern="[0-9]+(\.[0-9]{2})?$" title="Ingrese una cantidad sin separador de miles y con dos decimales" name="amount" />
  </div>
</f:with>
<input type="hidden" id="businessEntity" name="businessEntity" value="${params.businessEntity}" />
