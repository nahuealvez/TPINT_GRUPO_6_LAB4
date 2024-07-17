<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="dominio.Prestamo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dominio.Cliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<%
		Cliente clienteLogueado = (Cliente) session.getAttribute("cliente");
	    ArrayList<Prestamo> listaPrestamos = null;
	    if (request.getAttribute("listaPrestamos") != null) {
	        listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("listaPrestamos");
	    } else {
	    	listaPrestamos = new ArrayList<>();
	    }
	    
	    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
	    
	    boolean existeMensaje = false;
		String mensaje = null;
		String claseMensaje = null;
	
		if (request.getAttribute("txtMensaje") != null) {
			mensaje = (String)request.getAttribute("txtMensaje");
			existeMensaje = true;
		}
	
		if (request.getAttribute("claseMensaje") != null) {
			claseMensaje = (String)request.getAttribute("claseMensaje");
			existeMensaje = true;
		}
	%>
	
	<h3>Préstamos</h3>
	<div class="card">
	  <div class="card-header d-flex justify-content-end">
	  
	  	<form action="ServletPrestamo" method="post">
	  		<input type="hidden" name="idCliente" value="<%=clienteLogueado.getIdCliente()%>">
	  		<input type="submit" value="Solicitar" id="btnSolicitarPrestamo"  name="btnSolicitarPrestamo"class="btn btn-primary">
	  	</form>
	  </div>
		                    	  
	  <div class="card-body">
	  	<% if (existeMensaje) { %>
		  	<div id="alert" class="<%= claseMensaje %>" role="alert">
	  			<%= mensaje %>
			</div>
		<% } %>
	    <table id="tablaPrestamosBanco" class="table table-striped" style="width:100%">
	        <thead>
	            <tr>
	                <th scope="col">#</th>
	                <th scope="col">Fecha solicitud</th>
	                <th scope="col">Importe de cuota</th>
	                <th scope="col">Cuotas</th>
	                <th scope="col">Prestamo solicitado</th>
	                <th scope="col">Monto a Pagar</th>
	                <th scope="col">Estado</th>
	                <th scope="col">Acciones</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<%for (Prestamo pre : listaPrestamos) {%>
                <tr>
                    <td class="align-middle"><%= pre.getId() %></td>
                    <td class="align-middle"><%= pre.getFechaSolicitud().format(formatoFecha) %></td>
                    <td class="align-middle"><%= formatoMoneda.format(pre.getImporteMensual()) %></td>
                    <td class="align-middle"><%= pre.getCuotas() %></td>
                    <td class="align-middle"><%= formatoMoneda.format(pre.getImportePedido()) %></td>
                    <td class="align-middle"><%= formatoMoneda.format(pre.getImporteAPagar()) %></td>
                    <% if (pre.isEstadoValidacion() == null) { %>
	                    <td class="align-middle">
	                    	<span class='badge bg-warning'>APROBACION PENDIENTE</span>
	                    </td>
                    <% } else if (!pre.isEstadoValidacion()) { %>
                    	<td class="align-middle">
                    		<span class="badge bg-danger">RECHAZADO</span>
                    	</td>
                    <% } else { %>
                    	<td class="align-middle">
                    		<span class="badge text-bg-success">APROBADO</span>
                    	</td> 
                    <% } %>
                    <td class="align-middle">
                    	<div class="d-flex justify-content-start align-items-center gap-2">
                    		<form action="ServletPrestamo" method="post">
							<input type="hidden" name="idPrestamo" value="<%=pre.getId()%>">
								<button type="submit" id="btnVerPrestamo" name="btnVerPrestamo"
									class="btn btn-primary btn-sm">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
										fill="currentColor" class="bi bi-eye-fill" viewBox="0 0 16 16">
									  <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0" />
									  <path
											d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8m8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7" />
									</svg>
								</button>
							
							<% if (pre.isEstadoValidacion() == null) { %>
			                    <button type="button" class="btn btn-outline-success btn-sm disabled">
								  Pagar
								</button>
		                    <% } else if (!pre.isEstadoValidacion()) { %>
		                    	<button type="button" class="btn btn-outline-success btn-sm disabled">
								  Pagar
								</button>
		                    <% } else { %>
		                    	<button type="submit" id="btnPagarPrestamo" name="btnPagarPrestamo" class="btn btn-outline-success btn-sm">
								  Pagar
								</button>
		                    <% } %>
		                    </form>
                    	</div>
                    </td>
                </tr>
                <% } %>
	        </tbody>
	    </table>
	  </div>
	</div>
	
<script>
	new DataTable('#tablaPrestamosBanco', {
		language: {
			url: 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json',
		},
	});
</script>