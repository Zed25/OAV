<%@ page import="enumerations.ErrorType" %>
<%@ page import="Controllers.FileController" %><%--
  Created by IntelliJ IDEA.
  User: dilettalagom
  Date: 03/07/17
  Time: 21.20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="loginBean" class="beans.login.UserBean" scope="session"/>

<jsp:include page="header.jsp"/>
<%if(loginBean.isLogged()){%>
<% FileController controller = FileController.getFileControllerInstance();
    ErrorType errorType = controller.errorToShow;
    if (errorType == ErrorType.DIFFERENTCHOOSEFILE) {%>
<div class="container center-align">
    <br>
    <h5 style="color: red">The csv you are uploading is different from the choosen one</h5>
    <h4>Please try again</h4>
    <br>
    <a class="waves-effect waves-light btn" href="selectfile.jsp">Back to Search</a>
</div>

<%} if (errorType == ErrorType.DIFFERENTTABLEFILE) {%>
<div class="container center-align">
    <br>
    <h5 style="color: red">The csv has an unexpected table structure </h5>
    <h4>Please check</h4>
    <br>
    <a class="waves-effect waves-light btn" href="selectfile.jsp">Back to Search</a>
</div>

<%} if (errorType == ErrorType.UNFORMATFILE) {%>
<div class="container center-align">
    <br>
    <h5 style="color: red">This file has different expected values </h5>
    <h4>Please check</h4>
    <br>
    <a class="waves-effect waves-light btn" href="selectfile.jsp">Back to Search</a>
</div>

<%} if (errorType == ErrorType.GEN_ERR) {%>
<div class="container center-align">
    <br>
    <h5 style="color: red">It has been impossible to read the file</h5>
    <h4>Sorry</h4>
    <br>
    <a class="waves-effect waves-light btn" href="selectfile.jsp">Back to Search</a>
</div>

<%} if (errorType == ErrorType.ALREADY_EXISTS) {%>
<div class="container center-align">
    <br>
    <h5 style="color: black">This file has already added values</h5>
    <h4>Please check</h4>
    <br>

    <a class="waves-effect waves-light btn" href="backToSearch.jsp">Back to Search</a>
</div>

<%} if (errorType == ErrorType.NO_ERR) {%>
<div class="container center-align">
    <br>
    <h5 style="color: black">File uploaded successfully</h5>
    <h4>Have fun surfing here!</h4>
    <br>

    <a class="waves-effect waves-light btn" href="backToSearch.jsp">Back to Search</a>
</div>

<% }
}%>
<jsp:include page="footer.jsp"/>

