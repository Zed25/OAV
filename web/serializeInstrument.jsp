<%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 11/04/17
  Time: 17.47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="newInstrument" scope="session" class="beans.login.InstrumentBean"/>

<jsp:include page="header.jsp"/>
<%if(newInstrument.serializeInstrument()){%>
<div class="row">
    Instrument insert correctly
</div>

<div class="row">
    <form action="index.jsp" method="post">
        <button class="waves-effect waves-light blue" type="submit">
            Home Page
        </button>
    </form>
        <%}else{%>
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
        <%}
    newInstrument.emptyBean();%>
    <jsp:include page="footer.jsp"/>
