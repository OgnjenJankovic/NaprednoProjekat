package rs.ac.bg.fon.nprog.so.teren;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
import rs.ac.bg.fon.nprog.domain.Opstina;
import rs.ac.bg.fon.nprog.domain.Teren;
import rs.ac.bg.fon.nprog.so.AbstractSOTest;

class SOGetAllTerenTest extends AbstractSOTest{

	@InjectMocks
    private SOGetAllTeren soGetAllTeren;

    @Mock
    private DBBroker dbBroker;

    @BeforeEach
    protected void setUp() throws Exception {
        super.setUp();
        soGetAllTeren = new SOGetAllTeren(dbBroker);
    }

    @AfterEach
    protected void tearDown() throws Exception {
        super.tearDown();
        soGetAllTeren = null;
    }

    @Test
    void testValidateSuccess() throws Exception {
        Teren teren = new Teren(); 
        assertDoesNotThrow(() -> soGetAllTeren.validate(teren));
    }

    @Test
    void testValidateFailure() {
        Korisnik korisnik = new Korisnik();
        Exception thrownException = assertThrows(Exception.class, () -> soGetAllTeren.validate(korisnik));
        assertEquals("Prosledjeni objekat nije instanca klase Teren!", thrownException.getMessage());
    }

    
    
    
}
