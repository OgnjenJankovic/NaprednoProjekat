package rs.ac.bg.fon.nprog.so.korisnik;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Tip;
import rs.ac.bg.fon.nprog.so.AbstractSOTest;

 class SOAddKorisnikTest extends AbstractSOTest{

	private SOAddKorisnik so;
    private DBBroker dbBrokerMock;

    @BeforeEach
	protected
    void setUp() throws Exception {
    	super.setUp();
        dbBrokerMock = Mockito.mock(DBBroker.class);
        so = new SOAddKorisnik(dbBrokerMock);
    }
    
    @AfterEach
	protected
    void tearDown() throws Exception {
    	super.tearDown();
        dbBrokerMock = null;
        so = null;
    }

    

    @Test
    void testAddKorisnikDuplicateEmail() throws Exception {
    	Korisnik k1 = new Korisnik(1L, "Ognjen", "Jankovic", "ogi@gmail.com", "0631231234", new Tip(1L, "Premium"));
        Korisnik k2 = new Korisnik(2L, "Janko", "Jankovic", "ogi@gmail.com", "0657654321", new Tip(2L, "Premium"));

        ArrayList<AbstractDomainObject> existingUsers = new ArrayList<>();
        existingUsers.add(k2);

        when(dbBrokerMock.select(any(Korisnik.class))).thenReturn(existingUsers);

        Exception exception = assertThrows(Exception.class, () -> so.templateExecute(k1));

        String expectedMessage = "Korisnik sa tim emailom vec postoji!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(dbBrokerMock, never()).insert(k1);
    }

    

    @Test
    void testAddInvalidObject() throws SQLException {
        AbstractDomainObject invalidObject = mock(AbstractDomainObject.class);

        Exception exception = assertThrows(Exception.class, () -> so.templateExecute(invalidObject));

        String expectedMessage = "Prosledjeni objekat nije instanca klase Korisnik!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(dbBrokerMock, never()).insert(any(AbstractDomainObject.class));
    }

    
    
    
    
}
