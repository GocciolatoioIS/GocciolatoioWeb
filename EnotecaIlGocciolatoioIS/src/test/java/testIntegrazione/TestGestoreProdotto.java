package testIntegrazione;
import static org.junit.jupiter.api.Assertions.*;

import model.bean.Prodotto;
import classDAO.ProdottoDAO;
import model.service.GestoreProdotto;
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
    private GestoreProdotto servlet;

    private Prodotto prodotto = new Prodotto(72,"NomeBottiglia","Tipo","Descrizione",127,1,"immagine",2000,"regione",13,13,2,"Vino");

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
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);

        int id= Integer.parseInt(String.valueOf(request.getAttribute("testid")));
        request.addParameter("id", String.valueOf(id));
        servlet.gestoreCancellaProdotto(request,response);
    }

    @Test
    void TC_AggiuntaProdottoNomeNonCorretto() throws ServletException, IOException {
        request.addParameter("nome","");
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

        String message = "nome non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoTipoNonCorretto() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo","");
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

        String message = "tipo non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoDescrizioneNonCorretto() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione","");
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "descrizione non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoPrezzoNonCorretto() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf("0"));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "prezzo non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoScontoNonCorretto() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", "0");
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "sconto non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoImmagineNonCorretto() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine","");
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "immagine non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoAnnoNonCorretto() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf("0"));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "anno non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoRegioneNonCorretto() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione","");
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "regione non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoGradazioneNonCorretto() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf("0"));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "gradazione non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoFormatoNonCorretto() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf("0"));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoQuantitaNonCorretto() throws ServletException, IOException {
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
        request.addParameter("nome_categoria","");

        String message = "nome_categoria non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_CancellaProdotto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));

        String message = "Prodotto eliminato con successo";
        System.out.println("oracolo: "+message);
        servlet.gestoreCancellaProdotto(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdotto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
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

        String message = "Prodotto modificato con successo";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoNomeNonCorretto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome","");
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

        String message = "nome non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModifcaProdottoTipoNonCorretto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo","");
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

        String message = "tipo non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoDescrizioneNonCorretto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione","");
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "descrizione non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoPrezzoNonCorretto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf("0"));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "prezzo non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoScontoNonCorretto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", "0");
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "sconto non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoImmagineNonCorretto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine","");
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "immagine non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoAnnoNonCorretto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf("0"));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "anno non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoRegioneNonCorretto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione","");
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "regione non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoGradazioneNonCorretto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf("0"));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "gradazione non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoFormatoNonCorretto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf("0"));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoQuantitaNonCorretto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
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
        request.addParameter("nome_categoria","");

        String message = "nome_categoria non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoByAdmin() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));

        String message = "prodotto trovato";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdottoByAdmin(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_MostraProdotto1() throws ServletException, IOException {
        request.addParameter("product", String.valueOf(prodotto.getId()));

        String message = "prodotto trovato";
        System.out.println("oracolo: "+message);
        servlet.gestoreMostraProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_MostraProdotto2() throws ServletException, IOException {
        String pnull=null;
        request.addParameter("product",pnull);
        request.addParameter("prodId", String.valueOf(prodotto.getId()));

        String message = "prodotto trovato";
        System.out.println("oracolo: "+message);
        servlet.gestoreMostraProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_MostraTuttiProdotti() throws ServletException, IOException {
        servlet.gestoreMostraTuttiProdotti(request,response);
        String message = "lista trovata";
        System.out.println("oracolo: "+message);

        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_RicercaProdotto1() throws ServletException, IOException {
        request.addParameter("stringP", prodotto.getNome());
        servlet.gestoreRicercaProdotto(request,response);

        String message = "lista non trovata";
        System.out.println("oracolo: "+message);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_RicercaProdotto2() throws ServletException, IOException {

        request.addParameter("stringP", "Brunello di Montalcino");
        servlet.gestoreRicercaProdotto(request,response);

        String message = "ricerca avvenuta con successo";
        System.out.println("oracolo: "+message);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @AfterEach
    void tearDown() throws Exception {

        request=null;
        response=null;
    }

}
