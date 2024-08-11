package rs.ac.bg.fon.nprog.so;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Connection;

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

@RunWith(MockitoJUnitRunner.class)
public abstract class AbstractSOTest {

	
	protected DBBroker dbb;
	private AbstractSO abstractSO;
    private DBBroker dbBrokerMock;
    private Connection connectionMock;

    @BeforeEach
    public void setUp() {
        dbBrokerMock = mock(DBBroker.class);
        connectionMock = mock(Connection.class);
    }
    
    @AfterEach
    void tearDown() {
    	dbBrokerMock = null;
    	connectionMock = null;
    }

    @Test
    public void testTemplateExecuteSuccess() throws Exception {

        abstractSO.templateExecute(mock(AbstractDomainObject.class));

        verify(connectionMock, times(1)).commit();
        verify(connectionMock, never()).rollback();
    }

    @Test
    public void testTemplateExecuteValidationFails() throws Exception {
        doThrow(new Exception("Validation failed")).when(abstractSO).validate(any());

        try {
            abstractSO.templateExecute(mock(AbstractDomainObject.class));
        } catch (Exception e) {
            verify(connectionMock, never()).commit();
            verify(connectionMock, times(1)).rollback();
        }
    }

    @Test
    public void testTemplateExecuteExecutionFails() throws Exception {
        doThrow(new Exception("Execution failed")).when(abstractSO).execute(any());

        try {
            abstractSO.templateExecute(mock(AbstractDomainObject.class));
        } catch (Exception e) {
            verify(connectionMock, never()).commit();
            verify(connectionMock, times(1)).rollback();
        }
    }

}
