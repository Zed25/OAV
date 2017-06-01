<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 30/03/17
  Time: 14.48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="searchBean" scope="session" class="beans.login.search.SearchBean"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>

<jsp:include page="header.jsp"/>

<%--max 50 elementi visualizzati per pagina--%>

<%--Sources case--%>

<% if (!searchBean.getMapName().equals("HiGal")) {
    request.setAttribute("Sources", resultBean.getSourceBeans());%>
    <div class="container center-align">
        <display:table name="Sources" pagesize="50">
            <display:column property="sourceID" title="Source ID"/>
            <display:column property="band" title="Band Width"/>
            <display:column property="fluxValue" title="Flux Value"/>
        </display:table>
    </div>

<%--Clumps case--%>

<%} else {
    request.setAttribute("Clumps", resultBean.getClumpBeans());%>
    <div class="container center-align">
        <div class="container center-align">
            <display:table name="Clumps" pagesize="50">
                <display:column property="clumpID" title="Clump ID"/>
                <display:column property="band" title="Band Width"/>
                <display:column property="fluxValue" title="Flux Value"/>
            </display:table>
        </div>

<% } %>

<div class="container center-align">
    <a class="waves-effect waves-light btn" href="backToSearch.jsp">Back to Search</a>
</div>

<br>

<jsp:include page="footer.jsp"/>
