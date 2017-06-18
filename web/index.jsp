<%@ page import="enumerations.ErrorType" %><%--
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
    if((!loginBean.isLogged() && !loginBean.getUserID().equals("")) || (!loginBean.isLogged() && !loginBean.getPassword().equals(""))){
        if(loginBean.getUserID().equals("") || loginBean.getPassword().equals("")) {
            loginBean.emptyBean();
            out.println("<h3 class=\"red-text\">Please enter both UserID and Password in order to login!</h3>");
        }
        else {
            ErrorType errorType = loginBean.login();
            if(errorType != ErrorType.NO_ERR){
                loginBean.emptyBean();
                switch (errorType){
                    case GEN_ERR:
                        out.println("<h3 class=\"red-text\">An error occurred, please try again!</h3>");
                        break;
                    case NO_RESULTS:
                        out.println("<h3 class=\"red-text\">UserID or password wrong!</h3>");
                        break;
                }
            }
        }
}%>
<jsp:include page="header.jsp"/>
<jsp:include page="login.jsp"/>

<jsp:include page="footer.jsp"/>





