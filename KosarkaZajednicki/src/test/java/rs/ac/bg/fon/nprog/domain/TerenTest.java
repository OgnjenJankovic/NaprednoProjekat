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

class TerenTest {

	Teren t;
	
	@Mock
	private ResultSet rs;
	
	@BeforeEach
	void setUp() throws Exception {
		t = new Teren();
	}

	@AfterEach
	void tearDown() throws Exception {
		t = null;
	}

	@Test
	void testTerenKonstruktorEmpty() {
	    t = new Teren();
	    assertNotNull(t);
	    assertNull(t.getTerenID()); 
	    assertNull(t.getNaziv());
	    assertNull(t.getAdresa());
	    assertNull(t.getOpis());
	    assertEquals(0.0, t.getCenaPoSatu(), 0.001);  
	    assertNull(t.getOpstina());
	    assertNull(t.getGrad());
	}
	
	@Test
	void testTerenKonstruktorFull() {
	    Teren t = new Teren(1L, "Vozdovac 1", "Ustanicka 23", "Betonski teren sa dva kosa.", 3000, new Opstina(1L, "Vozdovac"), new Grad(1L, "Beograd"));
	    assertNotNull(t);
	    assertEquals(1L, t.getTerenID());
	    assertEquals("Vozdovac 1", t.getNaziv());
	    assertEquals("Ustanicka 23", t.getAdresa());
	    assertEquals("Betonski teren sa dva kosa.", t.getOpis());
	    assertEquals(3000, t.getCenaPoSatu(), 0.001);
	    assertEquals(new Opstina(1L, "Vozdovac"), t.getOpstina());
	    assertEquals(new Grad(1L, "Beograd"), t.getGrad());
	}
	
	@Test
	void testTerenSetTerenIDNula() {
		assertThrows(IllegalArgumentException.class, ()->t.setTerenID(0L));
	}
	
	@Test
	void testTerenSetTerenIDOk() {
		t.setTerenID(1L);
		assertEquals(1, t.getTerenID());
	}
	
	@Test
	void testTerenSetNazivNull() {
		assertThrows(NullPointerException.class, ()->t.setNaziv(null));
	}
	
	@Test
	void testTerenSetNazivOk() {
		t.setNaziv("Vozdovac 1");
		assertEquals("Vozdovac 1", t.getNaziv());
	}
	
	@Test
	void testTerenSetAdresaNull() {
		assertThrows(NullPointerException.class, ()->t.setAdresa(null));
	}
	
	@Test
	void testTerenSetAdresaOk() {
		t.setAdresa("Ustanicka 23");
		assertEquals("Ustanicka 23", t.getAdresa());
	}
	
	
	@Test
	void testTerenSetOpisNull() {
		assertThrows(NullPointerException.class, ()->t.setOpis(null));
	}
	
	@Test
	void testTerenSetOpisOk() {
		t.setOpis("Betonski teren sa dva kosa.");
		assertEquals("Betonski teren sa dva kosa.", t.getOpis());
	}
	
	@Test
	void testTerenSetCenaPoSatuManjaOdNula() {
		assertThrows(IllegalArgumentException.class, ()->t.setCenaPoSatu(-1));
	}
	
	@Test
	void testTerenSetCenaPoSatuOk() {
		t.setCenaPoSatu(3000);
		assertEquals(3000, t.getCenaPoSatu());
	}
	
	@Test
	void testTerenSetOpstinaNull() {
		assertThrows(NullPointerException.class, ()->t.setOpstina(null));
	}
	
	@Test
	void testTerenSetOpstinaOk() {
		t.setOpstina(new Opstina(1L, "Vozdovac"));
		assertEquals(new Opstina(1L, "Vozdovac"), t.getOpstina());
	}
	
	
	@Test
	void testTerenSetGradNull() {
		assertThrows(NullPointerException.class, ()->t.setGrad(null));
	}
	
	@Test
	void testTerenSetGradOk() {
		t.setGrad(new Grad(1L, "Beograd"));
		assertEquals(new Grad(1L, "Beograd"), t.getGrad());
	}
	
	@Test
	void testKorisnikToString() {
		t.setNaziv("Vozdovac 1");
		String rezultat = t.toString();
		assertTrue(rezultat.contains("Vozdovac 1"));
	}
	
	@ParameterizedTest
	@CsvSource(
			{
				"1, 1, true",
				"1, 2, false",
			}
	)
	void testEqualsObject(long terenID1, long terenID2, boolean rez) {
		t.setTerenID(terenID1);
		Teren t2 = new Teren();
		t2.setTerenID(terenID2);
		assertEquals(rez, t.equals(t2));
	}
	
	@Test
	void testTerenRSuTabelu()throws Exception{
		AutoCloseable ac = MockitoAnnotations.openMocks(this);
		KreirajTerenResultSet();
		
		Teren t1 = new Teren();
		List<AbstractDomainObject> lista1 = t1.vratiListu(rs);
		
		Teren t2 = new Teren();
		t2.setTerenID(1L);
		t2.setNaziv("Vozdovac 1");
		t2.setAdresa("Ustanicka 23");
		t2.setOpis("Betonski teren sa dva kosa.");
		t2.setCenaPoSatu(3000);
		t2.setOpstina(new Opstina(1L, "Vozdovac"));
		t2.setGrad(new Grad(1L, "Beograd"));
		
		List<AbstractDomainObject> lista2 = new ArrayList();
		lista2.add(t2);
		
		assertEquals(lista1, lista2);
		ac.close();
	}

	private void KreirajTerenResultSet() throws SQLException {
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);		
		Mockito.when(rs.getLong("terenID")).thenReturn(1L);	
		Mockito.when(rs.getString("naziv")).thenReturn("Vozdovac 1");	
		Mockito.when(rs.getString("adresa")).thenReturn("Ustanicka 23");
		Mockito.when(rs.getString("opis")).thenReturn("Betonski teren sa dva kosa.");
		Mockito.when(rs.getDouble("cenaPoSatu")).thenReturn(3000d);
		Mockito.when(rs.getDouble("cenaPoSatu")).thenReturn(3000d);
		Mockito.when(rs.getLong("opstinaID")).thenReturn(1L);
		Mockito.when(rs.getLong("gradID")).thenReturn(1L);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
