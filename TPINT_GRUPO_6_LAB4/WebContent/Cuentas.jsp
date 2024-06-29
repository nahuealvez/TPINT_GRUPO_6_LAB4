<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dominio.Cliente" %>
<%@ page import="dominio.Cuenta" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="Header.jsp"%>

<h3>Cuentas</h3>

<div class="card">
  <div class="card-header d-flex justify-content-between align-items-center">
    <h4>Buscar Cliente por DNI</h4>
    <form action="ServletCuenta" method="post" class="d-flex gap-3">
      <input type="text" class="form-control" placeholder="Ingrese DNI" name="dniCliente">
      <button type="submit" name="buscarCliente" class="btn btn-primary">Buscar</button>
    </form>
  </div>
  <div class="card-body">
    <% 
    Cliente clienteServlet = (Cliente) request.getAttribute("clienteServlet");
    Boolean errorBusqueda = (Boolean) request.getAttribute("errorBusqueda");
    
    if (clienteServlet != null) { %>
    
   <div class="card-header d-flex justify-content-end">
	 <a class="btn btn-primary" href="ServletCuenta?accion=agregar&dniCliente=<%= clienteServlet.getDni() %>">Agregar</a>
	  </div>
	  
      <div class="alert alert-success" role="alert">
        Cliente encontrado:
        <ul>
          <li><strong>Nombre:</strong> <%= clienteServlet.getNombre() %></li>
          <li><strong>Apellido:</strong> <%= clienteServlet.getApellido() %></li>
          <li><strong>DNI:</strong> <%= clienteServlet.getDni() %></li>
        </ul>
      </div>
      
	  
	  
      <h5>Cuentas del Cliente</h5>
      
      <% 
      ArrayList<Cuenta> cuentasxCliente = (ArrayList<Cuenta>) request.getAttribute("cuentasxCliente");
      if (cuentasxCliente != null && !cuentasxCliente.isEmpty()) { %>
        <table id="tablaCuentas" class="table table-striped" style="width:100%">
            <thead>
                <tr>
                    <th>Tipo de cuenta</th>
                    <th>Número</th>
                    <th>Fecha Creación</th>
                    <th>CBU</th>
                    <th>Saldo</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% 
                for (Cuenta cuenta : cuentasxCliente) { %>
                    <tr>
                        <td><%= cuenta.getTipoCuenta().getDescripcion() %></td>
                        <td><%= cuenta.getId() %></td>
                        <td><%= cuenta.getFechaCreacion() %></td>
                        <td><%= cuenta.getCbu() %></td>
                        <td><%= cuenta.getSaldo() %></td>
                         <td>
	                    	<%= cuenta.isEstado() ? "<span class='badge text-bg-success'>Activo</span>" : "<span class='badge text-bg-danger'>Inactivo</span>" %>
	                    </td>
	                    
                        <td class="d-flex justify-content-center align-items-center gap-2">
                            <form action="ServletCuenta" method="post">
                                <input type="hidden" name="idCuenta" value="<%= cuenta.getId() %>">
                                <input type="submit" value="Modificar" name="btnModificarCuenta" class="btn btn-outline-primary btn-sm">
                            </form>
                            <form action="ServletCliente" method="post">
                                <input type="hidden" name="idCuenta" value="<%= cuenta.getId() %>">
                                <input type="submit" value="Desactivar" name="btnDesactivarCuenta" class="btn btn-outline-danger btn-sm">
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
      <% } else { %>
        <div class="alert alert-info" role="alert">
          No se encontraron cuentas para este cliente.
        </div>
      <% } %>
      
    <% } else if (errorBusqueda != null && errorBusqueda) { %>
      <div class="alert alert-danger" role="alert">
        No se encontró ningún cliente con ese DNI.
      </div>
    <% } %>
    
  </div>
</div>

<!-- Scripts -->
<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/plug-ins/1.13.1/i18n/es-ES.json"></script>
<script>
  $(document).ready(function() {
      $('#tablaCuentas').DataTable({
          language: {
              url: 'https://cdn.datatables.net/plug-ins/1.13.1/i18n/es-ES.json'
          }
      });
  });
</script>

<%@ include file="Footer.jsp" %>

