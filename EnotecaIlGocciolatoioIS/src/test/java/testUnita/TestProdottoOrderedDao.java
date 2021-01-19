package testUnita;
import model.bean.ProductOrdered;
import classDAO.ProductOrderedDao;
import junit.framework.TestCase;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestProdottoOrderedDao extends  TestCase{

    private final ProductOrderedDao productOrderedDao= new ProductOrderedDao();
    private ProductOrdered productOrderedEsistente;
    private ProductOrdered productOrderedNonEsistente;

    @BeforeEach
    public void setUp(){
        //ProdottoOrdinatoEsistente
        productOrderedEsistente= new ProductOrdered(-1,"NomeProd","Bianco","DescrizioneProd",10,0,"img.jpg","vino",2010,"campania",10,10,10,99);
        productOrderedDao.doSave(productOrderedEsistente,productOrderedEsistente.getId_ordine());

        //ProdottoOrdinatoNonEsistente
        productOrderedNonEsistente= new ProductOrdered(-1,"NomeProdNonEsistente","NonEsistente","DescrizioneProd",10,0,"img.jpg","vino",2010,"campania",10,10,10,150);


    }

    @Test
    public void testRetriveByOrderIdEsistente(){
        assertNotNull(productOrderedDao.retriveByOrderId(productOrderedEsistente.getId_ordine()));
    }

    @Test
    public void testRetriveByOrderIdNonEsistente(){
        ArrayList<ProductOrdered> list = new ArrayList<>();
        assertEquals(list,productOrderedDao.retriveByOrderId(productOrderedNonEsistente.getId_ordine()));
    }

    @AfterEach
    public void tearDown(){

        productOrderedDao.deleteProductOrdered(productOrderedEsistente.getId());
    }
}
