<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 02/06/17
  Time: 22.21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h4>Registration</h4>
<form action="#" method="post" onsubmit="registrationFormValidate()">
    <div class="row">
        <div class="input-field">
            <input id="reg_name" name="reg_name" type="text" class="validate"><span></span>
            <label style="color:#000;" for="reg_name">Name</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field">
            <input id="reg_surname" name="reg_surname" type="text" class="validate"><span></span>
            <label style="color:#000;" for="reg_surname">Surname</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field">
            <input id="reg_username" name="reg_userID" type="text" class="validate"><span></span>
            <label style="color:#000;" for="reg_username">Username</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field">
            <input id="reg_pswd" name="reg_password" type="text" class="validate"><span></span>
            <label style="color:#000;" for="reg_pswd">Password</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field">
            <input id="reg_pswd_repete" type="text" class="validate"><span></span>
            <label style="color:#000;" for="reg_pswd_repete">Repete Password</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field">
            <input id="reg_mail" name="reg_email" type="email" class="validate"><span></span>
            <label style="color:#000;" for="reg_mail" data-error="wrong" data-success="right">Mail Address</label>
        </div>
    </div>
    <div class="row">
        <div class="input-field">
            <select id="reg_type" name="reg_type" class="col s12 m6 l4">
                <option value="" disabled selected>Choose user type</option>
                <option value="Admin" name="reg_type">Amministratore</option>
                <option value="User" name="reg_type">Utente</option>
            </select>
            <label style="color:#000;">User Type</label><span></span>
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

