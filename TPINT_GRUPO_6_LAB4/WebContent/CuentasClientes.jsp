<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dominio.Cliente"%>
<%@ page import="dominio.Cuenta"%>
<%@ page import="java.util.ArrayList"%>
<%@ include file="Header.jsp"%>

<%
    HttpSession sessionLogueada1 = request.getSession(false);
    Cliente clienteServlet = null;

    if (sessionLogueada1 != null) {
        clienteServlet = (Cliente) session.getAttribute("cliente");
    } else {
        response.sendRedirect("Login.jsp");
        return;
    }

    ArrayList<Cuenta> cuentasxCliente = (ArrayList<Cuenta>) request.getAttribute("cuentasxCliente");
%>

<h3>Mis Cuentas</h3>

<div class="card">
    <%
        if (request.getAttribute("txtMensajeCuenta") != null && request.getAttribute("claseMensajeCuenta") != null) {
            String mensaje = (String) request.getAttribute("txtMensajeCuenta");
            String claseMensaje = (String) request.getAttribute("claseMensajeCuenta");
    %>
    <div class="<%=claseMensaje%>" role="alert">
        <%=mensaje%>
    </div>
    <%
        }
    %>

    <div class="card-body">
        <%
            if (cuentasxCliente != null && !cuentasxCliente.isEmpty()) {
        %>
        <table id="tablaCuentas" class="table table-striped" style="width: 100%">
            <thead>
                <tr>
                    <th>Tipo de cuenta</th>
                    <th>Número</th>
                    <th>Fecha Creación</th>
                    <th>CBU</th>
                    <th>Saldo</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Cuenta cuenta : cuentasxCliente) {
                %>
                <tr>
                    <td><%=cuenta.getTipoCuenta().getDescripcion()%></td>
                    <td><%=cuenta.getId()%></td>
                    <td><%=cuenta.getFechaCreacion()%></td>
                    <td><%=cuenta.getCbu()%></td>
                    <td><%=cuenta.getSaldo()%></td>
                    <td><%=cuenta.isEstado() ? "<span class='badge text-bg-success'>Activo</span>" : "<span class='badge text-bg-danger'>Inactivo</span>" %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            } else {
        %>
        <p>No tiene cuentas asociadas.</p>
        <%
            }
        %>
    </div>
</div>

<!-- Scripts -->
<script>
    new DataTable('#tablaCuentas', {
        language: {
            url: 'https://cdn.datatables.net/plug-ins/2.0.8/i18n/es-AR.json',
        },
    });
</script>

<%@ include file="Footer.jsp"%>
