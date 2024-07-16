<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dominio.Movimiento" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>  

<!-- <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap4.min.css"> -->
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> -->

<%@ include file="Header.jsp" %>
		<h3 class="pt-3">Movimientos</h3>
		
	<div class="card">
    <div class="card-header d-flex justify-content-end">
        <button class="btn btn-dark btn-sm" onclick="volverPantallaAnterior()">< Regresar</button>
    </div>
    <div class="card-body">
        <!-- Filtro de Fecha -->
        <div class="row g-3 mb-3">
            <div class="col-md-4">
                <label for="minDate" class="form-label btn-sm">Fecha Minima:</label>
                <input type="date" class="form-control form-control-sm" id="minDate" name="minDate">
            </div>
            <div class="col-md-4">
                <label for="maxDate" class="form-label btn-sm">Fecha Maxima:</label>
                <input type="date" class="form-control form-control-sm" id="maxDate" name="maxDate">
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
<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.4/css/dataTables.bootstrap5.min.css"> -->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/3.0.2/css/buttons.dataTables.css">
<script>
    $(document).ready(function() {
        var table = $('#tablaMovimientos').DataTable({
            language: {
                url: 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json'
            },
            layout: {
            	topStart: {
            		buttons: [
                        {
	                       	extend: 'excel',
	                       	text: 'Excel',
	                        className: 'btn btn-primary'
                        },
                        {
	                       	extend: 'pdf',
	                       	text: 'Pdf',
	                        className: 'btn-primary'
                        },
                        {
	                       	extend: 'print',
	                       	text: 'Imprimir',
	                        className: 'btn btn-primary'
                        }
                   ]
            	}
            }
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