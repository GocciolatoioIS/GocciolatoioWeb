package testUnita;


import classDAO.IndirizzoDAO;
import bean.Indirizzo;
import junit.framework.TestCase;

import org.junit.jupiter.api.*;

import static org.junit.Assert.assertNotEquals;

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
import java.lang.Exception;

public class TestIndirizzoDAO extends TestCase {

    private IndirizzoDAO indirizzoDAO=new IndirizzoDAO();
    private Indirizzo indirizzoNonEsistente;
    private Indirizzo indirizzoEsistente;


    @BeforeEach
    protected void setUp() {
        //indirizzo non esistente
        indirizzoNonEsistente = new Indirizzo(1, "Ischia", 80067, "via San Francesco", 18, "Italia");
        indirizzoDAO.doSave(indirizzoNonEsistente, indirizzoNonEsistente.getId());

        //indirizzo esistente
        indirizzoEsistente = new Indirizzo(5, "Marigliano", 80034, "via Clemente Mastrisciano", 54, "Italia");
        indirizzoDAO.doSave(indirizzoEsistente, indirizzoEsistente.getId());
    }


    @Test
    public void testDoSaveIndirizzoEsistente(){

        assertNotNull(indirizzoDAO.doSave(indirizzoEsistente, indirizzoEsistente.getId()));
    }


    @Test
    public void testDoSaveIndirizzoNonEsistente(){
        assertNotNull(indirizzoDAO.doSave(indirizzoNonEsistente,indirizzoNonEsistente.getId()));
    }

    @Test
   public void testRetriveAllByIdEsistente() {
        Indirizzo i= new Indirizzo();
        assertNotEquals(i, indirizzoDAO.retriveAllbyID(indirizzoEsistente.getId()));
    }


    @Test
    public void testRemoveIndirizzoEsistente(){
        Indirizzo i=new Indirizzo(100, "Procida", 80068, "via del mare", 30, "Italia");
        assertNotEquals(0,indirizzoDAO.deleteIndirizzo(indirizzoEsistente.getId()));
    }



    @Test
    public void testRetriveByIDEsistente(){
        assertEquals(indirizzoEsistente,indirizzoDAO.retriveByID(indirizzoEsistente.getId())); //?????????????

    }

    @Test
    public void testRetriveByIDNonEsistente(){
        Indirizzo i = new Indirizzo();
        assertNotEquals(i, indirizzoDAO.retriveByID(indirizzoEsistente.getId()));
    }

    @Test
    public void testRetriveIndirizzoOrdineByIDEsistente(){
        Indirizzo i= new Indirizzo();
        assertNotEquals(i,indirizzoDAO.retriveByID(indirizzoEsistente.getId()));
    }

    @AfterEach
    protected void tearDown(){
        indirizzoDAO.deleteIndirizzo(indirizzoEsistente.getId());
        indirizzoDAO.deleteIndirizzo(indirizzoNonEsistente.getId());
    }

}


