package rs.ac.bg.fon.nprog.so.igrac;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Igrac;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

/**
 * Klasa koja predstavlja sistemsku operaciju koja vraca listu svih igraca koji postoje u bazi.
 * Klasa nasledjuje AbstractSO koja predstavlja apstraktnu sistemsku operaciju
 * 
 * @author 2000o
 * @version 1.1.0
 */
public class SOGetAllIgrac extends AbstractSO{

	
	public SOGetAllIgrac(DBBroker dbBroker) {
        super(dbBroker);
    }
	
	public SOGetAllIgrac() {
        super();
    }
	/**
	 * Promenljiva u kojoj se cuvaju igraci kao rezultat upita.
	 */
	private ArrayList<Igrac> lista;

	/**
	 * Validira prosleđeni objekat kako bi se osiguralo da je instanca klase `Igrac`.
	 * 
	 * @param ado Objekat koji treba da se validira. Treba da bude instanca klase `Igrac`.
	 * @throws Exception Ako prosleđeni objekat nije instanca klase `Igrac`.
	 */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Igrac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Igrac!");
        }
    }

    /**
     * Izvršava operaciju preuzimanja podataka iz baze podataka za prosleđeni objekat.
     * 
     * @param ado Objekat tipa `AbstractDomainObject` koji se koristi za preuzimanje podataka iz baze.
     * @throws Exception Ako dođe do greške prilikom preuzimanja podataka iz baze ili tokom izvršenja operacije.
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> igraci = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Igrac>) (ArrayList<?>) igraci;
    }

    /**
     * Vraća listu igrača preuzetih tokom prethodnog izvršavanja operacije.
     * 
     * @return Lista objekata tipa `Igrac` koja sadrži rezultate prethodnog upita.
     */
    public ArrayList<Igrac> getLista() {
        return lista;
    }

	
}
