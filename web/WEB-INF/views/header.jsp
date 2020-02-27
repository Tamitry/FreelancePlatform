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
</style>
<html lang="${language}">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width; initial-scale=1.0">
</head>
<body>
<div class="wrapper">
  <div class="header">
    <div class="headerContent">
      <div class="logo"><a href=""><span class="pink">Pink <span class="pink">Flamingo</span><span class="gray">.com</span></span></a></div>
      <ul class="nav">
        <c:url value="/home.html" var="home"/>
        <li><a href="${home}" class="active"><fmt:message key="home"/></a></li>
        <li><a href="#"><fmt:message key="about"/></a></li>
        <c:url value="/userlist.html" var="userlist"/>
        <li><a href="${userlist}"><fmt:message key="freelancers"/></a></li>
        <c:url value="/orderlist.html" var="orders"/>
        <li><a href="${orders}"><fmt:message key="orders"/></a></li>
        <c:if test="${user ne null}">
          <c:url value="/toprofile.html" var="toprofile"/>
          <li><a href="${toprofile}?userid=${curuser.id}"><fmt:message key="profile"/></a></li>
          <c:url value="/exit.html" var="exit"/>
          <li><a href="${exit}"><fmt:message key="exit"/></a></li>
        </c:if>
        <c:if test="${user eq null}">
          <c:url value="/toregistration.html" var="registration"/>
          <li><a href="${registration}"><fmt:message key="registration"/></a></li>
          <c:url value="/tologin.html" var="login"/>
          <li><a href="${login}"><fmt:message key="log_in"/></a></li>
        </c:if>
      </ul>
    </div>
  </div>
  <div>
    <form action="localechange.html">
      <select name="locale" onchange="this.form.submit()">
        <option disabled selected><fmt:message key="chooselang"/></option>
        <option value="ru_RU">Русский</option>
        <option value="en_US">English</option>
      </select>
    </form>
  </div>
</div>
<script src="css3-mediaqueries.js"></script>
</body>
</html>