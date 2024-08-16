package rs.ac.bg.fon.nprog.so.termin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

class SOGetAllTerminTest extends AbstractSOTest{

	private SOGetAllTermin soGetAllTermin;
    private Termin termin;
    private ArrayList<AbstractDomainObject> mockResultList;

    @BeforeEach
    protected void setUp() throws Exception {
        super.setUp();
        soGetAllTermin = new SOGetAllTermin(dbb);

        termin = new Termin();
        termin.setTerminID(1L);

        mockResultList = new ArrayList<>();
        mockResultList.add(termin);
    }

    @Test
    void testValidateSuccess() {
        assertDoesNotThrow(() -> soGetAllTermin.templateExecute(termin));
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
            public String koloneZaInsert() { return null; }
            @Override
            public String uslov() { return null; }
            @Override
            public String vrednostiZaInsert() { return null; }
            @Override
            public String vrednostiZaUpdate() { return null; }
            @Override
            public String uslovZaSelect() { return null; }
			@Override
			public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
				return null;
			}
        };

        Exception exception = assertThrows(Exception.class, () -> soGetAllTermin.templateExecute(invalidObject));
        assertEquals("Prosledjeni objekat nije instanca klase Termin!", exception.getMessage());
    }

    

    
}
