<!doctype html>
<html>
  <head>
    <meta name="layout" content="main">
  </head>
  <body>

    <div class="page-title">
      <h1>
        Administración de <companyInfo:companyInfo />
        <small>Operaciones de tu Compañia</small>
      </h1>
      <ol class="breadcrumb">
        <li>
          <i class="fa fa-dashboard"></i>  <a href="index.html">Tablero principal</a>
        </li>
      </ol>
    </div>

    <div class="row">
      <div class="col-lg-4 col-sm-12">
        <div class="circle-tile">
          <a href="#">
            <div class="circle-tile-heading orange">
              <i class="fa fa-files-o fa-fw fa-3x"></i>
            </div>
          </a>
          <div class="circle-tile-content orange">
            <div class="circle-tile-description text-faded">
              Órdenes de depósito
            </div>
            <div class="circle-tile-number text-faded">
              <p>${depositOrderCreatedCount} creadas</p>
              <p>${depositOrderValidateCount} por ejecutar</p>
              <p>${depositOrderAuthorizedCount} autorizadas</p>
            </div>
            <g:link controller="depositOrder" action="list" params="[status:'AUTHORIZED']" class="circle-tile-footer">
              Ver órdenes pendientes <i class="fa fa-chevron-circle-right"></i>
            </g:link>
            <g:link controller="depositOrder" action="list" class="circle-tile-footer">
              Ver todas <i class="fa fa-chevron-circle-right"></i>
            </g:link>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-sm-12">
        <div class="circle-tile">
          <a href="#">
            <div class="circle-tile-heading blue">
              <i class="fa fa-money fa-fw fa-3x"></i>
            </div>
          </a>
          <div class="circle-tile-content blue">
            <div class="circle-tile-description text-faded">
              Órdenes de retiro
            </div>
            <div class="circle-tile-number text-faded">
              <p>${cashOutOrderCreatedCount} creadas</p>
              <p>${cashOutOrderValidateCount} por ejecutar</p>
              <p>${cashOutOrderAuthorizedCount} autorizadas</p>
            </div>
            <g:link controller="cashOutOrder" params="[status:'AUTHORIZED']" action="list" class="circle-tile-footer">
              Ver órdenes autorizadas <i class="fa fa-chevron-circle-right"></i>
            </g:link>
            <g:link controller="cashOutOrder" action="list" class="circle-tile-footer">
              Ver todas <i class="fa fa-chevron-circle-right"></i>
            </g:link>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-sm-12">
        <div class="circle-tile">
          <a href="#">
            <div class="circle-tile-heading purple">
              <i class="fa fa-truck fa-fw fa-3x"></i>
            </div>
          </a>
          <div class="circle-tile-content purple">
            <div class="circle-tile-description text-faded">
              Órdenes de facturación y cobranza
            </div>
            <div class="circle-tile-number text-faded">
              <p>${saleOrderCreatedCount} creadas</p>
              <p>${saleOrderValidateCount} por ejecutar</p>
              <p>${saleOrderAuthorizedCount} autorizadas</p>
            </div>
            <g:link controller="saleOrder" params="[status:'AUTORIZADA']" action="list" class="circle-tile-footer">
              Ver órdenes autorizadas <i class="fa fa-chevron-circle-right"></i>
            </g:link>
            <g:link controller="saleOrder" action="list" class="circle-tile-footer">
              Ver todas <i class="fa fa-chevron-circle-right"></i>
            </g:link>
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-lg-4 col-sm-12">
        <div class="circle-tile">
          <a href="#">
            <div class="circle-tile-heading green">
              <i class="fa fa-users fa-fw fa-3x"></i>
            </div>
          </a>
          <div class="circle-tile-content green">
            <div class="circle-tile-description text-faded">
              Órdenes de pago a proveedores
            </div>
            <div class="circle-tile-number text-faded">
              ${purchaseOrderAuthorizedCount} por ejecutar
            </div>
            <g:link controller="purchaseOrder" action="list" params="[status:'AUTORIZADA']" class="circle-tile-footer">
              Ver órdenes autorizadas <i class="fa fa-chevron-circle-right"></i>
            </g:link>
            <g:link controller="purchaseOrder" action="list" class="circle-tile-footer">
              Ver todas <i class="fa fa-chevron-circle-right"></i>
            </g:link>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-sm-12">
        <div class="circle-tile">
          <a href="#">
            <div class="circle-tile-heading yellow">
              <i class="fa fa-suitcase fa-fw fa-3x"></i>
            </div>
          </a>
          <div class="circle-tile-content yellow">
            <div class="circle-tile-description text-faded">
              Órdenes de reembolso
            </div>
            <div class="circle-tile-number text-faded">
              ${moneyBackOrderAuthorizedCount} por ejecutar
            </div>
            <g:link controller="purchaseOrder" action="listMoneyBackOrders" params="[status:'AUTORIZADA']" class="circle-tile-footer">
              Ver órdenes autorizadas <i class="fa fa-chevron-circle-right"></i>
            </g:link>
            <g:link controller="purchaseOrder" action="listMoneyBackOrders" class="circle-tile-footer">
              Ver todas <i class="fa fa-chevron-circle-right"></i>
            </g:link>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-sm-12">
        <div class="circle-tile">
          <a href="#">
            <div class="circle-tile-heading blue">
              <i class="fa fa-file-text-o fa-fw fa-3x"></i>
            </div>
          </a>
          <div class="circle-tile-content blue">
            <div class="circle-tile-description text-faded">
              Recibos de Honorarios
            </div>
            <div class="circle-tile-number text-faded">
               ${feesReceiptCount} por ejecutar
            </div>
            <g:link controller="feesReceipt" action="list" params="[status:'AUTORIZADA']" class="circle-tile-footer">
              Ver órdenes pendientes <i class="fa fa-chevron-circle-right"></i>
            </g:link>
            <g:link controller="feesReceipt" action="list" class="circle-tile-footer">
              Ver todas <i class="fa fa-chevron-circle-right"></i>
            </g:link>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
