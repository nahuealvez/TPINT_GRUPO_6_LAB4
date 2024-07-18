<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="dominio.Cuota"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="dominio.Prestamo"%>
<%@page import="dominio.Cuenta"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>

<%@ include file="Header.jsp" %>
	
	<%
		boolean existeMensaje = false;
		String mensaje = null;
		String claseMensaje = null;
	
		if (request.getAttribute("mensaje") != null) {
			mensaje = (String)request.getAttribute("mensaje");
			existeMensaje = true;
		}
	
		if (request.getAttribute("claseMensaje") != null) {
			claseMensaje = (String)request.getAttribute("claseMensaje");
			existeMensaje = true;
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
	<div class="card">	                    	  
	  <div class="card-body">
	  	<% if (existeMensaje) { %>
		  	<div id="alert" class="<%= claseMensaje %>" role="alert">
	  			<%= mensaje %>
			</div>
		<% } %>
	    <table id="tablaCuotas" class="table table-striped" style="width:100%">
	        <thead>
	            <tr>
	                <th scope="col">Cuota</th>
	                <th scope="col">Fecha Vencimiento</th>
	                <th scope="col">Importe de cuota</th>
	                <th scope="col">Estado</th>
	                <th scope="col">Fecha Pago</th>
	                <th scope="col">Acciones</th>
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
	                <td class="align-middle">
	                	<div class="d-flex justify-content-start align-items-center gap-2">
	                		<% if(cuota.getFechaPago() == null){ %>	
		                		<form action="ServletPrestamo" method="post">
		                			<input type="hidden" value="<%= cuota.getId() %>" name="idCuota">
		                			<input type="hidden" value="<%= cuota.getPrestamo().getCliente().getId() %>" name="idCliente">
		                			<button type="submit" class="btn btn-outline-success btn-sm" name="btnPagarCuota">
									  Pagar
									</button>
		                		</form>
	                		<% } else { %>
		                		<button type="button" class="btn btn-outline-success btn-sm disabled">
								  Pagar
								</button>
							<% } %>
	                	</div>
	                </td>
                </tr>
              	<% } %>
	        </tbody>
	    </table>
	  </div>
	</div>
	
<script>
	new DataTable('#tablaCuotas', {
		language: {
			url: 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json',
		},
        columnDefs: [
            { type: 'num', targets: 0 }
        ]
	});
</script>

<%@ include file="Footer.jsp" %>
