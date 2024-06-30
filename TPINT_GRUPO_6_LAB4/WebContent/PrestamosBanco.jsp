<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<h3>Préstamos</h3>
	<div class="card">
	  <div class="card-header d-flex justify-content-end">
	  	<a class="btn btn-primary" href="ServletProvincia?cargarCampos=1.jsp">Agregar</a>
	  </div>
	  <div class="card-body">
	    <table id="tablaPrestamosBanco" class="table table-striped" style="width:100%">
	        <thead>
	            <tr>
	                <th scope="col">#</th>
	                <th scope="col">Fecha solicitud</th>
	                <th scope="col">Dni</th>
	                <th scope="col">Nombre completo</th>
	                <th scope="col">Importe solicitado</th>
	                <th scope="col">Cuotas</th>
	                <th scope="col">Estado</th>
	                <th scope="col">Acciones</th>
	            </tr>
	        </thead>
	        <tbody>
                <tr>
                    <td>1</td>
                    <td>12/06/2024</td>
                    <td>33781477</td>
                    <td>Alvez Nahuel</td>
                    <td>$1.000.000</td>
                    <td>48</td>
                    <td>
                    	<span class='badge text-bg-warning'>Pendiente</span>
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
						  Aprobar
						</button>
						<button type="button" class="btn btn-outline-danger btn-sm" data-bs-toggle="modal" data-bs-target="#mdlRechazarPrestamo">
						  Rechazar
						</button>
                    </td>
                </tr>
	        </tbody>
	    </table>
	  </div>
	</div>
	
	<div class="modal fade" id="mdlAprobarPrestamo" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<form>
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h1 class="modal-title fs-5" id="exampleModalLabel">Aprobar préstamo #</h1>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        ¿Confirma aprobar el préstamo?
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-success">Sí, apruebo</button>
		        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
		      </div>
		    </div>
		  </div>
		</form>
	</div>
	
	<div class="modal fade" id="mdlRechazarPrestamo" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<form>
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h1 class="modal-title fs-5" id="exampleModalLabel">Rechazar préstamo #</h1>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		        ¿Confirma rechazar el préstamo?
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-success">Sí, rechazo</button>
		        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
		      </div>
		    </div>
		  </div>
		</form>
	</div>

<!-- Scripts -->

<!-- <script>
	$(document).ready(function() {
		$('#btnAprobarPrestamo').click(function(e) {
			e.preventDefault();
			var idPrestamo = $(this).parent().find('idPrestamo').val();
			Swal.fire({
			  title: "¿Confirma aprobar el préstamo?",
			  text: "Esta acción no es reversible",
			  icon: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#3085d6",
			  cancelButtonColor: "#d33",
			  confirmButtonText: "Sí, apruebo el préstamo",
			  cancelButtonText: "Cancelar"
			}).then((result) => {
			  if (result.isConfirmed) {
				  aprobarPrestamo(idPrestamo);
			  }
			});
		})
	})
	
	function aprobarPrestamo(idPrestamo) {
		console.log('prestamoAprobado');
		$.ajax({
			type: 'POST',
			url: 'ServletPrestamo',
			async: true,
			success: function(response) {
			    Swal.fire({
			      title: "Aprobado",
			      text: "El préstamo fue aprobado",
			      icon: "success"
			    });
			},
			error: function(xhr, status, error) {
			    Swal.fire({
			      title: "No aprobado",
			      text: "Hubo un error al aprobar el préstamo",
			      icon: "error"
			    });
			}
		});
	}
	
	$(document).ready(function() {
		$('#btnRechazarPrestamo').click(function(e) {
			e.preventDefault();
			var idPrestamo = $(this).parent().find('idPrestamo').val();
			Swal.fire({
			  title: "¿Confirma rechazar el préstamo?",
			  text: "Esta acción no es reversible",
			  icon: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#3085d6",
			  cancelButtonColor: "#d33",
			  confirmButtonText: "Sí, rechazo el préstamo",
			  cancelButtonText: "Cancelar"
			}).then((result) => {
			  if (result.isConfirmed) {
				  rechazarPrestamo(idPrestamo);
			  }
			});
		})
	})
	
	function rechazarPrestamo(idPrestamo) {
		console.log('prestamoAprobado');
		$.ajax({
			type: 'POST',
			url: 'ServletPrestamo',
			async: true,
			success: function(response) {
			    Swal.fire({
			      title: "Aprobado",
			      text: "El préstamo fue rechazado",
			      icon: "success"
			    });
			},
			error: function(xhr, status, error) {
			    Swal.fire({
			      title: "No aprobado",
			      text: "Hubo un error al rechazar el préstamo",
			      icon: "error"
			    });
			}
		});
	}
</script>  -->

<script>
	new DataTable('#tablaPrestamosBanco', {
		language: {
			url: 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json',
		},
	});
</script>