// TODO: Refactor
function complete() {
  var zipCode = $(this).val()

  var ajax = $.ajax({
    url: $('#sepomexUrl').val() + zipCode,
    dataType: 'json',
    crossDomain: true
  })

  ajax.done(function (response) {
    console.log(response)
    if(response.id != null) {
      var colonia    = $('#colony')
      var delegacion = $('#town')
      var ciudad     = $('#city')
      var estado     = $('#federalEntity')
      var pais       = $('#country')

      colonia.find('option').remove()
      delegacion.val('')
      ciudad.val('')
      estado.val('')
      pais.val('')

      $.each(response.dAsenta, function (k, v) {
        colonia.append('<option value="' + v + '">' + v + '</option>')
      })

      delegacion.val(response.dMnpio)
      ciudad.val(response.dCiudad)
      estado.val(response.dEstado)
      pais.val(response.country)
    }else{
      error()
    }
  })

  ajax.error(function (xhr, ajaxOptions, thrownError) {
    error()
  })
}

function error() {
  $('#street').val('')
  $('#streetNumber').val('')
  $('#suite').val('')
  $('#zipCode').val('')
  $('#colony').find('option').remove()
  $('#town').val('')
  $('#county').val('')
  $('#federalEntity').val('')
}

$(document).ready(function(){
  $('#zipCode').on('change',complete)
})

