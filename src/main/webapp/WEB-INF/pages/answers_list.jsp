<!DOCTYPE html>
<HTML>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link type="text/css" rel="stylesheet" href="/bootstrap/bootstrap.css"/>
    <script type="text/javascript" src="/js/jquery-1.10.2.js"></script>
    <script>
        $(document).ready(
                function () {
                    $(".btn-success").click(
                            function (button) {
                                button.preventDefault();
                                var markCode = 1;
                                var answerID = $(this).attr('id');
                                answerID = answerID.substring(4);
                                $.post("/evaluate.json", {markCode: markCode, answerID: answerID})
                                var newId = "#ans-" + answerID;
                                $(newId).hide();
                            }
                    );
                }
        )
        $(document).ready(
                function () {
                    $(".btn-danger").click(
                            function (button) {
                                button.preventDefault();
                                var markCode = 2;
                                var answerID = $(this).attr('id');
                                answerID = answerID.substring(4);
                                $.post("/evaluate.json", {markCode: markCode, answerID: answerID})
                                var newId = "#ans-" + answerID;
                                $(newId).hide();
                            }
                    );
                }
        )
        $(document).ready(
                function () {
                    $(".btn-warning").click(
                            function (button) {
                                button.preventDefault();
                                var newId = "#ans-" + $(this).attr('id').substring(4);
                                console.log(newId);
                                $(newId).hide();
                            }
                    );
                }
        )
    </script>
</head>
<BODY>
<h1 class="text-center"> Непроверенные ответы </h1>

<!-- varStatus could be used there -->

<div id='global-container' class="container">
    <c:forEach var="entry" items="${aMap}">
        <div id="ans-${entry.value.id}" class="span12" style="border-bottom: 3px solid #000000">

            <div class="span12 pagination-centered">
                <label><strong>Question</strong> </label>
                <textarea disabled="true">${entry.key.text}</textarea>
            </div>

            <div class="span12 pagination-centered">
                <label><strong>Answer</strong></label>
                <textarea disabled="true">${entry.value.text}</textarea>
            </div>

            <div class="span12 pagination-centered">
                <form class="form-horizontal" action="/evaluate.html" method="post">

                    <div class="control-group inline">
                        <button id="lat-${entry.value.id}" class="btn btn-warning">Later</button>
                        <button id="suc-${entry.value.id}" class="btn btn-success">Correct</button>
                        <button id="wro-${entry.value.id}" class="btn btn-danger">Wrong</button>
                    </div>

                </form>
            </div>

        </div>
    </c:forEach>
</div>


</BODY>