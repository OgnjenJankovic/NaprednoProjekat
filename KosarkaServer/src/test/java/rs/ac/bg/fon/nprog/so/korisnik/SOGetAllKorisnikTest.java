package rs.ac.bg.fon.nprog.so.korisnik;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Tip;

class SOGetAllKorisnikTest {

	private SOGetAllKorisnik soGetAllKorisnik;
    private DBBroker dbBroker;

    @BeforeEach
    public void setUp() {
        soGetAllKorisnik = new SOGetAllKorisnik();
        dbBroker = mock(DBBroker.class);
        DBBroker mockDBBroker = mock(DBBroker.class);
        when(DBBroker.getInstance()).thenReturn(mockDBBroker);
    }

    @Test
    public void testValidateValidInstance() throws Exception {
        Korisnik korisnik = new Korisnik();
        assertDoesNotThrow(() -> soGetAllKorisnik.validate(korisnik));
    }

    @Test
    public void testValidateInvalidInstance() {
        AbstractDomainObject invalidObject = mock(AbstractDomainObject.class);
        Exception exception = assertThrows(Exception.class, () -> soGetAllKorisnik.validate((Korisnik) invalidObject));
        assertEquals("Prosledjeni objekat nije instanca klase Korisnik!", exception.getMessage());
    }

    @Test
    public void testExecute() throws Exception {
        ArrayList<AbstractDomainObject> korisniciList = new ArrayList<>();
        Tip tip = new Tip(1L, "Free");
        Korisnik korisnik = new Korisnik(1L, "Ognjen", "Jankovic", "ogi@email.com", "0621095131", tip);
        korisniciList.add(korisnik);

        when(DBBroker.getInstance().select(any(Korisnik.class))).thenReturn(korisniciList);

        soGetAllKorisnik.execute(new Korisnik());

        assertNotNull(soGetAllKorisnik.getLista());
        assertEquals(1, soGetAllKorisnik.getLista().size());
        assertEquals(korisnik, soGetAllKorisnik.getLista().get(0));
    }

}
