package rs.ac.bg.fon.nprog.so.login;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import rs.ac.bg.fon.nprog.controller.ServerController;
import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Administrator;
import rs.ac.bg.fon.nprog.so.AbstractSOTest;

 class SOLoginTest extends AbstractSOTest {

	 	private SOLogin so;
	    private DBBroker dbBroker;

	    @BeforeEach
	    protected void setUp() {
	        dbBroker = mock(DBBroker.class);
	        so = new SOLogin(dbBroker);
	    }

	    @AfterEach
	    protected void tearDown() {
	        ServerController.getInstance().getUlogovaniAdministratori().clear();
	    }

	    @Test
	    void testSOLoginAlreadyLoggedIn() {
	        Administrator a1 = new Administrator(1L, "Marko", "Markovic", "marko", "pass123");

	        ServerController.getInstance().getUlogovaniAdministratori().add(a1);

	        Exception exception = assertThrows(Exception.class, () -> so.templateExecute(a1));

	        String expectedMessage = "Ovaj administrator je vec ulogovan na sistem!";
	        String actualMessage = exception.getMessage();

	        assertTrue(actualMessage.contains(expectedMessage));
	    }

	    @Test
	    void testSOLoginSuccess() throws Exception {
	        Administrator a1 = new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi");

	        ArrayList<AbstractDomainObject> administratori = new ArrayList<>();
	        administratori.add(a1);

	        when(dbBroker.select(a1)).thenReturn(administratori);

	        assertDoesNotThrow(() -> so.templateExecute(a1));

	        Administrator loggedInAdmin = so.getUlogovani();

	        assertNotNull(loggedInAdmin);
	        assertEquals(a1.getUsername(), loggedInAdmin.getUsername());
	        assertEquals(a1.getPassword(), loggedInAdmin.getPassword());
	        assertTrue(ServerController.getInstance().getUlogovaniAdministratori().contains(loggedInAdmin));
	    }

	    @Test
	    void testSOLoginNonExistentAdministrator() throws Exception {
	        Administrator a1 = new Administrator(1L, "Petar", "Petrovic", "petar", "pass123");

	        ArrayList<AbstractDomainObject> administratori = new ArrayList<>();

	        when(dbBroker.select(a1)).thenReturn(administratori);

	        Exception exception = assertThrows(Exception.class, () -> so.templateExecute(a1));

	        String expectedMessage = "Ne postoji administrator sa tim kredencijalima.";
	        String actualMessage = exception.getMessage();

	        assertTrue(actualMessage.contains(expectedMessage));
	    }

	    @Test
	    void testSOLoginIncorrectPassword() throws Exception {
	        Administrator a1 = new Administrator(1L, "Marko", "Markovic", "marko", "wrongpass");

	        ArrayList<AbstractDomainObject> administratori = new ArrayList<>();
	        administratori.add(new Administrator(1L, "Marko", "Markovic", "marko", "correctpass"));

	        when(dbBroker.select(a1)).thenReturn(administratori);

	        Exception exception = assertThrows(Exception.class, () -> so.templateExecute(a1));

	        String expectedMessage = "Ne postoji administrator sa tim kredencijalima.";
	        String actualMessage = exception.getMessage();

	        assertTrue(actualMessage.contains(expectedMessage));
	    }


	    @Test
	    void testTearDownClearsLoggedInAdministrators() {
	        Administrator a1 = new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi");

	        ServerController.getInstance().getUlogovaniAdministratori().add(a1);

	        tearDown();

	        assertTrue(ServerController.getInstance().getUlogovaniAdministratori().isEmpty(),
	            "Lista ulogovanih administratora bi trebala biti prazna nakon tearDown poziva.");
	    }
    
    
}
