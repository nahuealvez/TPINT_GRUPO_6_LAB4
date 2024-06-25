<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp"%>
	<%
    // Obtener el parámetro tipoUsuario
    String tipoUsuario = request.getParameter("tipoUsuario");
	//String tipoUsuario = "1";
	//String tipoUsuario = "2";
%>

<% if ("1".equals(tipoUsuario)) { %>
    <%@ include file="IndexBanco.jsp" %>
<% } else if ("2".equals(tipoUsuario)) { %>
    <%@ include file="IndexCliente.jsp" %>
<% } else { %>
    <p>Tipo de usuario no reconocido.</p>
<% } %>


<%@ include file="Footer.jsp"%>