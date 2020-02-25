<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="lang" uri="lang" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${lang:locale(locale, cookie.locale.value)}"/>
<fmt:setLocale value="${language}"/>
<fmt:requestEncoding value="UTF-8"/>
<fmt:setBundle basename="Localization"/>

<!DOCTYPE html>
<style>
    * {
        margin: 0;
        padding: 0;
    }

    html,body {
        width: 100%;
        height: 100%;
        z-index: -2;
        outline: none;
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
        display: flex;
        justify-content: center;
        align-items: center;
        height: 70vh;
    }

    .form {
        width: 300px;
        padding: 32px;
        border-radius: 20px;
        box-shadow: 0 2px 16px;
        background-color: aliceblue;
        font-family: sans-serif;
    }

    .form__title,
    .form_input,
    .form__button {
        background-color: aliceblue;
        font-family: sans-serif;
        color: darkblue;
    }

    .form__title {
        text-align: center;
        margin: 0 0 32px 0;
    }

    .form__group {
        position: relative;
        margin: 32px;
    }

    .role {
        margin-bottom: 20px;
        text-align: center;
        color: #4c545e;
    }

    .form__input {
        width: 100%;
        padding: 0 0 10px 0;
        border: none;
        margin-bottom: 30px;
        border-bottom: 1px solid #e0e0e0;
        background-color: transparent;
        transition: 0.3s;
        outline: none;
    }
    .form__input:placeholder-shown {
        text-align: center;
    }

    .form__button {
        width: 30%;
        text-align: center;
        padding: 5px 5px 5px 5px;
        border-radius: 15px;
        border: none;
        background-color: aqua;
        font-weight: bold;
    }

    .form__button:hover {
        background-color: aquamarine;
    }


    .form__reset {
        width: 30%;
        text-align: center;
        border: none;
        background-color: transparent;
    }

    .form__reset:hover {
        color: blue;
    }
</style>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="media-queries.css">
    <title><fmt:message key="registration"/></title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <div class="content">
        <jsp:include page="leftnavbar.jsp"/>
        <div class="main">
            <form action="registration.html" class="form" method="post">
                <h1 class="form__title"><fmt:message key="registration"/></h1>
                <div class="form_group">
                    <input class="form__input" placeholder="E-mail" name="email" required>
                </div>

                <div class="form_group">
                    <input class="form__input" placeholder=<fmt:message key="login"/> name="login" required>
                </div>

                <div class="form_group">
                    <input class="form__input" placeholder=<fmt:message key="password"/> name="password" type="password" required>
                </div>
                <div class="form_group">
                    <input class="form__input" placeholder=<fmt:message key="repeatpassword"/> name="repeat" type="password" required>
                </div>
                <div class="role">
                    <p><fmt:message key="role"/></p>
                    <p><input name="role" type="radio" value="client"><fmt:message key="client"/></p>
                    <p><input name="role" type="radio" value="freelancer" checked><fmt:message key="freelancer"/></p>
                </div>
                <div class="form_group">
                    <c:set var="first"><fmt:message key="firstname"/> <fmt:message key="optional"/></c:set>
                    <input class="form__input" name="firstname" placeholder="${first}">
                </div>
                <div class="form_group">
                    <c:set var="last"><fmt:message key="lastname"/> <fmt:message key="optional"/></c:set>
                    <input class="form__input" name="lastname" placeholder="${last}">
                </div>
                <button class="form__button" type="submit"><fmt:message key="registration"/></button>
                <input type="reset" class="form__reset" name=<fmt:message key="reset    "/>>
            </form>
        </div>
    </div>
</div>
<script src="css3-mediaqueries.js"></script>
</body>
</html>