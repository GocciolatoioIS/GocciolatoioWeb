package control.account;

import model.service.GestoreAccount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

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

