<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="dominio.Cuota"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="dominio.Prestamo"%>
<%@page import="dominio.Cuenta"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@page import="java.math.BigDecimal" %>

<%@ include file="Header.jsp" %>

<%

	if (usuarioLogueado.getId() == 2) {
		response.sendRedirect("SinPermisos.jsp");
	}	

	int cuotaPagada = 0;
	int cuotaPendiente = 0;
	int cuotaTodas = 0; 

	BigDecimal sumaPagas = BigDecimal.ZERO;
	BigDecimal sumaPendientes = BigDecimal.ZERO;
	BigDecimal sumaTodas = BigDecimal.ZERO;

	if (request.getAttribute("sumaPagas") != null) {
		sumaPagas = (BigDecimal) request.getAttribute("sumaPagas");
	}

	if (request.getAttribute("sumaPendientes") != null) {
		sumaPendientes = (BigDecimal) request.getAttribute("sumaPendientes");
	}

	if (request.getAttribute("sumaTodas") != null) {
		sumaTodas = (BigDecimal) request.getAttribute("sumaTodas");
	}

	if (request.getAttribute("cuotaPagada") != null) {
		cuotaPagada = (int)request.getAttribute("cuotaPagada");
	}
	if (request.getAttribute("cuotaPendiente") != null) {
		cuotaPendiente = (int)request.getAttribute("cuotaPendiente");
	}

	if (request.getAttribute("cuotaTodas") != null) {
		cuotaTodas = (int)request.getAttribute("cuotaTodas");
	}


		int idPrestamo = 0;
	
		if (request.getAttribute("idPrestamo") != null) {
			idPrestamo = (Integer)request.getAttribute("idPrestamo");
		}
	
		ArrayList<Cuota> listaCuotas = null;
		if(request.getAttribute("listaCuotas") != null){
			listaCuotas = (ArrayList<Cuota>)request.getAttribute("listaCuotas");
		} else {
			listaCuotas = new ArrayList<Cuota>();
		}
		
	    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
	    
	%>
	<h3>Cuotas | Prestamo #<%= idPrestamo %></h3>
	
	<div class="container py-4 border rounded">
    <div class="row justify-content-center">
    
 <!-- Tarjeta 1 - En Evaluación -->       
<a class="card-accesos col-md-4 text-center text-decoration-none text-dark" href="#" onclick="submitForm('formPagada')">
    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-check mb-2" viewBox="0 0 16 16">
        <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425z"/>
    </svg>
    <h6 class="card-title">Pagada</h6>
    <p class="card-text mb-0 badge text-bg-success"><%=cuotaPagada %></p>
    <p class="card-text">$ <%=sumaPagas %></p>
</a>
<form id="formPagada" action="ServletReporte" method="post" style="display:none;">
    <input type="hidden" name="idPrestamo" value="<%= idPrestamo %>">
    <input type="hidden" name="tipo" value="pagada">
    <input type="hidden" name="btnVerCuotaFiltrada" value="true">
</form>
<!-- Fin Tarjeta 1 -->

<!-- Tarjeta 2 - Pendientes -->
<a class="card-accesos col-md-4 text-center text-decoration-none text-dark" href="#" onclick="submitForm('formPendiente')">
    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-x mb-2" viewBox="0 0 16 16">
        <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708"/>
    </svg>
    <h6 class="card-title">Pendiente</h6>
    <p class="card-text mb-0 badge text-bg-danger"><%=cuotaPendiente %></p>
    <p class="card-text">$ <%=sumaPendientes %></p>
</a>
<form id="formPendiente" action="ServletReporte" method="post" style="display:none;">
    <input type="hidden" name="idPrestamo" value="<%= idPrestamo %>">
    <input type="hidden" name="tipo" value="pendiente">
    <input type="hidden" name="btnVerCuotaFiltrada" value="true">
</form>
<!-- Fin Tarjeta 2 -->
<!-- Tarjeta 3 - Todas -->
<a class="card-accesos col-md-4 text-center text-decoration-none text-dark" href="#" onclick="submitForm('formTodas')">
    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-list mb-2" viewBox="0 0 16 16">
        <path d="M3 4.5a.5.5 0 0 1 .5-.5H12a.5.5 0 0 1 0 1H3.5a.5.5 0 0 1-.5-.5zm0 3a.5.5 0 0 1 .5-.5H12a.5.5 0 0 1 0 1H3.5a.5.5 0 0 1-.5-.5zm0 3a.5.5 0 0 1 .5-.5H12a.5.5 0 0 1 0 1H3.5a.5.5 0 0 1-.5-.5z"/>
    </svg>
    <h6 class="card-title">Todas</h6>
    <p class="card-text mb-0 badge text-bg-primary"><%=cuotaTodas %></p>
    <p class="card-text">$ <%=sumaTodas %> </p>
</a>
<form id="formTodas" action="ServletReporte" method="post" style="display:none;">
    <input type="hidden" name="idPrestamo" value="<%= idPrestamo %>">
    <input type="hidden" name="tipo" value="todas">
    <input type="hidden" name="btnVerCuotaFiltrada" value="true">
</form>

<script>
function submitForm(formId) {
    document.getElementById(formId).submit();
}
</script>
 
    </div>
</div>
	
	<div class="card">	
	<div class="card-header d-flex justify-content-end">
        <button class="btn btn-dark btn-sm" onclick="volverPantallaAnterior()">< Regresar</button>
    </div>                    	  
	  <div class="card-body">
	    <table id="tablaCuotas" class="table table-striped" style="width:100%">
	        <thead>
	            <tr>
	                <th scope="col">Cuota</th>
	                <th scope="col">Fecha Vencimiento</th>
	                <th scope="col">Importe de cuota</th>
	                <th scope="col">Estado</th>
	                <th scope="col">Fecha Pago</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<%for (Cuota cuota : listaCuotas){ %>
                <tr>
                    <td># <%= cuota.getNroCuota()%></td>
                    <td><%= cuota.getFechaVencimiento().format(formatoFecha) %></td>
                    <td><%= formatoMoneda.format(cuota.getPrestamo().getImporteMensual()) %></td>
                    <% if(cuota.getFechaPago() == null){ %>
	                    <td>
	                    	<span class="badge bg-danger">PENDIENTE DE PAGO</span>
	                    </td>
	                    <td>Pendiente</td>
	                <% } else { %>
	                	<td>
                    		<span class="badge bg-success">PAGADA</span>
                    	</td>
                    	<td><%= cuota.getFechaPago().format(formatoFecha) %></td>
	                <% } %>
                </tr>
              	<% } %>
	        </tbody>
	    </table>
	  </div>
	</div>
	
<script>	
	$(document).ready(function() {
	    $('#tablaCuotas').DataTable({
	        language: {
	        	url: 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json',            
	        },
		    layout: {
		    	topStart: {
		    		buttons: [
		                {
		                   	extend: 'excel',
		                   	text: 'Excel',
		                    className: 'btn btn-primary'
		                },
		                {
		                   	extend: 'pdf',
		                   	text: 'Pdf',
		                    className: 'btn-primary'
		                },
		                {
		                   	extend: 'print',
		                   	text: 'Imprimir',
		                    className: 'btn btn-primary'
		                }
		           ]
		    	}
	    	}
	    });
	    columnDefs: [
            { type: 'num', targets: 0 }
        ]
	});
</script>

<%@ include file="Footer.jsp" %>
