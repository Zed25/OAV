<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 03/04/17
  Time: 16.15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="loginBean" scope="session" class="beans.login.UserBean"/>
<%loginBean.logout();%>
<jsp:forward page="index.jsp"/>
