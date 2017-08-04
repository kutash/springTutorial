<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/delete.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>List of offers</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/">Home</a><p/>

<a href="${pageContext.request.contextPath}/createoffer">Add new offer</a><p/>

<sec:authorize access="!isAuthenticated()">
<a href="${pageContext.request.contextPath}/newaccount">Add account</a><p/>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_ADMIN')">
<a href="${pageContext.request.contextPath}/admin">Admin page</a><p/>
</sec:authorize>

<sec:authorize access="!isAuthenticated()">
<a href="<c:url value="/login"/>">login</a><p/>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <c:url value="/logout" var="logoutUrl" />
<form id="logout" action="${logoutUrl}" method="post" >
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:if test="${pageContext.request.userPrincipal.name != null}">
<a href="javascript:document.getElementById('logout').submit()">Logout</a><p/>
</c:if>
</sec:authorize>
<h3>All offers</h3><br/>
<c:forEach var="row" items="${offers}">
<a href="<c:url value='/message?uid=${row.username}'/>">${row.username}</a><br/>
Id:${row.id}<br/>
Name: ${row.name}<br/>
Text: ${row.text}<br/>
Email: ${row.user.email}<br/>
    =================<br/>
</c:forEach>
<sec:authorize access="isAuthenticated()">
<h3>My offers</h3><br/>
<c:forEach var="row2" items="${offersMy}">
    Id:${row2.id}<br/>
    Name: ${row2.name}<br/>
    Text: ${row2.text}<br/>
    Email: ${row2.user.email}<br/>
    <form action="${pageContext.request.contextPath}/delete/${row2.id}?${_csrf.parameterName}=${_csrf.token}" method="post">
    <input type="submit" value="delete" class="delete">
    </form>
    <form action="${pageContext.request.contextPath}/offer/${row2.id}">
        <input type="submit" value="edit" class="edit">
    </form>
    =================<br/>
</c:forEach>
</sec:authorize>
