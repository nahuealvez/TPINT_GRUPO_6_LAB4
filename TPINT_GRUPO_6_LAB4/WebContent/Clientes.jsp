<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dominio.Cliente" %>
<%@ page import="java.util.ArrayList" %>

<%@ include file="Header.jsp" %>

	<%
	
		boolean existeMensaje = false;
		String mensaje = null;
		String claseMensaje = null;
	
		if (request.getAttribute("txtMensajeAgregarCliente") != null) {
			mensaje = (String)request.getAttribute("txtMensajeAgregarCliente");
			existeMensaje = true;
		}
	
		if (request.getAttribute("claseMensajeAgregarCliente") != null) {
			claseMensaje = (String)request.getAttribute("claseMensajeAgregarCliente");
			existeMensaje = true;
		}
	
	%>

	<h3>Clientes</h3>
	<div class="card">
	  <div class="card-header d-flex justify-content-end">
	  	<a class="btn btn-primary" href="ServletProvincia?cargarCampos=1.jsp">Agregar</a>
	  </div>
	  <div class="card-body">
	  	<% if (existeMensaje) { %>
		  	<div id="alert" class="<%= claseMensaje %>" role="alert">
	  			<%= mensaje %>
			</div>
		<% } %>
	    <table id="tablaClientes" class="table table-striped" style="width:100%">
	        <thead>
	            <tr>
	                <th>#</th>
	                <th>Dni</th>
	                <th>Usuario</th>
	                <th>Nombre</th>
	                <th>Apellido</th>
	                <th>Estado</th>
	                <th>Acciones</th>
	            </tr>
	        </thead>
	        <tbody>
	            <%
	            ArrayList<Cliente> listaCliente = null;
	            if (request.getAttribute("listaC") != null) {
	                listaCliente = (ArrayList<Cliente>) request.getAttribute("listaC");
	            } else {
	                listaCliente = new ArrayList<>();
	            }
	
	            for (Cliente cliente : listaCliente) {
	            %>
	                <tr>
	                    <td><%= cliente.getIdCliente() %></td>
	                    <td><%= cliente.getDni() %></td>
	                    <td><%= cliente.getUsuario() %></td>
	                    <td><%= cliente.getNombre() %></td>
	                    <td><%= cliente.getApellido() %></td>
	                    <td><%= cliente.getEstado() %></td>
	                    <td>
		                    <form action="ServletCliente" method="post">
		                    		<input type="hidden" name="idCliente" value="<%= cliente.getIdCliente() %>">
		                            <input type="submit" value="Editar" id="btnModificarCliente"  name="btnModificarCliente"class="btn btn-outline-primary btn-sm">
		                    </form>
		                    <form action="ServletCliente" method="post">
		                    		<input type="hidden" name="idCliente" value="<%= cliente.getIdCliente() %>">
		                            <input type="submit" value="Desactivar" id="btnDesactivarCliente"  name="btnDesactivarCliente"class="btn btn-outline-danger btn-sm">
		                    </form>
	                    </td>
	                </tr>
	            <%
	            }
	            %>
	        </tbody>
	    </table>
	  </div>
	</div>

<!-- Scripts -->

<script>
	new DataTable('#tablaClientes', {
		language: {
			url: 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json',
		},
	});
</script>

<%@ include file="Footer.jsp" %>
