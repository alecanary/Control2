<%@page import="modelo.ComunidadesAutonomas , modelo.Provincia"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page import="java.util.ArrayList , java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	//@SuppressWarnings("unchecked")
	//HttpSession sesion = request.getSession();
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>COntrol2</title>
</head>
<body>
	<div>Lista de Comunidades Autonomas en un Hashmap</div>
	<%
		HashMap<Integer, ComunidadesAutonomas> comunidades = (HashMap<Integer, ComunidadesAutonomas>) session.getAttribute("cAut");
	%>
	<form action="adr" method="POST">
		<input type="hidden" name="oper" value="obj1_2" /> <select
			name="equipo">

			<c:forEach items="${cAut}" var="elemento">
				<option value="${elemento.key}">${elemento.value.getCA()}></option>
			</c:forEach>
		</select> <input type="submit" value="Enviar" />
	</form>
	<%
		if (session.getAttribute("seleccionada") != null) {
			ArrayList<Provincia> provincias = (ArrayList<Provincia>) request.getAttribute("provincias");
			if (provincias != null) {
				if (provincias.size() != 0) {
	%>
	<ul>
		<c:forEach var="obj" items="${provincias}">
			<li>${obj.getProvincia()}</li>
		</c:forEach>
	</ul>
	<%
		}
			}
		}
	%>
	<br />
	<form>
		<input type="button" value="Volver" onclick="history.back();" />
	</form>
</body>
</html>