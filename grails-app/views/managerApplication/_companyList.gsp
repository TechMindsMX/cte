<%! import com.cte.CompanyStatus %>
<div>
  <table class="table">
    <thead>
      <th><g:message code="company.rfc" /></th>
      <th><g:message code="company.bussinessName" /></th>
      <th><g:message code="company.grossAnnualBilling" /></th>
      <th><g:message code="company.status" /></th>
      <sec:ifAnyGranted roles="ROLE_ADMIN">
      <th><g:message code="manager.actions" /></th>
      </sec:ifAnyGranted>
    </thead>
    <tbody>
      <g:each var="company" in="${companies}">
      <tr>
        <td>
          ${company.rfc}
        </td>
        <td>${company.bussinessName}</td>
        <td><integradora:formatPrice number="${company.grossAnnualBilling}"/></td>
        <td><integradora:statusForCompany status="${company.status}"/></td>
        <sec:ifAnyGranted roles="ROLE_ADMIN">
        <td>
          <a href='${createLink(controller:"managerApplication",action:"show",id:"${company.id}")}' class="btn btn-default">
            <i class="fa fa-check-square-o"></i>
          </a>
          <!-- if acepted -->
          <g:if test="${company.status == CompanyStatus.ACCEPTED}">
          <a href='${createLink(controller:"managerApplication",action:"genereDocumentOfAccount",id:"${company.id}")}' class="btn btn-default"><i class="fa fa-file-pdf-o"></i></a>
          </g:if>
          <!-- rejected -->
          <g:if test="${company.status == CompanyStatus.REJECTED}">
            <a href='${createLink(controller:"company",action:"show",id:"${company.id}")}' class="btn btn-default"><i class="fa fa-check-square-o"></i></a>
          </g:if>
        </td>
        </sec:ifAnyGranted>
      </tr>
      </g:each>
    </tbody>
  </table>
</div>
