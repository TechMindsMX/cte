<!DOCTYPE html>
<html>
  <head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'bankAccount.label', default: 'BankAccount')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
    <asset:javascript src="clabe.js" />
  </head>
  <body>
    <div class="page-title">
      <h1>
        <g:if test="${params.companyBankAccount}">
          <g:message code="bankAccount.new" args='["${companyInfo.companyInfo()}"]' />
        </g:if>
        <g:else>
          <g:message code="bankAccount.new.${relation}" args='["${params.businessEntityInfo}"]' default="Agregar Cuenta Bancaria para ${params.businessEntityInfo}" />
        </g:else>
      </h1>
      <ol class="breadcrumb">
        <li>
        <g:if test="${params.companyBankAccount}">
          <i class="fa fa-caret-square-o-up"></i><g:link controller="company" action="show" id="${session.company}"> Compañía</g:link>
        </g:if>
        <g:else>
          <i class="fa fa-caret-square-o-up"></i><g:link controller="businessEntity" action="show" id="${params.businessEntity}"> Relación Comercial </g:link>
        </g:else>
        </li>
        <li class="active"><g:message code="businessEntity.label.bankAccounts.${relation}" default="Cuentas Bancarias"/></li>
      </ol>
    </div>
    <div id="create-bankAccount" class="content scaffold-create" role="main">
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
              <div class="alert alert-danger" role="alert">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.bankAccount}">
              <ul class="alert alert-danger alert-dismissable" role="alert">
                <g:eachError bean="${this.bankAccount}" var="error">
                  <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
              </ul>
            </g:hasErrors>
            <g:form action="save" class="form-horizontal" role="form">
                <input type="hidden" name="businessEntityInfo" value="${params.businessEntityInfo}"/>

              <g:if test="${relation == 'CLIENTE'}">
                <input type="hidden" name="clabe" value="002115016003269411"/>
                <input type="hidden" name="branchNumber" value="115"/>
                <input type="hidden" name="relation" value="${relation}"/>
                <input type="hidden" name="businessEntity" value="${params.businessEntity}"/>

                <div class="form-group">
                  <label class="col-sm-5 control-label">Últimos 4 dígitos del número de cuenta</label>
                  <div class="col-sm-4">
                    <g:field class="form-control" name="accountNumber" value="${bankAccount?.accountNumber?.replace('*','')}" maxLength="4" pattern="[0-9]{4}" title="Ingrese 4 dígitos exactamente" required=""/>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-5 control-label">Banco</label>
                  <div class="col-sm-4">
                    <g:select name="bank" from="${banks}" optionValue="name" optionKey="bankingCode" class="form-control" aria-controls="example-table" required="" noSelection="['':'']" value="${bankAccount?.banco?.bankingCode}"/>
                  </div>
                </div>

              </g:if><g:else>

              <f:display id="clabe" bean="bankAccount" property="clabe" label="${message(code:"bankAccount.clabe")}" wrapper="edit" />
              <f:display id="branchNumber" bean="bankAccount" property="branchNumber" label="${message(code:"bankAccount.branchNumber")}" wrapper="edit" />
              <f:display id="accountNumber" bean="bankAccount" property="accountNumber" label="${message(code:"bankAccount.accountNumber")}" wrapper="editReadOnly" />
              <input id="bank" name="bank" type="hidden"/>
              <div class="form-group">
                <label class="col-sm-5 control-label">Banco</label>
                <div class="col-sm-4">
                  <g:select name="banco" from="${banks}" optionValue="name" optionKey="bankingCode" class="form-control" aria-controls="example-table" readonly="true" noSelection="['':'']"/>
                </div>
              </div>
              <g:if test="${params.companyBankAccount}">
                <input type="hidden" name="company" value="${session.company}" />
                <input type="hidden" name="companyBankAccount" value="${params.companyBankAccount}" />
              </g:if>
              <g:else>
                <input type="hidden" name="businessEntity" value="${params.businessEntity}" />
              </g:else>

              </g:else>

              <div class="form-group">
                <div class="col-sm-offset-4 col-sm-10">
                  <g:submitButton name="create" class="save btn btn-default btn-lg" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </div>
              </div>

            </g:form>
            <g:if test="${params.companyBankAccount}">
              <g:link controller="company" action="show" id="${session.company}" class="btn btn-default">
                <g:message code="company.show.return"/>
              </g:link>
            </g:if>
            <g:else>
              <g:link controller="businessEntity" action="show" id="${params.businessEntity}" params="[company:session.company]" class="btn btn-default">Regresar</g:link>
            </g:else>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
