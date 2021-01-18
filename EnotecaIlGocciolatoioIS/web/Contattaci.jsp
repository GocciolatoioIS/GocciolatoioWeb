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
    <link rel="stylesheet" href="css/Contatti.css">
    <!-- link icone -->
    <script src="https://kit.fontawesome.com/cf835fc8f8.js" crossorigin="anonymous"></script>
    <script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>


</head>
<body>
<%@ include file= "Header.jsp"%>

<div class="titolo-pagina-top container">
    <h1 align="center"><b>I nostri contatti</b></h1>
</div>

<!-- MAIN -->
<div id="main" role="main" class="container">



    <div class="row">

        <div class="col-md-6">

        </div>
    </div>

    <div class="row">
        <div id="contact-info" class="col-md-6">
            <div id="contact-info-inner">
                <h2><i class="fa fa-info"></i> Info</h2>
                <h3>Per informazioni, ordini e preventivi potete contattarci telefonicamente.<br>
                    Altrimenti scriveteci un messaggio su Whatsapp.<br>
                    Sarete ricontattati al più presto.<br>
                    Grazie!</h3>
                <p>&nbsp;</p>
                <p><i class="fa fa-map-marker"></i> <strong>Via Turati, 20, Somma Vesuviana</strong></p>
                <div id="mappa"></div>
                <div class="wp-block-uagb-google-map uagb-google-map_wrap uagb-block-ba0917dc"><iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3016.9617879411144!2d14.43726431527265!3d40.87271277931471!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x133bafbc910f8e27%3A0x41129925962637b0!2sEnoteca%20Il%20Gocciolatoio!5e0!3m2!1sit!2sit!4v1604865698786!5m2!1sit!2sit" width="100%" height="450px" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe></div>
                <p><i class="fa fa-phone"></i> <strong>Tel 331 338 6476</strong></p>
                <p>&nbsp;</p>
                <p><strong><i class="fa fa-at"></i> EnotecaIlGocciolatoio@gmail.com</strong></p>
                <p><strong><i class="fa fa-facebook"></i> <a href="https://www.facebook.com/IlGocciolatoioEnotecaGourmet" target="_blank">facebook.com/Enoteca</a></strong></p>
                <p><a href="https://www.instagram.com/enoteca_il_gocciolatoio/" target="_blank">www.instagram.com/enotecailGocciolatoio/</a></p>

            </div>
        </div>
        <div id="contact-form" class="col-md-6">
            <div id="contact-form-inner">
                <h3><i class="fa fa-pencil"></i> Scrivi un messaggio a Enoteca Il Gocciolatoio</h3>
                <div class="wpcf7" id="wpcf7-f75-o1" dir="ltr" lang="it-IT">
                    <div class="screen-reader-response"></div>
                    <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
                        <lottie-player src="https://assets6.lottiefiles.com/private_files/lf30_R3HiEj.json" background="transparent" speed="1" style="width: 300px; height: 300px;" loop autoplay></lottie-player>
                        <a href="https://web.whatsapp.com/" id="whatsapp">Contattaci su whatsapp!</a>
                </div>
            </div>
        </div>
    </div>

</div>
<!-- /MAIN -->

<!-- FOOTER -->
<footer id="footer">

    </div>


</footer>
<!-- /FOOTER -->

