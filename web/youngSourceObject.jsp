<%--
  Created by IntelliJ IDEA.
  User: dilettalagom
  Date: 02/07/17
  Time: 12.44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="loginBean" class="beans.login.UserBean" scope="session"/>
<jsp:useBean id="searchBean" scope="session" class="beans.login.search.SearchBean"/>
<jsp:setProperty name="searchBean" property="*"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>

<%if(!loginBean.isLogged()){%>
<h2>Error: this action can't be performed without logging in!</h2>
<h4>Please, log in with a valid username to continue</h4>
<%}else{
    if (searchBean.isResetflag())
        searchBean.dropAllData();
    resultBean.reset();
    if (searchBean.isFullClump()) {
        searchBean.setResetflag(true);
%>
<jsp:forward page="resultYoungSource.jsp"/>
<% } %>

<jsp:include page="header.jsp"/>
<br>

<div class="row">
    <h4 style="margin-top: 2%;margin-left: 2%"> Insert the Clump for the Young Source Object</h4>
    <br>

    <form action="#" method="post" style="margin-left: 1%">

        <div class="row">
            <div class="input-field col s6">

                <input placeholder="Clump ID" id="clumpID" name="clumpID" type="text" class="validate">
                <label for="clumpID">Select your Clump</label>
            </div>
        </div>
        <div class="row" style="margin-left: 60%" >
            <button class="btn waves-effect waves-light blue offset-s10" type="submit" name="searchBean">
                Search
            </button>
        </div>
    </form>

<%
        //TEST:System.out.println("CLUMP FROM VIEW: "+searchBean.getClumpID());
    }
%>

<jsp:include page="footer.jsp"/>