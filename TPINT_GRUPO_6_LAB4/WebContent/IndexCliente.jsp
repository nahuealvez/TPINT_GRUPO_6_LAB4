<%@page import="dominio.Cliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dominio.Cuenta" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.String" %>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>

<%
	ArrayList<Cuenta> cuentas = null;

	if (request.getAttribute("cuentasPorCliente") != null) {
		cuentas = (ArrayList<Cuenta>)request.getAttribute("cuentasPorCliente");
	}
	else {
		cuentas = new ArrayList<Cuenta>();
	}
	Cliente clienteLogueado = (Cliente) session.getAttribute("cliente");
	
    NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
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
							    <p class="card-text"><%= formatoMoneda.format(c.getSaldo()) %></p>
							  </div>
							  <div class="card-footer">
							  	<form action="ServletReporte" method="get">
							  		<input type="hidden" value="<%=c.getId() %>" name="IdCuentaCte">
							  		<button type="submit" class="card-link d-flex gap-2 align-items-center m-0 btn btn-link p-0" style="text-decoration: none;" id="btnVerMovimientosCta" value="1" name="btnVerMovimientosCta">
							  			<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-coin" viewBox="0 0 16 16">
										  <path d="M5.5 9.511c.076.954.83 1.697 2.182 1.785V12h.6v-.709c1.4-.098 2.218-.846 2.218-1.932 0-.987-.626-1.496-1.745-1.76l-.473-.112V5.57c.6.068.982.396 1.074.85h1.052c-.076-.919-.864-1.638-2.126-1.716V4h-.6v.719c-1.195.117-2.01.836-2.01 1.853 0 .9.606 1.472 1.613 1.707l.397.098v2.034c-.615-.093-1.022-.43-1.114-.9zm2.177-2.166c-.59-.137-.91-.416-.91-.836 0-.47.345-.822.915-.925v1.76h-.005zm.692 1.193c.717.166 1.048.435 1.048.91 0 .542-.412.914-1.135.982V8.518z"/>
										  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14m0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16"/>
										  <path d="M8 13.5a5.5 5.5 0 1 1 0-11 5.5 5.5 0 0 1 0 11m0 .5A6 6 0 1 0 8 2a6 6 0 0 0 0 12"/>
										</svg>
								    	Ver movimientos
							  		</button>
							  	</form>
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
					<form action="ServletTransferencia" method="post">
						<input type="hidden" name="idCliente" value="<%=clienteLogueado.getIdCliente()%>">
						<button class="card-accesos" type="submit" id="btnTransferencia" name="btnTransferencia">
							<svg xmlns="http://www.w3.org/2000/svg" width="55" height="55" fill="currentColor" class="bi bi-arrow-left-right" viewBox="0 0 16 16">
								<path fill-rule="evenodd" d="M1 11.5a.5.5 0 0 0 .5.5h11.793l-3.147 3.146a.5.5 0 0 0 .708.708l4-4a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 11H1.5a.5.5 0 0 0-.5.5m14-7a.5.5 0 0 1-.5.5H2.707l3.147 3.146a.5.5 0 1 1-.708.708l-4-4a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 4H14.5a.5.5 0 0 1 .5.5"/>
							</svg>
							<p>Transferencias</p>
						</button>
					</form>
					<form action="ServletPrestamo" method="post">
						<input type="hidden" name="idCliente" value="<%=clienteLogueado.getIdCliente()%>">
						<button class="card-accesos" type="submit" name="accederPrestamosCliente">
							<svg xmlns="http://www.w3.org/2000/svg" width="55" height="55" fill="currentColor" class="bi bi-cash-coin" viewBox="0 0 16 16">
							  <path fill-rule="evenodd" d="M11 15a4 4 0 1 0 0-8 4 4 0 0 0 0 8m5-4a5 5 0 1 1-10 0 5 5 0 0 1 10 0"/>
							  <path d="M9.438 11.944c.047.596.518 1.06 1.363 1.116v.44h.375v-.443c.875-.061 1.386-.529 1.386-1.207 0-.618-.39-.936-1.09-1.1l-.296-.07v-1.2c.376.043.614.248.671.532h.658c-.047-.575-.54-1.024-1.329-1.073V8.5h-.375v.45c-.747.073-1.255.522-1.255 1.158 0 .562.378.92 1.007 1.066l.248.061v1.272c-.384-.058-.639-.27-.696-.563h-.668zm1.36-1.354c-.369-.085-.569-.26-.569-.522 0-.294.216-.514.572-.578v1.1zm.432.746c.449.104.655.272.655.569 0 .339-.257.571-.709.614v-1.195z"/>
							  <path d="M1 0a1 1 0 0 0-1 1v8a1 1 0 0 0 1 1h4.083q.088-.517.258-1H3a2 2 0 0 0-2-2V3a2 2 0 0 0 2-2h10a2 2 0 0 0 2 2v3.528c.38.34.717.728 1 1.154V1a1 1 0 0 0-1-1z"/>
							  <path d="M9.998 5.083 10 5a2 2 0 1 0-3.132 1.65 6 6 0 0 1 3.13-1.567"/>
							</svg>
							<p>Préstamos</p>
						</button>
					</form>
				</div>
			</section>
		</div>
	</div>  
	
	