package com.cte

import grails.test.mixin.TestFor
import spock.lang.Specification
import grails.test.mixin.Mock

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(DepositOrderService)
@Mock([DepositOrder,User,Profile,Company])
class DepositOrderServiceSpec extends Specification {

  void "create a emailNotificationDepositOrder "() {
    given:
      Profile profile = new Profile(email:"sergio@makingdevs.com").save(validate:false)
    and:
      User user = new User(profile:profile).save(validate:false)
    and:
      Company company = new Company(name:"makingdevs").save(validate:false)
    and:
      DepositOrder order = new DepositOrder()
      order.amount = 123
      order.company = company
      order.dateCreated = new Date()
      order.save()
    when:
      def notification = service.createEmailCommand(order,user,company,"url")
    then:
      notification.email == "sergio@makingdevs.com"
  }

}
