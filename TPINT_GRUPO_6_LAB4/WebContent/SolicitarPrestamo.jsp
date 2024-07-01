<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dominio.Provincia" %>
<%@ page import="dominio.Localidad" %>
<%@ page import="java.util.ArrayList" %>

<%@ include file="Header.jsp" %>

    <div class="container mt-2 p-1">
        <h3 class="mb-3">Solicitar préstamo</h2>
        <form class="d-flex flex-column gap-3 needs-validation" action="ServletCliente" method="get" novalidate>
            <div class="col-md-4 position-relative">
                <label for="txtDni">Importe solicitado:</label>
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
                <label for="txtApellido">Cuotas:</label>
                <input type="text" 
                	class="form-control form-control-sm" 
                	id="txtCuotas" 
                	name="txtCuotas"
                	placeholder="Ingrese cantidad de cuotas" 
                required>
            </div>
            <div class="col-md-4 position-relative">
                <label for="cuentaDestino">Cuenta destino:</label>
                <select id="cuentaDestino" class="form-select form-select-sm" required>
                	<option selected disabled value="">Seleccione cuenta destino...</option>
                </select>
            </div>
            <div class="col-md-4 position-relative alert alert-primary mb-0">
            	<h5>¿Está seguro de solicitar este préstamo?</h5>
            	<br>
            	<p><strong>Importe solicitado: </strong>$1.000.000</p>
            	<p><strong>Cuotas: </strong>10</p>
            	<p><strong>Interés aplicado: </strong>20%</p>
            	<p><strong>Monto a pagar: </strong>$1.200.000</p>
            	<p><strong>Importe de cuota: </strong>$120.000</p>
            </div>
            <div class="col-md-4">
            	<button class="btn btn-dark btn-sm" onclick="volverPantallaAnterior()">< Volver</button>
                <button class="btn btn-success btn-sm">Solicitar</button>
                <button class="btn btn-success btn-sm">Confirmar</button>
            </div>
    </div>

<%@ include file="Footer.jsp" %>
