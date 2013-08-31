<!DOCTYPE html>
<HTML>
<head>
    <link type="text/css" rel="stylesheet" href="/bootstrap/bootstrap.css"/>
</head>
<BODY>

<div id="global-container" class="container">
    <h1 class="text-center"> Question # ${question_info.question.id} </h1>

    <div id="main-container" class="row main-container">
        <div id="center-container" class="span12">
            <textarea disabled="true" name="text" rows="10" class="input-block-level span12"
                      style="width:100%;">${question_info.question.text}</textarea>
        </div>
    </div>
    <form action="/submit_answer.html" method="post">
        <button class="btn btn-large btn-success pull-left">
            submit answer
        </button>
        <div id="answer-container" class="span8">
            <textarea name="text" rows="12" class="input-block-level span8">

            </textarea>
        </div>
        <input type="hidden" name="examId" value="${question_info.exam.id}">
    </form>
    <form action="/submit_answer.html" method="post">
        <button class="btn btn-large btn-warning pull-right">
            skip question
        </button>
        <input type="hidden" name="examId" value="${question_info.exam.id}">
    </form>

</div>
</BODY>
</HTML>