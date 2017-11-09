package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
@WebServlet("/adr")
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

	public void Ejer1(HttpServletRequest request) {
	
		try {
			Connection con = getConexion();
			String CodCA = request.getParameter("CodCA");
			String CA = request.getParameter("CA");
			 System.out.println(CodCA + " " + CA);

			
			PreparedStatement pst = con.prepareStatement("Select * from comunidadesautonomas");
			pst.setString(1, CodCA);
			pst.setString(2, CA);

			ResultSet resultado = pst.executeQuery();
			if (resultado.next()) {
				ComunidadesAutonomas comunidadAutonoma = new ComunidadesAutonomas();
				comunidadAutonoma.setCodCA(resultado.getInt(1));
				comunidadAutonoma.setCA(resultado.getString(2));
				System.out.println(comunidadAutonoma.getCA());
				HttpSession sesion = request.getSession();
				sesion.setAttribute("comAuton", request.getParameter("nombre"));

				

			} else {
				System.out.println("Usuario NO encontrado");
				

			}
		} catch (SQLException e) {
			System.out.println("Error SQL");
		}
	}
	/*private ComunidadesAutonomas creaCA(ResultSet resultado) throws SQLException, ParseException {
		ComunidadesAutonomas CA = new ComunidadesAutonomas();
		CA.setCodCA(resultado.getInt(1));
		CA.setCA(resultado.getString(2));
		

		System.out.println(CA.getCA());

		// LocalDate fecha = FORMATADOR.parse(resultadoBusca.getDate(4));
		// persona.setFechaNac(fecha);
		return CA;
	}*/

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
				Ejer1(request);
				url = base + "ejercicio1.jsp";
				break;

			case "obj3_2":

				url = base + "objetivo3.jsp";

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
