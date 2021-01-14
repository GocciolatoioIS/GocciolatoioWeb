<%@ page import="bean.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Indirizzo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <!--link bootstrap -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- link css -->
    <link rel="stylesheet" href="css/InformazioniPersonali.css">
    <!-- link icone -->
    <script src="https://kit.fontawesome.com/cf835fc8f8.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="css/Link.css">

    <title>Il mio Account</title>
</head>
<body>
<%
    Utente user= (Utente) request.getSession().getAttribute("utente");
%>
<!-- include l'header -->
<%@ include file= "HeaderLogo.jsp"%>

<!--sezione spazio bianco prima delle info -->
<div class="jumbotron jumbotron-fluid " style="background:transparent !important">
</div>

<div class="container">

    <div class="title">Il mio Account</div>

    <input type="hidden" name="id" value="${utente.id}">

    <div class="row">
        <div class="col order-first">
            <img class="fotoUtente" src="img/astronaut.jpeg" alt="FotoUtente">
            <h3 class="NomeFoto"><%=user.getUsername() %></h3>
        </div>

        <div class="col order-second">

            <ul class="Informazioni">
                <li><h5 class="info-items">Email: <%=user.getEmail() %></h5></li>
                <li><h5 class="info-items ">Nome: <%=user.getNome()%></h5></li>
                <li><h5 class="info-items ">Cognome: <%=user.getCognome()%></h5></li>
                <li><h5 class="info-items ">Data di Nascita: <%=user.getData_nascita()%></h5></li>
            </ul>


        </div>

        <div class="col order-last">

            <button><i class="fas fa-pencil-alt" ></i> <a class="linkA" href="ModificaUtente.jsp">Modifica </a> </button>
            <button><i class="fas fa-list fa-lg" ></i> <a class="linkA" href="retriveorderuser">visualizza i tuoi ordini </a> </button>
            <button><i class="fas fa-list fa-lg" ></i> <a class="linkA" href="AggiungiIndirizzo.jsp">Inserisci Indirizzo</a> </button>
            <button><i class="fas fa-sign-out-alt"></i> <a class="linkA" href="logout">Logout </a> </button>
        </div>

    </div>
    <br>
    <br>
</div>
<div class="container">
    <div class="title">I miei indirizzi:</div>



        <%
            List<Indirizzo> listI=user.getIndirizzoList();
            if(listI!= null){
            for(int i=0;i<listI.size();i++){
                Indirizzo ind=listI.get(i);    %>
    <div class="row">
            <div class="col order-second">
                <ul class="Indirizzi">
                <li><h4 class=""><%=i+1%>)Indirizzo: <%=ind.getNazione()%>,<%=ind.getCitta() %>,<%=ind.getVia()%> , <%=ind.getNumCivico()%>,<%=ind.getCap()%>
                    </h4>
                </li>
                    </ul>
            </div>
            <div class="col order-last">
                <a class="linkA" href="mod-indirizzo?action=redirect&id=<%=ind.getId()%>"> <button><i class="fa fa-pencil" aria-hidden="true"></i></button></a>
                <a class="linkA" href="mod-indirizzo?action=delete&id=<%=ind.getId()%>"> <button><i class="fa fa-trash" aria-hidden="true"></i></button></a>


            </div>
    </div>
        <%}}

            if(listI.isEmpty()){%>
    <br>
    <br>
    <h5 align="center" style="color: darkred"> Non hai alcun indirizzo inserito</h5>

    <%}%>



    <div class="col order-last">

       <button><i class="fas fa-list fa-lg" ></i> <a class="linkA" href="AggiungiIndirizzo.jsp">Inserisci Indirizzo</a> </button>
    </div>
    <br>
    </div>
</div>

<br>
<br>
</body>
</html>