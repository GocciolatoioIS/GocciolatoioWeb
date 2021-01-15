package testIntegrazione;
import static org.junit.jupiter.api.Assertions.*;

import bean.Prodotto;
import classDAO.ProdottoDAO;
import control.AggiungiProdotto;
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


public class TestGestoreProdotto extends Mockito{

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Mock
    private ProdottoDAO dao;

    @InjectMocks
    private AggiungiProdotto servlet;

    private Prodotto prodotto = new Prodotto(56,"NomeBottiglia",null,"Descrizione",127,1,null,2000,"Toscana",13,13,2,"Vino");

    @BeforeEach
    void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void TC_AggiuntaProdotto() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "Prodotto inserito con successo";
        servlet.doPost(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }



    @AfterEach
    void tearDown() throws Exception {
        request=null;
        response=null;
    }

}
