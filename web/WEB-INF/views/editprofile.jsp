<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" type="text/css" href="media-queries.css">
    <title>Orders</title>
</head>
<body>
<div class="wrapper">
    <div class="header">
        <div class="headerContent">
            <div class="logo"><a href=""><span class="pink">Pink <span class="pink">Flamingo</span><span class="gray">.com</span></span></a></div>
            <ul class="nav">
                <c:url value="/home.html" var="home"/>
                <li><a href="${home}" class="active">Home</a></li>
                <li><a href="#">About</a></li>
                <c:url value="/userlist.html" var="userlist"/>
                <li><a href="${userlist}">Freelancers</a></li>
                <li><a href="#">Works</a></li>
                <c:if test="${user ne null}">
                    <c:url value="/toprofile.html" var="toprofile"/>
                    <li><a href="${toprofile}?userid=${user.id}">Profile</a></li>
                    <c:url value="/exit.html" var="exit"/>
                    <li><a href="${exit}">Exit</a></li>
                </c:if>
                <c:if test="${user eq null}">
                    <c:url value="/toregistration.html" var="registration"/>
                    <li><a href="${registration}">Registration</a></li>
                    <c:url value="/tologin.html" var="login"/>
                    <li><a href="${login}">Log In</a></li>
                </c:if>
            </ul>
        </div>
    </div>
    <div class="content">
        <div class="leftCol">
            <ul class="leftNav">
                <c:if test="${curuser.role eq 'CLIENT'}">
                    <c:url value="/toaddprofile.html" var="addorder"/>
                    <li><a href="${addorder}">Add project</a></li>
                </c:if>
                <li>
                    <form>
                        <div class="form__label">Search</div>
                        <input type="search" contenteditable="false">
                        <input type="button" name="Search" value="Search">
                    </form>
                </li>
            </ul>
        </div>
        <div class="main">
            <form action="saveprofile.html" method="post" class="form">
                <p>First name: <input type="text" placeholder="First name" value="${user.firstName}" name="firstname"></p>
                <p>Last name: <input type="text" placeholder="Last name" value="${user.lastName}" name="lastname"></p>
                <c:forEach var="elem" items="${skills}">
                    <p><input type="checkbox" name="${elem.id}" checked>${elem.name}</p>
                </c:forEach>
                <c:forEach var="elem" items="${lastcategories}">
                    <p><input type="checkbox" name="${elem.id}">${elem.name}</p>
                </c:forEach>
                <button type="submit">Save</button>
                <button type="reset">Reset</button>
            </form>
        </div>
    </div>
    <div class="footer">
        <p>&copy; Footer content <a href="#">Link footer</a></p>
    </div>
</div>
<script src="css3-mediaqueries.js"></script>
</body>
</html>