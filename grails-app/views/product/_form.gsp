<f:with bean="product">
  <div class="form-group">
    <label for="">${message(code:"product.sku.label")}</label>
    <g:textField name="sku" class="form-control" required="" pattern=".{4,50}" title="4 caracteres mínimo" autofocus="" value="${product?.sku}"/>
  </div>
  <div class="form-group">
    <label for="">${message(code:"product.name.label")}</label>
    <g:textField name="name" class="form-control" required="" value="${product?.name}"/>
  </div>
  <div class="form-group">
    <label for="">${message(code:"product.price.label")}</label>
    <input type="text" name="price" value="${formatNumber(number:product.price,locale: 'es_MX', format:'##0.00')}" class="form-control" required="" min="0" pattern="[0-9]+(\.[0-9]{2})?$" title="Ingrese una cantidad sin separador de miles y con dos decimales"/>
  </div>
  <div class="form-group">
    <label for="">${message(code:"product.ieps.label")}</label>
    <input type="text" name="ieps" class="form-control" required="" min="0" value="${product?.ieps}" pattern="[0-9]+(\.[0-9]{2})?$" title="Ingrese una cantidad sin separador de miles y con dos decimales"/>
  </div>
  <div class="form-group">
    <label for="">${message(code:"product.iva.label")}</label>
    <input type="text" name="iva" class="form-control" required="" min="0" value="${product?.iva}" pattern="[0-9]+(\.[0-9]{2})?$" title="Ingrese una cantidad sin separador de miles y con dos decimales"/>
  </div>
	<div class="form-group">
	  <label for="">${message(code:"product.currencyType.label")}</label>
	  <g:select name="currencyType" from="${com.cte.CurrencyType.values()}" class="form-control"  />
  </div>
  <div class="form-group">
    <label for="">${message(code:"product.unitType.label")}</label>
    <g:select name="unitType" from="${com.cte.UnitType.values()}" class="form-control"  />
  </div>
</f:with>
