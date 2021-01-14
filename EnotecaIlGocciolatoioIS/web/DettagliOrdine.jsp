<%@ page import="bean.ProductOrdered" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Utente" %>
<%@ page import="bean.Indirizzo" %><%--
  Created by IntelliJ IDEA.
  User: vince
  Date: 31/08/2020
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>Dettagli Ordine</title>

    <!-- link bootstrap css -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <link rel="stylesheet" href="css/DettagliOrdine.css">
</head>
<body>

<!-- include l'header -->
<%@ include file= "HeaderLogo.jsp"%>

    <%
        List<ProductOrdered> lista= (List<ProductOrdered>) request.getAttribute("lista");
        Utente user=(Utente) request.getAttribute("user2");
        Indirizzo i= (Indirizzo) request.getAttribute("indirizzo");
    %>

<!--sezione spazio bianco prima delle info -->
<div class="jumbotron jumbotron-fluid " style="background:transparent !important">
</div>

<div class="container" >
    <h3> Dettagli del tuo ordine </h3>
    <br>

    <div class="row justify-content-start">

        <div class="row ">


            <div class="col">
                <table>
                    <thead>
                    <tr>
                        <th>Indirizzo di Spedizione:</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>città: <%=i.getCitta()%></td>
                    </tr>
                    <tr>
                        <td>cap: <%=i.getCap()%></td>

                    </tr>
                    <tr>
                        <td>via: <%=i.getVia()%></td>
                    </tr>
                    <tr>
                        <td>numero civico: <%=i.getNumCivico()%></td>
                    </tr>
                    </tbody>
                </table>
            </div>


            <div class="col">
                <table>
                    <thead>
                        <th> <h3> Lista Prodotto/i: </h3> </th>
                    </thead>
                <% for(ProductOrdered po:lista){ %>

                    <thead>
                    <tr>
                        <th> <%=po.getNome()%></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Prezzo: <%=po.getPrezzo()%></td>
                    </tr>
                    <td>Quantità: <%=po.getQuantita()%></td>
                    </tr>
                    </tbody>

                <%}%>
                </table>
            </div>

        </div>

    </div>
</div>
</html>