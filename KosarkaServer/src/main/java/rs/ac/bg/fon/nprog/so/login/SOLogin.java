package rs.ac.bg.fon.nprog.so.login;

import rs.ac.bg.fon.nprog.controller.ServerController;
import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Administrator;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

/**
 * Klasa `SOLogin` predstavlja specifičnu implementaciju poslovne logike za proces prijavljivanja korisnika.
 * 
 * Ova klasa nasleđuje AbstractSO i pruža konkretne implementacije za metode validate i execute,
 * koje su specifične za logiku prijavljivanja. 
 * 
 * @author OgnjenJankovic
 * @version 1.1.0
 */
public class SOLogin extends AbstractSO{


	private DBBroker dbBroker;
	
	public SOLogin(DBBroker dbBroker) {
		this.dbBroker = dbBroker;
    }
	
	public SOLogin() {
        super();
    }

	/**
	 * Predstavlja trenutno prijavljenog administratora.
	 * 
	 * Ovaj atribut čuva instancu klase `Administrator` koja se koristi za identifikaciju
	 * i rad sa trenutno prijavljenim administratorom u okviru poslovne logike prijavljivanja.
	 */
	Administrator ulogovani;

	/**
	 * Validira prosleđeni objekat kako bi se osiguralo da je instanca klase `Administrator` 
	 * i da trenutno prijavljeni administrator nije već prijavljen u sistemu.
	 * 
	 * @param ado Objekat koji treba da se validira. Treba da bude instanca klase `Administrator`.
	 * @throws Exception Ako prosleđeni objekat nije instanca klase `Administrator` ili ako
	 *         administrator sa istim korisničkim imenom već postoji u sistemu i već je prijavljen.
	 */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
        }

        Administrator a = (Administrator) ado;

        for (Administrator administrator : ServerController.getInstance().getUlogovaniAdministratori()) {
            if (administrator.getUsername().equals(a.getUsername())) {
                throw new Exception("Ovaj administrator je vec ulogovan na sistem!");
            }
        }

    }

    /**
     * Izvršava operaciju prijavljivanja administratora na osnovu prosleđenih kredencijala.
     * 
     * @param ado Objekat tipa `AbstractDomainObject` koji se koristi za pretragu administratora u bazi podataka.
     * @throws Exception Ako dođe do greške tokom pretrage ili ako ne postoji administrator sa prosleđenim
     *         korisničkim imenom i lozinkom.
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Administrator a = (Administrator) ado;

        ArrayList<Administrator> administratori
                = (ArrayList<Administrator>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Administrator administrator : administratori) {
            if (administrator.getUsername().equals(a.getUsername())
                    && administrator.getPassword().equals(a.getPassword())) {
                ulogovani = administrator;
                ServerController.getInstance().getUlogovaniAdministratori().add(administrator);
                return;
            }
        }

        throw new Exception("Ne postoji administrator sa tim kredencijalima.");

    }

    /**
     * Vraća trenutno prijavljenog administratora.
     * 
     * @return Instanca klase `Administrator` koja predstavlja trenutno prijavljenog administratora.
     */
    public Administrator getUlogovani() {
        return ulogovani;
    }
	
}
