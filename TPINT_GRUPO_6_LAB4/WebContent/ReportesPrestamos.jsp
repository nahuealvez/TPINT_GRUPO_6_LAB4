<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="dominio.Prestamo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat" %>
<%@page import="java.math.BigDecimal" %>

<%@ include file="Header.jsp" %>

<%
	int prestamosAprobados = 0;
	int prestamosRechazados = 0;
	int prestamosEvaluacion = 0; 
	
	BigDecimal sumaAprobados = BigDecimal.ZERO;
    BigDecimal sumaRechazados = BigDecimal.ZERO;
    BigDecimal sumaEnEvaluacion = BigDecimal.ZERO;
    
    if (request.getAttribute("sumaAprobadosSum") != null) {
        sumaAprobados = (BigDecimal) request.getAttribute("sumaAprobadosSum");
    }
	
    if (request.getAttribute("sumaRechazadosSum") != null) {
    	sumaRechazados = (BigDecimal) request.getAttribute("sumaRechazadosSum");
    }

    if (request.getAttribute("sumaEnEvaluacionSum") != null) {
    	sumaEnEvaluacion = (BigDecimal) request.getAttribute("sumaEnEvaluacionSum");
    }
	
	if (request.getAttribute("aprobadosCount") != null) {
		 prestamosAprobados = (int)request.getAttribute("aprobadosCount");
	}

	if (request.getAttribute("rechazadosCount") != null) {
		prestamosRechazados = (int)request.getAttribute("rechazadosCount");
	}

	if (request.getAttribute("evaluacionCount") != null) {
		 prestamosEvaluacion = (int)request.getAttribute("evaluacionCount");
	}
	
	

	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
%>


<h3 class="pt-3" >Reporte: Préstamos</h3>

<div class="container py-4 border rounded">
    <div class="row justify-content-center">
    
        <!-- Tarjeta 1 - En Evaluación -->
        <a class=" card-accesos col-md-4 text-center text-decoration-none text-dark" href="ServletReporte?param=2">
            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-search mb-2" viewBox="0 0 16 16">
                <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
            </svg>
            <h6 class="card-title">En Evaluación</h6>
            <p class="card-text mb-0"><%= prestamosEvaluacion %></p>
            <p class="card-text">$ <%= sumaEnEvaluacion %></p>
        </a>
        <!-- Fin Tarjeta 1 -->

        <!-- Tarjeta 2 - Aprobados -->
        <a class="card-accesos col-md-4 text-center text-decoration-none text-dark" href="ServletReporte?param=3">
            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-check mb-2" viewBox="0 0 16 16">
                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425z"/>
            </svg>
            <h6 class="card-title">Aprobados</h6>
            <p class="card-text mb-0"><%= prestamosAprobados %></p>
            <p class="card-text">$ <%= sumaAprobados %></p>
        </a>
        <!-- Fin Tarjeta 2 -->

        <!-- Tarjeta 3 - Rechazados -->
        <a class=" card-accesos col-md-4 text-center text-decoration-none text-dark" href="ServletReporte?param=4">
            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-x mb-2" viewBox="0 0 16 16">
                <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708"/>
            </svg>
            <h6 class="card-title">Rechazados</h6>
            <p class="card-text mb-0"><%= prestamosRechazados %></p>
            <p class="card-text">$ <%= sumaRechazados %></p>
        </a>
        <!-- Fin Tarjeta 3 -->
    </div>
</div>
<br>
<div class="card">
    <div class="card-header d-flex justify-content-end">
        <form action="ServletReporte" method="get">
            <input type="submit" value="Listar" class="btn btn-primary" id="btnListarPrestamo" name="btnListarPrestamo">
        </form>
    </div>
    <div class="card-body">
        <table id="example" class="table table-striped table-bordered" style="width:100%">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Fecha solicitud</th>
                    <th scope="col">Dni</th>
                    <th scope="col">Nombre completo</th>
                    <th scope="col">Importe solicitado</th>
                    <th scope="col">Cuotas</th>
                    <th scope="col">Estado</th>
                    
                </tr>
            </thead>
            <tbody>
     
    <% 
    ArrayList<Prestamo> listaPrestamos = null;
	if (request.getAttribute("listaPrestamos") != null) {
		listaPrestamos = (ArrayList<Prestamo>) request.getAttribute("listaPrestamos");
	} else {
		listaPrestamos = new ArrayList<>();
		%>
        <p class="alert alert-info lead">Presiona el botón Listar para cargar las Cuentas Corrientes .</p>
  		<%
	}
    for (Prestamo pre : listaPrestamos) { %>
    <tr>
        <td><%= pre.getId() %></td>
        <td><%= pre.getFechaSolicitud().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) %></td>
        <td><%= pre.getCliente().getDni() %></td>
        <td><%= pre.getCliente().getApellido() + " " + pre.getCliente().getNombre() %></td>
        <td><%= new DecimalFormat("#,##0.00").format(pre.getImportePedido()) %></td>
        <td><%= pre.getCuotas() %></td>
        <td>
    		<% if (pre.isEstadoValidacion() != null) { %>
        		<%= pre.isEstadoValidacion() ? 
            		"<span class='badge text-bg-success'>Aprobado</span>" : 
            		"<span class='badge text-bg-danger'>Rechazado</span>" %>
    		<% } else { %>
        		<span class='badge text-bg-warning'>En Evaluación</span>
    		<% } %>
		</td>
    </tr>
    <% } %>
</tbody>
        </table>
    </div>
</div>


<div class="col-md-6 mt-3">
	<a class="btn btn-dark btn-sm" href="Reportes.jsp">< Volver</a>
</div>


<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.4/css/dataTables.bootstrap5.min.css">

<!-- DataTables JS -->
<script type="text/javascript" src="https://cdn.datatables.net/2.0.8/js/dataTables.js"></script>

<!-- DataTables Buttons CSS -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/3.0.2/css/buttons.dataTables.css">

<!-- DataTables Buttons JS -->
<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.0.2/js/dataTables.buttons.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.dataTables.js"></script>

<!-- JSZip -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>

<!-- PDFMake -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>

<!-- DataTables Buttons HTML5 export -->
<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.html5.min.js"></script>

<!-- DataTables Buttons print -->
<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.print.min.js"></script>



<script >
$(document).ready(function() {
    $('#example').DataTable({
        dom: 'Bfrtip', 
        buttons: [
             'excel', 'pdf', 'print'
        ],
        language: {
        	url: 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json',            
          
            "buttons": {                             
                "excel": "Excel",
                "pdf": "PDF",
                "print": "Imprimir"
            }
        }

    });
    
});

</script>
<%@ include file="Footer.jsp" %>