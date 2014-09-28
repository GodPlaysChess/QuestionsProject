<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<HTML>
<head>
    <link type="text/css" rel="stylesheet" href="/bootstrap/bootstrap.css"/>
</head>
<BODY>
<h1 class="text-center">Incomplete tests</h1>

<div id='global-container' class="main-container">
    <div class="container">
        <div class="row">
            <c:forEach var="exam" items="${exam_list}">
                <div class="span4">
                    <form class="form-horizontal" action="/repair.html" method="post">
                        <fieldset>
                            <!-- Form Name -->
                            <legend>Exam N ${exam.id}</legend>
                            <!-- Button -->
                            <div class="control-group">
                                <label class="control-label" for="cont">Course description</label>

                                <div class="controls">
                                    <button id="cont" name="cont" class="btn btn-success">Continue</button>
                                </div>
                            </div>
                            <input type="hidden" name="examId" value="${exam.id}"/>
                        </fieldset>
                    </form>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</BODY>