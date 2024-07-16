package presentacion.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import dominio.Cuenta;
import dominio.Movimiento;
import dominio.Prestamo;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegImpl;
import negocio.CuentaNegocio;
import negocio.MovimientoNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.PrestamoNegImpl;

@WebServlet("/ServletReporte")
public class ServletReporte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String valorParam = request.getParameter("param");
		
		System.out.println("valor param: " + valorParam);
		if ("1".equals(valorParam))
		{
			cargarReportePrestamos(request, response);
		}
		else if ("2".equals(valorParam))
		{
			listarPrestamosEnEvalucacion(request, response);
		}
		else if ("3".equals(valorParam))
		{
			listarPrestamosAprobados(request, response);
		}
		else if ("4".equals(valorParam))
		{
			listarPrestamosRechazados(request, response);
		}
		
		if(request.getParameter("btnListarCajaAhorro")!= null)
		{
			listarCuentasAhorro(request, response);
		}
		if(request.getParameter("btnListarCuentas")!= null)
		{
			listarCuentasCorriente(request, response);
		}
		if(request.getParameter("btnVerMovimientosCta")!= null)
		{
			verMovimientosCuenta(request, response);
		}
		if(request.getParameter("btnVerMovimientosAhorro")!= null)
		{
			verMovimientosAhorro(request, response);
		}
		
		if(request.getParameter("btnListarPrestamo")!= null)
		{
			listarPrestamos(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}
	
	//-----------------------------------------------------------------------------------
		private void cargarReportePrestamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			cargarDatosPrestamo(request, response);      
	        
			request.getRequestDispatcher("/ReportesPrestamos.jsp").forward(request, response);
		}
		private void listarCuentasAhorro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		private void listarCuentasCorriente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			ArrayList <Cuenta> listaCtas= new ArrayList<Cuenta>();
				CuentaNegocio NCta = new CuentaNegocioImpl();
				try {
					listaCtas=(ArrayList<Cuenta>) NCta.obtenerTodasLasCuentasCorrientes();
			} catch (Exception e) {
				 e.printStackTrace();
			}
				request.setAttribute("listaCtaCte", listaCtas);
		        request.getRequestDispatcher("/ReportesCuentasCorriente.jsp").forward(request, response);
		
		}
		private void verMovimientosCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			int idCuenta= Integer.parseInt(request.getParameter("IdCuentaCte"));
			System.out.println("Todo bien: " + idCuenta);
						
			ArrayList<Movimiento> listaMovimientoCta = new ArrayList<Movimiento>();
			MovimientoNegocio NMvto = new MovimientoNegImpl();
			try 
			{
				listaMovimientoCta=NMvto.listarMovimientosPorCuenta(idCuenta);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}	
				request.setAttribute("idCuenta", idCuenta);
				request.setAttribute("listaMovimiento", listaMovimientoCta);
			request.getRequestDispatcher("/ReporteVerCuenta.jsp").forward(request, response);
		}
		private void verMovimientosAhorro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			int idCuenta= Integer.parseInt(request.getParameter("IdCuentaCte"));
			System.out.println("Todo bien: " + idCuenta);
						
			ArrayList<Movimiento> listaMovimientoCta = new ArrayList<Movimiento>();
			MovimientoNegocio NMvto = new MovimientoNegImpl();
			try 
			{
				listaMovimientoCta=NMvto.listarMovimientosPorCuenta(idCuenta);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}	
				request.setAttribute("idCuenta", idCuenta);
				request.setAttribute("listaMovimiento", listaMovimientoCta);
			request.getRequestDispatcher("/ReporteVerCuenta.jsp").forward(request, response);
		}
		private void listarPrestamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			PrestamoNegocio NPtamo = new PrestamoNegImpl();
			
			try {
				listaPrestamos = NPtamo.listarTodosLosPrestamos();
			 } 
			catch (SQLException e) {
				e.printStackTrace();   
			} 
			catch ( Exception e) {
				e.printStackTrace();
			}
			cargarDatosPrestamo(request, response);
	        
			request.setAttribute("listaPrestamos", listaPrestamos);
			request.getRequestDispatcher("/ReportesPrestamos.jsp").forward(request, response);
		}
		
		private void listarPrestamosAprobados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			PrestamoNegocio NPtamo = new PrestamoNegImpl();
			
			try {
				listaPrestamos = NPtamo.listarTodosLosPrestamosAprobados();
			 } 
			catch (SQLException e) {
				e.printStackTrace();   
			} 
			catch ( Exception e) {
				e.printStackTrace();
			}
			cargarDatosPrestamo(request, response);
	        
			request.setAttribute("listaPrestamos", listaPrestamos);
			request.getRequestDispatcher("/ReportesPrestamos.jsp").forward(request, response);
		}
		private void listarPrestamosRechazados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			PrestamoNegocio NPtamo = new PrestamoNegImpl();
			
			try {
				listaPrestamos = NPtamo.listarTodosLosPrestamosRechazados();
			 } 
			catch (SQLException e) {
				e.printStackTrace();   
			} 
			catch ( Exception e) {
				e.printStackTrace();
			}
			cargarDatosPrestamo(request, response);
	        
			request.setAttribute("listaPrestamos", listaPrestamos);
			request.getRequestDispatcher("/ReportesPrestamos.jsp").forward(request, response);
		}
		private void listarPrestamosEnEvalucacion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			ArrayList<Prestamo> listaPrestamos = new ArrayList<Prestamo>();
			PrestamoNegocio NPtamo = new PrestamoNegImpl();
			
			try {
				listaPrestamos = NPtamo.listarTodosLosPrestamosEnEvaluacio();
			 } 
			catch (SQLException e) {
				e.printStackTrace();   
			} 
			catch ( Exception e) {
				e.printStackTrace();
			}
			cargarDatosPrestamo(request, response);
	        
			request.setAttribute("listaPrestamos", listaPrestamos);
			request.getRequestDispatcher("/ReportesPrestamos.jsp").forward(request, response);
		}
		public void cargarDatosPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int aprobados = 0;
	        int rechazados = 0;
	        int enEvaluacion = 0;

	        BigDecimal sumaAprobados = BigDecimal.ZERO;
	        BigDecimal sumaRechazados = BigDecimal.ZERO;
	        BigDecimal sumaEnEvaluacion = BigDecimal.ZERO;
	        
	        PrestamoNegocio NPtamo = new PrestamoNegImpl();

	        try {
	            aprobados = NPtamo.contarPrestamosAprobados();
	            rechazados = NPtamo.contarPrestamosRechazados();
	            enEvaluacion = NPtamo.contarPrestamosEnEvaluacion();

	            sumaAprobados = NPtamo.sumarPrestamosAprobados();
	            sumaRechazados = NPtamo.sumarPrestamosRechazados();
	            sumaEnEvaluacion = NPtamo.sumarPrestamosEnEvaluacion();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        request.setAttribute("aprobadosCount", aprobados);
	        request.setAttribute("rechazadosCount", rechazados);
	        request.setAttribute("evaluacionCount", enEvaluacion);

	        request.setAttribute("sumaAprobadosSum", sumaAprobados);
	        request.setAttribute("sumaRechazadosSum", sumaRechazados);
	        request.setAttribute("sumaEnEvaluacionSum", sumaEnEvaluacion);
	    }

}
