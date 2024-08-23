package rs.ac.bg.fon.nprog.so.opstina;

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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Opstina;
import rs.ac.bg.fon.nprog.domain.Teren;
import rs.ac.bg.fon.nprog.so.AbstractSOTest;

class SOGetAllOpstinaTest extends AbstractSOTest{

    
	@InjectMocks
    private SOGetAllOpstina soGetAllOpstina;

    private Opstina opstina1;
    private Opstina opstina2;
    private ArrayList<Opstina> opstine;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        opstina1 = new Opstina(1L, "Vozdovac");
        opstina2 = new Opstina(2L, "Zvezdara");
        opstine = new ArrayList<>(Arrays.asList(opstina1, opstina2));
    }

    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
        soGetAllOpstina = null;
    }

    @Test
    void testValidateSuccess() throws Exception {
        Opstina opstina = new Opstina(); 
        assertDoesNotThrow(() -> soGetAllOpstina.validate(opstina));
    }

    @Test
    void testValidateFailure() {
        Korisnik korisnik = new Korisnik();
        Exception thrownException = assertThrows(Exception.class, () -> soGetAllOpstina.validate(korisnik));
        assertEquals("Prosledjeni objekat nije instanca klase Opstina!", thrownException.getMessage());
    }

    @Test
    void testExecuteSuccess() throws Exception {
        when(dbb.select(any(Opstina.class))).thenReturn(new ArrayList<AbstractDomainObject>(opstine));

        soGetAllOpstina.templateExecute(new Opstina());

        verify(dbb, times(1)).select(any(Opstina.class));

        assertNotNull(soGetAllOpstina.getLista());
        assertEquals(2, soGetAllOpstina.getLista().size());
        assertEquals("Vozdovac", soGetAllOpstina.getLista().get(0).getNaziv());
        assertEquals("Zvezdara", soGetAllOpstina.getLista().get(1).getNaziv());
    }

    @Test
    void testExecuteEmptyResult() throws Exception {
        when(dbb.select(any(Opstina.class))).thenReturn(new ArrayList<>());

        soGetAllOpstina.templateExecute(new Opstina());

        verify(dbb, times(1)).select(any(Opstina.class));

        assertNotNull(soGetAllOpstina.getLista());
        assertTrue(soGetAllOpstina.getLista().isEmpty());
    }
	
	

}
