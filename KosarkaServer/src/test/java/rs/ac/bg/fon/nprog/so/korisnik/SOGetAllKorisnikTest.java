package rs.ac.bg.fon.nprog.so.korisnik;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Tip;
import rs.ac.bg.fon.nprog.so.AbstractSOTest;

class SOGetAllKorisnikTest extends AbstractSOTest{

	private SOGetAllKorisnik soGetAllKorisnik;
    private DBBroker dbBroker;

    @BeforeEach
	protected
    void setUp() throws Exception {
        dbBroker = Mockito.mock(DBBroker.class);
        soGetAllKorisnik = new SOGetAllKorisnik(dbBroker);
    }

    @Test
    void testValidateNonKorisnikObject() {
        Exception exception = assertThrows(Exception.class, () -> soGetAllKorisnik.templateExecute((AbstractDomainObject) new Grad()));
        assertEquals("Prosledjeni objekat nije instanca klase Korisnik!", exception.getMessage());
    }

    @Test
    void testValidateKorisnikObject() {
        assertDoesNotThrow(() -> soGetAllKorisnik.templateExecute(new Korisnik()));
    }

    

    

}
