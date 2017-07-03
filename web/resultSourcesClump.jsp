<%@ page import="enumerations.ErrorType" %><%--Created by IntelliJ IDEA.
User: dilettalagom
Date: 01/07/17
Time: 18.34
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBean" scope="session" class="beans.login.search.SearchBean"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>

<jsp:include page="header.jsp"/>

<% ErrorType errorType = searchBean.getElementsFromBean(searchBean, resultBean);
    if (errorType == ErrorType.NO_RESULTS) {%>
<div class="container center-align">
    <br>
    <h5>There aren't sources in this Clump at this band</h5>
    <h4>Please try again</h4>
    <br>
    <a class="waves-effect waves-light btn" href="sourceInClump.jsp">Back to Search</a>
</div>
   <% } else { %>
<div class="container center-align">

    <br>
    <h5> The sources for the selected ClumpID <%=searchBean.getClumpID()%> at <%=searchBean.getBand()%> band are: </h5>

    <table class="striped ">
        <thead>
        <div class="container center-align">
            <tr>
                <th class="center-align">Sources</th>
            </tr>
        </div>
        </thead>

        <tbody>

        <%for (resultBean.getCount(); resultBean.getCount()<resultBean.getSourceBeans().size(); resultBean.incrementCount()) {
        %>

        <tr>
            <% if (resultBean.getCount() >= 0) { %>
            <td class="center-align"> <%out.println(resultBean.getSourceBeans().get(resultBean.getCount()).getSourceID());%> </td>
            <% } %>
        </tr>
        <% } %>

        </tbody>
    </table>

    <br>

    <a class="waves-effect waves-light btn" href="backToSearch.jsp">Back to Search</a>
</div>
<% } %>
<jsp:include page="footer.jsp"/>
