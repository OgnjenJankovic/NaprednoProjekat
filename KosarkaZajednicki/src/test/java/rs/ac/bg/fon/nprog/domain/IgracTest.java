package rs.ac.bg.fon.nprog.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class IgracTest {

	Igrac i;
	
	@Mock
	private ResultSet rs;
	
	@BeforeEach
	void setUp() throws Exception {
		i = new Igrac();
	}

	@AfterEach
	void tearDown() throws Exception {
		i = null;
	}

	@Test
	void testKorisnikKonstruktorEmpty() {
		i = new Igrac();
		assertNotNull(i);
		assertEquals(null, i.getTermin());
		assertEquals(0, i.getRbIgraca());
		assertEquals(null, i.getNapomena());
		assertEquals(null, i.getKorisnikIgrac());
	}
	
	@Test
	void testKorisnikKonstruktorFull() {
	    Termin termin = new Termin(1L, new Date(123, 11, 29, 18, 0, 0), new Date(123, 11, 29, 20, 0, 0), 2, 8000, 
	        new Teren(1L, "Vozdovac 1", "Ustanicka 23", "Betonski teren sa dva kosa.", 3000, 
	        new Opstina(1L, "Vozdovac"), new Grad(1L, "Beograd")),
	        new Korisnik(1L, "Ognjen","Jankovic","ogi@gmail.com","0631231234", new Tip(1L, "Premium")), 
	        new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi"), null);
	    
	    Korisnik korisnik = new Korisnik(1L, "Ognjen","Jankovic","ogi@gmail.com","0631231234", new Tip(1L, "Premium"));
	    Igrac expectedIgrac = new Igrac(termin, 1, "/", korisnik);
	    
	    Igrac actualIgrac = new Igrac(termin, 1, "/", korisnik);
	    
	    assertNotNull(actualIgrac);
	    assertEquals(expectedIgrac, actualIgrac);  
	}
	
	
	@Test
	void testIgracSetTerminNull() {
		assertThrows(NullPointerException.class, ()->i.setTermin(null));
	}
	
	@Test
	void testIgracSetTerminOk() {
	    Termin expectedTermin = new Termin(1L, new Date(123, 11, 29, 18, 0, 0), new Date(123, 11, 29, 20, 0, 0), 2, 8000, 
	        new Teren(1L, "Vozdovac 1", "Ustanicka 23", "Betonski teren sa dva kosa.", 3000, new Opstina(1L, "Vozdovac"), new Grad(1L, "Beograd")),
	        new Korisnik(1L, "Ognjen", "Jankovic", "ogi@gmail.com", "0631231234", new Tip(1L, "Premium")), 
	        new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi"), null);

	    i.setTermin(expectedTermin);

	    assertEquals(expectedTermin, i.getTermin());
	}
	
	@Test
	void testIgracSetRbIgracaNula() {
		assertThrows(IllegalArgumentException.class, ()->i.setRbIgraca(0));
	}
	
	@Test
	void testIgracSetRbIgracaOk() {
		i.setRbIgraca(1);
		assertEquals(1, i.getRbIgraca());
	}
	
	
	@Test
	void testIgracSetNapomenaNull() {
		assertThrows(NullPointerException.class, ()->i.setNapomena(null));
	}
	
	@Test
	void testIgracSetNapomenaOk() {
		i.setNapomena("/");
		assertEquals("/", i.getNapomena());
	}
	
	@Test
	void testIgracSetKorisnikIgracNull() {
		assertThrows(NullPointerException.class, ()->i.setKorisnikIgrac(null));
	}
	
	@Test
	void testIgracSetKorisnikIgracOk() {
		i.setKorisnikIgrac(new Korisnik(1L, "Ognjen","Jankovic","ogi@gmail.com","0631231234", new Tip(1L, "Premium")));
		assertEquals(new Korisnik(1L, "Ognjen","Jankovic","ogi@gmail.com","0631231234", new Tip(1L, "Premium")), i.getKorisnikIgrac());
	}
	
	
	@Test
	void testIgracToString() {
	    Igrac i = new Igrac();
	    i.setRbIgraca(1);
	    String rezultat = i.toString();
	    assertTrue(rezultat.contains("1"));
	}
	
	@ParameterizedTest
	@CsvSource(
			{
				"1, 1, true",
				"1, 2, false",
			}
	)
	void testEqualsObject(int rbIgraca1, int rbIgraca2, boolean rez) {
		i.setRbIgraca(rbIgraca1);
		Igrac i2 = new Igrac();
		i2.setRbIgraca(rbIgraca2);
		assertEquals(rez, i.equals(i2));
	}
	
	@Test
	void testTerenRSuTabelu()throws Exception{
		AutoCloseable ac = MockitoAnnotations.openMocks(this);
		KreirajIgracResultSet();
		
		Igrac i1 = new Igrac();
		List<AbstractDomainObject> lista1 = i1.vratiListu(rs);
		
		Igrac i2 = new Igrac();
		i2.setTermin(new Termin(1L, new Date(123, 11, 29, 18, 0, 0),new Date(123, 11, 29, 20, 0, 0),2, 8000, new Teren(1L, "Vozdovac 1", "Ustanicka 23", "Betonski teren sa dva kosa.", 3000, new Opstina(1L, "Vozdovac"), new Grad(1L, "Beograd")),
				new Korisnik(1L, "Ognjen","Jankovic","ogi@gmail.com","0631231234", new Tip(1L, "Premium")), new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi"), null));
		i2.setRbIgraca(1);
		i2.setNapomena("/");
		i2.setKorisnikIgrac(new Korisnik(1L, "Ognjen","Jankovic","ogi@gmail.com","0631231234", new Tip(1L, "Premium")));
		
		List<AbstractDomainObject> lista2 = new ArrayList();
		lista2.add(i2);
		
		assertEquals(lista1, lista2);
		ac.close();
	}

	private void KreirajIgracResultSet() throws SQLException {
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);		
		Mockito.when(rs.getLong("terminID")).thenReturn(1L);	
		Mockito.when(rs.getInt("rbIgraca")).thenReturn(1);	
		Mockito.when(rs.getString("napomena")).thenReturn("/");
		Mockito.when(rs.getLong("korisnikID")).thenReturn(1L);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
