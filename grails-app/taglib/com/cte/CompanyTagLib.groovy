package com.cte

class CompanyTagLib {

  static namespace = "integradora"
  static defaultEncodeAs = [taglib:'text']
  //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

  def statusForCompany = { attrs, body ->
    def labelStatus = [
      (CompanyStatus.CREATED) : "label-primary",
      (CompanyStatus.VALIDATE): "label-warning",
      (CompanyStatus.ACCEPTED): "label-success",
      (CompanyStatus.REJECTED): "label-danger" ]
    out << """
      <span class="label ${labelStatus[attrs.status] ?: 'label-default'}">
        ${g.message(code:attrs.status.code)}
      </span>
    """
  }
}
