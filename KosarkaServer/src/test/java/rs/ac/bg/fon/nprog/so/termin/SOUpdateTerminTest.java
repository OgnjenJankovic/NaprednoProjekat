package rs.ac.bg.fon.nprog.so.termin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

class SOUpdateTerminTest extends AbstractSOTest{

	private SOUpdateTermin soUpdateTermin;
    private Termin termin;
    private ArrayList<Igrac> igraci;

    @BeforeEach
    protected void setUp() throws Exception {
        super.setUp();
        soUpdateTermin = new SOUpdateTermin(dbb);

        termin = new Termin();
        termin.setTerminID(1L);
        termin.setDatumVremePocetka(new Date(System.currentTimeMillis() - 3600000));
        termin.setDatumVremeKraja(new Date(System.currentTimeMillis() + 3600000)); 
        termin.setBrojSati(2); 
        termin.setUkupnaCena(4000.00); 
        
        igraci = new ArrayList<>();
        igraci.add(new Igrac());
        igraci.add(new Igrac()); 
        termin.setIgraci(igraci);

        termin.setTeren(new Teren());
        termin.setKorisnikOrganizator(new Korisnik());
        termin.setAdministrator(new Administrator());
    }

    

    @Test
    void testValidateFailureInvalidObject() {
        AbstractDomainObject invalidObject = new AbstractDomainObject() {
            @Override
            public String nazivTabele() { return null; }
            @Override
            public String alijas() { return null; }
            @Override
            public String join() { return null; }
            @Override
            public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException { return null; }
            @Override
            public String koloneZaInsert() { return null; }
            @Override
            public String uslov() { return null; }
            @Override
            public String vrednostiZaInsert() { return null; }
            @Override
            public String vrednostiZaUpdate() { return null; }
            @Override
            public String uslovZaSelect() { return null; }
        };

        Exception exception = assertThrows(Exception.class, () -> soUpdateTermin.templateExecute(invalidObject));
        assertEquals("Prosledjeni objekat nije instanca klase Termin!", exception.getMessage());
    }

    @Test
    void testValidateFailureInvalidBrojSati() {
        termin.setBrojSati(0); // Invalid value
        Exception exception = assertThrows(Exception.class, () -> soUpdateTermin.templateExecute(termin));
        assertEquals("Morate izracunati broj sati i ukupnu cenu!", exception.getMessage());
    }

    @Test
    void testValidateFailureInvalidBrojIgraca() {
        termin.setIgraci(new ArrayList<>()); // Invalid value
        Exception exception = assertThrows(Exception.class, () -> soUpdateTermin.templateExecute(termin));
        assertEquals("Broj igraca mora biti izmedju 2 i 10!", exception.getMessage());
    }

    

}
