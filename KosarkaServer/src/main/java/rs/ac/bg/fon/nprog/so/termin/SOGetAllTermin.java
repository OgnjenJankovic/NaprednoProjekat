package rs.ac.bg.fon.nprog.so.termin;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Termin;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

/**
 * Klasa `SOGetAllTermin` predstavlja specijalizovanu operaciju za dohvat svih termina iz baze podataka.
 * 
 * Ova klasa nasleđuje `AbstractSO` i implementira konkretne metode za validaciju i izvršenje operacije 
 * dohvata svih objekata tipa `Termin`. Rezultat operacije je lista svih termina koji su dostupni u 
 * bazi podataka.
 * 
 * @author OgnjenJankovic
 * @version 1.1.0
 */
public class SOGetAllTermin extends AbstractSO{

	
	public SOGetAllTermin(DBBroker dbBroker) {
        super(dbBroker);
    }
	
	public SOGetAllTermin() {
        super();
    }
	
	/**
	 * Lista koja sadrži sve termine dohvaćene iz baze podataka.
	 * 
	 * Ovaj atribut se koristi za čuvanje rezultata operacije dohvata svih termina. 
	 * Nakon što se izvrši metoda `execute`, ovaj atribut se popunjava sa svim 
	 * objektima tipa `Termin` koji su pronađeni u bazi podataka.
	 */
	private ArrayList<Termin> lista;

	/**
	 * Validira prosleđeni objekat kako bi se osiguralo da je instanca klase `Termin`.
	 * 
	 * Ova metoda proverava da li je objekat koji se prosleđuje kao argument
	 * instanca klase `Termin`. Ako nije, baca se izuzetak sa odgovarajućom porukom.
	 * 
	 * @param ado objekat koji treba da se validira
	 * @throws Exception ako objekat nije instanca klase `Termin`
	 */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Termin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Termin!");
        }
    }

    /**
     * Izvršava operaciju preuzimanja svih objekata tipa `Termin` iz baze podataka.
     * 
     * Ova metoda koristi `DBBroker` da izvrši selekciju objekata iz baze podataka,
     * zatim konvertuje rezultate u listu objekata tipa `Termin` i dodeljuje je
     * atributu `lista`.
     * 
     * @param ado objekat koji se koristi za selekciju iz baze podataka, mora biti instanca klase `Termin`
     * @throws Exception ako dođe do greške tokom selekcije iz baze podataka
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> termini = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Termin>) (ArrayList<?>) termini;
    }

    /**
     * Vraća listu objekata tipa `Termin`.
     * 
     * Ova metoda omogućava pristup listi svih objekata `Termin` koji su preuzeti
     * iz baze podataka tokom izvršavanja operacije.
     * 
     * @return lista objekata tipa `Termin`
     */
    public ArrayList<Termin> getLista() {
        return lista;
    }
	
}
