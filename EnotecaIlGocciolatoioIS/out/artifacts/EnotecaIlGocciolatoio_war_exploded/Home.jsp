<%@ page import="model.bean.Categoria" %>
<%@ page import="model.bean.Prodotto" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: vince
  Date: 10/05/2020
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- include l'header -->
<%@ include file= "Header.jsp"%>
<html>
<head>
    <title>EnotecaIlGocciolatoio </title>
    <!-- link bootstrap css -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/HomePageStyle.css">

    <!-- link icone -->
    <script src="https://kit.fontawesome.com/cf835fc8f8.js" crossorigin="anonymous"></script>
</head>
<body>

<%
    ServletContext ctx=request.getServletContext();
    Prodotto p1,p2,p3,p4,p5,p6,p7,p8;
    p1= (Prodotto) ctx.getAttribute("p1");
    p2= (Prodotto) ctx.getAttribute("p2");
    p3= (Prodotto) ctx.getAttribute("p3");
    p4= (Prodotto) ctx.getAttribute("p4");
    p5= (Prodotto) ctx.getAttribute("p5");
    p6= (Prodotto) ctx.getAttribute("p6");
    p7= (Prodotto) ctx.getAttribute("p7");
    p8= (Prodotto) ctx.getAttribute("p8");
%>

<!-- slider -->
<div class="container">
    <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
            <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="img/Slideshow1.png" class="d-block w-100" alt="">
                <div class="carousel-caption d-none d-md-block">
                    <h5>I NOSTRI VINI</h5>
                    <p>Presso la nostra enoteca trovate una vastissima scelta di etichette pregiate di vini e spumanti italiani ed esteri, tutti selezionati con grande cura. </p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="img/Slideshow2.png" class="d-block w-100" alt="">
                <div class="carousel-caption d-none d-md-block">
                    <h5>CONFEZIONI E REGALI</h5>
                    <p>Dovete fare un regalo ad un parente o un amico o ad un professionista per ringraziarlo? Avete un’azienda e non vi viene un’idea per i regali aziendali?
                        Da noi troverete sicuramente qualcosa di buono che fa al caso vostro. Creiamo confezioni di tutti i tipi, cassette di cartone o legno e cesti.</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="img/Slideshow3.png" class="d-block w-100" alt="">
                <div class="carousel-caption d-none d-md-block">
                    <h5>SPUMANTI E CHAMPAGNE</h5>
                    <p>Oltre ai vini bianchi, rossi, rosati, spumanti, passiti e liquorosi, provenienti dalle aziende vitivinicole più famose e da quelle più di nicchia, all’Enoteca Il Gocciolatoio trovate anche un ricercato assortimento di birre artigianali. </p>
                </div>
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
<!-- fine slider -->

<!--sezione spazio bianco prima del footer-->
<div class="jumbotron jumbotron-fluid " style="background:transparent !important">
</div>

<!-- prodotti random -->
<div class="container">
    <div class="card-deck">
        <div class="card" >
            <a href="show-product?product=<%=p1.getId()%>"><img class="card-img-top" src="<%=p1.getImmagine()%>" alt="Card image cap"></a>
            <div class="card-body">
                <h6 class="card-title"><%=p1.getNome()%></h6>
                <p><%=p1.getPrezzo()%>€</p>
                <a href="carrello?prodId=<%=p1.getId()%>&addNum=1" class="buttonAcquista" onclick="myFunction()">Acquista</a>
            </div>
        </div>
        <div class="card" >
            <a href="show-product?product=<%=p2.getId()%>"><img class="card-img-top" src="<%=p2.getImmagine()%>" alt="Card image cap" ></a>
            <div class="card-body">
                <h6 class="card-title"><%=p2.getNome()%></h6>
                <p><%=p2.getPrezzo()%>€</p>
                <a href="carrello?prodId=<%=p2.getId()%>&addNum=1" class="buttonAcquista" onclick="myFunction()">Acquista</a>
            </div>
        </div>
        <div class="card" >
            <a href="show-product?product=<%=p3.getId()%>"><img class="card-img-top" src="<%=p3.getImmagine()%>" alt="Card image cap" ></a>
            <div class="card-body">
                <h6 class="card-title"><%=p3.getNome()%></h6>
                <p><%=p3.getPrezzo()%>€</p>
                <a href="carrello?prodId=<%=p3.getId()%>&addNum=1" class="buttonAcquista" onclick="myFunction()">Acquista</a>
            </div>
        </div>
        <div class="card" >
            <a href="show-product?product=<%=p4.getId()%>"><img class="card-img-top" src="<%=p4.getImmagine()%>" alt="Card image cap" ></a>
            <div class="card-body">
                <h6 class="card-title"><%=p4.getNome()%></h6>
                <p><%=p4.getPrezzo()%>€</p>
                <a href="carrello?prodId=<%=p4.getId()%>&addNum=1" class="buttonAcquista" onclick="myFunction()">Acquista</a>
            </div>
        </div>
    </div>

    <br>

    <div class="card-deck">
        <div class="card" >
            <a href="show-product?product=<%=p5.getId()%>"><img class="card-img-top" src="<%=p5.getImmagine()%>" alt="Card image cap" ></a>
            <div class="card-body">
                <h6 class="card-title"><%=p5.getNome()%></h6>
                <p><%=p5.getPrezzo()%>€</p>
                <a href="carrello?prodId=<%=p5.getId()%>&addNum=1" class="buttonAcquista" onclick="myFunction()">Acquista</a>
            </div>
        </div>
        <div class="card" >
            <a href="show-product?product=<%=p6.getId()%>"><img class="card-img-top" src="<%=p6.getImmagine()%>" alt="Card image cap" ></a>
            <div class="card-body">
                <h6 class="card-title"><%=p6.getNome()%></h6>
                <p><%=p6.getPrezzo()%>€</p>
                <a href="carrello?prodId=<%=p6.getId()%>&addNum=1" class="buttonAcquista" onclick="myFunction()">Acquista</a>
            </div>
        </div>
        <div class="card" >
            <a href="show-product?product=<%=p7.getId()%>"><img class="card-img-top" src="<%=p7.getImmagine()%>" alt="Card image cap" ></a>
            <div class="card-body">
                <h6 class="card-title"><%=p7.getNome()%></h6>
                <p><%=p7.getPrezzo()%>€</p>
                <a href="carrello?prodId=<%=p7.getId()%>&addNum=1" class="buttonAcquista" onclick="myFunction()">Acquista</a>
            </div>
        </div>
        <div class="card" >
            <a href="show-product?product=<%=p8.getId()%>"><img class="card-img-top" src="<%=p8.getImmagine()%>" alt="Card image cap" ></a>
            <div class="card-body">
                <h6 class="card-title"><%=p8.getNome()%></h6>
                <p><%=p8.getPrezzo()%>€</p>
                <a href="carrello?prodId=<%=p8.getId()%>&addNum=1" class="buttonAcquista" onclick="myFunction()">Acquista</a>
            </div>
        </div>
    </div>

</div>
<!-- fine prodotti random-->

<!--sezione spazio bianco prima del footer-->
<div class="jumbotron jumbotron-fluid " style="background:transparent !important">
</div>

<!-- include il footer -->
<%@ include file= "Footer.jsp"%>
</div>



<!-- link jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- link bootstrap js -->
<script src="javascript/bootstrap.min.js"></script>
</body>
</html>