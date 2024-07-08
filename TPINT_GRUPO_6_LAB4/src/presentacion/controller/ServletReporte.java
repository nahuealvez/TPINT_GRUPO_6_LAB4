package presentacion.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import dominio.Cuenta;
import negocioImpl.CuentaNegocioImpl;
import negocio.CuentaNegocio;


@WebServlet("/ServletReporte")
public class ServletReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnListarCajaAhorro")!= null)
		{
 			ArrayList <Cuenta> listaCtas= new ArrayList<Cuenta>();
 			CuentaNegocio NCta = new CuentaNegocioImpl();
 			try {
 				listaCtas=(ArrayList<Cuenta>) NCta.obtenerTodasLasCuentasAhorro();
			} catch (Exception e) {
				 e.printStackTrace();
			}
 			request.setAttribute("listaCA", listaCtas);
 	        request.getRequestDispatcher("/ReporteCajasDeAhorro.jsp").forward(request, response);
		}
		if(request.getParameter("btnListarCuentas")!= null)
		{
 			ArrayList <Cuenta> listaCtas= new ArrayList<Cuenta>();
 			CuentaNegocio NCta = new CuentaNegocioImpl();
 			try {
 				listaCtas=(ArrayList<Cuenta>) NCta.obtenerTodasLasCuentasAhorro();
			} catch (Exception e) {
				 e.printStackTrace();
			}
 			request.setAttribute("listaCtaCte", listaCtas);
 	        request.getRequestDispatcher("/ReportesCuentasCorriente.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

}
