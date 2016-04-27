<%! import com.cte.CompanyStatus %>
<%! import com.cte.CompanyTaxRegime %>
<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Modulus UNO</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>

    <g:layoutHead/>
  </head>
  <body>
    <nav class="navbar-top" role="navigation">
    <!-- begin BRAND HEADING -->

      <div class="navbar-header">
        <br /><font color="white" size="4">MODULUS UNO</font>
        <button type="button" class="navbar-toggle pull-right" data-toggle="collapse" data-target=".sidebar-collapse">
          <i class="fa fa-bars"></i> Menu
        </button>
        <div class="navbar-brand">
        </div>
      </div>

      <div class="nav-top">
        <ul class="nav navbar-left">
          <li class="tooltip-sidebar-toggle">
            <a href="#" id="sidebar-toggle" data-toggle="tooltip" data-placement="right" title="Mostrar/ocultar menú">
              <i class="fa fa-bars"></i>
            </a>
          </li>
          <!-- You may add more widgets here using <li> -->
        </ul>
        <ul class="nav navbar-brand">
          <li class="tooltip-sidebar-toggle" align="right">
            <sec:ifAnyGranted roles="ROLE_INTEGRADO,ROLE_LEGAL_REPRESENTATIVE,ROLE_INTEGRADO_AUTORIZADOR,ROLE_INTEGRADO_OPERADOR">
            <g:if test="${session.company}">
            <g:form class="form-group" id="company-selection" url="[action:'setCompanyInSession',controller:'company']" >
            <font color="white">Selecciona tu Compañia </font>${companyInfo.selectedCompany()}
            <input type="submit" class="btn btn-primary btn-xs" />
            </g:form>
            </g:if>
            </sec:ifAnyGranted>
          </li>
        </ul>
        <ul class="nav navbar-right">
          <!-- begin USER ACTIONS DROPDOWN -->
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-user"></i>  <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
              <li>
                <g:link controller="user" action="profile" id="${sec.loggedInUserInfo(field: "id")}">
                <i class="fa fa-user"></i> Mi Perfil
                </g:link>
              </li>
              <li class="divider"></li>
              <li>
                <g:link controller="logout" action="index" class="logout_open">
                <i class="fa fa-sign-out"></i> Cerrar sesión
                </g:link>
              </li>
            </ul>
            <!-- /.dropdown-menu -->
          </li>
          <!-- /.dropdown -->
          <!-- end USER ACTIONS DROPDOWN -->
        </ul>

      </div> <!--ENDOF NAV-TOP -->

    </nav>

    <nav class="navbar-side" role="navigation">
      <div class="navbar-collapse sidebar-collapse collapse">
        <ul id="side" class="nav navbar-nav side-nav">
          <li>
            <g:link controller="dashboard" action="iecce" class="active">
            <i class="fa fa-dashboard"></i> Tablero principal
            </g:link>
          </li>
          <sec:ifAnyGranted roles="ROLE_INTEGRADO,ROLE_LEGAL_REPRESENTATIVE">
            <li><g:link controller="company" action="create" ><i class="fa fa-angle-double-right"></i> Crear Nueva Empresa</g:link></li>
            <g:if test="${session.company}">
                <li class="panel">
                  <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#administracion">
                    Administración<i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="collapse nav" id="administracion"><!---->
                    <li>
                      <g:link controller="company" action="show" id="${session.company}">Mi Empresa</g:link>
                    </li>
                    <li><g:link controller="user" action="authorizer">Alta Usuario</g:link></li>
                    <li>
                      <g:link controller="user" action="edit" id="${sec.loggedInUserInfo(field: "id")}">
                        Editar Usuario
                      </g:link>
                    </li>
                    <li><g:link controller="user" action="manageusers">Lista de Usuarios</g:link></li>
                  </ul>
                </li> <!--ENDOF LI.PANEL -->
            <g:if test="${companyInfo.isAvailableForOperationInThisCompany() == "true"}">
            <li class="panel">
              <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#registros">
                Registros <i class="fa fa-caret-down"></i>
              </a>
              <ul class="collapse nav" id="registros">
                <!--li>
                  <g:link action="create" controller="businessEntity" params='[type:"${com.cte.LeadType.CLIENTE}"]' ><i class="fa fa-angle-double-right"> </i>Alta Cliente</g:link>
                </li>
                <li>
                  <g:link action="create" controller="businessEntity"  params='[type:"${com.cte.LeadType.PROVEEDOR}"]'><i class="fa fa-angle-double-right"> </i>Alta Proveedor</g:link>
                </li-->
                <li>
                  <g:link action="create" controller="businessEntity"  params='[type:"${com.cte.LeadType.EMPLEADO}"]'><i class="fa fa-angle-double-right"> </i>Alta Empleado</g:link>
                </li>
                <li>
                  <g:link action="createMultiEmployees" controller="businessEntity" ><i class="fa fa-angle-double-right"> </i>Alta Multiples Empleados</g:link>
                </li>
                <!--li>
                  <g:link action="create" controller="product"><i class="fa fa-angle-double-right"> </i>Alta Producto/Servicio</g:link>
                </li-->
                <li>
                  <g:link controller="businessEntity" action="index"><i class="fa fa-angle-double-right"> </i>Lista de Relaciones Comerciales</g:link>
                </li>
                <!--li>
                  <g:link controller="product" action="index"><i class="fa fa-angle-double-right"> </i>Mis Productos/Servicios</g:link>
                </li-->
              </ul>
            </li> <!--ENDOF LI.PANEL -->
            </g:if>
            </g:if>
          </sec:ifAnyGranted>

          <sec:ifAnyGranted roles="ROLE_INTEGRADO,ROLE_LEGAL_REPRESENTATIVE, ROLE_INTEGRADO_OPERADOR">
            <g:if test="${session.company}">
              <g:if test="${companyInfo.isAvailableForOperationInThisCompany() == "true"}">
                <li class="panel">
                  <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#operaciones">
                    Operaciones <i class="fa fa-caret-down"></i>
                  </a>
                  <ul class="collapse nav" id="operaciones">
                    <!--li>
                      <g:link controller="company" action="accountstatement" ><i class="fa fa-angle-double-right"></i> <g:message code="Estado de Cuenta" default="Estado de Cuenta" /></g:link>
                    </li-->
                    <li class="panel">
                      <a href="javascript:;" data-parent="#operaciones" data-toggle="collapse" class="accordion-toggle" data-target="#ordenesDeposito">
                        Depósito <i class="fa fa-caret-down"></i>
                      </a>
                      <ul class="collapse nav" id="ordenesDeposito">
                        <li>
                          <g:link controller="depositOrder" action="create"><i class="fa fa-angle-double-right"> </i>Nueva</g:link>
                        </li>
                        <li>
                          <g:link controller="depositOrder" action="list"><i class="fa fa-angle-double-right"></i> Listado</g:link>
                        </li>
                      </ul>
                    </li>
                    <li>
                    <!--li class="panel">
                      <a href="javascript:;" data-parent="#operaciones" data-toggle="collapse" class="accordion-toggle" data-target="#cashoutOrder">
                        Retiro<i class="fa fa-caret-down"></i>
                      </a>
                      <ul class="collapse nav" id="cashoutOrder">
                        <li>
                          <g:link controller="cashOutOrder" action="create"><i class="fa fa-angle-double-right"> </i>Nueva</g:link>
                        </li>
                        <li>
                          <g:link controller="cashOutOrder" action="list"><i class="fa fa-angle-double-right"></i>Listado</g:link>
                        </li>
                      </ul>
                    </li>
                    <li class="panel">
                      <a href="javascript:;" data-parent="#operaciones" data-toggle="collapse" class="accordion-toggle" data-target="#saleOrder">
                        Facturación y Cobranza<i class="fa fa-caret-down"></i>
                      </a>
                      <ul class="collapse nav" id="saleOrder">
                        <li>
                          <g:link controller="saleOrder" action="create"><i class="fa fa-angle-double-right"> </i>Nueva</g:link>
                        </li>
                        <li>
                          <g:link controller="saleOrder" action="list"><i class="fa fa-angle-double-right"></i>Listado</g:link>
                        </li>
                        <li>
                          <g:link controller="payment" action="reconcile"><i class="fa fa-angle-double-right"></i>Conciliaciones</g:link>
                        </li>
                      </ul>
                    </li-->
                    <li class="panel">
                      <a href="javascript:;" data-parent="#operaciones" data-toggle="collapse" class="accordion-toggle" data-target="#payroll">
                        Disperción de Fondos<i class="fa fa-caret-down"></i>
                      </a>
                      <ul class="collapse nav" id="payroll">
                        <li>
                          <g:link controller="payroll" action="create"><i class="fa fa-angle-double-right"> </i>Nueva</g:link>
                        </li>
                        <li>
                          <g:link controller="payroll" action="index"><i class="fa fa-angle-double-right"></i>Listado</g:link>
                        </li>

                      </ul>
                    </li>

                    <!--li class="panel">
                      <a href="javascript:;" data-parent="#operaciones" data-toggle="collapse" class="accordion-toggle" data-target="#ordenesCompra">
                        Pago a Proveedores <i class="fa fa-caret-down"></i>
                      </a>
                      <ul class="collapse nav" id="ordenesCompra">
                        <li>
                          <g:link controller="purchaseOrder" action="create"><i class="fa fa-angle-double-right"> </i>Nueva</g:link>
                        </li>
                        <li>
                          <g:link controller="purchaseOrder" action="list"><i class="fa fa-angle-double-right"></i> Listado</g:link>
                        </li>
                      </ul>
                    </li>
                    <li class="panel">
                      <a href="javascript:;" data-parent="#operaciones" data-toggle="collapse" class="accordion-toggle" data-target="#ordenesReembolso">
                        Reembolso <i class="fa fa-caret-down"></i>
                      </a>
                      <ul class="collapse nav" id="ordenesReembolso">
                        <li>
                          <g:link controller="purchaseOrder" action="createMoneyBackOrder"><i class="fa fa-angle-double-right"> </i>Nueva</g:link>
                        </li>
                        <li>
                          <g:link controller="purchaseOrder" action="listMoneyBackOrders"><i class="fa fa-angle-double-right"></i> Listado</g:link>
                        </li>
                      </ul>
                    </li>
                    <li class="panel">
                      <a href="javascript:;" data-parent="#consultas" data-toggle="collapse" class="accordion-toggle" data-target="#feesReceipt">
                        Recibo de Honorarios<i class="fa fa-caret-down"></i>
                      </a>
                      <ul class="collapse nav" id="feesReceipt">
                        <li>
                          <g:link controller="feesReceipt" action="list">
                          <i class="fa fa-angle-double-right"></i>Todas</a>
                          </g:link>
                        </li>
                        <li>
                          <g:link controller="feesReceipt" action="list" params="[status:'CREADA']">
                          <i class="fa fa-angle-double-right"></i> Creadas
                          </g:link>
                        </li>
                      </ul>
                    </li-->
                  </ul>
                <li>
              </g:if>
            </g:if>
          </sec:ifAnyGranted>



          <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
          <li>
            <a class="" href="${createLink(controller:'managerApplication')}">
              <i class="fa fa-book"></i> Consultas CTE
            </a>
          </li>
          </sec:ifAnyGranted>

          <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_ADMIN_IECCE">
          <li class="panel">
            <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#reportes">
              Operaciones<i class="fa fa-caret-down"></i>
            </a>
            <ul class="collapse nav" id="reportes">
              <li>
                <g:link action="processorPayroll" controller="payroll" ><i class="fa fa-angle-double-right"> </i>Listado de Archivos procesados</g:link>
              </li>
              <li>
                <g:link action="list" controller="managerApplication"><i class="fa fa-angle-double-right"></i>Listado de Empresas</g:link>
              </li>
            </ul>
          </li> <!--ENDOF LI.PANEL -->
          </sec:ifAnyGranted>

          <sec:ifAnyGranted roles="ROLE_INTEGRADO_AUTORIZADOR">
            <li class="panel">
              <a href="javascript:;" data-parent="#side" data-toggle="collapse" class="accordion-toggle" data-target="#autorizaciones">
                Autorizaciones <i class="fa fa-caret-down"></i>
              </a>
              <ul class="collapse nav" id="autorizaciones">
                <li><g:link controller="purchaseOrder" action="list" params="[status:'POR_AUTORIZAR']" ><i class="fa fa-angle-double-right"> </i>Órdenes de Pago a Proveedores</g:link></li>
                <li><g:link controller="saleOrder" action="showSaleOrdersByAuthorize" ><i class="fa fa-angle-double-right"> </i>Órdenes de Facturación y Cobranza</g:link></li>
                <li><g:link controller="cashOutOrder" action="index" ><i class="fa fa-angle-double-right"> </i>Órdenes de Retiro</g:link></li>
                <li><g:link controller="depositOrder" action="list" params="[status:'VALIDATE']" ><i class="fa fa-angle-double-right"> </i>Órdenes de Depósito</g:link></li>
                <li><g:link controller="loanOrder" action="index" ><i class="fa fa-angle-double-right"> </i>Órdenes de Préstamo</g:link></li>
                <li><g:link controller="purchaseOrder" action="listMoneyBackOrders" params="[status:'POR_AUTORIZAR']" ><i class="fa fa-angle-double-right"> </i>Órdenes de Reembolso</g:link></li>
                <li><g:link controller="feesReceipt" action="list" params="[status:'CREADA']" ><i class="fa fa-angle-double-right"> </i>Recibos de Honorarios</g:link></li>
              </ul>
            </li> <!--ENDOF LI.PANEL -->
          </sec:ifAnyGranted>
          <li><g:link controller="logout" action="index"><i class="fa fa-sign-out"></i> Cerrar sesión</g:link></li>
        </ul>
      </div>
    </nav> <!--ENDOF NAVBAR-SIDE -->

    <div id="page-wrapper">
      <div class="page-content page-content-ease-in">
        <div class="row">
          <div class="col-lg-12">
            <g:layoutBody/>

          </div>
        </div>
      </div>
    </div><!-- ENDOF PAGE-WRAPPER -->


    <div class="footer" role="contentinfo"></div>
    <div id="spinner" class="spinner" style="display:none;">
      <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

  </body>
</html>
