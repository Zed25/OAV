<%@ page import="java.util.List" %>
<%@ page import="beans.login.AgenciesListBean" %><%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 11/04/17
  Time: 15.09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="newInstrument" scope="session" class="beans.login.InstrumentBean"/>
<jsp:setProperty name="newInstrument" property="name" param="new_instr_name"/>
<jsp:setProperty name="newInstrument" property="satellite" param="new_instr_satellite"/>
<jsp:setProperty name="newInstrument" property="map" param="new_instr_map"/>
<jsp:setProperty name="newInstrument" property="bandsFromFormString" param="new_instr_bands"/>
<jsp:useBean id="loginBean" scope="session" class="beans.login.UserBean"/>

<jsp:include page="header.jsp"/>
<%if(!loginBean.isAdmin()){
    out.println("<h2>Error: You must be an admin in order to perform this action!</h2>");
}else{
    if(!newInstrument.isFull()){%>
<h4>Instrument's data</h4>
<div class="row">
    <form id="new_instrument_form" action="#" method="post">
        <div class="row">
            <div class="input-field">
                <input id="new_instr_name" name="new_instr_name" type="text" class="validate"><span></span>
                <label for="new_instr_name">Instrument's Name</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field">
                <select id="new_instr_satellite" name="new_instr_satellite" class="col s12 m6 l4">
                    <option value="" disabled selected>Choose satellite's name</option>
                    <%List<String> satellites = newInstrument.getAllSatellitesNameFromDB();
                        if(satellites != null){
                            for(int i = 0; i < satellites.size(); i++){%>
                    <option value="<%=satellites.get(i)%>" name="new_instr_satellite"><%=satellites.get(i)%></option>
                    <%}}%>
                    <label>Satellite's name</label><span></span>
                </select>
            </div>
            <div class="input-field">
                <select id="new_instr_map" name="new_instr_map" class="col s12 m6 l4">
                    <option value="" disabled selected>Choose maps's name</option>
                    <%
                        List<String> maps = newInstrument.getAllStarMapsNameFromDB();
                        if(maps != null){
                            for(int i = 0; i < maps.size(); i++){%>
                    <option value="<%=maps.get(i)%>" name="new_instr_satellite"><%=maps.get(i)%></option>
                    <%}}%>
                    <label>Map's name</label><span></span>
                </select>
            </div>
        </div>
        <div class="divider"></div>
        <div id="agencies_satellite">
            <h5>Bands</h5>
            <%List<String> bands = newInstrument.getAllBandsFromDB();
                if(bands != null){
                    for(int i = 0; i < bands.size(); i++) {%>
            <div class="row">
                <input type="checkbox" id="<%="checkbox" + i%>" name="agencies_satellite_name" onchange="connectCheckBosListToBean(this.id, 'new_instr_bands')"/>
                <label for="<%="checkbox" + i%>" id="label_<%="checkbox" + i%>"><%=bands.get(i)%></label>
            </div>
            <%}}%>
        </div>
        <input id="new_instr_bands" type="text" name="new_instr_bands">
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

    <h3>If Satellite or Map not in list go to <a href="insertSatellite.jsp">insertSatellite</a> or insertMap</h3>
</div>
<%}else{%>
<h4>Satellite's data summary</h4>
<form action="serializeInstrument.jsp" method="post">
    <div class="row"></div>
    Name: <%=newInstrument.getName()%>
    </div>
    <div class="row">
        Satellite's name: <%=newInstrument.getSatellite()%>
    </div>
    <div class="row">
        Map's name: <%=newInstrument.getMap()%>
    </div>
    <div class="divider"></div>
    <h6>measured bands:</h6>
    <%for(int i = 0; i < newInstrument.getBands().size(); i++){%>
    <div class="row">
        <%=newInstrument.getBands().get(i)%>
    </div>
    <%}%>
    <button class="btn waves-effect waves-light blue offset-s10" type="submit">
        Register
    </button>
    <button class="btn waves-effect waves-light blue" type="button" value="Back" onClick="history.go(-1);return true;">
        Back
    </button>
</form>
<%}
}%>
<jsp:include page="footer.jsp"/>