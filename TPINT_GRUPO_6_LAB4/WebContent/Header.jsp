
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="dominio.Usuario"%>
<%@ page import="dominio.Cliente"%>

<%
	HttpSession sessionLogueada = request.getSession(false);
	Usuario usuarioLogueado = null;
	Cliente cliente = null;

	if (sessionLogueada != null ) {
		usuarioLogueado = (Usuario) session.getAttribute("sessionUsuario");
	}

	if (usuarioLogueado == null || usuarioLogueado.getEstado() == false ) {
		response.sendRedirect("Login.jsp");
	}

	if (sessionLogueada != null) {
		cliente = (Cliente) session.getAttribute("cliente");
	}
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/x-icon" href="./images/piggy16x16.png" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<link href="https://cdn.datatables.net/2.0.8/css/dataTables.bootstrap5.css" rel="stylesheet">
<link href="https://cdn.datatables.net/buttons/3.0.2/css/buttons.bootstrap5.css" rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/StyleSheet.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PiggyBank</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://cdn.datatables.net/2.0.8/js/dataTables.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/2.0.8/js/dataTables.bootstrap5.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.0.2/js/dataTables.buttons.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.bootstrap5.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.print.min.js"></script>
</head>
<body class="d-flex flex-column">

	<header class="d-flex bg-body-tertiary bg-secondary py-2 ps-4 pe-4 align-items-center justify-content-between">
		<div class="d-flex gap-2 flex-row align-items-center">
			<svg xmlns="http://www.w3.org/2000/svg" width="45" height="45"
				fill="currentColor" class="bi bi-piggy-bank-fill"
				viewBox="0 0 16 16">
		  		<path
					d="M7.964 1.527c-2.977 0-5.571 1.704-6.32 4.125h-.55A1 1 0 0 0 .11 6.824l.254 1.46a1.5 1.5 0 0 0 1.478 1.243h.263c.3.513.688.978 1.145 1.382l-.729 2.477a.5.5 0 0 0 .48.641h2a.5.5 0 0 0 .471-.332l.482-1.351c.635.173 1.31.267 2.011.267.707 0 1.388-.095 2.028-.272l.543 1.372a.5.5 0 0 0 .465.316h2a.5.5 0 0 0 .478-.645l-.761-2.506C13.81 9.895 14.5 8.559 14.5 7.069q0-.218-.02-.431c.261-.11.508-.266.705-.444.315.306.815.306.815-.417 0 .223-.5.223-.461-.026a1 1 0 0 0 .09-.255.7.7 0 0 0-.202-.645.58.58 0 0 0-.707-.098.74.74 0 0 0-.375.562c-.024.243.082.48.32.654a2 2 0 0 1-.259.153c-.534-2.664-3.284-4.595-6.442-4.595m7.173 3.876a.6.6 0 0 1-.098.21l-.044-.025c-.146-.09-.157-.175-.152-.223a.24.24 0 0 1 .117-.173c.049-.027.08-.021.113.012a.2.2 0 0 1 .064.199m-8.999-.65a.5.5 0 1 1-.276-.96A7.6 7.6 0 0 1 7.964 3.5c.763 0 1.497.11 2.18.315a.5.5 0 1 1-.287.958A6.6 6.6 0 0 0 7.964 4.5c-.64 0-1.255.09-1.826.254ZM5 6.25a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0" />
			</svg>
			<h1 class="title m-0">Piggy Bank</h1>
		</div>
		<nav class="navbar navbar-expand-lg g-1 d-flex align-items-center gap-3">
			<div class="collapse navbar-collapse justify-content-center"
				id="navbarNavAltMarkup">
				<div class="navbar-nav d-flex flex-row gap-3">

					<%
						if (usuarioLogueado.getTipoUsuario().getId() == 1) {
					%>

					<a class="nav-link p-0" href="Index.jsp">Inicio</a> <a
						class="nav-link p-0" href="ServletCliente?Param=1">Clientes</a> <a
						class="nav-link p-0" href="Cuentas.jsp">Cuentas</a>

					<form class="m-0" action="ServletPrestamo" method="post">

						<button class="nav-link p-0" type="submit"
							name="btnPrestamosAdminBanco">Préstamos</button>
					</form>

					<a class="nav-link p-0" href="Reportes.jsp">Reportes</a>

					<%
						} else if (usuarioLogueado.getTipoUsuario().getId() == 2) {
					%>

					<form class="m-0" action="ServletIndex" method="post">
						<button id="btnInicio" class="nav-link p-0" name="indexClienteNav"
							type="submit">Inicio</button>
					</form>

					<a class="nav-link p-0" href="ServletCuenta?opcion=listar">Cuentas</a>
					
					<form class="m-0" action="ServletTransferencia" method="post">
						<button class="nav-link p-0" id="btnTransferencia" name="btnTransferencia" type="submit">Transferencias</button>
					</form>

					<form class="m-0" action="ServletPrestamo" method="post">
						<input type="hidden" name="idCliente"
							value="<%=cliente.getIdCliente()%>">
						<button class="nav-link p-0" type="submit"
							name="accederPrestamosCliente">Préstamos</button>
					</form>
					<%
						}
					%>

					<!-- <a class="nav-link p-0" href="Prestamos.jsp">Préstamos</a> -->
				</div>
			</div>
			<div class="dropdown d-flex align-items-center gap-3">
				<span class="userProfile">|</span> <span class="userProfile"><%=usuarioLogueado.getTipoUsuario().getId() == 1 ? usuarioLogueado.getUsuario()
					: cliente.getNombreApellido()%></span> <a href="#"
					class="d-block link-body-emphasis text-decoration-none dropdown-toggle show"
					data-bs-toggle="dropdown" aria-expanded="true"> <svg
						xmlns="http://www.w3.org/2000/svg" width="30" height="30"
						fill="currentColor" class="bi bi-person-circle"
						viewBox="0 0 16 16">
					  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0" />
					  <path fill-rule="evenodd"
							d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1" />
					</svg>
				</a>
				<ul class="dropdown-menu dropdown-menu-end mt-2">
					<%
						if (usuarioLogueado.getTipoUsuario().getId() == 2) {
					%>
					<li><a class="dropdown-item" href="ServletCliente?Param=2">Ver
							perfil</a></li>
					<%
						}
					%>
					<li><a class="dropdown-item"
						href="ServletLogout?action=logout">Cerrar sesión</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<main class="background-layer">
		<div class="container container--main pb-4 pt-4">