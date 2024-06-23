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
			
			String usuario = request.getParameter("txtUsuario");
			String contrasenia = request.getParameter("txtPass");
									
			try {
				user = usuarioNeg.verificarUsuario(usuario, contrasenia);
				
				if(user != null) {
					System.out.println("Usuario: " + user.getUsuario());
					System.out.println("Contrase�a: " + user.getContrasenia());
					
					session.setAttribute("sessionUsuario", usuario);
					//session.setAttribute("sessionPass", contrasenia);							
					response.sendRedirect("Index.jsp?tipoUsuario="+user.getTipoUsuario().getId());
				
					
				}else {
					request.setAttribute("loginError", "Usuario o contrase�a incorrectos.");
					RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
					rd.forward(request, response);
				}
			
			}catch(NullPointerException e) {
				request.setAttribute("loginError", "Usuario o contrase�a incorrectos.");
				System.out.println(e.getMessage());
	            RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
	            rd.forward(request, response);
			}
				
			}
											
	}

}
