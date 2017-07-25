<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 12/05/17
  Time: 10.19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBean" scope="session" class="beans.login.SearchBean"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.ResultBean"/>

<jsp:include page="header.jsp"></jsp:include>

<% searchBean.dropAllData();
resultBean.dropAllData(); %>

<jsp:forward page="index.jsp"></jsp:forward>
<jsp:include page="footer.jsp"></jsp:include>