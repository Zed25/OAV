<%@ page import="java.util.List" %>
<%@ page import="beans.login.ClumpBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="enumerations.ErrorType" %>
<%@ page import="beans.login.ResultBean" %><%--
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
<%}else {
    List<ClumpBean> searchResults = new ArrayList<>();
    ResultBean percClumpPop = new ResultBean();
    ErrorType errorType = loginBean.getClumpsByDensity(searchResults, percClumpPop);
    switch (errorType){
        case GEN_ERR:
            out.println("<h4 class=\"red-text\">Something went wrong! There aren't results. We are sorry for it!</h4>");
            break;
        case NO_ERR:%>
            <h4>Clump population percentage: <%=percClumpPop.getPercClumpPop()*100%>%</h4>
            <table>
                <thead>
                    <tr>
                        <th>Clump's ID</th>
                        <th>Density</th>
                        <th>Population's fraction (%)</th>
                    </tr>
                </thead>
            <tbody>
            <%for(int i = 0; i < searchResults.size(); i ++){%>
              <tr>
                <td><%=searchResults.get(i).getClumpID()%></td>
                <td><%=searchResults.get(i).getDensity()%></td>
                  <%if(searchResults.get(i).getPercPop() != 0.0){%>
                <td><%=searchResults.get(i).getPercPop()*100%></td>
                  <%}else{%>
                      <td>N/A</td>
                  <%}%>
              </tr>
            <%}%>
            </tbody>
            </table>
          <%break;
    }%>
 <div class="row">
     <form action="index.jsp" method="get">
         <button class="waves-effect waves-light blue left-align" type="submit">
             Home Page
         </button>
     </form>
 </div>
<%}%>
<jsp:include page="footer.jsp"/>


