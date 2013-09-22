<!DOCTYPE html>
<HTML>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link type="text/css" rel="stylesheet" href="/bootstrap/bootstrap.css"/>
</head>
<BODY>
<h1 class="text-center"> Непроверенные ответы </h1>

<!-- varStatus could be used there -->

<div id='global-container' class="container">
    <c:forEach var="entry" items="${aMap}">
        <div class="span12" style="border-bottom: 3px solid #000000">

            <div class="span12 pagination-centered">
                <label><strong>Question</strong> </label>
                <textarea disabled="true">${entry.key.text}</textarea>
            </div>

            <div class="span12 pagination-centered">
                <label><strong>Answer</strong></label>
                <textarea disabled="true">${entry.value.text}</textarea>
            </div>

            <div class="span12 pagination-centered">
                <form class="form-horizontal" action="/evaluate_answer.html" method="post">

                    <div class="control-group inline">
                        <button id="later" name="singlebutton" class="btn btn-warning">Later</button>
                        <button id="correct" name="singlebutton" class="btn btn-success">Correct</button>
                        <button id="wrong" name="singlebutton" class="btn btn-danger">Wrong</button>
                    </div>

                </form>
            </div>

        </div>
    </c:forEach>
</div>


</BODY>