package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import bean.Carrello;
import bean.Carrello.ProdottoQuantita;
import classDAO.ProdottoDAO;
import model.GestoreCarrello;
import model.GestoreProdotto;

/*
    Questa Servlet serve per aggiungere i prodotti al carrello
 */

@WebServlet("/carrello")
public class CarrelloServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final ProdottoDAO prodottoDAO = new ProdottoDAO();
    private final GestoreCarrello gestoreCarrello = new GestoreCarrello();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        gestoreCarrello.gestoreAggiungiProdottoCarrello(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}


