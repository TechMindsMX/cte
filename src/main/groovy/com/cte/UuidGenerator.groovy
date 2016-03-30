package com.cte

class UuidGenerator {
  static def generateUuid() {
    UUID.randomUUID().toString().replaceAll('-', '');
  }
}
