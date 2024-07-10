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
        <h3 class="mb-3">Detalle de préstamo</h2>
        <form class="d-flex flex-column gap-3 needs-validation" action="ServletPrestamo" method="post" novalidate>
       
            <div id="prestamoAConfirmar" class="col-md-4 position-relative alert alert-primary mb-0">
				
			    <br>
			    <p><strong>Importe solicitado: </strong><span id="confImporteSolicitado"></span></p>
			    <p><strong>Cuotas: </strong><span id="confCuotas"></span></p>
			    <p><strong>Interés aplicado: </strong>20%</p>
			    <p><strong>Monto a pagar: </strong><span id="confMontoPagar"></span></p>
			    <p><strong>Importe de cuota: </strong><span id="confImporteCuota"></span></p>
            </div>
            <div class="col-md-4">
            	<button id="volverBtn" class="btn btn-dark btn-sm" type="button" onclick="volverPantallaAnterior()">Volver</button>
               
            </div>
       </form>
    </div>

<%@ include file="Footer.jsp" %>
