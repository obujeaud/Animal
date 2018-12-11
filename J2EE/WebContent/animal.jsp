<%@page import="business.entities.Animal"%>
<%@page import="persistance.dao.j22.AnimalDAO"%>
<%@page import="business.entities.Person"%>
<%@page import="persistance.dao.j22.PersonDAO"%>
<%@page import="service.ServiceAnimal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	function createAni() {
		window.location.assign("createAnimal.jsp");
	}
	
	function myUpdate(id) {
		window.location.assign("createAnimal.jsp?leid="+id);
	}
	
	function myDelete(id, name) {
		if(confirm("Souhaitez vous vraiment supprimer "+name+" ?")){
			var sub = document.getElementById("delAni");
			sub.value = id;
			window.location.assign("redirectAnimal.jsp?idDelete="+sub.value);
		}
	}
	
	function detruire(id){
		document.location = "redirectAnimal.jsp?deleteAniList="+id;
	}
	
	function retour(){
		document.location = "person.jsp";
	}
</script>
<%
	PersonDAO pdao = new PersonDAO();
	Person p = pdao.findById(Long.parseLong(request.getParameter("leHidAni")));
	session.setAttribute("idPers", p);
	AnimalDAO adao = new AnimalDAO();
	ServiceAnimal sa = new ServiceAnimal();
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="deco.jsp">
		<input type="submit" value="Deconnexion" name="dec" />
	</form>
	<h2>Liste des animaux déjà en possession</h2>
	<table border="2px solid black">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Sex</th>
			<th>Coat</th>
			<th>Specie</th>
			<th>Enlever</th>
		</tr>
		<%
			for (Animal a : p.getA()) {
		%><tr>
			<td><%=a.getId()%></td>
			<td><%=a.getName()%></td>
			<td><%=a.getSex()%></td>
			<td><%=a.getCoat_color()%></td>
			<td><%=a.getS().getCommon_name()%></td>
			<td><input type="button" value="Enlever" id="enleve" name="lEnleve" onclick="detruire(<%=a.getId()%>)"/></td>
		</tr>
		<%
			}
		%>
	</table>
	<form method="get" action="redirectAnimal.jsp" id="formuAni"
		name="laformu">
		<h2>Animaux à ajouter</h2>
		<table border="2px solid black">
			<tr>
				<th></th>
				<th>ID</th>
				<th>Name</th>
				<th>Sex</th>
				<th>Coat</th>
				<th>Specie</th>
				<th>Mettre à jour</th>
				<th>Supprimer</th>
			</tr>
			<%
				int i = 1;
				for (Animal a : adao.findList()) {
			%><tr>
				<td><input type="checkbox" id="<%=a.getId()%>"
					name="animal<%=i%>" value="<%=a.getId()%>" /></td>
				<td><%=a.getId()%></td>
				<td><%=a.getName()%></td>
				<td><%=a.getSex()%></td>
				<td><%=a.getCoat_color()%></td>
				<td><%=a.getS().getCommon_name()%></td>
				<td><input type="button" name="deletebtn" value="delete"
					onclick="myDelete(<%=a.getId()%>, '<%=a.getName()%>')" /></td>
				<td><input type="button" name="updatebtn" value="update"
					onclick="myUpdate(<%=a.getId()%>)" /></td>
			</tr>
			<%
				i++;
				}
			%>
		</table>
		<input type="hidden" id="idPers" name="leIdPers"
			value="<%=p.getId()%>" /> <input type="submit" value="Ajouter" /> <input
			type="hidden" id="upAni" name="leUpAni" value="" />
	</form>
	<input type="button" value="Retour" onclick="retour()" />
	<input type="button" id="createAni" name="leCreateAni"
		value="Créer un animal" onclick="createAni()" />
	<input type="hidden" value="" id="delAni" name="leDelAni" />
	<input type="hidden" value="" id="idPers" name="leIdPers" />
</body>
</html>