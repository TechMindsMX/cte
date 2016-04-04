package com.cte

import spock.lang.Specification
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(UserService)
@Mock([UserRole])
class UserServiceSpec extends Specification {

  def service = new UserService()
  def recoveryService = Mock(RecoveryService)

  def setup(){
    service.recoveryService = recoveryService
  }

  void "should create an user"() {
  given: "An user and email"
    def user = Mock(User)
    def email = 'josdem@email.com'
  and: "A role"
    def role = Mock(Role)
  and: "A profile"
    def profile = Mock(Profile)
    profile.email >> email
    user.profile >> profile
    Profile.metaClass.static.findByEmail = { null }
  when: "We assing values to command"
    service.save(user, role)
  then:"We validate command"
    user.profile >> profile
    1 * user.save()
    1 * recoveryService.sendConfirmationAccountToken(email)
  }
}
