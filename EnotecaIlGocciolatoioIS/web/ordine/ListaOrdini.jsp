<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.bean.Ordine" %>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.Utente" %>

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
    List<Ordine> ordini= (List<Ordine>) request.getAttribute("lista");
%>


<!--sezione spazio bianco prima delle info -->
<div class="jumbotron jumbotron-fluid " style="background:transparent !important">
</div>


<div class="container" >
    <%
        if(user.getRuolo().equals("amministratore")){ %>
    <div class="row" align="right">
        <div class="col order-last">
            <div align="right">
                <form method="get" action="../account/ProfiloAmministratore.jsp">
                    <button class="btn btn-outline-Secondary align-self-xl-end"><i class="fas fa-plus fa-lg"></i><b>Torna all'Area Amministratore</b></button>
                </form>
            </div>
        </div>
    </div>
    <%}%>
    <%if(user.getRuolo().equals("utente")){%>
    <div class="row" align="right">
        <div class="col order-last">
            <div align="right">
                <form method="get" action="../account/InformazioniPersonali.jsp">
                    <button class="btn btn-outline-Secondary align-self-xl-end"><i class="fas fa-plus fa-lg"></i><b>Torna all'Area Utente</b></button>
                </form>
            </div>
        </div>
    </div>
    <%}%>
    <h3> Lista Ordini: </h3>
    <% int i=1;
    if(ordini!=null){
        for(Ordine o:ordini){ %>
    <hr>
    <div class="row ">
        <div class="col">
            <label>Ordine:<%=i%> effettuato in data: <%=o.getData_ordine()%> con indirizzo spedizione: <%=o.getIndirizzo().getFullIndirizzo()%></label>
        </div>
    </div>
    <button type="button" class="btn btn-outline-success"><a class="link" href="retrivedetails?idord=<%=o.getId_ordine()%>&iduser=<%=o.getId_utente()%>&idIndirizzo=<%=o.getId_indirizzo()%>"> Visualizza i Dettagli </a></button>

    <hr>
    <%i++;}

    }

    if(ordini.isEmpty()){%>
    <br>
    <br>
    <h5 align="center" style="color: darkred"> Non hai effettuato alcun ordine</h5>

    <%}%>
</div>

<!-- link jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- link bootstrap js -->
<script src="../javascript/bootstrap.min.js"></script>
<script src="../javascript/account/DeleteUser.js"></script>
</body>
</html>