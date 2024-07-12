<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dominio.Cuenta" %>
<%@ page import="java.util.ArrayList" %>

<%@ include file="Header.jsp"%>
	
	<%
		boolean existeMensaje = false;
		String mensaje = null;
		String claseMensaje = null;
	
		if (request.getAttribute("txtMensajeAgregarTransferencia") != null) {
			mensaje = (String)request.getAttribute("txtMensajeAgregarTransferencia");
			existeMensaje = true;
		}
	
		if (request.getAttribute("claseMensajeAgregarTransferencia") != null) {
			claseMensaje = (String)request.getAttribute("claseMensajeAgregarTransferencia");
			existeMensaje = true;
		}
		
		int tipoTransferencia = 0;
		String claseBtnActivo = "btn btn-outline-dark active";
		String claseBtnRegular = "btn btn-outline-dark";
		
		if (request.getAttribute("tipoTransferencia") != null) {
			tipoTransferencia = (Integer)request.getAttribute("tipoTransferencia");
		}
	%>

	<div class="container mt-2 p-1">
        <h2 class="mb-3">Transferencias</h2>
        <% if (existeMensaje) { %>
		  	<div id="alert" class="<%= claseMensaje %>" role="alert">
	  			<%= mensaje %>
			</div>
		<% } %>
		<div class="d-flex flex-column col-md-4 mb-3 gap-2">
			<label>Seleccione tipo de transferencia:</label>
			<% if (tipoTransferencia == 0){ %>
				<div class="btn-group" role="group" aria-label="Basic radio toggle button group">
					<form class="btn-group" action="ServletTransferencia" method="post">
						<button type="submit" class="<%= claseBtnRegular %>" name="btnMovimientoCuentas" id="btnMovimientoCuentas">Movimiento de cuentas</button>
					</form>
					<form class="btn-group" action="ServletTransferencia" method="post">
						<button type="submit" class="<%= claseBtnRegular %>" name="btnTransferenciaTerceros" id="btnMovimientoCuentas">Transferencia a terceros</button>
					</form>					
				</div>
			<% }
			   else if (tipoTransferencia == 1) { %>
			    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
					<button class="<%= claseBtnActivo %>" name="btnMovimientoCuentas" id="btnMovimientoCuentas">Movimiento de cuentas</button>
					<form class="btn-group" action="ServletTransferencia" method="post">
						<button type="submit" class="<%= claseBtnRegular %>" name="btnTransferenciaTerceros" id="btnMovimientoCuentas">Transferencia a terceros</button>
					</form>
				</div>
			<% }
			   else if (tipoTransferencia == 2) { %>
			    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
			    	<form class="btn-group" action="ServletTransferencia" method="post">
						<button type="submit" class="<%= claseBtnRegular %>" name="btnMovimientoCuentas" id="btnMovimientoCuentas">Movimiento de cuentas</button>
					</form>
					<button class="<%= claseBtnActivo %>" name="btnTransferenciaTerceros" id="btnMovimientoCuentas">Transferencia a terceros</button>
				</div>
			<% } %>
		</div>
		<% if (tipoTransferencia == 1) { %>
		    <%@ include file="MovimientoDeCuentas.jsp" %>
		<% }
		   else if (tipoTransferencia == 2) { %>
		   	<%@ include file="TransferenciasTerceros.jsp" %>
		<% } %>
    </div>
	
	

<%@ include file="Footer.jsp"%>


