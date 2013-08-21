<!DOCTYPE html>
<HTML>
<head>
    <link type="text/css" rel="stylesheet" href="/bootstrap/bootstrap.css"/>
</head>
<BODY>
<h1 class='text-center'>Вопрос № ${questioninfo.Question.id}</h1>
<div id="global-container" class="container">

    <div id="main-container" class="row main-container">
        <div id="center-container" class="span12">
                <textarea name="text" rows="10" class="input-block-level span12"
                          style="width:100%;">${questioninfo.getQuestion().text}</textarea>
        </div>
    </div>
</div>
</BODY>
</HTML>