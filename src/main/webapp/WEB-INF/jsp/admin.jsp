<%--
  Created by IntelliJ IDEA.
  User: Galina
  Date: 22.07.2017
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>admin page</title>
</head>
<body>
Hello admin!!!<p/>
List of users
<c:forEach var="row" items="${users}">
    Name: ${row.username}<br/>
    Email: ${row.email}<br/>
    authority: ${row.authority}<br/>
    =================<br/>
</c:forEach>
</body>
</html>
