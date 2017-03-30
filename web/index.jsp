<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 24/03/17
  Time: 14.51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% if(request.getParameter("loginBean") != null){%>
    <jsp:useBean id="loginBean" scope="session" class="beans.login.UserBean"/>
    <jsp:setProperty name="loginBean" property="*"/>
    <%if(loginBean.login()) {
      session.setAttribute("loginBean", loginBean);
    }else{
      loginBean = null;
    }
}%>

<jsp:include page="header.jsp"/>
<jsp:include page="login.jsp"/>
<jsp:include page="registration.jsp"/>
<jsp:include page="footer.jsp"/>

