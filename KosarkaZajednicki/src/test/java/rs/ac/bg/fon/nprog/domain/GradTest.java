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
class GradTest {

	Grad g;
	
	@Mock
	private ResultSet rs;
	
	@BeforeEach
	void setUp() throws Exception {
		g = new Grad();
	}

	
	@AfterEach
	void tearDown() throws Exception {
		g = null;
	}

	@Test
	void testGradKonstruktorEmpty() {
		g = new Grad();
	    assertNotNull(g);
	    assertNull(g.getGradID());  
	    assertNull(g.getNaziv());    
	}

	@Test
	void testGradKonstruktorFull() {
		g = new Grad(1L, "Beograd");
		assertNotNull(g);
		assertEquals(1, g.getGradID());
		assertEquals("Beograd", g.getNaziv());
	}
	
	@Test
	void testGradSetGradIDNula() {
		assertThrows(IllegalArgumentException.class, ()->g.setGradID(0L));
	}
	
	@Test
	void testGradSetGradIDOk() {
		g.setGradID(1L);
		assertEquals(1, g.getGradID());
	}
	
	@Test
	void testGradSetNazivNull() {
		assertThrows(NullPointerException.class, ()->g.setNaziv(null));
	}
	
	@Test
	void testGradSetNazivOk() {
		g.setNaziv("Beograd");
		assertEquals("Beograd", g.getNaziv());
	}
	
	@Test
	void testGradToString() {
		g.setNaziv("Beograd");
		String rezultat = g.toString();
		assertTrue(rezultat.contains("Beograd"));
	}
	
	@ParameterizedTest
	@CsvSource(
	    {
	        "1, 1, true",
	        "1, 2, false",
	    }
	)
	void testEqualsObject(long gradID1, long gradID2, boolean rez) {
	    Grad g1 = new Grad();
	    g1.setGradID(gradID1);

	    Grad g2 = new Grad();
	    g2.setGradID(gradID2);

	    assertEquals(rez, g1.equals(g2), "Greska u uporedjivanju objekata");
	}
	
	@Test
	void testGradRSuTabelu()throws Exception{
		AutoCloseable ac = MockitoAnnotations.openMocks(this);
	    KreirajGradResultSet();

	    Grad g1 = new Grad();
	    List<AbstractDomainObject> lista1 = g1.vratiListu(rs);

	    Grad g2 = new Grad();
	    g2.setGradID(1L);
	    g2.setNaziv("Beograd");

	    List<AbstractDomainObject> lista2 = new ArrayList<>();
	    lista2.add(g2);

	    assertEquals(1, lista1.size(), "Broj elemenata u lista1 nije tačan");
	    assertEquals(1, lista2.size(), "Broj elemenata u lista2 nije tačan");

	    Grad gradFromRS = (Grad) lista1.get(0);
	    Grad expectedGrad = (Grad) lista2.get(0);

	    assertEquals(expectedGrad.getGradID(), gradFromRS.getGradID(), "GradID nije isti");
	    assertEquals(expectedGrad.getNaziv(), gradFromRS.getNaziv(), "Naziv nije isti");

	    ac.close();
	}


	private void KreirajGradResultSet() throws SQLException {
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
	    Mockito.when(rs.getLong("GradID")).thenReturn(1L);    
	    Mockito.when(rs.getString("Naziv")).thenReturn("Beograd");  	
	}
	
	
	
	
	
	
	
	
	
	
	
}
