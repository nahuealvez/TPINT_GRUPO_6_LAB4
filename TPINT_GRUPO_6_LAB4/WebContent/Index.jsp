<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="Header.jsp"%>
	<%
    // Obtener el parámetro tipoUsuario
    String tipoUsuario = request.getParameter("tipoUsuario");
    
%>

<% if ("1".equals(tipoUsuario)) { %>
    <%@ include file="IndexBanco.jsp" %>
<% } else if ("2".equals(tipoUsuario)) { %>
    <%@ include file="IndexCliente.jsp" %>
<% } else { %>
    <p>Tipo de usuario no reconocido.</p>
<% } %>

<%@ include file="Footer.jsp"%>