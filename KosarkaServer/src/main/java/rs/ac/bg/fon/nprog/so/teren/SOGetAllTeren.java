package rs.ac.bg.fon.nprog.so.teren;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Teren;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

/**
 * Klasa `SOGetAllTeren` predstavlja specifičnu implementaciju poslovne logike za preuzimanje
 * svih terena iz baze podataka.
 * 
 * Ova klasa nasleđuje `AbstractSO` i pruža konkretne implementacije za metode `validate` i `execute`,
 * koje su specifične za preuzimanje terena. 
 * 
 * @author 2000o
 * @version 1.1.0
 */
public class SOGetAllTeren extends AbstractSO{

	
	public SOGetAllTeren(DBBroker dbBroker) {
        super(dbBroker);
    }
	
	public SOGetAllTeren() {
        super();
    }
	
	/**
	 * Lista objekata tipa `Teren` koja sadrži rezultate preuzete tokom prethodnog izvršavanja operacije.
	 * 
	 * Ovaj atribut čuva sve terene koji su preuzeti iz baze podataka kao rezultat pretrage ili upita.
	 */
	private ArrayList<Teren> lista;

	/**
	 * Validira prosleđeni objekat kako bi se osiguralo da je instanca klase `Teren`.
	 * 
	 * @param ado Objekat koji treba da se validira. Treba da bude instanca klase `Teren`.
	 * @throws Exception Ako prosleđeni objekat nije instanca klase `Teren`.
	 */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Teren)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Teren!");
        }
    }

    /**
     * Izvršava operaciju preuzimanja podataka o terenima iz baze podataka na osnovu prosleđenog objekta.
     * 
     * @param ado Objekat tipa `AbstractDomainObject` koji se koristi za preuzimanje podataka o terenima iz baze.
     * @throws Exception Ako dođe do greške prilikom preuzimanja podataka iz baze ili tokom izvršenja operacije.
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> tereni = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Teren>) (ArrayList<?>) tereni;
    }

    /**
     * Vraća listu terena preuzetih tokom prethodnog izvršavanja operacije.
     * 
     * @return Lista objekata tipa `Teren` koja sadrži rezultate prethodnog upita.
     */
    public ArrayList<Teren> getLista() {
        return lista;
    }
	
}
