<%@ page import="DAO.SuperDAO" %>
<%@ page import="Controllers.SearchController" %><%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 02/05/17
  Time: 16.51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="loginBean" class="beans.login.UserBean" scope="session"/>
<jsp:useBean id="searchSquareOrCircleBean" class="beans.login.squareCircleSearchBean" scope="session"/>
<jsp:setProperty name="searchSquareOrCircleBean" property="nElements" param="n_element"/>
<jsp:setProperty name="searchSquareOrCircleBean" property="elementType" param="elem_type"/>
<jsp:setProperty name="searchSquareOrCircleBean" property="areaType" param="area_type"/>
<jsp:setProperty name="searchSquareOrCircleBean" property="baseLenght" param="base_length"/>
<jsp:setProperty name="searchSquareOrCircleBean" property="galLat" param="gal_lat"/>
<jsp:setProperty name="searchSquareOrCircleBean" property="galLong" param="gal_long"/>

<jsp:include page="header.jsp"/>

<%if(!loginBean.isLogged()){%>
<h2>Error: this action can't be performed without logging in!</h2>
<h4>Please, log in with a valid username to continue</h4>
<%}else{
    if(!searchSquareOrCircleBean.isFull()){%>
        <form action="#" method="post">
            <div class="row">
                <h4 class="col s4">Choose number of element</h4>
                <%int n_element = searchSquareOrCircleBean.getMaxClumpSourcesNumber();%>
                <p id="n_element" class="range-field col s6">
                    <input type="range" name="n_element" id="test5" min="0" max="<%=n_element%>"/>
                </p>
                <h4 for="n_element" class="col s2">/<%=n_element%></h4>
            </div>
            <div class="row">
                <div class="input-field">
                    <select id="elem_type" name="elem_type" class="col s12 m6 l4">
                        <option value="" disabled selected>Choose element's type</option>
                        <option value="Clumps" name="elem_type">Clumps</option>
                        <option value="Sources" name="elem_type">Sources</option>
                    </select>
                    <label for="elem_type">Element's type</label><span></span>
                </div>
            </div>
            <div class="row">
                <div class="input-field">
                    <select id="area_type" name="area_type" class="col s12 m6 l4">
                        <option value="" disabled selected>Choose area's type</option>
                        <option value="Square" name="area_type">Square</option>
                        <option value="Circle" name="area_type">Circle</option>
                    </select>
                    <label for="area_type">Area's type</label><span></span>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s5">
                    <input id="base_length" name="base_length" type="text" class="validate">
                    <label for="base_length">Length in meters</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s5">
                    <input id="gal_lat" name="gal_lat" type="text" class="validate">
                    <label for="gal_lat">Gal lat</label>
                </div>
                <div class="input-field col s5">
                    <input id="gal_long" name="gal_long" type="text" class="validate">
                    <label for="gal_long">Gal long</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field right-align">
                    <button id="search_circle_square" class="btn waves-effect waves-light blue offset-s10" type="submit">
                        Search
                    </button>
                    <button class="btn waves-effect waves-light blue" type="button" value="Back" onClick="history.go(-1);return true;">
                        Back
                    </button>
                </div>
            </div>
        </form>
<%}else{
    searchSquareOrCircleBean.searchElementsInArea();%>
    <table class="stripped centered highlight">
        <thead>
        <tr>
            <th>N</th>
            <th><%=searchSquareOrCircleBean.getElementType()%>_ID</th>
            <th>Distance</th>
        </tr>
        </thead>
        <tbody>
            <%if(searchSquareOrCircleBean.getElementType().equals("Clumps")){
                for(int i = 0; i < Math.min(searchSquareOrCircleBean.getResultClumps().size(), Integer.parseInt(searchSquareOrCircleBean.getnElements())); i++){ //print only the min between results number and elements number query%>
            <tr>
                <td><%=i + 1%></td>
                <td><%=searchSquareOrCircleBean.getResultClumps().get(i).getClumpID()%></td>
                <td><%=searchSquareOrCircleBean.getResultClumps().get(i).getDistance()%> m</td>
            </tr>
            <%}}else{
                for(int i = 0; i < Math.min(searchSquareOrCircleBean.getResultSources().size(), Integer.parseInt(searchSquareOrCircleBean.getnElements())); i++){ //print only the min between results number and elements number query%>
            <tr>
                <td><%=i + 1%></td>
                <td><%=searchSquareOrCircleBean.getResultSources().get(i).getSourceID()%></td>
                <td><%=searchSquareOrCircleBean.getResultSources().get(i).getDistance()%> m</td>
            </tr>
            <%}%>
        </tbody>
    </table>
    <%}
        searchSquareOrCircleBean.emptyBean();}}%>
<jsp:include page="footer.jsp"/>
