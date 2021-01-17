package model;

import bean.*;
import classDAO.IndirizzoDAO;
import classDAO.OrdineDAO;
import classDAO.ProdottoDAO;
import classDAO.ProductOrderedDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class GestoreCarrello {

    private final ProdottoDAO prodottoDAO = new ProdottoDAO();
    private final IndirizzoDAO indirizzoDAO = new IndirizzoDAO();

    public GestoreCarrello() { }


    public void gestoreAggiungiProdottoCarrello(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Retrive del carrello dalla sessione
        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        session.setMaxInactiveInterval(90 * 60); //Lo setto nella sessione per 90 minuti
        Cookie cookie = new Cookie("key", "value");
        cookie.setMaxAge(60); //60 secondi
        response.addCookie(cookie);
        String msg=null;

        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }

        //Retrive dell'id del prodotto. Successivamente possiamo richiedere due parametri:
        String prodIdStr = request.getParameter("prodId");
        if (prodIdStr != null) {
            int prodId = Integer.parseInt(prodIdStr);
            String addNumStr = request.getParameter("addNum");//Per aggiungere un valore ad uno già esistente
            if (addNumStr != null) {
                msg="prodotto aggiunto al carrello";
                int addNum = Integer.parseInt(addNumStr);
                Carrello.ProdottoQuantita prodQuant = carrello.get(prodId);
                if (prodQuant != null) {
                    prodQuant.setQuantita(prodQuant.getQuantita() + addNum); //Se il prodotto non esiste lo aggiungo
                } else {
                    carrello.put(prodottoDAO.retriveOne(prodId), addNum);//Altrimenti lo aggiorno
                }
            } else {
                String setNumStr = request.getParameter("setNum");//Questo parametro setta la quantità
                msg="prodotti aggiunti al carrello";
                if (setNumStr != null) {
                    int setNum = Integer.parseInt(setNumStr);
                    if (setNum <= 0) {
                        carrello.remove(prodId);
                    } else {
                        Carrello.ProdottoQuantita prodQuant = carrello.get(prodId);
                        if (prodQuant != null) {
                            prodQuant.setQuantita(setNum);//Il prodotto lo trova e ne aggiorna la quantità
                        } else {
                            carrello.put(prodottoDAO.retriveOne(prodId), setNum);//Se il prodotto non esiste lo aggiorna con la quantita
                        }
                    }
                }
            }
        }

        System.out.println("Servlet: "+msg);
        request.setAttribute("errorTest",msg);
        session.setAttribute("carrello", carrello);
        String address = "Carrello.jsp";
        response.sendRedirect(address);
    }


    public void gestoreRimuoviProdottoCarrello(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        session.setMaxInactiveInterval(90*60);

        String prodIdStr = request.getParameter("prodId");
        int prodId=Integer.parseInt(prodIdStr);
        carrello.remove(prodId);

        String msg="prodotto rimosso dal carrello";
        System.out.println("Servlet: "+msg);
        request.setAttribute("errorTest",msg);
        session.setAttribute("carrello", carrello);
        String address = "Carrello.jsp";
        response.sendRedirect(address);
    }

    public void gestoreAcquisto(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

        Utente user= (Utente) request.getSession().getAttribute("utente");
        String msg;
        //Controllo per verificare se l'utente è loggato
        if (user == null ) {
            msg="utente non loggato, ritorno alla pagina login";
            System.out.println("Servlet: "+msg);
            request.setAttribute("errorTest",msg);
            String p="Per effettuare un acquisto Registrati oppure effettua il Login:";
            System.out.println(p);
            request.setAttribute("error",p);
            RequestDispatcher view = request.getRequestDispatcher("LoginRegistrazione.jsp");/*dove inoltro il form*/
            view.forward(request,response);
            return;
        }

        //Controllo indirizzo
        if(request.getParameter("address").equals("")){
            msg="l'utente non ha un indirizzo, ritorno alla pagina login";
            System.out.println("Servlet: "+msg);
            request.setAttribute("errorTest",msg);
            RequestDispatcher view = request.getRequestDispatcher("Carrello.jsp");/*dove inoltro il form*/
            HttpSession currentSession = request.getSession();
            request.setAttribute("error", "error");
            view.forward(request,response);
            return;
        }

        //Dao per l'indirizzo
        Indirizzo indirizzoSpedizione= indirizzoDAO.retriveByID(Integer.parseInt(request.getParameter("address")));

        //Creo l'ordine
        Carrello cart = (Carrello) request.getSession().getAttribute("carrello");
        ProductOrderedDao podao=new ProductOrderedDao();
        OrdineDAO ordao=new OrdineDAO();
        ProdottoDAO prodao=new ProdottoDAO();
        Collection<Carrello.ProdottoQuantita> lista=cart.getProdotti();
        Ordine ord=new Ordine();

        ord.setId_utente(user.getId());
        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate= LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        ord.setData_ordine(date);
        int idIndirizzo=ordao.addAddressToOrder(indirizzoSpedizione);

        ordao.doSave(ord,idIndirizzo);//Salvo l'ordine nel DB
        List<Integer> ids= new ArrayList<>();
        //Ora mi creo una istanza del prodotto ordinato
        for(Carrello.ProdottoQuantita p:lista){
            ProductOrdered pO=new ProductOrdered();
            Prodotto prodotto = p.getProdotto();
            pO.setNome(prodotto.getNome());
            pO.setDescrizione(prodotto.getDescrizione());
            pO.setTipo(prodotto.getTipo());
            pO.setPrezzo(prodotto.getPrezzo());
            pO.setSconto(prodotto.getSconto());
            pO.setImmagine(prodotto.getImmagine());
            pO.setAnno(prodotto.getAnno());
            pO.setRegione(prodotto.getRegione());
            pO.setGradazione((int) prodotto.getGradazione());
            pO.setFormato(prodotto.getFormato());
            pO.setQuantita(p.getQuantita());
            pO.setNome_categoria(prodotto.getNome_categoria());

            podao.doSave(pO, ord.getId_ordine()); //Qui salvi il prodotto ordinato
            ids.add(pO.getId());

            prodao.doUpdateQuantity(p.getProdotto().getId(),p.getQuantita()); //Aggiorni le quantità acquistate con le quantità nel magazzino

        }

        //E reindirizzo una pagina statica
        request.getSession().removeAttribute("carrello");
        String address = "/Grazie.jsp";
        msg="acquisto effettuato correttamente";
        request.setAttribute("idOrd",String.valueOf(ord.getId_ordine()));
        request.setAttribute("idAdd",String.valueOf(ord.getId_indirizzo()));
        request.setAttribute("idProdotti",ids);
        System.out.println("Servlet: "+msg);
        request.setAttribute("errorTest",msg);
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }
}
