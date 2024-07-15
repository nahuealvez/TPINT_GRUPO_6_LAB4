package presentacion.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Provincia;
import negocioImpl.ProvinciaNegImpl;

@WebServlet("/ServletProvincia")
public class ServletProvincia extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletProvincia() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("cargarCampos") != null) {
			ProvinciaNegImpl provinciaNeg = new ProvinciaNegImpl();
			ArrayList<Provincia> provincias = provinciaNeg.listarProvincias();
			
			request.setAttribute("provincias", provincias);
			RequestDispatcher rd = request.getRequestDispatcher("/AgregarCliente.jsp");
	        rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Logica para cargar provincias al hacer click en "MODIFICAR CLIENTE"
	    // Cargar las provincias
		int idCliente = Integer.parseInt(request.getParameter("idCliente"));
	    ProvinciaNegImpl provinciaNeg = new ProvinciaNegImpl();
	    ArrayList<Provincia> provincias = provinciaNeg.listarProvincias();
	    
	    // Setear las provincias como atributo del request
	    request.setAttribute("provincias", provincias);
	    request.setAttribute("idCliente", idCliente);
	    

	    RequestDispatcher rd = request.getRequestDispatcher("/ServletCliente");
	    rd.forward(request, response);
	}

}
