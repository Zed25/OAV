<%@ page import="enumerations.ErrorType" %><%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 11/04/17
  Time: 17.47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="newInstrument" scope="session" class="beans.login.InstrumentBean"/>
<jsp:useBean id="loginBean" scope="session" class="beans.login.UserBean"/>

<jsp:include page="header.jsp"/>
<%
    ErrorType errorType = loginBean.getAdministrationRole().serializeInstrument(newInstrument);
    newInstrument.emptyBean();

    switch (errorType){
        case NO_ERR:%>
            <div class="row">
                Instrument insert correctly
            </div>

            <div class="row">
                <form action="index.jsp" method="post">
                    <button class="waves-effect waves-light blue" type="submit">
                        Home Page
                    </button>
                </form>
            <%break;
        case GEN_ERR:%>
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
        case NO_ADMIN:%>
            <div class="row">
                You aren't an admin. This action can't be performed.
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
    }
    %>
<jsp:include page="footer.jsp"/>
