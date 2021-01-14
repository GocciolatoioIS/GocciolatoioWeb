<%--
  Created by IntelliJ IDEA.
  User: vince
  Date: 10/05/2020
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>Enoteca Il Gocciolatoio</title>
    <!-- link bootstrap css -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/HomePageStyle.css">

    <!-- link icone -->
    <script src="https://kit.fontawesome.com/cf835fc8f8.js" crossorigin="anonymous"></script>
</head>
<body>
<!-- utilizzando class = "container" si raggrupa il contenuto al centro dello schermo limitando la grandezza massima -->
<div class="navbar-fixed-top container">
    <nav class="navbar navbar-light navbar-expand-lg " >
        <a class="navbar-brand" href="index.html">
            <img src="./img/logo.png" width="450" height="128" alt="">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCategorie" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
            <i class="fas fa-angle-down"></i>
        </button>

        <div class="collapse navbar-collapse" id="collapseExample">

            <form class="form-inline my-2 my-lg-0 " action="search-product">
                <input class="form-control form-control-sm mr-sm-2" type="search" placeholder="Cosa stai cercando?" aria-label="Search" id="navBarSearchForm" name="stringP" onkeyup="ricerca(this.value)">
                <button class="btn btn-light btn-sm my-2 my-sm-0" type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
            </form>

            <ul class="navbar-nav ">

                <li class="nav-item ">
                    <a class="nav-link"  href="indirizzamento"><i class="fas fa-user fa-lg" ></i> </a>

                </li>
                <li class="nav-item">
                    <a class="nav-link" href="Carrello.jsp" ><i class="fas fa-shopping-cart  fa-lg"></i></a>

                </li>
            </ul>

        </div>

    </nav>

    <nav class="navbar navbar-expand-lg navbar-light ">

        <div class="collapse navbar-collapse" id ="navbarCategorie" >
            <ul class="navbar-nav">

                <!-- href indirizza alla servlet, aggiungendo come parametro category a cui è assegnata la categoria corrispondente -->
                <li class="nav-item">
                    <a class="nav-link" href="show-category?category=vini">VINI  <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"  href="show-category?category=spumanti">SPUMANTI</a>

                </li>
                <li class="nav-item">
                    <a class="nav-link" href="show-category?category=birre">BIRRE</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="show-category?category=champagne">CHAMPAGNE</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="show-category?category=superalcolici">SUPERALCOLICI</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="show-category?category=amari">AMARI</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="show-category?category=cibo">CIBO</a>
                </li>

            </ul>
        </div>
    </nav>

    <div id="search-layer"></div>

</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script src="javascript/preSearch.js"></script>

</body>
</html>