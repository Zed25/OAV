<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 26/03/17
  Time: 19.20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
