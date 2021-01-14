<%@ page import="bean.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- link bootstrap css -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

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

    <div class="row" align="right">
        <div class="col order-last">
            <div align="right">
                <form method="get" action="InformazioniPersonali.jsp">
                    <button class="btn btn-outline-Secondary align-self-xl-end"><i class="fas fa-plus fa-lg"></i><b>Torna all'Area Utente</b></button>
                </form>
            </div>
        </div>
    </div>
</div>

<form action="modifyuser" method="get" onsubmit="return FormValidation();">
    <div class="container">

        <div class="form-group">
            <h1 align="center">Modifica Profilo:</h1>

            <input type="hidden" name="ruolo" value="<%=user.getRuolo()%>">

            <label for="UserInput">MODIFICA USERNAME</label>
            <input type="text" class="form-control" id="UserInput" placeholder="Username" name="username" value="<%=user.getUsername()%>" required>

            <label for="EmailInput">MODIFICA EMAIL</label>
            <input type="email" class="form-control" id="EmailInput" placeholder="name@example.com" name="email" value="<%=user.getEmail()%>"required>


            <label for="nomeInput">MODIFICA NOME</label>
            <input type="text" class="form-control" id="nomeInput" placeholder="Mario" name="nome" value="<%=user.getNome()%>" required>

            <label for="cognomeInput">MODIFICA COGNOME</label>
            <input type="text" class="form-control" id="cognomeInput" placeholder="Rossi" name="cognome" value="<%=user.getCognome()%>" required>


            <input type="hidden"  id="dataInput" name="data" placeholder="Data di Nascita" required value="<%=user.getData_nascita()%>"/>
            <BR>

        </div>

        <button type="submit" class="btn btn-secondary">CONFERMA MODIFICHE</button>

    </div>

</form>


<!-- link jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- >
</html>link bootstrap js -->
<script src="javascript/bootstrap.min.js"></script>
<script src="javascript/FormValidationUser.js"></script>
</body
