<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<HTML>
<head>
    <link type="text/css" rel="stylesheet" href="/bootstrap/bootstrap.css"/>
</head>
<BODY>

<form class="form-horizontal" action="/start.html" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend>Welcome, fellow student!</legend>

        <!-- Button (Double) -->
        <div class="control-group">
            <label class="control-label" for="start"></label>

            <div class="controls">
                <button id="start" name="start" class="btn btn-success">Start Text</button>
                <c:if test="${showRepair}">
                    <a href="/repair.html" class="btn btn-warning">Continue Test</a>
                </c:if>

            </div>
        </div>

        <div class="control-group">
            <label class="control-label"></label>

            <div class="controls">
                <div class="input-prepend">
                    <span class="add-on">Выберите курс</span>
                    <select name="course_id">
                        <c:forEach var="course" items="${courses}">
                            <option value="${course.id}">${course.name}</option>
                        </c:forEach>
                    </select>

                </div>
            </div>
        </div>
    </fieldset>
</form>

<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_TEACHER')">

<form class="form-horizontal" action="/teacher/exams_to_evaluate.html" method="get">
    <fieldset>
        <!-- Form Name -->
        <legend>Проверить ответы</legend>
        <!-- Text input-->
        <div class="control-group">
            <label class="control-label"></label>

            <div class="controls">
                <div class="input-prepend">
                    <span class="add-on">Выберите курс</span>
                    <select name="course_id">
                        <option value="0">Все курсы</option>
                        <c:forEach var="course" items="${courses}">
                            <option value="${course.id}">${course.name}</option>
                        </c:forEach>
                    </select>

                </div>
            </div>
        </div>
        <!-- Button -->
        <div class="control-group">
            <label class="control-label" for="begin"></label>
            <div class="controls">
                <button id="begin" class="btn btn-primary">Проверить</button>
            </div>
        </div>
    </fieldset>


</form>
</sec:authorize>


</BODY>
</HTML>