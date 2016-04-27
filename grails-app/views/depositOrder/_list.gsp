<div class="table-responsive">
  <table class="table">
   <tr>
     <th>Compañía</th>
     <th>Monto</th>
     <th>Fecha de Creación</th>
     <th>Estatus</th>
   </tr>
   <g:each in="${depositOrder.sort{it.dateCreated}}" var="dep">
   <tr class="${message(code: 'depositOrder.style.background.'+dep.status)}">
      <td>
        <g:if test="${params.status}">
          <g:link action="show" id="${dep.id}" params="[status:"${params.status}"]">${dep.company}</g:link></td>
        </g:if>
        <g:else>
          <g:link action="show" id="${dep.id}">${dep.company}</g:link></td>
        </g:else>
      <td>${integradora.formatPrice(number: dep.amount)}</td>
      <td><g:formatDate format="dd-MM-yyyy hh:mm" date="${dep.dateCreated}"/></td>
      <td>
        <g:message code="depositOrder.status.${dep.status}"/>
      </td>
    </tr>
   </g:each>
 </table>
 <nav>
  <ul class="pagination">
    <g:paginate class="pagination" controller="depositOrder" action="list" total="${depositOrderCount}" />
  </ul>
 </nav>
</div>

