<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 24/05/17
  Time: 16.44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBean" scope="session" class="beans.login.search.SearchBean"/>
<jsp:setProperty name="searchBean" property="*"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>
<jsp:include page="header.jsp"/>

<div>
    <br>
    <h5 style="color: slategray"> Calcola dati medi sulla massa dei Clumps </h5>
    <br>
    <form class="col s12" action="ratioBetweenLinesResult.jsp" method="post">
        <button class="waves-effect waves-light btn-large"><i class="material-icons left">cloud</i>Calcola</button>
    </form>
</div>


<jsp:include page="footer.jsp"/>
