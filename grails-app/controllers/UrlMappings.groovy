class UrlMappings {

  static mappings = {
    "/$controller/$action?/$id?(.$format)?"{
      constraints {
        // apply constraints here
      }
    }

  "/activation"(controller: "recovery") {
    action = [GET : "activeAccountOfLegalRepresentative"]
  }

  "/"(controller:"home")
  "500"(view:'/error')
  "404"(view:'/notFound')
  }
}
