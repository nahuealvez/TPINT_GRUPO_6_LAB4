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
import javax.servlet.http.HttpSession;

import dominio.Cliente;
import dominio.Cuenta;
import dominio.Movimiento;
import dominio.TipoCuenta;
import dominio.TipoMovimiento;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegImpl;

@WebServlet("/ServletCuenta")
public class ServletCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CuentaNegocio cuentaNeg = new CuentaNegocioImpl();
	private ClienteNegocio clienteNeg = new ClienteNegImpl();
	private MovimientoNegocio movimientoNeg = new MovimientoNegImpl();
	private String mensaje = null;
	private String claseMensaje = null;

	public ServletCuenta() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");
		switch(opcion) {
		case "agregar":
			try {
				String dni = request.getParameter("dniCliente");
				Cliente clienteServlet = clienteNeg.buscarClienteXDNI(dni);

				if (clienteServlet != null) {

					List<Cuenta> listaDeCuentas = cuentaNeg.cuentasXCliente(clienteServlet.getIdCliente());

					if (cuentaNeg.contarCuentasActivas(listaDeCuentas) < 3) {
						String cbu = cuentaNeg.nuevoCbu();

						request.setAttribute("cbuGenerado", cbu);
						request.setAttribute("clienteServlet", clienteServlet);

						request.getRequestDispatcher("/AgregarCuenta.jsp").forward(request, response);
					} else {

						mostrarMensaje(request, response, "El cliente ya posee tres cuentas activas", "alert alert-danger", clienteServlet, listaDeCuentas);

						request.getRequestDispatcher("/Cuentas.jsp").forward(request, response);
					}
				} else {

					request.getRequestDispatcher("/Cuentas.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("/Cuentas.jsp").forward(request, response);
			}
			
			break;
		case "listar":
			eventoListarCuentasdDeClientes ( request, response);
			break;
		
		default:
			break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");

		switch (opcion) {
		case "agregarCuenta":
			eventoAgregarCuenta(request, response);
			break;

		case "buscarCliente":

			eventoBuscarCliente(request, response);

			break;

		case "cambiarEstado":

			eventoCambiarEstado(request, response);

			break;

		default:
			break;
		}
	}

	/* METODOS */

	public void eventoAgregarCuenta(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
				
				Movimiento movimiento = new Movimiento();
				
				TipoMovimiento tipoMov = new TipoMovimiento();
				tipoMov.setId(1);
				
                movimiento.setFecha(LocalDateTime.now());
                movimiento.setTipoMovimiento(tipoMov);;
                movimiento.setCuenta(cuentagregada);
                movimiento.setImporte(cuentagregada.getSaldo());
                
                boolean movCuentaNueva = movimientoNeg.agregarMovimiento(movimiento);
                
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

	public void eventoBuscarCliente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dni = request.getParameter("dniCliente");

		try {
			Cliente clienteServlet = clienteNeg.buscarClienteXDNI(dni);

			if (clienteServlet != null) {

				List<Cuenta> cuentas = cuentaNeg.cuentasXCliente(clienteServlet.getIdCliente());

				request.setAttribute("clienteServlet", clienteServlet);
				request.setAttribute("cuentasxCliente", cuentas);

				if (cuentas == null || cuentas.isEmpty()) {

					mostrarMensaje(request, response, "El cliente no tiene cuentas asociadas", "alert alert-danger", clienteServlet, null);
				}

			} else {
				
				mostrarMensaje(request, response, "No se encontr� ning�n cliente con ese DNI", "alert alert-danger", clienteServlet, null);

			}

			request.getRequestDispatcher("Cuentas.jsp").forward(request, response);

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void eventoCambiarEstado(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
		boolean estado = !Boolean.parseBoolean(request.getParameter("estado"));
		String dni2 = request.getParameter("dniCliente");
		Cliente clienteServlet = clienteNeg.buscarClienteXDNI(dni2);

		try {

			List<Cuenta> cuentasActivas = cuentaNeg.cuentasXCliente(clienteServlet.getIdCliente());
			int aux = cuentaNeg.contarCuentasActivas(cuentasActivas);

			if (estado) {

				if (aux >= 3) {

					mostrarMensaje(request, response, "El cliente ya posee tres cuentas activas", "alert alert-danger", clienteServlet, cuentasActivas);
					request.getRequestDispatcher("Cuentas.jsp").forward(request, response);
					return;
				}

			}
			boolean estadoActualizado = cuentaNeg.actualizarEstado(idCuenta, estado);

			if (estadoActualizado) {

				if (!estado) {

					mostrarMensaje(request, response, "Cuenta desactivada", "alert alert-danger", null, null);
					
				} else {

					mostrarMensaje(request, response, "Cuenta reactivada", "alert alert-danger", null, null);
				}

			} else {
				request.getRequestDispatcher(
						"ServletCuenta?opcion=buscarCliente&dniCliente=" + request.getParameter("dniCliente"))
						.forward(request, response);
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

	private void mostrarMensaje(HttpServletRequest request, HttpServletResponse response, String mensaje,
			String claseMensaje, Cliente cliente, List<Cuenta> cuentas) throws ServletException, IOException {
		request.setAttribute("txtMensajeCuenta", mensaje);
		request.setAttribute("claseMensajeCuenta", claseMensaje);
		if (cliente != null) {
			request.setAttribute("clienteServlet", cliente);
		}
		if (cuentas != null) {
			request.setAttribute("cuentasxCliente", cuentas);
		}
		request.getRequestDispatcher("Cuentas.jsp").forward(request, response);
	}

	
	private void eventoListarCuentasdDeClientes (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 try {
	         HttpSession sessionLogueada1 = request.getSession(false);
	         Cliente clienteServlet = null;
	         System.out.println("entro al evento de listar cuentas de clientes");

	         if (sessionLogueada1 != null) {
	             clienteServlet = (Cliente) sessionLogueada1.getAttribute("cliente");
	             System.out.println("cliente servlet: "+clienteServlet.getIdCliente());
	         }

	         if (clienteServlet != null) {
	             List<Cuenta> listaDeCuentas = cuentaNeg.cuentasActivas(clienteServlet.getIdCliente());
	             System.out.println("flag2");
	             if (listaDeCuentas != null ) {
	                 request.setAttribute("cuentasxCliente", listaDeCuentas);
	             } else {
	            	 System.out.println("flag3");
	                 request.setAttribute("txtMensajeCuenta", "No tiene cuentas asociadas.");
	                 request.setAttribute("claseMensajeCuenta", "alert alert-danger");
	             }
	         } else {
	             request.setAttribute("txtMensajeCuenta", "No se encontró el cliente en la sesión.");
	             request.setAttribute("claseMensajeCuenta", "alert alert-danger");
	         }
	         System.out.println("flag4");
	         request.getRequestDispatcher("/CuentasClientes.jsp").forward(request, response);
	     } catch (Exception e) {
	         e.printStackTrace();
	         System.out.println("flag5");
	         request.setAttribute("txtMensajeCuenta", "Ocurrió un error al obtener las cuentas.");
	         request.setAttribute("claseMensajeCuenta", "alert alert-danger");
	         request.getRequestDispatcher("/CuentasClientes.jsp").forward(request, response);
	     }
	 }
}

