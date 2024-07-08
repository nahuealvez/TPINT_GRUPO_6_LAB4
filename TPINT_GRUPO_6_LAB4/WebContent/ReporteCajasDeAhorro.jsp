<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dominio.Cuenta" %>
<%@ page import="java.util.ArrayList" %>

<%@ include file="Header.jsp" %>

<h3 class="pt-3" >Reportes: Caja de Ahorro</h3>

<div class="card">
    <div class="card-header d-flex justify-content-end">
    <form action="ServletReporte" method="get">
	  	<input type="submit" value="Listar" class="btn btn-primary" id="btnListarCajaAhorro" name="btnListarCajaAhorro">
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
	            ArrayList<Cuenta> listaCajaAhorro = null;
	            if (request.getAttribute("listaCA") != null) {
	            	listaCajaAhorro = (ArrayList<Cuenta>) request.getAttribute("listaCA");
	            } else {
	            	listaCajaAhorro = new ArrayList<>();
	        	%>
	              <p class="alert alert-info lead">Presiona el bot�n Listar para cargar las Cajas de Ahorro .</p>
	        	<%
	            }
	            for (Cuenta cuentaCA : listaCajaAhorro) 
	            {
	        %>
                <tr>
                    <td><%= cuentaCA.getId() %></td>
            		<td><%= cuentaCA.getCliente().getApellido() %></td>
            		<td><%= cuentaCA.getCliente().getNombre() %></td>
            		<td><%= cuentaCA.getCliente().getDni() %></td>
            		<td><%= cuentaCA.getFechaCreacion() %></td> 
            		<td><%= cuentaCA.getTipoCuenta().getDescripcion() %></td>
            		<td><%= cuentaCA.getCbu() %></td>
            		<td><%= cuentaCA.getSaldo() %></td> 
      		     	<td><%= cuentaCA.isEstado() ? "<span class='badge text-bg-success'>Activo</span>" : "<span class='badge text-bg-danger'>Inactivo</span>" %></td>             		
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