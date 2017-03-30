<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 29/03/17
  Time: 19.07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Login modal structure -->
<div id="login_modal" class="modal bottom-sheet">
    <div class="modal-content">
        <h4>Login</h4>
        <form action="#">
            <div class="row">
                <div class="input-field col s5">
                    <input id="user-id" name="userID" type="text" class="validate">
                    <label for="user-id">Username</label>
                </div>
                <div class="input-field col s5">
                    <input id="password" name="password" type="text" class="validate">
                    <label for="password">Password</label>
                </div>
                <div>
                    <button class="btn-floating btn-large waves-effect waves-light blue offset-s10" type="submit" name="loginBean" id="loginBean">
                        <i class="material-icons">send</i>
                    </button>
                    <button class="btn-floating btn-large waves-effect waves-light blue modal-close" type="button">
                        <i class="material-icons">close</i>
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
