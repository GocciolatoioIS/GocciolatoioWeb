package control;

import bean.Ordine;
import classDAO.OrdineDAO;
import bean.Utente;
import classDAO.UtenteDAO;
import model.GestoreOrdine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/showprivateorders")
public class MostraOrdini extends HttpServlet {

    private  final GestoreOrdine gestoreOrdine = new GestoreOrdine();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        gestoreOrdine.gestoreOrdini(request, response);

    }
}
