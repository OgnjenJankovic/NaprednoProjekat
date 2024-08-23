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
import rs.ac.bg.fon.nprog.so.AbstractSOTest;

class SOAddTerminTest extends AbstractSOTest{

	private SOAddTermin soAddTermin;
    private Termin termin;

    @BeforeEach
    protected void setUp() throws Exception {
        super.setUp();
        soAddTermin = new SOAddTermin(dbb);
        
        Teren teren = new Teren(1L, "Vozdovac 1", "Ustanicka 23", "Betonski teren sa dva kosa.", 3000.00, new Opstina(1L, "Vozdovac"), new Grad(1L, "Beograd"));
        Korisnik korisnik = new Korisnik(1L, "Ognjen", "Jankovic", "ogi@gmail.com", "0631231234", new Tip(1L, "Premium"));
        Administrator administrator = new Administrator(1L, "Ognjen", "Jankovic", "ogi", "ogi");
        
        ArrayList<Igrac> igraci = new ArrayList<>();
        igraci.add(new Igrac());
        igraci.add(new Igrac()); 
        
        termin = new Termin();
        termin.setBrojSati(2); 
        termin.setUkupnaCena(4000.00); 
        termin.setDatumVremePocetka(new Date(System.currentTimeMillis() - 3600000));
        termin.setDatumVremeKraja(new Date(System.currentTimeMillis() + 3600000)); 
        termin.setTeren(teren);
        termin.setKorisnikOrganizator(korisnik);
        termin.setAdministrator(administrator);
        termin.setIgraci(igraci);
    }
    
    @AfterEach
    public void tearDown() throws Exception {
        super.tearDown();
        soAddTermin = null;
    }

    

    @Test
    void testValidateFailureNotTerminInstance() {
        Exception exception = assertThrows(Exception.class, () -> soAddTermin.templateExecute(new Grad()));
        assertEquals("Prosledjeni objekat nije instanca klase Termin!", exception.getMessage());
    }

    @Test
    void testValidateFailureInvalidBrojSati() {
        termin.setBrojSati(0);

        Exception exception = assertThrows(Exception.class, () -> soAddTermin.templateExecute(termin));
        assertEquals("Morate izracunati broj sati i ukupnu cenu!", exception.getMessage());
    }

    @Test
    void testValidateFailureInvalidBrojIgraca() {
        termin.getIgraci().clear();  
        termin.getIgraci().add(new Igrac());  
        Exception exception = assertThrows(Exception.class, () -> soAddTermin.templateExecute(termin));
        assertEquals("Broj igraca mora biti izmedju 2 i 10!", exception.getMessage());
    }

    

}
