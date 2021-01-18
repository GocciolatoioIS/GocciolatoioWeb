package testUnita;

import classDAO.CategoriaDAO;
import junit.framework.TestCase;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
