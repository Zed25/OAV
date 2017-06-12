<%@ page import="java.util.List" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: dilettalagom
  Date: 27/05/17
  Time: 18.34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="newSource" scope="session" class="beans.login.SourceBean"/>

<jsp:include page="header.jsp"/>


<div class="row">

    <h4 style="margin-top: 2%;margin-left: 2%"> Insert the Clump and the Band</h4>

    <form class="col s12" style="margin-top: 2%">

        <div class="row">
            <div class="input-field col s6">
                <input placeholder="clumpID" id="first_name" type="text" class="validate">
                <label for="first_name">Select your Clump</label>
            </div>
        </div>
    </form>

    <form id="new_instrument_form" action="#" method="post" style="margin-left: 1%">
        <div class="row">
            <div class="input-field">
                <select id="select_band" name="select_band" class="col s12 m6 l4">
                    <option value="" disabled selected>Choose satellite's name</option>
                    <%ArrayList<Integer> bande = new ArrayList<Integer>(Arrays.asList(70,160,250,350,500));
                        for(int i = 0; i < bande.size(); i++){%>
                    <option value="<%=bande.get(i)%>" name="select_band"><%=bande.get(i)%></option>
                    <%}%>
                    <label>Satellite's name</label><span></span>
                </select>
            </div>
        </div>
        <div class="row" style="margin-left: 60%" >
            <button class="btn waves-effect waves-light blue offset-s10" type="submit">
                Search
            </button>
            <button class="btn waves-effect waves-light blue" type="button" value="Back" onClick="history.go(-1);return true;">
                Back
            </button>
        </div>
    </form>
</div>



<jsp:include page="footer.jsp"/>
