package rs.ac.bg.fon.nprog.so.korisnik;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

/**
 * Klasa `SOAddKorisnik` predstavlja specifičnu implementaciju poslovne logike za dodavanje novog korisnika u bazu podataka.
 * 
 * Ova klasa nasleđuje `AbstractSO` i pruža konkretne implementacije za metode `validate` i `execute`,
 * koje su specifične za dodavanje korisnika. 
 * 
 * @author OgnjenJankovic
 * @version 1.1.0
 */
public class SOAddKorisnik extends AbstractSO{

	
	public SOAddKorisnik(DBBroker dbBroker) {
        super(dbBroker);
    }
	
	public SOAddKorisnik() {
        super();
    }
	
	
	/**
	 * Validira prosleđeni objekat kako bi se osiguralo da je instanca klase `Korisnik` i da ne postoje 
	 * korisnici sa istim emailom ili telefonom u bazi podataka.
	 * 
	 * @param ado Objekat koji treba da se validira. Treba da bude instanca klase `Korisnik`.
	 * @throws Exception Ako prosleđeni objekat nije instanca klase `Korisnik`, ili ako već postoji korisnik 
	 *         sa istim emailom ili telefonom u bazi podataka.
	 */
	@Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }

        Korisnik k = (Korisnik) ado;

        ArrayList<Korisnik> korisnici = (ArrayList<Korisnik>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Korisnik korisnik : korisnici) {
            if (korisnik.getEmail().equals(k.getEmail())) {
                throw new Exception("Korisnik sa tim emailom vec postoji!");
            }
            if (korisnik.getTelefon().equals(k.getTelefon())) {
                throw new Exception("Korisnik sa tim telefonom vec postoji!");
            }
        }

    }

	/**
	 * Izvršava operaciju dodavanja novog korisnika u bazu podataka.
	 * 
	 * Ova metoda koristi `DBBroker` za umetanje prosleđenog objekta u bazu podataka.
	 * 
	 * @param ado Objekat tipa `AbstractDomainObject` koji treba da se doda u bazu. Treba da bude instanca klase `Korisnik`.
	 * @throws Exception Ako dođe do greške prilikom umetanja objekta u bazu podataka.
	 */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
	
}
