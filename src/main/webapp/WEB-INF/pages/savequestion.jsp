<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String name = request.getParameter("username");
    session.setAttribute("theName" , name);
%>
<html>
<head>
    <A HREF="/nextpage.html">Continue</A>
</head>
<body>

</body>
</html>