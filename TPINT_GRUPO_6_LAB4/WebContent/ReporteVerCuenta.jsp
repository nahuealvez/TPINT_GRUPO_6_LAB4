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
        <button class="btn btn-primary btn-sm" onclick="volverPantallaAnterior()"> Regresar</button>
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
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.4/css/dataTables.bootstrap5.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/3.0.2/css/buttons.dataTables.css">
<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.0.2/js/dataTables.buttons.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.dataTables.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/3.0.2/js/buttons.print.min.js"></script>
<script>
    $(document).ready(function() {
        var table = $('#tablaMovimientos').DataTable({
            language: {
                url: 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json'
            },
            dom: 'Bfrtip',
            buttons: [
                 'excel', 'pdf', 'print'
            ]
        });

        $.fn.dataTable.ext.search.push(
            function(settings, data, dataIndex) {
                var min = $('#minDate').val();
                var max = $('#maxDate').val();
                var date = new Date(data[1]);

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