package rs.ac.bg.fon.nprog.so.teren;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Opstina;
import rs.ac.bg.fon.nprog.domain.Teren;

class SOGetAllTerenTest {

	private SOGetAllTeren soGetAllTeren;
    private Teren teren;
    private DBBroker dbBroker;

    @BeforeEach
    void setUp() {
        soGetAllTeren = new SOGetAllTeren();
        teren = new Teren();
        dbBroker = Mockito.mock(DBBroker.class);

        Mockito.mockStatic(DBBroker.class);
        Mockito.when(DBBroker.getInstance()).thenReturn(dbBroker);
    }

    @AfterEach
    void tearDown() {
        soGetAllTeren = null;
        teren = null;
        dbBroker = null;
    }

    @Test
    void testValidate_Success() {
        assertDoesNotThrow(() -> soGetAllTeren.validate(teren));
    }

    @Test
    void testValidate_Failure() {
        AbstractDomainObject notTeren = new Opstina(); 
        Exception exception = assertThrows(Exception.class, () -> soGetAllTeren.validate(notTeren));
        assertEquals("Prosledjeni objekat nije instanca klase Teren!", exception.getMessage());
    }

    @Test
    void testExecute_Success() throws Exception {
        ArrayList<AbstractDomainObject> abstractDomainObjects = new ArrayList<>();
        Opstina opstina = new Opstina(1L, "Novi Sad");
        Grad grad = new Grad(1L, "Beograd");
        abstractDomainObjects.add(new Teren(1L, "Teren1", "Adresa1", "Opis1", 100.0, opstina, grad));
        abstractDomainObjects.add(new Teren(2L, "Teren2", "Adresa2", "Opis2", 150.0, opstina, grad));

        Mockito.when(dbBroker.select(Mockito.any(AbstractDomainObject.class))).thenReturn(abstractDomainObjects);

        soGetAllTeren.execute(teren);

        ArrayList<Teren> resultList = soGetAllTeren.getLista();
        assertEquals(2, resultList.size());
        assertEquals("Teren1", resultList.get(0).getNaziv());
        assertEquals("Teren2", resultList.get(1).getNaziv());
    }

    @Test
    void testExecute_Failure() throws Exception {
        Mockito.when(dbBroker.select(Mockito.any(AbstractDomainObject.class))).thenThrow(new SQLException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soGetAllTeren.execute(teren));
        assertEquals("Database error", exception.getMessage());
    }
    
    
    
}
