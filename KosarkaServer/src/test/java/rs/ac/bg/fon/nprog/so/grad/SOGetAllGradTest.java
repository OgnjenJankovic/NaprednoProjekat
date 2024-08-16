package rs.ac.bg.fon.nprog.so.grad;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Opstina;
import rs.ac.bg.fon.nprog.so.AbstractSOTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SOGetAllGradTest extends AbstractSOTest {

	@InjectMocks
    private SOGetAllGrad soGetAllGrad;

    @Mock
    private Grad grad;

    private Grad grad1;
    private Grad grad2;
    private ArrayList<Grad> gradovi;

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        grad1 = new Grad(1L, "Beograd");
        grad2 = new Grad(2L, "Novi Sad");
        gradovi = new ArrayList<>(Arrays.asList(grad1, grad2));
    }

    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    void testValidateSuccess() throws Exception {
        Grad grad = new Grad(); 
        assertDoesNotThrow(() -> soGetAllGrad.validate(grad));
    }

    @Test
    void testValidateFailure() {
        Korisnik korisnik = new Korisnik();
        Exception thrownException = assertThrows(Exception.class, () -> soGetAllGrad.validate(korisnik));
        assertEquals("Prosledjeni objekat nije instanca klase Grad!", thrownException.getMessage());
    }

   
    
    
    
}
