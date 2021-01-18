package control.ordine;
import model.service.GestoreOrdine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/retriveorders")
public class RestituisciOrdini extends HttpServlet {

    private final GestoreOrdine gestoreOrdine = new GestoreOrdine();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         gestoreOrdine.gestoreRestituisciOrdini(request, response);

    }
}
