<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dominio.Cuenta" %>
<%@ page import="java.util.ArrayList" %>   
    
<%@ include file="Header.jsp" %>
<h3 class="pt-3" >Reportes: Cuentas Corriente</h3>

<div class="card">
    <div class="card-header d-flex justify-content-end">
	  	<form action="ServletReporte" method="get">
	  	<input type="submit" value="Listar" class="btn btn-primary" id="btnListarCuentas" name="btnListarCuentas">
	</form>  	
    </div>
    <div class="card-body">
        <table id="tablaClientes" class="table table-striped" style="width:100%">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Apellido</th>
            		<th scope="col">Nombre</th>
            		<th scope="col">DNI</th>
            		<th scope="col">Fecha de Creaci�n</th>
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
		              <p class="alert alert-info lead">Presiona el bot�n Listar para cargar las Cuentas Corrientes .</p>
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
            		<td><%= cuentaCC.getFechaCreacion() %></td> 
            		<td><%= cuentaCC.getTipoCuenta().getDescripcion() %></td>
            		<td><%= cuentaCC.getCbu() %></td>
            		<td><%= cuentaCC.getSaldo() %></td> 
      		     	<td><%= cuentaCC.isEstado() ? "<span class='badge text-bg-success'>Activo</span>" : "<span class='badge text-bg-danger'>Inactivo</span>" %></td>             		
            		<td>
                        <button class="btn btn-primary">Ver Movimientos</button>
                    </td>
                </tr>
              <% } %>
            </tbody>
        </table>
    </div>
</div>

<div class="col-md-6 mt-3">
	<a class="btn btn-dark btn-sm" href="Reportes.jsp">< Volver</a>
</div>

<script>
	new DataTable('#tablaClientes', {
		language: {
			url: 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json',
		},
	});
</script>
<%@ include file="Footer.jsp" %>