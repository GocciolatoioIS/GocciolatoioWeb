<%--
  Created by IntelliJ IDEA.
  User: vince
  Date: 10/05/2020
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Footer</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- link Icone -->
    <script src="https://kit.fontawesome.com/cf835fc8f8.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/footer.css">
</head>
<body>
<!-- Inizio Footer -->

<div class="footer">
    <div class="inner-footer">


        <!-- Prima lista link footer -->
        <div class="footer-items">
            <h4>Il Gocciolatoio</h4>
            <div class="border1"></div> <!--for the underline -->
            <ul>
                <a href="#"><li>Home</li></a>


                <a href="#"><li>Chi siamo</li></a>
            </ul>
        </div>

        <!--  Lista link prodotti   -->
        <div class="footer-items">
            <h4>Prodotti</h4>
            <div class="border1"></div>  <!--for the underline -->
            <ul>
                <a href="show-category?category=vini#"><li>Vini</li></a>
                <a href="show-category?category=spumanti#"><li>Spumanti</li></a>
                <a href="#"><li>Liquori</li></a>



            </ul>
        </div>

        <!-- Contatti -->
        <div class="footer-items">
            <h4>Contattaci</h4>
            <div class="border1"></div>
            <ul>
                <li><i class="fas fa-map-marked-alt"></i>Via Turati, 20, Somma Vesuviana</li>
                <br>
                <li><i class="fa fa-phone" aria-hidden="true"></i>331 338 6476</li>
                <li><i class="fa fa-envelope" aria-hidden="true"></i>email@gmail.com</li>
                <a href="Contattaci.jsp"><li>Maggiori Dettagli</li></a>
            </ul>

            <!-- Social -->
            <div class="social-media">
                <a href="https://www.instagram.com/explore/locations/321339394652078/enoteca-il-gocciolatoio/"><i class="fab fa-instagram"></i></a>
                <a href="https://www.facebook.com/IlGocciolatoioEnotecaGourmet"><i class="fab fa-facebook"></i></a>
            </div>
        </div>
    </div>

    <!--  Inzio Sezione copyright   -->
    <div class="footer-bottom">
        Copyright &copy; EnotecaIlGocciolatoio 2020.
    </div>
</div>

<!-- Fine sezione copyright -->

<!-- Fine Footer -->
</body>
</html>