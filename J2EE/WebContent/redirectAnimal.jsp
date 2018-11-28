<%@page import="business.entities.Species"%>
<%@page import="business.entities.Animal"%>
<%@page import="business.entities.Person"%>
<%@page import="persistance.dao.PersonDAO"%>
<%@page import="persistance.dao.SpeciesDAO"%>
<%@page import="service.ServiceAnimal"%>
<%@page import="persistance.dao.AnimalDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		ServiceAnimal sa = new ServiceAnimal();
		AnimalDAO adao = new AnimalDAO();
		PersonDAO pdao = new PersonDAO();

		if (request.getParameter("leIdPers") != null) {
			Person p = pdao.findById(Long.parseLong(request.getParameter("leIdPers")));
			for (int i = 1; i <= adao.findList().size(); i++) {
				if (request.getParameter("animal" + i) != null) {
					sa.ajoutAnimal(adao.findById(Long.parseLong(request.getParameter("animal" + i))));
				}
			}
			sa.maj(p);
			response.sendRedirect("person.jsp");
		} else if(request.getParameter("deleteAniList") != null && session.getAttribute("idPers") != null){
			Animal a = adao.findById(Integer.parseInt(request.getParameter("deleteAniList")));
			sa.removeAnimal(session, a);
			response.sendRedirect("person.jsp");
		}else {
			if (request.getParameter("idDelete") != null) {
				adao.deleteById(Long.parseLong(request.getParameter("idDelete")));
				response.sendRedirect("person.jsp");
			} else {
				SpeciesDAO sdao = new SpeciesDAO();
				if (request.getParameter("leHidUpAni") != "") {
					Animal a = adao.findById(Long.parseLong(request.getParameter("leHidUpAni")));
					a.setName(request.getParameter("leNameAni"));
					a.setSex(request.getParameter("leSexAni"));
					a.setCoat_color(request.getParameter("leCoatAni"));
					Species s = sdao.findById(Long.parseLong(request.getParameter("leSpecieAni")));
					a.setS(s);
					adao.updateById(a);
					response.sendRedirect("person.jsp");
				} else {
					Animal a = new Animal();
					a.setName(request.getParameter("leNameAni"));
					a.setSex(request.getParameter("leSexAni"));
					a.setCoat_color(request.getParameter("leCoatAni"));
					Species s = sdao.findById(Long.parseLong(request.getParameter("leSpecieAni")));
					a.setS(s);
					adao.create(a);
					response.sendRedirect("person.jsp");
				}
			}
		}
	%>
</body>
</html>