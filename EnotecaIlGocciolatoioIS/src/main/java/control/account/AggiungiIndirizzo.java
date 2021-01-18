package control.account;

import model.service.GestoreAccount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    Aggiunta dell'indirizzo dell'utente
 */
@WebServlet("/add-address")
public class AggiungiIndirizzo extends HttpServlet {

    private final GestoreAccount gestoreAccount=new GestoreAccount();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        gestoreAccount.gestioneAddIndirizzo(request,response);

    }
}
