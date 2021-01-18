package control.carrello;


import model.service.GestoreCarrello;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/remove")
public class RimuoviDalCarrello extends HttpServlet {

    public final GestoreCarrello gestoreCarrello = new GestoreCarrello();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        gestoreCarrello.gestoreRimuoviProdottoCarrello(request, response);

    }
}


