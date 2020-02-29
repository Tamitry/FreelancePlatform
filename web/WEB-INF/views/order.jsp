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
        overflow: hidden;
    }

    .main h1 {
        padding: 10px 10px 5px 5px;
        text-align: center;
        font: sans-serif;
        font-weight: 1000;
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


    .modalDialog:target {
        display: block;
        pointer-events: auto;
    }

    .modalDialog > div {
        width: 400px;
        position: relative;
        margin: 10% auto;
        padding: 5px 20px 13px 20px;
        border-radius: 10px;
        background: #475363;
    }

    .modalDialog {
        position: fixed;
        font-family: Arial, Helvetica, sans-serif;
        color: #333333;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        background: rgba(0,0,0,0.8);
        z-index: 99999;
        -webkit-transition: opacity 400ms ease-in;
        -moz-transition: opacity 400ms ease-in;
        transition: opacity 400ms ease-in;
        display: none;
        pointer-events: none;
        text-align: center;
    }

    .choose {
        border: 1px solid #333;
        display: inline-block;
        padding: 5px 15px;
        text-decoration: none;
        color: #ffffff;
    }

    .choose:hover {
        box-shadow: 0 0 5px rgba(0,0,0,0.3);
        background: linear-gradient(to bottom, #fcfff4, #e9e9ce);
        color: #a00;
    }
</style>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0">
    <title><fmt:message key="order"/></title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <div class="content">
        <jsp:include page="leftnavbar.jsp"/>
        <div class="main">
            <p><fmt:message key="ordername"/>: ${order.orderName}</p>
            <p><fmt:message key="registrationdate"/>: ${order.orderRegDate}</p>
            <p><fmt:message key="deadlinedate"/>: ${order.orderDeadLine}</p>
            <p><fmt:message key="orderdescription"/>: ${order.description}</p>
            <p><fmt:message key="client"/>: ${order.client.firstName} ${order.client.lastName}</p>
            <h2><fmt:message key="categories"/></h2>
            <c:forEach var="elem" items="${properties}">
                <p>${elem.name}</p>
            </c:forEach>
            <h1><fmt:message key="confirmedfreelancers"/></h1>
            <table class="bordered">
                <thead>
                <tr>
                    <th><fmt:message key="freelancer"/></th>
                    <c:if test="${order.client.id eq curuser.id and ended eq true}">
                        <th><fmt:message key="cancel"/></th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="elem" items="${confworks}">
                    <tr>
                        <td><a href="toprofile.html?userid=${elem.user.id}"><c:out value="${elem.user.firstName} ${elem.user.lastName}"/></a></td>
                        <c:if test="${order.client.id eq curuser.id}">
                            <td><a href="cancelwork.html?workid=${elem.id}"><fmt:message key="cancel"/></a></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <h1><fmt:message key="unconfirmedfreelancers"/></h1>
            <table class="bordered">
                <thead>
                <tr>
                    <th><fmt:message key="freelancer"/></th>
                    <c:if test="${order.client.id eq curuser.id and ended eq true}">
                        <th><fmt:message key="confirm"/></th>
                        <th><fmt:message key="cancel"/></th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="elem" items="${unconfworks}">
                    <tr>
                        <td><a href="toprofile.html?userid=${elem.user.id}"><c:out value="${elem.user.firstName} ${elem.user.lastName}"/></a></td>
                        <c:if test="${order.client.id eq curuser.id and ended eq true}">
                            <td><a href="confirmwork.html?workid=${elem.id}"><fmt:message key="confirm"/></a></td>
                            <td><a href="cancelwork.html?workid=${elem.id}"><fmt:message key="cancel"/></a></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <c:if test="${curuser.role eq 'FREELANCER' and ended eq true}">
                <div class="buttons__group">
                    <c:if test="${subscribe eq false}">
                        <a href="subscribe.html?orderid=${order.id}" class="choose"><fmt:message key="subscribe"/></a>
                    </c:if>
                    <c:if test="${subscribe eq true}">
                        <a href="unsubscribe.html?orderid=${order.id}" class="choose"><fmt:message key="unsubscribe"/></a>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${curuser.id eq order.client.id}">
                <a href="toeditorder.html?orderid=${order.id}" class="choose"><fmt:message key="edit"/></a>
                <a href="#modalDelete" class="choose"><fmt:message key="delete"/></a>
                <div id="modalDelete" class="modalDialog">
                    <div>
                        <h1><fmt:message key="delete"/>?</h1>
                        <p>
                            <a href="deleteorder.html?orderid=${order.id}" class="choose"><fmt:message key="yes"/></a> <a href="#close" class="choose"><fmt:message key="no"/></a>
                        </p>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
<script src="css3-mediaqueries.js"></script>
</body>
</html>