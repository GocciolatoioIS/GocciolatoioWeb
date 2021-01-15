package testUnita;

import bean.Indirizzo;
import bean.Utente;
import classDAO.UtenteDAO;
import junit.framework.TestCase;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//

public class TestUtente extends TestCase {

    private final UtenteDAO utenteDAO = new UtenteDAO();
    private Utente utenteNonEsistente;
    private Utente utenteEsistente;

    @BeforeEach
    public void setUp(){

        //Creazione Utente non esistente
        List<Indirizzo> indirizzoListNon=new ArrayList<>();
        Indirizzo indirizzoNonEsistente= new Indirizzo(1,"GenericaCitta", 9000, "GenericaVia", 01, "IT");
        indirizzoListNon.add(indirizzoNonEsistente);
        utenteNonEsistente=new Utente(1,"nonEsisto@live.it","nonEsisto","nonEsisto","utente","Nome","Cognome",new Date("01/01/1980"),indirizzoListNon);
        utenteDAO.doSave(utenteNonEsistente);

        //Creazione Utente esistente

    }


    @AfterEach
    public void tearDown(){

        utenteDAO.deleteUser(utenteNonEsistente.getId());

    }




    @Test
    public void testRetriveAll(){

        List<Utente> ul=new ArrayList<>();
        assertNotEquals(ul,utenteDAO.retriveAll());

    }

    @Test
    public void doRetrieveByUsernamePassword(){


    }

}
