/**
 * 
 */
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

/**
 * 
 */
class TipTest {

	Tip t;
	
	@Mock
	private ResultSet rs;
	
	@BeforeEach
	void setUp() throws Exception {
		t = new Tip();
	}

	
	@AfterEach
	void tearDown() throws Exception {
		t = null;
	}

	@Test
	void testTipKonstruktorEmpty() {
	    t = new Tip();
	    assertNotNull(t);
	    assertNull(t.getTipID());
	    assertNull(t.getNaziv());
	}

	@Test
	void testTipKonstruktorFull() {
	    t = new Tip(1L, "Free");
	    assertNotNull(t);
	    assertEquals(1L, t.getTipID());
	    assertEquals("Free", t.getNaziv()); 
	}

	@Test
	void testTipSetTipIDNula() {
		assertThrows(IllegalArgumentException.class, ()->t.setTipID(0L));
	}
	
	@Test
	void testTipSetTipIDOk() {
		t.setTipID(1L);
		assertEquals(1, t.getTipID());
	}
	
	@Test
	void testTipSetNazivNull() {
		assertThrows(NullPointerException.class, ()->t.setNaziv(null));
	}
	
	@Test
	void testTipSetNazivOk() {
		t.setNaziv("Free");
		assertEquals("Free", t.getNaziv());
	}
	
	@Test
	void testGradToString() {
		t.setNaziv("Free");
		String rezultat = t.toString();
		assertTrue(rezultat.contains("Free"));
	}
	
	@ParameterizedTest
	@CsvSource(
			{
				"1, 1, true",
				"1, 2, false",
			}
	)
	void testEqualsObject(long tipID1, long tipID2, boolean rez) {
		t.setTipID(tipID1);
		Tip t2 = new Tip();
		t2.setTipID(tipID2);
		assertEquals(rez, t.equals(t2));
	}
	
	@Test
	void testTipRSuTabelu()throws Exception{
	    AutoCloseable ac = MockitoAnnotations.openMocks(this);
	    KreirajTipResultSet();
	    
	    Tip t1 = new Tip();
	    List<AbstractDomainObject> lista1 = t1.vratiListu(rs);
	    
	    Tip t2 = new Tip();
	    t2.setTipID(1L);
	    t2.setNaziv("Free");
	    
	    List<AbstractDomainObject> lista2 = new ArrayList<>();
	    lista2.add(t2);
	    
	    assertEquals(lista2, lista1);
	    
	    ac.close();
	}


	private void KreirajTipResultSet() throws SQLException {
	    Mockito.when(rs.next()).thenReturn(true).thenReturn(false);        
	    Mockito.when(rs.getLong("TipID")).thenReturn(1L);    
	    Mockito.when(rs.getString("Naziv")).thenReturn("Free");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
