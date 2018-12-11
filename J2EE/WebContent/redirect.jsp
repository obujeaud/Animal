<%@page import="service.MyService"%>
<%@page import="persistance.dao.j22.UserDAO"%>
<%@page import="business.entities.User"%>
<%@page import="hash.Encoder"%>
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
User u = new User(request.getParameter("login"), request.getParameter("pass"));
u.setMdp(Encoder.getInstance().sha256Encoder(u.getMdp()));
MyService ms = new MyService();
if((u = ms.checkUser(u)) != null){
	session.setAttribute("login", request.getParameter("login"));
	if(ms.isAdmin(u)){
		session.setAttribute("admin", "Oui");
	}else{
		session.setAttribute("admin", "Non");
	}
	response.sendRedirect("person.jsp");
}else{
	response.sendRedirect("error.jsp");
} %>
</body>
</html>