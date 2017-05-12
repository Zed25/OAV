<%@ page import="beans.login.search.SearchBean" %><%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 30/03/17
  Time: 14.45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>
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

NO MATCHES FOUND

<br>

<a href="index.jsp" class="waves-effect waves-light">Back To Search</a>

<script src="js\materialize.min.js"></script>
<script type="text\javascript" src="js\jquery-2.1.1.min.js"></script>
</body>
</html>

