<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<style>
    <%@include file="css/styles.css"%>
</style>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users Test</title>

    <style>
        tr:first-child {
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>

</head>

<body>

<h2>List of Users</h2>

<div>
    <br/>
    <br/>
    <form:form action="/search/" method="get">
        <input type="text" id="ssn" name="ssn" placeholder="Input SSN"/>
        <input type="submit" value="Search by SSN"/>
    </form:form>
</div>
<div>
    <input type="text" id="text-to-find" value="">
    <input type="button" onclick="javascript: FindOnPage('text-to-find'); return false;" value="Search by text"/>
</div>

<table>

    <tr>
        <td>NAME</td>
        <td>AGE</td>
        <td>SSN</td>
        <td>Created date</td>
        <td>IsAdmin</td>
        <td>Edit</td>
        <td>Delete</td>
        <td></td>
    </tr>
    <c:forEach items="${users}" var="user">
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
    </c:forEach>
</table>
<br/>
<br/>
<a href="<c:url value='/new' />">Add New User</a>
<br/>
<br/>

</div>
</body>

<script type="text/javascript">
    var lastResFind = "";
    var copy_page = "";
    function TrimStr(s) {
        s = s.replace(/^\s+/g, '');
        return s.replace(/\s+$/g, '');
    }
    function FindOnPage(inputId) {
        var obj = window.document.getElementById(inputId);
        var textToFind;

        if (obj) {
            textToFind = TrimStr(obj.value);
        } else {
            alert("Text not found");
            return;
        }
        if (textToFind == "") {
            alert("You did not enter anything");
            return;
        }

        if (document.body.innerHTML.indexOf(textToFind) == "-1")
            alert("Nothing found");

        if (copy_page.length > 0)
            document.body.innerHTML = copy_page;
        else copy_page = document.body.innerHTML;


        document.body.innerHTML = document.body.innerHTML.replace(eval("/name=" + lastResFind + "/gi"), " ");
        document.body.innerHTML = document.body.innerHTML.replace(eval("/" + textToFind + "/gi"), "<a name=" + textToFind + " style='background:red'>" + textToFind + "</a>");
        lastResFind = textToFind;
        window.location = '#' + textToFind;
    }
</script>
</html>