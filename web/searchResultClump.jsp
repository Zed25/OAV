<%@ page import="beans.login.ClumpBean" %>
<%@ page import="enumerations.ErrorType" %><%--
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

<% ErrorType errorType = searchBean.findClumpByID(searchBean, resultBean);
    if (errorType == ErrorType.BOAR) {%>

<div class="container center-align">
    <br>
    <h3>THERE WAS AN ERROR</h3>
    <h5>(Boar)</h5>
    <h4>Sorry for the dust. Please try again</h4>
    <br>
    <a class="waves-effect waves-light btn" href="backToSearch.jsp">Back to Search</a>
</div>
<% } else { %>

<div class="container center-align">
    <table class="striped">
        <thead>
        <div class="container center-align">
            <tr>
                <th>Clump ID</th>
                <th>Galactic Latitude</th>
                <th>Galactic Longitude</th>
                <th>Band Width</th>
                <th>Flow Value</th>
            </tr>
        </div>
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

    <br>

    <a class="waves-effect waves-light btn" href="backToSearch.jsp">Back to Search</a>
</div>
<% } %>

<jsp:include page="footer.jsp"/>
