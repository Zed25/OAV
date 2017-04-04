<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 24/03/17
  Time: 14.51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="loginBean" class="beans.login.UserBean" scope="session"/>
<jsp:setProperty name="loginBean" property="userID" param="log_userID"/>
<jsp:setProperty name="loginBean" property="password" param="log_password"/>

<%  //loginBean.setUserID("Zed");
    //loginBean.setPassword("root");
    if(!loginBean.isLogged() && !loginBean.getUserID().equals("")){
    loginBean.login();
}%>

<jsp:include page="header.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="footer.jsp"/>

