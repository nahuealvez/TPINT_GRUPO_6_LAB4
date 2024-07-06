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

import dominio.Cliente;
import dominio.Cuenta;
import dominio.Prestamo;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PrestamoNegImpl;

/**
 * Servlet implementation class ServletPrestamo
 */
@WebServlet("/ServletPrestamo")
public class ServletPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final ClienteNegocio clienteNeg = new ClienteNegImpl();
	private static final CuentaNegocio cuentaNeg = new CuentaNegocioImpl();
	private static final PrestamoNegocio prestamoNeg = new PrestamoNegImpl();

    public ServletPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnSolicitarPrestamo")!= null)
		{
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
			try {
				ArrayList<Cuenta> cuentasCliente = (ArrayList<Cuenta>) cuentaNeg.cuentasPorClienteActivas(idCliente);
			    request.setAttribute("cuentasCliente", cuentasCliente);
			    request.setAttribute("idCliente", idCliente);
			    RequestDispatcher rd = request.getRequestDispatcher("/SolicitarPrestamo.jsp");
			    rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("confirmarBtn")!= null)
		{
			System.out.println("SALUDOS DESDE CONFIRMAR");
			
			
		}
	}

}
