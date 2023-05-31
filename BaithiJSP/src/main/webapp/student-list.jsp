
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
  <link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<h1>Product List</h1>
<a class="button add-button" href="students?action=new">Add New Student</a>
<table>
  <tr style="background-color: chartreuse">
    <th>ID</th>
    <th>Name</th>
    <th>MÃ HS</th>
    <th>Lớp</th>
    <th>Điểm</th>
    <th>Action</th>
  </tr>
  <c:forEach var="student" items="${studentList}">
    <tr class="tr">
      <td>${student.id}</td>
      <td>${student.name}</td>
      <td>${student.maHS}</td>
      <td>${student.classHS}</td>
      <td>${student.point}</td>
      <td>
        <a class="button" href="students?action=edit&id=${student.id}">Edit</a>
        <a class="button" href="students?action=delete&id=${student.id}"
           onclick="return confirm('Are you sure you want to delete this student?')">Delete</a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
