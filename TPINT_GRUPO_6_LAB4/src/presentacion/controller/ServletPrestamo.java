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



import dominio.Cliente;
import dominio.Cuenta;
import dominio.Cuota;
import dominio.Prestamo;
import negocio.CuentaNegocio;
import negocio.CuotaNegocio;
import negocio.PrestamoNegocio;
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
	private static final CuotaNegocio cuotaNeg = new CuotaNegImpl();

    public ServletPrestamo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

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
		
		if(request.getParameter("btnPrestamosAdminBanco")!= null) {
            ArrayList<Prestamo> prestamos = cargarSolicitudesPrestamos();
           
            request.setAttribute("listaPrestamosCliente", prestamos);
            request.getRequestDispatcher("/Prestamos.jsp").forward(request, response);
        }
		
		if(request.getParameter("confirmarAprobacion")!= null)
		{
			int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
			System.out.println("ID PRESTAMO: "+idPrestamo + " SERVLETPRESTAMO");
			ArrayList<Prestamo> prestamosActualizados =new ArrayList<Prestamo>();

			try {
				Prestamo prestamoAprob = prestamoNeg.obtenerPrestamoPorId(idPrestamo);
				prestamoNeg.aprobarPrestamo(prestamoAprob);
				prestamosActualizados = cargarSolicitudesPrestamos();
				System.out.println("SE APROBO EL PRESTAMO");

				request.setAttribute("listaPrestamosCliente", prestamosActualizados);
				request.getRequestDispatcher("/Prestamos.jsp").forward(request, response);
			} 
			catch (SQLException ex) {
				ex.printStackTrace();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		if(request.getParameter("rechazarSolicitud")!= null)
		{
			int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
			System.out.println("ID PRESTAMO A RECHAZAR: "+idPrestamo + " SERVLETPRESTAMO");
			ArrayList<Prestamo> prestamosActualizados =new ArrayList<Prestamo>();

			try {

				prestamoNeg.actualizarEstadoSolicitud(idPrestamo, false);
				prestamosActualizados = cargarSolicitudesPrestamos();
				System.out.println("SE RECHAZO EL PRESTAMO");

				request.setAttribute("listaPrestamosCliente", prestamosActualizados);
				request.getRequestDispatcher("/Prestamos.jsp").forward(request, response);
			} 
			catch (SQLException ex) {
				ex.printStackTrace();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		if(request.getParameter("btnVerPrestamo")!= null)
		{
			try {
				int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
				Prestamo prestamoADetallar = new Prestamo();
				prestamoADetallar = prestamoNeg.obtenerPrestamoPorId(idPrestamo);
				System.out.println("ID PRESTAMO A DETALLAR: "+idPrestamo + " SERVLETPRESTAMO");
				request.setAttribute("prestamoADetallar", prestamoADetallar);
				request.getRequestDispatcher("/DetallePrestamo.jsp").forward(request, response);
				
			} 
			catch (SQLException ex) {
				ex.printStackTrace();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		if(request.getParameter("btnPagarPrestamo")!= null)
		{
			int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
			
			try 
			{
				ArrayList<Cuota> cuotasPrestamo = new ArrayList<Cuota>();
				cuotasPrestamo = cuotaNeg.listarCuotasPorPrestamo(idPrestamo);
				request.setAttribute("idPrestamo", idPrestamo);
	            request.setAttribute("listaCuotas", cuotasPrestamo);
	            System.out.println(cuotasPrestamo.toString());
	            request.getRequestDispatcher("/CuotasPrestamo.jsp").forward(request, response);
	            System.out.println("SALUDOS DESDE BOTON PAGAR" + idPrestamo);
			}
			catch (SQLException ex) 
			{
				ex.printStackTrace();
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}		
		
		if(request.getParameter("btnFiltrarEstado")!= null)
		{
			String estadoPrestamo = request.getParameter("selectedFiltroEstadoPrestamo");
			System.out.println(estadoPrestamo);
			if (estadoPrestamo != null) {
		        ArrayList<Prestamo> prestamosFiltrados = new ArrayList<Prestamo>();
		        try {
		            if (estadoPrestamo.equals("Pendiente")) {
		                prestamosFiltrados = prestamoNeg.listarTodosLosPrestamosEnEvaluacio();
		            } else if (estadoPrestamo.equals("1")) {
		                prestamosFiltrados = prestamoNeg.listarTodosLosPrestamosAprobados();
		            } else if (estadoPrestamo.equals("0")) {
		                prestamosFiltrados = prestamoNeg.listarTodosLosPrestamosRechazados();
		            }

		            request.setAttribute("listaPrestamosCliente", prestamosFiltrados);
		            request.getRequestDispatcher("/Prestamos.jsp").forward(request, response);
		        } 
		        catch (SQLException ex) 
		        {
		            ex.printStackTrace();

		        } 
		        catch (Exception ex) 
		        {
		            ex.printStackTrace();

		        }
		    }

		}
		
		if (request.getParameter("btnPagarCuota") != null) {
			String mensaje = "";
			String claseMensaje = "";
			
			int idCuota = Integer.parseInt(request.getParameter("idCuota"));
			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
			System.out.println("Cuota" + idCuota);
			Cuota cuota = new Cuota();
			CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
			
			try {
				cuota = cuotaNeg.obtenerCuotaPorId(idCuota);
				System.out.println("IdCUOTA: " + cuota.getId());
				System.out.println("NroCUOTA: " + cuota.getNroCuota());
				request.setAttribute("cuota", cuota);
				
				ArrayList<Cuenta> cuentas = (ArrayList<Cuenta>)cuentaNegocio.cuentasPorClienteActivas(idCliente);
				request.setAttribute("cuentas", cuentas);
			}
			catch (SQLException ex) {
				mensaje = "No se pudo generar el volante de pago de cuota | " + ex.getMessage();
				claseMensaje = "btn btn-danger";
			}
			catch (Exception ex) {
				mensaje = "No se pudo generar el volante de pago de cuota | " + ex.getMessage();
				claseMensaje = "btn btn-danger";
			}
			
			request.getRequestDispatcher("/PagoCuota.jsp").forward(request, response);
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
	        	        
		}
        catch (SQLException ex) {
        	mensaje = "La solicitud no pudo ser generada | " + ex.getMessage();
     		claseMensaje = "alert alert-danger";
        }
        catch (Exception e) {
     		mensaje = "La solicitud no pudo ser generada | " + e.getMessage();
     		claseMensaje = "alert alert-danger";
		}
        
     	request.setAttribute("txtMensaje", mensaje);
        request.setAttribute("claseMensaje", claseMensaje);
	}
		
}
