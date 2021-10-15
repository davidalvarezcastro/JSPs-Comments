<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="es.ubu.lsi.database.Comment"%>
<%@ page import="es.ubu.lsi.database.CommentsDAO"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Comment JSP</title>
</head>
<body>
	<%
		Date date = new Date();
	%>
	<!-- create beans and initialize properties -->
	<jsp:useBean id="comment"
		class="es.ubu.lsi.database.Comment"
		scope="request">
		<jsp:setProperty name="comment" property="*"/>
		<jsp:setProperty name="comment" property="date" value="<%= date %>" />
	</jsp:useBean>
	
	<!-- add comment to database -->
	<%
		CommentsDAO dao = new CommentsDAO();
		dao.add(comment);
	%>
	
	<!-- forward to list jsp -->
	<jsp:forward page="list.jsp"/>
</body>
</html>