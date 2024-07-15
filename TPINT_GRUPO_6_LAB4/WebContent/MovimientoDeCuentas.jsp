<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dominio.Cuenta" %>
<%@ page import="java.util.ArrayList" %>

<%
	ArrayList<Cuenta> cuentas = null;

	if (session.getAttribute("cuentasPorCliente") != null) {
		cuentas = (ArrayList<Cuenta>)session.getAttribute("cuentasPorCliente");
	}
	else {
		cuentas = new ArrayList<Cuenta>();
	}
%>

		<form id="formularioSolicitud" class="d-flex flex-column gap-3 needs-validation" action="ServletTransferencia" method="post" novalidate>
			<input type="hidden" id="tipoTransferencia" name="tipoTransferencia" value="1" />
            <div class="col-md-4 position-relative camposFormulario">
                <label for="txtImporteMovimientoCuenta">Importe a transferir:</label>
                <input type="number" 
                	class="form-control 
                	form-control-sm"
                	id="txtImporteMovimientoCuenta" 
                	name="txtImporteMovimientoCuenta" 
                	placeholder="Ingrese el importe con punto para decimales (ej. 10300.50)"
                	step="0.01" 
	                min="0"
	                onkeypress="return validarDecimal(event)" 
                required>
            </div>
            <div class="col-md-4 position-relative camposFormulario">
                <label for="ddlCuentaSaliente">Cuenta saliente:</label>
                <select id="ddlCuentaSaliente" name="ddlCuentaSaliente" class="form-select form-select-sm" onchange="cargarCuentaDestino()" required>
                	<option selected disabled value="">Seleccione cuenta...</option>
                	<% for (Cuenta cuenta : cuentas) { %>
                    	<option value="<%= cuenta.getId() %>"><%= cuenta.toStringResumido() %></option>
                    <% } %>
                </select>
            </div>
            <div class="col-md-4 position-relative camposFormulario">
                <label for="ddlCuentaDestino">Cuenta destino:</label>
                <select id="ddlCuentaDestino" name="ddlCuentaDestino" class="form-select form-select-sm" required>
                	<option selected disabled value="">Seleccione cuenta...</option>
                </select>
            </div>
            <div id="MovimientoCuentaAConfirmar" class="col-md-4 position-relative alert alert-primary mb-0" style="display: none;">
            	<h6 class="mb-3">¿Está seguro de realizar esta transferencia?</h6>
            	<div>
            		<strong>Importe a transferir: </strong>
            		<div class="d-flex flex-row">
            			<p>$</p>
            			<p id="txtImporteMovimientoCuentaAConfirmar"></p>
            		</div>
            	</div>
            	<div>
            		<strong>Cuenta saliente: </strong>
            		<p id="txtCuentaSalienteAConfirmar"></p>
            	</div>
            	<div>
            		<strong>Cuenta destino: </strong>
            		<p id="txtCuentaDestinoAConfirmar"></p>
            	</div>
            </div>
            <div class="col-md-4">
            	<button id="volverBtn" class="btn btn-dark btn-sm" type="button" onclick="volverPantallaAnterior()"> Volver</button>
                <button id="solicitarBtn" class="btn btn-primary btn-sm" type="button" onclick="validarYMostrarConfirmacionMovimientoCuenta(event)">Transferir</button>
                <button id="confirmarBtn" style="display: none;" class="btn btn-success btn-sm" type="submit" name="confirmarBtn">Confirmar</button>
                <button id="cancelarBtn" style="display: none;" type="button" class="btn btn-danger btn-sm" onclick="cancelarOperacion()">Cancelar</button>
            </div>
        </form>