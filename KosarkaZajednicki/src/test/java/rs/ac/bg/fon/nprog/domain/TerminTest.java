package rs.ac.bg.fon.nprog.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.sql.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class TerminTest {

	Termin t;
	
	@Mock
	private ResultSet rs;
	
	@BeforeEach
	void setUp() throws Exception {
		t = new Termin();
	}

	@AfterEach
	void tearDown() throws Exception {
		t = null;
	}

	@Test
	void testKorisnikKonstruktorEmpty() {
	    t = new Termin();
	    assertNotNull(t);
	    assertEquals(null, t.getTerminID());
	    assertEquals(null, t.getDatumVremePocetka());
	    assertEquals(null, t.getDatumVremeKraja());
	    assertEquals(0, t.getBrojSati());
	    assertEquals(0.0, t.getUkupnaCena(), 0.001);
	    assertEquals(null, t.getTeren());
	    assertEquals(null, t.getKorisnikOrganizator());
	    assertEquals(null, t.getAdministrator());
	    assertEquals(null, t.getIgraci());
	}
	
	@Test
	void testKorisnikKonstruktorFull() {
	    Date datumVremePocetka = new Date(123, 11, 29, 18, 0, 0);
	    Date datumVremeKraja = new Date(123, 11, 29, 20, 0, 0);
	    Teren teren = new Teren(1L, "Vozdovac 1", "Ustanicka 23", "Betonski teren sa dva kosa.", 3000, new Opstina(1L, "Vozdovac"), new Grad(1L, "Beograd"));
	    Korisnik korisnik = new Korisnik(1L, "Ognjen", "Jankovic", "ogi@gmail.com", "0631231234", new Tip(1L, "Premium"));
	    Administrator administrator = new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi");

	    t = new Termin(1L, datumVremePocetka, datumVremeKraja, 2, 8000, teren, korisnik, administrator, new ArrayList<>());
	    
	    assertNotNull(t);
	    assertEquals(1L, t.getTerminID().longValue());
	    assertEquals(datumVremePocetka, t.getDatumVremePocetka());
	    assertEquals(datumVremeKraja, t.getDatumVremeKraja());
	    assertEquals(2, t.getBrojSati());
	    assertEquals(8000, t.getUkupnaCena(), 0.001);
	    assertEquals(teren, t.getTeren());
	    assertEquals(korisnik, t.getKorisnikOrganizator());
	    assertEquals(administrator, t.getAdministrator());
	    assertTrue(t.getIgraci().isEmpty());
	}
	
	@Test
	void testTerminSetTerminIDNula() {
		assertThrows(IllegalArgumentException.class, ()->t.setTerminID(0L));
	}
	
	@Test
	void testTerminSetTerminIDOk() {
		t.setTerminID(1L);
		assertEquals(1, t.getTerminID());
	}
	
	@Test
	void testTerminSetBrojSatiManjiOdNula() {
		assertThrows(IllegalArgumentException.class, ()->t.setBrojSati(-1));
	}
	
	@Test
	void testTerminSetBrojSatiOk() {
		t.setBrojSati(2);
		assertEquals(2, t.getBrojSati());
	}
	
	@Test
	void testTerminSetUkupnaCenaManjaOdNula() {
		assertThrows(IllegalArgumentException.class, ()->t.setUkupnaCena(-1));
	}
	
	@Test
	void testTerminSetUkupnaCenaOk() {
		t.setUkupnaCena(8000);
		assertEquals(8000, t.getUkupnaCena());
	}
	
	@Test
	void testTerminSetTerenNull() {
		assertThrows(NullPointerException.class, ()->t.setTeren(null));
	}
	
	@Test
	void testTerminSetTerenOk() {
		t.setTeren(new Teren(1L, "Vozdovac 1", "Ustanicka 23", "Betonski teren sa dva kosa.", 3000, new Opstina(1L, "Vozdovac"), new Grad(1L, "Beograd")));
		assertEquals(new Teren(1L, "Vozdovac 1", "Ustanicka 23", "Betonski teren sa dva kosa.", 3000, new Opstina(1L, "Vozdovac"), new Grad(1L, "Beograd")), t.getTeren());
	}
	
	@Test
	void testTerminSetKorisnikNull() {
		assertThrows(NullPointerException.class, ()->t.setKorisnikOrganizator(null));
	}
	
	@Test
	void testTerminSetKorisnikOk() {
		t.setKorisnikOrganizator(new Korisnik(1L, "Ognjen","Jankovic","ogi@gmail.com","0631231234", new Tip(1L, "Premium")));
		assertEquals(new Korisnik(1L, "Ognjen","Jankovic","ogi@gmail.com","0631231234", new Tip(1L, "Premium")), t.getKorisnikOrganizator());
	}
	
	
	@Test
	void testTerminSetAdministratorNull() {
		assertThrows(NullPointerException.class, ()->t.setAdministrator(null));
	}
	
	@Test
	void testTerminSetAdministratorOk() {
		t.setAdministrator(new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi"));
		assertEquals(new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi"), t.getAdministrator());
	}
	
	@Test
	void testTerenRSuTabelu() throws Exception {
	    AutoCloseable ac = MockitoAnnotations.openMocks(this);
	    KreirajTerminResultSet();
	    
	    Termin t1 = new Termin();
	    List<AbstractDomainObject> lista1 = t1.vratiListu(rs);
	    
	    Termin t2 = new Termin();
	    t2.setTerminID(1L);
	    t2.setDatumVremePocetka(new Date(123, 11, 29, 18, 0, 0));
	    t2.setDatumVremeKraja(new Date(123, 11, 29, 20, 0, 0));
	    t2.setBrojSati(2);
	    t2.setUkupnaCena(8000);
	    t2.setTeren(new Teren(1L, "Vozdovac 1", "Ustanicka 23", "Betonski teren sa dva kosa.", 3000, new Opstina(1L, "Vozdovac"), new Grad(1L, "Beograd")));
	    t2.setKorisnikOrganizator(new Korisnik(1L, "Ognjen", "Jankovic", "ogi@gmail.com", "0631231234", new Tip(1L, "Premium")));
	    t2.setAdministrator(new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi"));
	    
	    List<AbstractDomainObject> lista2 = new ArrayList<>();
	    lista2.add(t2);
	    
	    assertEquals(lista1, lista2);
	    ac.close();
	}

	private void KreirajTerminResultSet() throws SQLException {
	    long timeInMillis1 = new GregorianCalendar(2023, Calendar.DECEMBER, 29, 18, 0).getTimeInMillis();
	    long timeInMillis2 = new GregorianCalendar(2023, Calendar.DECEMBER, 29, 20, 0).getTimeInMillis();
	    java.sql.Timestamp timestamp1 = new java.sql.Timestamp(timeInMillis1);
	    java.sql.Timestamp timestamp2 = new java.sql.Timestamp(timeInMillis2);

	    Mockito.when(rs.next()).thenReturn(true).thenReturn(false);		
	    Mockito.when(rs.getLong("terminID")).thenReturn(1L);	
	    Mockito.when(rs.getTimestamp("datumVremePocetka")).thenReturn(timestamp1);
	    Mockito.when(rs.getTimestamp("datumVremeKraja")).thenReturn(timestamp2);
	    Mockito.when(rs.getInt("brojSati")).thenReturn(2);
	    Mockito.when(rs.getDouble("ukupnaCena")).thenReturn(8000d);
	    
	    Mockito.when(rs.getLong("terenID")).thenReturn(1L);
	    Mockito.when(rs.getString("terenNaziv")).thenReturn("Vozdovac 1");
	    Mockito.when(rs.getString("terenAdresa")).thenReturn("Ustanicka 23");
	    Mockito.when(rs.getString("terenOpis")).thenReturn("Betonski teren sa dva kosa.");
	    Mockito.when(rs.getDouble("terenCenaPoSatu")).thenReturn(3000d);
	    Mockito.when(rs.getLong("opstinaID")).thenReturn(1L);
	    Mockito.when(rs.getString("opstinaNaziv")).thenReturn("Vozdovac");
	    Mockito.when(rs.getLong("gradID")).thenReturn(1L);
	    Mockito.when(rs.getString("gradNaziv")).thenReturn("Beograd");

	    Mockito.when(rs.getLong("korisnikOrganizator")).thenReturn(1L);
	    Mockito.when(rs.getString("korisnikIme")).thenReturn("Ognjen");
	    Mockito.when(rs.getString("korisnikPrezime")).thenReturn("Jankovic");
	    Mockito.when(rs.getString("korisnikEmail")).thenReturn("ogi@gmail.com");
	    Mockito.when(rs.getString("korisnikTelefon")).thenReturn("0631231234");
	    Mockito.when(rs.getLong("tipID")).thenReturn(1L);
	    Mockito.when(rs.getString("tipNaziv")).thenReturn("Premium");

	    Mockito.when(rs.getLong("administrator")).thenReturn(1L);
	    Mockito.when(rs.getString("administratorIme")).thenReturn("Ognjen");
	    Mockito.when(rs.getString("administratorPrezime")).thenReturn("Jankovic");
	    Mockito.when(rs.getString("administratorUsername")).thenReturn("ogi");
	    Mockito.when(rs.getString("administratorPassword")).thenReturn("ogi");
	}
	
	@ParameterizedTest
	@CsvSource(
			{
				"1, 1, true",
				"1, 2, false",
			}
	)
	void testEqualsObject(long terminID1, long terminID2, boolean rez) {
		t.setTerminID(terminID1);
		Termin t2 = new Termin();
		t2.setTerminID(terminID2);
		assertEquals(rez, t.equals(t2));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
