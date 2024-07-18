<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dominio.Provincia" %>
<%@ page import="dominio.Localidad" %>
<%@ page import="java.util.ArrayList" %>

<%@ include file="Header.jsp" %>

<script >
	//Para validar que las contraseñas coincidan antes de enviar el formulario
	function validarContrasenias() {
    	var clave = document.getElementById("txtClave").value;
    	var validarClave = document.getElementById("txtValidarClave").value;
    		if (clave !== validarClave) 
    		{
        		alert("Las contraseñas no coinciden. Por favor, inténtelo de nuevo.");
        	return false;
    		}
    return true;
	}
	// limitar fecha maxima.
	document.addEventListener('DOMContentLoaded', (event) => {
    const today = new Date().toISOString().split('T')[0];
    document.getElementById('txtFechaNacimiento').setAttribute('max', today);
	});
</script>

	<%	
	
		if (usuarioLogueado.getId() == 2) {
			response.sendRedirect("SinPermisos.jsp");
		}
	
		ArrayList<Provincia> provincias = null;
		if (request.getAttribute("provincias") != null) {
			provincias = (ArrayList<Provincia>)request.getAttribute("provincias");
		}
		else {
			provincias = new ArrayList<Provincia>();
		}
		
		ArrayList<Localidad> localidades = null;
		
	%>
	
	<script>
	    $(document).ready(function() {
	        $('#ddlProvincia').change(function() {
	            var idProvincia = $(this).val();
	            if (idProvincia) {
	                $.ajax({
	                    type: 'GET',
	                    url: 'ServletLocalidad',
	                    data: { accion: 'agregar', idProvincia: idProvincia },
	                    success: function(response) {
	                        $('#ddlLocalidad').html(response);
	                    },
	                    error: function() {
	                        alert('Error al cargar localidades');
	                    }
	                });
	            } else {
	                $('#ddlLocalidad').html('<option selected disabled value="">Seleccione Localidad</option>');
	            }
	        });
	    });
	</script>

    <div class="container mt-2 p-1">
        <h3 class="mb-3">Agregar cliente</h3>
        <%--  Inicio de controles  --%>
        <form class="row g-2 needs-validation" action="ServletCliente" method="get" onsubmit="return validarContrasenias()" novalidate>
            <div class="col-md-6 position-relative">
                <label for="txtDni">Dni:</label>
                <input type="text" 
                	class="form-control 
                	form-control-sm" 
                	pattern="\d+" 
                	id="txtDni" 
                	name="txtDni"
                	value="" 
                	minlength="7" 
                	maxlength="8" 
                	placeholder="Ingrese N° de documento" 
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtCuil">Cuil:</label>
                <input type="text" 
                	class="form-control form-control-sm" 
                	id="txtCuil" 
                	name="txtCuil" 
                	pattern="\d+" 
                	minlength="11" 
                	maxlength="11" 
                	placeholder="Ingrese numero de cuil" 
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtNombre">Nombres:</label>
                <input type="text" 
                	class="form-control form-control-sm" 
                	id="txtNombre" 
                	name="txtNombre" 
                	pattern="[a-zA-ZáéíóúÁÉÍÓÚñÑ]+([ ]?[a-zA-ZáéíóúÁÉÍÓÚñÑ]+)*" 
                	minlength="1" 
                	maxlength="45" 
                	placeholder="Ingrese nombre/s" 
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtApellido">Apellidos:</label>
                <input type="text" 
                	class="form-control form-control-sm" 
                	id="txtApellido" 
                	name="txtApellido"  
                	pattern="[a-zA-ZáéíóúÁÉÍÓÚñÑ]+([ ]?[a-zA-ZáéíóúÁÉÍÓÚñÑ]+)*"  
                	minlength="1" maxlength="45" 
                	placeholder="Ingrese apellido/s" 
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtFecha">Fecha de Nacimiento:</label>
                <input type="date" 
                	class="form-control form-control-sm" 
                	id="txtFechaNacimiento" 
                	name="txtFechaNacimiento"
                	min="1910-01-01"
                	placeholder="Ingrese Fecha de nacimiento"
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="ddlGenero">Género:</label>
                <select class="form-select form-select-sm" 
                	id="ddlSexo" 
                	aria-label="Default select example" 
                	name="ddlSexo"
                required>
                    <option selected disabled value="">Seleccione su género</option>
                    <option value="M">Masculino</option>
                    <option value="F">Femenino</option>
                    <option value="X">No binario</option>
                </select>
            </div>
            <div class="col-md-6 position-relative">
                <label for="ddlNacionalidad">Nacionalidad:</label>
                <select class="form-select form-select-sm" 
                	id="ddlNacionalidad" 
                	name="ddlNacionalidad"  
                required>
                    <option selected disabled value="">Seleccione su Nacionalidad</option>
                    <option value="Argentina">Argentina</option>
                    <option value="Extranjera">Extranjera</option>
                </select>
            </div>
            <div class="col-md-6 position-relative">
                <!-- A esta hay que adaptarlo para que cargue una lista de Provincias -->
                <label for="ddlProvincia">Provincia:</label>
                <select class="form-select form-select-sm" 
                	id="ddlProvincia" 
                	aria-label="Default select example" 
                	name="ddlProvincia" 
                required>
                    <option selected disabled value="">Seleccione su Provincia</option>
                    <% for (Provincia provincia : provincias) { %>
                    	<option value="<%= provincia.getId() %>"><%= provincia.getNombre() %></option>
                    <% } %>
                </select>
            </div>
            <div class="col-md-6 position-relative">
                <label for="ddlLocalidad">Localidad:</label>
                <select class="form-select form-select-sm" 
                id="ddlLocalidad" 
                aria-label="Default select example" 
                name="ddlLocalidad" 
                required>
                    <option selected disabled value="">Seleccione Localidad</option>
                </select>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtDireccion">Dirección:</label>
                <input type="text" 
                	class="form-control form-control-sm" 
                	id="txtDireccion" 
                	name="txtDireccion"
                 	minlength="2" 
                 	maxlength="60"
                 	pattern="[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9]+([ ]?[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9]+)*" 
                 required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtEmail">Email:</label>
                <input type="text" 
                	class="form-control form-control-sm" 
                	placeholder="Ingrese correo electronico" 
                	id="txtEmail" 
                	name="txtEmail" 
                	placeholder="name@example.com" 
                	minlength="2" 
                	maxlength="80"
                	pattern="^[^\s@]+@[^\s@]+\.[^\s@]+$"  
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtTelefono">Teléfono:</label>
                <input type="text" 
                	class="form-control 
                	form-control-sm" 
                	id="txtTelefono" 
                	name="txtTelefono" 
                	pattern="\d+" 
                	minlength="8" 
                	maxlength="20" 
                	placeholder="Ingrese numero de celular" 
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtUsuario">Usuario:</label>
                <input type="text" 
                	class="form-control 
                	form-control-sm" 
                	id="txtUsuario" 
                	name="txtUsuario"
                	minlength="8" 
                	maxlength="14" 
                	pattern="^[A-Za-z0-9_]+$"
                	placeholder="Ingrese nuevo usuario" 
                required>
            </div>
            <div class="col-md-6 position-relative">
        	</div>
            <div class="col-md-6 position-relative">
                <label for="txtClave">Clave:</label>
                <input type="password" 
                	class="form-control 
                	form-control-sm" 
                	id="txtClave" 
                	name="txtClave"
                	minlength="8" 
                	maxlength="14" 
                	placeholder="entre 8 y 14 caracteres, un número y carácter especial(!@#$%^&*)" 
                	pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]+$" 
                required>
            </div>
            <div class="col-md-6 position-relative">
             	<label for="txtValidarClave">Validar Clave:</label>
            	<input type="password" 
                	class="form-control 
                	form-control-sm" 
                	id="txtValidarClave" 
                	name="txtValidarClave" 
                	minlength="8" 
                	maxlength="14" 
                	placeholder="entre 8 y 14 caracteres,un número y carácter especial(!@#$%^&*)" 
                	pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]+$" 
            	required>
            </div>
            <div class="col-md-6 mt-3">
            	<button class="btn btn-dark btn-sm" onclick="volverPantallaAnterior()"> Volver</button>
                <input type="submit" value="Agregar" id="btnAgregarCliente" name="btnAgregarCliente" class="btn btn-success btn-sm">
            </div>
        </form>
    </div>

<%@ include file="Footer.jsp" %>
