<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../Header.jsp" %>

<% if (usuarioLogueado.getTipoUsuario().getId()==1) { %>
    <%@ include file="PrestamosBanco.jsp" %>
<% } else if (usuarioLogueado.getTipoUsuario().getId()==2) { %>
    <%@ include file="PrestamosCliente.jsp" %>
<% } else { %>
    <p>Tipo de usuario no reconocido.</p>
<% } %>

<%@ include file="../Footer.jsp" %>