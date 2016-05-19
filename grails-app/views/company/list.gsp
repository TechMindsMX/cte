<%! import com.cte.CompanyStatus %>
<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'company.label', default: 'Company')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
      <g:pageTitle icon="company.icon.title.list" title="admin.label.title" title2="company.list.label" subtitle="company.view.label.list"/>
    </div>
    <div align="center">
      <p class="text-primary">Selecciona una de las empresar para poder operar</p>
      <br />
    </div>
    <div id="list-company" role="main">
      <g:if test="${flash.message}">
      <div class="message alert alert-info" align="center" role="status">${flash.message}</div>
      </g:if>
      <div>
        <table class="table">
          <thead>
            <th><g:message code="company.rfc" /></th>
            <th><g:message code="company.bussinessName" /></th>
            <th><g:message code="company.grossAnnualBilling" /></th>
            <th><g:message code="company.status" /></th>
          </thead>
          <tbody>
            <g:each var="company" in="${companyList}">
            <tr>
              <td>
                ${company.rfc}
              </td>
              <td><g:link action="show" id="${company.id}">${company.bussinessName}</g:link></td>
              <td><integradora:formatPrice number="${company.grossAnnualBilling}"/></td>
              <td><integradora:statusForCompany status="${company.status}"/></td>
            </tr>
            </g:each>
          </tbody>
        </table>
      </div>

    </div>
  </body>
</html>
