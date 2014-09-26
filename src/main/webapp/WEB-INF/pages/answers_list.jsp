<!DOCTYPE html>
<HTML>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <link type="text/css" rel="stylesheet" href="/bootstrap/bootstrap.css"/>
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
        $(document).ready(
                function () {
                    $(".btn-success").click(
                            function (button) {
                                button.preventDefault();
                                var markCode = 1;
                                var answerID = $(this).attr('id');
                                answerID = answerID.substring(4);
                                $.ajax({type: "POST",
                                url : "/teacher/evaluate.json",
                                data: {mark: 'TRUE', answer_id: answerID},
                               // contentType: "application/json",
                                contentType : 'application/x-www-form-urlencoded',
                                dataType: 'json',
                                success: function(response){
                                    if (response){
                                        var txt = $(button.currentTarget).closest('.clickedparent').find('textarea')[1];
                                        $(txt).removeClass("wrong");
                                        $(txt).addClass("good");
                                    }
                                }});

                            }
                    );
                }
        )
        $(document).ready(
                function () {
                    $(".btn-danger").click(
                            function (button) {
                                button.preventDefault();
                                var answerID = $(this).attr('id');
                                answerID = answerID.substring(4);
                                $.post("/teacher/evaluate.json", {mark: "FALSE", answer_id: answerID}, function(response){
                                    if (response){
                                    var txt = $(button.target).closest(".clickedparent").find('textarea')[1];
                                    $(txt).removeClass("good");
                                    $(txt).addClass("wrong");
                                    }
                                })

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
        <div id="ans-${entry.key.id}" class="span12 clickedparent" style="border-bottom: 3px solid #000000">

            <div class="span12 pagination-centered">
                <label><strong>Question</strong> </label>
                <textarea id="text-${entry.key.id}" disabled="true"

                        >${entry.value.text}</textarea>
            </div>

            <div class="span12 pagination-centered">
                <label><strong>Answer</strong></label>
                <textarea disabled="true"
                          <c:if test="${entry.key.mark==\"TRUE\"}">class="good"</c:if>
                          <c:if test="${entry.key.mark==\"FALSE\"}">class="wrong"</c:if>
                        >${entry.key.text}</textarea>
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