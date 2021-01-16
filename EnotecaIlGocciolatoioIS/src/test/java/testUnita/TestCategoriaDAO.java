package testUnita;

import bean.Indirizzo;
import bean.Ordine;
import bean.Utente;
import classDAO.CategoriaDAO;
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
 * Test per CategoriaDao
 */
public class TestCategoriaDAO extends TestCase{

    private final CategoriaDAO categoriaDAO=new CategoriaDAO();

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void testDoRetriveAllCategoria(){
        assertNotNull(categoriaDAO.retriveAll());
    }

    @AfterEach
    public void tearDown(){

    }

}
