package presentacion.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import dominio.Cliente;
import dominio.Cuenta;
import dominio.Cuota;
import dominio.Prestamo;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.CuotaNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.CuotaNegImpl;
import negocioImpl.PrestamoNegImpl;

/**
 * Servlet implementation class ServletPrestamo
 */
@WebServlet("/ServletPrestamo")
public class ServletPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
		if(request.getParameter("accederPrestamosCliente") != null)
		{
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
			
			ArrayList<Prestamo> prestamos = cargarPrestamosPorCliente(idCliente);
			request.setAttribute("listaPrestamos", prestamos);
			request.getRequestDispatcher("/Prestamos.jsp").forward(request, response);
		}
		
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
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
			eventoBtnConfirmarSolicitudCliente(request, response);
			
			ArrayList<Prestamo> prestamos = cargarPrestamosPorCliente(idCliente); 
			request.setAttribute("listaPrestamos", prestamos);
			request.getRequestDispatcher("/Prestamos.jsp").forward(request, response);
		}
		
		if(request.getParameter("btnPrestamosCliente")!= null)
		{
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
			
			ArrayList<Prestamo> prestamos = cargarPrestamosPorCliente(idCliente); 
			request.setAttribute("listaPrestamos", prestamos);
			request.getRequestDispatcher("/Prestamos.jsp").forward(request, response);
		}
		
		if(request.getParameter("btnPrestamosAdminBanco")!= null)
		{
			
			ArrayList<Prestamo> prestamos = cargarSolicitudesPrestamos();
			request.setAttribute("listaPrestamosCliente", prestamos);
			request.getRequestDispatcher("/Prestamos.jsp").forward(request, response);
		}
		
		if(request.getParameter("confirmarAprobacion")!= null)
		{
			int idPrestamo = 7; // HARDCODEADO -- RESOLVER PASAR POR PARAMETRO.
			try {
				Prestamo prestamoAprob = prestamoNeg.obtenerPrestamoPorId(idPrestamo);
				prestamoNeg.aprobarPrestamo(prestamoAprob);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			
			try {
				CuotaNegocio cNeg = new CuotaNegImpl();
				ArrayList<Cuota> cuotas = cNeg.listarCuotasPorPrestamo(idPrestamo);
				for (Cuota cuota : cuotas) {
					System.out.println("SERVLET " + cuota.toString());
				}
			} 
			catch (SQLException ex) {
				ex.printStackTrace();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	//------------------------FUNCIONES SOBRE EVENTOS--------------------
	
	private ArrayList<Prestamo> cargarPrestamosPorCliente(int idCliente) 
	{
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		try {
			prestamos = prestamoNeg.listarPrestamosXCliente(idCliente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prestamos;
	}
	
	private ArrayList<Prestamo> cargarSolicitudesPrestamos() 
	{
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		try {
			prestamos = prestamoNeg.listarSolicitudesPrestamos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prestamos;
	}

	private void eventoBtnConfirmarSolicitudCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Prestamo prestamo = new Prestamo();
		Cliente cliente = new Cliente();
		Cuenta cuenta = new Cuenta();
		
		float tasaInteres = 20.0f;
		int plazoPago = 30;
		BigDecimal importePedido = new BigDecimal(request.getParameter("txtImporteSolicitado"));
		int cuotas = (Integer.parseInt(request.getParameter("txtCuotas")));

		BigDecimal tasaDecimal = BigDecimal.valueOf(tasaInteres).divide(BigDecimal.valueOf(100));

		BigDecimal importeInteres = importePedido.multiply(tasaDecimal);
		
		BigDecimal importeTotalAPagar = importePedido.add(importeInteres);

		BigDecimal importeMensual = importeTotalAPagar.divide(BigDecimal.valueOf(cuotas));
		
		cliente.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
		prestamo.setCliente(cliente);
		
		cuenta.setId(Integer.parseInt(request.getParameter("ddlCuentasCliente")));
		prestamo.setCuenta(cuenta);
		
		prestamo.setImporteAPagar(importeTotalAPagar);
		prestamo.setImportePedido(importePedido);
		prestamo.setPlazoDePago(plazoPago);
		prestamo.setCuotas(cuotas);
		prestamo.setImporteMensual(importeMensual);
		
        String mensaje = null;
        String claseMensaje = null;
        
        try {
        	prestamoNeg.crearPrestamo(prestamo);
			
	        mensaje = "La solicitud fue generada exitosamente.";
	        claseMensaje = "alert alert-success";
	        	        
		} catch (Exception e) {
     		mensaje = "La solicitud no pudo ser generada. Intentelo nuevamente. | " + e.getMessage();
     		claseMensaje = "alert alert-danger";
		}
        
     	request.setAttribute("txtMensajeAgregarCliente", mensaje);
        request.setAttribute("claseMensajeAgregarCliente", claseMensaje);
	}

}
