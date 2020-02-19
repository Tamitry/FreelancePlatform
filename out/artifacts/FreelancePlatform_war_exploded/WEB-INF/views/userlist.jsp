<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.11.2019
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<style>
    * {
        margin: 0;
        padding: 0;
    }

    html,body {
        width: 100%;
        height: 100%;
    }

    body {
        background: #373e47 url("images/bg.jpg") repeat;
        font-family: Arial;
        font-size: 12px;
        color: #e4e4e4;
        line-height: 18px;
        text-shadow: 0 1px 0 rgba(0,0,0,0.75);
    }

    ::selection {
        background-color: #a45f93;
        color: white;
        text-shadow: none;
    }

    ::-moz-selection {
        background-color: #a45f93;
        color: white;
        text-shadow: none;
    }

    a {
        color: #ff8de3;
        text-decoration: underline;
    }

    p {
        margin-bottom: 10px;
    }

    h1,h2,h3,h4,h5,h6 {
        color: white;
        line-height: normal;
    }

    h1 {
        font-size: 18px;
    }

    h2 {
        font-size: 16px;
        margin-top: 15px;
    }

    h3 {
        font-size: 14px;
    }

    h1+p,h2+p,h3+p {
        margin-top: 3px;
    }


    input:-moz-placeholder {
        color: #9c9c9c;
    }

    input::-webkit-input-placeholder {
        color: #9c9c9c;
    }


    table.bordered {
        margin: 10px 0;
        padding: 0;
        border-collapse: separate;
        *border-collapse: collapse;
        border: 1px solid #292e35;
        width: 100%;
        background-color: #373e47;
        border-radius: 3px;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-spacing: 0;
    }

    table.bordered th, table.bordered td {
        padding: 5px;
        vertical-align: top;
        border-left: 1px solid #4c545e;
    }

    table.bordered td {
        border-top: 1px solid #4c545e;
    }

    table.bordered thead tr th:first-child,
    table.bordered tbody tr td:first-child {
        border-left: none;
    }

    table.bordered th {
        background-color: #292e35;
        text-align: left;
        font-weight: bold;
        color: white;
    }

    table.bordered tbody tr:hover {
        background-color: #2e333b;
    }

    table.bordered thead tr:first-child th:first-child {
        border-radius: 3px 0 0 0;
        -webkit-border-radius: 3px 0 0 0;
        -moz-border-radius: 3px 0 0 0;
    }

    table.bordered thead:first-child th:last-child {
        border-radius: 0 3px 0 0;
        -webkit-border-radius: 0 3px 0 0;
        -moz-border-radius: 0 3px 0 0;
    }

    table.bordered tbody tr:last-child td:first-child {
        border-radius: 0 0 0 3px;
        -webkit-border-radius: 0 0 0 3px;
        -moz-border-radius: 0 0 0 3px;
    }

    table.bordered tbody tr:last-child td:last-child {
        border-radius: 0 0 3px 0;
        -webkit-border-radius: 0 0 3px 0;
        -moz-border-radius: 0 0 3px 0;
    }

    .wrapper {
        width: 980px;
        padding: 0 10px;
        margin: 0 auto;
        min-height: 100%;
        position: relative;

    }
    /*Шапка*/
    .header {
        padding-top: 20px;
        margin-bottom: 20px;
    }

    .headerContent {
        box-shadow: 0 3px 2px rgba(0,0,0,0.3);
        -webkit-box-shadow: 0 3px 2px rgba(0,0,0,0.3);
        -moz-box-shadow: 0 3px 2px rgba(0,0,0,0.3);
        background-color: #f2f2f2;
        background-repeat: no-repeat;
        background-image: -ms-linear-gradient(top, #f2f2f2, #e4e4e4);
        background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#f2f2f2), to(#e4e4e4));
        background-image: -webkit-linear-gradient(top, #f2f2f2, #e4e4e4);
        background-image: -o-linear-gradient(top, #f2f2f2, #e4e4e4);
        background-image: linear-gradient(top, #f2f2f2, #e4e4e4);
        background-image: -moz-linear-gradient(top, #f2f2f2, #e4e4e4);
        filter: progid:dximagetransform.microsoft.gradient(startColorstr='#f2f2f2', endColorstr='#e4e4e4', GradientType=0);
        height: 33px;
        border-radius: 3px;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        padding-top: 10px;
    }

    .logo {
        display: inline-block;
        float: left;
        font-weight: bold;
    }

    .logo a {
        text-decoration: none;
        font-size: 24px;
        text-shadow: 0 1px 0 white;
        color: #373e47;
        padding-left: 10px;
    }

    .logo a:hover {
        color: #a45f93;
    }

    *:first-child+html .logo a {
        line-height: normal;
    }

    .pink {
        color: #a45f93;
    }

    .logo a:hover .pink {
        color: #373e47;
    }

    .gray {
        color: #8b95a4;
        font-size: 18px;
    }

    /*Навигация*/
    ul.nav {
        margin: 0;
        padding: 3px 0 0 0;
        overflow: hidden;
        float: right;
    }

    ul.nav li {
        float: left;
        list-style: none;
        padding: 0;
    }

    ul.nav li a {
        font-size: 14px;
        font-weight: bold;
        color: white;
        text-decoration: none;
        padding: 0 30px;
        display: block;
        color: #143567;
        text-shadow: 0 1px 0 white;
    }

    ul.nav li a.active {
        color: #a45f93;
    }

    ul.nav li a:hover {
        color: #a45f93;

    }
    /*Контент*/
    .content {
        padding: 10px;
        border-radius: 3px;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border: 1px solid #67707d;
        background-color: #475363;
        background-color: rgba(87,103,127,0.5);
        overflow: hidden;
    }

    *:first-child+html .content {
        background-color: #475363;
    }
    /*Левая колонка*/
    .leftCol {
        float: left;
        margin-right: 10px;
        width: 220px;
    }

    ul.leftNav {
        margin: 0 0 40px 0;
        padding: 10px;
        background-color: #373e47;
        border-radius: 3px;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        box-shadow: 0 1px 0 rgba(255,255,255,0.3), inset 0 2px 1px rgba(0,0,0,0.3);
        -webkit-box-shadow: 0 1px 0 rgba(255,255,255,0.3), inset 0 2px 1px rgba(0,0,0,0.3);
        -moz-box-shadow: 0 1px 0 rgba(255,255,255,0.3), inset 0 2px 1px rgba(0,0,0,0.3);
    }

    ul.leftNav li {
        list-style: none;
    }

    ul.leftNav li a {
        color: #e4e4e4;
        text-decoration: none;
        padding: 8px 10px 8px 30px;
        display: block;
        border-top: 1px solid #4c545e;
        background: url("images/arrow-right.png") 13px 50% no-repeat;
    }

    ul.leftNav li:first-child a {
        border-top: none;
    }

    ul.leftNav li a:hover {
        background-color: #292e35;
    }


    ul.leftNav li form input[type=search] {
        width: 120px;
        padding: 6px;
        border-radius: 10px;
        position: relative;

    }

    ul.leftNav li form input[type=button] {
        padding: 4px;
        width: 60px;
        border-radius: 10px;
    }

    .form__label {
        position: absolute;
        color: #4c545e;
        transition: 0.3s;
    }

    .main {
        overflow: hidden;
    }

    .main h1 {
        padding: 10px 10px 5px 5px;
        text-align: center;
        font-weight: 900;
    }

    .main img {
        margin-right: 15px;
        border: 10px solid #697689;
        -webkit-transition: all linear 0.2s;
        -moz-transition: all linear 0.2s;
        -ms-transition: all linear 0.2s;
        -o-transition: all linear 0.2s;
        transition: all linear 0.2s;
    }

    .main img:hover {
        border: 10px solid #a45f93;
    }
    /*Подвал*/
    .footer {

    }

    .footer p {
        text-align: center;
    }
</style>
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
            <div class="logo"><a href=""><span class="pink">Pink</span><span class="pink">Flamingo</span><span class="gray">.com</span></a></div>
            <ul class="nav">
                <c:url value="/home.html" var="home"/>
                <li><a href="${home}" class="active">Home</a></li>
                <li><a href="#">About</a></li>
                <c:url value="/userlist.html" var="userlist"/>
                <li><a href="${userlist}">Freelancers</a></li>
                <c:url value="/orderlist.html" var="orders"/>
                <li><a href="${orders}">Works</a></li>
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
                <c:if test="${curuser.role eq 'CLIENT'}">
                    <c:url value="/toaddprofile.html" var="addorder"/>
                    <li><a href="${addorder}">Add project</a></li>
                </c:if>
                <li>
                    <form action="usersearch.html" method="get">
                        <input type="search" contenteditable="false" name="search">
                        <input type="submit" value="Search">
                    </form>
                    <form action="ordersearch.html" method="get">
                        <input type="search" contenteditable="false" name="search">
                        <input type="submit" value="Search">
                    </form>
                </li>
            </ul>
        </div>
        <div class="main">
            <h1>List of freelancers</h1>
            <!--Таблица-->
            <div class="mobile">
                <table class="bordered">
                    <thead>
                    <tr>
                        <th>First name</th>
                        <th>Last name</th>
                        <th>E-mail</th>
                        <th>Data of registration</th>
                    </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="elem" items="${users}">
                            <tr>
                                <td><a href="toprofile.html?userid=${elem.id}"><c:out value="${elem.firstName}"/></a></td>
                                <td><a href="toprofile.html?userid=${elem.id}"><c:out value="${elem.lastName}"/></a></td>
                                <td><a href="toprofile.html?userid=${elem.id}"><c:out value="${elem.email}"/></a></td>
                                <td><a href="toprofile.html?userid=${elem.id}"><c:out value="${elem.regDate}"/></a></td>
                            <tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="footer">
        <p>&copy; Footer content <a href="#">Link footer</a></p>
    </div>
</div>
<script src="css3-mediaqueries.js"></script>
</body>
</html>
