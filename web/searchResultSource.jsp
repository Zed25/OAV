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

<jsp:include page="header.jsp"/>

<table class="striped">
    <thead>
    <tr>
        <% if (!searchBean.getMapName().equals("HiGal")) {%>
        <th>Source Name</th>
        <% } else { %>
        <th>Clump ID</th>
        <% } %>
        <th>Band Width</th>
        <th>Flow Value</th>
    </tr>
    </thead>

    <tbody>
    <%--max 50 elementi visualizzati per pagina--%>

    <%--Sources case--%>

    <% if (!searchBean.getMapName().equals("HiGal")) {
        for(resultBean.getCount(); resultBean.getCount()<50*resultBean.getPage(); resultBean.incrementCount()) {
            if (resultBean.getCount()<resultBean.getSourceBeans().size()) {  %>
                    <tr>
                        <td> <%out.println(resultBean.getSourceBeans().get(resultBean.getCount()).getSourceID());%> </td>
                        <td> <%out.println(Float.toString(resultBean.getSourceBeans().get(resultBean.getCount()).getBand()));%> </td>
                        <td> <%out.println(Float.toString(resultBean.getSourceBeans().get(resultBean.getCount()).getFluxValue()));%> </td>
                    </tr>
    <% }
    }
    if (resultBean.getCount()<resultBean.getSourceBeans().size()) {%>

    <div class="row">
    <form class="col s12" action="avanti.jsp" method="get" name="nextform">
        <div class="row">
            <button class="waves-effect" type="submit" name="nextpage">next</button>
        </div>
    </form>

    <form class="col s12" action="indietro.jsp" method="get" name="previousform">
        <div class="row">
            <button class="waves-effect" type="submit" name="previouspage">previous</button>
        </div>
    </form>
    </div>

    <%--Last page reached--%>

    <% } else { %>

    <div>
        <form class="col s12" action="indietro.jsp" method="get" name="previousform">
            <div class="row">
                <button class="waves-effect" type="submit" name="previouspage">previous</button>
            </div>
        </form>
    </div>

    <% };%>

    <%--Clumps case--%>

    <%} else {
         for(resultBean.getCount(); resultBean.getCount()<50*resultBean.getPage(); resultBean.incrementCount()) {
             if (resultBean.getCount()<resultBean.getClumpBeans().size()) {  %>
                <tr>
                    <td> <%out.println(Integer.toString(resultBean.getClumpBeans().get(resultBean.getCount()).getClumpID()));%> </td>
                    <td> <%out.println(Float.toString(resultBean.getClumpBeans().get(resultBean.getCount()).getBand()));%> </td>
                    <td> <%out.println(Float.toString(resultBean.getClumpBeans().get(resultBean.getCount()).getFluxValue()));%> </td>
                </tr>

    <% }
    } if (resultBean.getCount()<resultBean.getClumpBeans().size()) {%>

    <div class="row">
        <form class="col s12" action="avanti.jsp" method="get" name="nextform">
            <div class="row">
                <button class="waves-effect" type="submit" name="nextpage">next</button>
            </div>
        </form>

        <form class="col s12" action="indietro.jsp" method="get" name="previousform">
            <div class="row">
                <button class="waves-effect" type="submit" name="previouspage">previous</button>
            </div>
        </form>
    </div>

    <%--Last page reached--%>

    <% } else { %>

    <div>
    <form class="col s12" action="indietro.jsp" method="get" name="previousform">
        <div class="row">
            <button class="waves-effect" type="submit" name="previouspage">previous</button>
        </div>
    </form>
    </div>

    <% };}%>
    </tbody>
</table>

<form class="col s12" action="backToSearch.jsp" method="post">
    <button class="waves-effect" name="backToSearchButton" id="backToSearchButton">Back to Search</button>
</form>

<jsp:include page="footer.jsp"/>
