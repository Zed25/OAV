<%@ page import="enumerations.ErrorType" %><%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 26/03/17
  Time: 19.19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="newUserBean" scope="session" class="beans.login.UserBean"/>
<jsp:setProperty name="newUserBean" property="userID" param="reg_userID"/>
<jsp:setProperty name="newUserBean" property="password" param="reg_password"/>
<jsp:setProperty name="newUserBean" property="name" param="reg_name"/>
<jsp:setProperty name="newUserBean" property="surname" param="reg_surname"/>
<jsp:setProperty name="newUserBean" property="email" param="reg_email"/>
<jsp:setProperty name="newUserBean" property="type" param="reg_type"/>

<jsp:useBean id="loginBean" scope="session" class="beans.login.UserBean"/>

<jsp:include page="header.jsp"/>

<%if(!newUserBean.isFull()){%>
<jsp:include page="forms/registrationForm.jsp"/>
<%}else{
    if(loginBean.isAdmin()){
        ErrorType errorType;
        if(loginBean.isAdmin()){
            errorType = loginBean.getAdministrationRole().newUserRegistration(newUserBean);
        }else {
            errorType = ErrorType.NO_ADMIN;
        }
        switch (errorType){
            case NO_ERR:
                newUserBean.emptyBean();%>
                <div class="row">
                Registration complete succesfully!
                </div>
                <div class="row">
                <form action="index.jsp" method="get">
                    <button class="waves-effect waves-light blue" type="submit">
                        Home Page
                    </button>
                </form>
                </div>
                <%break;
            case GEN_ERR:
                newUserBean.emptyBean();%>
                <div class="row">
                    <h3 class="red-text">A problem occurred, try again please</h3>
                </div>
                <div class="row">
                    <jsp:include page="forms/registrationForm.jsp"/>
                </div>
                <%break;
            case MISS_VAL:
                newUserBean.emptyBean();%>
                <div class="row">
                    <h3 class="red-text">Please insert all required values</h3>
                </div>
                <div class="row">
                    <jsp:include page="forms/registrationForm.jsp"/>
                </div>
                <%break;
            case ALREADY_EXISTS:
                newUserBean.emptyBean();%>
                <div class="row">
                    <h3 class="red-text">The chosen username already exists</h3>
                </div>
                <div class="row">
                    <jsp:include page="forms/registrationForm.jsp"/>
                </div>
        <%}%>
<%}}%>
<jsp:include page="footer.jsp"/>
