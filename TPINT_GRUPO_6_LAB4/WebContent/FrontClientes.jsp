<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <%--Hoja de estilo --%>
    <style type="text/css">
	<jsp:include page="css\StyleSheet.css"></jsp:include>
	</style>
    <%-- Para DataTables --%>    
    <link rel="stylesheet" href="https://cdn.datatables.net/2.0.8/css/dataTables.dataTables.min.css">
   	<link rel="stylesheet" href="https://cdn.datatables.net/2.0.8/js/dataTables.min.js">
   	
    <title>Clientes</title>
</head>
<body>
<div class="encabezado"> </div>
<%--  Inicio de menú --%>
    <nav class="navbar navbar-expand-lg bg-body-tertiary bg-secondary g-1">
        <div class="container-fluid">
            <img src="" alt="" class="img-fluid mb-3" style="max-width: 5%; height: auto;">
            <div class="collapse navbar-collapse justify-content-center" id="navbarNavAltMarkup">
                <div class="navbar-nav">    
                    <a class="nav-link" href="#">Inicio</a>
                    <a class="nav-link" href="FronClientes.jsp">Clientes</a>
                    <a class="nav-link" href="#">Cuentas</a>
                    <a class="nav-link" href="#">Préstamos</a>
                </div>
            </div>
        </div>
    </nav>
<%--  Fin de menú  --%>
<div class="ms-8" style="margin-left: 130px;">
    <h3>Clientes</h3>
</div>
<br>
<%--  Controles  --%>
<div style="margin-left: 1150px;">
    <a class="btn btn-primary" href="AgregarCliente.jsp">Agregar</a>
</div>
<%--  Tabla Controles  --%>
<%-- 
<div class="container text-center">
<hr>
  <div class="row">
    <div class="col font-weight-bold">#</div>
    <div class="col font-weight-bold">Dni</div>
    <div class="col">Usuario</div>
    <div class="col">Apellido</div>
    <div class="col">Nombre</div>
    <div class="col">email</div>
    <div class="col">Acciones</div>
  </div>
<hr>
</div>
--%>

<%-- Fin de tabla Controles  --%>
<div class="container mt-2 border border-dark rounded p-1 bg-body-tertiary">
<table id="myTable" class="display"><font></font>
    <thead>
        <tr>
            <th>#</th><font></font>
            <th>Dni</th><font></font>
            <th>Usuario</th><font></font>
            <th>Nombre</th><font></font>
            <th>Apellido</th><font></font>
            <th>fede@email.com</th><font></font>
            <th>Acciones</th><font></font>
        </tr>
    </thead>
    <tbody><font></font>
        <tr><font></font>
           <th>02</th><font></font>
            <th>1122334455</th><font></font>
            <th>TomasDoc17@</th><font></font>
            <th>Tomas</th><font></font>
            <th>Docteur</th><font></font>
            <th>tomas2@gmail.com</th><font></font>
            <th><input type="submit" value="Modificar" id="btnModificar" class="btn btn-outline-primary btn-sm"></th><font></font>
        </tr><font></font>
        <tr><font></font>
           <th>01</th><font></font>
            <th>1122334455</th><font></font>
            <th>FedeDoc17@</th><font></font>
            <th>Federico</th><font></font>
            <th>Docteur</th><font></font>
            <th>email</th><font></font>
            <th><input type="submit" value="Modificar" id="btnModificar" class="btn btn-outline-primary btn-sm"></th><font></font>
        </tr><font></font>
    </tbody><font></font>
</table><font></font>
</div>
<br>
<!-- Pie de página -->
<footer class="footer mt-auto py-3 bg-dark">
    <div class="container text-center">
        <span class="text-white fw-bold display-14">Piggy bank -</span>
        <span class="text-white">Todos los derechos reservados - 2024. </span>
    </div>
</footer>
<!-- Scripts  -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/2.0.8/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<%-- Para DataTables --%>
<script src="https://cdn.datatables.net/2.0.8/js/dataTables.js"></script>
<script>
    $(document).ready(function () {
        new DataTable('#myTable');
    });
</script>

</body>
</html>
