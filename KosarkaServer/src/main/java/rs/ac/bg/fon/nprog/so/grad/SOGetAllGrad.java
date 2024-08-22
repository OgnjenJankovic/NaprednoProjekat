package rs.ac.bg.fon.nprog.so.grad;

import rs.ac.bg.fon.nprog.db.DBBroker; 
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Opstina;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

/**
 * Klasa koja predstavlja sistemsku operaciju koja vraca listu svih gradova koji postoje u bazi.
 * Klasa nasledjuje AbstractSO koja predstavlja apstraktnu sistemsku operaciju
 * 
 * @author OgnjenJankovic
 * @version 1.1.0
 */
public class SOGetAllGrad extends AbstractSO{

	
	public SOGetAllGrad(DBBroker dbBroker) {
        super(dbBroker);
    }
	
	public SOGetAllGrad() {
        super();
    }
	/**
	 * Promenljiva u kojoj se cuvaju gradovi kao rezultat upita.
	 */
	private ArrayList<Grad> lista;

	/**
	 * Validira prosleđeni objekat kako bi se osiguralo da je instanca klase `Grad`.
	 * 
	 * @param ado Objekat koji treba da se validira. Treba da bude instanca klase `Grad`.
	 * @throws Exception Ako prosleđeni objekat nije instanca klase `Grad`.
	 */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Grad)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Grad!");
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
        ArrayList<AbstractDomainObject> gradovi = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Grad>) (ArrayList<?>) gradovi;
    }

    /**
     * Vraća listu gradova preuzetih tokom prethodnog izvršavanja operacije.
     * 
     * @return Lista objekata tipa `Grad` koja sadrži rezultate prethodnog upita.
     */
    public ArrayList<Grad> getLista() {
        return lista;
    }
	
	
}
