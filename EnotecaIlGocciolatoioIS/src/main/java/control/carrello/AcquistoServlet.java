package control.carrello;

import model.service.GestoreCarrello;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    Questa Servlet è utilizzata per l'acquisto dei prodotti.
    Viene reindirizzata alla fine col pulsante di acquisto
 */
@WebServlet("/acquisto")
public class AcquistoServlet extends HttpServlet {

    private GestoreCarrello gestoreCarrello = new GestoreCarrello();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        gestoreCarrello.gestoreAcquisto(request, response);
    }
}
