package model;

import bean.Indirizzo;
import bean.Ordine;
import bean.ProductOrdered;
import bean.Utente;
import classDAO.IndirizzoDAO;
import classDAO.OrdineDAO;
import classDAO.ProductOrderedDao;
import classDAO.UtenteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestoreOrdine {

    private final IndirizzoDAO indirizzoDAO=new IndirizzoDAO();

    public GestoreOrdine() {
    }

    public void gestoreCancellaOrdine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String param=request.getParameter("id");
        int id=Integer.parseInt(param);

        OrdineDAO udao=new OrdineDAO();
        udao.deleteOrder(id);

        String address="retriveorders";

        request.setAttribute("var","Cancellazione eseguita con Successo :)");
        response.sendRedirect(address);


    }

    public void gestoreOrdini(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrdineDAO orderDAO=new OrdineDAO();

        Utente user= (Utente) request.getSession().getAttribute("utente");

        ArrayList<Ordine> lista=orderDAO.retriveByIdUser(user.getId());

        request.setAttribute("lista", lista);

        String address = "/ListaOrdini.jsp";
        request.setAttribute("errorTest", "TuttiGliOrdiniUtente");
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    public void gestoreRestituisciDettagliOrdine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductOrderedDao podao=new ProductOrderedDao();
        UtenteDAO userdao=new UtenteDAO();

        String idOrdineString=request.getParameter("idord");
        int ido=Integer.parseInt(idOrdineString);

        String idUserString=request.getParameter("iduser");
        int idu=Integer.parseInt(idUserString);

        String idIndirizzo=request.getParameter("idIndirizzo");
       // System.out.println("indirizzo:"+idIndirizzo);
        int idI=Integer.parseInt(idIndirizzo);

        Utente u=userdao.retriveById(idu);
        request.setAttribute("user2",u);

        Indirizzo i= indirizzoDAO.retriveIndirizzoOrdineByID(idI);
        //System.out.println(i.toString());
        request.setAttribute("indirizzo",i);

        List<ProductOrdered> lista=podao.retriveByOrderId(ido);
        request.setAttribute("lista", lista);

        request.setAttribute("errorTest","dettaglioOrdineSuccesso");

        String address = "/DettagliOrdine.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }

    public void gestoreRestituisciOrdini(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        OrdineDAO orderDAO=new OrdineDAO();
        UtenteDAO userDAO=new UtenteDAO();

        ArrayList<Ordine> lista=orderDAO.retriveAll();

        request.setAttribute("lista", lista);
        System.out.println("ho preso tutti i dati");
        String address = "/ListaOrdiniAdmin.jsp";
        request.setAttribute("errorTest", "OrdiniDITuttiIClienti");
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);

    }


    public void gestoreRestituisciOrdiniUtenti(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrdineDAO orderDAO=new OrdineDAO();
        Utente u= (Utente) request.getSession().getAttribute("utente");
        ArrayList<Ordine> lista=orderDAO.retriveByIdUser(u.getId());

        request.setAttribute("lista", lista);

        String address = "/ListaOrdini.jsp";
        request.setAttribute("errorTest", "TuttiGliOrdiniUtente");
        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

}
