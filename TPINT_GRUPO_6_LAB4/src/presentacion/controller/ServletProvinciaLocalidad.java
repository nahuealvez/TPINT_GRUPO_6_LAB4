package presentacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Localidad;
import dominio.Provincia;
import negocioImpl.LocalidadNegImpl;
import negocioImpl.ProvinciaNegImpl;

/**
 * Servlet implementation class ServletProvinciaLocalidad
 */
@WebServlet("/ServletProvinciaLocalidad")
public class ServletProvinciaLocalidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProvinciaLocalidad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("cargarCampos") != null) {
			ProvinciaNegImpl provinciaNeg = new ProvinciaNegImpl();
			ArrayList<Provincia> provincias = provinciaNeg.listarProvincias();
			
			LocalidadNegImpl localidadNeg = new LocalidadNegImpl();
			ArrayList<Localidad> localidades = localidadNeg.listarLocalidades();
			
			request.setAttribute("provincias", provincias);
			request.setAttribute("localidades", localidades);
			RequestDispatcher rd = request.getRequestDispatcher("/AgregarCliente.jsp");
	        rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
