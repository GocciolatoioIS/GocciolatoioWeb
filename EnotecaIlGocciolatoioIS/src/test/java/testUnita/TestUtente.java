package testUnita;

import bean.Indirizzo;
import bean.Utente;
import classDAO.UtenteDAO;
import connectionPool.ConPool;
import junit.framework.TestCase;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//Test su UtenteDao

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
        List<Indirizzo> indirizzoListEssist=new ArrayList<>();
        Indirizzo indirizzoEsistente= new Indirizzo(2,"Napoli", 9000, "Via Grande", 01, "IT");
        indirizzoListEssist.add(indirizzoEsistente);
        utenteEsistente=new Utente(1,"esisto@live.it","esisto","esisto","utente","Mario","Rossi",new Date("01/01/1980"),indirizzoListEssist);
        utenteDAO.doSave(utenteEsistente);
    }






    @Test
    public void testRetriveAll(){

        List<Utente> ul=new ArrayList<>();
        assertNotEquals(ul,utenteDAO.retriveAll());

    }

    @Test
    public void testDoRetrieveByUsernameEmail() {
        String username=utenteEsistente.getUsername();
        String email=utenteEsistente.getEmail();
        assertNotNull( utenteDAO.doRetrieveByUsernameEmail(username,email));
    }

    @Test
    public void testDoRetrieveByUsernamePassword(){




    }



    @AfterEach
    public void tearDown(){
        utenteDAO.deleteUser(utenteNonEsistente.getId());
        utenteDAO.deleteUser(utenteEsistente.getId());
    }

}
