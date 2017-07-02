<%@ page import="Controllers.FileController" %><%--
  Created by IntelliJ IDEA.
  User: dilettalagom
  Date: 23/06/17
  Time: 19.52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp"/>

<div class="row" style="margin-top: 3%">

    <div class="col s11" style="margin-left: 10%">
        <div class="white">

            <h5 style="color:darkslategray" >Which file do you want to insert? </h5>


            <div class="" style="margin-left: 26%; margin-top: 5%">

                <form>
                    <p>
                        <input class="with-gap" type="radio" name="filescelto" id="higal" value="higal.csv"/>
                        <label for="higal" id="filescelto" style="color: darkslategray" data-error="wrong" data-success="ok" >Higal</label>
                    </p>

                    <p>
                        <input class="with-gap" type="radio" name="filescelto" id="higal_additionalinfo" value="higal_additionalinfo.csv"/>
                        <label for="higal_additionalinfo" style="color: darkslategray">Higal_additionalinfo</label>

                    </p>

                    <p>
                        <input class="with-gap" type="radio" name="filescelto" id="mips"  value="mips.csv"/>
                        <label for="mips" style="color: darkslategray">Mips</label>
                    </p>

                    <p>
                        <input class="with-gap" type="radio" name="filescelto" id="r08" value="r08.csv"/>
                        <label for="r08" style="color: darkslategray">R08</label>
                    </p>


                    <button class="btn waves-effect waves-light blue" type="submit" style="margin-top:2%" value="validate" >Submit
                        <i class="material-icons right">send</i>
                    </button>
                </form>

            </div>

        </div>
        <%
            String filescelto = "";
            if (request.getParameter("filescelto")!=null){
                filescelto= request.getParameter("filescelto");

                FileController c = FileController.getFileControllerInstance();
                c.filescelto = filescelto;

                // Test: System.out.println("selectfile" + filescelto);
        %>
                <jsp:forward page="csvInsert.jsp"/>
        <%
            }

        /*if (request.getParameter("validateform")== on)
        response.sendRedirect("upload.jsp");*/
        //System.out.println(request.getParameter("validate"));
        %>
    </div>



<jsp:include page="footer.jsp"/>