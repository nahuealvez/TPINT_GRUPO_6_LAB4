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

    <div class="container mt-2 p-1">
        <h3 class="mb-3">Solicitar préstamo</h3>
        <form id="formularioSolicitud" class="d-flex flex-column gap-3 needs-validation" action="ServletPrestamo" method="post" novalidate>
            <div class="col-md-4 position-relative camposFormulario">
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
            <div class="col-md-4 position-relative camposFormulario">
                <label for="txtCuotas">Cuotas:</label>
                <input type="text" 
                	class="form-control form-control-sm" 
                	id="txtCuotas" 
                	name="txtCuotas"
                	placeholder="Ingrese cantidad de cuotas" 
                required>
            </div>
            <div class="col-md-4 position-relative camposFormulario">
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
            <div id="prestamoAConfirmar" class="col-md-4 position-relative alert alert-primary mb-0" style="display: none;">
				<h5>¿Está seguro de solicitar este préstamo?</h5>
			    <p><strong>Importe solicitado: </strong><span id="confImporteSolicitado"></span></p>
			    <p><strong>Cuotas: </strong><span id="confCuotas"></span></p>
			    <p><strong>Interés aplicado: </strong>20%</p>
			    <p><strong>Monto a pagar: </strong><span id="confMontoPagar"></span></p>
			    <p><strong>Importe de cuota: </strong><span id="confImporteCuota"></span></p>
            </div>
            <div class="col-md-4">
            	<button id="volverBtn" class="btn btn-dark btn-sm" type="button" onclick="volverPantallaAnterior()">Volver</button>
                <button id="solicitarBtn" class="btn btn-primary btn-sm" type="button" onclick="validarYMostrarConfirmacionPrestamo(event)">Solicitar</button>
                <input type="hidden" name="idCliente" value="<%= cliente.getIdCliente() %>">
	            <button id="confirmarBtn" class="btn btn-success btn-sm" type="submit" name="confirmarBtn" style="display: none;">Confirmar</button>
	            <button id="cancelarBtn" style="display: none;" type="button" class="btn btn-danger btn-sm" onclick="volverPantallaAnterior()">Cancelar</button>
            </div>
       </form>
    </div>

<%@ include file="Footer.jsp" %>
