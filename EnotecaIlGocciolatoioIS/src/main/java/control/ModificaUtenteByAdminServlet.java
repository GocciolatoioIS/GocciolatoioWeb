package control;

import bean.Utente;
import classDAO.UtenteDAO;
import model.GestoreAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/modifyuserbyadmin")
public class ModificaUtenteByAdminServlet extends HttpServlet {

    private final GestoreAccount gestoreAccount = new GestoreAccount();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            gestoreAccount.gestoreModificaUtenteByAdmin(request, response);

    }
}
