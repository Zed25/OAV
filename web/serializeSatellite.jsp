<%@ page import="enumerations.ErrorType" %><%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 05/04/17
  Time: 21.38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="newSatellite" scope="session" class="beans.login.SatelliteBean"/>
<jsp:useBean id="loginBean" scope="session" class="beans.login.UserBean"/>

<jsp:include page="header.jsp"/>
<%
    ErrorType errorType = newSatellite.serializeSatellite(loginBean);
    switch (errorType){
        case NO_ERR:
            newSatellite.emptyBean();%>
            <div class="row">
                Satellite insert correctly
            </div>
            <div class="row">
                <form action="index.jsp" method="post">
                    <button class="waves-effect waves-light blue" type="submit">
                        Home Page
                    </button>
                </form>
            <%break;
        case GEN_ERR:
            newSatellite.emptyBean();%>
            <div class="row">
                A problem occurred, try again please.
            </div>
            <div class="row">
                <form action="insertSatellite.jsp" method="get">
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
            <%break;
    }%>
<jsp:include page="footer.jsp"/>
