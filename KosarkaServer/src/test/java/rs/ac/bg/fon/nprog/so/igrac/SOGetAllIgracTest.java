package rs.ac.bg.fon.nprog.so.igrac;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Igrac;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Termin;

class SOGetAllIgracTest {

	private SOGetAllIgrac soGetAllIgrac;
    private Termin mockTermin;
    private Korisnik mockKorisnik;
    private Igrac mockIgrac;
    private DBBroker dbBroker;
    private Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        soGetAllIgrac = new SOGetAllIgrac();
        mockTermin = Mockito.mock(Termin.class);
        mockKorisnik = Mockito.mock(Korisnik.class);
        mockIgrac = new Igrac(mockTermin, 1, "Test Napomena", mockKorisnik);
        dbBroker = Mockito.mock(DBBroker.class);
        connection = Mockito.mock(Connection.class);

        Mockito.mockStatic(DBBroker.class);
        Mockito.when(DBBroker.getInstance()).thenReturn(dbBroker);

        Mockito.when(dbBroker.getConnection()).thenReturn(connection);
    }

    @AfterEach
    void tearDown() {
        soGetAllIgrac = null;
        mockTermin = null;
        mockKorisnik = null;
        mockIgrac = null;
        dbBroker = null;
        connection = null;
    }

    @Test
    void testValidate_Success() {
        assertDoesNotThrow(() -> soGetAllIgrac.validate(mockIgrac));
    }

    @Test
    void testValidate_Failure() {
        AbstractDomainObject notIgrac = new Grad(); 
        Exception exception = assertThrows(Exception.class, () -> soGetAllIgrac.validate(notIgrac));
        assertEquals("Prosledjeni objekat nije instanca klase Igrac!", exception.getMessage());
    }

    @Test
    void testExecute_Success() throws Exception {
        ArrayList<AbstractDomainObject> igraci = new ArrayList<>();
        igraci.add(mockIgrac);
        
        Mockito.when(dbBroker.select(mockIgrac)).thenReturn(igraci);

        soGetAllIgrac.execute(mockIgrac);

        ArrayList<Igrac> resultList = soGetAllIgrac.getLista();
        assertEquals(1, resultList.size());
        Igrac resultIgrac = resultList.get(0);
        assertEquals(mockTermin, resultIgrac.getTermin());
        assertEquals(1, resultIgrac.getRbIgraca());
        assertEquals("Test Napomena", resultIgrac.getNapomena());
        assertEquals(mockKorisnik, resultIgrac.getKorisnikIgrac());
    }

    @Test
    void testExecute_Failure() throws Exception {
        Mockito.when(dbBroker.select(mockIgrac)).thenThrow(new Exception("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soGetAllIgrac.execute(mockIgrac));
        assertEquals("Database error", exception.getMessage());
    }

}
