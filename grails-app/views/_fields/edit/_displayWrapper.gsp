<div class="form-group">
  <label id="${label}-label" class="col-sm-5 control-label"><g:message code="${bean.class.simpleName[0].toLowerCase()}${bean.class.simpleName[1..-1]}.${property}" /></label>
  <div class="col-sm-4">
     <input type="text" class="form-control" id="${id}" name="${property}" placeholder="${label}" value="${value}">
  </div>
</div>
