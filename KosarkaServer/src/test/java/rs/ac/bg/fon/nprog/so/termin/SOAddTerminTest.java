package rs.ac.bg.fon.nprog.so.termin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Administrator;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Igrac;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Opstina;
import rs.ac.bg.fon.nprog.domain.Teren;
import rs.ac.bg.fon.nprog.domain.Termin;
import rs.ac.bg.fon.nprog.domain.Tip;

class SOAddTerminTest {

	private SOAddTermin soAddTermin;
    private DBBroker dbBroker;
    private Termin termin;

    @BeforeEach
    public void setUp() throws SQLException {
        soAddTermin = new SOAddTermin();
        dbBroker = mock(DBBroker.class);

        when(DBBroker.getInstance()).thenReturn(dbBroker);

        PreparedStatement ps = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);
        when(dbBroker.insert(any(AbstractDomainObject.class))).thenReturn(ps);
        when(ps.getGeneratedKeys()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getLong(1)).thenReturn(1L);

        termin = new Termin(1L, new Date(123, 11, 29, 18, 0, 0),new Date(123, 11, 29, 20, 0, 0),2, 8000, new Teren(1L, "Vozdovac 1", "Ustanicka 23", "Betonski teren sa dva kosa.", 3000, new Opstina(1L, "Vozdovac"), new Grad(1L, "Beograd")),
				new Korisnik(1L, "Ognjen","Jankovic","ogi@gmail.com","0631231234", new Tip(1L, "Premium")), new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi"), new ArrayList<>());
        
        termin.getIgraci().add(new Igrac(new Termin(1L, new Date(123, 11, 29, 18, 0, 0),new Date(123, 11, 29, 20, 0, 0),2, 8000, new Teren(1L, "Vozdovac 1", "Ustanicka 23", "Betonski teren sa dva kosa.", 3000, new Opstina(1L, "Vozdovac"), new Grad(1L, "Beograd")),
				new Korisnik(1L, "Ognjen","Jankovic","ogi@gmail.com","0631231234", new Tip(1L, "Premium")), new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi"), null),
				1, "/", new Korisnik(1L, "Ognjen","Jankovic","ogi@gmail.com","0631231234", new Tip(1L, "Premium"))));
        termin.getIgraci().add(new Igrac(new Termin(1L, new Date(123, 11, 29, 18, 0, 0),new Date(123, 11, 29, 20, 0, 0),2, 8000, new Teren(1L, "Vozdovac 1", "Ustanicka 23", "Betonski teren sa dva kosa.", 3000, new Opstina(1L, "Vozdovac"), new Grad(1L, "Beograd")),
				new Korisnik(2L, "Veljko","Nikolic","veljko@gmail.com","0654645434", new Tip(1L, "Free")), new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi"), null),
				1, "/", new Korisnik(1L, "Ognjen","Jankovic","ogi@gmail.com","0631231234", new Tip(1L, "Premium"))));
    }

    @Test
    public void testValidateValidTermin() throws Exception {
        assertDoesNotThrow(() -> soAddTermin.validate(termin));
    }

    @Test
    public void testValidateInvalidBrojSati() {
        termin.setBrojSati(0);
        Exception exception = assertThrows(Exception.class, () -> soAddTermin.validate(termin));
        assertEquals("Morate izracunati broj sati i ukupnu cenu!", exception.getMessage());
    }

    @Test
    public void testValidateInvalidIgraci() {
        termin.getIgraci().clear();
        Exception exception = assertThrows(Exception.class, () -> soAddTermin.validate(termin));
        assertEquals("Broj igraca mora biti izmedju 2 i 10!", exception.getMessage());
    }

    @Test
    public void testExecute() throws Exception {
        soAddTermin.execute(termin);

        verify(dbBroker, times(1)).insert(termin);
        for (Igrac igrac : termin.getIgraci()) {
            verify(dbBroker, times(1)).insert(igrac);
        }
    }

}
