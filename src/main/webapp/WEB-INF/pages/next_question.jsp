<!DOCTYPE html>
<HTML>
<head>
    <link type="text/css" rel="stylesheet" href="/bootstrap/bootstrap.css"/>
    <script type="text/javascript" src="/js/jquery-1.10.2.js">
    </script>

    <script type="text/javascript">
        var saveAns = function() {
            var data = $('form').serialize();
            $.post("/submit_answer-json.json", data);
        };
        $(document).ready(function () {
            setInterval(saveAns, 10000);
        });
    </script>
</head>

<BODY>
<div id="global-container" class="container">
    <h1 class="text-center"> Question # ${question_info.question.id} </h1>

    <div id="main-container" class="row main-container">
        <div id="center-container" class="span12">
            <textarea disabled="true" name="text" rows="10" class="span12">
                ${question_info.question.text}</textarea>
        </div>
    </div>

    <form action="/submit_answer.html" method="post">
        <input type="hidden" name="studentId" value="${question_info.exam.studentId}">
        <input type="hidden" name="examId" value="${question_info.exam.id}">
        <input type="hidden" name="questionId" value="${question_info.question.id}">

        <div id="answer-container" class="span8">
            <textarea name="text" rows="12" class="input-block-level span8">

            </textarea>
        </div>
        <button class="btn btn-large btn-primary ">
            submit answer
        </button>
    </form>

</div>
</BODY>
</HTML>