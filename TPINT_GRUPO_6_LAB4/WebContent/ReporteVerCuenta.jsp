<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dominio.Movimiento" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>  

<%@ include file="Header.jsp" %>
		<h3 class="pt-3">Movimientos de la Cuenta</h3>
		
		<div class="card">
    <div class="card-header d-flex justify-content-end">
           <button class="btn btn-primary btn-sm" onclick="volverPantallaAnterior()">< Regresar</button>
    </div>
    <div class="card-body">
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
                    <td>Agregar el TipoMovimiento</td>
                    <td><%= movimiento.getConcepto() %></td>
                    <td><%= movimiento.getImporte() %></td>
                </tr>
              <% } %>
            </tbody>
        </table>
    </div>
</div>
		
</body>
</html>

<%@ include file="Footer.jsp" %>