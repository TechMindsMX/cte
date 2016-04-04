<!DOCTYPE html>
<%! import com.cte.BusinessEntityType %>
<%! import com.cte.NameType %>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'businessEntity.label', default: 'BusinessEntity')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
  </head>
  <body>
    <div class="page-title">
       <h1><g:message code="businessEntity.view.show.label" /></h1>
        <ol class="breadcrumb">
          <li><i class="fa fa-caret-square-o-up"></i> Compañia</li>
          <li class="active"><g:message code="businessEntity.view.show.label" /></li>
        </ol>
      </div>
      <div id="edit-address" class="content scaffold-edit" role="main">
        <div class="portlet portlet-blue">
          <div class="portlet-heading">
            <div class="portlet-title">
              <br />
              <br />
            </div>
            <div class="clearfix"></div>
          </div>
          <div id="horizontalFormExample" class="panel-collapse collapse in">
            <div class="portlet-body">
          <g:if test="${flash.message}">
            <div class="alert alert-success" role="alert">${flash.message} - ${relation}</div>
          </g:if>
          <ol class="property-list businessEntity">
            <f:display bean="businessEntity" property="rfc" wrapper="show"/>
            <g:if test="${!relation.equals('EMPLEADO')}">
            <f:display bean="businessEntity" property="website" wrapper="show"/>
            </g:if>

            <g:if test="${businessEntity.type == BusinessEntityType.FISICA}">
            <g:each var="name" in="${businessEntity.names}">
            <g:if test="${name.type == NameType.NOMBRE}">
            <li class="fieldcontain">
              <span id="name-label" class="property-label">Nombre</span>
              <div class="property-value" aria-labelledby="name-label">
                ${name.value}
              </div>
            </li>
            </g:if>
            </g:each>
            <g:each var="name" in="${businessEntity.names}">
            <g:if test="${name.type == NameType.APELLIDO_PATERNO}">
            <li class="fieldcontain">
              <span id="lastName-label" class="property-label">Apellido Paterno</span>
              <div class="property-value" aria-labelledby="lastName-label">
                ${name.value}
              </div>
            </li>
            </g:if>
            </g:each>
            <g:each var="name" in="${businessEntity.names}">
            <g:if test="${name.type == NameType.APELLIDO_MATERNO}">
            <li class="fieldcontain">
              <span id="lastName-label" class="property-label">Apellido Materno</span>
              <div class="property-value" aria-labelledby="lastName-label">
                ${name.value}
              </div>
            </li>
            </g:if>
            </g:each>
            </g:if>
            <g:set var="employee" value="${businessEntity.names.find{ val -> val['type'] == NameType.NUMERO_EMPLEADO}}" />
            <g:if test="${employee}">
              <li class="fieldcontain">
                <span id="lastName-label" class="property-label">Numero de Empleado</span>
                <div class="property-value" aria-labelledby="lastName-label">
                  ${employee.value}
                </div>
              </li>
            </g:if>
            <g:set var="clabe" value="${businessEntity.names.find{ val -> val['type'] == NameType.CLABE}}" />
            <g:if test="${clabe}">
              <li class="fieldcontain">
                <span id="lastName-label" class="property-label">Cuenta Clabe</span>
                <div class="property-value" aria-labelledby="lastName-label">
                  ${clabe.value}
                </div>
              </li>
            </g:if>
            <g:set var="cuenta" value="${businessEntity.names.find{ val -> val['type'] == NameType.CUENTA}}" />
            <g:if test="${cuenta}">
              <li class="fieldcontain">
                <span id="lastName-label" class="property-label">Cuenta</span>
                <div class="property-value" aria-labelledby="lastName-label">
                  ${cuenta.value}
                </div>
              </li>
            </g:if>
            <g:if test="${banco}">
              <li class="fieldcontain">
                <span id="lastName-label" class="property-label">Banco</span>
                <div class="property-value" aria-labelledby="lastName-label">
                  ${banco.name}
                </div>
              </li>
            </g:if>
            <g:if test="${businessEntity.type == BusinessEntityType.MORAL}">
              <g:each var="name" in="${businessEntity.names}">
              <g:if test="${name.type == NameType.RAZON_SOCIAL}">
              <li class="fieldcontain">
                <span id="name-label" class="property-label">Razón Social</span>
                <div class="property-value" aria-labelledby="name-label">
                  ${name.value}
                </div>
              </li>
              </g:if>
              </g:each>
            </g:if>
          </ol>
          <g:if test="${businessEntity.addresses}" >
            <span id="address-label" class="property-label">Dirección</span>
            <div class="property-value" aria-labelledby="telefono-label">
              <ul>
                <g:each var="address" in="${businessEntity.addresses}">
                  <li class="subList">${address.street} #${address.streetNumber} - ${address.suite} CP ${address.zipCode}, ${address.colony}, ${address.city}, ${address.town}. ${address.federalEntity}, ${address.country}</li>
                </g:each>
              </ul>
            </div>
          </g:if>
          <g:else>
            <g:if test="${!relation.equals('EMPLEADO')}">
            <div class="property-value" aria-labelledby="company-label">
              <g:link action="create" controller="address" params="[businessEntity:businessEntity.id,sepomexUrl:"${params.sepomexUrl}"]" class="btn btn-default">Agregar Dirección</g:link>
            </div>
            </g:if>
          </g:else>
          <br />
          <div class="property-value">
            <span class="property-label"><g:message code="businessEntity.label.bankAccounts.${relation}" default="Cuentas Bancarias"/></span>
            <g:if test="${!businessEntity.banksAccounts}">
            <p><h4><label class="label label-warning"><g:message code="businessEntity.label.noBankAccounts.${relation}" default="No hay cuentas bancarias registradas"/></label></h4></p>
            </g:if>
            <ul>
              <g:each in="${businessEntity.banksAccounts.sort{it.banco.name}}" var="account">
                <li class="sublist">
                  <g:link controller="bankAccount" action="edit" params="[businessEntity:businessEntity.id, businessEntityInfo:businessEntity.toString(), relation:relation]" id="${account.id}">
                    ${account.banco} -
                    ${account.accountNumber}
                    <g:if test="${relation != 'CLIENTE'}">- CLABE: ${account.clabe}</g:if>
                  </g:link>
                </li>
              </g:each>
            </ul>
            <div class="property-value">
              <fieldset class="buttons">
                <g:link class="btn btn-default" action="create" controller="bankAccount" params="[businessEntity:businessEntity.id, businessEntityInfo:businessEntity.toString(), relation:relation]"><g:message code="businessEntity.label.createBankAccount.${relation}" default="Agregar Cuenta"/></g:link>
                <g:if test="${employeeLib.isEmployee(be:businessEntity) == '1'}" >
                  <g:link class="btn btn-default" action="create" controller="feesReceipt" params="[businessEntity:businessEntity.id]"><g:message code="feesReceipt.create.label" /></g:link>
                   <g:link class="list btn btn-default" action="index" controller="feesReceipt" params='[businessEntity:businessEntity.id]'><g:message code="feesReceipt.list.label" args="[entityName]" /></g:link>
                </g:if>
              </fieldset>
            </div>
        </div>
        <br/>
        <g:form resource="${this.businessEntity}" method="DELETE">
          <fieldset class="buttons">
            <g:link class="edit btn btn-default" action="edit" resource="${this.businessEntity}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
          </fieldset>
        </g:form>
        <br />
    <p>
    </p>
  </body>
</html>
