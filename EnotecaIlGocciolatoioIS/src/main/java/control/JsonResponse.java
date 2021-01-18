package control;

import com.google.gson.Gson;
import model.bean.Prodotto;
import classDAO.ProdottoDAO;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JsonResponse")
public class JsonResponse extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdottoDAO prodao=new ProdottoDAO();
        ArrayList<Prodotto> list= prodao.retriveAll(); //Costruisco lista di tutti i prodotti
        String json= new Gson().toJson(list); //E li confronto con la mia stringa inserita lettera per lettera
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json);
    }
}