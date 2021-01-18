package control.prodotto;

import model.service.GestoreProdotto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/search-product")
public class RicercaServlet extends HttpServlet {

    private final GestoreProdotto gestoreProdotto = new GestoreProdotto();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        gestoreProdotto.gestoreRicercaProdotto(request, response);

    }

}
