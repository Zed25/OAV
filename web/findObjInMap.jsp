<%@ page import="beans.login.SearchBean" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 30/03/17
  Time: 14.45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBean" scope="session" class="beans.login.SearchBean"/>
<jsp:setProperty name="searchBean" property="*"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.ResultBean"/>
<%  if (searchBean.isResetflag())
        searchBean.dropAllData();
    resultBean.reset();
    if (searchBean.isFullSource()) {
        searchBean.setResetflag(true);
%>
<jsp:forward page="searchResultSource.jsp"/>
<%      }
%>

<jsp:include page="header.jsp"/>

<br>

<div class="container center-align">

    <h5 style="color: slategray">Insert search params: </h5>

    <br>

    <form class="col s12" action="#" method="post">

        <div class="input-field">
            <select id="mapName" name="mapName" class="col s12 m6 l4">
                <option value="" disabled selected>Choose map name</option>
                <%
                    List<String> maps = searchBean.getAllStarMapsNameFromDB();
                    if(maps != null){
                        for(int i = 0; i < maps.size(); i++){%>
                <option value="<%=maps.get(i)%>" name="new_instr_satellite"><%=maps.get(i)%></option>
                <%}}%>
                <label>Map name</label><span></span>
            </select>
        </div>

        <div class="input-field">
            <select id="band" name="band" class="col s12 m6 l4">
                <option value="" disabled selected>Choose band resolution</option>
                <%
                    List<String> bands = searchBean.getAllBandsFromDB();
                    if(bands != null){
                        for(int i = 0; i < bands.size(); i++){%>
                <option value="<%=bands.get(i)%>" name="band"><%=bands.get(i)%></option>
                <%}}%>
                <label>Band resolution</label><span></span>
            </select>
        </div>

        <br>

        <button class="btn waves-effect waves-light" type="submit" name="searchBean">Cerca
        </button>

    </form>

    <br>

    <form class="col s12" action="index.jsp" method="post">
        <button class="btn waves-effect waves-light" type="submit" name="action">Torna indietro</button>
    </form>

</div>

<br>

<jsp:include page="footer.jsp"/>
