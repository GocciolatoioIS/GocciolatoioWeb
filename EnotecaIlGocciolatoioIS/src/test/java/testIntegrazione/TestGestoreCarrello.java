package testIntegrazione;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.format.DateTimeFormatter;

import bean.*;
import classDAO.UtenteDAO;
import classDAO.IndirizzoDAO;
import classDAO.OrdineDAO;
import model.GestoreCarrello;
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

public class TestGestoreCarrello extends Mockito {
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @InjectMocks
    public GestoreCarrello servlet;

    private Carrello carrello=new Carrello();
    private Prodotto prodotto = new Prodotto(72,"NomeBottiglia","Tipo","Descrizione",127,1,"immagine",2000,"regione",13,13,2,"Vino");


    @BeforeEach
    void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void TC_AggiungiProdottoCarrello1() throws ServletException, IOException {
        //Aggiunta prodotto al carrello singola quantità
        request.getSession().setAttribute("carrello",carrello);
        request.addParameter("prodId", String.valueOf(prodotto.getId()));

        String message = "Prodotto aggiunto al carrello";
        System.out.println("oracolo: "+message);
        servlet.gestoreAggiungiProdottoCarrello(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);

        request.getSession().removeAttribute("carrello");
    }

    @AfterEach
    void tearDown() throws Exception {

        request=null;
        response=null;
    }


}
