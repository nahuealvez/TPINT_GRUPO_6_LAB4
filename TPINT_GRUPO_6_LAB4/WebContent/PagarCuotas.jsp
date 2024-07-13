<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="dominio.Prestamo"%>
<%@page import="dominio.Cuenta"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>

<%@ include file="Header.jsp" %>
	
	<h3>Cuotas</h3>
	<div class="card">
	  <div class="card-header d-flex justify-content-end">
		                    	  
	  <div class="card-body">
	    <table id="tablaCuotas" class="table table-striped" style="width:100%">
	        <thead>
	            <tr>
	                <th scope="col">#</th>
	                <th scope="col">Fecha Vencimiento</th>
	                <th scope="col">Importe de cuota</th>
	                <th scope="col">Fecha Pago</th>
	                <th scope="col">Acciones</th>
	            </tr>
	        </thead>
	        <tbody>
                <tr>
                    <td>1</td>
                    <td>12/07/2024</td>
                    <td>$2600</td>
                    <td>PENDIENTE</td>
                    <td class="d-flex justify-content-start align-items-center gap-2">
	                   	<button type="button" class="btn btn-outline-success btn-sm disabled" data-bs-toggle="modal" data-bs-target="#mdlAprobarPrestamo" aria-disabled="true">
						  Pagar
						</button>
                    </td>                    
                </tr>
	        </tbody>
	    </table>
	  </div>
	</div>
	
<script>
	new DataTable('#tablaPrestamosBanco', {
		language: {
			url: 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json',
		},
	});
</script>

<%@ include file="Footer.jsp" %>
