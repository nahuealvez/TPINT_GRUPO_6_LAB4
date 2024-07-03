<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="Header.jsp" %>

	<div class="container p-1">
        <h2 class="mb-3">Datos del cliente</h2>
        <div class="row g-2">
        <%--  Inicio de controles  --%>       
            <div class="col-md-6 position-relative">
                <label for="txtDni">Dni:</label>
                <input type="text" 
                	class="form-control 
                	form-control-sm"  
                	id="txtDni" 
                	name="txtDni"
                	value=""
                	readonly 
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtCuil">Cuil:</label>
                <input type="text" 
                	class="form-control form-control-sm" 
                	id="txtCuil" 
                	name="txtCuil"
                	value="" 
                	readonly 
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtNombre">Nombres:</label>
                <input type="text" 
                	class="form-control form-control-sm" 
                	id="txtNombre" 
                	name="txtNombre"
                	value=""                 	
                	readonly 
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtApellido">Apellidos:</label>
                <input type="text" 
                	class="form-control form-control-sm" 
                	id="txtApellido" 
                	name="txtApellido"
                	value=""   
                	readonly 
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtFecha">Fecha de Nacimiento:</label>
                <input type="date" 
                	class="form-control form-control-sm" 
                	id="txtFechaNacimiento" 
                	name="txtFechaNacimiento"
                	value=""   
                	readonly
                required>
            </div>
			<div class="col-md-6 position-relative">
			    <label for="ddlGenero">Género:</label>
			    <input class="form-control form-control-sm" 
			            id="ddlSexo"  
			            name="ddlSexo"
			            value=""
			            readonly
			            required>
			</div>    
			<div class="col-md-6 position-relative">
			    <label for="ddlNacionalidad">Nacionalidad:</label>
			    <input class="form-control form-control-sm" 
			            id="ddlNacionalidad" 
			            name="ddlNacionalidad"
			            value=""
			            readonly  
			            required>
			</div>
            <div class="col-md-6 position-relative">
                <!-- A esta hay que adaptarlo para que cargue una lista de Provincias -->
                <label for="ddlProvincia">Provincia:</label>
                <input class="form-control form-control-sm" 
                	id="ddlProvincia" 
                	aria-label="Default select example" 
                	name="ddlProvincia"
                	value=""
                	readonly 
                	required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="ddlLocalidad">Localidad:</label>
                <input class="form-control form-control-sm" 
                id="ddlLocalidad" 
                aria-label="Default select example" 
                name="ddlLocalidad"
                value=""
                readonly 
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtDireccion">Dirección:</label>
                <input type="text" 
                	class="form-control form-control-sm" 
                	id="txtDireccion" 
                	name="txtDireccion"       	
                	value=""
                 	readonly 
                 required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtEmail">Email:</label>
                <input type="text" 
                	class="form-control form-control-sm" 
                	id="txtEmail" 
                	name="txtEmail" 
                	value=""
                	readonly  
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtTelefono">Teléfono:</label>
                <input type="text" 
                	class="form-control 
                	form-control-sm" 
                	id="txtTelefono" 
                	name="txtTelefono"
                	value="" 
                	readonly 
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtUsuario">Usuario:</label>
                <input type="text" 
                	class="form-control 
                	form-control-sm" 
                	id="txtUsuario" 
                	name="txtUsuario"
                	value=""
                	required
                readonly>
            </div>
		</div>
<%@ include file="Footer.jsp" %>