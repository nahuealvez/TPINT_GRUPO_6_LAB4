<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="Header.jsp" %>
<h3 class="pt-3" >Reportes: Prestamo</h3>

<div class="card">
    <div class="card-header d-flex justify-content-end">
	  	<form action="ServletReporte" method="get">
	  
	  	<input type="submit" value="Listar" class="btn btn-primary" id="btnListarPrestamo" name="btnListarPrestamo">
	</form>  	
    </div>
    <div class="card-body">
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
                <tr>
                    <td>1</td>
                    <td>12345678</td>
                    <td>usuario123</td>
                    <td>Nombre</td>
                    <td>Apellido</td>
                    <td>Activo</td>
                    <td>
                        <button class="btn btn-primary">Ver Movimientos</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>



<div class="col-md-6 mt-3">
	<a class="btn btn-dark btn-sm" href="Reportes.jsp">< Volver</a>
</div>
<%@ include file="Footer.jsp" %>