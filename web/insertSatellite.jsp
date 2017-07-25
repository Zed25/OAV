<%@ page import="java.util.List" %>
<%@ page import="beans.login.AgencyBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="beans.login.AgenciesListBean" %><%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 03/04/17
  Time: 16.30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="newSatellite" scope="session" class="beans.login.SatelliteBean"/>
<jsp:setProperty name="newSatellite" property="agenciesLinked" param="agencies_in_mission"/>
<jsp:setProperty name="newSatellite" property="name" param="sat_name"/>
<jsp:setProperty name="newSatellite" property="startMissionDate" param="sat_start_mission"/>
<jsp:setProperty name="newSatellite" property="endMissionDate" param="sat_end_misison"/>
<jsp:useBean id="loginBean" scope="session" class="beans.login.UserBean"/>


<jsp:include page="header.jsp"/>

<%if(!loginBean.isAdmin()){
    out.println("<h2>Error: You must be an admin in order to perform this action!</h2>");
}else{
    if(!newSatellite.isFull()){%>
<jsp:include page="forms/insertSatelliteForm.jsp"/>
<%}else{%>
<h4>Satellite's data summary</h4>
<form action="serializeSatellite.jsp" method="post">
    <div class="row">
        Name: <%=newSatellite.getName()%>
    </div>
    <div class="row">
        Start Mission Date: <%=newSatellite.getStartMissionDate()%>
    </div>
    <div class="row">
        <%if(newSatellite.getEndMissionDate() != null){%>
            End Mission Date: <%=newSatellite.getEndMissionDate()%>
        <%}else{%>
            End Mission Date: not specified
        <%}%>
    </div>
    <div class="divider"></div>
    <!--<h6>Agencies connected to mission:</h6>-->
        <%//for(int i = 0; i < newSatellite.getAgencyPartecipationList().size(); i++){%>
        <!--<div class="row">
            <%//=newSatellite.getAgencyPartecipationList().get(i).getName()%>
        </div>-->
        <%//}%>
    <button class="btn waves-effect waves-light blue offset-s10" type="submit">
        Register
    </button>
    <button class="btn waves-effect waves-light blue" type="button" value="Back" onClick="history.go(-1);return true;">
        Back
    </button>
</form>
<%}}%>
<jsp:include page="footer.jsp"/>
