package rs.ac.bg.fon.nprog.so.korisnik;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.so.AbstractSO;

/**
 * Klasa `SODeleteKorisnik` predstavlja specifičnu implementaciju poslovne logike za brisanje korisnika iz baze podataka.
 * 
 * Ova klasa nasleđuje `AbstractSO` i pruža konkretne implementacije za metode `validate` i `execute`,
 * koje su specifične za brisanje korisnika. 
 * 
 * @author OgnjenJankovic
 * @version 1.1.0
 */
public class SODeleteKorisnik extends AbstractSO{
	
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
	 * Izvršava operaciju brisanja korisnika iz baze podataka.
	 * 
	 * Ova metoda koristi `DBBroker` za brisanje prosleđenog objekta iz baze podataka.
	 * 
	 * @param ado Objekat tipa `AbstractDomainObject` koji treba da se obriše iz baze. Treba da bude instanca klase `Korisnik`.
	 * @throws Exception Ako dođe do greške prilikom brisanja objekta iz baze podataka.
	 */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
	
	

}
