<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dominio.Cuenta" %>
<%@ page import="java.util.ArrayList" %>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
    
<%@ include file="Header.jsp" %>

<%

	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy H:m");
	NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));

%>

<h3 class="pt-3" >Reportes: Cuentas Corriente</h3>

<div class="card">
    <div class="card-header d-flex justify-content-end">
	  	<form action="ServletReporte" method="get">
	  	<input type="submit" value="Listar" class="btn btn-primary" id="btnListarCuentas" name="btnListarCuentas">
	</form>  	
    </div>
    <div class="card-body">
        <table id="tablaCuentas" class="table table-striped" style="width:100%">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Apellido</th>
            		<th scope="col">Nombre</th>
            		<th scope="col">DNI</th>
            		<th scope="col">Fecha de Creación</th>
            		<th scope="col">Tipo de Cuenta</th>
            		<th scope="col">CBU</th>
            		<th scope="col">Saldo</th>
            		<th scope="col">Estado</th>
            		<th scope="col">Acciones</th>
                </tr>
            </thead>
            <tbody>
            
            <%
	            ArrayList<Cuenta> listaCtaCte = null;
	            if (request.getAttribute("listaCtaCte") != null) {
	            	listaCtaCte = (ArrayList<Cuenta>) request.getAttribute("listaCtaCte");
	            } else {
	            	listaCtaCte = new ArrayList<>();
	            	%>
		              <p class="alert alert-info lead">Presiona el botón Listar para cargar las Cuentas Corrientes .</p>
		        	<%
	            }
	            for (Cuenta cuentaCC : listaCtaCte) 
	            {
	        %>
                <tr>
                    <td><%= cuentaCC.getId() %></td>
            		<td><%= cuentaCC.getCliente().getApellido() %></td>
            		<td><%= cuentaCC.getCliente().getNombre() %></td>
            		<td><%= cuentaCC.getCliente().getDni() %></td>
            		<td><%= cuentaCC.getFechaCreacion().format(formatoFecha) %></td> 
            		<td><%= cuentaCC.getTipoCuenta().getDescripcion() %></td>
            		<td><%= cuentaCC.getCbu() %></td>
            		<td><%= formatoMoneda.format(cuentaCC.getSaldo()) %></td> 
      		     	<td><%= cuentaCC.isEstado() ? "<span class='badge text-bg-success'>Activo</span>" : "<span class='badge text-bg-danger'>Inactivo</span>" %></td>             		
            		<td>
                        <form action="ServletReporte" method="get"> 
            				<input type="hidden" value="<%= cuentaCC.getId() %>" name= "IdCuentaCte"  >                  	
                        	<input type="submit" value="Ver Movimientos" class="btn btn-primary" id="btnVerMovimientosCta" name="btnVerMovimientosCta">
                        </form> 
                    </td>
                </tr>
              <% } %>
            </tbody>
        </table>
    </div>
</div>

<div class="col-md-6 mt-3">
	<a class="btn btn-dark btn-sm" href="Reportes.jsp"> Volver</a>
</div>

   
    <script>
        new DataTable('#tablaCuentas', {
            language: {
                url: 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json',
            },
            layout: {
    	    	topStart: {
    	    		buttons: [
    	                {
    	                   	extend: 'excel',
    	                   	text: 'Excel',
    	                    className: 'btn btn-primary'
    	                },
    	                {
    	                   	extend: 'pdf',
    	                   	text: 'Pdf',
    	                    className: 'btn-primary'
    	                },
    	                {
    	                   	extend: 'print',
    	                   	text: 'Imprimir',
    	                    className: 'btn btn-primary'
    	                }
    	           ]
    	    	}
        	}
        });
    </script>
<%@ include file="Footer.jsp" %>