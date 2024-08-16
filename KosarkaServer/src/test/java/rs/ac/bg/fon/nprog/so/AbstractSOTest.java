package rs.ac.bg.fon.nprog.so;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Korisnik;

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractSOTest{

	private AutoCloseable closeable;

    @Mock
    protected DBBroker dbb;

    @BeforeEach
    protected void setUp() throws Exception {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
	protected
    void tearDown() throws Exception {
        if (closeable != null) {
            closeable.close();
        }
    }
    
    

    @Test
    void testAbstractSO() {
        DBBroker dbbroker = DBBroker.getInstance();
        assertNotNull(dbbroker);
    }

}
