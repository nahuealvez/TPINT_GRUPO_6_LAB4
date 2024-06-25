package presentacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Excepciones.ErrorUsuarioException;
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
			
			String usuario = request.getParameter("txtUsuario");
			String contrasenia = request.getParameter("txtPass");
									
			try {
				user = usuarioNeg.verificarUsuario(usuario, contrasenia);
								
					session.setAttribute("sessionUsuario", user);						
					response.sendRedirect("Index.jsp");

			
			}catch(ErrorUsuarioException e) {
				request.setAttribute("loginError", "Usuario o contraseña incorrectos.");
				System.out.println(e.getMessage());
	            RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
	            rd.forward(request, response);
			}
				
		}
											
	}

}
