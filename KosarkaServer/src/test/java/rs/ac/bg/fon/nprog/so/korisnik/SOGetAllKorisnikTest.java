package rs.ac.bg.fon.nprog.so.korisnik;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Tip;
import rs.ac.bg.fon.nprog.so.AbstractSOTest;

class SOGetAllKorisnikTest extends AbstractSOTest{

	@InjectMocks
    private SOGetAllKorisnik soGetAllKorisnik;

    private Korisnik korisnik1;
    private Korisnik korisnik2;
    private ArrayList<Korisnik> korisnici;

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        korisnik1 = new Korisnik(1L, "Marko", "Markovic", "marko@example.com", "123456789", null);
        korisnik2 = new Korisnik(2L, "Jovan", "Jovanovic", "jovan@example.com", "987654321", null);
        korisnici = new ArrayList<>(Arrays.asList(korisnik1, korisnik2));
    }

    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
        soGetAllKorisnik = null;
    }

    @Test
    void testValidateSuccess() throws Exception {
        Korisnik korisnik = new Korisnik();
        assertDoesNotThrow(() -> soGetAllKorisnik.validate(korisnik));
    }

    @Test
    void testValidateFailure() {
        Grad grad = new Grad();
        Exception thrownException = assertThrows(Exception.class, () -> soGetAllKorisnik.validate(grad));
        assertEquals("Prosledjeni objekat nije instanca klase Korisnik!", thrownException.getMessage());
    }

   
    

    

}
