package rs.ac.bg.fon.nprog.so.termin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Termin;
import rs.ac.bg.fon.nprog.so.AbstractSOTest;

class SODeleteTerminTest extends AbstractSOTest{

	@InjectMocks
    private SODeleteTermin soDeleteTermin;

    @Mock
    private DBBroker dbBrokerMock;

    @BeforeEach
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        soDeleteTermin = new SODeleteTermin(dbBrokerMock);
    }

    @AfterEach
    protected void tearDown() throws Exception {
        super.tearDown();
        soDeleteTermin = null;
    }

    @Test
    void testValidateSuccess() throws Exception {
        Termin termin = new Termin(); 
        assertDoesNotThrow(() -> soDeleteTermin.validate(termin));
    }

    @Test
    void testValidateFailure() {
        Grad grad = new Grad(); 
        Exception exception = assertThrows(Exception.class, () -> soDeleteTermin.validate(grad));
        assertEquals("Prosledjeni objekat nije instanca klase Termin!", exception.getMessage());
    }

    
    

    

}
