<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="beans.login.search.SearchBean" %>
<%@ page import="enumerations.ErrorType" %>
<%@ page import="csvReader.SourceClumpController" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: dilettalagom
  Date: 27/05/17
  Time: 18.34
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
    if (searchBean.isFullSourceinClump()) {
        searchBean.setResetflag(true);
%>
<jsp:forward page="resultSourcesClump.jsp"/>
<% } %>

<jsp:include page="header.jsp"/>

<div class="row">
    <h4 style="margin-top: 2%;margin-left: 2%"> Insert the Clump and the Band</h4>

    <form action="#" method="post" style="margin-left: 1%">

        <div class="row">
            <div class="input-field col s6">

                <input placeholder="Clump ID" id="clumpID" name="clumpID" type="text" class="validate">
                <label for="clumpID">Select your Clump</label>
            </div>
        </div>

        <div class="row">
            <div class="input-field">
                <select id="band" name="band" class="col s12 m6 l4">
                    <option value="" disabled selected>Choose band's value</option>

                    <%  List<String> bande = Arrays.asList("70.0", "160.0", "250.0", "350.0", "500.0");
                        for(int i = 0; i < bande.size(); i++){%>
                            <option value="<%=bande.get(i)%>" name="band"><%=bande.get(i)%></option>
                    <%}%>
                    <label>Band's value</label><span></span>
                </select>
            </div>
        </div>

        <div class="row" style="margin-left: 60%" >
            <button class="btn waves-effect waves-light blue offset-s10" type="submit" name="searchBean">
                Search
            </button>
        </div>
    </form>
</div>

<%
    //TEST:System.out.println("source.jsp " + searchBean.getBand() + " " +searchBean.getClumpID());

}
%>

<jsp:include page="footer.jsp"/>
