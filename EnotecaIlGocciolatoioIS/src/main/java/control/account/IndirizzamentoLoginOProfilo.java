package control.account;

import model.service.GestoreAccount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/indirizzamento")
public class IndirizzamentoLoginOProfilo extends HttpServlet {

    private final GestoreAccount gestoreAccount=new GestoreAccount();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        gestoreAccount.gestoreReindirizzamentoLoginOProfilo(request,response);

    }
}
