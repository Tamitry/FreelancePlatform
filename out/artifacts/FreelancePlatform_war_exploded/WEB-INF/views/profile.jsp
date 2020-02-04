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
                <c:if test="${curuser ne null}">
                    <c:url value="/toprofile.html" var="toprofile"/>
                    <li><a href="${toprofile}?userid=${curuser.id}">Profile</a></li>
                    <c:url value="/exit.html" var="exit"/>
                    <li><a href="${exit}">Exit</a></li>
                </c:if>
                <c:if test="${curuser eq null}">
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
                <li><a href="#">Add project</a></li>
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
            <p>First name: ${user.firstName}</p>
            <p>Last name: ${user.lastName}</p>
            <p>Date of registration: ${user.regDate}</p>
            <p>E-mail: ${user.email}</p>
            <c:if test="${user.role eq 'FREELANCER'}">
                <p>User role: Freelancer</p>
                <p></p>
                <c:forEach var="elem" items="${categories}">
                    <p>${elem.name}</p>
                </c:forEach>
            </c:if>
            <c:if test="${user.role eq 'CLIENT'}">
                <p>User role: Client</p>
            </c:if>
            <c:if test="${curuser.id eq user.id}">
                <form method="get" action="toeditprofile.html?userid=${user.id}">
                    <input type="button" value="Edit">
                </form>
            </c:if>
        </div>
    </div>
    <div class="footer">
        <p>&copy; Footer content <a href="#">Link footer</a></p>
    </div>
</div>
<script src="css3-mediaqueries.js"></script>
</body>
</html>