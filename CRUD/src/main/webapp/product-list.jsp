<%--
  Created by IntelliJ IDEA.
  User: 1
  Date: 5/29/2023
  Time: 12:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
  <link type="text/css" rel="stylesheet" href="css/Style.css">
</head>
<body>
  <h1>Product List</h1>
  <a class="button add-button" href="products?action=new">Add New Product</a>
  <table>
    <tr style="background-color: chartreuse">
      <th>ID</th>
      <th>Name</th>
      <th>Price</th>
      <th>Image</th>
      <th>Action</th>
    </tr>
    <c:forEach var="product" items="${productList}">
       <tr class="tr">
         <td>${product.id}</td>
         <td>${product.name}</td>
         <td>${product.price}$</td>
         <td><img  src="${product.image}" alt="Product Image" class="image-product"></td>
         <td>
           <a class="button" href="products?action=edit&id=${product.id}">Edit</a>
           <a class="button" href="products?action=delete&id=${product.id}"
              onclick="return confirm('Are you sure you want to delete this product?')">Delete</a>
         </td>
       </tr>
    </c:forEach>
  </table>
</body>
</html>
