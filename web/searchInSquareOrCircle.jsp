<%@ page import="enumerations.ErrorType" %><%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 02/05/17
  Time: 16.51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="loginBean" class="beans.login.UserBean" scope="session"/>
<jsp:useBean id="searchSquareOrCircleBean" class="beans.login.SquareCircleSearchBean" scope="session"/>
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
        <jsp:include page="forms/searchInAreaForm.jsp"/>
<%}else{
    ErrorType errorType = searchSquareOrCircleBean.searchElementsInArea();
    if(errorType == ErrorType.NO_ERR){%>
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
    <%}}else {
        if(errorType == ErrorType.GEN_ERR){%>
            <h4 class="red-text">An error occurred. Please, try again!</h4>
        <%}else if(errorType == ErrorType.NO_RESULTS){
            out.write("<h4 class=\"red-text\">No matches. Please, try again with other parameters!</h4>");
        }else if(errorType == ErrorType.BAD_VALUE){
            out.write("<h4 class=\"red-text\">Wrong values inserted. Insert numbers and try again, please!</h4>");
        }%>
            <jsp:include page="forms/searchInAreaForm.jsp"/>
    <%}
    searchSquareOrCircleBean.emptyBean();}
}%>
<jsp:include page="footer.jsp"/>
