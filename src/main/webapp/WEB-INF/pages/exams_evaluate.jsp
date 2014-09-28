<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<HTML>
<head>
    <link type="text/css" rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"/>
    <%--<link type="text/css" rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"/>--%>
    <link href="/bootstrap/style.css" rel="stylesheet">
    <%--<link href="http://startbootstrap.com/templates/modern-business/css/modern-business.css" rel="stylesheet">--%>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css">

</head>
<BODY>
<%@include file="header.jsp" %>


<div id="global-container" class="global-container">
    <div class="container">
        <h1 class='text-center'>Непроверенные экзамены</h1>
        <div class="row">

            <c:forEach var="ex" items="${exams}">

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a href="/teacher/evaluate.html?exam_id=${ex.id}" class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion">${ex.id} ${ex.course.name} <fmt:formatDate value="${ex.timeStart}" pattern="MM/dd/yyyy"/></a>
                        </h4>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</BODY>
</HTML>