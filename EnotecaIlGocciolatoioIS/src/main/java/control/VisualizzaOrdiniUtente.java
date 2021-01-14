package control;
import bean.Ordine;
import classDAO.OrdineDAO;
import bean.Utente;
import model.GestoreOrdine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/retriveorderuser")
public class VisualizzaOrdiniUtente extends HttpServlet {

    private final GestoreOrdine gestoreOrdine = new GestoreOrdine();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        gestoreOrdine.gestoreRestituisciOrdiniUtenti(request, response);
    }
}