package rs.ac.bg.fon.nprog.so.termin;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Igrac;
import rs.ac.bg.fon.nprog.domain.Termin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import rs.ac.bg.fon.nprog.so.AbstractSO;

/**
 * Klasa `SOAddTermin` predstavlja specifikaciju za dodavanje termina u sistem.
 * 
 * Ova klasa nasleđuje `AbstractSO` i implementira specifične metode za validaciju
 * i izvršenje operacije dodavanja novog termina u bazu podataka. Klasa koristi
 * obrasce za rad sa objektima i bazom podataka kako bi se obezbedilo da svi
 * podaci budu pravilno obrađeni i sačuvani.
 * 
 * @author OgnjenJankovic
 * @version 1.1.0
 */
public class SOAddTermin extends AbstractSO{

	
	public SOAddTermin(DBBroker dbBroker) {
        super(dbBroker);
    }
	
	public SOAddTermin() {
        super();
    }
	
	/**
	 * Validira prosleđeni objekat kako bi se osiguralo da ispunjava uslove
	 * za dodavanje termina u bazu podataka.
	 * 
	 * Ova metoda proverava da li je prosleđeni objekat instanca klase `Termin`.
	 * Takođe, obavlja dodatne provere kao što su:
	 * <ul>
	 *     <li>Proverava da li je broj sati veći ili jednak 1.</li>
	 *     <li>Proverava da li broj igrača u terminu odgovara opsegu od 2 do 10.</li>
	 * </ul>
	 * Ako bilo koja od provera ne uspe, baca se izuzetak sa odgovarajućom porukom.
	 * 
	 * @param ado Objekat koji treba da bude validiran. Treba da bude instanca klase `Termin`.
	 * @throws Exception Ako prosleđeni objekat nije instanca klase `Termin` ili ako ne zadovoljava
	 *                    uslove za broj sati i broj igrača.
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
	 * Izvršava operaciju dodavanja novog termina u bazu podataka.
	 * 
	 * Ova metoda prvo izvršava SQL upit za umetanje termina u bazu podataka koristeći `DBBroker`.
	 * Nakon umetanja termina, metoda koristi `PreparedStatement` da dobije generisani ključ (ID) novog termina.
	 * Zatim, postavlja ID termina u objekat `Termin` i izvršava umetanje svih igrača koji su povezani sa tim terminom.
	 * 
	 * Koraci uključeni u ovoj metodi su:
	 * <ol>
	 *     <li>Izvršavanje SQL upita za umetanje termina i dobijanje generisanog ključa (ID) termina.</li>
	 *     <li>Postavljanje ID-a termina u objekat `Termin`.</li>
	 *     <li>Iteracija kroz sve igrače povezane sa terminom i umetanje svakog igrača u bazu podataka.</li>
	 * </ol>
	 * 
	 * @param ado Objekat koji predstavlja novi termin. Treba da bude instanca klase `Termin`.
	 * @throws Exception Ako dođe do greške prilikom umetanja termina ili igrača u bazu podataka.
	 */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long terminID = tableKeys.getLong(1);

        Termin t = (Termin) ado;
        t.setTerminID(terminID);

        
        for (Igrac igrac : t.getIgraci()) {
            igrac.setTermin(t);
            DBBroker.getInstance().insert(igrac);
        }

    }
	
}
