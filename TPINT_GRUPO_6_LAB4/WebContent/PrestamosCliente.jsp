<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<h3>Préstamos</h3>
	<div class="card">
	  <div class="card-header d-flex justify-content-end">
	  	<a class="btn btn-primary" href="ServletProvincia?cargarCampos=1.jsp">Solicitar</a>
	  </div>
	  <div class="card-body">
	    <table id="tablaPrestamosBanco" class="table table-striped" style="width:100%">
	        <thead>
	            <tr>
	                <th scope="col">#</th>
	                <th scope="col">Fecha solicitud</th>
	                <th scope="col">Importe de cuota</th>
	                <th scope="col">Cuotas</th>
	                <th scope="col">Prestamo solicitado</th>
	                <th scope="col">Monto a Pagar</th>
	                <th scope="col">Estado</th>
	                <th scope="col">Acciones</th>
	            </tr>
	        </thead>
	        <tbody>
                <tr>
                    <td>1</td>
                    <td>12/06/2024</td>
                    <td>$120.000,00</td>
                    <td>10</td>
                    <td>$1.000.000,00</td>
                    <td>$1.200.000,00</td>
                    <td>
                    	<span class='badge text-bg-success'>Aprobado</span>
                    </td>
                    <td class="d-flex justify-content-start align-items-center gap-2">
                    	<form action="ServletPrestamo" method="post">
                    		<button type="submit" id="btnVerPrestamo" class="btn btn-primary btn-sm">
                    			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-eye-fill" viewBox="0 0 16 16">
								  <path d="M10.5 8a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0"/>
								  <path d="M0 8s3-5.5 8-5.5S16 8 16 8s-3 5.5-8 5.5S0 8 0 8m8 3.5a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7"/>
								</svg>
                    		</button>
                    	</form>
                    	<!--  <form>
	                    	<input type="hidden" name="idPrestamo" value="">
	                        <input type="submit" value="Aprobar" id="btnAprobarPrestamo"  name="btnAprobarPrestamo" class="btn btn-outline-success btn-sm">
                    	</form>
	                    <form action="ServletCliente" method="post">
	                        <input type="hidden" name="idPrestamo" value="">
	                        <input type="submit" value="Rechazar" id="btnRechazarPrestamo"  name="btnRechazarPrestamo" class="btn btn-outline-danger btn-sm">
	                    </form>  -->
	                    <button type="button" class="btn btn-outline-success btn-sm" data-bs-toggle="modal" data-bs-target="#mdlAprobarPrestamo">
						  Pagar
						</button>
                    </td>
                </tr>
	        </tbody>
	    </table>
	  </div>
	</div>
	
<script>
	new DataTable('#tablaPrestamosBanco', {
		language: {
			url: 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json',
		},
	});
</script>