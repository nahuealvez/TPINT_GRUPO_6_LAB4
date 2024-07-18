<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page buffer="8192kb" autoFlush="true" %>
<%@ page import="dominio.Cuenta" %>
<%@ page import="java.util.ArrayList" %>

<%@ include file="Header.jsp"%>
	
	<%
		if (usuarioLogueado.getId() == 1) {
			response.sendRedirect("SinPermisos.jsp");
		}
	
		if (sessionLogueada != null) {
			cliente = (Cliente) session.getAttribute("cliente");
		}
	
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
					<a class="<%= claseBtnRegular %>" href="ServletTransferencia?btnMovimientoCuentas=1">Movimiento de cuentas</a>
					<a class="<%= claseBtnRegular %>" href="ServletTransferencia?btnTransferenciaTerceros=1">Transferencia a terceros</a>				
				</div>
			<% }
			   else if (tipoTransferencia == 1) { %>
			    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
					<button class="<%= claseBtnActivo %>">Movimiento de cuentas</button>
					<a class="<%= claseBtnRegular %>" href="ServletTransferencia?btnTransferenciaTerceros=1">Transferencia a terceros</a>
				</div>
			<% }
			   else if (tipoTransferencia == 2) { %>
			    <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
			    	<a class="<%= claseBtnRegular %>" href="ServletTransferencia?btnMovimientoCuentas=1">Movimiento de cuentas</a>
					<button class="<%= claseBtnActivo %>">Transferencia a terceros</button>
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


