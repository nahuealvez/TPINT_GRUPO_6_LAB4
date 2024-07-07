<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dominio.Cliente"%>
<%@ page import="dominio.Cuenta"%>
<%@ page import="java.util.ArrayList"%>
<%@ include file="Header.jsp"%>

<%
	boolean existeMensaje = false;
	String mensaje = null;
	String claseMensaje = null;

	if (request.getAttribute("txtMensajeCuenta") != null) {
		mensaje = (String) request.getAttribute("txtMensajeCuenta");
		existeMensaje = true;
	}

	if (request.getAttribute("claseMensajeCuenta") != null) {
		claseMensaje = (String) request.getAttribute("claseMensajeCuenta");
		existeMensaje = true;
	}
%>


<h3>Cuentas</h3>

<div class="card">
	<%
		if (existeMensaje) {
	%>
	<div class="<%=claseMensaje%>" role="alert">
		<%=mensaje%>
	</div>
	<%
		}
	%>

	<div
		class="card-header d-flex justify-content-between align-items-center">
		<h4>Buscar Cliente por DNI</h4>
		<form action="ServletCuenta" method="post" class="d-flex gap-3">
			<input type="hidden" name="opcion" value="buscarCliente"> <input
				type="text" class="form-control" minlength="7" maxlength="8"
				placeholder="Ingrese DNI" name="dniCliente">
			<button type="submit" class="btn btn-primary">Buscar</button>
		</form>
	</div>

	<div class="card-body">

		<%
			Cliente clienteServlet = (Cliente) request.getAttribute("clienteServlet");
			Boolean errorBusqueda = (Boolean) request.getAttribute("errorBusqueda");

			if (clienteServlet != null) {
		%>

		<div class="card-header d-flex justify-content-end">
			<a class="btn btn-primary"
				href="ServletCuenta?opcion=agregar&dniCliente=<%=clienteServlet.getDni()%>">Agregar</a>
		</div>

		<div class="alert alert-success" role="alert">
			Cliente encontrado:
			<ul>
				<li><strong>Nombre:</strong> <%=clienteServlet.getNombre()%></li>
				<li><strong>Apellido:</strong> <%=clienteServlet.getApellido()%></li>
				<li><strong>DNI:</strong> <%=clienteServlet.getDni()%></li>
			</ul>
		</div>

		<h5>Cuentas del Cliente</h5>

		<%
			ArrayList<Cuenta> cuentasxCliente = (ArrayList<Cuenta>) request.getAttribute("cuentasxCliente");
				if (cuentasxCliente != null && !cuentasxCliente.isEmpty()) {
		%>
		<table id="tablaCuentas" class="table table-striped"
			style="width: 100%">
			<thead>
				<tr>
					<th>Tipo de cuenta</th>
					<th>Número</th>
					<th>Fecha Creación</th>
					<th>CBU</th>
					<th>Saldo</th>
					<th>Estado</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (Cuenta cuenta : cuentasxCliente) {
				%>
				<tr>
					<td><%=cuenta.getTipoCuenta().getDescripcion()%></td>
					<td><%=cuenta.getId()%></td>
					<td><%=cuenta.getFechaCreacion()%></td>
					<td><%=cuenta.getCbu()%></td>
					<td><%=cuenta.getSaldo()%></td>
					<td><%=cuenta.isEstado()
								? "<span class='badge text-bg-success'>Activo</span>"
								: "<span class='badge text-bg-danger'>Inactivo</span>"%></td>
					<td class="d-flex justify-content-center align-items-center gap-2">

						<form action="ServletCuenta" method="post">

							<%
								if (cuenta.isEstado()) {
							%>
							<button type="button" class="btn btn-outline-danger btn-sm"
								data-bs-toggle="modal"
								data-bs-target="#mlDesactivarCuenta<%=cuenta.getId()%>">
								Desactivar</button>

							<!-- Modal para desactivar -->
							<div class="modal fade"
								id="mlDesactivarCuenta<%=cuenta.getId()%>" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h1 class="modal-title fs-5" id="exampleModalLabel">Confirmar
												desactivación</h1>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">¿Está seguro de querer
											desactivar la cuenta?</div>
										<div class="modal-footer">
											<form action="ServletCuenta" method="post">
												<input type="hidden" name="opcion" value="cambiarEstado">
												<input type="hidden" name="idCuenta"
													value="<%=cuenta.getId()%>"> <input type="hidden"
													name="dniCliente" value="<%=clienteServlet.getDni()%>">
												<input type="hidden" name="estado"
													value="<%=cuenta.isEstado()%>"> <input
													type="submit" value="Desactivar" class="btn btn-danger">

												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Cancelar</button>
											</form>
										</div>
									</div>
								</div>
							</div>

							<%
								} else {
							%>
							<!-- Modal para activar -->
							<button type="button" class="btn btn-outline-success btn-sm"
								data-bs-toggle="modal"
								data-bs-target="#mlActivarCuenta<%=cuenta.getId()%>">
								Reactivar</button>

							<div class="modal fade" id="mlActivarCuenta<%=cuenta.getId()%>"
								tabindex="-1" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h1 class="modal-title fs-5" id="exampleModalLabel">Confirmar
												Reactivación</h1>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">¿Está seguro de querer reactivar
											la cuenta?</div>
										<div class="modal-footer">
											<form action="ServletCuenta" method="post">
												<input type="hidden" name="opcion" value="cambiarEstado">
												<input type="hidden" name="idCuenta"
													value="<%=cuenta.getId()%>"> <input type="hidden"
													name="dniCliente" value="<%=clienteServlet.getDni()%>">
												<input type="hidden" name="estado"
													value="<%=cuenta.isEstado()%>"> <input
													type="submit" value="Reactivar" class="btn btn-success">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Cancelar</button>
											</form>
										</div>
									</div>
								</div>
							</div>

							<%
								}
							%>

						</form>
					</td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<%
			} else {
		%>
		<div class="alert alert-info" role="alert">No se encontraron
			cuentas para este cliente.</div>
		<%
			}
		%>

		<%
			} else if (errorBusqueda != null && errorBusqueda) {
		%>
		<div class="alert alert-danger" role="alert">No se encontró
			ningún cliente con ese DNI.</div>
		<%
			}
		%>
	</div>
</div>


<!-- Scripts -->
<script
	src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/plug-ins/1.13.1/i18n/es-ES.json"></script>
<script>
	$(document)
			.ready(
					function() {
						$('#tablaCuentas')
								.DataTable(
										{
											language : {
												url : 'https://cdn.datatables.net/plug-ins/1.13.1/i18n/es-ES.json'
											}
										});
					});
</script>

<%@ include file="Footer.jsp"%>
