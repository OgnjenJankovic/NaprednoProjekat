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
class AdministratorTest {

	Administrator a;
	
	@Mock
	ResultSet rs;
	
	@BeforeEach
	void setUp() throws Exception {
		a = new Administrator();
	}

	
	@AfterEach
	void tearDown() throws Exception {
		a = null;
	}

	@Test
	void testAdministratorKonstruktorEmpty() {
		a = new Administrator();
		assertNotNull(a);
		assertEquals(0, a.getAdministratorID());
		assertEquals(null, a.getIme());
		assertEquals(null, a.getPrezime());
		assertEquals(null, a.getUsername());
		assertEquals(null, a.getPassword());
	}
	
	@Test
	void testAdministratorKonstruktorFull() {
		a = new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi");
		assertNotNull(a);
		assertEquals(1, a.getAdministratorID());
		assertEquals("Ognjen", a.getIme());
		assertEquals("Jankovic", a.getPrezime());
		assertEquals("ogi", a.getUsername());
		assertEquals("ogi", a.getPassword());
	}
	
	@Test
	void testAdministratorSetAdministratorIDNula() {
		assertThrows(java.lang.IllegalArgumentException.class, ()->a.setAdministratorID(0L));
	}
	
	@Test
	void testAdministratorSetAdministratorIDOk() {
		a.setAdministratorID(123L);
		assertEquals(123, a.getAdministratorID());
	}
	
	@Test
	void testAdministratorSetImeNull() {
		assertThrows(java.lang.NullPointerException.class, ()->a.setIme(null));
	}
	
	@Test
	void testAdministratorSetImeOk() {
		a.setIme("Ognjen");
		assertEquals("Ognjen", a.getIme());
	}
	
	@Test
	void testAdministratorSetPrezimeNull() {
		assertThrows(java.lang.NullPointerException.class, ()->a.setPrezime(null));
	}
	
	@Test
	void testAdministratorSetPrezimeOk() {
		a.setIme("Jankovic");
		assertEquals("Jankovic", a.getPrezime());
	}
	
	@Test
	void testAdministratorSetUsernameNull() {
		assertThrows(java.lang.NullPointerException.class, ()->a.setUsername(null));
	}
	
	@Test
	void testAdministratorSetUsernameOk() {
		a.setIme("ogi");
		assertEquals("ogi", a.getUsername());
	}
	
	@Test
	void testAdministratorSetPasswordNull() {
		assertThrows(java.lang.NullPointerException.class, ()->a.setPassword(null));
	}
	
	@Test
	void testAdministratorSetPasswordOk() {
		a.setIme("ogi");
		assertEquals("ogi", a.getPassword());
	}
	
	@Test
	void testAdministratorToString() {
		a.setIme("Ognjen");
		a.setPrezime("Jankovic");
		
		String rezultat = a.toString();
		
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
	void testEqualsObject(long administratorID1, long administratorID2, boolean rez) {
		a.setAdministratorID(administratorID1);
		Administrator a2 = new Administrator();
		a2.setAdministratorID(administratorID2);
		assertEquals(rez, a.equals(a2));
	}
	
	@Test
	void testAdministratorRSuTabelu()throws Exception{
		AutoCloseable ac = MockitoAnnotations.openMocks(this);
		KreirajAdministratorResultSet();
		
		Administrator a1 = new Administrator();
		List<AbstractDomainObject> lista1 = a1.vratiListu(rs);
		
		Administrator a2 = new Administrator();
		a2.setAdministratorID(1L);
		a2.setIme("Ognjen");
		a2.setPrezime("Jankovic");
		a2.setUsername("ogi");
		a2.setPassword("ogi");
		
		List<AbstractDomainObject> lista2 = new ArrayList();
		lista2.add(a2);
		
		assertEquals(lista1, lista2);
		ac.close();
	}


	private void KreirajAdministratorResultSet() throws SQLException{
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);		
		Mockito.when(rs.getLong("administratorID")).thenReturn(1L);	
		Mockito.when(rs.getString("ime")).thenReturn("Ognjen");	
		Mockito.when(rs.getString("prezime")).thenReturn("Jankovic");	
		Mockito.when(rs.getString("username")).thenReturn("ogi");	
		Mockito.when(rs.getString("password")).thenReturn("ogi");	
	}
	
	@Test
	void testAdministratorVratiUpdate() {
		a.setIme("Ognjen");
		a.setPrezime("Jankovic");
		a.setUsername("ogi");
		a.setPassword("ogi");
		String format = a.vrednostiZaUpdate();
		assertEquals("ime='Ognjen',prezime='Jankovic', username='ogi', password='ogi'", format);
	}
	
	@Test
	void testAdministratorVratiParametre() {
		a.setIme("Ognjen");
		a.setPrezime("Jankovic");
		a.setUsername("ogi");
		a.setPassword("ogi");
		String format = a.vrednostiZaUpdate();
		assertEquals("'Ognjen', 'Jankovic', 'ogi', 'ogi'", format);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
