package rs.ac.bg.fon.nprog.so.grad;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Korisnik;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SOGetAllGradTest {

	private SOGetAllGrad soGetAllGrad;
    private Grad grad;
    private DBBroker dbBroker;
    private Connection connection;

    @BeforeEach
    void setUp() throws Exception {
        soGetAllGrad = new SOGetAllGrad();
        grad = new Grad();
        dbBroker = Mockito.mock(DBBroker.class);
        connection = Mockito.mock(Connection.class);

        Mockito.mockStatic(DBBroker.class);
        Mockito.when(DBBroker.getInstance()).thenReturn(dbBroker);

        Mockito.when(dbBroker.getConnection()).thenReturn(connection);
    }

    @AfterEach
    void tearDown() {
        soGetAllGrad = null;
        grad = null;
        dbBroker = null;
        connection = null;
    }

    @Test
    void testValidate_Success() {
        assertDoesNotThrow(() -> soGetAllGrad.validate(grad));
    }

    @Test
    void testValidate_Failure() {
    	AbstractDomainObject notGrad = new Korisnik();
        Exception exception = assertThrows(Exception.class, () -> soGetAllGrad.validate(notGrad));
        assertEquals("Prosledjeni objekat nije instanca klase Grad!", exception.getMessage());
    }

    @Test
    void testExecute_Success() throws Exception {
        ArrayList<AbstractDomainObject> gradovi = new ArrayList<>();
        gradovi.add(new Grad(1L, "Beograd"));
        gradovi.add(new Grad(2L, "Novi Sad"));

        Mockito.when(dbBroker.select(grad)).thenReturn(gradovi);

        soGetAllGrad.execute(grad);

        ArrayList<Grad> resultList = soGetAllGrad.getLista();
        assertEquals(2, resultList.size());
        assertEquals("Beograd", resultList.get(0).getNaziv());
        assertEquals("Novi Sad", resultList.get(1).getNaziv());
    }

    @Test
    void testExecute_Failure() throws Exception {
        Mockito.when(dbBroker.select(grad)).thenThrow(new Exception("Database error"));

        Exception exception = assertThrows(Exception.class, () -> soGetAllGrad.execute(grad));
        assertEquals("Database error", exception.getMessage());
    }

}
