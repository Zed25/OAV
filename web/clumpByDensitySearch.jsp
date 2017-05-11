<%@ page import="java.util.List" %>
<%@ page import="beans.login.ClumpBean" %><%--
  Created by IntelliJ IDEA.
  User: simone
  Date: 28/04/17
  Time: 19.28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="loginBean" class="beans.login.UserBean" scope="session"/>
<jsp:include page="header.jsp"/>

<%if(!loginBean.isLogged()){%>
    <h2>Error: this action can't be performed without logging in!</h2>
    <h4>Please, log in with a valid username to continue</h4>
<%}else{
    ClumpBean clump = new ClumpBean();
    List<ClumpBean> clumpBeans = clump.getClumpsByDensity(0.1F, 1.0F);
    %>
     <table>
        <thead>
          <tr>
              <th>Clump's ID</th>
              <th>Density</th>
              <th>Population's fraction</th>
          </tr>
        </thead>
        <tbody>
        <%for(int i = 0; i < clumpBeans.size(); i ++){%>
          <tr>
            <td><%=clumpBeans.get(i).getClumpID()%></td>
            <td><%=clumpBeans.get(i).getDensity()%></td>
            <td>0</td>
          </tr>
        <%}%>
        </tbody>
     </table>
  <%}%>
 <div class="row">
     <form action="index.jsp" method="get">
         <button class="waves-effect waves-light blue left-align" type="submit">
             Home Page
         </button>
     </form>
 </div>
<jsp:include page="footer.jsp"/>


