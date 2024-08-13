package rs.ac.bg.fon.nprog.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class OpstinaTest {

	Opstina o;
	
	@Mock
	private ResultSet rs;
	 
	@BeforeEach
	void setUp() throws Exception {
		o = new Opstina();
	}

	@AfterEach
	void tearDown() throws Exception {
		o = null;
	}

	@Test
	void testOpstinaKonstruktorEmpty() {
	    o = new Opstina();
	    assertNotNull(o);
	    assertNull(o.getOpstinaID()); 
	    assertNull(o.getNaziv());
	}

	@Test
	void testOpstinaKonstruktorFull() {
		o = new Opstina(1L, "Vozdovac");
		assertNotNull(o);
		assertEquals(1, o.getOpstinaID());
		assertEquals("Vozdovac", o.getNaziv());
	}
	
	@Test
	void testOpstinaSetOpstinaIDNula() {
		assertThrows(IllegalArgumentException.class, ()->o.setOpstinaID(0L));
	}
	
	@Test
	void testOpstinaSetOpstinaIDOk() {
		o.setOpstinaID(1L);
		assertEquals(1, o.getOpstinaID());
	}
	
	@Test
	void testOpstinaSetNazivNull() {
		assertThrows(NullPointerException.class, ()->o.setNaziv(null));
	}
	
	@Test
	void testOpstinaSetNazivOk() {
		o.setNaziv("Vozdovac");
		assertEquals("Vozdovac", o.getNaziv());
	}
	
	@Test
	void testOpstinaToString() {
		o.setNaziv("Vozdovac");
		String rezultat = o.toString();
		assertTrue(rezultat.contains("Vozdovac"));
	}
	
	@ParameterizedTest
	@CsvSource(
			{
				"1, 1, true",
				"1, 2, false",
			}
	)
	void testEqualsObject(long opstinaID1, long opstinaID2, boolean rez) {
		o.setOpstinaID(opstinaID1);
		Opstina o2 = new Opstina();
		o2.setOpstinaID(opstinaID2);
		assertEquals(rez, o.equals(o2));
	}
	
	@Test
	void testOpstinaRSuTabelu() throws Exception {
	    AutoCloseable ac = MockitoAnnotations.openMocks(this);
	    KreirajOpstinaResultSet();

	    Opstina o1 = new Opstina();
	    List<AbstractDomainObject> lista1 = o1.vratiListu(rs);

	    Opstina o2 = new Opstina();
	    o2.setOpstinaID(1L); 
	    o2.setNaziv("Vozdovac"); 

	    List<AbstractDomainObject> lista2 = new ArrayList<>();
	    lista2.add(o2);

	    assertEquals(lista2, lista1);

	    ac.close();
	}

	private void KreirajOpstinaResultSet() throws SQLException {
	    Mockito.when(rs.next()).thenReturn(true).thenReturn(false);		
	    Mockito.when(rs.getLong("OpstinaID")).thenReturn(1L);	
	    Mockito.when(rs.getString("Naziv")).thenReturn("Vozdovac");	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
