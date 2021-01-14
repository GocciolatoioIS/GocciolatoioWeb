package control;

import bean.*;
import classDAO.*;
import model.GestoreCarrello;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

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
