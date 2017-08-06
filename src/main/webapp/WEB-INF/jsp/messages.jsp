<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-3.2.1.min.js"></script>
<link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css" />
<div id="messages">


</div>


<script type="text/javascript">
<!--

    function showReply(i) {
       //alert(i);
       $("#form"+i).toggle();
    }

    function success(data) {
        alert("success")
        $("#form" + data.target).toggle();
        startTimer();
    }

    function error(data) {
        alert("error");
        $("#form" + data.target).toggle();
        startTimer();
    }

    function sendMessage(i, sender, email,subject) {

        var text = ($("#textbox" + i).val());
        var letter = {"target": i, "text": text, "sender": sender, "email": email, "subject":subject, "username": "rusel123"};
        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var headers = {};
        headers[csrfHeader] = csrfToken;
        $.ajax({
             type: 'POST',
             url: "<c:url value="/sendmessage?${_csrf.parameterName}=${_csrf.token}"/>",
             data : JSON.stringify(letter),
             "success": success,
             "error": error,
             contentType: "application/json",
             dataType: "json"
        });

    }

	function showMessages(data) {
        stopTimer();
		$("div#messages").html("");
	
		for(var i=0; i<data.messages.length; i++) {
			var message = data.messages[i];
			
			var messageDiv = document.createElement("div");
			messageDiv.setAttribute("class", "message");
			
			var subjectSpan = document.createElement("span");
			subjectSpan.setAttribute("class", "subject");
			subjectSpan.appendChild(document.createTextNode(message.subject));
			
			var contentSpan = document.createElement("span");
			contentSpan.setAttribute("class", "messagebody");
			contentSpan.appendChild(document.createTextNode(message.text));
			
			var nameSpan = document.createElement("span");
			nameSpan.setAttribute("class", "name");
			nameSpan.appendChild(document.createTextNode(message.sender + " ("));

			var link = document.createElement("a");
			link.setAttribute("class","replylink");
            link.setAttribute("href","#");
            link.setAttribute("onclick","showReply(" + i + ")");
            link.appendChild(document.createTextNode(message.email));
            nameSpan.appendChild(link);
            nameSpan.appendChild(document.createTextNode(")"));

            var replyForm = document.createElement("form");
            replyForm.setAttribute("class", "replyform");
            replyForm.setAttribute("id", "form"+i);
            replyForm.setAttribute("method", "post");

            var csrfForm = document.createElement("input");
            csrfForm.setAttribute("type", "hidden");
            csrfForm.setAttribute("name", "${_csrf.parameterName}");
            csrfForm.setAttribute("value", "${_csrf.token}");

            var textarea = document.createElement("textarea");
            textarea.setAttribute("class", "replayarea");
            textarea.setAttribute("id", "textbox" + i);

            var replyButton = document.createElement("input");
            replyButton.setAttribute("type", "submit");
            replyButton.setAttribute("value", "Reply");
            replyButton.setAttribute("class", "replybutton");
            replyButton.onclick = function(j, sender, email, subject) {
                return function() {
                    sendMessage(j, sender, email, subject);
                }
            }(i, message.sender, message.email, message.subject);

            replyForm.appendChild(textarea);
            replyForm.appendChild(replyButton);
            replyForm.appendChild(csrfForm);

			messageDiv.appendChild(subjectSpan);
			messageDiv.appendChild(contentSpan);
			messageDiv.appendChild(nameSpan);
            messageDiv.appendChild(replyForm);

			$("div#messages").append(messageDiv);
		}
	}

    function onLoad() {
        updatePage();
        startTimer();
    }

    function startTimer() {
        timer = window.setInterval(updatePage, 30000);
    }

    function stopTimer() {
        window.clearInterval(timer);
    }
	
	function updatePage() {
		$.getJSON("<c:url value="/getmessages"/>", showMessages);
	}

	$(document).ready(onLoad);
//-->
</script>
