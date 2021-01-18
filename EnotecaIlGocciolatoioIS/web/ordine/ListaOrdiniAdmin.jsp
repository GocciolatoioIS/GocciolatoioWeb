<%@ page import="model.bean.Prodotto" %>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.Ordine" %>
<%@ page import="model.bean.Utente" %><%--
  Created by IntelliJ IDEA.
  User: dimy1
  Date: 07/01/2021
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista Ordini</title>

    <!-- link bootstrap css -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">

    <link rel="stylesheet" href="../css/Link.css">
</head>
<body>
<!-- include l'header -->
<%@ include file= "../HeaderLogo.jsp"%>

<%
    Utente user= (Utente) request.getSession().getAttribute("utente");
    List<Ordine> lista= (List<Ordine>) request.getAttribute("lista");
%>


<!--sezione spazio bianco prima delle info -->
<div class="jumbotron jumbotron-fluid " style="background:transparent !important">
</div>

<div class="container">
    <div class="row" align="right">
        <div class="col order-last">
            <div align="right">
                <form method="get" action="../account/ProfiloAmministratore.jsp">
                    <button class="btn btn-outline-Secondary align-self-xl-end"><i class="fas fa-plus fa-lg"></i><b>Torna all'Area Amministratore</b></button>
                </form>
            </div>
        </div>
    </div>
    <h3>Lista Ordini:</h3>
    <%
        for(Ordine o:lista){ %>
    <hr>
    <div class="row ">
        <div class="col">
            <label><b>Ordine effettuato in data:</b><%=o.getData_ordine()%></Label>
            <label><b>con indirizzo spedizione:</b> <%=o.getIndirizzo().getFullIndirizzo()%></label>
            <label><b>Utente <%=o.getUtente().getUsername()%></b>:<%=o.getUtente().getNome()%>,<%=o.getUtente().getCognome()%>,<%=o.getUtente().getData_nascita()%>,<%=o.getUtente().getRuolo()%>
                ,<%=o.getUtente().getEmail()%></label>
        </div>
    </div>
    <button type="button" class="btn btn-outline-success"><a class="link" href="retrivedetails?idord=<%=o.getId_ordine()%>&iduser=<%=o.getId_utente()%>&idIndirizzo=<%=o.getId_indirizzo()%>"> Visualizza i Dettagli </a></button>
        <%
        if(user.getRuolo().equals("amministratore")){ %>
    <button type="button" class="btn btn-outline-danger"><a class="link" href="deleteorder?id=<%=o.getId_ordine()%>" onclick="myFunction()"> Cancella Ordine </a> </button>
        <%} else { %>
    <button type="button" hidden=hidden class="btn btn-outline-secondary" >Cancella Ordine  </button>
        <%}%>
    <hr>
<%}%>
</div>
<script src="../javascript/account/DeleteUser.js"></script>
</body>
</html>
