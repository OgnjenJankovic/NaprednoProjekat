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
		assertEquals(0, t.getTerminID());
		assertEquals(null, t.getDatumVremePocetka());
		assertEquals(null, t.getDatumVremeKraja());
		assertEquals(0, t.getBrojSati());
		assertEquals(0, t.getUkupnaCena());
		assertEquals(null, t.getTeren());
		assertEquals(null, t.getKorisnikOrganizator());
		assertEquals(null, t.getAdministrator());
		assertEquals(null, t.getIgraci());
	}
	
	@Test
	void testKorisnikKonstruktorFull() {
		t = new Termin(1L, new Date(123, 11, 29, 18, 0, 0),new Date(123, 11, 29, 20, 0, 0),2, 8000, new Teren(1L, "Vozdovac 1", "Ustanicka 23", "Betonski teren sa dva kosa.", 3000, new Opstina(1L, "Vozdovac"), new Grad(1L, "Beograd")),
				new Korisnik(1L, "Ognjen","Jankovic","ogi@gmail.com","0631231234", new Tip(1L, "Premium")), new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi"), null);
		assertNotNull(t);
		assertEquals(1L, t.getTerminID());
		assertEquals(new Date(123, 11, 29, 18, 0, 0), t.getDatumVremePocetka());
		assertEquals(new Date(123, 11, 29, 20, 0, 0), t.getDatumVremeKraja());
		assertEquals(2, t.getBrojSati());
		assertEquals(8000, t.getUkupnaCena());
		assertEquals(new Teren(1L, "Vozdovac 1", "Ustanicka 23", "Betonski teren sa dva kosa.", 3000, new Opstina(1L, "Vozdovac"), new Grad(1L, "Beograd")), t.getTeren());
		assertEquals(new Korisnik(1L, "Ognjen","Jankovic","ogi@gmail.com","0631231234", new Tip(1L, "Premium")), t.getKorisnikOrganizator());
		assertEquals(new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi"), t.getAdministrator());
		assertEquals(new ArrayList<>(), t.getIgraci());
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
	void testTerminSetBrojSatiNula() {
		assertThrows(IllegalArgumentException.class, ()->t.setBrojSati(0));
	}
	
	@Test
	void testTerminSetBrojSatiOk() {
		t.setBrojSati(2);
		assertEquals(2, t.getBrojSati());
	}
	
	@Test
	void testTerminSetUkupnaCenaNula() {
		assertThrows(IllegalArgumentException.class, ()->t.setUkupnaCena(0));
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
	void testTerenRSuTabelu()throws Exception{
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
		t2.setKorisnikOrganizator(new Korisnik(1L, "Ognjen","Jankovic","ogi@gmail.com","0631231234", new Tip(1L, "Premium")));
		t2.setAdministrator(new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi"));
		
		List<AbstractDomainObject> lista2 = new ArrayList();
		lista2.add(t2);
		
		assertEquals(lista1, lista2);
		ac.close();
	}

	private void KreirajTerminResultSet() throws SQLException {
		long timeInMillis = new GregorianCalendar(2023, Calendar.DECEMBER, 29, 18, 0).getTimeInMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(timeInMillis);
		long timeInMilli2s = new GregorianCalendar(2023, Calendar.DECEMBER, 29, 20, 0).getTimeInMillis();
		java.sql.Timestamp timestamp2 = new java.sql.Timestamp(timeInMilli2s);
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);		
		Mockito.when(rs.getLong("terminID")).thenReturn(1L);	
		Mockito.when(rs.getTimestamp("datumVremePocetka")).thenReturn(timestamp);
		Mockito.when(rs.getTimestamp("datumVremePocetka")).thenReturn(timestamp2);
		Mockito.when(rs.getInt("brojSati")).thenReturn(2);
		Mockito.when(rs.getDouble("ukupnaCena")).thenReturn(8000d);
		Mockito.when(rs.getLong("terenID")).thenReturn(1L);
		Mockito.when(rs.getLong("korisnikOrganizator")).thenReturn(1L);
		Mockito.when(rs.getLong("administrator")).thenReturn(1L);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
