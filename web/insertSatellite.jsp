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
<jsp:setProperty name="newSatellite" property="agenciesConnected" param="agencies_in_mission"/>
<jsp:setProperty name="newSatellite" property="name" param="sat_name"/>
<jsp:setProperty name="newSatellite" property="startMissionDate" param="sat_start_mission"/>
<jsp:setProperty name="newSatellite" property="endMissionDate" param="sat_end_misison"/>

<jsp:include page="header.jsp"/>
<h4>Satellite's data</h4>
<div class="row">
    <form id="new_satellite_form" action="#" method="post">
        <div class="row">
            <div class="input-field">
                <input id="sat_name" name="sat_name" type="text" class="validate"><span></span>
                <label for="sat_name">Satellite's Name</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field">
                <label for="sat_start_mission">Star Mission Date</label>
                <input id="sat_start_mission" name="sat_start_mission" type="date" class="datepicker"><span></span>
            </div>
        </div>
        <div class="row">
            <div class="input-field">
                <label>End Mission Date(if it's over)</label>
                <input id="sat_end_misison" name="sat_end_misison" type="date" class="datepicker"><span></span>
            </div>
        </div>
        <div class="divider"></div>
        <div id="agencies_satellite">
            <h5>Agencies included</h5>
            <%AgenciesListBean agencyList = new AgenciesListBean();
            agencyList.getAllAgencies();
            if(agencyList.getAgencyBeansList() != null && agencyList.getAgencyBeansList().size() > 0){
                System.out.println(agencyList.getAgencyBeansList().get(0).getName());
                for(int i = 0; i < agencyList.getAgencyBeansList().size(); i++) {%>
                    <div class="row">
                        <input type="checkbox" id="<%="checkbox" + i%>" name="agencies_satellite_name" onchange="connectAgencyToSatellite(this.id, 'agencies_to_satellite')"/>
                        <label for="<%="checkbox" + i%>" id="label_<%="checkbox" + i%>"><%=agencyList.getAgencyBeansList().get(i).getName()%></label>
                    </div>
                <%}}%>
        </div>
        <div class="row">
            <input type='text area' id='label_new_agency'>
            <button class="btn waves-effect waves-light blue" type="button" onClick="addNewAgency('agencies_to_satellite')">Add</button>
        </div>
        <input id="agencies_to_satellite" type="text" name="agencies_in_mission">
        <div class="row">
            <div class="input-field right-align">
                <button class="btn waves-effect waves-light blue offset-s10" type="submit">
                    Register
                </button>
                <button class="btn waves-effect waves-light blue" type="button" value="Back" onClick="history.go(-1);return true;">
                    Back
                </button>
            </div>
        </div>
    </form>
</div>
<jsp:include page="footer.jsp"/>
