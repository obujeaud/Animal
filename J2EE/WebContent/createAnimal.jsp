<%@page import="persistance.dao.AnimalDAO"%>
<%@page import="business.entities.Animal"%>
<%@page import="persistance.dao.SpeciesDAO"%>
<%@page import="business.entities.Species"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	List<Species> lspe = new SpeciesDAO().findList();
%>
<script>
function mySubmit(id){
	var nom = document.getElementById("textNameAni").value;
	var sex = document.getElementById("textSexAni").value;
	var pelage = document.getElementById("textCoatAni").value;
	var spe = document.getElementById("textSpecieAni").value;
	var formulaire = document.getElementById("formuCreateA");
	var hid = document.getElementById("hidUpAni");
	hid.value = id;
	formulaire.submit();
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
		Animal a = new Animal();
		AnimalDAO adao = new AnimalDAO();
		String req = request.getParameter("leid");
		if (req == null) {
	%>

	<h1>Création d'un animal</h1>

	<form action="redirectAnimal.jsp" method="post" id="formuCreateA">
		<label for="nameAni">Nom : <input type="text" id="textNameAni"
			name="leNameAni" /></label> <label for="sexAni">Sexe : <select
			id="textSexAni" name="leSexAni">
				<option id="f">F</option>
				<option id="m">M</option>
		</select></label> <label for="coatAni">Pelage : <input type="text"
			id="textCoatAni" name="leCoatAni" /></label> <label for="specieAni">Espece
			: <select id="textSpecieAni" name="leSpecieAni">
				<%
					int i = 1;
						for (Species s : lspe) {
				%><option id="<%=i%>" value="<%=s.getId()%>"><%=s.getId()%></option>
				<%
					}
				%>
		</select>
		</label> <input type="submit" value="Créer" id="subAni" onclick="mysubmit()" />
	</form>
	<%
		} else {
			a = adao.findById(Long.parseLong(request.getParameter("leid")));
	%>

	<h1>
		Modification de l'animal
		<%=a.getName()%></h1>

	<form action="redirectAnimal.jsp" method="post" id="formuCreateA">
		<label for="nameAni">Nom : <input type="text" id="textNameAni"
			name="leNameAni" value="<%=a.getName()%>" /></label> <label for="sexAni">Sexe
			: <select id="textSexAni" name="leSexAni">
				<option id="f">F</option>
				<option id="m">M</option>
		</select>
		</label> <label for="coatAni">Pelage : <input type="text"
			id="textCoatAni" name="leCoatAni" value="<%=a.getCoat_color()%>" /></label>
		<label for="specieAni">Espece : <select id="textSpecieAni"
			name="leSpecieAni">
				<%
					int i = 1;
						for (Species s : lspe) {
				%><option id="<%=i%>"><%=s.getId()%></option>
				<%
					}
				%>
		</select>
		</label> <input type="submit" value="Modifier" id="subAni"
			onclick="mySubmit(<%=req%>)" /> <input type="hidden" value=""
			id="hidUpAni" name="leHidUpAni" />
	</form>
	<%
		}
	%>
</body>
</html>