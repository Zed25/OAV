<%@ page import="beans.login.ClumpBean" %><%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 23/05/17
  Time: 10.57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBean" scope="session" class="beans.login.search.SearchBean"/>
<jsp:setProperty name="searchBean" property="*"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>
<jsp:include page="header.jsp"/>

<% searchBean.getMassAllClumps(resultBean); %>

<table class="striped, striped">
    <thead>
    <tr>
        <th>Clump ID</th>
        <th>Mass</th>
    </tr>
    </thead>

    <tbody>

    <% for (ClumpBean clump : resultBean.getClumpBeans()) { %>
        <tr>
            <td> <%out.println(clump.getClumpID());%> </td>
            <td> <%out.println(clump.getMass());%> </td>
        <tr/>
    <% } %>

    </tbody>
</table>

<jsp:include page="footer.jsp"/>
