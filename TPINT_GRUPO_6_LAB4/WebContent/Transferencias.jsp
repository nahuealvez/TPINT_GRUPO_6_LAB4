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
	    
	    function cargarConfirmacion() {
	    	let txtImporteATransferir = document.getElementById("txtImporteATransferir").value;
	    	let txtCbuDestino = document.getElementById("txtCbuDestino").value;
	    	let txtCuentaSaliente = document.getElementById("txtCuentaSaliente").options[document.getElementById("txtCuentaSaliente").selectedIndex].text;
	    	
	    	let txtImporteAConfirmar = document.getElementById("txtImporteAConfirmar");
	    	let txtCbuAConfirmar = document.getElementById("txtCbuAConfirmar");
	    	let txtCuentaAConfirmar = document.getElementById("txtCuentaAConfirmar");
	    	
	    	txtImporteAConfirmar.textContent = txtImporteATransferir;
	    	txtCbuAConfirmar.textContent = txtCbuDestino;
	    	txtCuentaAConfirmar.textContent = txtCuentaSaliente;
	    };

	    function mostrarConfirmacion() {	    	
	        document.getElementById("prestamoAConfirmar").style.display = "block";
	        document.getElementById("confirmarBtn").style.display = "inline-block";
	        document.getElementById("cancelarBtn").style.display = "inline-block";
	        
	        document.getElementById("formularioSolicitud").style.display = "none";
	        document.getElementById("volverBtn").style.display = "none";
	        document.getElementById("solicitarBtn").style.display = "none";
	        
	        let camposFormulario = document.getElementsByClassName("camposFormulario");
	        for (var i = 0; i < camposFormulario.length; i++) {
	            camposFormulario[i].style.display = "none";
	        }
	    };
	
		function cancelarOperacion() {
			document.getElementById("btnInicio").click();
		};
		
		function validarYMostrarConfirmacion(event) {
	        var form = document.getElementById("formularioSolicitud");
	        if (form.checkValidity() === false) {
	            event.preventDefault();
	            event.stopPropagation();
	        } else {
	        	cargarConfirmacion();
	            mostrarConfirmacion();
	        }
	        form.classList.add('was-validated');
	    };
	    
	    function validarDecimal(event) {
            const char = String.fromCharCode(event.which);
            if (!(/[0-9.]|\./.test(char))) {
                event.preventDefault();
            }
        };
	</script>

	<div class="container mt-2 p-1">
        <h2 class="mb-3">Transferencias</h2>
        <form id="formularioSolicitud" class="d-flex flex-column gap-3 needs-validation" action="ServletCliente" method="get" novalidate>
            <div class="col-md-4 position-relative camposFormulario">
                <label for="txtDni">Importe a transferir:</label>
                <input type="number" 
                	class="form-control 
                	form-control-sm"
                	id="txtImporteATransferir" 
                	name="txtImporteATransferir" 
                	placeholder="Ingrese el importe con punto para decimales (ej. 10300.50)"
                	step="0.01" 
	                min="0"
	                onkeypress="return validarDecimal(event)" 
                required>
            </div>
            <div class="col-md-4 position-relative camposFormulario">
                <label for="txtApellido">CBU destino:</label>
                <input type="text" 
                	class="form-control form-control-sm" 
                	id="txtCbuDestino" 
                	name="txtCbuDestino"
                	placeholder="Ingrese el CBU de destino"
                	pattern="\d{22}" 
               	    maxlength="22" 
                required>
            </div>
            <div class="col-md-4 position-relative camposFormulario">
                <label for="txtCuentaSaliente">Cuenta saliente:</label>
                <select id="txtCuentaSaliente" class="form-select form-select-sm" required>
                	<option selected disabled value="">Seleccione cuenta saliente...</option>
                	<% for (Cuenta cuenta : cuentas) { %>
                    	<option value="<%= cuenta.getId() %>"><%= cuenta.toStringResumido() %></option>
                    <% } %>
                </select>
            </div>
            <div id="prestamoAConfirmar" class="col-md-4 position-relative alert alert-primary mb-0">
            	<h6 class="mb-3">¿Está seguro de realizar esta transferencia?</h6>
            	<div>
            		<strong>Importe a transferir: </strong>
            		<div class="d-flex flex-row">
            			<p>$</p>
            			<p id="txtImporteAConfirmar"></p>
            		</div>
            	</div>
            	<div>
            		<strong>CBU destino: </strong>
            		<p id="txtCbuAConfirmar"></p>
            	</div>
            	<div>
            		<strong>Cuenta saliente: </strong>
            		<p id="txtCuentaAConfirmar"></p>
            	</div>
            </div>
            <div class="col-md-4">
            	<button id="volverBtn" class="btn btn-dark btn-sm" type="button" onclick="volverPantallaAnterior()">< Volver</button>
                <button id="solicitarBtn" class="btn btn-primary btn-sm" type="button" onclick="validarYMostrarConfirmacion(event)">Transferir</button>
                <button id="confirmarBtn" class="btn btn-success btn-sm" type="submit">Confirmar</button>
                <button id="cancelarBtn" type="button" class="btn btn-danger btn-sm" onclick="cancelarOperacion()">Cancelar</button>
            </div>
        </form>
    </div>
	
	

<%@ include file="Footer.jsp"%>


