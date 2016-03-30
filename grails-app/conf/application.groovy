grails.views.default.codec = "html"
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
grails.plugin.databasemigration.updateOnStart = true
grails.plugin.databasemigration.updateOnStartFileNames = ['changelog.groovy']

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.cte.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.cte.UserRole'
grails.plugin.springsecurity.authority.className = 'com.cte.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/':                ['permitAll'],
	'/error':           ['permitAll'],
	'/index':           ['permitAll'],
	'/index.gsp':       ['permitAll'],
	'/shutdown':        ['permitAll'],
	'/assets/**':       ['permitAll'],
	'/**/js/**':        ['permitAll'],
	'/**/css/**':       ['permitAll'],
	'/**/images/**':    ['permitAll'],
	'/**/favicon.ico':  ['permitAll']
]

