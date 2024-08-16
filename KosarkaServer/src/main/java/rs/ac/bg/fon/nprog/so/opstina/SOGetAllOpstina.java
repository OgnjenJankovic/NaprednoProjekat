package rs.ac.bg.fon.nprog.so.opstina;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Opstina;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

/**
 * Klasa koja predstavlja sistemsku operaciju koja vraca listu svih opstina koji postoje u bazi.
 * Klasa nasledjuje AbstractSO koja predstavlja apstraktnu sistemsku operaciju
 * 
 * @author 2000o
 * @version 1.1.0
 */
public class SOGetAllOpstina extends AbstractSO{

	
	public SOGetAllOpstina(DBBroker dbBroker) {
        super(dbBroker);
    }
	
	public SOGetAllOpstina() {
        super();
    }
	
	/**
	 * Promenljiva u kojoj se cuvaju opstine kao rezultat upita.
	 */
	private ArrayList<Opstina> lista;

	/**
	 * Validira prosleđeni objekat kako bi se osiguralo da je instanca klase `Opstina`.
	 * 
	 * @param ado Objekat koji treba da se validira. Treba da bude instanca klase `Opstina`.
	 * @throws Exception Ako prosleđeni objekat nije instanca klase `Opstina`.
	 */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Opstina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Opstina!");
        }
    }

    /**
     * Izvršava operaciju preuzimanja podataka o opštinama iz baze podataka na osnovu prosleđenog objekta.
     * 
     * @param ado Objekat tipa `AbstractDomainObject` koji se koristi za preuzimanje podataka o opštinama iz baze.
     * @throws Exception Ako dođe do greške prilikom preuzimanja podataka iz baze ili tokom izvršenja operacije.
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> opstine = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Opstina>) (ArrayList<?>) opstine;
    }

    /**
     * Vraća listu opština preuzetih tokom prethodnog izvršavanja operacije.
     * 
     * @return Lista objekata tipa `Opstina` koja sadrži rezultate prethodnog upita.
     */
    public ArrayList<Opstina> getLista() {
        return lista;
    }
	
}
