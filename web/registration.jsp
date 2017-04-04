<%--
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
<h4>Registration</h4>
<form action="#" method="post" onsubmit="registrationFormValidate()">
    <div class="row">
        <div class="input-field">
            <input id="reg_name" name="reg_name" type="text" class="validate"><span></span>
            <label for="reg_name">Name</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field">
            <input id="reg_surname" name="reg_surname" type="text" class="validate"><span></span>
            <label for="reg_surname">Surname</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field">
            <input id="reg_username" name="reg_userID" type="text" class="validate"><span></span>
            <label for="reg_username">Username</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field">
            <input id="reg_pswd" name="reg_password" type="text" class="validate"><span></span>
            <label for="reg_pswd">Password</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field">
            <input id="reg_pswd_repete" type="text" class="validate"><span></span>
            <label for="reg_pswd_repete">Repete Password</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field">
            <input id="reg_mail" name="reg_email" type="email" class="validate"><span></span>
            <label for="reg_mail" data-error="wrong" data-success="right">Mail Address</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field">
            <select id="reg_type" name="reg_type" class="col s12 m6 l4">
                <option value="" disabled selected>Choose user type</option>
                <option value="Admin" name="reg_type">Amministratore</option>
                <option value="User" name="reg_type">Utente</option>
            </select>
            <label>User Type</label><span></span>
        </div>
    </div>
    <div class="row">
        <div class="input-field right-align">
            <button id="registration" class="btn waves-effect waves-light blue offset-s10" type="submit">
                Register
            </button>
            <button class="btn waves-effect waves-light blue" type="button" value="Back" onClick="history.go(-1);return true;">
                Back
            </button>
        </div>
    </div>
</form>
<%}else{
    if(loginBean.isAdmin()){
        if(loginBean.newUserRegistration(newUserBean)){
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
<%}else {
newUserBean.emptyBean();%>
<div class="row">
A problem occurred, try again please.
</div>
<div class="row">
    <form action="registration.jsp" method="get">
        <button class="waves-effect waves-light blue" type="submit">
            Try Again
        </button>
    </form>
    <form action="index.jsp" method="get">
        <button class="waves-effect waves-light blue" type="submit">
            Home Page
        </button>
    </form>
</div>
<%}%>
<%}}%>
<jsp:include page="footer.jsp"/>
