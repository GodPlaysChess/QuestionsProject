<!DOCTYPE html>
<HTML>
<head>
    <link type="text/css" rel="stylesheet" href="/bootstrap/bootstrap.css"/>
    <script>
        function goToSpecificQuestion() {
            var x = document.getElementById("questionId").value;
            if (x == "" || isNaN(x)) {
                alert("Enter a number");
            }
            location.href = '/question.html?questionid=' + x;
        }
    </script>
</head>
<BODY>
<h1 class='text-center'>Choose your action, <%= session.getAttribute("lastName")%></h1>
<dic class="container">

</dic>

<div id="global-container" class="container">
    <div id="main-container" class="row main-container">
        <div id="center-container" class="span12">

            <button type="submit" class="btn btn-large" onclick="goToSpecificQuestion()">
                get specific question
            </button>
            <input id="questionId" type="number">

            <p/>

            <p/>
            <button id="randq" type="submit" class="btn btn-large"> get random question</button>
            <p/>
            <button type="submit" class="btn btn-large" onclick="location.href='questionlist.html'"> get questions list</button>
        </div>
    </div>
</div>
</BODY>
</HTML>