package rs.ac.bg.fon.nprog.so.tip;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Opstina;
import rs.ac.bg.fon.nprog.domain.Tip;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

/**
 * Klasa `SOGetAllTip` predstavlja specifičnu implementaciju poslovne logike za preuzimanje
 * svih tipova iz baze podataka.
 * 
 * Ova klasa nasleđuje `AbstractSO` i pruža konkretne implementacije za metode `validate` i `execute`,
 * koje su specifične za preuzimanje tipova. 
 * 
 * @author OgnjenJankovic
 * @version 1.1.0
 */
public class SOGetAllTip extends AbstractSO{

	
	public SOGetAllTip(DBBroker dbBroker) {
        super(dbBroker);
    }
	
	public SOGetAllTip() {
        super();
    }
	
	/**
	 * Lista objekata tipa `Tip` koja sadrži rezultate preuzete tokom prethodnog izvršavanja operacije.
	 * 
	 * Ovaj atribut čuva sve tipove koji su preuzeti iz baze podataka kao rezultat pretrage ili upita.
	 */
	private ArrayList<Tip> lista;

	/**
	 * Validira prosleđeni objekat kako bi se osiguralo da je instanca klase `Tip`.
	 * 
	 * @param ado Objekat koji treba da se validira. Treba da bude instanca klase `Tip`.
	 * @throws Exception Ako prosleđeni objekat nije instanca klase `Tip`.
	 */
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Tip)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Tip!");
        }
    }

    /**
     * Izvršava operaciju preuzimanja podataka o tipovima iz baze podataka na osnovu prosleđenog objekta.
     * 
     * @param ado Objekat tipa `AbstractDomainObject` koji se koristi za preuzimanje podataka o tipovima iz baze.
     * @throws Exception Ako dođe do greške prilikom preuzimanja podataka iz baze ili tokom izvršenja operacije.
     */
    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> tip = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Tip>) (ArrayList<?>) tip;
    }

    /**
     * Vraća listu tipova preuzetih tokom prethodnog izvršavanja operacije.
     * 
     * @return Lista objekata tipa `Tip` koja sadrži rezultate prethodnog upita.
     */
    public ArrayList<Tip> getLista() {
        return lista;
    }
	
}
