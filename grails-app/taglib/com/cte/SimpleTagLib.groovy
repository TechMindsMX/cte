package com.cte

class SimpleTagLib {

  def pageTitle = { attrs, body ->
    out << "<h1>"
    out << "<i class='${message(code:attrs.icon)}'></i>"
    out << "${message(code:attrs.title)} | ${message(code:attrs.title2)}"
    out << "<small>${message(code:attrs.subtitle)}</small>"
    out << "</h1>"
  }

}
