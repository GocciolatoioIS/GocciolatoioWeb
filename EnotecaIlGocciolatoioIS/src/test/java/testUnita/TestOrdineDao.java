package testUnita;

import bean.Indirizzo;
import bean.Ordine;
import bean.Utente;
import classDAO.IndirizzoDAO;
import classDAO.OrdineDAO;
import classDAO.UtenteDAO;
import junit.framework.TestCase;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/*
 * Test per OrdineDao
 */
public class TestOrdineDao extends TestCase{

    private final OrdineDAO ordineDAO=new OrdineDAO();
    private final IndirizzoDAO indirizzoDAO= new IndirizzoDAO();
    private final UtenteDAO utenteDAO= new UtenteDAO();
    private Ordine ordineEsistente;
    private Ordine ordineNonEsistente;
    private Indirizzo indirizzoEsistente;
    private Date dataEsistente;
    private Utente utenteEsistente;
    private Indirizzo indirizzoNonEsistente;
    private Date dataNonEsistente;
    private Utente utenteNonEsistente;

    @BeforeEach
    public void setUp() throws ParseException {
        //OrdineEsistente
        String sDate="31/12/1998";
        utenteEsistente=new Utente(1,"esisto@live.it","esisto","esisto","utente","Mario","Rossi",new Date("01/01/1980"),null);
        utenteDAO.doSave(utenteEsistente);
        dataEsistente= new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
        ordineEsistente= new Ordine(99,utenteEsistente.getId(),99,dataEsistente,null,null);
        indirizzoEsistente= new Indirizzo(-1,"Napoli",9000,"Via Grande",1,"IT");
        int id= ordineDAO.addAddressToOrder(indirizzoEsistente);
        indirizzoEsistente.setId(id);
        ordineDAO.doSave(ordineEsistente,id);
        ordineEsistente.setId_ordine(ordineEsistente.getId_ordine());
        //OrdineNonEsistente
        String sDate2="31/12/1998";
        utenteNonEsistente=new Utente(1,"Nonesisto@live.it","Nonesisto","Nonesisto","utente","Mario","Rossi",new Date("01/01/1980"),null);
        dataNonEsistente= new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
        ordineNonEsistente= new Ordine(99,utenteEsistente.getId(),99,dataEsistente,null,null);
        indirizzoNonEsistente= new Indirizzo(-1,"Napoli",9000,"Via Grande",1,"IT");
        ordineNonEsistente.setId_ordine(ordineEsistente.getId_ordine());


    }

    @Test
    public void testRetriveAll(){
        assertNotNull(ordineDAO.retriveAll());
    }

    @Test
    public void testRetriveByIdUser(){
        assertNotNull(ordineDAO.retriveByIdUser(2));
    }

    @Test
    public void testSalvataggioAddIndirizzoCancellazioneOrdine(){

        int id= ordineDAO.addAddressToOrder(indirizzoNonEsistente);
        utenteDAO.doSave(utenteNonEsistente);
        indirizzoNonEsistente.setId(id);
        ordineDAO.doSave(ordineNonEsistente,id);
        ordineDAO.deleteOrder(ordineNonEsistente.getId_ordine());
    }



    @AfterEach
    public void tearDown(){
        System.out.println(ordineEsistente.getId_ordine());
        ordineDAO.deleteOrder(ordineEsistente.getId_ordine());
        ordineDAO.removeAddressOrder(indirizzoEsistente.getId());
        utenteDAO.deleteUser(utenteEsistente.getId());

    }


}
