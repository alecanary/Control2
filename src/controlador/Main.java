package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ComunidadesAutonomas;
import modelo.Provincia;

/**
 * Servlet implementation class Main
 */ 
//@WebServlet("/adr")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String usuario = "root";
	private final String password = "root";
	private final String url_bd = "jdbc:mysql://localhost:3306/paro";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Main() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Connection getConexion() {
		Connection conexion = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(url_bd, usuario, password);
			// System.out.println("Conectado!!");
		} catch (SQLException e) {
			System.out.println("Problema al conectar: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Clase no encontrada");
		}
		return conexion;
	}

	public HashMap<Integer, ComunidadesAutonomas> getAllComunidades() {

		HashMap<Integer, ComunidadesAutonomas> comunidades = new HashMap<Integer, ComunidadesAutonomas>();
		String consulta = "SELECT * FROM comunidadesautonomas";
		try {
			Connection con = getConexion();
			PreparedStatement pst = con.prepareStatement("Select * from comunidadesautonomas");
			ResultSet rs = pst.executeQuery(consulta);
			while (rs.next()) {
				comunidades.put(rs.getInt(1), new ComunidadesAutonomas(rs.getInt(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();

		}
		return comunidades;
	}

	public ArrayList<Provincia> getAllProvinciasByCA(int comAut) {
		ArrayList<Provincia> provincias = new ArrayList<Provincia>();
		
		try {
			Connection con = getConexion();
			PreparedStatement pst = con.prepareStatement(
					"select * from provincias pro JOIN comunidadesautonomas ca on pro.CodCA = ca.CodCA where pro.CodCA="
							+ comAut + "");
			ResultSet rs = pst.executeQuery(
					"select * from provincias pro JOIN comunidadesautonomas ca on pro.CodCA = ca.CodCA where pro.CodCA="
							+ comAut + "");
			while (rs.next()) {
				provincias.add(new Provincia(rs.getInt(1), rs.getString(2), rs.getInt(3)));
			}
			return provincias;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void Ejer1(HttpServletRequest request) {
		HttpSession sesion = request.getSession();
		if (sesion.getAttribute("cAut") == null) {
			HashMap<Integer, ComunidadesAutonomas> ComuAut = getAllComunidades();
			sesion.setAttribute("cAut", ComuAut);
		}
		ArrayList<Provincia> provinciasPorCA = new ArrayList<Provincia>();
		if (sesion.getAttribute("seleccionada") != null) {
			provinciasPorCA = getAllProvinciasByCA((int) sesion.getAttribute("seleccionada"));
		}
		request.setAttribute("provincias", provinciasPorCA);

	}
	/*
	 * private ComunidadesAutonomas creaCA(ResultSet resultado) throws SQLException,
	 * ParseException { ComunidadesAutonomas CA = new ComunidadesAutonomas();
	 * CA.setCodCA(resultado.getInt(1)); CA.setCA(resultado.getString(2));
	 * 
	 * 
	 * System.out.println(CA.getCA());
	 * 
	 * // LocalDate fecha = FORMATADOR.parse(resultadoBusca.getDate(4)); //
	 * persona.setFechaNac(fecha); return CA; }
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String base = "/jsp/";
		String url = base + "index.jsp";
		String action = request.getParameter("oper");

		if (action != null) {
			switch (action) {

			case "obj1":

				url = base + "ejercicio1.jsp";
				break;

			case "obj1_2":
				Ejer1(request);
				url = base + "ejercicio1.jsp";

				break;
			}
		}
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
