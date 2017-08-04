<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/confirm.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Create New Account</title>
</head>
<body>



<sf:form method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user" id="details">

<table class="formtable">
<tr><td class="label">Username: </td><td><sf:input class="control" path="username" name="username" type="text" /><br/>
    <div class="error"><sf:errors path="username"></sf:errors></div></td></tr>
<tr><td class="label">Email: </td><td><sf:input class="control"  path="email" name="email" type="text" /><br/>
    <div class="error"><sf:errors path="email"></sf:errors></div></td></tr>
<tr><td class="label">Password: </td><td><sf:input class="control"  id="password" path="password" name="password" type="password" /><br/>
    <div class="error"><sf:errors path="password"></sf:errors></div></td></tr>
<tr><td class="label">Confirm Password: </td><td><input class="control" id="confirmpass"  name="confirmpass" type="password" /><br/>
    <div id="matchpass"></div>
</td></tr>
<tr><td class="label"> </td><td><input class="control"  value="Create account" type="submit" /></td></tr>
</table>

</sf:form>

</body>
</html>