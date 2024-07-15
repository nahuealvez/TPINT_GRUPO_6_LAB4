package presentacion.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dominio.Localidad;
import negocioImpl.LocalidadNegImpl;

/**
 * Servlet implementation class ServletLocalidades
 */
@WebServlet("/ServletLocalidad")
public class ServletLocalidad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletLocalidad() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ("agregar".equals(request.getParameter("accion"))) {
			LocalidadNegImpl localidadNeg = new LocalidadNegImpl();
			int idProvincia = Integer.parseInt(request.getParameter("idProvincia"));
			ArrayList<Localidad> localidades = localidadNeg.listarLocalidadesPorProvincia(idProvincia);
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<option selected disabled value=''>Seleccione Localidad</option>");
			for (Localidad localidad : localidades) {
	            out.println("<option value='" + localidad.getId() + "'>" + localidad.getNombre() + "</option>");
	        }
			out.close();
		}
		
		if ("modificar".equals(request.getParameter("accion"))) {
			LocalidadNegImpl localidadNeg = new LocalidadNegImpl();
			int idProvincia = Integer.parseInt(request.getParameter("idProvincia"));
			int idLocalidadSeleccionada = Integer.parseInt(request.getParameter("idLocalidadSeleccionada"));
			ArrayList<Localidad> localidades = localidadNeg.listarLocalidadesPorProvincia(idProvincia);
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<option selected disabled value=''>Seleccione Localidad</option>");
			for (Localidad localidad : localidades) {
	            if (localidad.getId() == idLocalidadSeleccionada) {
	            	out.println("<option value='" + localidad.getId() + "' selected>" + localidad.getNombre() + "</option>");
	            }
	            else {
	            	out.println("<option value='" + localidad.getId() + "'>" + localidad.getNombre() + "</option>");
	            }
	        }
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}
}
