package presentacion.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Cliente;
import dominio.Cuenta;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class ServletTransferencia
 */
@WebServlet("/ServletTransferencia")
public class ServletTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTransferencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("btnTransferencia") != null) {
			Cliente cliente = new Cliente();
			ArrayList<Cuenta> cuentasPorCliente = new ArrayList<Cuenta>();
			CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
			
			HttpSession sessionLogueada = request.getSession(false);
			
			if (sessionLogueada != null) {
				try {
					cliente = (Cliente)sessionLogueada.getAttribute("cliente");
					int id = cliente.getIdCliente();
					cuentasPorCliente = (ArrayList<Cuenta>) cuentaNegocio.cuentasPorClienteActivas(id);
				}
				catch (SQLException ex) {
					ex.printStackTrace();
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				
				sessionLogueada.setAttribute("cuentasPorCliente", cuentasPorCliente);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/Transferencias.jsp");
			rd.forward(request, response);
		}
	}

}
