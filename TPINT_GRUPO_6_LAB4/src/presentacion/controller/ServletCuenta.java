package presentacion.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Cliente;
import dominio.Cuenta;
import dominio.TipoCuenta;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegocioImpl;

@WebServlet("/ServletCuenta")
public class ServletCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CuentaNegocio cuentaNeg = new CuentaNegocioImpl();		
	private ClienteNegocio clienteNeg = new ClienteNegImpl();
	private String mensaje = null;
	private String claseMensaje = null;

	public ServletCuenta() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");

		if ("agregar".equals(opcion)) {
			try {
				String dni = request.getParameter("dniCliente");
				
				Cliente clienteServlet = clienteNeg.buscarClienteXDNI(dni);
				
				if (clienteServlet != null) {

					List<Cuenta> listaDeCuentas = cuentaNeg.cuentasXCliente(clienteServlet.getIdCliente());
				
					if (cuentaNeg.contarCuentasActivas(listaDeCuentas)<3 ) {
						String cbu= cuentaNeg.nuevoCbu();
						
						request.setAttribute("cbuGenerado", cbu);
						request.setAttribute("clienteServlet", clienteServlet);
						
						request.getRequestDispatcher("/AgregarCuenta.jsp").forward(request, response);
					} else {
						request.setAttribute("listaDeCuentas", listaDeCuentas);
						request.setAttribute("clienteServlet", clienteServlet);
						
						String mensaje = "El cliente ya posee tres cuentas activas";
						String claseMensaje = "alert alert-danger";
						request.setAttribute("txtMensajeCuenta", mensaje);
						request.setAttribute("claseMensajeCuenta", claseMensaje);

						request.getRequestDispatcher("/Cuentas.jsp").forward(request, response);
					}
				} else {
					request.setAttribute("error", "Cliente no encontrado.");
					request.getRequestDispatcher("/Cuentas.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("/Cuentas.jsp").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");


		switch (opcion) {
		case "agregarCuenta":
			eventoAgregarCuenta( request, response);
			break;

		case "buscarCliente":

			eventoBuscarCliente( request,response);
			
			break;

		case "cambiarEstado":

			eventoCambiarEstado( request, response) ;

			break;

		default:
			break;
		}
	}
	
	public void eventoAgregarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Cuenta cuentagregada = new Cuenta();
		
		try {

			Cliente cliente = new Cliente();
			cliente.setIdCliente(Integer.parseInt(request.getParameter("clienteId")));

			TipoCuenta tipodecuenta = new TipoCuenta();
			tipodecuenta.setId(Integer.parseInt(request.getParameter("ddlTipoCuenta")));
			
			

			cuentagregada.setCliente(cliente);
			cuentagregada.setFechaCreacion(LocalDateTime.now());
			cuentagregada.setTipoCuenta(tipodecuenta);
			cuentagregada.setCbu(request.getParameter("txtCbu"));
			cuentagregada.setSaldo(new BigDecimal(request.getParameter("txtSaldo")));
			cuentagregada.setEstado(true);

			boolean cuentaCreada = cuentaNeg.insert(cuentagregada);

			if (cuentaCreada) {
				mensaje = "La cuenta fue creada con �xito";
				claseMensaje = "alert alert-success";
			} else {
				mensaje = "La cuenta no pudo ser creada ";
				claseMensaje = "alert alert-danger";
			}

		} catch (NullPointerException e) {
			mensaje = "La cuenta no pudo ser creada " + e.getMessage();
			claseMensaje = "alert alert-danger";
			e.printStackTrace();

		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		request.setAttribute("txtMensajeCuenta", mensaje);
		request.setAttribute("claseMensajeCuenta", claseMensaje);
		request.getRequestDispatcher("/Cuentas.jsp").forward(request, response);

	}
	public void eventoBuscarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String dni = request.getParameter("dniCliente");

		try {
			Cliente clienteServlet = clienteNeg.buscarClienteXDNI(dni);

			if (clienteServlet != null) {

				List<Cuenta> cuentas = cuentaNeg.cuentasXCliente(clienteServlet.getIdCliente());

				request.setAttribute("clienteServlet", clienteServlet);
				request.setAttribute("cuentasxCliente", cuentas);
				
			} else {

				request.setAttribute("errorBusqueda", true);
			}

			request.getRequestDispatcher("Cuentas.jsp").forward(request, response);

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public void eventoCambiarEstado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
		boolean estado = !Boolean.parseBoolean(request.getParameter("estado"));
		String dni2 = request.getParameter("dniCliente");
		Cliente clienteServlet = clienteNeg.buscarClienteXDNI(dni2);
		
		try {
			
			List<Cuenta> cuentasActivas = cuentaNeg.cuentasXCliente(clienteServlet.getIdCliente());
			int aux = cuentaNeg.contarCuentasActivas(cuentasActivas);
			
			if(estado) {
									
				if(aux >= 3) {
					mensaje = "El cliente ya posee tres cuentas activas";
					claseMensaje = "alert alert-danger";
					request.setAttribute("txtMensajeCuenta", mensaje);
					request.setAttribute("claseMensajeCuenta", claseMensaje);

					request.getRequestDispatcher("Cuentas.jsp").forward(request, response);
					return;
				}
				
			}
			boolean estadoActualizado = cuentaNeg.actualizarEstado(idCuenta, estado);
			
			if (estadoActualizado) {
			
				if (!estado) {
					
					mensaje = "Cuenta desactivada";
					claseMensaje = "alert alert-success";
					request.setAttribute("txtMensajeCuenta", mensaje);
					request.setAttribute("claseMensajeCuenta", claseMensaje);

				} else {
					mensaje = "Cuenta reactivada";
					claseMensaje = "alert alert-success";
					request.setAttribute("txtMensajeCuenta", mensaje);
					request.setAttribute("claseMensajeCuenta", claseMensaje);

				}
				
			}else {
				request.getRequestDispatcher("ServletCuenta?opcion=buscarCliente&dniCliente=" + request.getParameter("dniCliente")).forward(request, response);
			}
			
			request.getRequestDispatcher("Cuentas.jsp").forward(request, response);
			

		} catch (NullPointerException e) {
			mensaje = "Ocurri� un error en el cambio de estado" + e.getMessage();
			claseMensaje = "alert alert-danger";				
			request.getRequestDispatcher("Cuentas.jsp").forward(request, response);

		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	
	
}
