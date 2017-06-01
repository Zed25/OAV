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

<div class="container center-align">
    <table class="striped">
        <thead>
        <tr>
            <th>Clump ID</th>
            <th>Mass</th>
        </tr>
        </thead>

        <tbody>

        <% for (ClumpBean clump : resultBean.getClumpBeans()) { %>
            <tr>
                <td> <%=clump.getClumpID()%> </td>
                <td> <%=clump.getMass()%> </td>
            </tr>
        <% } %>

        </tbody>
    </table>

    <br>

    <a class="waves-effect waves-light btn" href="backToSearch.jsp">Back to Search</a>

</div>

<jsp:include page="footer.jsp"/>
