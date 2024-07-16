<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="dominio.Prestamo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	ArrayList<Prestamo> listaPrestamos = null;
	if (request.getAttribute("listaPrestamosCliente") != null) {
		listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("listaPrestamosCliente");
	} else {
		listaPrestamos = new ArrayList<>();
	}

	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
%>

<h3>Préstamos</h3>
<div class="card">
	<form action="ServletPrestamo" method="post">
		<div class="card-header d-flex justify-content-start gap-2">
			<div>
				<select class="form-select col-sm-3" name="selectedFiltroEstadoPrestamo">
					<option value="Pendiente">Pendiente</option>
					<option value="1">Aprobado</option>
					<option value="0">Rechazado</option>
				</select>
			</div>
			<button class="btn btn-primary" type="submit" name="btnFiltrarEstado" id="btnFiltrarEstado">Filtrar</button>
			<a class="btn btn-dark" href="#">Quitar filtros</a>
		</div>
	</form>
	<div class="card-body">
		<table id="tablaPrestamosBanco" class="table table-striped"
			style="width: 100%">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Fecha solicitud</th>
					<th scope="col">Dni</th>
					<th scope="col">Nombre completo</th>
					<th scope="col">Importe solicitado</th>
					<th scope="col">Cuotas</th>
					<th scope="col">Estado</th>
					<th scope="col">Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Prestamo pre : listaPrestamos) {
				%>
				<tr>
					<td class="align-middle"><%=pre.getId()%></td>
					<td class="align-middle"><%=pre.getFechaSolicitud().format(formatoFecha)%></td>
					<td class="align-middle"><%=pre.getCliente().getDni()%></td>
					<td class="align-middle"><%=pre.getCliente().getApellido() + " " + pre.getCliente().getNombre()%></td>
					<td class="align-middle"><%=formatoMoneda.format(pre.getImportePedido())%></td>
					<td class="align-middle"><%=pre.getCuotas()%></td>
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
							</form> 
							<!-- MODAL PARA APROBAR EL PRESTAMO  -->
							<% if (pre.isEstadoValidacion() == null) { %>
								<button type="button" class="btn btn-outline-success btn-sm" data-bs-toggle="modal" data-bs-target="#mdlAprobarPrestamo<%= pre.getId() %>">
									Aprobar
								</button>
							<% } else if (!pre.isEstadoValidacion()) { %>
								<button type="button" class="btn btn-outline-success btn-sm disabled">
									Aprobar
								</button>
							<% } else { %>
								<button type="button" class="btn btn-outline-success btn-sm disabled">
									Aprobar
								</button>
							<% } %>
							<div class="modal fade" id="mdlAprobarPrestamo<%= pre.getId() %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h1 class="modal-title fs-5" id="exampleModalLabel<%= pre.getId() %>"><%="Aprobar préstamo #" + pre.getId()%></h1>
											<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">¿Confirma aprobar el préstamo?</div>
										<div class="modal-footer">
											<form action="ServletPrestamo" method="post">
												<input type="hidden" name="confirmarAprobacion" value="confirmarAprobacion">
												<input type="hidden" name="idPrestamo" value="<%=pre.getId()%>">
												<button class="btn btn-success" type="submit" name="confirmarAprobacion">Sí, apruebo</button>
												<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
											</form>
										</div>
									</div>
								</div>
							</div>
							<!-- MODAL PARA RECHAZAR EL PRESTAMO  -->
							<% if (pre.isEstadoValidacion() == null) { %>
								<button type="button" class="btn btn-outline-danger btn-sm" data-bs-toggle="modal" data-bs-target="#mdlAprobarPrestamo<%= pre.getId() %>">
									Rechazar
								</button>
							<% } else if (!pre.isEstadoValidacion()) { %>
								<button type="button" class="btn btn-outline-danger btn-sm disabled">
									Rechazar
								</button>
							<% } else { %>
								<button type="button" class="btn btn-outline-danger btn-sm disabled">
									Rechazar
								</button>
							<% } %>
							<div class="modal fade" id="mdlRechazarPrestamo<%= pre.getId() %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h1 class="modal-title fs-5" id="exampleModalLabel<%= pre.getId() %>"><%="Rechazar préstamo #" + pre.getId()%></h1>
											<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">¿Confirma rechazar la solicitud?</div>
										<div class="modal-footer">
											<form action="ServletPrestamo" method="post">
												<input type="hidden" name="idPrestamo" value="<%=pre.getId()%>">
												<button class="btn btn-success" type="submit" name="rechazarSolicitud">Sí, rechazar</button>
												<button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</td>
				</tr>
				<% } %>
			</tbody>
		</table>
	</div>
</div>

<!-- Scripts -->

<script>
	new DataTable('#tablaPrestamosBanco', {
		language : {
			url : 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json',
		},
	});
</script>