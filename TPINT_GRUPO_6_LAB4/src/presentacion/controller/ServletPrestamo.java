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
import dominio.Prestamo;
import negocio.ClienteNegocio;
import negocio.CuentaNegocio;
import negocio.PrestamoNegocio;
import negocioImpl.ClienteNegImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PrestamoNegImpl;

/**
 * Servlet implementation class ServletPrestamo
 */
@WebServlet("/ServletPrestamo")
public class ServletPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final ClienteNegocio clienteNeg = new ClienteNegImpl();
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
			eventoBtnConfirmarSolicitudCliente(request, response);		
		}
	}
	
	//------------------------FUNCIONES SOBRE EVENTOS--------------------
	private void eventoBtnConfirmarSolicitudCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Prestamo prestamo = new Prestamo();
		Cliente cliente = new Cliente();
		Cuenta cuenta = new Cuenta();
		
		float tasaInteres = 20.0f;
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
		prestamo.setCuotas(cuotas);
		prestamo.setImporteMensual(importeMensual);
		
        String mensaje = null;
        String claseMensaje = null;
        
        try {
        	prestamoNeg.crearPrestamo(prestamo);
			
	        mensaje = "La solicitud fue generada exitosamente.";
	        claseMensaje = "alert alert-success";
	        
	        System.out.println(prestamo.getCliente().getIdCliente());
	        System.out.println(prestamo.getCuenta().getId());
	        System.out.println(prestamo.getImporteAPagar());
	        System.out.println(prestamo.getImportePedido());
	        System.out.println(prestamo.getCuotas());
	        System.out.println(prestamo.getImporteMensual());
	        
		} catch (Exception e) {
     		mensaje = "La solicitud no pudo ser generada. Intentelo nuevamente. | " + e.getMessage();
     		claseMensaje = "alert alert-danger";
		}
        
     	request.setAttribute("txtMensajeAgregarCliente", mensaje);
        request.setAttribute("claseMensajeAgregarCliente", claseMensaje);
	}

}
