<%@page import="business.entities.Person"%>
<%@page import="persistance.dao.j22.PersonDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	
<%long i;%>
function mySubmit(id) {
	var leId = document.getElementById("idUpCreate");
	var leName = document.getElementById("name").value;
	var lePrenom = document.getElementById("prenom").value;
	var leAge = document.getElementById("age").value;
	var form = document.getElementById("formu");
	leId.value=id;
	form.submit();
}

function animal(id) {
	var leId = document.getElementById("hidAni");
	var form = document.getElementById("formAni");
	leId.value=id;
	form.submit();
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="deco.jsp">
		<input type="submit" value="Deconnexion" name="dec" />
	</form>
	<%
		PersonDAO pdao = new PersonDAO();
		Person p = new Person();
		if (request.getParameter("leid") != null) {
			i = Long.parseLong(request.getParameter("leid"));
			p = pdao.findById(i);
	%>
	<h1>
		Modification de la personne :<%=p.getName()%></h1>
	<%
		} else {
	%><h1>Création d'une personne</h1>
	<%
		p.setName("");
			p.setPrenom("");
			p.setAge(0);

		}
	%>
	<form action="redirectModif.jsp" method="post" id="formu">
		<label for="name">Nom: <input type="text" id="name"
			name="lename" value="<%=p.getName()%>" /></label> <label for="prenom">Prénom:
			<input type="text" id="prenom" name="leprenom"
			value="<%=p.getPrenom()%>" />
		</label>
		<%
			if (p.getAge() == 0) {
		%><label for="age">Age: <input type="text" id="age"
			name="leage" value="" /></label>
		<%
			} else {
		%><label for="age">Age: <input type="text" id="age"
			name="leage" value="<%=p.getAge()%>" /></label>
		<%
			}
		%>
		<input type="submit" value="Envoyer"
			onclick="mySubmit(<%=request.getParameter("leid")%>)" /> <input
			type="hidden" value="" id="idUpCreate" name="leIdUpCreate" />
	</form>
	<%
		if (request.getParameter("leid") != null) {
	%>
	<form action="animal.jsp" method="post" id="formAni">
		<input type="button" id="clicAni" value="Ajouter un animal"
			onclick="animal(<%=request.getParameter("leid")%>)" /> <input
			type="hidden" value="" id="hidAni" name="leHidAni" />
	</form>
	<%
		}
	%>
</body>
</html>