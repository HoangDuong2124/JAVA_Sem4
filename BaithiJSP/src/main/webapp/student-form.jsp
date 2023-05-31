

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        body{
            font-family: Arial,sans-serif;
            margin: 20px;
        }
        h1{color:#333;}
        form{
            width: 300px;
            margin-top: 20px;
        }
        label{
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"]{
            width: 100px;
            padding: 5px;
            margin-bottom: 10px;
        }
        input[type="submit"],
        a.button{
            display: inline-block;
            background-color: #4CAF50;
            color: #fff;
            padding: 8px 16px;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        input[type="submit"]:hover,
        a.button:hover{
            background-color: #45a049;
        }
        a.button{
            margin-left: 5px;
        }
    </style>
</head>
<body>
<h1>Student Form</h1>
<c:choose>
    <c:when test="${empty student.id}">
        <form method="POST" action="students?action=create">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name">
            <br><br>
            <label for="maHS">Mã học sinh:</label>
            <input type="text" id="maHS" name="maHS">
            <br><br>
            <label for="classHS">Lớp:</label>
            <input type="text" id="classHS" name="classHS">
            <br><br>
            <label for="point">Điểm:</label>
            <input type="text" id="point" name="point">
            <br><br>
            <input type="submit" value="Create">
            <a class="button" href="students">Cancel</a>
        </form>
    </c:when>
    <c:otherwise>
        <form method="POST" action="students?action=update">
            <input type="hidden" name="id" value="${student.id}">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${student.name}">
            <br><br>
            <label for="maHS">Mã học sinh:</label>
            <input type="text" id="maHS" name="maHS" value="${student.maHS}">
            <br><br>
            <label for="classHS">Lớp:</label>
            <input type="text" id="classHS" name="classHS" value="${student.classHS}">
            <br><br>
            <label for="point">Điểm:</label>
            <input type="text" id="point" name="point" value="${student.point}">
            <br><br>
            <input type="submit" value="Update">
            <a class="button" href="students">Cancel</a>
        </form>
        <form method="POST" action="students?action=delete">
            <input type="hidden" name="id" value="${student.id}">
            <input class="button" type="submit" value="Delete">
        </form>
    </c:otherwise>
</c:choose>
</body>
</html>
