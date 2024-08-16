package rs.ac.bg.fon.nprog.so.login;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

    @BeforeEach
	protected
    void setUp() {
        so = new SOLogin();
    }

    @AfterEach
	protected
    void tearDown() {
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
    void testSOLoginSuccess() {
        Administrator a1 = new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi");

        

        assertDoesNotThrow(() -> so.templateExecute(a1));

        Administrator loggedInAdmin = so.getUlogovani();

        assertNotNull(loggedInAdmin);
        assertEquals(a1.getUsername(), loggedInAdmin.getUsername());
        assertEquals(a1.getPassword(), loggedInAdmin.getPassword());
    }
}
