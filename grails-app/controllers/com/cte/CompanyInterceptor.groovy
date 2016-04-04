package com.cte

import grails.gsp.PageRenderer

class CompanyInterceptor {

    int order = HIGHEST_PRECEDENCE;

    def springSecurityService
    def companyService
    PageRenderer groovyPageRenderer

    CompanyInterceptor() {
      log.info "************** Contructor Method of interceptor ******************"
      matchAll()
           .excludes(controller:"login")
           .excludes(controller:"logout")
           .excludes(controller:'company')
      match(controller:"company",action:"show")
      match(controller:"company",action:"index")

    }

    boolean before() {
      log.info "Interceptor method for a company Id in session"
      def currentUser = springSecurityService.currentUser
      // Refactor: Por qué está sepomex en la sesión?
      session["sepomexUrl"] = grails.util.Holders.grailsApplication.config.sepomex.url
      if (!currentUser)
        return true
      if (currentUser.authorities.any { it.authority == "ROLE_ADMIN"})
        return true
      if (session.company)
        return true
      def companies = companyService.findCompaniesForThisUser(currentUser)
      switch (companies.size()) {
        case 0:
          return true
          break
        case 1:
          session["company"] = companies.first().id
          return true
          break
        default:
          render view: '/company/companiesSelect', model: [companies:companies]
          break
      }

      return true
    }

    boolean after() {
      return true
    }

    void afterView() {
        // no-op
    }
}
