<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 30/03/17
  Time: 14.48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBean" scope="session" class="beans.login.search.SearchBean"/>
<jsp:setProperty name="searchBean" property="*"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>
<jsp:setProperty name="resultBean" property="*"/>
<html>
<head>
    <title>OAV</title>
</head>
<body>
<div class="container" style="align-content: center">
    <div class="container" style="vertical-align: text-top">

        <h5 style="color: slategray">Source :</h5>
        <br>
    <% for (int i = 0; i<50; i++) {
        System.out.println("ciao");
        } %>
        <br>

    </div>

    <div class="container" style="vertical-align: text-top">

        <h5 style="color: slategray">Flow value :</h5>
        <br>
    <% for (int j = 0; j<2; j++) {
        System.out.println(Double.toString(resultBean.getValues()[j]));
        } %>
        <br>

    </div>
</div>

</body>
</html>
