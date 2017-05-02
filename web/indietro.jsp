<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 27/04/17
  Time: 14.34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBean" scope="session" class="beans.login.search.SearchBean"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="header.jsp"/>
<jsp:include page="footer.jsp"/>
<body>
<% resultBean.decrementPage(); %>
<jsp:forward page="searchResult.jsp"></jsp:forward>

</body>
</html>
