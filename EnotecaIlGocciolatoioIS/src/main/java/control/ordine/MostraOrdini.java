package control.ordine;

import model.service.GestoreOrdine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showprivateorders")
public class MostraOrdini extends HttpServlet {

    private  final GestoreOrdine gestoreOrdine = new GestoreOrdine();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        gestoreOrdine.gestoreOrdini(request, response);

    }
}
