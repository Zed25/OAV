<%@ page import="beans.login.search.SearchBean" %><%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 30/03/17
  Time: 14.45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>
<jsp:useBean id="searchBean" scope="session" class="beans.login.search.SearchBean"/>
<jsp:include page="header.jsp"/>

<div class="container center-align">
    <br>
    <h3>NO MATCHES FOUND</h3>
    <h4>Please try again</h4>
    <br>
    <a class="waves-effect waves-light btn" href="backToSearch.jsp">Back to Search</a>
</div>

<jsp:include page="footer.jsp"/>
