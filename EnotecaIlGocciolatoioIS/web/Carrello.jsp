<%@ page import="bean.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Utente" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Indirizzo" %>
<%@ page import="bean.Carrello" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="Header.jsp">
    <jsp:param name="pageTitle" value="Carrello"/>
</jsp:include>

<html>
<head>
    <title>Carrello </title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
    <link rel="stylesheet" href="css/CarrelloStyle1.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<% Carrello carrello= (Carrello) session.getAttribute("carrello");
    DecimalFormat df = new DecimalFormat("#.00");
    df.setMaximumFractionDigits(2);

%>
<div class="container mt-5 mb-5">
    <div class="row justify-content-center">
        <div class="col-xl-7 col-lg-8 col-md-7">
            <div class="border border-gainsboro p-3">
                <h2 id="title" class="h6 text-uppercase mb-0">CARRELLO</h2>
            </div>

            <c:forEach items="${carrello.prodotti}" var="prod">
                <div class="border border-gainsboro p-3 mt-3 clearfix item">
                    <div class="col-md-3">
                        <img class="img-fluid mx-auto d-block image" src="${prod.prodotto.immagine}">
                    </div>
                    <div class="col-lg-5 col-5 text-lg-left">
                        <h3 class="h6 mb-0"><a class="link" href="show-product?product=${prod.prodotto.id}">${prod.prodotto.nome}</a> <br>
                            <small type="number">€${prod.prodotto.prezzo}</small>
                        </h3>
                    </div>
                    <div class="product-price d-none">${prod.prodotto.prezzo}</div>
                    <div class="pass-quantity col-lg-3 col-md-4 col-sm-3">
                            <%--@declare id="pass-quantity"--%><label for="pass-quantity" class="pass-quantity">QUANTITÀ:</label>
                        <input type="hidden" id="idProdotto" name="prodId" value=${prod.prodotto.id}>
                        <input class="form-control" type="number" id="quantProd" name="setNum" value=${prod.quantita} min="1" max="${prod.prodotto.quantita_magazzino}" onfocusout="save(${prod.prodotto.id},this.value)">
                    </div>
                    <div class="col-lg-2 col-md-1 col-sm-2 product-line-price pt-4">
          <span class="product-line-price" type="number" value=${prod.prodotto.prezzo}>${prod.prodotto.prezzo*prod.quantita}
          </span>
                    </div>
                    <div class="remove-item pt-4">
                        <a id="rimuovi" href="remove?prodId=${prod.prodotto.id}">Rimuovi</a>
                    </div>
                </div>

            </c:forEach>
            <c:if test="${empty carrello.prodotti}">
                <br>
                <p id="np" align="center">Nessun prodotto nel carrello</p>
                <br>
            </c:if>
        </div>
<form action="acquisto">
        <div class=" totals">
            <c:if test="${not empty carrello.prodotti}">
                <%
                    Utente u= (Utente) request.getSession().getAttribute("utente");
                    if(u != null){
                %>
                <!-- Metodo di Pagamentp  -->
                <div class="border border-gainsboro px-3">
                    <div class="border-gainsboro">
                        <p class="text-uppercase mb-0 py-3"><strong>Pagamento tramite contrasegno:</strong></p>
                    </div>
                </div>

                <br>

                <!-- Indirizzo -->
                <div class="border border-gainsboro px-3">
                    <div class="border-bottom border-gainsboro">
                        <p class="text-uppercase mb-0 py-3"><strong>Indirizzo Recapito:</strong></p>
                    </div>
                    <BR>
                    <%   String error= (String) request.getAttribute("error");
                        if(error!=null){
                            if(error.equals("error")){
                    %>

                    <p style="color:red" align="center">*Non hai selezionato alcun indirizzo, ordine fallito </p>
                    <%}}%>
                                <select id="Address" name="address" class="input-xlarge">
                                <option value="" selected="selected">(seleziona un' indirizzo)</option>
                            <%

                            List<Indirizzo> listInd=u.getIndirizzoList();
                            if(listInd!=null){
                            for(int i=0;i<listInd.size();i++){
                                Indirizzo ind=listInd.get(i);
                            %>
                                <option value="<%=ind.getId()%>"><%=ind.getFullIndirizzo()%></option>
                        <%}}%>
                    </select>
                    <div class="totals-item totals-item-total d-flex align-items-center justify-content-between mt-3 pt-3 border-top border-gainsboro">
                        <p class="text-uppercase"><strong>Aggiungi nuovo indirizzo</strong></p>
                        <p><button><i class="fa fa-plus" aria-hidden="true"></i><a class="linkA" href="AggiungiIndirizzo.jsp">Inserisci Indirizzo</a> </button></p>
                    </div>
                </div>
                <%}%>
                <br>

                <!-- Riepilogo-->
            <div class="border border-gainsboro px-3">
                <div class="border-bottom border-gainsboro">
                    <p class="text-uppercase mb-0 py-3"><strong>Riepilogo </strong></p>
                </div>
                <div class="totals-item d-flex align-items-center justify-content-between mt-3">
                    <p class="text-uppercase">Totale Parziale</p>
                    <p class="totals-value" id="cart-subtotal">€${carrello.prezzoTotCent}</p>
                </div>
                <div class="totals-item d-flex align-items-center justify-content-between">
                    <p class="text-uppercase">Spese di Spedizione</p>
                    <p class="totals-value" id="cart-tax">€7.90</p>
                </div>
                <div class="totals-item totals-item-total d-flex align-items-center justify-content-between mt-3 pt-3 border-top border-gainsboro">
                    <p class="text-uppercase"><strong>Totale</strong></p>
                    <p class="totals-value font-weight-bold cart-total">€<%=df.format(carrello.getPrezzoTotCent()+7.90)%></p>

                </div>
            </c:if>
            <c:if test="${not empty carrello.prodotti}">

            </div>
                <input class="mt-3 btn btn-pay w-100 d-flex justify-content-between btn-lg rounded-0" type="submit" value="Paga Ora">
            </c:if>


        </div>
    </form>
    </div>

</div><!-- container -->

</body>
<script src="javascript/Cart1.js"></script>
<%@ include file= "Footer.jsp"%>
<script src="javascript/AggiornaQuantitaCarrello.js"></script>
</body>
</html>