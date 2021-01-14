package control;

import bean.Indirizzo;
import bean.Utente;
import classDAO.*;
import model.GestoreAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class Login extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final GestoreAccount gestoreAccount = new GestoreAccount();


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        gestoreAccount.gestoreLogin(request, response);
    }

}

