package rs.ac.bg.fon.nprog.so.termin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Termin;

class SOGetAllTerminTest {

	private SOGetAllTermin soGetAllTermin;
    private DBBroker dbBroker;
    private Termin termin;

    @BeforeEach
    public void setUp() {
        soGetAllTermin = new SOGetAllTermin();
        dbBroker = mock(DBBroker.class);

        when(DBBroker.getInstance()).thenReturn(dbBroker);

        termin = new Termin();
    }

    @Test
    public void testValidateValidTermin() throws Exception {
        assertDoesNotThrow(() -> soGetAllTermin.validate(termin));
    }

    @Test
    public void testValidateNullTermin() {
        Exception exception = assertThrows(Exception.class, () -> soGetAllTermin.validate(null));
        assertEquals("Termin objekat ne sme biti null!", exception.getMessage());
    }

    @Test
    public void testExecute() throws Exception {
        ArrayList<AbstractDomainObject> mockTermini = new ArrayList<>();
        mockTermini.add(termin);

        when(dbBroker.select(any(Termin.class))).thenReturn(mockTermini);

        soGetAllTermin.execute(termin);

        verify(dbBroker, times(1)).select(termin);

        assertEquals(1, soGetAllTermin.getLista().size());
        assertEquals(termin, soGetAllTermin.getLista().get(0));
    }
}
