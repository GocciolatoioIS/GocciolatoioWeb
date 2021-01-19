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
    void TC_AggiuntaProdottoNomeVuoto() throws ServletException, IOException {
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
    void TC_AggiuntaProdottoNomeErrato() throws ServletException, IOException {
        request.addParameter("nome","@@@");
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

        String message = "nome formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoTipoVuoto() throws ServletException, IOException {
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
    void TC_AggiuntaProdottoTipoErrato() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo","@@@");
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

        String message = "tipo formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoDescrizioneVuoto() throws ServletException, IOException {
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
    void TC_AggiuntaProdottoPrezzoVuoto() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", "");
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
    void TC_AggiuntaProdottoPrezzoErrato() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", "@@@");
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "prezzo formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoScontoVuoto() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", "");
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
    void TC_AggiuntaProdottoScontoErrato() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", "@@@");
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "sconto formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoImmagineVuoto() throws ServletException, IOException {
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
    void TC_AggiuntaProdottoImmagineErrato() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine","@@@");
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "immagine formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoAnnoVuoto() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", "");
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
    void TC_AggiuntaProdottoAnnoErrato() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", "@@@");
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "anno formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoRegioneVuoto() throws ServletException, IOException {
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
    void TC_AggiuntaProdottoRegioneErrato() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione","@@@");
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "regione formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoGradazioneVuoto() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", "");
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
    void TC_AggiuntaProdottoGradazioneErrato() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", "@@@");
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "gradazione formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoFormatoVuoto() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", "");
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoFormatoErrato() throws ServletException, IOException {
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", "@@@");
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "formato formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreAddProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_AggiuntaProdottoQuantitaVuoto() throws ServletException, IOException {
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
    void TC_AggiuntaProdottoQuantitaErrato() throws ServletException, IOException {
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
        request.addParameter("nome_categoria","@@@");

        String message = "nome_categoria formato non corretto";
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
    void TC_ModificaProdottoNomeVuoto() throws ServletException, IOException {
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
    void TC_ModifcaProdottoTipoVuoto() throws ServletException, IOException {
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
    void TC_ModificaProdottoDescrizioneVuoto() throws ServletException, IOException {
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
    void TC_ModificaProdottoPrezzoVuoto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", "");
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
    void TC_ModificaProdottoScontoVuoto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", "");
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
    void TC_ModificaProdottoImmagineVuoto() throws ServletException, IOException {
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
    void TC_ModificaProdottoAnnoVuoto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", "");
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
    void TC_ModificaProdottoRegioneVuoto() throws ServletException, IOException {
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
    void TC_ModificaProdottoGradazioneVuoto() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", "");
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
    void TC_ModificaProdottoFormatoVuoto() throws ServletException, IOException {
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
        request.addParameter("formato", "");
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoQuantitaVuoto() throws ServletException, IOException {
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
    void TC_ModificaProdottoNomeErrato() throws ServletException, IOException {
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
    void TC_ModifcaProdottoTipoErrato() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo","@@@");
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

        String message = "tipo formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoPrezzoErrato() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", "@@@");
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "prezzo formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoScontoErrato() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", "@@@");
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "sconto formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoImmagineErrato() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine","@@@");
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "immagine formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoAnnoErrato() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", "@@@");
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "anno formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoRegioneErrato() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione","@@@");
        request.addParameter("gradazione", String.valueOf(prodotto.getGradazione()));
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "regione formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoGradazioneErrato() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));
        request.addParameter("nome",prodotto.getNome());
        request.addParameter("tipo",prodotto.getTipo());
        request.addParameter("descrizione",prodotto.getDescrizione());
        request.addParameter("prezzo", String.valueOf(prodotto.getPrezzo()));
        request.addParameter("sconto", String.valueOf(prodotto.getSconto()));
        request.addParameter("immagine",prodotto.getImmagine());
        request.addParameter("anno", String.valueOf(prodotto.getAnno()));
        request.addParameter("regione",prodotto.getRegione());
        request.addParameter("gradazione", "@@@");
        request.addParameter("formato", String.valueOf(prodotto.getFormato()));
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "gradazione formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoFormatoErrato() throws ServletException, IOException {
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
        request.addParameter("formato", "@@@");
        request.addParameter("quantita_magazzino", String.valueOf(prodotto.getQuantita_magazzino()));
        request.addParameter("nome_categoria",prodotto.getNome_categoria());

        String message = "formato formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoQuantitaErrato() throws ServletException, IOException {
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
        request.addParameter("nome_categoria","@@@");

        String message = "nome_categoria formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoByAdminID() throws ServletException, IOException {
        request.addParameter("id", String.valueOf(prodotto.getId()));

        String message = "prodotto trovato";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdottoByAdmin(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoByAdminIDVuoto() throws ServletException, IOException {
        request.addParameter("id", "");

        String message = "id non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdottoByAdmin(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_ModificaProdottoByAdminIDErrato() throws ServletException, IOException {
        request.addParameter("id", "@@@");

        String message = "id formato non corretto";
        System.out.println("oracolo: "+message);
        servlet.gestoreModificaProdottoByAdmin(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_MostraProdotto() throws ServletException, IOException {
        request.addParameter("product", String.valueOf(prodotto.getId()));

        String message = "prodotto trovato";
        System.out.println("oracolo: "+message);
        servlet.gestoreMostraProdotto(request,response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_MostraProdottoVuoto() throws ServletException, IOException {
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
    void TC_RicercaProdottoNonTrovato() throws ServletException, IOException {
        request.addParameter("stringP", "@@@");
        servlet.gestoreRicercaProdotto(request,response);

        String message = "lista non trovata";
        System.out.println("oracolo: "+message);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_RicercaProdottoTrovato() throws ServletException, IOException {

        request.addParameter("stringP", "Brunello di Montalcino");
        servlet.gestoreRicercaProdotto(request,response);

        String message = "ricerca avvenuta con successo";
        System.out.println("oracolo: "+message);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @Test
    void TC_RicercaProdottoVuoto() throws ServletException, IOException {

        request.addParameter("stringP", "");
        servlet.gestoreRicercaProdotto(request,response);

        String message = "ricerca prodotto vuoto";
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
