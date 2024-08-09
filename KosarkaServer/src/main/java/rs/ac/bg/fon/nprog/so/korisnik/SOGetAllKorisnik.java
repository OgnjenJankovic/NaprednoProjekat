package rs.ac.bg.fon.nprog.so.korisnik;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

/**
 * Klasa `SOGetAllKorisnik` predstavlja specifičnu implementaciju poslovne logike za dohvat svih korisnika iz baze podataka.
 * 
 * Ova klasa nasleđuje `AbstractSO` i pruža konkretne implementacije za metode `validate` i `execute`,
 * koje su specifične za dohvat svih korisnika. 
 * 
 * @author 2000o
 * @version 1.1.0
 */
public class SOGetAllKorisnik extends AbstractSO{

	/**
	 * Lista svih korisnika dobijena iz baze podataka.
	 * 
	 * Ovaj atribut sadrži rezultate operacije dohvaćanja svih korisnika i koristi se za čuvanje i pristup 
	 * listi korisnika unutar klase `SOGetAllKorisnik`.
	 */
	private ArrayList<Korisnik> lista;

	/**
	 * Validira prosleđeni objekat kako bi se osiguralo da je instanca klase `Korisnik`.
	 * 
	 * @param ado Objekat koji treba da se validira. Treba da bude instanca klase `Korisnik`.
	 * @throws Exception Ako prosleđeni objekat nije instanca klase `Korisnik`.
	 */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }
    }

    /**
     * Izvršava operaciju dohvaćanja svih korisnika iz baze podataka.
     * 
     * Ova metoda koristi `DBBroker` za selektovanje svih korisnika iz baze podataka
     * na osnovu prosleđenog objekta. Rezultati se čuvaju u listi `lista`.
     * 
     * @param ado Objekat tipa `AbstractDomainObject` koji se koristi za dohvat korisnika iz baze.
     *            Treba da bude instanca klase `Korisnik`.
     * @throws Exception Ako dođe do greške prilikom dohvaćanja korisnika iz baze podataka.
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> korisnici = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Korisnik>) (ArrayList<?>) korisnici;
    }

    /**
     * Vraća listu svih korisnika dobijenu iz baze podataka.
     * 
     * Ova metoda omogućava pristup listi korisnika koja je dobijena kao rezultat
     * operacije dohvaćanja iz baze podataka. Lista se čuva u atributu `lista`.
     * 
     * @return Lista korisnika dobijena iz baze podataka.
     */
    public ArrayList<Korisnik> getLista() {
        return lista;
    }
	
}
