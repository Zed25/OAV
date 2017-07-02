<%@ page import="Controllers.FileController" %>
<%@ page import="enumerations.ErrorType" %>
<%@ page import="java.io.BufferedWriter" %>
<%@ page import="java.io.FileWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dilettalagom
  Date: 04/04/17
  Time: 14.46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>

<div class="row" style="margin-top: 3%">
    <div class="col s11" style="margin-left: 10%;">

        <h5 style="color:darkslategray" >Please, select here: </h5>

        <form action="upload" method="post" enctype="multipart/form-data">
            <div class="row" style="margin-left:5%; margin-top: 10%">
                <div class="col s12">
                    <div class="col s7">
                        <div class="file-field input-field" >

                            <div class="btn">
                                <span>File</span>
                                <input type="file" name="file" accept=".csv"/>
                            </div>

                            <div class="file-path-wrapper">
                                <input class="file-path validate" type="text">
                            </div>
                        </div>
                    </div>


                    <button class="btn waves-effect waves-light blue" type="submit" style="margin-top:2%" name="validate" value="OK">Submit
                        <i class="material-icons right">send</i>
                    </button>

                </div>

            </div>
        </form>

    </div>
</div>

<%/*
    FileController c = FileController.getFileControllerInstance();
    if (c.errorToShow==ErrorType.DIFFERENTCHOOSEFILE) {

<jsp:forward page="upload.jsp"/>
}*/%>




<jsp:include page="footer.jsp"/>

