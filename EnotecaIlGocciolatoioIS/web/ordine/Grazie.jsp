<%--
  Created by IntelliJ IDEA.
  User: dipal
  Date: 04/09/2020
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Grazie per aver Acquistato</title>
    <link href="../css/HomePageStyle.css">
</head>
<body>
    <%@include file="../Header.jsp"%>

    <%
        for(int i=0;i<5;i++){
    %>
        <br>
    <%}%>

    <h1 align="center" >Grazie per aver Acquistato!</h1>

    <%
        for(int i=0;i<5;i++){
    %>
    <br>
    <%}%>

    <%@include file="../Footer.jsp"%>

</body>
</html>
