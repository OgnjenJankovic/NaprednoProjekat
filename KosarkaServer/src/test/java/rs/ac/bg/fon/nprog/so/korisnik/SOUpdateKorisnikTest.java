package rs.ac.bg.fon.nprog.so.korisnik;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Tip;
import rs.ac.bg.fon.nprog.so.AbstractSOTest;

class SOUpdateKorisnikTest extends AbstractSOTest{

	private SOUpdateKorisnik soUpdateKorisnik;

    @BeforeEach
    protected void setUp() throws Exception {
        super.setUp();
        soUpdateKorisnik = new SOUpdateKorisnik(dbb);
    }

    @AfterEach
    protected void tearDown() throws Exception {
        super.tearDown();
        soUpdateKorisnik = null;
    }

    @Test
    void testValidateSuccess() throws Exception {
        Korisnik k = new Korisnik(1L, "Ognjen", "Jankovic", "ogi@gmail.com", "0631231234", new Tip(1L, "Premium"));
        ArrayList<AbstractDomainObject> existingUsers = new ArrayList<>();
        existingUsers.add(k);

        when(dbb.select(any(Korisnik.class))).thenReturn(existingUsers);

        assertDoesNotThrow(() -> soUpdateKorisnik.validate(k));
    }
    
    @Test
    void testValidateFailure() {
        Grad grad = new Grad();
        Exception thrownException = assertThrows(Exception.class, () -> soUpdateKorisnik.validate(grad));
        assertEquals("Prosledjeni objekat nije instanca klase Korisnik!", thrownException.getMessage());
    }

    @Test
    void testValidateEmailConflict() throws Exception {
        Korisnik k = new Korisnik(1L, "Ognjen", "Jankovic", "veljko@gmail.com", "0631231234", new Tip(1L, "Premium"));
        ArrayList<AbstractDomainObject> existingUsers = new ArrayList<>();
        existingUsers.add(new Korisnik(2L, "Veljko", "Nikolic", "veljko@gmail.com", "0632232234", new Tip(2L, "Free")));
        
        when(dbb.select(any(Korisnik.class))).thenReturn(existingUsers);

        Exception thrownException = assertThrows(Exception.class, () -> soUpdateKorisnik.validate(k));
        assertEquals("Korisnik sa tim emailom vec postoji!", thrownException.getMessage());
    }

    @Test
    void testValidateTelefonConflict() throws Exception {
        Korisnik k = new Korisnik(1L, "Ognjen", "Jankovic", "ogi@gmail.com", "0654645434", new Tip(1L, "Premium"));
        ArrayList<AbstractDomainObject> existingUsers = new ArrayList<>();
        existingUsers.add(new Korisnik(2L, "Veljko", "Nikolic", "veljko@gmail.com", "0654645434", new Tip(2L, "Free")));

        when(dbb.select(any(Korisnik.class))).thenReturn(existingUsers);

        Exception thrownException = assertThrows(Exception.class, () -> soUpdateKorisnik.validate(k));
        assertEquals("Korisnik sa tim telefonom vec postoji!", thrownException.getMessage());
    }

    
    

   
}
