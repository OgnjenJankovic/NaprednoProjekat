package rs.ac.bg.fon.nprog.so.korisnik;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Tip;

class SOAddKorisnikTest {

	private SOAddKorisnik soAddKorisnik;
    private Korisnik korisnik;
    private DBBroker dbBroker;

    @BeforeEach
    void setUp() {
        soAddKorisnik = new SOAddKorisnik();
        korisnik = new Korisnik(1L, "Ognjen", "Jankovic", "ogi@email.com", "0621095131", new Tip(1L, "Free"));
        dbBroker = Mockito.mock(DBBroker.class);

        Mockito.mockStatic(DBBroker.class);
        Mockito.when(DBBroker.getInstance()).thenReturn(dbBroker);
    }

    @AfterEach
    void tearDown() {
        soAddKorisnik = null;
        korisnik = null;
        dbBroker = null;
    }

    @Test
    void testValidate_Success() throws Exception {
        ArrayList<AbstractDomainObject> existingKorisnici = new ArrayList<>();

        Mockito.when(dbBroker.select(Mockito.any(AbstractDomainObject.class))).thenReturn(existingKorisnici);

        assertDoesNotThrow(() -> soAddKorisnik.validate(korisnik));
    }

    @Test
    void testValidate_EmailExists() throws Exception {
        Korisnik existingKorisnik = new Korisnik(2L, "Ognjen2", "Jankovic2", "ogi@gmail.com", "0621095131", new Tip(2L, "Free"));
        ArrayList<AbstractDomainObject> existingKorisnici = new ArrayList<>();
        existingKorisnici.add(existingKorisnik);

        Mockito.when(dbBroker.select(Mockito.any(AbstractDomainObject.class))).thenReturn(existingKorisnici);

        Exception exception = assertThrows(Exception.class, () -> soAddKorisnik.validate(korisnik));
        assertEquals("Korisnik sa tim emailom vec postoji!", exception.getMessage());
    }

    @Test
    void testValidate_TelefonExists() throws Exception {
    	Korisnik existingKorisnik = new Korisnik(2L, "Ognjen2", "Jankovic2", "ogi@gmail.com", "0621095131", new Tip(2L, "Free"));
        ArrayList<AbstractDomainObject> existingKorisnici = new ArrayList<>();
        existingKorisnici.add(existingKorisnik);

        Mockito.when(dbBroker.select(Mockito.any(AbstractDomainObject.class))).thenReturn(existingKorisnici);

        Exception exception = assertThrows(Exception.class, () -> soAddKorisnik.validate(korisnik));
        assertEquals("Korisnik sa tim telefonom vec postoji!", exception.getMessage());
    }
    
    @Test
    void testExecute_Success() throws Exception {
        Mockito.doNothing().when(dbBroker).insert(Mockito.any(AbstractDomainObject.class));

        assertDoesNotThrow(() -> soAddKorisnik.execute(korisnik));

        Mockito.verify(dbBroker, Mockito.times(1)).insert(Mockito.eq(korisnik));
    }
    
    @Test
    void testExecute_InsertFails() throws Exception {
        Mockito.doThrow(new RuntimeException("Insert failed")).when(dbBroker).insert(Mockito.any(AbstractDomainObject.class));

        Exception exception = assertThrows(Exception.class, () -> soAddKorisnik.execute(korisnik));
        assertEquals("Insert failed", exception.getCause().getMessage());
    }

}
