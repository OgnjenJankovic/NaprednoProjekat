package rs.ac.bg.fon.nprog.so.tip;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Opstina;
import rs.ac.bg.fon.nprog.domain.Tip;

class SOGetAllTipTest {

	private SOGetAllTip soGetAllTip;
    private Tip tip;
    private DBBroker dbBroker;

    @BeforeEach
    void setUp() {
        soGetAllTip = new SOGetAllTip();
        tip = new Tip();
        dbBroker = Mockito.mock(DBBroker.class);

        Mockito.mockStatic(DBBroker.class);
        Mockito.when(DBBroker.getInstance()).thenReturn(dbBroker);
    }

    @AfterEach
    void tearDown() {
        soGetAllTip = null;
        tip = null;
        dbBroker = null;
    }

    @Test
    void testValidate_Success() {
        assertDoesNotThrow(() -> soGetAllTip.validate(tip));
    }

    @Test
    void testValidate_Failure() {
        AbstractDomainObject notTip = new Opstina(); 
        Exception exception = assertThrows(Exception.class, () -> soGetAllTip.validate(notTip));
        assertEquals("Prosledjeni objekat nije instanca klase Tip!", exception.getMessage());
    }
    
    @Test
    void testExecute_Success() throws Exception {
        ArrayList<AbstractDomainObject> abstractDomainObjects = new ArrayList<>();
        abstractDomainObjects.add(new Tip(1L, "Tip1"));
        abstractDomainObjects.add(new Tip(2L, "Tip2"));

        Mockito.when(dbBroker.select(Mockito.any(AbstractDomainObject.class))).thenReturn(abstractDomainObjects);

        soGetAllTip.execute(tip);

        ArrayList<Tip> resultList = soGetAllTip.getLista();
        assertEquals(2, resultList.size());
        assertEquals("Tip1", resultList.get(0).getNaziv());
        assertEquals("Tip2", resultList.get(1).getNaziv());
    }

    @Test
    void testExecute_Failure() throws Exception {
        Mockito.when(dbBroker.select(Mockito.any(AbstractDomainObject.class))).thenThrow(new SQLException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soGetAllTip.execute(tip));
        assertEquals("Database error", exception.getMessage());
    }

}
