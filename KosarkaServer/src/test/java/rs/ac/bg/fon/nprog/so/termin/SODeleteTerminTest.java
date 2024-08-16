package rs.ac.bg.fon.nprog.so.termin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Termin;
import rs.ac.bg.fon.nprog.so.AbstractSOTest;

class SODeleteTerminTest extends AbstractSOTest{

	private SODeleteTermin soDeleteTermin;
    private Termin termin;
    
    @BeforeEach
    protected void setUp() throws Exception {
        super.setUp();
        soDeleteTermin = new SODeleteTermin(dbb);
        
        termin = new Termin();
        termin.setTerminID(1L); 
    }

    @Test
    void testValidateSuccess() {
        assertDoesNotThrow(() -> soDeleteTermin.templateExecute(termin));
    }

    @Test
    void testValidateFailureInvalidObject() {
        AbstractDomainObject invalidObject = new AbstractDomainObject() {
            @Override
            public String nazivTabele() { return null; }
            @Override
            public String alijas() { return null; }
            @Override
            public String join() { return null; }
            @Override
            public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException { return null; }
            @Override
            public String koloneZaInsert() { return null; }
            @Override
            public String uslov() { return null; }
            @Override
            public String vrednostiZaInsert() { return null; }
            @Override
            public String vrednostiZaUpdate() { return null; }
            @Override
            public String uslovZaSelect() { return null; }
        };

        Exception exception = assertThrows(Exception.class, () -> soDeleteTermin.templateExecute(invalidObject));
        assertEquals("Prosledjeni objekat nije instanca klase Termin!", exception.getMessage());
    }

    

    

}
