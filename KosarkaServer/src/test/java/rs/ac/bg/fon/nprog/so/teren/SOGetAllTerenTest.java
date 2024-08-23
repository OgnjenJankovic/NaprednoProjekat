package rs.ac.bg.fon.nprog.so.teren;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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


    private Teren teren1;
    private Teren teren2;
    private ArrayList<Teren> tereni;

    @BeforeEach
    protected void setUp() throws Exception {
        super.setUp();
        teren1 = new Teren(1L, "Teren1", "Adresa1", "Opis1", 100, new Opstina(), new Grad());
        teren2 = new Teren(2L, "Teren2", "Adresa2", "Opis2", 200, new Opstina(), new Grad());
        tereni = new ArrayList<>(Arrays.asList(teren1, teren2));
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
        Grad grad = new Grad();
        Exception exception = assertThrows(Exception.class, () -> soGetAllTeren.validate((AbstractDomainObject) grad));
        assertEquals("Prosledjeni objekat nije instanca klase Teren!", exception.getMessage());
    }

    
    
    
}
