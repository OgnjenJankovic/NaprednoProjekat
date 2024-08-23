package rs.ac.bg.fon.nprog.so.korisnik;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Tip;
import rs.ac.bg.fon.nprog.so.AbstractSOTest;

class SODeleteKorisnikTest extends AbstractSOTest{

	 private SODeleteKorisnik soDeleteKorisnik;
	    

	    @BeforeEach
		protected
	    void setUp() throws Exception {
	        super.setUp();
	        soDeleteKorisnik = new SODeleteKorisnik(dbb); 
	    }

	    @AfterEach
		protected
	    void tearDown() throws Exception {
	        super.tearDown();
	        soDeleteKorisnik = null;
	    }

	    

	    @Test
	    void testDeleteKorisnikInvalidObject() {
	        Object invalidObject = new Grad();

	        Exception exception = assertThrows(Exception.class, () -> soDeleteKorisnik.templateExecute((AbstractDomainObject) invalidObject));
	        assertEquals("Prosledjeni objekat nije instanca klase Korisnik!", exception.getMessage());
	    }
	
	    
	
	
	
	
	
	

}
