<%@page import="business.entities.Person"%>
<%@page import="persistance.dao.PersonDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<script>
		
	<%if (request.getParameter("id") != null) {
		new PersonDAO().deleteById(Long.parseLong(request.getParameter("id")));
		response.sendRedirect("person.jsp?iddeleted=" + request.getParameter("id"));
	}else{
		PersonDAO pdao = new PersonDAO();
		Person p = new Person();
		if(request.getParameter("leIdUpCreate") != "") {
			p = pdao.findById(Long.parseLong(request.getParameter("leIdUpCreate")));
			p.setName(request.getParameter("lename"));
			p.setPrenom(request.getParameter("leprenom"));
			p.setAge(Integer.parseInt(request.getParameter("leage")));
			pdao.updateById(p);
			response.sendRedirect("person.jsp");
				} else {
					p.setName(request.getParameter("lename"));
					p.setPrenom(request.getParameter("leprenom"));
					p.setAge(Integer.parseInt(request.getParameter("leage")));
					pdao.create(p);
					response.sendRedirect("person.jsp");
				}
			}%>
		
	</script>
</body>
</html>