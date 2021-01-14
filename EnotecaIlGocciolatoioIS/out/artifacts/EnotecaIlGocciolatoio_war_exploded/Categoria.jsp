<%@ page import="java.util.List" %>
<%@ page import="bean.Prodotto" %>
<%@ page import="bean.Categoria" %>
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
<%@ include file= "Header.jsp"%>
<!-- include l'header -->
<%
    List<Prodotto> prodotti= (List<Prodotto>) request.getAttribute("prodotti");
    String cat= (String) request.getAttribute("category");
%>

<div class="container">

    <div class="title" align="center"><%=cat%></div>




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

    <!--sezione spazio bianco prima della categoria -->
    <div class="jumbotron jumbotron-fluid " style="background:transparent !important">
    </div>
    <%@ include file= "Footer.jsp"%>
</div>

</body>
</html>