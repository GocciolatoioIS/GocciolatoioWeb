<%@ page import="model.bean.Prodotto" %><%--
  Created by IntelliJ IDEA.
  User: vince
  Date: 17/07/2020
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- link bootstrap css -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">

</head>
<body>

<!-- include l'header -->
<%@ include file= "../HeaderLogo.jsp"%>

<%
    Prodotto prod= (Prodotto) request.getAttribute("prodotto1");
%>

<!--sezione spazio bianco prima delle info -->
<div class="jumbotron jumbotron-fluid " style="background:transparent !important">
</div>

<form class="container center_div" action="modifyproduct" method="get" onsubmit="return FormValidation();" enctype="multipart/form-data">
    <div class="row">
        <div class="col order-first">

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Categoria</label>
                    <input type="hidden" id="id" name="id" value="<%=prod.getId()%>">
                </div>
                <select id="categoria" class="custom-select" id="inputGroupSelect01" name="nome_categoria" value="<%=prod.getNome_categoria()%>" required>
                    <option selected>Seleziona una categoria...</option>
                    <option value="Vino">Vino</option>
                    <option value="Spumanti">Spumanti</option>
                    <option value="Birra">Birra</option>
                    <option value="Champagne">Champagne</option>
                    <option value="Superalcolici">Superalcolici</option>
                    <option value="Amari">Amari</option>
                    <option value="Cibo">Cibo</option>
                    <option value="Confetture">Confetture</option>
                </select>
            </div>

            <div class="form-group">
                <label class=control-label" for="NomeProdotto">NOME PRODOTTO</label>
                <div >
                    <input type="text" class="form-control" id="NomeProdotto" placeholder="Inserisci nome prodotto" name="nome" value="<%=prod.getNome()%>" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label" for="Descrizione" required>DESCRIZIONE</label>
                <div>
                    <textarea class="form-control" id="Descrizione" name="descrizione" value="<%=prod.getDescrizione()%>" required> </textarea>
                </div>
            </div>


            <div class="form-group">
                <label class="control-label" for="Annata">ANNATA</label>
                <div >
                    <input type="text" class="form-control" id="Annata" placeholder="Inserisci Annata" name="anno" value="<%=prod.getAnno()%>" required>
                </div>
            </div>


            <div class="form-group">
                <label class="control-label" for="Regione">REGIONE </label>
                <div >
                    <input type="text" class="form-control" id="Regione" placeholder="Inserisci Regione" name="regione" value="<%=prod.getRegione()%>" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label" for="Gradazione" required>GRADAZIONE </label>
                <div >
                    <input type="text" class="form-control" id="Gradazione" placeholder="Inserisci Gradazione" name="gradazione" value="<%=(int)(prod.getGradazione())%>" required>
                </div>
            </div>





        </div>

        <div class="col order-last">

            <div class="form-group">
                <label class="control-label" for="Formato">FORMATO </label>
                <div>
                    <input type="text" class="form-control" id="Formato" placeholder="Inserisci Formato" name="formato" value="<%=prod.getFormato()%>" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label" for="Disponibilità">DISPONIBILITÀ </label>
                <div >
                    <input type="text" class="form-control" id="Disponibilità" placeholder="Inserisci Disponibilità" name="quantita_magazzino" value="<%=prod.getQuantita_magazzino()%>" required>
                </div>
            </div>


            <div class="form-group">
                <label class="control-label" for="Tipo">TIPO </label>
                <div >
                    <input type="text" class="form-control" id="Tipo" placeholder="Bianco Rosso Rosato" name="tipo" value="<%=prod.getTipo()%>" >
                </div>
            </div>

            <div class="form-group">
                <label class="control-label" for="Prezzo">PREZZO </label>
                <div >
                    <input type="text" class="form-control" id="Prezzo" placeholder="Inserisci prezzo" name="prezzo" value="<%=(int)prod.getPrezzo()%>" required>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label" for="Sconto">SCONTO</label>
                <div >
                    <input type="text" class="form-control" id="Sconto" placeholder="Inserisci Sconto" name="sconto" value="<%=(int)prod.getSconto()%>">
                </div>
            </div>


                <div class="form-group">
                    <label for="fileupload">Scegli un'immagine</label>
                    <input type="file" class="form-control-file" id="fileupload" name="immagine" required>
                </div>


            <!-- Bottone di conferma -->
            <div class="form-group">
                <div >
                    <button id="aggiungi" class="btn btn-secondary">MODIFICA PRODOTTO</button>
                </div>
            </div>
        </div>
    </div>
</form>

<!-- link jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- link bootstrap js -->
<script src="../javascript/bootstrap.min.js"></script>
<script src="../javascript/prodotto/FormValidationProductAdmin.js"></script>
</body>


</html>