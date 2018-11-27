<%@page import="business.entities.Person"%>
<%@page import="java.awt.event.WindowStateListener"%>
<%@page import="java.awt.Window"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="javax.xml.ws.Response"%>
<%@ page import="service.MyService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	
<%String se = (String) session.getAttribute("login");%>
			
	function mySubmit(id) {
		var sub = document.getElementById("myid");
		var form = document.getElementById("formu");
		sub.value = id;
		form.submit();
	}
	
	function myCreate() {
		window.location.assign("updatePerson.jsp");
	}
	
	function myUpdate(id) {
		window.location.assign("updatePerson.jsp?leid="+id);
	}
	
	function myDelete(id, name) {
		if(confirm("Souhaitez vous vraiment supprimer "+name+" ?")){
			var sub = document.getElementById("del");
			sub.value = id;
			window.location.assign("redirectModif.jsp?id="+sub.value);
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>
		Bienvenue
		<%=se%></h1>
	<%
		MyService s = new MyService();
		if (session.getAttribute("admin") == "Oui") {
	%>
	<form method="post" action="deco.jsp">
		<input type="submit" value="Deconnexion" name="dec" />
	</form>
	<form method="post" action="listA.jsp" id="formu">
		<table border="1px solid">
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Prénom</th>
				<th>Age</th>
				<th>Nb Animaux</th>
				<th>Delete</th>
				<th>Update</th>
			</tr>
			<%
				List<Person> listp = new ArrayList<>();
					try {
						listp = s.personList();
					} catch (Exception e) {
						e.printStackTrace();
						response.sendRedirect("error.jsp");
						return;
					}
					for (Person p : listp) {
			%>
			<tr>
				<td id="id"><%=p.getId()%></td>
				<td id="name"><%=p.getName()%></td>
				<td id="prenom"><%=p.getPrenom()%></td>
				<td id="age"><%=p.getAge()%> ans</td>
				<%
					if (p == null) {
								response.sendRedirect("error.jsp");
								return;
							}
							if (p.getA().size() > 0) {
				%>
				<td align="center"><a
					href="javascript:mySubmit(<%=p.getId()%>)"> <%=p.getA().size()%></a></td>
				<td><input type="button" name="deletebtn" value="delete"
					onclick="myDelete(<%=p.getId()%>, '<%=p.getName()%>')" /></td>
				<td><input type="button" name="updatebtn" value="update"
					onclick="myUpdate(<%=p.getId()%>)" /></td>
				<%
					} else {
				%>
				<td align="center"><%=p.getA().size()%></td>
				<td><input type="button" name="deletebtn" value="delete"
					onclick="myDelete(<%=p.getId()%>, '<%=p.getName()%>')" /></td>
				<td><input type="button" name="updatebtn" value="update"
					onclick="myUpdate(<%=p.getId()%>)" /></td>
				<%
					}
				%>
			</tr>
			<%
				}
			%>
		</table>
		<input type="hidden" id="del" name="mydel" value="" /> <input
			type="hidden" id="myid" name="id" value="" /> <input type="hidden"
			id="update" name="myupdate" value="" />
	</form>
	<input type="button" id="create" name="creer" value="Créer"
		onclick="myCreate()" />
	<%
		} else {
	%>
	<form method="post" action="deco.jsp">
		<input type="submit" value="Deconnexion" name="dec" />
	</form>
	<table border="1px solid">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Prénom</th>
			<th>Age</th>
			<th>Nb Animaux</th>
		</tr>
		<%
			List<Person> listp = new ArrayList<>();
				try {
					listp = s.personList();
				} catch (Exception e) {
					e.printStackTrace();
					response.sendRedirect("error.jsp");
					return;
				}
				for (Person p : listp) {
		%>
		<tr>
			<td id="id"><%=p.getId()%></td>
			<td id="name"><%=p.getName()%></td>
			<td id="prenom"><%=p.getPrenom()%></td>
			<td id="age"><%=p.getAge()%> ans</td>
			<%
				if (p == null) {
							response.sendRedirect("error.jsp");
							return;
						}
						if (p.getA().size() > 0) {
			%>
			<td align="center"><%=p.getA().size()%></td>
			<%
				} else {
			%>
			<td align="center"><%=p.getA().size()%></td>
			<%
				}
			%>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
	%>
</body>
</html>