<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 24/05/17
  Time: 16.46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBean" scope="session" class="beans.login.search.SearchBean"/>
<jsp:setProperty name="searchBean" property="*"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>
<jsp:include page="header.jsp"/>

<% searchBean.ratioBetweenLines(resultBean); %>

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

<jsp:include page="footer.jsp"/>