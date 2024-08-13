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

class KorisnikTest {

	Korisnik k;
	
	@Mock
	private ResultSet rs;
	
	@BeforeEach
	void setUp() throws Exception {
		k = new Korisnik();
	}

	@AfterEach
	void tearDown() throws Exception {
		k = null;
	}

	@Test
	void testKorisnikKonstruktorEmpty() {
	    k = new Korisnik();
	    assertNotNull(k);
	    assertNull(k.getKorisnikID()); 
	    assertNull(k.getIme());        
	    assertNull(k.getPrezime());    
	    assertNull(k.getEmail());    
	    assertNull(k.getTelefon());   
	    assertNull(k.getTipKorisnika()); 
	}

	@Test
	void testKorisnikKonstruktorFull() {
		k = new Korisnik(1L, "Ognjen","Jankovic","ogi@gmail.com","0631231234", new Tip(1L, "Premium"));
		assertNotNull(k);
		assertEquals(1, k.getKorisnikID());
		assertEquals("Ognjen", k.getIme());
		assertEquals("Jankovic", k.getPrezime());
		assertEquals("ogi@gmail.com", k.getEmail());
		assertEquals("0631231234", k.getTelefon());
		assertEquals(new Tip(1L, "Premium"), k.getTipKorisnika());
	}
	
	@Test
	void testKorisnikSetKorisnikIDNula() {
		assertThrows(IllegalArgumentException.class, ()->k.setKorisnikID(0L));
	}
	
	@Test
	void testKorisnikSetKorisnikIDOk() {
		k.setKorisnikID(1L);
		assertEquals(1, k.getKorisnikID());
	}
	
	@Test
	void testKorisnikSetImeNull() {
		assertThrows(NullPointerException.class, ()->k.setIme(null));
	}
	
	@Test
	void testKorisnikSetImeOk() {
		k.setIme("Ognjen");
		assertEquals("Ognjen", k.getIme());
	}
	
	@Test
	void testKorisnikSetPrezimeNull() {
		assertThrows(NullPointerException.class, ()->k.setPrezime(null));
	}
	
	@Test
	void testKorisnikSetPrezimeOk() {
		k.setPrezime("Jankovic");
		assertEquals("Jankovic", k.getPrezime());
	}
	
	@Test
	void testKorisnikSetEmailNull() {
		assertThrows(NullPointerException.class, ()->k.setEmail(null));
	}
	
	@Test
	void testKorisnikSetEmailOk() {
		k.setEmail("ogi@gmail.com");
		assertEquals("ogi@gmail.com", k.getEmail());
	}
	
	@Test
	void testKorisnikSetTelefonNull() {
		assertThrows(NullPointerException.class, ()->k.setTelefon(null));
	}
	
	@Test
	void testKorisnikSetTelefonOk() {
		k.setTelefon("0631231234");
		assertEquals("0631231234", k.getTelefon());
	}
	
	@Test
	void testKorisnikSetTipKorisnikaNull() {
		assertThrows(NullPointerException.class, ()->k.setTipKorisnika(null));
	}
	
	@Test
	void testKorisnikSetTipKorisnikaOk() {
		k.setTipKorisnika(new Tip(1L, "Free"));
		assertEquals(new Tip(1L, "Free"), k.getTipKorisnika());
	}
	
	@Test
	void testKorisnikToString() {
		k.setIme("Ognjen");
		k.setPrezime("Jankovic");
		String rezultat = k.toString();
		assertTrue(rezultat.contains("Ognjen"));
		assertTrue(rezultat.contains("Jankovic"));
	}
	
	@ParameterizedTest
	@CsvSource(
			{
				"1, 1, true",
				"1, 2, false",
			}
	)
	void testEqualsObject(long korisnikID1, long korisnikID2, boolean rez) {
		k.setKorisnikID(korisnikID1);
		Korisnik k2 = new Korisnik();
		k2.setKorisnikID(korisnikID2);
		assertEquals(rez, k.equals(k2));
	}
	
	@Test
	void testKorisnikRSuTabelu()throws Exception{
		AutoCloseable ac = MockitoAnnotations.openMocks(this);
	    KreirajKorisnikResultSet();
	    
	    Korisnik k1 = new Korisnik();
	    List<AbstractDomainObject> lista1 = k1.vratiListu(rs);
	    
	    Tip t = new Tip();
	    t.setTipID(1L);
	    t.setNaziv("Free");
	    
	    Korisnik k2 = new Korisnik();
	    k2.setKorisnikID(1L);
	    k2.setIme("Ognjen");
	    k2.setPrezime("Jankovic");
	    k2.setEmail("ogi@gmail.com");
	    k2.setTelefon("0631231234");
	    k2.setTipKorisnika(t);
	    
	    List<AbstractDomainObject> lista2 = new ArrayList<>();
	    lista2.add(k2);
	    
	    assertEquals(lista2, lista1);
	    
	    ac.close();
	}

	private void KreirajKorisnikResultSet() throws SQLException {
	    Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
	    Mockito.when(rs.getLong("KorisnikID")).thenReturn(1L);
	    Mockito.when(rs.getString("Ime")).thenReturn("Ognjen");
	    Mockito.when(rs.getString("Prezime")).thenReturn("Jankovic");
	    Mockito.when(rs.getString("Email")).thenReturn("ogi@gmail.com");
	    Mockito.when(rs.getString("Telefon")).thenReturn("0631231234");
	    Mockito.when(rs.getLong("TipID")).thenReturn(1L);
	    Mockito.when(rs.getString("Naziv")).thenReturn("Free");
	}
	
	@Test
	void testKorisnikVratiParametre() {
	    k.setKorisnikID(1L); 
	    k.setIme("Ognjen");
	    k.setPrezime("Jankovic");
	    k.setEmail("ogi@gmail.com");
	    k.setTelefon("0631231234");
	    k.setTipKorisnika(new Tip(1L, "Premium"));
	    String format = k.vrednostiZaInsert();
	    assertEquals("'Ognjen', 'Jankovic', 'ogi@gmail.com', '0631231234', 1", format);
	}
	
	
	
	
	
	

}
