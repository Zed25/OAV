<%@ page import="beans.login.ClumpBean" %><%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 08/05/17
  Time: 15.53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBean" scope="session" class="beans.login.search.SearchBean"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>

<jsp:include page="header.jsp"/>

<table class="striped">
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

    <% for (resultBean.getCount(); resultBean.getCount()<resultBean.getClumpBeans().size(); resultBean.incrementCount()) {
    %>
    <tr>
        <% if (resultBean.getCount() == 0) { %>
        <td> <%out.println(resultBean.getClumpBeans().get(resultBean.getCount()).getClumpID());%> </td>
        <td> <%out.println(resultBean.getClumpBeans().get(resultBean.getCount()).getGalLat());%> </td>
        <td> <%out.println(resultBean.getClumpBeans().get(resultBean.getCount()).getGalLong());%> </td>
        <% } else { %>
        <td></td>
        <td></td>
        <td></td>
        <% } %>
        <td> <%out.println(Float.toString(resultBean.getClumpBeans().get(resultBean.getCount()).getBand()));%> </td>
        <td> <%out.println(Float.toString(resultBean.getClumpBeans().get(resultBean.getCount()).getFluxValue()));%> </td>
    </tr>
    <% } %>

    </tbody>
</table>

<form class="col s12" action="backToSearch.jsp" method="post">
    <button class="waves-effect" name="backToSearchButton" id="backToSearchButton">Back to Search</button>
</form>

<jsp:include page="footer.jsp"/>
