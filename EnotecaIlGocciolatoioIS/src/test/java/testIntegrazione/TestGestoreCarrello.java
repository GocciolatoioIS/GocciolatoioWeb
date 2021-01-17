package testIntegrazione;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import classDAO.UtenteDAO;
import classDAO.IndirizzoDAO;
import bean.Utente;
import bean.Indirizzo;
import bean.Ordine;
import classDAO.OrdineDAO;
import model.GestoreCarrello;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestGestoreCarrello extends Mockito {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @InjectMocks
    public GestoreCarrello gestorecarrello;

}
