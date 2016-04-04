package com.cte

class ChangePasswordCommand {
  String password
  String confirmPassword

  static constraints = {
    password(blank:false, size:10..50, matches:/^(?=.*\d)(?=.*[=_\-¿?¡!@#\$%^&*]+)?(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/,validator:{val, obj ->
      if(!val.equalsIgnoreCase(obj.confirmPassword)) {
        return false
      }
    })
  }
}
