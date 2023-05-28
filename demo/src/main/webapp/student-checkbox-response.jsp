<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 5/12/2023
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
The student is confirmed: ${param.firstName} ${param.lastName}
<br><br>
Favorite Programing Languages: <br>

<ul>
  <%
    String[] langs = request.getParameterValues("favoriteLanguage");
    for (String tempLang : langs){
      out.println("<li>"+tempLang + "</li>");
    }
  %>
</ul>
</body>
</html>
