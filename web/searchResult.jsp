<%--
  Created by IntelliJ IDEA.
  User: andrea
  Date: 30/03/17
  Time: 14.48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="searchBean" scope="session" class="beans.login.search.SearchBean"/>
<jsp:useBean id="resultBean" scope="session" class="beans.login.search.ResultBean"/>
<html>
<head>
    <title>OAV</title>
</head>
<jsp:include page="header.jsp"/>
<jsp:include page="footer.jsp"/>
<body>
<table>
    <thead>
    <tr>
        <th>Source Name</th>
        <th>Flow Value</th>
        <th>Band Width</th>
    </tr>
    </thead>

    <tbody>
    <tr>
        <td><% for(int i=0; i<resultBean.getSources().size(); i++) {
            out.println((resultBean.getSources().get(i)));
        }%>
        </td>
        <td><% for(int i=0; i<resultBean.getValues().size(); i++) {
            out.println((resultBean.getValues().get(i)).toString());
        }%>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>
