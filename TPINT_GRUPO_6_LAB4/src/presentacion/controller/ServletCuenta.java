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
            System.out.println("Botón Agregar Cuenta presionado en doGet");
            try {
                String dni = request.getParameter("dniCliente");
                System.out.println("DNI recibido en doGet: " + dni);

                Cliente clienteServlet = clienteNeg.buscarClienteXDNI(dni);

                if (clienteServlet != null) {
                    List<Cuenta> listaDeCuentas = cuentaNeg.cuentasXCliente(clienteServlet.getIdCliente());
                    

                    if (listaDeCuentas.size() < 3) {
                        request.setAttribute("clienteServlet", clienteServlet);
                        request.getRequestDispatcher("/AgregarCuenta.jsp").forward(request, response);
                    } else {
                        request.setAttribute("listaDeCuentas", listaDeCuentas);
                        request.setAttribute("clienteServlet", clienteServlet);
                        request.setAttribute("error", "El cliente ya tiene tres o más cuentas.");
                        request.getRequestDispatcher("/Cuentas.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("error", "Cliente no encontrado.");
                    request.getRequestDispatcher("/Cuentas.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Ocurrió un error al buscar el cliente.");
                request.getRequestDispatcher("/Cuentas.jsp").forward(request, response);
            }
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");

		switch (opcion) {
		case "agregarCuenta":
			System.out.println("Botón Agregar Cuenta presionado");
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
				request.setAttribute("error", "Parámetros nulos recibidos. Verifica los campos ingresados.");
				request.getRequestDispatcher("/AgregarCuenta.jsp").forward(request, response);
			}
			break;

		case "buscarCliente":
			System.out.println("Botón Buscar Cliente presionado");

			String dni = request.getParameter("dniCliente");
			System.out.println("DNI recibido en doPost: " + dni);

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

		case "desactivarCuenta":
			// Implement the logic for deactivating an account if needed.
			break;

		default:
			// Handle default case if needed.
			break;
		}
	}
}
