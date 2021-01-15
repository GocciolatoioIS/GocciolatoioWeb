<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="bean.Prodotto" %>
<%@ page import="java.text.DecimalFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: vince
  Date: 10/05/2020
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!-- link bootstrap css -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/ProdottoStyle.css">

    <title>Prodotto</title>
</head>
<body>
<!-- include l'header -->
<%@ include file= "HeaderLogo.jsp"%>
<div class="jumbotron jumbotron-fluid " style="background:transparent !important"></div>
<!--sezione spazio bianco prima della sezione prodotto -->
<div class="jumbotron jumbotron-fluid " style="background:transparent !important"></div>

<%
    Prodotto p= (Prodotto) request.getAttribute("prodotto");
%>
<div class="container">
    <div class="row">
        <div class="col order-first">
            <img class="img-fluid mx-auto d-block image" src=<%=p.getImmagine()%> >
        </div>

        <div class="col order-last">
            <!-- inserire nome -->
            <h2 class="titolo-prodotto"><%=p.getNome()%></h2>

            <!-- inserire descrizione -->
            <p class="p-desc"><%=p.getDescrizione()%></p>

            <!-- inserire annata -->
            <%
               if(p.getAnno()!=0){
            %>
            <div class="caratteristiche"> <span class="h-caratteristiche"> Annata</span>
                <span><%=p.getAnno()%></span>
            </div>
            <%}%>

            <!-- inserire regione -->
            <div class="caratteristiche"> <span class="h-caratteristiche"> Regione</span>
                <span><%=p.getRegione()%></span>
            </div>

            <!-- inserire gradazione -->

            <%
                if(p.getGradazione()!=0){
            %>
            <div class="caratteristiche"> <span class="h-caratteristiche"> Gradazione</span>
                <span><%=p.getGradazione()%>%</span>
            </div>
            <%}%>

            <!-- inserire formato -->
            <%
                if(p.getFormato()!=0){
            %>
            <div class="caratteristiche"> <span class="h-caratteristiche"> Formato</span>
                <span>Bottiglia <%=p.getFormato()%> cl</span>
            </div>
            <%}%>

            <!-- inserire disponibilità calcolata sui prodotti in magazzino -->
            <div class="caratteristiche">
                <span class="h-caratteristiche"> Disponibilità: </span>
                <span class="h-caratteristiche" type="number" id="quantity" ><%=p.getQuantita_magazzino()%></span>
            </div>

            <!-- inserire tipo bianco/rosso -->
            <%
                if(p.getTipo()!=null){
            %>
            <div class="caratteristiche"> <span class="h-caratteristiche"> Tipo</span>
                <span><%=p.getTipo()%></span>
            </div>
            <%}%>

            <!-- Se lo sconto è presente viene mostrato e viene mostrato anche il prezzo scontato -->
            <%if(p.getSconto()>0){
                DecimalFormat df = new DecimalFormat("#.00");
                df.setMaximumFractionDigits(2);%>%>
            <div class="caratteristiche"> <span class="h-caratteristiche"> Sconto</span>
                <span>Sconto <%=p.getSconto()%> %</span>
            </div>

                <p class="p-head-price">Prezzo</p>
                <p class="p-price"><%=df.format(p.getPrezzo()-(p.getPrezzo()/100*p.getSconto()))%> €</p>
            <%} else{
                DecimalFormat df = new DecimalFormat("#.00");
                df.setMaximumFractionDigits(2);%>
                <!-- Altrimenti ne esce il prezzo normale -->
                <p class="p-head-price">Prezzo</p>
                <p class="p-price"><%=df.format(p.getPrezzo())%> €</p>
            <%}%>

            <!-- form quantità e bottone aggiungi al carrello -->
            <%if(p.getQuantita_magazzino()!=0){ %>
            <form class="buy-form" method="get" action="carrello">

                <input type="hidden" name="prodId" value=<%=p.getId()%>>
                <div class="value-button" id="decrease" onclick="decreaseValue()" value="Decrease Value">-</div>
                <input type="number" id="number" value="1" name="addNum"/>
                <div disabled="true" class="value-button" id="increase" onclick="increaseValue()" value="Increase Value">+</div>

                <!-- <small class="text-muted"> Acquista <a href="#" class="preferiti" ><i class="far fa-heart fa-lg"></i></a></small> -->
                <button id="aggiungi" class="btn btn-secondary">Acquista</button>
            </form>
            <!-- partial -->
            <%} else{ %>
            <h4>Il prodotto non è al momento disponibile</h4>
            <%}%>
        </div>
    </div>
</div>
<!-- link jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- link bootstrap js -->
<script src="javascript/bootstrap.min.js"></script>
<script src="javascript/QntButton.js"></script>

</body>
</html>