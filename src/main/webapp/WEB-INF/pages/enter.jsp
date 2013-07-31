<!DOCTYPE html>
<HTML>
<head>
    <link type="text/css" rel="stylesheet" href="/bootstrap/bootstrap.css"/>
</head>
<BODY>
<h1 class='well-large text-center'>Добро Пожаловать!</h1>
<form class="well pagination-centered" action="savename.html" method="post">
    <input type="text" class="span4" name="lastName" placeholder="Enter your name here"/>
    <%--<input type="hidden" name="id" value="${saveName.id}"/>
    <input type="hidden" name="role" value="${saveName.role}"/>
    <input type="hidden" name="name" value="StudentsName"/>--%>
    <button type="sumbit" class="btn">Enter</button>
    <% String name = request.getParameter("username");
        session.setAttribute("lastName" , name);
    %>
</form>
</BODY>
</HTML>