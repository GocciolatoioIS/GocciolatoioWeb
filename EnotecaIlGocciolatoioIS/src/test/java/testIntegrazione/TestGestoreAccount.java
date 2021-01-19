package testIntegrazione;

import static org.junit.jupiter.api.Assertions.*;

import model.bean.Indirizzo;
import model.bean.Utente;
import classDAO.IndirizzoDAO;
import classDAO.UtenteDAO;
import model.service.GestoreAccount;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestGestoreAccount extends Mockito{

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private Utente utente;

    @Mock
    private UtenteDAO utenteDAO=new UtenteDAO();

    @InjectMocks
    public GestoreAccount gestoreAccount;

    @BeforeEach
    void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        utente = new Utente();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    //Viene testata la aggiungiutenteelarimuovi
    void TC_AggiungiUtente() throws ServletException, IOException, ParseException {
        //aggiungere ai request i parametri
        request.addParameter("nome","Mario");
        request.addParameter("cognome","Rossi");
        request.addParameter("username","MarRoss");
        request.addParameter("email","marRoss@gmail.it");
        request.addParameter("pass","marioR10");
        request.addParameter("data","1980-01-01");

        gestoreAccount.gestioneRegistrazione(request, response);
        //utente=(Utente) request.getSession().getAttribute("utente");
        System.out.println(utente.toString());
        String msg = (String) request.getAttribute("msg");

        int id= Integer.parseInt(String.valueOf(request.getAttribute("testid")));
        assertEquals("utente inserito correttamente",msg);

        System.out.println(id);
        request.addParameter("id",String.valueOf(id));

        gestoreAccount.gestoreCancellaUtente(request,response);

    }

    @Test
    void TC_AggiungiUtenteNomeVuoto() throws ServletException, IOException, ParseException {

        //aggiungere ai request i parametri
        request.addParameter("nome","");
        request.addParameter("cognome","Rossi");
        request.addParameter("username","MarRossi");
        request.addParameter("email","marRoss@gmail.it");
        request.addParameter("pass","marioR10");
        request.addParameter("data","1980-01-01");

        gestoreAccount.gestioneRegistrazione(request, response);
        //utente=(Utente) request.getSession().getAttribute("utente");
        String exit= (String) request.getAttribute("errorTest");
        String oracolo="nome non corretto";

        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servelt: "+exit);

    }

    @Test
    void TC_AggiungiUtenteCognomeVuoto() throws ServletException, IOException, ParseException {

        //aggiungere ai request i parametri
        request.addParameter("nome","Mario");
        request.addParameter("cognome","");
        request.addParameter("username","MarRossi");
        request.addParameter("email","marRoss@gmail.it");
        request.addParameter("pass","marioR10");
        request.addParameter("data","1980-01-01");

        gestoreAccount.gestioneRegistrazione(request, response);
        //utente=(Utente) request.getSession().getAttribute("utente");
        String exit= (String) request.getAttribute("errorTest");
        String oracolo= "cognome non corretto";

        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servelt: "+exit);

    }

    @Test
    void TC_AggiungiUtenteUsernameVuoto() throws ServletException, IOException, ParseException {
        //aggiungere ai request i parametri
        request.addParameter("nome","Mario");
        request.addParameter("cognome","Rossi");
        request.addParameter("username","");
        request.addParameter("email","marRoss@gmail.it");
        request.addParameter("pass","marioR10");
        request.addParameter("data","1980-01-01");

        gestoreAccount.gestioneRegistrazione(request, response);
        //utente=(Utente) request.getSession().getAttribute("utente");
        String exit= (String) request.getAttribute("errorTest");

        String oracolo="username non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);

        System.out.println("servelt: "+exit);

    }

    @Test
    void TC_AggiungiUtenteEmailVuoto() throws ServletException, IOException, ParseException {

        //aggiungere ai request i parametri
        request.addParameter("nome","Mario");
        request.addParameter("cognome","Rossi");
        request.addParameter("username","MarRoss");
        request.addParameter("email","");
        request.addParameter("pass","marioR10");
        request.addParameter("data","1980-01-01");

        gestoreAccount.gestioneRegistrazione(request, response);
        //utente=(Utente) request.getSession().getAttribute("utente");
        String exit= (String) request.getAttribute("errorTest");

        String oracolo= "email non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals("email non corretto",exit);
        System.out.println("servelt: "+exit);

    }

    @Test
    void TC_AggiungiUtentePassVuoto() throws ServletException, IOException, ParseException {

        //aggiungere ai request i parametri
        request.addParameter("nome","Mario");
        request.addParameter("cognome","Rossi");
        request.addParameter("username","MarRoss");
        request.addParameter("email","marRoss@gmail.it");
        request.addParameter("pass","");
        request.addParameter("data","1980-01-01");

        gestoreAccount.gestioneRegistrazione(request, response);
        //utente=(Utente) request.getSession().getAttribute("utente");
        String exit= (String) request.getAttribute("errorTest");

        String oracolo= "password non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals("password non corretto",exit);
        System.out.println("servelt: "+exit);

    }

    @Test
    void TC_AggiungiUtenteDateVuoto() throws ServletException, IOException, ParseException {

        //aggiungere ai request i parametri
        request.addParameter("nome","Mario");
        request.addParameter("cognome","Rossi");
        request.addParameter("username","MarRoss");
        request.addParameter("email","marRoss@gmail.it");
        request.addParameter("pass","marioR10");
        request.addParameter("data","");

        gestoreAccount.gestioneRegistrazione(request, response);
        //utente=(Utente) request.getSession().getAttribute("utente");
        String exit= (String) request.getAttribute("errorTest");

        String oracolo= "data nascita non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals("data nascita non corretto",exit);
        System.out.println("servelt: "+exit);

    }

    @Test
    void TC_AggiungiUtenteNomeErrato() throws ServletException, IOException, ParseException {

        //aggiungere ai request i parametri
        request.addParameter("nome","@@@");
        request.addParameter("cognome","Rossi");
        request.addParameter("username","MarRossi");
        request.addParameter("email","marRoss@gmail.it");
        request.addParameter("pass","marioR10");
        request.addParameter("data","1980-01-01");

        gestoreAccount.gestioneRegistrazione(request, response);
        //utente=(Utente) request.getSession().getAttribute("utente");
        String exit= (String) request.getAttribute("errorTest");
        String oracolo="nome formato non corretto";

        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servelt: "+exit);

    }

    @Test
    void TC_AggiungiUtenteCognomeErrato() throws ServletException, IOException, ParseException {

        //aggiungere ai request i parametri
        request.addParameter("nome","Mario");
        request.addParameter("cognome","@@@");
        request.addParameter("username","MarRossi");
        request.addParameter("email","marRoss@gmail.it");
        request.addParameter("pass","marioR10");
        request.addParameter("data","1980-01-01");

        gestoreAccount.gestioneRegistrazione(request, response);
        //utente=(Utente) request.getSession().getAttribute("utente");
        String exit= (String) request.getAttribute("errorTest");
        String oracolo= "cognome formato non corretto";

        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servelt: "+exit);

    }

    @Test
    void TC_AggiungiUtenteUsernameErrato() throws ServletException, IOException, ParseException {
        //aggiungere ai request i parametri
        request.addParameter("nome","Mario");
        request.addParameter("cognome","Rossi");
        request.addParameter("username","@@@");
        request.addParameter("email","marRoss@gmail.it");
        request.addParameter("pass","marioR10");
        request.addParameter("data","1980-01-01");

        gestoreAccount.gestioneRegistrazione(request, response);
        //utente=(Utente) request.getSession().getAttribute("utente");
        String exit= (String) request.getAttribute("errorTest");

        String oracolo="username formato non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);

        System.out.println("servelt: "+exit);

    }

    @Test
    void TC_AggiungiUtenteEmailErrato() throws ServletException, IOException, ParseException {

        //aggiungere ai request i parametri
        request.addParameter("nome","Mario");
        request.addParameter("cognome","Rossi");
        request.addParameter("username","MarRoss");
        request.addParameter("email","@@@");
        request.addParameter("pass","marioR10");
        request.addParameter("data","1980-01-01");

        gestoreAccount.gestioneRegistrazione(request, response);
        //utente=(Utente) request.getSession().getAttribute("utente");
        String exit= (String) request.getAttribute("errorTest");

        String oracolo= "email formato non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals("email formato non corretto",exit);
        System.out.println("servelt: "+exit);

    }

    @Test
    void TC_AggiungiUtenteDateErrato() throws ServletException, IOException, ParseException {

        //aggiungere ai request i parametri
        request.addParameter("nome","Mario");
        request.addParameter("cognome","Rossi");
        request.addParameter("username","MarRoss");
        request.addParameter("email","marRoss@gmail.it");
        request.addParameter("pass","marioR10");
        request.addParameter("data","@@@");

        gestoreAccount.gestioneRegistrazione(request, response);
        //utente=(Utente) request.getSession().getAttribute("utente");
        String exit= (String) request.getAttribute("errorTest");

        String oracolo= "data nascita non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals("data nascita formato non corretto",exit);
        System.out.println("servelt: "+exit);

    }

    @Test
    public void TC_gestioneAddIndirizzo() throws ServletException, IOException {

        //Settaggio di parametri
        UtenteDAO uDao=new UtenteDAO();
        Utente u=uDao.retriveById(2);
        request.getSession().setAttribute("utente",u);
        request.addParameter("citta","Napoli");
        request.addParameter("cap","9000");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","01");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        gestoreAccount.gestioneAddIndirizzo(request,response);
        String exit= (String) request.getSession().getAttribute("errorTest");
        String oracolo="Indirizzo inserito";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servlet: "+exit);

        int idTest= (int) request.getSession().getAttribute("idTest");
        IndirizzoDAO indirizzoDAO= new IndirizzoDAO();
        indirizzoDAO.removeInd(idTest);

    }

    @Test
    public void TC_gestioneAddIndirizzoCittaVuoto() throws ServletException, IOException {

        //Settaggio di parametri
        UtenteDAO uDao=new UtenteDAO();
        Utente u=uDao.retriveById(2);
        request.getSession().setAttribute("utente",u);
        request.addParameter("citta","");
        request.addParameter("cap","9000");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","01");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        gestoreAccount.gestioneAddIndirizzo(request,response);
        String exit= (String) request.getAttribute("errorTest");
        String oracolo="citta indirizzo non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servlet: "+exit);

    }

    @Test
    public void TC_GestioneAddIndirizzoCapVuoto() throws ServletException, IOException {

        //Settaggio di parametri
        UtenteDAO uDao=new UtenteDAO();
        Utente u=uDao.retriveById(2);
        request.getSession().setAttribute("utente",u);
        request.addParameter("citta","Napoli");
        request.addParameter("cap","");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","01");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        gestoreAccount.gestioneAddIndirizzo(request,response);
        String exit= (String) request.getAttribute("errorTest");
        String oracolo="cap indirizzo non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servlet: "+exit);

    }

    @Test
    public void TC_GestioneAddIndirizzoViaVuoto() throws ServletException, IOException {

        //Settaggio di parametri
        UtenteDAO uDao=new UtenteDAO();
        Utente u=uDao.retriveById(2);
        request.getSession().setAttribute("utente",u);
        request.addParameter("citta","Napoli");
        request.addParameter("cap","9000");
        request.addParameter("via","");
        request.addParameter("ncivico","01");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        gestoreAccount.gestioneAddIndirizzo(request,response);
        String exit= (String) request.getAttribute("errorTest");
        String oracolo="via indirizzo non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servlet: "+exit);


    }

    @Test
    public void TC_GestioneAddIndirizzoNcivicoVuoto() throws ServletException, IOException {

        //Settaggio di parametri
        UtenteDAO uDao=new UtenteDAO();
        Utente u=uDao.retriveById(2);
        request.getSession().setAttribute("utente",u);
        request.addParameter("citta","Napoli");
        request.addParameter("cap","9000");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        gestoreAccount.gestioneAddIndirizzo(request,response);
        String exit= (String) request.getAttribute("errorTest");
        String oracolo="numero civico indirizzo non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servlet: "+exit);

    }

    @Test
    public void TC_GestioneAddIndirizzoNazioneVuoto() throws ServletException, IOException {

        //Settaggio di parametri
        UtenteDAO uDao=new UtenteDAO();
        Utente u=uDao.retriveById(2);
        request.getSession().setAttribute("utente",u);
        request.addParameter("citta","Napoli");
        request.addParameter("cap","9000");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","01");
        request.addParameter("country","");
        request.addParameter("iDUtente","2");

        gestoreAccount.gestioneAddIndirizzo(request,response);
        String exit= (String) request.getAttribute("errorTest");
        String oracolo="nazione indirizzo non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servlet: "+exit);


    }

    @Test
    public void TC_gestioneAddIndirizzoCittaErrato() throws ServletException, IOException {

        //Settaggio di parametri
        UtenteDAO uDao=new UtenteDAO();
        Utente u=uDao.retriveById(2);
        request.getSession().setAttribute("utente",u);
        request.addParameter("citta","@@@");
        request.addParameter("cap","9000");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","01");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        gestoreAccount.gestioneAddIndirizzo(request,response);
        String exit= (String) request.getAttribute("errorTest");
        String oracolo="citta indirizzo formato non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servlet: "+exit);

    }

    @Test
    public void TC_GestioneAddIndirizzoCapErrato() throws ServletException, IOException {

        //Settaggio di parametri
        UtenteDAO uDao=new UtenteDAO();
        Utente u=uDao.retriveById(2);
        request.getSession().setAttribute("utente",u);
        request.addParameter("citta","Napoli");
        request.addParameter("cap","@@@");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","01");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        gestoreAccount.gestioneAddIndirizzo(request,response);
        String exit= (String) request.getAttribute("errorTest");
        String oracolo="cap indirizzo formato non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servlet: "+exit);

    }

    @Test
    public void TC_GestioneAddIndirizzoViaErrato() throws ServletException, IOException {

        //Settaggio di parametri
        UtenteDAO uDao=new UtenteDAO();
        Utente u=uDao.retriveById(2);
        request.getSession().setAttribute("utente",u);
        request.addParameter("citta","Napoli");
        request.addParameter("cap","9000");
        request.addParameter("via","@@@");
        request.addParameter("ncivico","01");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        gestoreAccount.gestioneAddIndirizzo(request,response);
        String exit= (String) request.getAttribute("errorTest");
        String oracolo="via indirizzo formato non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servlet: "+exit);


    }

    @Test
    public void TC_GestioneAddIndirizzoNcivicoErrato() throws ServletException, IOException {

        //Settaggio di parametri
        UtenteDAO uDao=new UtenteDAO();
        Utente u=uDao.retriveById(2);
        request.getSession().setAttribute("utente",u);
        request.addParameter("citta","Napoli");
        request.addParameter("cap","9000");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","@@@");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        gestoreAccount.gestioneAddIndirizzo(request,response);
        String exit= (String) request.getAttribute("errorTest");
        String oracolo="numero civico indirizzo formato non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servlet: "+exit);

    }

    @Test
    public void TC_GestioneAddIndirizzoNazioneErato() throws ServletException, IOException {

        //Settaggio di parametri
        UtenteDAO uDao=new UtenteDAO();
        Utente u=uDao.retriveById(2);
        request.getSession().setAttribute("utente",u);
        request.addParameter("citta","Napoli");
        request.addParameter("cap","9000");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","01");
        request.addParameter("country","@@@");
        request.addParameter("iDUtente","2");

        gestoreAccount.gestioneAddIndirizzo(request,response);
        String exit= (String) request.getAttribute("errorTest");
        String oracolo="nazione indirizzo formato non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servlet: "+exit);


    }

    @Test
    public void TC_GestioneCancellaUtente() throws ServletException, IOException {
        UtenteDAO utenteDAO= new UtenteDAO();
        Utente utenteEsistente;
        //Creazione Utente esistente
        List<Indirizzo> indirizzoListEssist=new ArrayList<>();
        Indirizzo indirizzoEsistente= new Indirizzo(2,"Napoli", 9000, "Via Grande", 01, "IT");
        indirizzoListEssist.add(indirizzoEsistente);
        utenteEsistente=new Utente(1,"esisto@live.it","esisto","esisto","utente","Mario","Rossi",new Date("01/01/1980"),indirizzoListEssist);
        utenteDAO.doSave(utenteEsistente);
        int id= utenteEsistente.getId();
        request.addParameter("id",String.valueOf(id));
        gestoreAccount.gestoreCancellaUtente(request,response);
    }

    @Test
    public void TC_GestioneReindirizzamentoLoginOProfilo()throws ServletException, IOException {
        UtenteDAO uDAO=new UtenteDAO();
        Utente utenteEsistente,adminEsistente;

        //Utente non loggato
        System.out.println("Test utente non loggato:");
        gestoreAccount.gestoreReindirizzamentoLoginOProfilo(request,response);
        String exit2= (String) request.getAttribute("errorTest");
        String oracolo2="login area";
        System.out.println("oracolo: "+oracolo2);

        assertEquals(oracolo2,exit2);
        System.out.println("servlet: "+exit2);
        //Utente utente loggato
        System.out.println("\nTest utente loggato utente:");
        utenteEsistente=uDAO.retriveById(3);

        request.getSession().setAttribute("utente",utenteEsistente);
        gestoreAccount.gestoreReindirizzamentoLoginOProfilo(request,response);
        String exit= (String) request.getAttribute("errorTest");
        String oracolo="utente area";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servlet: "+exit);

        //Amministratore loggato
        System.out.println("\nTest admin loggato:");
        adminEsistente=uDAO.retriveById(2);
        request.getSession().setAttribute("utente",adminEsistente);

        gestoreAccount.gestoreReindirizzamentoLoginOProfilo(request,response);
        exit= (String) request.getAttribute("errorTest");
        oracolo="admin area";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servlet: "+exit);

    }

    @Test
    public void TC_GestioneLoginUtenteTrovato() throws ServletException, IOException {

        UtenteDAO uDao=new UtenteDAO();
        Utente utenteEsistente=uDao.retriveById(2);

        request.addParameter("username",utenteEsistente.getUsername());
        request.addParameter("pass","password");
        gestoreAccount.gestoreLogin(request,response);
        String exit= (String) request.getAttribute("errorTest");
        String oracolo="utente  trovato";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servlet: "+exit);


    }

    @Test
    public void TC_GestioneLoginUtenteUsernameVuoto() throws  ServletException,IOException{

        UtenteDAO uDao=new UtenteDAO();
        Utente utenteEsistente=uDao.retriveById(2);

        request.addParameter("username","");
        request.addParameter("pass","passwordddd");
        gestoreAccount.gestoreLogin(request,response);
        String exit= (String) request.getAttribute("errorTest");
        String oracolo="username login non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestioneLoginUtentePasswordVuoto() throws  ServletException,IOException{

        UtenteDAO uDao=new UtenteDAO();
        Utente utenteEsistente=uDao.retriveById(2);

        request.addParameter("username",utenteEsistente.getUsername());
        request.addParameter("pass","");
        gestoreAccount.gestoreLogin(request,response);
        String exit= (String) request.getAttribute("errorTest");
        String oracolo="password login non corretto";
        System.out.println("oracolo: "+oracolo);
        assertEquals(oracolo,exit);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestioneModificaIndirizzo() throws ServletException, IOException {
        IndirizzoDAO iDAO=new IndirizzoDAO();
        UtenteDAO uDao= new UtenteDAO();
        request.getSession().setAttribute("utente", uDao.retriveById(2));
        Indirizzo ind=new Indirizzo(-1,"Napoli",9000,"Via Grande",01,"IT");
        iDAO.doSave(ind,2);
        request.addParameter("action","modInd");
        request.addParameter("citta","Somma");
        request.addParameter("cap","9000");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","01");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        request.addParameter("idInd", String.valueOf(ind.getId()));

        gestoreAccount.gestoreModificaIndirizzo(request,response);

        String oracolo="indirizzo modificato";
        String exit=(String) request.getAttribute("errorTest");
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

        iDAO.deleteIndirizzo(ind.getId());
    }

    @Test
    public void TC_GestioneModificaIndirizzoCittaVuoto() throws ServletException, IOException {
        IndirizzoDAO iDAO=new IndirizzoDAO();
        UtenteDAO uDao= new UtenteDAO();
        request.getSession().setAttribute("utente", uDao.retriveById(2));
        Indirizzo ind=new Indirizzo(-1,"Napoli",9000,"Via Grande",01,"IT");
        iDAO.doSave(ind,2);
        request.addParameter("action","modInd");
        request.addParameter("citta","");
        request.addParameter("cap","9000");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","01");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        request.addParameter("idInd", String.valueOf(ind.getId()));

        gestoreAccount.gestoreModificaIndirizzo(request,response);

        String oracolo="citta indirizzo non corretto";
        String exit=(String) request.getAttribute("errorTest");
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

        iDAO.deleteIndirizzo(ind.getId());

    }

    @Test
    public void TC_GestioneModificaIndirizzoCapVuoto() throws ServletException, IOException {
        IndirizzoDAO iDAO=new IndirizzoDAO();
        UtenteDAO uDao= new UtenteDAO();
        request.getSession().setAttribute("utente", uDao.retriveById(2));
        Indirizzo ind=new Indirizzo(-1,"Napoli",9000,"Via Grande",01,"IT");
        iDAO.doSave(ind,2);
        request.addParameter("action","modInd");
        request.addParameter("citta","Napoli");
        request.addParameter("cap","");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","01");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        request.addParameter("idInd", String.valueOf(ind.getId()));

        gestoreAccount.gestoreModificaIndirizzo(request,response);

        String oracolo="cap indirizzo non corretto";
        String exit=(String) request.getAttribute("errorTest");
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

        iDAO.deleteIndirizzo(ind.getId());

    }

    @Test
    public void TC_GestioneModificaIndirizzoViaVuoto() throws ServletException, IOException {
        IndirizzoDAO iDAO=new IndirizzoDAO();
        UtenteDAO uDao= new UtenteDAO();
        request.getSession().setAttribute("utente", uDao.retriveById(2));
        Indirizzo ind=new Indirizzo(-1,"Napoli",9000,"Via Grande",01,"IT");
        iDAO.doSave(ind,2);
        request.addParameter("action","modInd");
        request.addParameter("citta","Napoli");
        request.addParameter("cap","9000");
        request.addParameter("via","");
        request.addParameter("ncivico","01");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        request.addParameter("idInd", String.valueOf(ind.getId()));

        gestoreAccount.gestoreModificaIndirizzo(request,response);

        String oracolo="via indirizzo non corretto";
        String exit=(String) request.getAttribute("errorTest");
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

        iDAO.deleteIndirizzo(ind.getId());

    }

    @Test
    public void TC_GestioneModificaIndirizzoNumeroCivicoVuoto() throws ServletException, IOException {
        IndirizzoDAO iDAO=new IndirizzoDAO();
        UtenteDAO uDao= new UtenteDAO();
        request.getSession().setAttribute("utente", uDao.retriveById(2));
        Indirizzo ind=new Indirizzo(-1,"Napoli",9000,"Via Grande",01,"IT");
        iDAO.doSave(ind,2);
        request.addParameter("action","modInd");
        request.addParameter("citta","Napoli");
        request.addParameter("cap","9000");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        request.addParameter("idInd", String.valueOf(ind.getId()));

        gestoreAccount.gestoreModificaIndirizzo(request,response);

        String oracolo="numero civico indirizzo non corretto";
        String exit=(String) request.getAttribute("errorTest");
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

        iDAO.deleteIndirizzo(ind.getId());

    }

    @Test
    public void TC_GestioneModificaIndirizzoNazioneVuoto() throws ServletException, IOException {
        IndirizzoDAO iDAO=new IndirizzoDAO();
        UtenteDAO uDao= new UtenteDAO();
        request.getSession().setAttribute("utente", uDao.retriveById(2));
        Indirizzo ind=new Indirizzo(-1,"Napoli",9000,"Via Grande",01,"IT");
        iDAO.doSave(ind,2);
        request.addParameter("action","modInd");
        request.addParameter("citta","Napoli");
        request.addParameter("cap","9000");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","01");
        request.addParameter("country","");
        request.addParameter("iDUtente","2");

        request.addParameter("idInd", String.valueOf(ind.getId()));

        gestoreAccount.gestoreModificaIndirizzo(request,response);

        String oracolo="nazione indirizzo non corretto";
        String exit=(String) request.getAttribute("errorTest");
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

        iDAO.deleteIndirizzo(ind.getId());
    }

    @Test
    public void TC_GestioneModificaIndirizzoCittaErrato() throws ServletException, IOException {
        IndirizzoDAO iDAO=new IndirizzoDAO();
        UtenteDAO uDao= new UtenteDAO();
        request.getSession().setAttribute("utente", uDao.retriveById(2));
        Indirizzo ind=new Indirizzo(-1,"Napoli",9000,"Via Grande",01,"IT");
        iDAO.doSave(ind,2);
        request.addParameter("action","modInd");
        request.addParameter("citta","@@@");
        request.addParameter("cap","9000");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","01");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        request.addParameter("idInd", String.valueOf(ind.getId()));

        gestoreAccount.gestoreModificaIndirizzo(request,response);

        String oracolo="citta indirizzo formato non corretto";
        String exit=(String) request.getAttribute("errorTest");
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

        iDAO.deleteIndirizzo(ind.getId());

    }

    @Test
    public void TC_GestioneModificaIndirizzoCapErrato() throws ServletException, IOException {
        IndirizzoDAO iDAO=new IndirizzoDAO();
        UtenteDAO uDao= new UtenteDAO();
        request.getSession().setAttribute("utente", uDao.retriveById(2));
        Indirizzo ind=new Indirizzo(-1,"Napoli",9000,"Via Grande",01,"IT");
        iDAO.doSave(ind,2);
        request.addParameter("action","modInd");
        request.addParameter("citta","Napoli");
        request.addParameter("cap","@@@");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","01");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        request.addParameter("idInd", String.valueOf(ind.getId()));

        gestoreAccount.gestoreModificaIndirizzo(request,response);

        String oracolo="cap indirizzo formato non corretto";
        String exit=(String) request.getAttribute("errorTest");
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

        iDAO.deleteIndirizzo(ind.getId());

    }

    @Test
    public void TC_GestioneModificaIndirizzoViaErrato() throws ServletException, IOException {
        IndirizzoDAO iDAO=new IndirizzoDAO();
        UtenteDAO uDao= new UtenteDAO();
        request.getSession().setAttribute("utente", uDao.retriveById(2));
        Indirizzo ind=new Indirizzo(-1,"Napoli",9000,"Via Grande",01,"IT");
        iDAO.doSave(ind,2);
        request.addParameter("action","modInd");
        request.addParameter("citta","Napoli");
        request.addParameter("cap","9000");
        request.addParameter("via","@@@");
        request.addParameter("ncivico","01");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        request.addParameter("idInd", String.valueOf(ind.getId()));

        gestoreAccount.gestoreModificaIndirizzo(request,response);

        String oracolo="via indirizzo formato non corretto";
        String exit=(String) request.getAttribute("errorTest");
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

        iDAO.deleteIndirizzo(ind.getId());

    }

    @Test
    public void TC_GestioneModificaIndirizzoNumeroCivicoErrato() throws ServletException, IOException {
        IndirizzoDAO iDAO=new IndirizzoDAO();
        UtenteDAO uDao= new UtenteDAO();
        request.getSession().setAttribute("utente", uDao.retriveById(2));
        Indirizzo ind=new Indirizzo(-1,"Napoli",9000,"Via Grande",01,"IT");
        iDAO.doSave(ind,2);
        request.addParameter("action","modInd");
        request.addParameter("citta","Napoli");
        request.addParameter("cap","9000");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","@@@");
        request.addParameter("country","IT");
        request.addParameter("iDUtente","2");

        request.addParameter("idInd", String.valueOf(ind.getId()));

        gestoreAccount.gestoreModificaIndirizzo(request,response);

        String oracolo="numero civico indirizzo formato non corretto";
        String exit=(String) request.getAttribute("errorTest");
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

        iDAO.deleteIndirizzo(ind.getId());

    }

    @Test
    public void TC_GestioneModificaIndirizzoNazioneErrato() throws ServletException, IOException {
        IndirizzoDAO iDAO=new IndirizzoDAO();
        UtenteDAO uDao= new UtenteDAO();
        request.getSession().setAttribute("utente", uDao.retriveById(2));
        Indirizzo ind=new Indirizzo(-1,"Napoli",9000,"Via Grande",01,"IT");
        iDAO.doSave(ind,2);
        request.addParameter("action","modInd");
        request.addParameter("citta","Napoli");
        request.addParameter("cap","9000");
        request.addParameter("via","Via Grande");
        request.addParameter("ncivico","01");
        request.addParameter("country","@@@");
        request.addParameter("iDUtente","2");

        request.addParameter("idInd", String.valueOf(ind.getId()));

        gestoreAccount.gestoreModificaIndirizzo(request,response);

        String oracolo="nazione indirizzo formato non corretto";
        String exit=(String) request.getAttribute("errorTest");
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

        iDAO.deleteIndirizzo(ind.getId());
    }

    @Test
    public void TC_GestioneModificaUtente() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(2);
        request.getSession().setAttribute("utente",u);

        request.addParameter("username",u.getUsername());
        request.addParameter("email",u.getEmail());
        request.addParameter("nome",u.getNome());
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtente(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="modifica utente successo";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

    }

    @Test
    public void TC_GestioneModificaUtenteUsernameVuoto() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(2);
        request.getSession().setAttribute("utente",u);

        request.addParameter("username","");
        request.addParameter("email",u.getEmail());
        request.addParameter("nome",u.getNome());
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtente(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="username non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

    }

    @Test
    public void TC_GestioneModificaUtenteEmailVuoto() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(2);
        request.getSession().setAttribute("utente",u);

        request.addParameter("username",u.getUsername());
        request.addParameter("email","");
        request.addParameter("nome",u.getNome());
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtente(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="email non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

    }

    @Test
    public void TC_GestioneModificaUtenteNomeVuoto() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(2);
        request.getSession().setAttribute("utente",u);

        request.addParameter("username",u.getUsername());
        request.addParameter("email",u.getEmail());
        request.addParameter("nome","");
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtente(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="nome non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

    }

    @Test
    public void TC_GestioneModificaUtenteCognomeVuoto() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(2);
        request.getSession().setAttribute("utente",u);

        request.addParameter("username",u.getUsername());
        request.addParameter("email",u.getEmail());
        request.addParameter("nome",u.getNome());
        request.addParameter("cognome","");
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtente(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="cognome non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

    }

    @Test
    public void TC_GestioneModificaUtenteUsernameErrato() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(2);
        request.getSession().setAttribute("utente",u);

        request.addParameter("username","@@@");
        request.addParameter("email",u.getEmail());
        request.addParameter("nome",u.getNome());
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtente(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="username formato non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

    }

    @Test
    public void TC_GestioneModificaUtenteEmailErrato() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(2);
        request.getSession().setAttribute("utente",u);

        request.addParameter("username",u.getUsername());
        request.addParameter("email","@@@");
        request.addParameter("nome",u.getNome());
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtente(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="email formato non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

    }

    @Test
    public void TC_GestioneModificaUtenteNomeErrato() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(2);
        request.getSession().setAttribute("utente",u);

        request.addParameter("username",u.getUsername());
        request.addParameter("email",u.getEmail());
        request.addParameter("nome","@@@");
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtente(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="nome formato non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

    }

    @Test
    public void TC_GestioneModificaUtenteCognomeErrato() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(2);
        request.getSession().setAttribute("utente",u);

        request.addParameter("username",u.getUsername());
        request.addParameter("email",u.getEmail());
        request.addParameter("nome",u.getNome());
        request.addParameter("cognome","@@@");
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtente(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="cognome formato non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);

    }


    @Test
    public void TC_GestioneModificaUtenteByAdmin() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(3);

        request.getSession().setAttribute("utente",u);

        request.addParameter("id",String.valueOf(u.getId()));
        request.addParameter("username",u.getUsername());
        request.addParameter("email",u.getEmail());
        request.addParameter("nome",u.getNome());
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtenteByAdmin(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="modifica utente successo admin";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestioneModificaUtenteByAdminUsernameVuoto() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(3);

        request.getSession().setAttribute("utente",u);

        request.addParameter("id",String.valueOf(u.getId()));
        request.addParameter("username","");
        request.addParameter("email",u.getEmail());
        request.addParameter("nome",u.getNome());
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtenteByAdmin(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="username non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestioneModificaUtenteByAdminEmailVuoto() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(3);

        request.getSession().setAttribute("utente",u);

        request.addParameter("id",String.valueOf(u.getId()));
        request.addParameter("username",u.getUsername());
        request.addParameter("email","");
        request.addParameter("nome",u.getNome());
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtenteByAdmin(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="email non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestioneModificaUtenteByAdminNomeVuoto() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(3);

        request.getSession().setAttribute("utente",u);

        request.addParameter("id",String.valueOf(u.getId()));
        request.addParameter("username",u.getUsername());
        request.addParameter("email",u.getEmail());
        request.addParameter("nome","");
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtenteByAdmin(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="nome non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestioneModificaUtenteByAdminCognomeVuoto() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(3);

        request.getSession().setAttribute("utente",u);

        request.addParameter("id",String.valueOf(u.getId()));
        request.addParameter("username",u.getUsername());
        request.addParameter("email",u.getEmail());
        request.addParameter("nome",u.getCognome());
        request.addParameter("cognome","");
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtenteByAdmin(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="cognome non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestioneModificaUtenteByAdminRuoloVuoto() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(3);

        request.getSession().setAttribute("utente",u);

        request.addParameter("id",String.valueOf(u.getId()));
        request.addParameter("username",u.getUsername());
        request.addParameter("email",u.getEmail());
        request.addParameter("nome",u.getCognome());
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo","");

        gestoreAccount.gestoreModificaUtenteByAdmin(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="ruolo non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestioneModificaUtenteByAdminUsernameErrato() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(3);

        request.getSession().setAttribute("utente",u);

        request.addParameter("id",String.valueOf(u.getId()));
        request.addParameter("username","@@@");
        request.addParameter("email",u.getEmail());
        request.addParameter("nome",u.getNome());
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtenteByAdmin(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="username formato non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestioneModificaUtenteByAdminEmailErrato() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(3);

        request.getSession().setAttribute("utente",u);

        request.addParameter("id",String.valueOf(u.getId()));
        request.addParameter("username",u.getUsername());
        request.addParameter("email","@@@");
        request.addParameter("nome",u.getNome());
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtenteByAdmin(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="email formato non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestioneModificaUtenteByAdminNomeErrato() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(3);

        request.getSession().setAttribute("utente",u);

        request.addParameter("id",String.valueOf(u.getId()));
        request.addParameter("username",u.getUsername());
        request.addParameter("email",u.getEmail());
        request.addParameter("nome","@@@");
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtenteByAdmin(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="nome formato non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestioneModificaUtenteByAdminCognomeErrato() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(3);

        request.getSession().setAttribute("utente",u);

        request.addParameter("id",String.valueOf(u.getId()));
        request.addParameter("username",u.getUsername());
        request.addParameter("email",u.getEmail());
        request.addParameter("nome",u.getCognome());
        request.addParameter("cognome","@@@");
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtenteByAdmin(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="cognome formato non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestioneModificaUtenteByAdminRuoloErrato() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(3);

        request.getSession().setAttribute("utente",u);

        request.addParameter("id",String.valueOf(u.getId()));
        request.addParameter("username",u.getUsername());
        request.addParameter("email",u.getEmail());
        request.addParameter("nome",u.getCognome());
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo","@@@");

        gestoreAccount.gestoreModificaUtenteByAdmin(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="ruolo formato non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestioneModificaUtenteByAdminIdVuoto() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(3);

        request.getSession().setAttribute("utente",u);

        request.addParameter("id","");
        request.addParameter("username",u.getUsername());
        request.addParameter("email",u.getEmail());
        request.addParameter("nome",u.getCognome());
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtenteByAdmin(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="id non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestioneModificaUtenteByAdminIdErrato() throws ServletException, IOException {

        UtenteDAO uDAO= new UtenteDAO();
        Utente u=uDAO.retriveById(3);

        request.getSession().setAttribute("utente",u);

        request.addParameter("id","@@@");
        request.addParameter("username",u.getUsername());
        request.addParameter("email",u.getEmail());
        request.addParameter("nome",u.getCognome());
        request.addParameter("cognome",u.getCognome());
        request.addParameter("ruolo",u.getRuolo());

        gestoreAccount.gestoreModificaUtenteByAdmin(request,response);
        String exit = (String)request.getAttribute("errorTest");
        String oracolo="id formato non corretto";
        assertEquals(oracolo,exit);
        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }


    @Test
    public void TC_GestoreMostraUtenti() throws ServletException, IOException {
        gestoreAccount.gestoreMostraUtenti(request,response);

        String oracolo="tutti gli utenti presi";
        String exit= (String) request.getAttribute("errorTest");
        assertEquals(oracolo,exit);

        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestoreTrovaUtenteByIDTrovato() throws ServletException, IOException {

        request.addParameter("id", String.valueOf(2));
        gestoreAccount.gestoreTrovaUtenteByID(request,response);

        String oracolo="utente trovato";
        String exit= (String) request.getAttribute("errorTest");
        assertEquals(oracolo,exit);

        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestoreTrovaUtenteByIDVuoto() throws ServletException, IOException {

        request.addParameter("id", "");
        gestoreAccount.gestoreTrovaUtenteByID(request,response);

        String oracolo="id non corretto";
        String exit= (String) request.getAttribute("errorTest");
        assertEquals(oracolo,exit);

        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }

    @Test
    public void TC_GestoreTrovaUtenteByIDNonTrovato() throws ServletException, IOException {

        request.addParameter("id", "-1");
        gestoreAccount.gestoreTrovaUtenteByID(request,response);

        String oracolo="utente non trovato";
        String exit= (String) request.getAttribute("errorTest");
        assertEquals(oracolo,exit);

        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }


    @Test
    public void TC_GestoreTrovaUtenteByIDErrato() throws ServletException, IOException {

        request.addParameter("id", "@@@");
        gestoreAccount.gestoreTrovaUtenteByID(request,response);

        String oracolo="id formato non corretto";
        String exit= (String) request.getAttribute("errorTest");
        assertEquals(oracolo,exit);

        System.out.println("oracolo: "+oracolo);
        System.out.println("servlet: "+exit);
    }


    @AfterEach
    void tearDown() throws Exception {
        request=null;
        response=null;
    }

}
