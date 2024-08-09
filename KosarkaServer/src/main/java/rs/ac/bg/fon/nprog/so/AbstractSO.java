package rs.ac.bg.fon.nprog.so;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import java.sql.SQLException;

/**
 * Klasa koja predstavlja apstraktnu sistemsku operaciju.
 * AbstractSO je apstraktna klasa koja definise osnovni sablon za izvrsavanje sistemske operacij, dok neke specificne metode prepusta podklasama
 * Sve sistemske operacije nasledjuju ovu apstraktnu klasu
 * Klasa sadrzi mehanizam za upravljanje transakcijama i izvrsavanje operacija nad bazom podataka.
 * Svaka klasa koja nasledjuje ovu klasu treba da implementira metode validate i execute
 */
public abstract class AbstractSO {

	/**
	 * Apstraktna metoda koja služi za validaciju objekta pre nego što se izvrši neka operacija.
	 * 
	 * @param ado Objekat tipa `AbstractDomainObject` koji treba validirati.
	 * @throws Exception Ukoliko validacija ne uspe, baca se izuzetak sa informacijama o grešci.
	 */
	protected abstract void validate(AbstractDomainObject ado) throws Exception;
	/**
	 * Apstraktna metoda koja definiše specifičnu operaciju koja se izvodi nad datim objektom.
	 * 
	 * @param ado Objekat tipa `AbstractDomainObject` nad kojim se izvršava operacija.
	 * @throws Exception Ukoliko operacija ne uspe, baca se izuzetak sa informacijama o grešci.
	 */
    protected abstract void execute(AbstractDomainObject ado) throws Exception;

    /**
     * Šablonska metoda koja obavlja celokupan proces validacije, izvršenja i transakcije nad datim objektom.
     * 
     * @param ado Objekat tipa `AbstractDomainObject` nad kojim se izvode operacije.
     * @throws Exception Ukoliko dođe do greške u bilo kojoj fazi (validacija, izvršenje, transakcija),
     *                   baca se izuzetak i transakcija se vraća na prethodno stanje.
     */
    public void templateExecute(AbstractDomainObject ado) throws Exception {
        try {
            validate(ado);
            execute(ado);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    /**
     * Potvrđuje trenutnu transakciju u bazi podataka.
     * 
     * @throws SQLException Ako dođe do greške prilikom potvrđivanja transakcije.
     */
    public void commit() throws SQLException {
        DBBroker.getInstance().getConnection().commit();
    }

    /**
     * Vraća trenutnu transakciju na prethodno stanje, poništavajući sve promene izvršene tokom transakcije.
     * 
     * @throws SQLException Ako dođe do greške prilikom vraćanja transakcije.
     */
    public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
	
}
