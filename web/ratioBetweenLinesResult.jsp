<%@ page import="enumerations.ErrorType" %><%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 24/05/17
  Time: 16.46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBean" scope="session" class="beans.login.SearchBean"/>
<jsp:setProperty name="searchBean" property="*"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.ResultBean"/>
<jsp:include page="header.jsp"/>

<% ErrorType errorType = searchBean.ratioBetweenLines(resultBean);
    if (errorType == ErrorType.CENTIPEDE) {%>

<div class="container center-align">
    <br>
    <h3>THERE WAS AN ERROR</h3>
    <h5>(Centipede)</h5>
    <h4>Sorry for the dust. Please try again</h4>
    <br>
    <a class="waves-effect waves-light btn" href="backToSearch.jsp">Back to Search</a>
</div>

<% } else { %>

<div class="container center-align">
    <table class="striped">
        <thead>
        <tr>
            <th>Medium Value</th>
            <th>Standard Deviation</th>
            <th>Median</th>
            <th>MAD</th>
        </tr>
        </thead>

        <tbody>

        <tr>
            <td> <%=resultBean.getMediumValue()%> </td>
            <td> <%=resultBean.getStandardDeviation()%> </td>
            <td> <%=resultBean.getMedian()%> </td>
            <td> <%=resultBean.getMAD()%> </td>
        </tr>

        </tbody>
    </table>

    <br>

    <a class="waves-effect waves-light btn" href="backToSearch.jsp">Back to Search</a>

</div>
<% } %>

<jsp:include page="footer.jsp"/>