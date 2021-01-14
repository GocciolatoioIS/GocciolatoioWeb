package control;

import classDAO.ProdottoDAO;
import model.GestoreProdotto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteproduct")
public class CancellaProdottoByIDServlet extends HttpServlet {

    private final GestoreProdotto gestoreProdotto = new GestoreProdotto();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        gestoreProdotto.gestoreCancellaProdotto(request,response);

    }
}