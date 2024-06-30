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

import datos.ClienteDao;
import datos.CuentaDao;
import datosImpl.ClienteDaoImpl;
import datosImpl.CuentaDaoImpl;
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

		String accion = request.getParameter("accion");

		if ("agregar".equals(accion)) {

			String dni = request.getParameter("dniCliente");
			Cliente clienteServlet = clienteNeg.buscarClienteXDNI(dni);

			if (clienteServlet != null) {

				request.setAttribute("clienteServlet", clienteServlet);
			}

			request.getRequestDispatcher("/AgregarCuenta.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		

			if (request.getParameter("btnAgregarCuenta") != null) {

				Cuenta cuentagregada = new Cuenta();
				
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

				boolean cuentaCreada = false;				
				cuentaCreada = cuentaNeg.insert(cuentagregada);
				
				if (cuentaCreada == true) {

					request.setAttribute("Cuentacreada", cuentaNeg);
					request.getRequestDispatcher("/Cuentas.jsp").forward(request, response);
					
				} else {

					request.setAttribute("error", "Error al agregar la cuenta.");
					request.getRequestDispatcher("/AgregarCuenta.jsp").forward(request, response);
				}

			} else if (request.getParameter("buscarCliente") != null) {

				String dni = request.getParameter("dniCliente");
				System.out.println("DNI:" + dni);

				try {
					
					Cliente clienteServlet = clienteNeg.buscarClienteXDNI(dni);

					if (clienteServlet != null) {
						
						List<Cuenta> cuentas = cuentaNeg.cuentasXCliente(clienteServlet.getIdCliente());

						request.setAttribute("clienteServlet", clienteServlet);
						request.setAttribute("cuentasxCliente", cuentas);
						
					} else {
						request.setAttribute("error busqueda", true);
					}

					request.getRequestDispatcher("Cuentas.jsp").forward(request, response);

				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		
	}
}

