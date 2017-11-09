<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
   @SuppressWarnings("unchecked")
   //HttpSession sesion = request.getSession();
   //ArrayList<String> lista=(ArrayList<String>) request.getAttribute("miLista");
   ArrayList<String> lista=(ArrayList<String>) session.getAttribute("miLista");

   
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <div>Lista de Comunidades Autonomas en un ArrayList</div>
   <c:forEach items="${miLista}" var="elemento">
         <li><a  href="${elemento} "> ${elemento} </a></li>
   </c:forEach>
   <br/>
	<form>
		<input type="button" value="Volver" onclick="history.back();" />
	</form>
</body>
</html>