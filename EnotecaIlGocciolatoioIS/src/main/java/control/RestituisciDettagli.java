package control;
import bean.Indirizzo;
import bean.ProductOrdered;
import bean.Utente;
import classDAO.*;
import model.GestoreAccount;
import model.GestoreOrdine;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/retrivedetails")
public class RestituisciDettagli extends HttpServlet {

    private final GestoreOrdine gestoreOrdine= new GestoreOrdine();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        gestoreOrdine.gestoreRestituisciDettagliOrdine(request, response);

    }
}