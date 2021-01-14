package testIntegrazione;

import static org.junit.jupiter.api.Assertions.*;

import model.GestoreAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

public class TestGestoreAccount extends Mockito{

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    public final GestoreAccount gestoreAccount = new GestoreAccount();

    @BeforeEach
    void setUp() throws Exception {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void TC_AggiungiUtente() throws ServletException, IOException {

        //aggiungere ai request i parametri
        request.addParameter("nome","Giovanni");
        request.addParameter("cognome","");

        String message = "il cognome non è vuoto";

        gestoreAccount.gestioneRegistrazione(request, response);
        String result = (String) request.getAttribute("errorTest");
        assertEquals(message, result);
    }

    @AfterEach
    void tearDown() throws Exception {
        request=null;
        response=null;
    }

}
