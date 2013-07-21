<!DOCTYPE html>
<HTML>
<head>
    <link type="text/css" rel="stylesheet" href="/bootstrap/bootstrap.css"/>
</head>
<BODY>
<h1 class='text-center'>Вопрос № ${getQuestionPage.id}</h1>
<div id="global-container" class="container">

    <div id="main-container" class="row main-container">
        <div id="center-container" class="span12">
            <form action="savequestion.html" method="post">
            <textarea name="text" rows="10" class="input-block-level span12"
                      style="width:100%;">${getQuestionPage.text}</textarea>
                <input type="hidden" name="id" value="${getQuestionPage.id}"/>
                <select name="type">
                    <option disabled>Выбор нескольких</option>
                    <option selected value="SIMPLE">Обычный</option>
                    <option value="RADIOBUTTON">Выбор одного</option>
                </select>
                <button type="submit" class="btn btn-primary pull-right">Сохранить</button>
            </form>
        </div>
    </div>
</div>
</BODY>
</HTML>