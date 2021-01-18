package testUnita;



import model.bean.Prodotto;
import classDAO.ProdottoDAO;
import junit.framework.TestCase;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


/*
* Test ProdottoDAO
*/

public class TestProdottoDAO extends TestCase {

    private final ProdottoDAO prodottoDAO = new ProdottoDAO();
    private Prodotto prodottoEsistente;
    private Prodotto prodottoNonEsistente;

    @BeforeEach
    public void setUp(){
        //ProdottoEsistente
        prodottoEsistente= new Prodotto(-1,"NomeProd","Bianco","DescrizioneProd",10,0,"img.jpg",2010,"Campania",10,10,10,"Vino");
        prodottoDAO.doSave(prodottoEsistente);

        //ProdottoNonEsistente
        prodottoNonEsistente= new Prodotto(-1,"NonEsistente","Nessuno","DescrizioneNonEsistente",10,0,"img.jpg",2010,"Campania",10,10,10,"Acqua");

    }
    @Test
    public void testRetriveOneEsistente(){
        assertNotNull(prodottoDAO.retriveOne(prodottoEsistente.getId()));

    }

    @Test
    public void testRetriveOneNonEsistente(){
        assertNull(prodottoDAO.retriveOne(prodottoNonEsistente.getId()));
    }

    @Test
    public void testRetriveQuantMagazinoEsistente(){
        assertEquals(prodottoEsistente.getQuantita_magazzino(),prodottoDAO.retriveQuant(prodottoEsistente.getId()));
    }

    @Test
    public void testRetriveQuantMagazinoNonEsistente(){
        assertEquals(0,prodottoDAO.retriveQuant(prodottoNonEsistente.getId()));
    }

    @Test
    public void testRetriveBySearchEsistente(){
        assertNotNull(prodottoDAO.retriveBySearch(prodottoEsistente.getNome()));

    }

    @Test
    public void testRetriveAll(){
        assertNotNull(prodottoDAO.retriveAll());
    }

    @Test
    public void testRetriveCategoryEsistente(){
        assertNotNull(prodottoDAO.retriveCategory(prodottoEsistente.getNome_categoria()));
    }

    @Test
    public void testRetriveCategoryNonEsistente(){
        ArrayList<Prodotto> list = new ArrayList<>(); //arrayListVuoto
        assertEquals(list,prodottoDAO.retriveCategory(prodottoNonEsistente.getNome_categoria()));
    }

    @Test
    public void testSalvataggioRimozioneProdotto(){

        Prodotto prodottoEsistente2= new Prodotto(-1,"NomeProd2","Bianco","DescrizioneProd2",10,0,"img.jpg",2010,"Campania",10,10,10,"Vino");
        assertEquals(1,prodottoDAO.doSave(prodottoEsistente2));
        assertEquals(1,prodottoDAO.deleteProduct(prodottoEsistente2.getId()));
    }

    @Test
    public void testDoUpdateQuantityEsistente(){
        assertEquals(1,prodottoDAO.doUpdateQuantity(prodottoEsistente.getId(),prodottoEsistente.getQuantita_magazzino()));
    }


    @Test
    public void testDoUpdate(){

        prodottoEsistente.setNome("NuovoNome");
        assertEquals(1,prodottoDAO.doUpdate(prodottoEsistente));

    }


    @AfterEach
    public void tearDown(){

        prodottoDAO.deleteProduct(prodottoEsistente.getId());
    }

}
