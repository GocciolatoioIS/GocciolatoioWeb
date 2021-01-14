package control;

import bean.Prodotto;
import classDAO.ProdottoDAO;
import model.GestoreProdotto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/modifyproduct")
public class ModificaProdotto extends HttpServlet {

    private final GestoreProdotto gestoreProdotto= new GestoreProdotto();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        gestoreProdotto.gestoreModificaProdotto(request, response);

    }
}
