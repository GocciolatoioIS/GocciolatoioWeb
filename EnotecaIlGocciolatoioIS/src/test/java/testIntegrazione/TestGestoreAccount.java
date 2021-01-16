package testIntegrazione;

import static org.junit.jupiter.api.Assertions.*;

import bean.Utente;
import classDAO.UtenteDAO;
import model.GestoreAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.ParseException;

public class TestGestoreAccount extends Mockito{

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private Utente utente;

    @Mock
    private UtenteDAO utenteDAO=new UtenteDAO();

    @InjectMocks
    public final GestoreAccount gestoreAccount = new GestoreAccount();



    @BeforeEach
    void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        utente = new Utente();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void TC_AggiungiUtente() throws ServletException, IOException, ParseException {

        //aggiungere ai request i parametri
        request.addParameter("nome","Mario");
        request.addParameter("cognome","Rossi");
        request.addParameter("username","MarRoss");
        request.addParameter("email","marRoss@gmail.it");
        request.addParameter("pass","marioR10");
        request.addParameter("data","1980-01-01");

        gestoreAccount.gestioneRegistrazione(request, response);
        utente=(Utente) request.getSession().getAttribute("utente");
        System.out.println(utente.toString());
        String msg = (String) request.getAttribute("msg");


        assertEquals("utente inserito correttamente",msg);
        request.addParameter("id",String.valueOf(utente.getId()));

        gestoreAccount.gestoreCancellaUtente(request,response);

    }





    @AfterEach
    void tearDown() throws Exception {

        request=null;
        response=null;
    }

}
