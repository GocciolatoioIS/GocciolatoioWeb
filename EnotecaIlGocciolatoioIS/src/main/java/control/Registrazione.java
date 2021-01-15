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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/register")
public class Registrazione extends HttpServlet {

    public final GestoreAccount gestoreAccount=new GestoreAccount();

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            gestoreAccount.gestioneRegistrazione(request,response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
