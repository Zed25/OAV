<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 24/03/17
  Time: 14.51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <!--Import Google Icon Font-->
  <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <!--Import materialize.css-->
  <link type="text/css" rel="stylesheet" href="materialize/css/materialize.min.css"  media="screen,projection"/>

  <!--Let browser know website is optimized for mobile-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
<nav>
  <div class="nav-wrapper blue">
    <a href="#" class="brand-logo">Logo</a>
    <ul id="nav-mobile" class="right hide-on-med-and-down">
      <li><a href="#login_modal">Login</a></li>
      <li><a href="#registration_modal">Registration</a></li>
    </ul>
  </div>
</nav>
<!-- Login modal structure -->
<div id="login_modal" class="modal bottom-sheet">
  <div class="modal-content">
    <h4>Login</h4>
    <form action="#">
      <div class="row">
        <div class="input-field col s5">
          <input id="username" type="text" class="validate">
          <label for="username">Username</label>
        </div>
        <div class="input-field col s5">
          <input id="password" type="text" class="validate">
          <label for="password">Password</label>
        </div>
        <div>
          <button class="btn-floating btn-large waves-effect waves-light blue offset-s10" type="submit" name="action">
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
<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="materialize/js/materialize.min.js"></script>
<script>
    $(document).ready(function(){
        // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
        $('.modal').modal();
    });
</script>
<script type="text/javascript">
    $('input[name="reg_username"]').blur(function() {
        var currentValue = $(this).val();
        var testValue = 'crumple';
        if(currentValue != testValue) {
            $(this).next('span').html('FAIL');
        } else {
            $(this).next('span').html('');
        }
    });
</script>
<script>
    $('input[name="reg_pswd"]').blur(function() {
        var currentValue = $(this).val();
        var testValue = 'crumple';
        if(currentValue != testValue) {
            $(this).next('span').html('FAIL');
        } else {
            $(this).next('span').html('');
        }
    });
</script>
<script>
    $('input[name="reg_pswd_reinsert"]').blur(function() {
        var currentValue = $(this).val();
        var testValue = 'crumple';
        if(currentValue != testValue) {
            $(this).next('span').html('FAIL');
        } else {
            $(this).next('span').html('');
        }
    });
</script>
<script>
    $('input[name="reg_mail"]').blur(function() {
        var currentValue = $(this).val();
        var testValue = 'crumple';
        if(currentValue != testValue) {
            $(this).next('span').html('FAIL');
        } else {
            $(this).next('span').html('');
        }
    });
</script>
</body>
</html>

