<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="Header.jsp"%>

	<div class="container mt-2 p-1">
        <h2 class="mb-3">Transferencias</h2>
        <form class="d-flex flex-column gap-3 needs-validation" action="ServletCliente" method="get" novalidate>
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
                	<option selected disabled value="">Seleccione cuenta destino...</option>
                </select>
            </div>
            <div class="col-md-4 position-relative alert alert-primary mb-0">
            	<h6 class="m-0">¿Está seguro de realizar esta transferencia?</h6>
            	<br>
            	<strong>Importe a transferir: </strong><p>$10.000,00</p>
            	<strong>CBU destino: </strong><p>9999999999999999999999</p>
            	<strong>Cuenta saliente: </strong><p>100001 | Caja de ahorros</p>
            </div>
            <div class="col-md-4">
            	<button class="btn btn-dark btn-sm" onclick="volverPantallaAnterior()">< Volver</button>
                <button class="btn btn-primary btn-sm">Transferir</button>
                <button class="btn btn-success btn-sm">Confirmar</button>
                <a class="btn btn-danger btn-sm" href="Index.jsp">Cancelar</a>
            </div>
    </div>
	
	

<%@ include file="Footer.jsp"%>


