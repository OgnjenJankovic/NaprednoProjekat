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
import rs.ac.bg.fon.nprog.domain.Igrac;
import rs.ac.bg.fon.nprog.domain.Termin;

class SOUpdateTerminTest {

	private SOUpdateTermin soUpdateTermin;
    private DBBroker dbBroker;
    private Termin termin;
    private ArrayList<Igrac> igraci;

    @BeforeEach
    public void setUp() {
        soUpdateTermin = new SOUpdateTermin();
        dbBroker = mock(DBBroker.class);

        when(DBBroker.getInstance()).thenReturn(dbBroker);

        termin = new Termin();
        igraci = new ArrayList<>();
        igraci.add(new Igrac()); 
        igraci.add(new Igrac());
        termin.setIgraci(igraci);
    }

    @Test
    public void testValidateValidTermin() throws Exception {
        termin.setBrojSati(2);
        assertDoesNotThrow(() -> soUpdateTermin.validate(termin));
    }

    @Test
    public void testValidateInvalidTermin() {
        termin.setBrojSati(0); 
        Exception exception = assertThrows(Exception.class, () -> soUpdateTermin.validate(termin));
        assertEquals("Morate izracunati broj sati i ukupnu cenu!", exception.getMessage());
    }

    @Test
    public void testExecute() throws Exception {
        soUpdateTermin.execute(termin);

        verify(dbBroker, times(1)).update(termin);

        verify(dbBroker, times(1)).delete(igraci.get(0));

        verify(dbBroker, times(2)).insert(any(Igrac.class));
    }

}
