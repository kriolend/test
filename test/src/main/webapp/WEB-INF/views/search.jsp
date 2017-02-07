
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style>
  <%@include file="css/styles.css"%>
</style>
<html>
<head>
  <style>
    tr:first-child{
      font-weight: bold;
      background-color: #C6C9C4;
    }
  </style>
  <title></title>
</head>
<body>

<table>

  <tr>
    <td>NAME</td><td>AGE</td><td>SSN</td><td>Created date</td><td>IsAdmin</td><td>Edit</td><td>Delete</td><td></td>
  </tr>
  <tr>
    <td>${user.name}</td>
    <td>${user.age}</td>
    <td>${user.ssn}</td>
    <td>${user.createdDate}</td>
    <td>
      <c:if test="${user.isAdmin == true}">
        <input type="checkbox" name="isAdmin" value="a3" checked disabled><Br>
      </c:if>
      <c:if test="${user.isAdmin == false}">
        <input type="checkbox" name="isAdmin" value="a3" disabled><Br>
      </c:if>

    </td>
    <td><a href="<c:url value='/edit-${user.ssn}-user' />">edit</a></td>
    <td><a href="<c:url value='/delete-${user.ssn}-user' />">delete</a></td>
  </tr>
</table>
<br/>
<br/>
Go back to <a href="<c:url value='/list' />">List of All Users</a>
</body>
</html>
