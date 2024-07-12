<%@page import="dominio.Cliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dominio.Cuenta" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.String" %>

<%
	ArrayList<Cuenta> cuentas = null;

	if (request.getAttribute("cuentasPorCliente") != null) {
		cuentas = (ArrayList<Cuenta>)request.getAttribute("cuentasPorCliente");
	}
	else {
		cuentas = new ArrayList<Cuenta>();
	}
	Cliente clienteLogueado = (Cliente) session.getAttribute("cliente");
%>
	
	<div class="d-flex flex-column gap-3">
		<div class="d-flex flex-column gap-4">
			<section>
				<h3 class="pb-2">Cuentas</h3>
				<div class="d-flex gap-3">
					<% if ( cuentas.size() > 0 ) { 
						for (Cuenta c : cuentas) { %>
							<div class="card" style="width: 18rem;">
							  <div class="card-body">
							    <h5 class="card-title"><%= c.getTipoCuenta().getDescripcion() %></h5>
							    <h6 class="card-subtitle mb-2 text-body-secondary">Nro de cuenta: <%= c.getId() %></h6>
							    <p class="card-text">$<%= c.getSaldo() %></p>
							  </div>
							  <div class="card-footer">
							  	<a href="#" class="card-link d-flex gap-2 align-items-center m-0">
							    	<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-coin" viewBox="0 0 16 16">
									  <path d="M5.5 9.511c.076.954.83 1.697 2.182 1.785V12h.6v-.709c1.4-.098 2.218-.846 2.218-1.932 0-.987-.626-1.496-1.745-1.76l-.473-.112V5.57c.6.068.982.396 1.074.85h1.052c-.076-.919-.864-1.638-2.126-1.716V4h-.6v.719c-1.195.117-2.01.836-2.01 1.853 0 .9.606 1.472 1.613 1.707l.397.098v2.034c-.615-.093-1.022-.43-1.114-.9zm2.177-2.166c-.59-.137-.91-.416-.91-.836 0-.47.345-.822.915-.925v1.76h-.005zm.692 1.193c.717.166 1.048.435 1.048.91 0 .542-.412.914-1.135.982V8.518z"/>
									  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
									  <path d="M8 13.5a5.5 5.5 0 1 1 0-11 5.5 5.5 0 0 1 0 11m0 .5A6 6 0 1 0 8 2a6 6 0 0 0 0 12"/>
									</svg>
							    	Ver movimientos
							    </a>
							  </div>
							</div>
						<% }
					   }
					   else { %>
							<div class="alert alert-secondary">
								En el momento, no existen cuentas activas asignadas al cliente.
							</div>
				       <% } %>
			</section>
			<section>		
				<h3 class="mb-0">Accesos rápidos</h3>
				<div class="d-flex pt-3 gap-3">
					<a href="Transferencias.jsp">
						<button class="card-accesos" type="button">
							<svg xmlns="http://www.w3.org/2000/svg" width="55" height="55" fill="currentColor" class="bi bi-arrow-left-right" viewBox="0 0 16 16">
								<path fill-rule="evenodd" d="M1 11.5a.5.5 0 0 0 .5.5h11.793l-3.147 3.146a.5.5 0 0 0 .708.708l4-4a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 11H1.5a.5.5 0 0 0-.5.5m14-7a.5.5 0 0 1-.5.5H2.707l3.147 3.146a.5.5 0 1 1-.708.708l-4-4a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 4H14.5a.5.5 0 0 1 .5.5"/>
							</svg>
							<p>Transferencias</p>
						</button>
					</a>
					

					<form action="ServletPrestamo" method="post">
						<input type="hidden" name="idCliente" value="<%=clienteLogueado.getIdCliente()%>">
						<button class="card-accesos" type="submit" name="accederPrestamosCliente">
							<svg xmlns="http://www.w3.org/2000/svg" width="55" height="55" fill="currentColor" class="bi bi-people" viewBox="0 0 16 16">
								<path d="M15 14s1 0 1-1-1-4-5-4-5 3-5 4 1 1 1 1zm-7.978-1L7 12.996c.001-.264.167-1.03.76-1.72C8.312 10.629 9.282 10 11 10c1.717 0 2.687.63 3.24 1.276.593.69.758 1.457.76 1.72l-.008.002-.014.002zM11 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4m3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0M6.936 9.28a6 6 0 0 0-1.23-.247A7 7 0 0 0 5 9c-4 0-5 3-5 4q0 1 1 1h4.216A2.24 2.24 0 0 1 5 13c0-1.01.377-2.042 1.09-2.904.243-.294.526-.569.846-.816M4.92 10A5.5 5.5 0 0 0 4 13H1c0-.26.164-1.03.76-1.724.545-.636 1.492-1.256 3.16-1.275ZM1.5 5.5a3 3 0 1 1 6 0 3 3 0 0 1-6 0m3-2a2 2 0 1 0 0 4 2 2 0 0 0 0-4"/>
							</svg>
							<p>Préstamos</p>
						</button>
					</form>
				</div>
			</section>
		</div>
	</div>  
	
	