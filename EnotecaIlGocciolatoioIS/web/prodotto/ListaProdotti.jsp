<%@ page import="model.bean.Prodotto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: vince
  Date: 01/09/2020
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ListaProdotti</title>

    <!-- link bootstrap css -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">

    <link rel="stylesheet" href="../css/Link.css">
</head>
<body>
<!-- include l'header -->
<%@ include file= "../HeaderLogo.jsp"%>

<%
    List<Prodotto> lista= (List<Prodotto>) request.getAttribute("prodotti");
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
    <h3>Lista Prodotti</h3>
    <%
        for(Prodotto p:lista){
    %>

    <hr>
    <div class="row ">

        <div class="col-sm-">
            <img class="img-prodotto" src="<%=p.getImmagine()%>" alt="" >
        </div>

        <div class="col">

            <p>Id Prodotto: <%=p.getId()%> </p>
            <p>Nome Prodotto: <%=p.getNome()%></p>
            <p>Disponibilità Magazzino: <%=p.getQuantita_magazzino()%></p>

            <button type="button" class="btn btn-outline-success"> <a class="link " href="findproduct?id=<%=p.getId()%>"> Modifica Prodotto </a></button>
            <button type="button" class="btn btn-outline-danger" ><a class="link" href="deleteproduct?id=<%=p.getId()%>" onclick="myFunction()"> Rimuovi Prodotto </a></button>

        </div>

    </div>
    <%}%>
    <br><br><br>
    <div class="row" align="right">
        <form method="get" action="AggiungiProdotto.jsp">
                <button class="btn btn-outline-secondary"><i class="fas fa-plus fa-lg"></i> Aggiungi Prodotto</button>
         </form>
    </div>
    <br><br><br>
</div>
<script src="../javascript/account/DeleteUser.js"></script>
</body>
</html>