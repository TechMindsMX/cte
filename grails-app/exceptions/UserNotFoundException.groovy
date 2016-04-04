package com.cte

class UserNotFoundException extends RuntimeException {

  UserNotFoundException(String msg){
    super(msg)
  }

  String getMessage(){
    super.message
  }

}
