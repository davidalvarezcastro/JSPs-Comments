<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="es.ubu.lsi.database.Comment"%>
<%@ page import="es.ubu.lsi.database.CommentsDAO"%>
<%@ page import="java.util.Date"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List of Comments</title>
</head>
<body>
	<h1> Comments List</h1>

	<!-- get comments from database -->
	<%
		CommentsDAO dao = new CommentsDAO();
		List<Comment> comments = dao.getComments();
	%>
	
	<!-- show info -->
	<p># comments: <%= comments.size() %></p>
	<c:forEach items="<%= comments %>" var="c">
	    <p>${c.getName()} ${c.getSurname()} [${c.getDate()}] .- ${c.getComments()}</p>
	</c:forEach>

	<a href="./formulario.html">Add Coment</a>

</body>
</html>