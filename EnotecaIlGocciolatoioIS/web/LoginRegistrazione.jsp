<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: vince
  Date: 10/05/2020
  Time: 21:37
  To change this template use File | Settings | File Templates.
  action="add-product" method="get"
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Enoteca il Gocciolatoio</title>

    <!-- Link css -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/LoginRegistration.css">

</head>

<body onload="document.registration.username.focus();">

<!-- include l'header -->
<%@ include file= "HeaderLogo.jsp"%>

<!--sezione spazio bianco prima del form -->
<div class="jumbotron jumbotron-fluid " style="background:transparent !important">
</div>

<!-- partial:index.partial.html -->
<h2> Accedi o Crea un Account </h2>
<%
    String error= (String) request.getAttribute("error");
    if(error!=null){
%>
        <span id="checkError" style="color:red" align="center" value="<%=error%>" > *<%=error%> </span>
<%}%>
<div class="container" id="container">
    <div class="form-container sign-up-container">
        <form name="registration" action="register" method="Post" onsubmit="FormValidation()">
            <h1>Registrati</h1>
            <input type="text" id="nome" name="nome"placeholder="Nome" required/>
            <input type="text" id="cognome" name="cognome"placeholder="Cognome" required/>
            <input type="date"  id="data"name="data" placeholder="Data di Nascita" required/>
            <input type="text" id="usernameReg" name="username"placeholder="Name" />
            <input type="email" id="email" name="email"placeholder="Email" required/>
            <input type="password" id="passwordReg" name="pass" placeholder="Password" required/>
            <button id="BottoneRegistrati">Registrati </button>
        </form>
    </div>
    <div class="form-container sign-in-container">
        <form action="login" method="POST" onsubmit="validaFormLogin()">
            <h1>Accedi</h1>

            <input type="text" id="username" name="username"placeholder="Username" />
            <input type="password" id="password" name="pass" placeholder="Password"  />

            <br>

            <button id="BottoneAccedi">Accedi</button>
        </form>
    </div>
    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1>Bentornato!</h1>
                <p>Per acquistare i nostri prodotti, effettua il login con le tue informazioni personali.</p>
                <button class="ghost" id="signIn">Accedi</button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1>Ciao!</h1>
                <p>Inserisci i tuoi dati personali e scopri i nostri prodotti!</p>
                <button class="ghost" id="signUp">Iscriviti</button>
            </div>
        </div>
    </div>
</div>

<!-- js -->
<script src="javascript/LoginRegistration.js"></script>
<script src="javascript/FormValidation.js"></script>
<script src="javascript/LoginValidation.js"></script>

</body>
</html>