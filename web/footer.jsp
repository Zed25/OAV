<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 26/03/17
  Time: 19.20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
</div>
<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="materialize/js/materialize.min.js"></script>
<script type="text/javascript" src="resources/js/oavScripts.js"></script>
<script>
    $(document).ready(function(){
        // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
        $('.modal').modal();
    });

    $(document).ready(function() {
        $('select').material_select();
    });


    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 5 // Creates a dropdown of 5 years to control year
    });

</script>
<script>$("#side_bar").sideNav();</script>
<script type="text/javascript">
    $('input[id="reg_username"]').blur(function() {
        var username = $(this).val();
        if(username.length < 6){
            $(this).next('span').html('USERNAME TO SHORT: IT MUST HAVE MORE THAN 6 CHARACTERS');
            return false;
        }else if(username.length > 20){
            $(this).next('span').html('USERNAME TO LONG: IT MUST HAVE LESS THAN 20 CHARACTERS');
            return false;
        }
        for(i = 0; i < username.length; i++){
            if(username[i] == " ") {
                $(this).next('span').html('USERNAME CANNOT CONTAIN SPACES');
                return false;
            }
        }
        $(this).next('span').html('');
        return true;
    });
</script>
<script>
    $('input[id="reg_pswd"]').blur(function() {
        var password = $(this).val();
        if(password.length < 6){
            $(this).next('span').html('PASSWORD TO SHORT: IT MUST HAVE MORE THAN 6 CHARACTERS');
            return false;
        }else if(password.length > 32){
            $(this).next('span').html('PASSWORD TO LONG: IT MUST HAVE LESS THAN 32 CHARACTERS');
            return false;
        }
        for(i = 0; i < password.length; i++){
            if(password[i] == " ") {
                $(this).next('span').html('PASSWORD CANNOT CONTAIN SPACES');
                return false;
            }
        }
        $(this).next('span').html('');
        return true;
    });
</script>
<script>
    $('input[id="reg_pswd_repete"]').blur(function() {
        var passwordRepeat = $(this).val();
        var password = document.getElementById('reg_pswd').value;
        if(passwordRepeat != password) {
            $(this).next('span').html("THIS LINE DOESN'T MATCH WITH THE PASSWORD");
            return false;
        } else {
            $(this).next('span').html('');
            return true;
        }
    });
</script>
<script>
    $('input[id="reg_mail"]').blur(function() {
        var mail = $(this).val();
        for(i = 0; i < mail.length; i++){
            if(mail[i] == "@"){
                hostMail = "";
                for(j = i + 1; j < mail.length; j ++){
                    hostMail += mail[j];
                }
                if(hostMail == "gmail.com" || hostMail == "libero.it" || hostMail == "alice.it") {
                    $(this).next('span').html('');
                    return true;
                }
                $(this).next('span').html("ONLY 'gmail.com', 'libero.it' AND 'alice.it' HOSTS ARE ACCEPTED" );
                return false;
            }
        }
        $(this).next('span').html('THIS IS NOT A MAIL ACCOUNT');
        return false;
    });
</script>
<script>
    $('input[id="reg_name"]').blur(function() {
        var name = $(this).val();
        if(name.length < 1) {
            $(this).next('span').html('THIS FIELD IS EMPTY');
            return false;
        }
        else {
            $(this).next('span').html('');
            return true;
        }
    });
</script>
<script>
    $('input[id="reg_surname"]').blur(function() {
        var surname = $(this).val();
        if(surname.length < 1) {
            $(this).next('span').html('THIS FIELD IS EMPTY');
            return false;
        }
        else {
            $(this).next('span').html('');
            return true;
        }
    });
</script>
<script>
    $('input[id="reg_type"]').blur(function() {
        var typeValue = document.getElementById("reg_type").value;
        if(typeValue != "Admin" || typeValue != "User") {
            $(this).next('span').html("YOU NEED A USER TYPE");
            return false;
        } else {
            $(this).next('span').html('');
            return true;
        }
    });
</script>
<script>
    $('button[id="registration"]').blur(function() {
        if($('input[id="reg_mail"]').blur() == false ||
        $('input[id="reg_pswd"]').blur() == false ||
        $('input[id="reg_pswd_repete"]').blur() == false||
        $('input[id="reg_surname"]').blur() == false||
        $('input[id="reg_name"]').blur() == false||
        $('input[id="reg_username"]').blur() == false){
            return false;
        }
        return true;
    });
</script>
<script>
    function registrationFormValidate() {
        if($('button[id="registration"]').blur == true)
            return true;
        return false;
    }
</script>

</body>
</html>
