<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 

<%@ include file="Header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%--  bootstrap5 --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
    <%--  Inicio de menú --%>
    <nav class="navbar navbar-expand-lg bg-body-tertiary bg-secondary g-1">
        <div class="container-fluid">
            <img src="" alt="" class="img-fluid mb-3" style="max-width: 5%; height: auto;">
            <div class="collapse navbar-collapse justify-content-center" id="navbarNavAltMarkup">
                <div class="navbar-nav">    
                    <a class="nav-link" href="#">Inicio</a>
                    <a class="nav-link" href="FrontClientes.jsp">Clientes</a>
                    <a class="nav-link" href="#">Cuentas</a>
                    <a class="nav-link" href="#">Préstamos</a>
                </div>
<%--                 
            </div>
        </div>
    </nav>
    <%--  Fin de menú  --%>
    <%--  Controles  --%>
    <div class="container mt-2 border border-dark rounded p-1 bg-body-tertiary">
        <div class="ms-8" style="margin-left: 90px;">
            <h3>Agregar Cliente</h3>
        </div>
        <%--  Inicio de controles  --%>
        <form class="row g-1 needs-validation" action="servletUsurio" method="get" novalidate>
            <div class="col-md-6 position-relative">
                <label for="txtDni">Dni:</label>
                <input type="text" 
                	class="form-control 
                	form-control-sm" 
                	pattern="\d+" 
                	id="txtDni" 
                	name="Dni" 
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
                	name="Cuil" 
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
                	name="Nombre" 
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
                	name="Apellido"  
                	pattern="[a-zA-ZáéíóúÁÉÍÓÚñÑ]+([ ]?[a-zA-ZáéíóúÁÉÍÓÚñÑ]+)*"  
                	minlength="1" maxlength="45" 
                	placeholder="Ingrese apellido/s" 
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtFecha">Fecha de Nacimiento:</label>
                <input type="date" 
                	class="form-control form-control-sm" 
                	id="txtFecha" 
                	name="Fecha" 
                	placeholder="Ingrese Fecha de nacimiento"
                required>
            </div>
            <div class="col-md-6 position-relative">
                <label for="ddlGenero">Género:</label>
                <select class="form-select form-select-sm" 
                	id="ddlGenero" 
                	aria-label="Default select example" 
                	name="sexo" 
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
                	name="nacionalidad" 
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
                	name="provincia" 
                required>
                    <option selected disabled value="">Seleccione su Provincia</option>
                    <option value="1">Buenos Aires</option>
                    <option value="2">Otra</option>
                </select>
            </div>
            <div class="col-md-6 position-relative">
                <label for="ddlLocalidad">Localidad:</label>
                <select class="form-select form-select-sm" id="ddlLocalidad" aria-label="Default select example" name="localidad" required>
                    <option selected disabled value="">Seleccione Localidad</option>
                    <option value="1">Tigre</option>
                    <option value="2">Otra</option>
                </select>
            </div>
            <div class="col-md-6 position-relative">
                <label for="txtDireccion">Dirección:</label>
                <input type="text" 
                	class="form-control form-control-sm" 
                	id="txtDireccion" 
                	name="Direccion"
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
                	name="Email" 
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
                	name="telefono" 
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
                	name="usuario" 
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
                	name="clave" 
                	minlength="8" 
                	maxlength="14" 
                	placeholder="entre 8 y 14 caracteres,una mayúscula,un número y carácter especial(!@#$%^&*)" 
                	pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]+$" 
                required>
            </div>
            <div class="col-md-6">
                <br>
                <input type="submit" value="Agregar" id="btnAgregarCliente" class="btn btn-outline-primary btn-sm">
                <input type="submit" value="Modificar" id="btnModificarCliente" class="btn btn-outline-secondary btn-sm">
                <input type="submit" value="Eliminar" id="btnEliminarCliente" class="btn btn-outline-danger btn-sm">
                <label for="mensaje" class="form-label">Agregar un mensaje al usuario</label>
            </div>
        </form>
        <%--  Fin de controles  --%>
    </div>
    <br>

    <!-- Pie de página -->
    <footer class="footer mt-auto py-3 bg-dark">
        <div class="container text-center">
            <span class="text-white fw-bold display-14">Piggy bank -</span>
            <span class="text-white">Todos los derechos reservados - 2024. </span>
        </div>
    </footer>
    <!-- Fin de Pie de página -->

    <!-- Scripts -->
    <!-- Scripts de Bootstrap  -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script>
        //JavaScript para activar la validación de Bootstrap
        (function() {
            'use strict';
            window.addEventListener('load', function() {
                var forms = document.getElementsByClassName('needs-validation');
                Array.prototype.filter.call(forms, function(form) {
                    form.addEventListener('submit', function(event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>
</body>
</html>
