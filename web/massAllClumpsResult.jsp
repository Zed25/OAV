<%@ page import="beans.login.ClumpBean" %>
<%@ page import="enumerations.ErrorType" %><%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 23/05/17
  Time: 10.57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBean" scope="session" class="beans.login.SearchBean"/>
<jsp:setProperty name="searchBean" property="*"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.ResultBean"/>
<jsp:include page="header.jsp"/>

<% ErrorType errorType = searchBean.getMassAllClumps(resultBean);
if (errorType == ErrorType.CATERPILLAR) {%>

<div class="container center-align">
<br>
<h3>THERE WAS AN ERROR</h3>
<h5>(Caterpillar)</h5>
<h4>Sorry for the dust. Please try again</h4>
<br>
</div>
<% } else {%>

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
<% } %>

<jsp:include page="footer.jsp"/>
