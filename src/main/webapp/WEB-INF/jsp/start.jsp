<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />
<h2>Send Message</h2>

User:${currentUser.name}
<form:form method="post" commandName="message">

    <input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
    <input type="hidden" name="_eventId" value="send" />

    <table class="formtable">
        <tr>
            <td class="label">Your name:</td>
            <td><form:input class="control" path="sender" type="text" value="${fromName}"/><br />
                <div class="error">
                    <form:errors path="sender"></form:errors>
                </div></td>
        </tr>
        <tr>
            <td class="label">Your email:</td>
            <td><form:input class="control" path="email" type="text" value="${fromEmail}"/><br />
                <div class="error">
                    <form:errors path="email"></form:errors>
                </div></td>
        </tr>
        <tr>
            <td class="label">Subject:</td>
            <td><form:input class="control" path="subject" type="text" /><br />
                <div class="error">
                    <form:errors path="subject"></form:errors>
                </div></td>
        </tr>
        <tr>
            <td class="label">Your Message:</td>
            <td><form:textarea class="control" path="text" name="name"
                             type="text" /><br />
                <div class="error">
                    <form:errors path="text"></form:errors>
                </div></td>
        </tr>
        <tr>
            <td class="label"></td>
            <td><input class="control" value="Send message" type="submit" /></td>
        </tr>
    </table>

</form:form>

