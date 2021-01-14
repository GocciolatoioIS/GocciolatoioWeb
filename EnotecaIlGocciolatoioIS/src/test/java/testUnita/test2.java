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

public class test2 extends TestCase {

    private final UtenteDAO utenteDAO = new UtenteDAO();
    private Utente utente;

    @BeforeEach
    public void setUp(){
        List<Indirizzo> ind=new ArrayList<>();
        Indirizzo i= new Indirizzo(1,"sdfa", 9080, "String via", 34, "IT");
        ind.add(i);
        utente=new Utente(1,"ciaox19x@live.it","Xxthedmg8xX","zipep","utente","Franco","Giuseppe",new Date("01/02/1996"),ind);
        System.out.println("-----");
        utenteDAO.doSave(utente);

        System.out.println(utente.getId());
    }


    @AfterEach
    public void tearDown(){

        utenteDAO.deleteUser(utente.getId());

    }



    @Test
    public void testComVuoTU(){

        List<Utente> ul=new ArrayList<>();

        assertNotEquals(ul,utenteDAO.retriveAll());


    }

}
