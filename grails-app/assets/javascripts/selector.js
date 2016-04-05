$( "select" ).change(function () {
  var actions = {
    fisica: function(){
      $(".fisica").show()
      $(".moral").hide()
    },
    moral: function(){
      $(".moral").show()
      $(".fisica").hide()
    }
  }
  var str = $("#type").val().toLowerCase()
  actions[str]()
  $("#persona").val(str)
}).change()

//$("input[name=clientProviderType]").click(
$(document).ready(function() {
      if ($("input[name=clientProviderType]").val()=='EMPLEADO'){
        $("#person").hide()
        $("#website").hide()
        $(".fisica").show()
        $(".moral").hide()
        $("select[name=type]").val('FISICA')
      } else {
        $("#person").show()
        $("#website").show()
        if ($("select[name=type]").val()=='MORAL'){
          $(".moral").show()
          $(".fisica").hide()
        }
      }
    }
)

$(function(){
  if($("input[name=clientProviderType]:checked").val() == 'EMPLEADO'){
    $("#person").hide()
    $("#website").hide()
    $(".fisica").show()
    $(".moral").hide()
  }
})

