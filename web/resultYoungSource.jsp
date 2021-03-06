<%@ page import="enumerations.ErrorType" %><%--
  Created by IntelliJ IDEA.
  User: dilettalagom
  Date: 02/07/17
  Time: 12.44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="searchBean" scope="session" class="beans.login.SearchBean"/>
<jsp:setProperty name="searchBean" property="*"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.ResultBean"/>

<jsp:include page="header.jsp"/>

<% ErrorType errorType = searchBean.findYoungSuorce(searchBean, resultBean);
    if (errorType == ErrorType.NO_RESULTS) {%>
<div class="container center-align">
    <br>
    <h5>There aren't Young Sources Objects in this Clump</h5>
    <h4>Please try again</h4>
    <br>
    <a class="waves-effect waves-light btn" href="youngSourceObject.jsp">Back to Search</a>
</div>
<% } else { %>
<div class="container center-align">

    <br>
    <h5> The sources for the selected ClumpID <%=searchBean.getClumpID()%> are: </h5>

    <table class="striped">
        <thead>
        <div class="container center-block" >
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
