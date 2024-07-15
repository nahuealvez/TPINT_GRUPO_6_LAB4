<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dominio.Cuenta" %>
<%@ page import="java.util.ArrayList" %>   
    
<%@ include file="Header.jsp" %>
<link rel="stylesheet" href="https://cdn.datatables.net/2.0.8/css/dataTables.dataTables.css">
<link rel="stylesheet" href="https://cdn.datatables.net/buttons/3.0.2/css/buttons.dataTables.css">

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
            		<td><%= cuentaCC.getFechaCreacion() %></td> 
            		<td><%= cuentaCC.getTipoCuenta().getDescripcion() %></td>
            		<td><%= cuentaCC.getCbu() %></td>
            		<td><%= cuentaCC.getSaldo() %></td> 
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


<!-- Scripts -->
	<script src="https://cdn.datatables.net/2.0.8/js/dataTables.js"></script>
    <script src="https://cdn.datatables.net/buttons/3.0.2/js/dataTables.buttons.js"></script>
    <script src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.dataTables.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>
    <script src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.html5.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.print.min.js"></script>

   
    <script>
        new DataTable('#tablaCuentas', {
            language: {
                url: 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json',
            },
            dom: 'Bfrtip', 
            buttons: [
                 'excel', 'pdf', 'print' 
            ]
        });
    </script>
<%@ include file="Footer.jsp" %>