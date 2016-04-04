var options = {
  url: function(phrase) {
    var companyId = $("input[name='saleOrder.id']").val();
    return "../listProducts?pname=" + phrase + "&format=json";
  },

  getValue: "name",

  list: {
    sort: {
      enabled: true
    },
    onSelectItemEvent: function() {
      $('#sku').val('');
      $('#price').val('');
      $('#unit').val('');
      /* Obtenemos el valor del campo */
      var valor = $('#product-name').getItemData($('#product-name').getSelectedItemIndex()).name;
      /* Si la longitud del valor es mayor a 2 caracteres.. */
      if(valor.length>=3){
        /* Hacemos la consulta ajax */
        var consulta = $.ajax({
          type:'POST',
          url:'../getProduct',
          data:{nombre:valor, companyid:$("input[name='saleOrder.id']").val()},
          datatype:'JSON'
        });

        /* En caso de que se haya retornado bien.. */
        consulta.done(function(data){
          if(data.error!==undefined){
            return false;
          } else {
            if(data.sku!==undefined){$('#sku').val(data.sku);}
            if(data.price!==undefined){$('#price').val(data.price.toFixed(2));}
            if(data.iva!==undefined){$('#iva').val(data.iva.toFixed(2));}
            if(data.ieps!==undefined){$('#ieps').val(data.ieps.toFixed(2));}
            if(data.unit!==undefined){$('#unit').val(data.unit).prop('selected',true);}
            return true;
          }
        });

        /* Si la consulta ha fallado.. */
        consulta.fail(function(){
          return false;
        });
      } else {
        return false;
      }
    }
  }
};

$("#product-name").easyAutocomplete(options);

//actualizar SKU, PRECIO Y UNIDAD
/* Ponemos evento blur a la escucha sobre id nombre en id cliente. */
$('#products').on('blur',function(){
  $('#sku').val('');
  $('#price').val('');
  $('#unit').val('');
  /* Obtenemos el valor del campo */
  var valor = this.value;
  /* Si la longitud del valor es mayor a 2 caracteres.. */
  if(valor.length>=3){
    /* Hacemos la consulta ajax */
    var consulta = $.ajax({
      type:'POST',
      url:'../getProduct',
      data:{nombre:valor},
      dataType:'JSON'
    });

    /* En caso de que se haya retornado bien.. */
    consulta.done(function(data){
      if(data.error!==undefined){
        return false;
      } else {
        if(data.sku!==undefined){$('#sku').val(data.sku);}
        if(data.price!==undefined){$('#price').val(data.price.toFixed(2));}
        if(data.iva!==undefined){$('#iva').val(data.iva.toFixed(2));}
        if(data.ieps!==undefined){$('#ieps').val(data.ieps.toFixed(2));}
        if(data.unit!==undefined){$('#unit').val(data.unit).prop('selected',true);}
        return true;
      }
    });

    /* Si la consulta ha fallado.. */
    consulta.fail(function(){
      return false;
    });
  } else {
    return false;
  }
});

