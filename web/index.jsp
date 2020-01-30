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
        <li><a href="http://localhost:8080/FreelancePlatform_war_exploded/" class="active">Home</a></li>
        <li><a href="#">About</a></li>
        <c:url value="/userlist.html" var="userlist"/>
        <li><a href="${userlist}">Freelancers</a></li>
        <li><a href="#">Works</a></li>
        <c:url value="/registration.html" var="registration"/>
        <li><a href="${registration}">Registration</a></li>
        <c:url value="/login.html" var="login"/>
        <li><a href="${login}">Log In</a></li>
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
    </div>
  </div>
  <div class="footer">
    <p>&copy; Footer content <a href="#">Link footer</a></p>
  </div>
</div>
<script src="css3-mediaqueries.js"></script>
</body>
</html>