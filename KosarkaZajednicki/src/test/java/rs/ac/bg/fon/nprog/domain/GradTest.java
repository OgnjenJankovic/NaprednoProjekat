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
		assertEquals(0, g.getGradID());
		assertEquals(null, g.getNaziv());
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
		g.setGradID(gradID1);
		Grad g2 = new Grad();
		g2.setGradID(gradID2);
		assertEquals(rez, g.equals(g2));
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
		
		List<AbstractDomainObject> lista2 = new ArrayList();
		lista2.add(g2);
		
		assertEquals(lista1, lista2);
		ac.close();
	}


	private void KreirajGradResultSet() throws SQLException {
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);		
		Mockito.when(rs.getLong("gradID")).thenReturn(1L);	
		Mockito.when(rs.getString("naziv")).thenReturn("Beograd");	
	}
	
	
	
	
	
	
	
	
	
	
	
}
