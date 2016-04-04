// Place your Spring DSL code here

import groovyx.net.http.RESTClient

beans = {
    localeResolver(org.springframework.web.servlet.i18n.SessionLocaleResolver) {
          defaultLocale = new Locale("es","ES_MX")
              java.util.Locale.setDefault(defaultLocale)
                }

      restClientBean(RESTClient) {
            // uri = grailsApplication.config.modulus.url
          }

}

