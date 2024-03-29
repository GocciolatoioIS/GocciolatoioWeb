<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.Prodotto" %>
<%@ page import="model.bean.Categoria" %>
<%@ page import="java.util.ArrayList" %>

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
    <!-- link bootstrap css -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <!-- link css -->
    <link rel="stylesheet" href="css/Categoria.css">

    <!-- link icone -->
    <script src="https://kit.fontawesome.com/cf835fc8f8.js" crossorigin="anonymous"></script>
</head>
<body>

<!-- include l'header -->
<%@ include file= "Header.jsp"%>

<%
    List<Prodotto> prodotti= (List<Prodotto>) request.getAttribute("prodotti");
    String ricerca= (String) request.getAttribute("ricerca");
%>

<!--sezione spazio bianco prima della categoria -->

<div class="container">

    <div class="title" id="intestazioneR" align="center">Risultato ricerca per: <%=ricerca%></div>

    <div class="row row-cols-4">

        <% for(Prodotto p:prodotti){ %>
        <div class="col">

            <div class="card" >
                <a href="show-product?product=<%=p.getId()%>"><img class="card-img-top" src="<%=p.getImmagine()%>" alt="Card image cap"></a>
                <div class="card-body">
                    <h6 class="card-title"><%=p.getNome()%></h6>
                    <p><%=p.getPrezzo()%></p>
                    <a href="carrello?prodId=<%=p.getId()%>&addNum=1" class="buttonAcquista" onclick="myFunction()">Acquista</a>
                </div>
            </div>
        </div>

        <% } %>
    </div>
    <%if(prodotti.isEmpty()){ %>

    <p id="messaggio1" style=" font-size: 15px "align="center"> prodotto/i non presente nel sistema. </p>
    <p id="messaggio2" style=" font-size: 15px " align="center">ci scusiamo per il disagio.</p>

    <% }%>

    <!--sezione spazio bianco prima della categoria -->
    <div class="jumbotron jumbotron-fluid " style="background:transparent !important">
    </div>

    <%@ include file= "Footer.jsp"%>
</div>

</body>
</html>