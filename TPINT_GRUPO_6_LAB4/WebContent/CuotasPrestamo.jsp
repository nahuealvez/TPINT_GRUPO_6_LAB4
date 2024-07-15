<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="dominio.Prestamo"%>
<%@page import="dominio.Cuenta"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>

<%@ include file="Header.jsp" %>
	
	<h3>Cuotas de prestamo #</h3>
	<div class="card">		                    	  
	  <div class="card-body">
	    <table id="tablaCuotas" class="table table-striped" style="width:100%">
	        <thead>
	            <tr>
	                <th scope="col">#</th>
	                <th scope="col">Fecha Vencimiento</th>
	                <th scope="col">Importe de cuota</th>
	                <th scope="col">Estado</th>
	                <th scope="col">Fecha Pago</th>
	                <th scope="col">Acciones</th>
	            </tr>
	        </thead>
	        <tbody>
                <tr>
                    <td>1</td>
                    <td>12/07/2024</td>
                    <td>$2600</td>
                    <td>
                    	<span class="badge bg-success">PAGADA</span>
                    </td>
                    <td>12/07/2024</td>
                    <td class="d-flex justify-content-start align-items-center gap-2">
	                   	<button type="button" class="btn btn-outline-success btn-sm">
						  Pagar
						</button>
                    </td>                    
                </tr>
                <tr>
                    <td>2</td>
                    <td>12/08/2024</td>
                    <td>$2600</td>
                    <td>
                    	<span class="badge bg-danger">PENDIENTE</span>
                    </td>
                    <td></td>
                    <td class="d-flex justify-content-start align-items-center gap-2">
	                   	<button type="button" class="btn btn-outline-success btn-sm">
						  Pagar
						</button>
                    </td>                    
                </tr>
	        </tbody>
	    </table>
	  </div>
	</div>
	
<script>
	new DataTable('#tablaCuotas', {
		language: {
			url: 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json',
		},
	});
</script>

<%@ include file="Footer.jsp" %>
