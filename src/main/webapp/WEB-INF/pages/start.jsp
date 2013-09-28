<!DOCTYPE html>
<HTML>
<head>
    <link type="text/css" rel="stylesheet" href="/bootstrap/bootstrap.css"/>
    <script type="text/javascript" src="/js/jquery-1.10.2.js"></script>
    <script>
        $(document).ready(
                function () {
                    $("#cont").click(
                            function (button) {
                                button.preventDefault();
                                var studentId = $("#studentId").val();
                                var link = "/repair.html?studentId=" + studentId;
                                window.location.href = link;
                            })
                })
  /*      $(document).ready(
                function () {
                    $("#begin").click()(
                            function (button) {
                                button.preventDefault();
                               // var courseId = 0;
                                var courseId = $("#course_id").val();
                                console.log(courseId);
                                var link = "/exams_to_evaluate.html?course_id=" + courseId;
                                window.location.href = link;
                            }
                    )
                }
        )*/
    </script>
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
                <button id="cont" name="cont" class="btn btn-warning">Continue Test</button>
            </div>
        </div>

        <!-- Prepended text-->
        <div class="control-group">
            <label class="control-label" for="courseId"></label>

            <div class="controls">
                <div class="input-prepend">
                    <span class="add-on">course</span>
                    <input id="courseId" name="courseId" class="input-xlarge" placeholder="number here" type="number">
                </div>

            </div>
        </div>

        <!-- Prepended text-->
        <div class="control-group">
            <label class="control-label" for="studentId"></label>

            <div class="controls">
                <div class="input-prepend">
                    <span class="add-on">student</span>
                    <input id="studentId" name="studentId" class="input-xlarge" placeholder="number here" type="number">
                </div>
            </div>
        </div>
    </fieldset>
</form>

<form class="form-horizontal" action="/exams_to_evaluate.html" method="get">
    <fieldset>
        <!-- Form Name -->
        <legend>Evaluate answers</legend>
        <!-- Text input-->
        <div class="control-group">
            <label class="control-label" for="course_id">Select course </label>

            <div class="controls">
                <input id="course_id" name="course_id" type="number" value="0" class="input-small">
            </div>
        </div>
        <!-- Button -->
        <div class="control-group">
            <label class="control-label" for="begin"></label>
            <div class="controls">
                <button id="begin" name="begin" class="btn btn-primary">Begin</button>
            </div>
        </div>
    </fieldset>


</form>


</BODY>
</HTML>