class GrailsApplicationMock  {
  def template = [register:'register',newUser:'newUser',forgot:'forgot',clientProvider:'clientProvider',authorizeSaleOrder:'authorizeSaleOrder']
  def modulus = [users:'users',cashin: 'cashin']
  def page = [register:'register',forgot:'forgot']
  def last = [url:'url']
  def path = [server:last]
  def config = [emailer:template, recovery:page, modulus:modulus, grails:path]
}
