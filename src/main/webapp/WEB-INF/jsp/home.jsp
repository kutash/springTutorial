<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-3.2.1.min.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/link.js"></script>--%>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>home</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <a href="<c:url value='/messages'/>">Messages (<span id="numberMessages">0</span>)</a>
</sec:authorize>

<h1>Home</h1>
<a href="${pageContext.request.contextPath}/offers">Offers</a><p/>

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

<script type="text/javascript">
    function updateMessageLink(data) {
        $("#numberMessages").text(data.number);
    }

    function onLoad() {
        updatePage();
        window.setInterval(updatePage, 60000);
    }

    function updatePage() {
        $.getJSON("<c:url value='/getmessages'/>", updateMessageLink);
    }

    $(document).ready(onLoad);
</script>
</body>
</html>