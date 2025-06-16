

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String name = request.getParameter("name");
if (name == null || name.length() == 0) {
%>
<form action="">
<h2>What's your name?</h2>
<p>Name: <input type="text" name="name"/>
<input type="submit" value="Send"/>
</p>
</form>
<%
}
else {
%> 
<h2>Hello <%= name %>!</h2>
<%
}
%>
</body>

</html>