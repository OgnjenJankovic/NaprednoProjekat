package rs.ac.bg.fon.nprog.so.termin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.Termin;

class SODeleteTerminTest {

	private SODeleteTermin soDeleteTermin;
    private DBBroker dbBroker;
    private Termin termin;

    @BeforeEach
    public void setUp() {
        soDeleteTermin = new SODeleteTermin();
        dbBroker = mock(DBBroker.class);

        when(DBBroker.getInstance()).thenReturn(dbBroker);

        termin = new Termin(1L, null, null, 0, 0, null, null, null, null);
    }

    @Test
    public void testValidateValidTermin() throws Exception {
        assertDoesNotThrow(() -> soDeleteTermin.validate(termin));
    }

    @Test
    public void testValidateNullTermin() {
        Exception exception = assertThrows(Exception.class, () -> soDeleteTermin.validate(null));
        assertEquals("Termin objekat ne sme biti null!", exception.getMessage());
    }

    @Test
    public void testExecute() throws Exception {
        soDeleteTermin.execute(termin);

        verify(dbBroker, times(1)).delete(termin);
    }

}
