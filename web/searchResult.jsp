<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 30/03/17
  Time: 14.48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBean" scope="session" class="beans.login.search.SearchBean"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>
<html>
<head>
    <title>OAV</title>
</head>

<jsp:include page="header.jsp"/>
<jsp:include page="footer.jsp"/>

<body>

<%--NON FUNGE--%>
<% if (resultBean.getValues() == null) { %>
    <jsp:forward page="noMatch.jsp"/>
<%}%>

<table class="striped, striped">
    <thead>
    <tr>
        <th>Source Name</th>
        <th>Flow Value</th>
        <th>Band Width</th>
    </tr>
    </thead>

    <tbody>
    <%--max 50 elementi visualizzati per pagina--%>
    <%  for(resultBean.getCount(); resultBean.getCount()<50*resultBean.getPage(); resultBean.incrementCount()) {
            if (resultBean.getCount()<resultBean.getValues().size()) {  %>
                    <tr>
                        <td> <%out.println((resultBean.getSources().get(resultBean.getCount())));%> </td>
                        <td> <%out.println((resultBean.getValues().get(resultBean.getCount())).toString());%> </td>
                        <td> <%out.println((resultBean.getBand().get(resultBean.getCount())).toString());%> </td>
                    </tr>

         <% }
            else break;
        } %>
    </tbody>
</table>

<div class="row">
    <form class="col s12" action="avanti.jsp" method="get" name="nextform">
        <div class="row">
            <button class="material-icons" type="submit" name="nextpage">next</button>
        </div>
    </form>

    <form class="col s12" action="indietro.jsp" method="get" name="previousform">
        <div class="row">
            <button class="material-icons" type="submit" name="previouspage">previous</button>
        </div>
    </form>
</div>
</body>
</html>
