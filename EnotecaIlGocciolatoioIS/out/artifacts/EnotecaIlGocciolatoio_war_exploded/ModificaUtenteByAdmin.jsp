<%@ page import="model.bean.Utente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- link bootstrap css -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
<%
    Utente user= (Utente) request.getAttribute("user1");
%>
<!-- include l'header -->
<%@ include file= "HeaderLogo.jsp"%>

<!--sezione spazio bianco prima delle info -->
<div class="jumbotron jumbotron-fluid " style="background:transparent !important">
</div>

<form action="modifyuserbyadmin" method="get">
    <div class="container">

        <div class="form-group">

            <input type="hidden" id="id" name="id" value="<%=user.getId()%>">

            <label for="UserInput">MODIFICA USERNAME</label>
            <input type="text" class="form-control" id="UserInput" placeholder="Username" name="username" value="<%=user.getUsername()%>" required>

            <label for="EmailInput">MODIFICA EMAIL</label>
            <input type="email" class="form-control" id="EmailInput" placeholder="name@example.com" name="email" value="<%=user.getEmail()%>" required>

            <label>MODIFICA IL RUOLO</label>
                <select class="custom-select" id="inputGroupSelect01" name="ruolo" value="<%=user.getRuolo()%>" required>
                    <option value="amministratore">amministratore</option>
                    <option value="utente">utente</option>
                </select>

            <label for="nomeInput">MODIFICA NOME</label>
            <input type="text" class="form-control" id="nomeInput" placeholder="Mario" name="nome" value="<%=user.getNome()%>" required>

            <label for="cognomeInput">MODIFICA COGNOME</label>
            <input type="text" class="form-control" id="cognomeInput" placeholder="Rossi" name="cognome" value="<%=user.getCognome()%>" required>


        </div>

        <button type="submit" class="btn btn-secondary">CONFERMA MODIFICHE</button>

    </div>

</form>

<!-- link jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- >
</html>link bootstrap js -->
<script src="javascript/bootstrap.min.js"></script>
<script src="javascript/account/FormValidationUserAdmin.js"></script>
</body