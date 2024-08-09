package rs.ac.bg.fon.nprog.so.korisnik;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

/**
 * Klasa `SOUpdateKorisnik` je specijalizovana za izvršavanje operacija ažuriranja podataka korisnika u bazi podataka.
 * 
 * Ova klasa nasleđuje `AbstractSO` i implementira logiku za validaciju i izvršenje operacije ažuriranja korisnika.
 * 
 * @author 2000o
 * @version 1.1.0
 */
public class SOUpdateKorisnik extends AbstractSO{

	/**
	 * Validira prosleđeni objekat kako bi se osiguralo da je instanca klase `Korisnik`
	 * i da se ne krše jedinstveni zahtevi za ažuriranje korisničkih podataka.
	 * 
	 * Ova metoda proverava da li je prosleđeni objekat instanca klase `Korisnik`.
	 * Takođe, proverava da li postoje drugi korisnici u bazi sa istim emailom ili telefonom,
	 * osim korisnika čiji ID odgovara ID-u prosleđenog korisnika. Ako su ovi uslovi
	 * prekršeni, baca se odgovarajući izuzetak.
	 * 
	 * @param ado Objekat koji treba da se validira. Treba da bude instanca klase `Korisnik`.
	 * @throws Exception Ako prosleđeni objekat nije instanca klase `Korisnik`, ili ako
	 *         postoji korisnik sa istim emailom ili telefonom koji nije korisnik čiji
	 *         ID odgovara ID-u prosleđenog korisnika.
	 */
	@Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }

        Korisnik k = (Korisnik) ado;

        ArrayList<Korisnik> korisnici = (ArrayList<Korisnik>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Korisnik korisnik : korisnici) {
            if (!korisnik.getKorisnikID().equals(k.getKorisnikID())) {
                if (korisnik.getEmail().equals(k.getEmail())) {
                    throw new Exception("Korisnik sa tim emailom vec postoji!");
                }
                if (korisnik.getTelefon().equals(k.getTelefon())) {
                    throw new Exception("Korisnik sa tim telefonom vec postoji!");
                }
            }
        }

    }

	/**
	 * Izvršava ažuriranje korisničkih podataka u bazi podataka.
	 * 
	 * Ova metoda koristi instancu `DBBroker` za izvođenje operacije ažuriranja
	 * na bazi podataka. Prosleđeni objekat treba da bude instanca klase `Korisnik`
	 * i sadrži ažurirane podatke koji će biti sačuvani u bazi.
	 * 
	 * @param ado Objekat koji se ažurira u bazi podataka. Treba da bude instanca klase `Korisnik`.
	 * @throws Exception Ako dođe do greške prilikom ažuriranja podataka u bazi podataka.
	 */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
	
}
