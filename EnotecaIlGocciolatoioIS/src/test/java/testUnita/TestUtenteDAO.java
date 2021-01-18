package testUnita;

import model.bean.Indirizzo;
import model.bean.Utente;
import classDAO.UtenteDAO;
import junit.framework.TestCase;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//Test su UtenteDao

public class TestUtenteDAO extends TestCase {

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
        utenteDAO.deleteUser(utenteNonEsistente.getId());
    }


    @Test
    public void testRetriveAll(){

        assertNotNull(utenteDAO.retriveAll());

    }

    @Test
    public void testDoRetrieveByUsernameEmail() {
        String username=utenteEsistente.getUsername();
        String email=utenteEsistente.getEmail();
        assertNotNull( utenteDAO.doRetrieveByUsernameEmail(username,email));
    }


    @Test
    public void TestCreazioneCancellazioneUtente(){

        List<Indirizzo> indirizzoListEssist2=new ArrayList<>();
        Indirizzo indirizzoEsistente2= new Indirizzo(2,"Napoli", 9000, "Via Grande", 02, "IT");
        indirizzoListEssist2.add(indirizzoEsistente2);
        Utente utenteEsistente2=new Utente(1,"esisto2@live.it","esisto2","esisto2","utente2","Mario2","Rossi2",new Date("01/01/1980"),indirizzoListEssist2);
        assertEquals(1,utenteDAO.doSave(utenteEsistente2));
        assertEquals(1,utenteDAO.deleteUser(utenteEsistente2.getId()));

    }

    @Test
    public void TestdoUpdate(){

        utenteEsistente.setNome("nomeMod");
        assertEquals(1,utenteDAO.doUpdate(utenteEsistente));

    }

    @Test
    public void TestretriveById(){
        assertNotNull(utenteDAO.retriveById(utenteEsistente.getId()));
    }

    @Test
    public void TestdeRetriveUsername(){
        assertNotNull(utenteDAO.deRetriveUsername(utenteEsistente.getUsername()));
    }

    @Test
    public void TestdoRetrieveByEmail(){
        assertNotNull(utenteDAO.doRetrieveByEmail(utenteEsistente.getEmail()));
    }



    @AfterEach
    public void tearDown(){
        utenteDAO.deleteUser(utenteNonEsistente.getId());
        utenteDAO.deleteUser(utenteEsistente.getId());
    }

}
