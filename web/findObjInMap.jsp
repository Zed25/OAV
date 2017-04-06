<%@ page import="beans.login.search.SearchBean" %><%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 30/03/17
  Time: 14.45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBean" scope="session" class="beans.login.search.SearchBean"/>
<jsp:setProperty name="searchBean" property="*"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>
<% if (searchBean.isFull()) {
    searchBean.findObjectInMap(searchBean, resultBean); %>
<jsp:forward page="searchResult.jsp"/>
<%
    }
%>

<html>
<head>
    <link rel="stylesheet" href="css\materialize.min.css">
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <title>OAV</title>
</head>
<jsp:include page="header.jsp"/>
<jsp:include page="footer.jsp"/>
<body>

<br>

<div class="container">

    <h5 style="color: slategray">Inserisci i parametri della ricerca: </h5>

    <br>

    <form class="col s12" action="#" method="post">

        <div class="row">
            <div class="input-field col s6">
                <input placeholder="Map" name="mapName" id="mapName" type="text" class="validate">
            </div>
        </div>

        <div class="row">
            <div class="input-field col s6">
                <input placeholder="Band" name="band" id="band" type="text" class="validate">
            </div>
        </div>

        <br>

        <button class="btn waves-effect waves-light" type="submit" name="searchBean">Cerca
        </button>

    </form>

    <br>

    <form class="col s12" action="index.jsp" method="post">
        <button class="btn waves-effect waves-light" type="submit" name="action">Torna indietro</button>
    </form>

</div>

<br>

<script src="js\materialize.min.js"></script>
<script type="text\javascript" src="js\jquery-2.1.1.min.js"></script>
</body>
</html>

