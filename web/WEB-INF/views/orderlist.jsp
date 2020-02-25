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
</style>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width; initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" type="text/css" href="media-queries.css">
    <title><fmt:message key="orders"/></title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="header.jsp"/>
    <div class="content">
        <jsp:include page="leftnavbar.jsp"/>
        <div class="main">
            <h1><fmt:message key="orderslist"/></h1>
            <form action="filterorder.html">
                <select name="filter" onchange="this.form.submit()">
                    <option value=${null}><fmt:message key="default"/></option>
                    <c:forEach var="elem" items="${categories}">
                        <c:if test="${elem.name ne filter}">
                            <option value="${elem.name}">${elem.name}</option>
                        </c:if>
                        <c:if test="${elem.name eq filter}">
                            <option value="${elem.name}" selected>${elem.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </form>
            <!--Таблица-->
            <div class  ="mobile">
                <table class="bordered">
                    <thead>
                    <tr>
                        <th><fmt:message key="ordername"/></th>
                        <th><fmt:message key="deadlinedate"/></th>
                        <th><fmt:message key="orderdescription"/></th>
                        <th><fmt:message key="client"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="elem" items="${orders}">
                        <tr>
                            <td><a href="toorder.html?orderid=${elem.id}"><c:out value="${elem.orderName}"/></a></td>
                            <td><a href="toorder.html?orderid=${elem.id}"><c:out value="${elem.orderDeadLine}"/></a></td>
                            <td><a href="toorder.html?orderid=${elem.id}"><c:out value="${elem.description}"/></a></td>
                            <c:url value="/toprofile.html" var="toprofile"/>
                            <td><a href="${toprofile}?userid=${elem.client.id}"><c:out value="${elem.client.firstName} ${elem.client.lastName}"/></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="css3-mediaqueries.js"></script>
</body>
</html>

