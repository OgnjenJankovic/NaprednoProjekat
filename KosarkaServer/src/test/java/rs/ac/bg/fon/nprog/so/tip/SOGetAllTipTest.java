package rs.ac.bg.fon.nprog.so.tip;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Opstina;
import rs.ac.bg.fon.nprog.domain.Tip;
import rs.ac.bg.fon.nprog.so.AbstractSOTest;

 class SOGetAllTipTest  extends AbstractSOTest{

	private SOGetAllTip soGetAllTip;

    @Mock
    private DBBroker dbb;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        soGetAllTip = new SOGetAllTip(dbb);
    }

    @Test
    void testValidateSuccess() throws Exception {
        Tip tip = new Tip(); 
        assertDoesNotThrow(() -> soGetAllTip.validate(tip));
    }

    @Test
    void testValidateFailure() {
        Korisnik korisnik = new Korisnik();
        Exception thrownException = assertThrows(Exception.class, () -> soGetAllTip.validate(korisnik));
        assertEquals("Prosledjeni objekat nije instanca klase Tip!", thrownException.getMessage());
    }
    
   

    
}
