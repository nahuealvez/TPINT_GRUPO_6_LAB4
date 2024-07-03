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
	                <th scope="col">#</th>
	                <th scope="col">Dni</th>
	                <th scope="col">Usuario</th>
	                <th scope="col">Nombre</th>
	                <th scope="col">Apellido</th>
	                <th scope="col">Estado</th>
	                <th scope="col">Acciones</th>
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
	                    <td>
	                    	<%= cliente.getEstado() ? "<span class='badge text-bg-success'>Activo</span>" : "<span class='badge text-bg-danger'>Inactivo</span>" %>
	                    </td>
	                    <td class="d-flex justify-content-center align-items-center gap-2">
	                    
		                    <form action="ServletProvincia" method="post">
		                    		<input type="hidden" name="idCliente" value="<%= cliente.getIdCliente() %>">
		                            <input type="submit" value="Modificar" id="btnModificarCliente"  name="btnModificarCliente"class="btn btn-outline-primary btn-sm">
		                    </form>
		                      <form action="ServletCliente" method="post">
                            <% if (cliente.getEstado()) { %>
                            <button type="button" class="btn btn-outline-danger btn-sm" data-toggle="modal" data-target="#mdlDesactivarCliente<%= cliente.getIdCliente() %>">
                                Desactivar
                            </button>
                            <!-- Modal para Desactivar -->
                             <div class="modal fade" id="mdlDesactivarCliente<%= cliente.getIdCliente() %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Confirmar Desactivación</h5>
                                            <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            ¿Está seguro de que desea desactivar el cliente?
                                        </div>
                                        <div class="modal-footer">
                                            <form action="ServletCliente" method="post">
                                                <input type="hidden" name="idClienteDesactivar" value="<%= cliente.getId() %>">    
                                                <input type="submit" value="Desactivar" class="btn btn-danger" id="btnDesactivarCliente" name="btnDesactivarCliente">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% } else { %>
                            <button type="button" class="btn btn-outline-success btn-sm" data-toggle="modal" data-target="#mdlActivarCliente<%= cliente.getIdCliente() %>">
                                Activar.......
                            </button>
                            <!-- Modal para Activar -->
                            <div class="modal fade" id="mdlActivarCliente<%= cliente.getIdCliente() %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Confirmar Activación</h5>
                                            <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            ¿Está seguro que desea activar el cliente?
                                        </div>
                                        <div class="modal-footer">
                                            <form action="ServletCliente" method="post">
                                                <input type="hidden" name="idClienteActivar" value="<%= cliente.getId() %>">                                             
                                                <input type="submit" value="Activar" class="btn btn-success" id="btnActivarCliente" name="btnActivarCliente">                                                
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% } %>
                        </form>
                    </td>
                </tr>
                <% } %>
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
<!-- Scripts de Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


<%@ include file="Footer.jsp" %>
