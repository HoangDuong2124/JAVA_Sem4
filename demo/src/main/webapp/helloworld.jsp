<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 5/10/2023
  Time: 2:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.demo.*" %>
<html>
<head>
    <title>JSP-HELLOWORLD</title>
</head>
<body>
   <h1>HELLOWORLD OF JAVAA</h1>
   The time on the server is <%= new java.util.Date()%>
   <br>
Coverting a string to uppercase: <%= new String("Hello ahihi").toUpperCase()%>
<br>
25 multiplied by 4 equals: <%= 25*4%>
<br>
Í 75 less than 69? <%= 75<69%>
<br>
<%
  for (int i=0;i<6;i++){
      out.println("<br>I really code:" +i);
  }
%>
<br>
Let have some fun:<%= FunUtils.makeItLower("HI HI HI HI") %>
<br>
Request language: <%=request.getLocale() %>
   <br>
Request user agent: <%=request.getHeader("User-Agent")%>
</body>
</html>
