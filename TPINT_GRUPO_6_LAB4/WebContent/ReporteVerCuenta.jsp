<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dominio.Movimiento" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>  

<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .dataTables_wrapper .dataTables_filter {
            float: none;
    		text-align: right; /* Alinea el buscador a la derecha */
        }
        .dataTables_wrapper .dataTables_paginate .paginate_button {
            padding: .5rem 1rem; /* Añade padding para estilizar los botones */
        }
    </style>

<%@ include file="Header.jsp" %>
		<h3 class="pt-3">Movimientos de la Cuenta</h3>
		
	<div class="card">
    <div class="card-header d-flex justify-content-end">
        <button class="btn btn-primary btn-sm" onclick="volverPantallaAnterior()">< Regresar</button>
    </div>
    <div class="card-body">
        <!-- Filtro de Fecha -->
        <div class="row g-3 mb-3">
            <div class="col-md-4">
                <label for="minDate" class="form-label btn-sm">Fecha Minima:</label>
                <input type="date" class="form-control btn-sm " id="minDate" name="minDate">
            </div>
            <div class="col-md-4">
                <label for="maxDate" class="form-label btn-sm">Fecha Maxima:</label>
                <input type="date" class="form-control btn-sm" id="maxDate" name="maxDate">
            </div>
            <div class="col-md-4 d-flex align-items-end">
                <button id="clearFilter" type="button" class="btn btn-secondary btn-sm">Limpiar Fecha</button>
            </div>
        </div>
        <hr>
        <!-- Tabla de Movimientos -->
        <table id="tablaMovimientos" class="table table-striped" style="width:100%">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Fecha</th>
                    <th scope="col">Tipo de Movimiento</th>
                    <th scope="col">Concepto</th>
                    <th scope="col">Importe</th>
                </tr>
            </thead>
            <tbody>
            
            <%
               ArrayList<Movimiento> listaMovimientos = null;
               if (request.getAttribute("listaMovimiento") != null) {
                    listaMovimientos = (ArrayList<Movimiento>) request.getAttribute("listaMovimiento");
                } else {
                    listaMovimientos = new ArrayList<>();
                    %>
                    <p class="alert alert-info lead">No se encontraron movimientos para esta cuenta.</p>
                    <%
                }
                

                for (Movimiento movimiento : listaMovimientos) {
                   
            %>
                <tr>
                    <td><%= movimiento.getId() %></td>
                    <td><%= movimiento.getFecha() %></td>
                    <td><%= movimiento.getTipoMovimiento().getDescripcion() %></td>
                    <td><%= movimiento.getConcepto() %></td>
                    <td><%= movimiento.getImporte() %></td>
                </tr>
              <% } %>
            </tbody>
        </table>
    </div>
</div>
		

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap4.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
        $(document).ready(function() {
            var table = $('#tablaMovimientos').DataTable({
                language: {
                    url: 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json',
                },
                dom: '<"d-flex justify-content-between"lfr>tip', // Cambia el orden de los elementos
                pagingType: 'simple_numbers', // Cambia el estilo de la paginación
                drawCallback: function () {
                    $('ul.pagination').addClass('pagination-sm');
                    $('ul.pagination').addClass('justify-content-center'); // Centra la paginación
                }
            });

            $.fn.dataTable.ext.search.push(
                function(settings, data, dataIndex) {
                    var min = $('#minDate').val();
                    var max = $('#maxDate').val();
                    var date = new Date(data[1]); // Asumiendo que la fecha está en la segunda columna

                    if (
                        (min === "" || new Date(min) <= date) &&
                        (max === "" || date <= new Date(max))
                    ) {
                        return true;
                    }
                    return false;
                }
            );

            $('#minDate, #maxDate').on('change', function() {
                table.draw();
            });

            $('#clearFilter').on('click', function() {
                $('#minDate').val('');
                $('#maxDate').val('');
                table.draw();
            });
        });
</script>

<%@ include file="Footer.jsp" %>