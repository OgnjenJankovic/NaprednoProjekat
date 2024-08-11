package rs.ac.bg.fon.nprog.so.login;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

class SOLoginTest {

	private SOLogin soLogin;
    private Administrator mockAdministrator;
    private DBBroker dbBroker;
    private ServerController serverController;

    @BeforeEach
    void setUp() throws Exception {
        soLogin = new SOLogin();
        mockAdministrator = new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi");
        dbBroker = Mockito.mock(DBBroker.class);
        serverController = Mockito.mock(ServerController.class);

        Mockito.mockStatic(DBBroker.class);
        Mockito.when(DBBroker.getInstance()).thenReturn(dbBroker);

        Mockito.mockStatic(ServerController.class);
        Mockito.when(ServerController.getInstance()).thenReturn(serverController);
        
        Mockito.when(serverController.getUlogovaniAdministratori()).thenReturn(new ArrayList<>());
    }

    @AfterEach
    void tearDown() {
        soLogin = null;
        mockAdministrator = null;
        dbBroker = null;
        serverController = null;
    }

    @Test
    void testValidate_Success() throws Exception {
        ArrayList<Administrator> loggedInAdmins = new ArrayList<>();
        Mockito.when(serverController.getUlogovaniAdministratori()).thenReturn(loggedInAdmins);
        
        assertDoesNotThrow(() -> soLogin.validate(mockAdministrator));
    }

    @Test
    void testValidate_Failure_AlreadyLoggedIn() {
        ArrayList<Administrator> loggedInAdmins = new ArrayList<>();
        loggedInAdmins.add(mockAdministrator);
        Mockito.when(serverController.getUlogovaniAdministratori()).thenReturn(loggedInAdmins);
        
        Exception exception = assertThrows(Exception.class, () -> soLogin.validate(mockAdministrator));
        assertEquals("Ovaj administrator je vec ulogovan na sistem!", exception.getMessage());
    }

    @Test
    void testExecute_Success() throws Exception {
        ArrayList<AbstractDomainObject> abstractDomainObjects = new ArrayList<>();
        abstractDomainObjects.add(mockAdministrator);

        when(dbBroker.select(any(AbstractDomainObject.class))).thenReturn(abstractDomainObjects);

        soLogin.execute(mockAdministrator);

        assertEquals(mockAdministrator, soLogin.getUlogovani());
        assertTrue(serverController.getUlogovaniAdministratori().contains(mockAdministrator));
    }

    @Test
    void testExecute_Failure_InvalidCredentials() throws Exception {
        ArrayList<AbstractDomainObject> abstractDomainObjects = new ArrayList<>();
        abstractDomainObjects.add(new Administrator(2L, "Jovan", "Petrovic", "joca", "joca"));

        when(dbBroker.select(any(AbstractDomainObject.class))).thenReturn(abstractDomainObjects);

        Exception exception = assertThrows(Exception.class, () -> soLogin.execute(mockAdministrator));
        assertEquals("Ne postoji administrator sa tim kredencijalima.", exception.getMessage());
    }

    @Test
    void testExecute_Failure_EmptyDatabase() throws Exception {
        when(dbBroker.select(any(AbstractDomainObject.class))).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(Exception.class, () -> soLogin.execute(mockAdministrator));
        assertEquals("Ne postoji administrator sa tim kredencijalima.", exception.getMessage());
    }

}
