function complete(){
  var clabe = this.value
  $("#banco option").each(function(){
    if(clabe.substring(0,3) == this.value.substring(this.value.length-3, this.value.length)){
      $("#banco").val(this.value)
      $("#bank").val(this.value)
      $("#branchNumber").val(clabe.substring(3,6))
      $("#accountNumber").val(clabe.substring(6,17))
    }
  })
}

$(document).ready(function(){
  $('#clabe').on('change', complete)
})

