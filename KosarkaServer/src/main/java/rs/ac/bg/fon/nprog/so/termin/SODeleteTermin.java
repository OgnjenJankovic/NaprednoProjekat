package rs.ac.bg.fon.nprog.so.termin;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Termin;
import rs.ac.bg.fon.nprog.so.AbstractSO;

/**
 * Specijalizovana operacija za brisanje termina iz baze podataka.
 * 
 * Ova klasa pruža implementaciju za brisanje objekta `Termin` iz baze podataka. Nasleđuje klasu `AbstractSO`
 * i koristi njene metode za validaciju i izvršenje brisanja termina. Klasa omogućava uklanjanje termina kao
 * i svih povezanih podataka iz baze.
 * 
 * @author OgnjenJankovic
 * @version 1.1.0
 */
public class SODeleteTermin extends AbstractSO{

	
	public SODeleteTermin(DBBroker dbBroker) {
        super(dbBroker);
    }
	
	public SODeleteTermin() {
        super();
    }
	
	/**
	 * Validira da li prosleđeni objekat predstavlja validan objekat klase `Termin` za brisanje.
	 * 
	 * Ova metoda proverava da li je prosleđeni objekat instanca klase `Termin`. Ako objekat nije instanca
	 * klase `Termin`, baca se izuzetak sa odgovarajućom porukom.
	 * 
	 * @param ado Objekat koji treba da bude validiran. Treba da bude instanca klase `Termin`.
	 * @throws Exception Ako prosleđeni objekat nije instanca klase `Termin`.
	 */
	@Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Termin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Termin!");
        }
    }

	/**
	 * Izvršava operaciju brisanja objekta iz baze podataka.
	 * 
	 * Ova metoda koristi instancu `DBBroker` za brisanje prosleđenog objekta iz baze podataka.
	 * 
	 * @param ado Objekat koji treba da bude obrisan iz baze podataka. Treba da bude instanca klase `Termin`.
	 * @throws Exception Ako dođe do greške tokom brisanja objekta iz baze podataka.
	 */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
	
}
