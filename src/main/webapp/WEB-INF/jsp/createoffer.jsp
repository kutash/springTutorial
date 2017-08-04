<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>create</title>
</head>
<body>
<form:form method="post" action="${pageContext.request.contextPath}/docreate" commandName="offer">

    <table>
        <tr><td><form:input path="id" type="hidden" name="name"></form:input></td></tr>
        <tr><td>Name: </td><td><form:input path="name" name="name" type="text" /><br/>
        <form:errors path="name" cssClass="error"></form:errors></td></tr>
        <tr><td>Your offer: </td><td><form:textarea path="text" name="text" rows="10" cols="10"></form:textarea><br/>
            <form:errors path="text" cssClass="error"></form:errors></td></tr>
        <tr><td> </td><td><input value="Create advert" type="submit" /></td></tr>
    </table>

</form:form>

<a href="${pageContext.request.contextPath}/">Home</a><p/>

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
</body>
