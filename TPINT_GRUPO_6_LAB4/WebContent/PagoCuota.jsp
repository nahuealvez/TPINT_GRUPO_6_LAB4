<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dominio.Cuota" %>
<%@ page import="dominio.Cuenta" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Locale"%>

<%@ include file="Header.jsp" %>

<%
	Cuota cuota = new Cuota();
	ArrayList<Cuenta> cuentas = null;

	if (request.getAttribute("cuota") != null) {
		cuota = (Cuota)request.getAttribute("cuota");
	}
	
	if (request.getAttribute("cuentas") != null) {
		cuentas = (ArrayList<Cuenta>)request.getAttribute("cuentas");
	}
	
	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
%>

    <div class="container mt-2 p-1">
        <h2 class="mb-3">Pago de cuota</h2>
        <form id="pagoDeCuota" class="d-flex flex-column gap-3 camposFormulario" action="ServletPrestamo" method="post" novalidate>
        <input type="hidden" name="idCuota" id="idCuota" value="<%= cuota.getId() %>">
        <input type="hidden" name="idPrestamo" id="idPrestamo" value="<%= cuota.getPrestamo().getId() %>">
	        <div id="resumenCuota" class="col-md-4 position-relative alert alert-primary mb-0 needs-validation" action="ServletPrestamo" method="post">
	           	<div>
	           		<strong>Nro de cuota: </strong>
	           		<p><%= cuota.getNroCuota() %></p>
	           	</div>
	           	<div>
	           		<strong>Fecha de vencimiento: </strong>
	           		<p><%= cuota.getFechaVencimiento().format(formatoFecha) %></p>
	           	</div>
	           	<div>
	           		<strong>Importe a pagar: </strong>
	           		<div class="d-flex flex-row">
	           			<p><%= formatoMoneda.format(cuota.getPrestamo().getImporteMensual()) %></p>
	           		</div>
	           	</div>
	        </div>
	        <div class="col-md-4 position-relative camposFormulario">
	           <label for="ddlCuentaSaliente">Cuenta saliente:</label>
	           <select id="ddlCuentaSaliente" name="ddlCuentaSaliente" class="form-select form-select-sm" required>
	           		<option selected disabled value="">Seleccione cuenta saliente...</option>
	           		<% for (Cuenta cuenta : cuentas) { %>
	           			<option value="<%= cuenta.getId() %>"><%= cuenta.toStringResumido() %></option>
	           		<% } %>
	           </select>
	       	</div>
	       	<div id="confirmacionPagoDeCuota" class="col-md-4 position-relative alert alert-primary mb-0" style="display: none">
	            	<h6 class="mb-3">¿Está seguro de realizar el pago?</h6>
	            	<div>
	            		<strong>Nro de cuota: </strong>
	            		<p><%= cuota.getNroCuota() %></p>
	            	</div>
	            	<div>
	            		<strong>Fecha de vencimiento: </strong>
	            		<p><%= cuota.getFechaVencimiento().format(formatoFecha) %></p>
	            	</div>
	            	<div>
	            		<strong>Importe a pagar: </strong>
	            		<div class="d-flex flex-row">
	            			<p><%= formatoMoneda.format(cuota.getPrestamo().getImporteMensual()) %></p>
	            		</div>
	            	</div>
	            	<div>
	            		<strong>Cuenta saliente: </strong>
	            		<p id="txtCuentaSalienteAConfirmar"></p>
	            	</div>
	        </div>
			<div class="col-md-4">
	         	<button id="volverBtn" class="btn btn-dark btn-sm" type="button" onclick="volverPantallaAnterior()">< Volver</button>
	            <button id="pagarBtn" class="btn btn-primary btn-sm" type="button" onclick="validarYMostrarConfirmacionPagoCuota(event)">Pagar</button>
	            <button id="confirmarBtn" class="btn btn-success btn-sm" type="submit" name="btnConfirmarPagoCuota" style="display: none;">Confirmar</button>
	            <button id="cancelarBtn" type="button" class="btn btn-danger btn-sm" onclick="volverPantallaAnterior()" style="display: none;">Cancelar</button>
	        </div>
        </form>        	
    </div>

<%@ include file="Footer.jsp" %>