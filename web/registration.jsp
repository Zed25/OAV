<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 26/03/17
  Time: 19.19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Registration modal structure -->
<div id="registration_modal" class="modal">
    <div class="modal-content">
        <h4>Registration</h4>
        <form action="#">
            <div class="row">
                <div class="input-field">
                    <input id="reg_username" name="reg_username" type="text" class="validate"><span></span>
                    <label for="reg_username">Username</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field">
                    <input id="reg_pswd" name="reg_pswd" type="text" class="validate"><span></span>
                    <label for="reg_pswd">Password</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field">
                    <input id="reg_pswd_reinsert" name="reg_pswd_reinsert" type="text" class="validate"><span></span>
                    <label for="reg_pswd_reinsert">Repete Password</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field">
                    <input id="reg_mail" name="reg_mail" type="email" class="validate"><span></span>
                    <label for="reg_mail" data-error="wrong" data-success="right">Mail Address</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field right-align">
                    <button class="btn waves-effect waves-light blue offset-s10" type="submit" name="action">
                        Register
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
