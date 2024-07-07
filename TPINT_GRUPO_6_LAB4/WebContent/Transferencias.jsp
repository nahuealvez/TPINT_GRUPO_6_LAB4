<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dominio.Cuenta" %>
<%@ page import="java.util.ArrayList" %>

<%@ include file="Header.jsp"%>
	
	<%
		ArrayList<Cuenta> cuentas = null;
		cuentas = (ArrayList<Cuenta>)session.getAttribute("cuentasPorCliente");
	%>
	
	<script>
		window.onload = function() {
	        document.getElementById("prestamoAConfirmar").style.display = "none";
	        document.getElementById("confirmarBtn").style.display = "none";
	        document.getElementById("cancelarBtn").style.display = "none";
	    };
	
	    // FUNCION PARA MOSTRAR FORMULARIO DE CONFIRMACION AL HACER CLICK EN "SOLICITAR"
	    function mostrarConfirmacion() {
	        document.getElementById("prestamoAConfirmar").style.display = "block";
	        document.getElementById("confirmarBtn").style.display = "inline-block";
	        document.getElementById("cancelarBtn").style.display = "inline-block";
	        
	        document.getElementById("formularioSolicitud").style.display = "none";
	        document.getElementById("volverBtn").style.display = "none";
	        document.getElementById("solicitarBtn").style.display = "none";
	    }
	</script>

	<div class="container mt-2 p-1">
        <h2 class="mb-3">Transferencias</h2>
        <form id="formularioSolicitud" class="d-flex flex-column gap-3 needs-validation" action="ServletCliente" method="get" novalidate>
            <div class="col-md-4 position-relative">
                <label for="txtDni">Importe a transferir:</label>
                <input type="number" 
                	class="form-control 
                	form-control-sm"
                	id="txtImporteSolicitado" 
                	name="txtImporteSolicitado"
                	value="" 
                	placeholder="Ingrese el importe a transferir" 
                required>
            </div>
            <div class="col-md-4 position-relative">
                <label for="txtApellido">CBU destino:</label>
                <input type="text" 
                	class="form-control form-control-sm" 
                	id="txtCuotas" 
                	name="txtCuotas"
                	placeholder="Ingrese el CBU de destino" 
                required>
            </div>
            <div class="col-md-4 position-relative">
                <label for="cuentaDestino">Cuenta saliente:</label>
                <select id="cuentaDestino" class="form-select form-select-sm" required>
                	<option selected disabled value="">Seleccione cuenta saliente...</option>
                	<% for (Cuenta cuenta : cuentas) { %>
                    	<option value="<%= cuenta.getId() %>"><%= cuenta.toStringResumido() %></option>
                    <% } %>
                </select>
            </div>
            <div id="prestamoAConfirmar" class="col-md-4 position-relative alert alert-primary mb-0">
            	<h6 class="m-0">¿Está seguro de realizar esta transferencia?</h6>
            	<br>
            	<strong>Importe a transferir: </strong><p>$10.000,00</p>
            	<strong>CBU destino: </strong><p>9999999999999999999999</p>
            	<strong>Cuenta saliente: </strong><p>100001 | Caja de ahorros</p>
            </div>
            <div class="col-md-4">
            	<button id="volverBtn" class="btn btn-dark btn-sm" type="button" onclick="volverPantallaAnterior()">< Volver</button>
                <button id="solicitarBtn" class="btn btn-primary btn-sm" type="button" onclick="mostrarConfirmacion()">Transferir</button>
                <button id="confirmarBtn" class="btn btn-success btn-sm" type="submit">Confirmar</button>
                <a id="cancelarBtn" class="btn btn-danger btn-sm" href="Index.jsp">Cancelar</a>
            </div>
        </form>
    </div>
	
	

<%@ include file="Footer.jsp"%>


