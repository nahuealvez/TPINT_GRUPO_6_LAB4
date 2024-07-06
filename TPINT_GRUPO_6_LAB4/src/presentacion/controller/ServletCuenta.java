package presentacion.controller;

import java.io.IOException;
import java.math.BigDecimal;
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
					System.out.println("entre al primer if");
					List<Cuenta> listaDeCuentas = cuentaNeg.cuentasXCliente(clienteServlet.getIdCliente());
				
					if (cuentaNeg.contarCuentasActivas(listaDeCuentas)<3 ) {
						String cbu= cuentaNeg.nuevoCbu();
						request.setAttribute("cbuGenerado", cbu);
						request.setAttribute("clienteServlet", clienteServlet);
						request.getRequestDispatcher("/AgregarCuenta.jsp").forward(request, response);
					} else {
						request.setAttribute("listaDeCuentas", listaDeCuentas);
						request.setAttribute("clienteServlet", clienteServlet);
						request.setAttribute("error", "El cliente ya tiene tres o m�s cuentas.");
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
				System.out.println("Cuenta creada: " + cuentaCreada);

				if (cuentaCreada) {
					request.setAttribute("Cuentacreada", cuentagregada);
					request.getRequestDispatcher("/Cuentas.jsp").forward(request, response);
				} else {
					request.setAttribute("error", "Error al agregar la cuenta.");
					request.getRequestDispatcher("/AgregarCuenta.jsp").forward(request, response);
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				request.getRequestDispatcher("/AgregarCuenta.jsp").forward(request, response);
			}
			break;

		case "buscarCliente":

			String dni = request.getParameter("dniCliente");

			try {
				Cliente clienteServlet = clienteNeg.buscarClienteXDNI(dni);

				if (clienteServlet != null) {
					System.out.println("Cliente encontrado: " + clienteServlet.getNombre());
					List<Cuenta> cuentas = cuentaNeg.cuentasXCliente(clienteServlet.getIdCliente());

					request.setAttribute("clienteServlet", clienteServlet);
					request.setAttribute("cuentasxCliente", cuentas);
				} else {
					System.out.println("Cliente no encontrado");
					request.setAttribute("errorBusqueda", true);
				}

				request.getRequestDispatcher("Cuentas.jsp").forward(request, response);

			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			break;

		case "cambiarEstado":


			int idCuenta = Integer.parseInt(request.getParameter("idCuenta"));
			boolean estado = !Boolean.parseBoolean(request.getParameter("estado"));
			String dni2 = request.getParameter("dniCliente");
			System.out.println("dni de cambiarestado: " + dni2);
			Cliente clienteServlet = clienteNeg.buscarClienteXDNI(dni2);
			System.out.println("cliente: " + clienteServlet.getApellido());
			
			try {
				System.out.println("entro al try");
				List<Cuenta> cuentasActivas = cuentaNeg.cuentasXCliente(clienteServlet.getIdCliente());
				int aux = cuentaNeg.contarCuentasActivas(cuentasActivas);
				System.out.println("aux " + aux);
				if(estado) {
					System.out.println("entro al primer if");
					
					if(aux >= 3) {
						System.out.println("entro a contarcuentas ");
						System.out.println(cuentaNeg.contarCuentasActivas(cuentasActivas));
						request.setAttribute("mensaje", "El cliente ya posee 3 cuentas activas");
						request.getRequestDispatcher("Cuentas.jsp").forward(request, response);
						return;
					}
					System.out.println(cuentaNeg.contarCuentasActivas(cuentasActivas));
				}
				boolean estadoActualizado = cuentaNeg.actualizarEstado(idCuenta, estado);
				
				if (estadoActualizado) {
					System.out.println("entro al segundo if");
					if (!estado) {
						System.out.println("entro al tercer if");
						request.setAttribute("mensaje", "Cuenta desactivada");
					} else {
						request.setAttribute("mensaje", "Cuenta reactivada");
					}
					
				}else {
					request.getRequestDispatcher("ServletCuenta?opcion=buscarCliente&dniCliente=" + request.getParameter("dniCliente")).forward(request, response);
				}
				System.out.println("estoy por salir");
				request.getRequestDispatcher("Cuentas.jsp").forward(request, response);
				

			} catch (NullPointerException e) {
				e.printStackTrace();
				request.setAttribute("error", "Ocurrio un error al cambiar el estado de la cuenta.");
				request.getRequestDispatcher("Cuentas.jsp").forward(request, response);
			}


			break;

		default:

			break;
		}
	}
}
