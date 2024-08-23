package rs.ac.bg.fon.nprog.so.igrac;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Igrac;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Termin;
import rs.ac.bg.fon.nprog.domain.Tip;
import rs.ac.bg.fon.nprog.so.AbstractSOTest;

class SOGetAllIgracTest extends AbstractSOTest{

	
    
    @InjectMocks
    private SOGetAllIgrac soGetAllIgrac;

    private Igrac igrac1;
    private Igrac igrac2;
    private ArrayList<Igrac> igraci;

    @BeforeEach
    public void setUp() {
    	MockitoAnnotations.openMocks(this);

        Termin termin = new Termin();
        
        Korisnik korisnik1 = new Korisnik(1L, "Milos", "Milosevic", "milos@email.com", "0612345678", new Tip());
        Korisnik korisnik2 = new Korisnik(2L, "Marko", "Markovic", "marko@email.com", "0612345679", new Tip());

        igrac1 = new Igrac(termin, 1, "Napomena 1", korisnik1);
        igrac2 = new Igrac(termin, 2, "Napomena 2", korisnik2);
        
        igraci = new ArrayList<>(Arrays.asList(igrac1, igrac2));
    }
    
    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
        soGetAllIgrac = null;
    }

    @Test
    public void testValidateValidObject() throws Exception {
        Igrac validIgrac = new Igrac(); 
        
        assertDoesNotThrow(() -> soGetAllIgrac.validate(validIgrac),
            "Validate metoda bi trebala da prihvati Igrac objekat bez izuzetaka.");
    }

    @Test
    public void testValidateInvalidObject() {
        Object invalidObject = new Grad(); 
        Exception exception = assertThrows(Exception.class, () -> soGetAllIgrac.validate((AbstractDomainObject) invalidObject),
            "Validate metoda bi trebala da baci izuzetak kada prosledi objekat koji nije instanca Igrac.");
        
        assertEquals("Prosledjeni objekat nije instanca klase Igrac!", exception.getMessage(),
            "Poruka izuzetka nije odgovarajuća.");
    }

    

}
