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
import dominio.Usuario;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class ServletIndex
 */
@WebServlet("/ServletIndex")
public class ServletIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getAttribute("indexCliente") != null) {
			Cliente cliente = new Cliente();
			ArrayList<Cuenta> cuentasPorCliente = new ArrayList<Cuenta>();
			ClienteNegocio clienteNegocio = new ClienteNegImpl();
			CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
			
			HttpSession sessionLogueada = request.getSession(false);
			Usuario usuarioLogueado = null;
			
		    if(sessionLogueada != null){
			    usuarioLogueado = (Usuario)sessionLogueada.getAttribute("sessionUsuario");
			    try {
			    	cliente = clienteNegocio.buscarClienteXidUsuario(usuarioLogueado.getId());
			    	int id = cliente.getIdCliente();
			    	cuentasPorCliente = (ArrayList<Cuenta>)cuentaNegocio.cuentasPorClienteActivas(id);
			    }
			    catch (SQLException ex) {
			    	ex.printStackTrace();
			    }
			    catch (Exception ex) {
			    	ex.printStackTrace();
			    }
			    
			    sessionLogueada.setAttribute("cliente", cliente);
			    request.setAttribute("cuentasPorCliente", cuentasPorCliente);
		    }
		    
		    RequestDispatcher rd = request.getRequestDispatcher("/Index.jsp");
            rd.forward(request, response);
		}
		
		if (request.getParameter("indexClienteNav") != null) {
			Cliente cliente = new Cliente();
			ArrayList<Cuenta> cuentasPorCliente = new ArrayList<Cuenta>();
			ClienteNegocio clienteNegocio = new ClienteNegImpl();
			CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
			
			HttpSession sessionLogueada = request.getSession(false);
			Usuario usuarioLogueado = null;
			
		    if(sessionLogueada != null){
			    usuarioLogueado = (Usuario)sessionLogueada.getAttribute("sessionUsuario");
			    try {
			    	cliente = clienteNegocio.buscarClienteXidUsuario(usuarioLogueado.getId());
			    	int id = cliente.getIdCliente();
			    	cuentasPorCliente = (ArrayList<Cuenta>)cuentaNegocio.cuentasPorClienteActivas(id);
			    }
			    catch (SQLException ex) {
			    	ex.printStackTrace();
			    }
			    catch (Exception ex) {
			    	ex.printStackTrace();
			    }
			    
			    sessionLogueada.setAttribute("cliente", cliente);
			    request.setAttribute("cuentasPorCliente", cuentasPorCliente);
		    }
		    
		    RequestDispatcher rd = request.getRequestDispatcher("/Index.jsp");
            rd.forward(request, response);
		}
	}

}
