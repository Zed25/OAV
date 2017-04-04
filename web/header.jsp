<%@ page import="beans.login.UserBean" %>
<%@ page import="DAO.UserDAO" %><%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 26/03/17
  Time: 19.20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="loginBean" scope="session" class="beans.login.UserBean"/>


<html>
<head>
    <!--Import Google Icon Font-->
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="materialize/css/materialize.min.css"  media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>OAV</title>
</head>
<body>
<nav>
    <div class="nav-wrapper blue">
<a href="#" class="brand-logo center">Logo</a>
<ul id="nav-mobile" class="left hide-on-med-and-down">
    <%if(loginBean.isLogged()){%>
    <li><a href="#" data-activates="slide-out" id="side_bar"><i class="material-icons white-text">menu</i></a></li>
    <li>Ciao <%=loginBean.getName()%>!</li>
    <%}else {%>
    <li><a href="#login_modal">Login</a></li>
    <%}%>
</ul>
</div>
</nav>
<%if(loginBean.isAdmin()){%>
<ul id="slide-out" class="side-nav">
    <li><div class="userView">
        <div class="background">
            <img src="images/space.jpg">
        </div>
        <img class="circle" src="images/admin.png">
        <span class="white-text name"><%=loginBean.getName()%> <%=loginBean.getSurname()%> (Admin)</span>
        <span class="white-text email"><%=loginBean.getEmail()%></span>
    </div></li>
    <li><a href="registration.jsp"><i class="material-icons black-text">person_add</i>Sign in a user</a></li>
    <li><a href="logout.jsp" ><i class="material-icons black-text">eject</i>Logout</a></li>
    <li><a href="insertSatellite.jsp"><i class="material-icons black-text">add</i>New Satellite</a></li>
    <li><a href="findObjInMap.jsp"><i class="material-icons black-text">add</i>Search Source</a></li>
    <li><a href="csv.jsp"><i class="material-icons black-text">add</i>Add csv</a></li>
    <li><div class="divider"></div></li>
    <li><a class="subheader">Subheader</a></li>
    <li><a class="waves-effect" href="#!">Third Link With Waves</a></li>
</ul>
<%}else{%>
<ul id="slide-out" class="side-nav">
    <li><div class="userView">
        <div class="background">
            <img src="images/space.jpg">
        </div>
        <img class="circle" src="images/normal_user.png">
        <span class="white-text name"><%=loginBean.getName()%> <%=loginBean.getSurname()%> (Normal User)</span>
        <span class="white-text email"><%=loginBean.getEmail()%></span>
    </div></li>
    <li><a href="#!"><i class="material-icons black-text">add</i>First Link With Icon</a></li>
    <li><a href="findObjInMap.jsp"><i class="material-icons black-text">add</i>Search Source</a></li>
    <li><a href="#!">Second Link</a></li>
    <li><div class="divider"></div></li>
    <li><a class="subheader">Subheader</a></li>
    <li><a class="waves-effect" href="#!">Third Link With Waves</a></li>
</ul>
<%}%>