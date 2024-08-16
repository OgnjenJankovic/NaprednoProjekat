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

    
private SOGetAllOpstina soGetAllOpstina;

@Mock
private DBBroker dbb;

@BeforeEach
public void setUp() {
    MockitoAnnotations.openMocks(this);
    soGetAllOpstina = new SOGetAllOpstina(dbb);
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
public void testExecute() throws Exception {
    soGetAllOpstina.execute(new Opstina());

    Opstina opstina1 = new Opstina(1L, "Vozdovac");
    Opstina opstina2 = new Opstina(2L, "Zvezdara");
    ArrayList<Opstina> expectedOpstine = new ArrayList<>(Arrays.asList(opstina1, opstina2));

    ArrayList<Opstina> actualList = soGetAllOpstina.getLista();
    assertNotNull(actualList, "Lista ne sme biti null");
}

}
