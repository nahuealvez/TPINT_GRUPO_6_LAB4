<%@page import="dominio.Cuenta"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>

<%@ include file="Header.jsp" %>

	<%
		ArrayList<Cuenta> cuentasCliente = null;
		if (request.getAttribute("cuentasCliente") != null) {
			cuentasCliente = (ArrayList<Cuenta>)request.getAttribute("cuentasCliente");
		}
		else {
			cuentasCliente = new ArrayList<Cuenta>();
		}
	%>
<script>
    // FUNCION PARA OCULTAR FORMULARIO DE CONFIRMACION
    window.onload = function() {
        document.getElementById("prestamoAConfirmar").style.display = "none";
        document.getElementById("confirmarBtn").style.display = "none";
        document.getElementById("cancelarBtn").style.display = "none";
    };

    // FUNCION PARA MOSTRAR FORMULARIO DE CONFIRMACION AL HACER CLICK EN "SOLICITAR"
    function mostrarConfirmacion() {
    	
        // OBTIENE LOS VALORES DEL FORMULARIO DE CARGA
        var importeSolicitado = document.getElementById("txtImporteSolicitado").value;
        var cuotas = document.getElementById("txtCuotas").value;
        
        // CALCULA EL IMPORTE FINAL INTERES INCLUIDO
        var interes = 0.20;
        var montoAPagar = importeSolicitado * (1 + interes);
        var importeCuota = montoAPagar / cuotas;
     
        // ACTUALIZA CONTENIDO A MOSTRAR DEL DIV DE CONFIRMACION
        document.getElementById("confImporteSolicitado").innerText = "$" + parseFloat(importeSolicitado).toFixed(2);
        document.getElementById("confCuotas").innerText = cuotas;
        document.getElementById("confMontoPagar").innerText = "$" + montoAPagar.toFixed(2);
        document.getElementById("confImporteCuota").innerText = "$" + importeCuota.toFixed(2);
    	
     	// MUESTRA DIV DE CONFIRMACION CON SUS BOTONES Y OCULTA EL DE SOLICITUD
        document.getElementById("prestamoAConfirmar").style.display = "block";
        document.getElementById("confirmarBtn").style.display = "inline-block";
        document.getElementById("cancelarBtn").style.display = "inline-block";
        
        document.getElementById("formularioSolicitud").style.display = "none";
        document.getElementById("volverBtn").style.display = "none";
        document.getElementById("solicitarBtn").style.display = "none";
    }
</script>
    <div class="container mt-2 p-1">
        <h3 class="mb-3">Solicitar préstamo</h2>
        <form class="d-flex flex-column gap-3 needs-validation" action="ServletPrestamo" method="post" novalidate>
        <div id="formularioSolicitud">
	            <div class="col-md-4 position-relative">
	                <label for="txtImporteSolicitado">Importe solicitado:</label>
	                <input type="number" 
	                	class="form-control 
	                	form-control-sm"
	                	id="txtImporteSolicitado" 
	                	name="txtImporteSolicitado"
	                	value="" 
	                	placeholder="Ingrese el importe a solicitar" 
	                required>
	            </div>
	            <div class="col-md-4 position-relative">
	                <label for="txtCuotas">Cuotas:</label>
	                <input type="text" 
	                	class="form-control form-control-sm" 
	                	id="txtCuotas" 
	                	name="txtCuotas"
	                	placeholder="Ingrese cantidad de cuotas" 
	                required>
	            </div>
	            <div class="col-md-4 position-relative">
	                <label for="ddlCuentasCliente">Cuenta destino:</label>
	                <select class="form-select form-select-sm" 
	                	id="ddlCuentasCliente" 
	                	aria-label="Default select example" 
	                	name="ddlCuentasCliente" 
                	required>
		                <option selected disabled value="">Seleccione cuenta destino...</option>
	                    <% for (Cuenta cuenta : cuentasCliente) { %>
	                    	<option value="<%= cuenta.getId() %>"><%= cuenta.toStringResumido()%></option>
	                    <% } %>	                
	              	</select>
	                
	            </div>
            </div>
            <div id="prestamoAConfirmar" class="col-md-4 position-relative alert alert-primary mb-0">
				<h5>¿Está seguro de solicitar este préstamo?</h5>
			    <br>
			    <p><strong>Importe solicitado: </strong><span id="confImporteSolicitado"></span></p>
			    <p><strong>Cuotas: </strong><span id="confCuotas"></span></p>
			    <p><strong>Interés aplicado: </strong>20%</p>
			    <p><strong>Monto a pagar: </strong><span id="confMontoPagar"></span></p>
			    <p><strong>Importe de cuota: </strong><span id="confImporteCuota"></span></p>
            </div>
            <div class="col-md-4">
            	<button id="volverBtn" class="btn btn-dark btn-sm" type="button" onclick="volverPantallaAnterior()">Volver</button>
                <button id="solicitarBtn" class="btn btn-primary btn-sm" type="button" onclick="mostrarConfirmacion()">Solicitar</button>
                <input type="hidden" name="idCliente" value="<%= cliente.getIdCliente() %>">
	            <button id="confirmarBtn" class="btn btn-success btn-sm" type="submit" name="confirmarBtn">Confirmar</button>
	            <a id="cancelarBtn" class="btn btn-danger btn-sm" href="Index.jsp">Cancelar</a>
            </div>
       </form>
    </div>

<%@ include file="Footer.jsp" %>
