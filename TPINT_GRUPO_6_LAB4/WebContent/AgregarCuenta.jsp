<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dominio.Cliente"%>
<%@ page import="dominio.Cuenta"%>
<%@ include file="Header.jsp"%>

<div class="container mt-2 p-1">
	<h3 class="mb-3">Agregar cuenta</h3>
	<form class="row g-2 needs-validation" action="ServletCuenta"
		method="post" novalidate>
		<input type="hidden" name="opcion" value="agregarCuenta">
		<div class="col-md-6 position-relative">
			<div class="card-body">
				<%
					Cliente clienteServlet = (Cliente) request.getAttribute("clienteServlet");
					if (clienteServlet != null) {
				%>

				<input type="hidden" name="clienteId"
					value="<%=clienteServlet.getIdCliente()%>">
				<div class="alert alert-success" role="alert">
					Cliente:
					<ul>
						<li><strong>Nombre:</strong> <%=clienteServlet.getNombre()%></li>
						<li><strong>Apellido:</strong> <%=clienteServlet.getApellido()%></li>
						<li><strong>DNI:</strong> <%=clienteServlet.getDni()%></li>
					</ul>
				</div>
				<%
					}
				%>

				<div class="col-md-6 position-relative">
					<label for="ddlTipoCuenta">Tipo Cuenta:</label> <select
						class="form-select form-select-sm" id="ddlTipoCuenta"
						name="ddlTipoCuenta" required>
						<option selected disabled value="">Seleccione tipo de
							cuenta</option>
						<option value="1">Caja de ahorro</option>
						<option value="2">Cuenta corriente</option>
					</select>
				</div>

				<div class="col-md-6 position-relative">
					<label for="txtCbu">Cbu:</label> <input type="text"
						class="form-control form-control-sm" id="txtCbu" name="txtCbu"
						readonly
						value="<%=request.getAttribute("cbuGenerado") != null ? request.getAttribute("cbuGenerado") : ""%>">
				</div>

				<div class="col-md-6 position-relative">
					<label for="txtSaldo">Saldo:</label> <input type="text"
						class="form-control form-control-sm" id="txtSaldo" name="txtSaldo"
						value="10000.00" readonly>
				</div>

				<div class="col-md-6 mt-3">
					<button class="btn btn-dark btn-sm" type="button"
						onclick="volverPantallaAnterior()"> Volver</button>
					<input type="submit" value="Agregar" id="btnAgregarCuenta"
						name="btnAgregarCuenta" class="btn btn-success btn-sm">
				</div>
			</div>
		</div>
	</form>
</div>

<%@ include file="Footer.jsp"%>
