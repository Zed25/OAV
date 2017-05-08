<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 08/05/17
  Time: 15.53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBeanClump" scope="session" class="beans.login.search.SearchBean"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>

<jsp:include page="header.jsp"/>

<%--NON FUNGE--%>
<% if (resultBean.getValues() == null) { %>
<jsp:forward page="noMatch.jsp"/>
<%}%>

<table class="striped, striped">
    <thead>
    <tr>
        <th>Clump ID</th>
        <th>Galactic Latitude</th>
        <th>Galactic Longitude</th>
        <th>Band Width</th>
        <th>Flow Value</th>
    </tr>
    </thead>

    <tbody>

    <% for(resultBean.getCount(); resultBean.getCount()<resultBean.getClumps().size(); resultBean.incrementCount()) {
    %>
    <tr>
        <td> <%out.println((resultBean.getClumps().get(resultBean.getCount())).toString());%> </td>
        <td> <%out.println((resultBean.getGalacticLatitude().get(resultBean.getCount())).toString());%> </td>
        <td> <%out.println((resultBean.getGalacticLongitude().get(resultBean.getCount())).toString());%> </td>
        <td> <%out.println((resultBean.getBand().get(resultBean.getCount())).toString());%> </td>
        <td> <%out.println((resultBean.getValues().get(resultBean.getCount())).toString());%> </td>
    </tr>
    <% } %>

    </tbody>
</table>

<jsp:include page="footer.jsp"/>
