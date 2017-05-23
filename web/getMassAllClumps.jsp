<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 23/05/17
  Time: 10.48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBean" scope="session" class="beans.login.search.SearchBean"/>
<jsp:setProperty name="searchBean" property="*"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>
<jsp:include page="header.jsp"/>

<div>
    <br>
    <h5 style="color: slategray"> Calcola la massa di tutti i Clumps </h5>
    <br>
    <form class="col s12" action="massAllClumpsResult.jsp" method="post">
        <button class="waves-effect waves-light btn-large"><i class="material-icons left">cloud</i>Cerca</button>
    </form>
</div>


<jsp:include page="footer.jsp"/>
