package rs.ac.bg.fon.nprog.so.opstina;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Opstina;

class SOGetAllOpstinaTest {

	private SOGetAllOpstina soGetAllOpstina;
    private Opstina opstina;
    private DBBroker dbBroker;

    @BeforeEach
    void setUp() {
        soGetAllOpstina = new SOGetAllOpstina();
        opstina = new Opstina();
        dbBroker = Mockito.mock(DBBroker.class);

        Mockito.mockStatic(DBBroker.class);
        Mockito.when(DBBroker.getInstance()).thenReturn(dbBroker);
    }

    @AfterEach
    void tearDown() {
        soGetAllOpstina = null;
        opstina = null;
        dbBroker = null;
    }

    @Test
    void testValidate_Success() {
        assertDoesNotThrow(() -> soGetAllOpstina.validate(opstina));
    }

    @Test
    void testValidate_Failure() {
        AbstractDomainObject notOpstina = new Korisnik(); 
        Exception exception = assertThrows(Exception.class, () -> soGetAllOpstina.validate(notOpstina));
        assertEquals("Prosledjeni objekat nije instanca klase Opstina!", exception.getMessage());
    }
    
    @Test
    void testExecute_Success() throws Exception {
        ArrayList<AbstractDomainObject> abstractDomainObjects = new ArrayList<>();
        abstractDomainObjects.add(new Opstina(1L, "Novi Sad"));
        abstractDomainObjects.add(new Opstina(2L, "Beograd"));

        Mockito.when(dbBroker.select(Mockito.any(AbstractDomainObject.class))).thenReturn(abstractDomainObjects);

        soGetAllOpstina.execute(opstina);

        ArrayList<Opstina> resultList = soGetAllOpstina.getLista();
        assertEquals(2, resultList.size());
        assertEquals("Novi Sad", resultList.get(0).getNaziv());
        assertEquals("Beograd", resultList.get(1).getNaziv());
    }

    @Test
    void testExecute_Failure() throws Exception {
        Mockito.when(dbBroker.select(Mockito.any(AbstractDomainObject.class))).thenThrow(new SQLException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soGetAllOpstina.execute(opstina));
        assertEquals("Database error", exception.getMessage());
    }

}
