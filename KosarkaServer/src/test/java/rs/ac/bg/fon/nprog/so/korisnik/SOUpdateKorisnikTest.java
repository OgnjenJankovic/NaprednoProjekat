package rs.ac.bg.fon.nprog.so.korisnik;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Tip;

class SOUpdateKorisnikTest {

	private SOUpdateKorisnik soUpdateKorisnik;
    private DBBroker dbBroker;

    @BeforeEach
    public void setUp() {
        soUpdateKorisnik = new SOUpdateKorisnik();
        dbBroker = mock(DBBroker.class);
        when(DBBroker.getInstance()).thenReturn(dbBroker);
    }

    @Test
    public void testValidateValidInstance() throws Exception {
        Tip tip = new Tip(1L, "Free");
        Korisnik korisnik = new Korisnik(1L, "Ognjen", "Jankovic", "ogi@gmail.com", "0621095131", tip);
        
        when(dbBroker.select(any(Korisnik.class))).thenReturn(new ArrayList<>());

        assertDoesNotThrow(() -> soUpdateKorisnik.validate(korisnik));
    }

    @Test
    public void testValidateDuplicateEmail() throws SQLException {
        Tip tip = new Tip(1L, "Free");
        Korisnik existingKorisnik = new Korisnik(2L, "Ognjen2", "Jankovic2", "ogi@gmail.com", "0621095131", tip);
        Korisnik korisnik = new Korisnik(1L, "Ognjen", "Jankovic", "ogi@gmail.com", "0621095131", tip);
        
        ArrayList<AbstractDomainObject> existingList = new ArrayList<>();
        existingList.add(existingKorisnik);
        when(dbBroker.select(any(Korisnik.class))).thenReturn(existingList);

        Exception exception = assertThrows(Exception.class, () -> soUpdateKorisnik.validate(korisnik));
        assertEquals("Korisnik sa tim emailom vec postoji!", exception.getMessage());
    }

    @Test
    public void testValidateDuplicatePhone() throws SQLException {
        Tip tip = new Tip(1L, "Free");
        Korisnik existingKorisnik = new Korisnik(2L, "Ognjen2", "Jankovic2", "ogi@gmail.com", "0621095131", tip);
        Korisnik korisnik = new Korisnik(1L, "Ognjen", "Jankovic", "ogi@gmail.com", "0621095131", tip);
        
        ArrayList<AbstractDomainObject> existingList = new ArrayList<>();
        existingList.add(existingKorisnik);
        when(dbBroker.select(any(Korisnik.class))).thenReturn(existingList);

        Exception exception = assertThrows(Exception.class, () -> soUpdateKorisnik.validate(korisnik));
        assertEquals("Korisnik sa tim telefonom vec postoji!", exception.getMessage());
    }

    @Test
    public void testExecute() throws Exception {
        Tip tip = new Tip(1L, "Tip");
        Korisnik korisnik = new Korisnik(1L, "Ime", "Prezime", "email@example.com", "123456789", tip);

        soUpdateKorisnik.execute(korisnik);

        verify(dbBroker).update(korisnik);
    }

}
