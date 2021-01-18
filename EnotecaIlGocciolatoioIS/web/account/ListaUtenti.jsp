<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="model.bean.Utente" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: vince
  Date: 24/07/2020
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista Utenti</title>

    <!-- link bootstrap css -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">

    <link rel="stylesheet" href="../css/Link.css">

</head>
<body>
<!-- include l'header -->
<%@ include file= "../HeaderLogo.jsp"%>

<%
    List<Utente> lista= (List<Utente>) request.getAttribute("lista");
%>


<!--sezione spazio bianco prima delle info -->
<div class="jumbotron jumbotron-fluid " style="background:transparent !important">
</div>



<div class="container">


    <div class="row" align="right">
        <div class="col order-last">
            <div align="right">
                <form method="get" action="ProfiloAmministratore.jsp">
                    <button class="btn btn-outline-Secondary align-self-xl-end"><i class="fas fa-plus fa-lg"></i><b>Torna all'Area Amministratore</b></button>
                </form>
            </div>
        </div>
    </div>
    <h3> Lista Utenti</h3>

    <hr>

    <% for(Utente u:lista){ %>

    <div class="container-informazioni-film" >
        <label>EMAIL:</label>
        <h4> <%=u.getEmail()%> </h4>
        <label>NOME UTENTE:</label>
        <h4><%=u.getUsername()%></h4>
        <label>RUOLO:</label>
        <h4><%=u.getRuolo()%></h4>

        <button ><i class="far fa-list-alt fa-lg"></i> <a class="btn btn-outline-success" href="finduser?id=<%=u.getId()%>"> Modifica </a></button>
        <button ><i class="far fa-list-alt fa-lg"></i> <a class="btn btn-outline-danger" href="deleteuser?id=<%=u.getId()%>" onclick="myFunction()"> Rimuovi </a></button>

        <hr>

    </div>
    <%}%>

</div>


<!-- link jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- link bootstrap js -->
<script src="../javascript/bootstrap.min.js"></script>
<script src="../javascript/account/DeleteUser.js"></script>

</body>

</html>