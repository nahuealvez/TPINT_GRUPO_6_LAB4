package presentacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dominio.Usuario;
import negocio.UsuarioNegocio;
import negocioImpl.UsuarioNegImpl;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioNegocio usuarioNeg = new UsuarioNegImpl();
   
    public ServletLogin() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario user = new Usuario();
		
		if(request.getParameter("btnIniciarSesion")!= null) {
			
			HttpSession session = request.getSession();
			
			String usuario = "";
			String contrasenia = "";
			
			if(request.getParameter("txtUsuario")!= null) {
				
				usuario = request.getParameter("txtUsuario");
				contrasenia = request.getParameter("txtPass");
				
			}
			
			user = usuarioNeg.verificarUsuario(usuario, contrasenia);
			
			session.setAttribute("sessionUsuario", usuario);
			session.setAttribute("sessionPass", contrasenia);
			
			if(user.getTipoUsuario().getId() == 1) {
				RequestDispatcher rd = request.getRequestDispatcher("/AgregarCliente.jsp");
				rd.forward(request, response);
			}else if(user.getTipoUsuario().getId() == 2) {
				RequestDispatcher rd = request.getRequestDispatcher("/FrontClientes.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
				rd.forward(request, response);
			}
		}
	}

}
