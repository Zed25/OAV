<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 08/05/17
  Time: 15.45
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="beans.login.search.SearchBean" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBean" scope="session" class="beans.login.search.SearchBean"/>
<jsp:setProperty name="searchBean" property="*"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>
<%  resultBean.reset();
    if (searchBean.isFullClump()) {
        if (searchBean.findClumpByID(searchBean, resultBean)) {
            %>
            <jsp:forward page="searchResultClump.jsp"/>
<%      } else { %>
            <jsp:forward page="noMatch.jsp"/>
<%      }}
%>

<jsp:include page="header.jsp"/>

<br>

<div class="container">

    <h5 style="color: slategray">Insert Clump ID: </h5>

    <br>

    <form class="col s12" action="#" method="post">

        <div class="input-field">
            <i class="material-icons prefix">mode_edit</i>
            <input placeholder="Clump ID" id="clumpID" name="clumpID" type="number" class="validate">
        </div>

        <br>

        <button class="btn waves-effect waves-light" type="submit" name="searchBeanClump">Cerca
        </button>

    </form>

    <br>

    <form class="col s12" action="index.jsp" method="post">
        <button class="btn waves-effect waves-light" type="submit" name="action">Torna indietro</button>
    </form>

</div>

<br>

<script src="js\materialize.min.js"></script>
<script type="text\javascript" src="js\jquery-2.1.1.min.js"></script>
<jsp:include page="footer.jsp"/>
