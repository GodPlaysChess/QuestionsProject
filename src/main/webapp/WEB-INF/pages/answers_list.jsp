<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<HTML>
<head>

    <%--<link type="text/css" rel="stylesheet" href="/bootstrap/bootstrap.css"/>--%>
    <link type="text/css" rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"/>
    <link href="/bootstrap/style.css" rel="stylesheet">
    <%--<link href="http://startbootstrap.com/templates/modern-business/css/modern-business.css" rel="stylesheet">--%>
    <style type="text/css">
        .wrong {
            background-color: #ffc057 !important;
            color: #000000
        }

        .good {
            background-color: #c3ffd2 !important;
            color: #000000;
        }
    </style>
    <script type="text/javascript" src="/js/jquery-1.10.2.js"></script>
    <script>
        var onclick = function (button) {
            button.preventDefault();
            var mark = button.data.mark;
            var answerID = $(this).attr('id');
            answerID = answerID.substring(4);
            $.ajax({type: "POST",
                url : "/teacher/evaluate.json",
                data: {mark: mark, answer_id: answerID},
                dataType: 'json',
                success: function(response){
                    debugger;
                    if (response){
                        var txt = $(button.currentTarget).closest('.clickedparent');
                        if (mark === 'TRUE') {
                            $(txt).removeClass("wrong");
                            $(txt).addClass("good");
                        } if (mark === 'FALSE') {
                            $(txt).removeClass("good");
                            $(txt).addClass("wrong");
                        }
                        if ($('#only-unmarked-input').is(':checked')) {
                            $(txt).slideUp("slow");
                        }
                    }
                }});

        };
        $(document).ready(
                function () {
                    $(".btn-success").click({ mark: "TRUE"}, onclick);
                    $(".btn-danger").click({ mark: "FALSE"}, onclick);
                    $('#only-unmarked-input').change(function() {
                        var elementsToHide = $('.clickedparent.wrong, .clickedparent.good');
                        console.log(elementsToHide);
                        if ($('#only-unmarked-input').is(':checked')) {
                            elementsToHide.slideUp("fast");
                        } else {
                            elementsToHide.slideDown("slow");
                        }
                    });
                    $('#only-unmarked-input').click();
                }
        );
    </script>
</head>
<BODY>
<%@include file="header.jsp" %>
<h1 class="text-center"> Непроверенные ответы </h1>

<!-- varStatus could be used there -->

<div id='global-container' class="container">
    <div class="checkbox">
        <label>
            <input type="checkbox" id="only-unmarked-input"> Только непроверенные
        </label>
    </div>
    <c:forEach var="entry" items="${aMap}">
        <div class="row clickedparent <c:if test="${entry.key.mark==\"TRUE\"}">good</c:if>
        <c:if test="${entry.key.mark==\"FALSE\"}">wrong</c:if>">
            <div class="col-md-6 ">
                    ${entry.value.text}
            </div>
            <div class="col-md-6">
                <c:out value="${entry.key.text}"/>
            </div>
            <div class="span12 pagination-centered">
                <div class="form-horizontal">

                    <div class="control-group inline">
                        <button id="suc-${entry.key.id}" class="btn btn-success">Correct</button>
                        <button id="wro-${entry.key.id}" class="btn btn-danger">Wrong</button>
                    </div>
                </div>
            </div>

        </div>

    </c:forEach>
</div>
</BODY>