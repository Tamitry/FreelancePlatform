<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="lang" uri="lang" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${lang:locale(locale, cookie.locale.value)}"/>
<fmt:setLocale value="${language}"/>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setBundle basename="Localization"/>

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
</style>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0">
    <title>Orders</title>
</head>
<body>
<div class="leftCol">
    <ul class="leftNav">
        <c:if test="${curuser.role eq 'CLIENT'}">
            <c:url value="/toaddprofile.html" var="addorder"/>
            <li><a href="${addorder}"><fmt:message key="addproject"/></a></li>
        </c:if>
        <li>
            <label><fmt:message key="searchuser"/></label>
            <form action="usersearch.html" method="get">
                <input type="search" contenteditable="false" name="search">
                <input type="submit" name=<fmt:message key="search"/>>
            </form>
            <label><fmt:message key="searchorder"/></label>
            <form action="ordersearch.html" method="get">
                <input type="search" contenteditable="false" name="search">
                <input type="submit" name="<fmt:message key="search"/>">
            </form>
        </li>
    </ul>
</div>
</body>
</html>
