package testIntegrazione;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import classDAO.UtenteDAO;
import classDAO.IndirizzoDAO;
import bean.Utente;
import bean.Indirizzo;
import bean.Ordine;
import classDAO.OrdineDAO;
import model.GestoreAccount;
import model.GestoreOrdine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestGestoreOrdine extends Mockito{

        private MockHttpServletRequest request;
        private MockHttpServletResponse response;

        @InjectMocks
        public GestoreOrdine gestoreOrdine;

    public TestGestoreOrdine() throws ServletException, IOException {
    }


    @BeforeEach
        void setUp() throws Exception{
            request=new MockHttpServletRequest();
            response=new MockHttpServletResponse();
            MockitoAnnotations.initMocks(this);
        }

        @Test
        void TC_aggiungiOrdine() throws ServletException, IOException, ParseException{

            //creare dao
            IndirizzoDAO indirizzoDAO;
            UtenteDAO utenteDao;
            OrdineDAO ordineDAO;

            //creare oggetti e response (per la servlet)


            //(dentro la servlet)  -  request.setAttribute("errorTest", "successo, valido. prod non trovato");

            //chiamare la servelt gestioneServlet(request, response);

            //prendere errore String exit=request.getAttribute("errorTest");

            //string oracolo="stesso messsaggio settato sopra";

            //assertEquals(oracolo,exit);

            //system.out.println("oraolo: "+oracolo);
            //system.out.println("serlet: "+exit);

            //se nella sessione di dao hai aggiunte cose al db rimuoverle chiamando le dao interne

        }

        @Test
        public void TC_RestituisciOrdiniUtente() throws ServletException, IOException {
            UtenteDAO uDAO=new UtenteDAO();

            Utente utente=uDAO.retriveById(2);
            request.getSession().setAttribute("utente", utente);
            gestoreOrdine.gestoreRestituisciOrdiniUtenti(request, response);

            String exit= (String) request.getAttribute("errorTest");
            String oracolo="TuttiGliOrdiniUtente";

            assertEquals(oracolo, exit);

            System.out.println("oracolo: "+oracolo);
            System.out.println("servlet: "+exit);

    }

        @Test
        public void TC_RestituisciOrdineClienti() throws ServletException, IOException {
            gestoreOrdine.gestoreRestituisciOrdini(request, response);
            String exit=(String)request.getAttribute("errorTest");
            String oracolo="OrdiniDITuttiIClienti";

            assertEquals(oracolo, exit);

            System.out.println("oracolo: "+oracolo);
            System.out.println("servlet: "+exit);

        }

        @Test
        public void TC_RestituisciDettagliOrdine() throws ServletException, IOException {

            request.addParameter("idord", String.valueOf(5));
            request.addParameter("iduser", String.valueOf(2));
            request.addParameter("idIndirizzo", String.valueOf(5));

            gestoreOrdine.gestoreRestituisciDettagliOrdine(request, response);

            String exit=(String)request.getAttribute("errorTest");
            String oracolo="dettaglioOrdineSuccesso";

            assertEquals(oracolo, exit);

            System.out.println("oracolo: "+oracolo);
            System.out.println("servlet: "+exit);



        }

    @Test
    public void TC_gestoreOrdine() throws ServletException, IOException {
        UtenteDAO uDAO=new UtenteDAO();
        Utente utente=uDAO.retriveById(2);
        request.getSession().setAttribute("utente", utente);
        gestoreOrdine.gestoreOrdini(request, response);
        String exit=(String)request.getAttribute("errorTest");
        String oracolo="TuttiGliOrdiniUtente";

        assertEquals(oracolo, exit);

        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

    }

    @Test
    public void TC_eliminaOrdine() throws ServletException, IOException {
        Indirizzo ind=new Indirizzo();
        UtenteDAO uDao=new UtenteDAO();
        Utente ut=uDao.retriveById(2);
        /*String sDate1="2020-01-01";
        Date date1=new SimpleDateFormat("yyyy-dd-MM").parse(sDate1);*/
        Date date=new Date(2020/01/01);
        Ordine or = new Ordine(1, 2, 6,date, ind,ut );
        OrdineDAO oDAO = new OrdineDAO();
        oDAO.doSave(or, 6);
        request.addParameter("id", String.valueOf(1));

        gestoreOrdine.gestoreCancellaOrdine(request, response);

        String exit = (String) request.getAttribute("var");
        String oracolo = "Cancellazione eseguita con Successo";

        assertEquals(oracolo, exit);

        System.out.println("oracolo: " + oracolo);
        System.out.println("servlet: " + exit);

    }


        @AfterEach
        void tearDown() throws Exception{
            request=null;
            response=null;
        }


}
