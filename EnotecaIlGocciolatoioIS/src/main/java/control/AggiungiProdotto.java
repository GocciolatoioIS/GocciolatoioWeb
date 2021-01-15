package control;

import bean.Prodotto;
import classDAO.ProdottoDAO;
import model.GestoreProdotto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/*
    Serve per aggiungere prodotti al DB
 */
@WebServlet("/add-product")
@MultipartConfig
public class AggiungiProdotto extends HttpServlet {

    private final GestoreProdotto gestoreProdotto= new GestoreProdotto();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        gestoreProdotto.gestoreAddProdotto(request,response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
