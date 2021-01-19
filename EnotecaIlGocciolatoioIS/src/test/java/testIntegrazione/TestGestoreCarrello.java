package testIntegrazione;

import static org.junit.jupiter.api.Assertions.*;

import model.bean.*;
import classDAO.ProductOrderedDao;
import classDAO.UtenteDAO;
import classDAO.IndirizzoDAO;
import classDAO.OrdineDAO;
import model.service.GestoreCarrello;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class TestGestoreCarrello extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @InjectMocks
    public GestoreCarrello servlet;

    private Prodotto prodotto = new Prodotto(72,"NomeBottiglia","Tipo","Descrizione",127,1,"immagine",2000,"regione",13,13,2,"Vino");
    private Carrello carrello=new Carrello();
    private Utente utente;
    private Indirizzo indirizzo;


    @BeforeEach
    void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void TC_AggiungiProdottoCarrello1() throws ServletException, IOException {
        //Aggiunta prodotto al carrello singola quantità
        carrello.put(prodotto,1);
        request.getSession().setAttribute("carrello",carrello);
        request.addParameter("prodId", String.valueOf(prodotto.getId()));
        request.addParameter("addNum", String.valueOf("1"));

        String message = "prodotto aggiunto al carrello";
        System.out.println("oracolo: "+message);
        servlet.gestoreAggiungiProdottoCarrello(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);

        request.getSession().removeAttribute("carrello");
    }

    @Test
    void TC_AggiungiProdottoCarrello2() throws ServletException, IOException {
        //Aggiunta prodotto al carrello singola quantità
        carrello.put(prodotto,1);
        request.getSession().setAttribute("carrello",carrello);
        request.addParameter("prodId", String.valueOf(prodotto.getId()));
        request.addParameter("setNum", String.valueOf("1"));

        String message = "prodotti aggiunti al carrello";
        System.out.println("oracolo: "+message);
        servlet.gestoreAggiungiProdottoCarrello(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);

        request.getSession().removeAttribute("carrello");
    }

    @Test
    void TC_RimuoviProdottoCarrello() throws ServletException, IOException {
        //Aggiunta prodotto al carrello singola quantità
        carrello.put(prodotto,1);
        request.getSession().setAttribute("carrello",carrello);
        request.addParameter("prodId", String.valueOf(prodotto.getId()));

        String message = "prodotto rimosso dal carrello";
        System.out.println("oracolo: "+message);
        servlet.gestoreRimuoviProdottoCarrello(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);

        request.getSession().removeAttribute("carrello");
    }

    @Test
    void TC_GestoreAcquistoUtenteLoggato() throws ServletException, IOException {
        //Acquisto corretto
        UtenteDAO uDao=new UtenteDAO();
        OrdineDAO oDao=new OrdineDAO();
        ProductOrderedDao poDao=new ProductOrderedDao();
        utente=uDao.retriveById(18);

        IndirizzoDAO iDao=new IndirizzoDAO();
        indirizzo=iDao.retriveByID(25);
        //System.out.println(indirizzo.toString());

        request.getSession().setAttribute("utente",utente);
        request.addParameter("address", String.valueOf(indirizzo.getId()));

        carrello.put(prodotto,1);
        request.getSession().setAttribute("carrello",carrello);

        String message = "acquisto effettuato correttamente";
        System.out.println("oracolo: "+message);
        servlet.gestoreAcquisto(request,response);

        String idOrd= (String) request.getAttribute("idOrd");
        String idAdd= (String) request.getAttribute("idAdd");
        List<Integer> list= (List<Integer>) request.getAttribute("idProdotti");

        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);

        oDao.deleteOrder(Integer.parseInt(idOrd));
        oDao.removeAddressOrder(Integer.parseInt(idAdd));
        for(Integer i:list){
            String in=String.valueOf(i);
            poDao.deleteProductOrdered(Integer.parseInt(in));
        }
        request.getSession().removeAttribute("carrello");
    }

    @Test
    void TC_GestoreAcquistoUtenteNonLoggato() throws ServletException, IOException {
        //Utente non Loggato
        UtenteDAO uDao=new UtenteDAO();
        OrdineDAO oDao=new OrdineDAO();
        ProductOrderedDao poDao=new ProductOrderedDao();
        utente=null;

        IndirizzoDAO iDao=new IndirizzoDAO();
        indirizzo=iDao.retriveByID(25);
        //System.out.println(indirizzo.toString());

        request.getSession().setAttribute("utente",utente);
        request.addParameter("address", String.valueOf(indirizzo.getId()));

        carrello.put(prodotto,1);
        request.getSession().setAttribute("carrello",carrello);

        String message = "utente non loggato, ritorno alla pagina login";
        System.out.println("oracolo: "+message);
        servlet.gestoreAcquisto(request,response);
    }

    @Test
    void TC_GestoreAcquistoUtentePrivoIndirizzo() throws ServletException, IOException {
        //Utente non ha un indirizzo
        UtenteDAO uDao=new UtenteDAO();
        OrdineDAO oDao=new OrdineDAO();
        ProductOrderedDao poDao=new ProductOrderedDao();
        utente=uDao.retriveById(18);

        //System.out.println(indirizzo.toString());

        request.getSession().setAttribute("utente",utente);
        request.addParameter("address", "");

        carrello.put(prodotto,1);
        request.getSession().setAttribute("carrello",carrello);

        String message = "l'utente non ha un indirizzo, ritorno alla pagina login";
        System.out.println("oracolo: "+message);
        servlet.gestoreAcquisto(request,response);
    }


    @AfterEach
    void tearDown() throws Exception {

        request=null;
        response=null;
    }


}
