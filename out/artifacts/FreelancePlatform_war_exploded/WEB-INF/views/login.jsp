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
    .wrapper {
        width: 980px;
        padding: 0 10px;
        margin: 0 auto;
        min-height: 100%;
        position: relative;

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

    .main {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 50vh;
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

    .form__reset {
        width: 30%;
        text-align: center;
        border: none;
        background-color: transparent;
    }

    .form__reset:hover {
        color: blue;
    }

    .form__button:hover {
        background-color: aquamarine;
    }
</style>
<html lang="${language}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0">
    <title><fmt:message key="log_in"/></title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <div class="content">
        <jsp:include page="leftnavbar.jsp"/>
        <div class="main">
            <form action="login.html" class="form" method="post">
                <h1 class="form__title"><fmt:message key="log_in"/></h1>
                <div class="form_group">
                    <input class="form__input" placeholder=<fmt:message key="login"/> name="login" required>
                </div>

                <div class="form_group">
                    <input type=password class="form__input" placeholder=<fmt:message key="password"/> name="password" required>
                </div>

                <button type="submit" class="form__button"><fmt:message key="log_in"/></button>
                <input type="reset" class="form__reset" name=<fmt:message key="reset"/>>
            </form>
        </div>
    </div>
</div>
<script src="css3-mediaqueries.js"></script>
</body>
</html>