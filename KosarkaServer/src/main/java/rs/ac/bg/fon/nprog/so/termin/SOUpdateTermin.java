package rs.ac.bg.fon.nprog.so.termin;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Igrac;
import rs.ac.bg.fon.nprog.domain.Termin;
import rs.ac.bg.fon.nprog.so.AbstractSO;

/**
 * Klasa koja implementira operaciju ažuriranja objekta `Termin` u bazi podataka.
 * 
 * Ova klasa proširuje `AbstractSO` i obezbeđuje specifičnu implementaciju za validaciju
 * i izvršenje ažuriranja objekta `Termin`. Prvi deo operacije uključuje proveru validnosti
 * objekta, dok drugi deo vrši ažuriranje u bazi podataka.
 * 
 * @author 2000o
 * @version 1.1.0
 */
public class SOUpdateTermin extends AbstractSO{

	/**
	 * Validira objekat pre nego što se izvrši operacija ažuriranja.
	 * 
	 * Ova metoda proverava da li je prosleđeni objekat instanca klase `Termin`. Takođe, 
	 * osigurava da objekat ispunjava sve neophodne uslove pre nego što se nastavi sa
	 * ažuriranjem u bazi podataka. Konkretno, proverava da li je broj sati veći od 0 
	 * i da li broj igrača leži u dozvoljenom opsegu.
	 * 
	 * @param ado objekat koji se validira
	 * @throws Exception ako objekat nije instanca klase `Termin`, ako broj sati 
	 *         nije veći od 0, ili ako broj igrača nije između 2 i 10
	 */
	@Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Termin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Termin!");
        }

        Termin t = (Termin) ado;

        if (t.getBrojSati() < 1) {
            throw new Exception("Morate izracunati broj sati i ukupnu cenu!");
        }

        if (t.getIgraci().size() < 2 || t.getIgraci().size() > 10) {
            throw new Exception("Broj igraca mora biti izmedju 2 i 10!");
        }

    }

	/**
	 * Izvršava operaciju ažuriranja objekta u bazi podataka.
	 * 
	 * Ova metoda ažurira objekat `Termin` u bazi podataka. Pre nego što ažurira, 
	 * obavlja sledeće korake:
	 * 
	 * 1. Ažurira zapis objekta u bazi podataka.
	 * 2. Briše sve prethodno povezane objekte `Igrac` (ako je potrebno). 
	 * 3. Zatim ubacuje nove objekte `Igrac` u bazu podataka.
	 * 
	 * @param ado objekat koji se ažurira, koji mora biti instanca klase `Termin`
	 * @throws Exception ako dođe do greške prilikom ažuriranja, brisanja ili umetanja u bazu podataka
	 */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        DBBroker.getInstance().update(ado);

        Termin t = (Termin) ado;
        
        DBBroker.getInstance().delete(t.getIgraci().get(0));

        for (Igrac igrac : t.getIgraci()) {
            DBBroker.getInstance().insert(igrac);
        }

        
    }
	
}
