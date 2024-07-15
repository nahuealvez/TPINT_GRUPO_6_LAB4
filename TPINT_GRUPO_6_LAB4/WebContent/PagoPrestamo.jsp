<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>

<%@ include file="Header.jsp" %>

    <div class="container mt-2 p-1">
        <h3 class="mb-3">Pago de cuota</h2>
        <form class="d-flex flex-column gap-3">
        <div id="pagoDeCuota" class="col-md-4 position-relative alert alert-primary mb-0">
           	<div>
           		<strong>Nro de cuota: </strong>
           		<p>2</p>
           	</div>
           	<div>
           		<strong>Fecha de vencimiento: </strong>
           		<p>12/08/2024</p>
           	</div>
           	<div>
           		<strong>Importe a pagar: </strong>
           		<div class="d-flex flex-row">
           			<p>$</p>
           			<p>2600</p>
           		</div>
           	</div>
        </div>
        <div id="confirmacionPagoDeCuota" class="col-md-4 position-relative alert alert-primary mb-0">
            	<h6 class="mb-3">¿Está seguro de realizar el pago de esta cuota?</h6>
            	<div>
            		<strong>Nro de cuota: </strong>
            		<p>2</p>
            	</div>
            	<div>
            		<strong>Fecha de vencimiento: </strong>
            		<p>12/08/2024</p>
            	</div>
            	<div>
            		<strong>Importe a pagar: </strong>
            		<div class="d-flex flex-row">
            			<p>$</p>
            			<p>2600</p>
            		</div>
            	</div>
            	<div>
            		<strong>Cuenta saliente: </strong>
            		<p>100001 || Caja de ahorro</p>
            	</div>
            </div>
        <div class="col-md-4 position-relative camposFormulario">
           <label for="txtCuentaSaliente">Cuenta saliente:</label>
           <select id="txtCuentaSaliente" name="txtCuentaSaliente" class="form-select form-select-sm" required>
           		<option selected disabled value="">Seleccione cuenta saliente...</option>
           </select>
       	</div>
		<div class="col-md-4">
         	<button id="volverBtn" class="btn btn-dark btn-sm" type="button" onclick="volverPantallaAnterior()">< Volver</button>
            <button id="pagarBtn" class="btn btn-primary btn-sm" type="button" onclick="">Pagar</button>
            <button id="confirmarBtn" class="btn btn-success btn-sm" type="submit" name="">Confirmar</button>
            <button id="cancelarBtn" type="button" class="btn btn-danger btn-sm" onclick="">Cancelar</button>
        </div>
        </form>        	
    </div>

<%@ include file="Footer.jsp" %>