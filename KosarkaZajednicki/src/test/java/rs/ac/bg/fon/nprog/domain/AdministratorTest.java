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
	    assertNull(a.getAdministratorID());  
	    assertNull(a.getIme());
	    assertNull(a.getPrezime());
	    assertNull(a.getUsername());
	    assertNull(a.getPassword());
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
		a.setPrezime("Jankovic");
		assertEquals("Jankovic", a.getPrezime());
	}
	
	@Test
	void testAdministratorSetUsernameNull() {
		assertThrows(java.lang.NullPointerException.class, ()->a.setUsername(null));
	}
	
	@Test
	void testAdministratorSetUsernameOk() {
		a.setUsername("ogi");
		assertEquals("ogi", a.getUsername());
	}
	
	@Test
	void testAdministratorSetPasswordNull() {
		assertThrows(java.lang.NullPointerException.class, ()->a.setPassword(null));
	}
	
	@Test
	void testAdministratorSetPasswordOk() {
		a.setPassword("ogi");
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
	    
	    Administrator a = new Administrator();
	    List<AbstractDomainObject> lista = a.vratiListu(rs);
	    
	    Administrator expectedAdmin = new Administrator();
	    expectedAdmin.setAdministratorID(1L);
	    expectedAdmin.setIme("Ognjen");
	    expectedAdmin.setPrezime("Jankovic");
	    expectedAdmin.setUsername("ogi");
	    expectedAdmin.setPassword("ogi");
	    
	    List<AbstractDomainObject> expectedList = new ArrayList<>();
	    expectedList.add(expectedAdmin);
	    
	    assertEquals(expectedList.size(), lista.size(), "Broj elemenata u listama nije isti");

	    Administrator actualAdmin = (Administrator) lista.get(0);
	    assertEquals(expectedAdmin.getAdministratorID(), actualAdmin.getAdministratorID(), "AdministratorID nije isti");
	    assertEquals(expectedAdmin.getIme(), actualAdmin.getIme(), "Ime nije isto");
	    assertEquals(expectedAdmin.getPrezime(), actualAdmin.getPrezime(), "Prezime nije isto");
	    assertEquals(expectedAdmin.getUsername(), actualAdmin.getUsername(), "Username nije isti");
	    assertEquals(expectedAdmin.getPassword(), actualAdmin.getPassword(), "Password nije isti");
	    
	    ac.close();
	}


	private void KreirajAdministratorResultSet() throws SQLException{
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
	    Mockito.when(rs.getLong("AdministratorID")).thenReturn(1L);
	    Mockito.when(rs.getString("Ime")).thenReturn("Ognjen");
	    Mockito.when(rs.getString("Prezime")).thenReturn("Jankovic");
	    Mockito.when(rs.getString("Username")).thenReturn("ogi");
	    Mockito.when(rs.getString("Password")).thenReturn("ogi");
	}
	
	
	
	@Test
	void testAdministratorVratiParametre() {
		a.setIme("Ognjen");
		a.setPrezime("Jankovic");
		a.setUsername("ogi");
		a.setPassword("ogi");
		String format = a.vrednostiZaInsert();
		assertEquals("'Ognjen', 'Jankovic', 'ogi', 'ogi'", format);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
