grails.views.default.codec = "html"
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
grails.plugin.databasemigration.updateOnStart = true
grails.plugin.databasemigration.updateOnStartFileNames = ['changelog.groovy']

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.cte.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.cte.UserRole'
grails.plugin.springsecurity.authority.className = 'com.cte.Role'
grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/dashboard'
grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.interceptUrlMap = [
'/':                        ['permitAll'],
'/error':                   ['permitAll'],
'/index':                   ['permitAll'],
'/index.gsp':               ['permitAll'],
'/shutdown':                ['permitAll'],
'/assets/**':               ['permitAll'],
'/**/js/**':                ['permitAll'],
'/**/css/**':               ['permitAll'],
'/**/images/**':            ['permitAll'],
'/**/favicon.ico':          ['permitAll'],
'/login':                   ['permitAll'],
'/login/**':                ['permitAll'],
'/logout':                  ['IS_AUTHENTICATED_FULLY'],
'/logout/**':               ['IS_AUTHENTICATED_FULLY'],
'/user/**':                 ['permitAll'],
'/confirmation/**':         ['permitAll'],
'/recovery/**':             ['permitAll'],
'/dashboard/**':            ['IS_AUTHENTICATED_REMEMBERED'],
'/company/**':              ['IS_AUTHENTICATED_FULLY'],
'/address/**':              ['IS_AUTHENTICATED_FULLY'],
'/bankAccount/**':          ['IS_AUTHENTICATED_FULLY'],
'/uploadDocuments/**':      ['IS_AUTHENTICATED_FULLY'],
'/telephone/**':            ['IS_AUTHENTICATED_FULLY'],
'/legalRepresentative/**':  ['IS_AUTHENTICATED_FULLY'],
'/requestCompany/**':       ['IS_AUTHENTICATED_FULLY'],
'/mamangerApplication/**':  ['IS_AUTHENTICATED_FULLY'],
'/activation/**':           ['permitAll'],
'/documentTemplates/**':    ['permitAll'],
'/depositOrder/**':         ['IS_AUTHENTICATED_FULLY'],
'/cashOutOrder/**':         ['IS_AUTHENTICATED_FULLY'],
'/payroll/**':              ['IS_AUTHENTICATED_FULLY'],
'/**':                      ['IS_AUTHENTICATED_REMEMBERED']
]
