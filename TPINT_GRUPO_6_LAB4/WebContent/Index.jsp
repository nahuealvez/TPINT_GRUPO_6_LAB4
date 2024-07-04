<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="Header.jsp"%>

<% if (usuarioLogueado.getTipoUsuario().getId()==1) { %>
	<h2 class="titleBienvenida mb-3">Bienvenido/a, <%= usuarioLogueado.getUsuario() %></h2>
    <%@ include file="IndexBanco.jsp" %>
<% } else if (usuarioLogueado.getTipoUsuario().getId()==2) { %>
	<h2 class="titleBienvenida mb-3">Bienvenido/a, <%= cliente.getNombreApellido() %></h2>
    <%@ include file="IndexCliente.jsp" %>
<% } else { %>
    <p>Tipo de usuario no reconocido.</p>
<% } %>


<%@ include file="Footer.jsp"%>