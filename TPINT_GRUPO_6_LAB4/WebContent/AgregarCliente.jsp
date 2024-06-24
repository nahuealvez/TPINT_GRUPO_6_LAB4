<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="Header.jsp" %>

    <div class="container mt-2 p-1">
        <h2 class="mb-3">Agregar cliente</h2>
        <%--  Inicio de controles  --%>
        <form class="row g-2 needs-validation" action="ServletCliente" method="get" novalidate>
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
                	maxlength="9" 
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
                    <option value="1">Masculino</option>
                    <option value="2">Femenino</option>
                    <option value="3">No binario</option>
                </select>
            </div>
            <div class="col-md-6 position-relative">
                <label for="ddlNacionalidad">Nacionalidad:</label>
                <select class="form-select form-select-sm" 
                	id="ddlNacionalidad" 
                	name="ddlNacionalidad"  
                required>
                    <option selected disabled value="">Seleccione su Nacionalidad</option>
                    <option value="1">Argentino</option>
                    <option value="2">Extranjero</option>
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
                    <option value="1">Buenos Aires</option>
                    <option value="2">Buenos Aires-GBA</option>
                    <option value="3">Capital Federal</option>
                    <option value="4">Catamarca</option>
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
                    <option value="1">25 de Mayo</option>
                    <option value="2">3 de febrero</option>
                    <option value="3">A. Alsina</option>
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
                	pattern="[A-Za-z0-9_]+" 
                	placeholder="Ingrese nuevo usuario" 
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtClave">Clave:</label>
                <input type="text" 
                	class="form-control 
                	form-control-sm" 
                	id="txtClave" 
                	name="txtClave"
                	minlength="8" 
                	maxlength="14" 
                	placeholder="entre 8 y 14 caracteres,una mayúscula,un número y carácter especial(!@#$%^&*)" 
                	pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]+$" 
                required>
            </div>
            <div class="col-md-6 mt-3">
                <input type="submit" value="Agregar" id="btnAgregarCliente" name="btnAgregarCliente" class="btn btn-outline-primary btn-sm">
            </div>
        </form>
        <%--  Fin de controles  --%>
    </div>
    <br>

<%@ include file="Footer.jsp" %>
