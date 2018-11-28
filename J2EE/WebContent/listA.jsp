<%@page import="business.entities.Animal"%>
<%@page import="persistance.dao.PersonDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% String se = (String) session.getAttribute("login"); 
   if(se == null || se.length() == 0){
	   response.sendRedirect("index.jsp");
	   return;
   }
%>
<%@ page import="service.MyService"%>
<%@ page import="persistance.dao.PersonDAO"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Bienvenue <%=se %></h1>
	<form method="post" action="person.jsp">
		<table border="2px solid">
			<%
				PersonDAO s = new PersonDAO();
			%>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Sex</th>
				<th>Coat color</th>
				<th>Specie</th>
			</tr>
			<%
				for (Animal a : s.findListAnimalP(Long.parseLong(request.getParameter("id")))) {
			%>
			<tr>
				<td><%=a.getId()%></td>
				<td><%=a.getName()%></td>
				<td><%=a.getSex()%></td>
				<td><%=a.getCoat_color()%></td>
				<td><%=a.getS().getCommon_name()%></td>
			</tr>
			<%
				}
			%>
		</table>
		<input type="submit" value="Retour" id="retour" />
	</form>
	<form method="post" action="deco.jsp">
		<input type="submit" value="Deconnexion" name="dec" />
	</form>
</body>
</html>