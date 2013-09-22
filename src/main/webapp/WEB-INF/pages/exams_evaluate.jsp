<!DOCTYPE html>
<HTML>
<head>
    <link type="text/css" rel="stylesheet" href="/bootstrap/bootstrap.css"/>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<BODY>
<h1 class='text-center'>Неоцененный вопросы</h1>

<div id="global-container" class="global-container">
    <div class="container">
        <div class="row">
            <c:forEach var="ex" items="${examIds}">
                <div class="span4">
                    <form class="form-horizontal" action="/evaluate.html" method="post">
                        <fieldset>
                            <!-- Form Name -->
                            <legend>Exam N ${ex}</legend>
                            <!-- Button -->
                            <div class="control-group">
                                <label class="control-label" for="cont">Course description</label>

                                <div class="controls">
                                    <button id="cont" name="cont" class="btn btn-success">Continue</button>
                                </div>
                            </div>
                            <input type="hidden" name="examId" value="${ex}"/>
                        </fieldset>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</BODY>
</HTML>