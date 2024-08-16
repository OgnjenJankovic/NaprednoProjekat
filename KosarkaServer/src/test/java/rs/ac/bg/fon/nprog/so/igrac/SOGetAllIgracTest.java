package rs.ac.bg.fon.nprog.so.igrac;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
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
import rs.ac.bg.fon.nprog.so.AbstractSOTest;

class SOGetAllIgracTest extends AbstractSOTest{

	@Mock
    private DBBroker dbb; // Mock DBBroker
    
    @InjectMocks
    private SOGetAllIgrac soGetAllIgrac;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
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
