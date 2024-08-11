package rs.ac.bg.fon.nprog.so.korisnik;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Tip;

class SODeleteKorisnikTest {

	private DBBroker dbBroker;
    private SODeleteKorisnik soDeleteKorisnik;
    private Korisnik korisnik;

    @BeforeEach
    public void setUp() {
        dbBroker = mock(DBBroker.class);
        soDeleteKorisnik = new SODeleteKorisnik();
        korisnik = new Korisnik(1L, "Ognjen", "Jankovic", "ogi@email.com", "0621095131", new Tip(1L, "Free"));

    }

    @Test
    public void testValidate_validKorisnik() {
        assertDoesNotThrow(() -> soDeleteKorisnik.validate(korisnik));
    }

    @Test
    public void testExecute_validKorisnik() throws SQLException {
        doNothing().when(dbBroker).delete(korisnik);

        assertDoesNotThrow(() -> soDeleteKorisnik.execute(korisnik));

        verify(dbBroker).delete(korisnik);
    }

}
