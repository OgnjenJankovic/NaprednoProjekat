package rs.ac.bg.fon.nprog.so.korisnik;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

public class SOGetAllKorisnik extends AbstractSO{

	private DBBroker dbBroker;
	
	public SOGetAllKorisnik(DBBroker dbBroker) {
		this.dbBroker = dbBroker;
    }
	
	public SOGetAllKorisnik() {
        super();
    }
	
	private ArrayList<Korisnik> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> korisnici = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Korisnik>) (ArrayList<?>) korisnici;
    }

    public ArrayList<Korisnik> getLista() {
        return lista;
    }
	
}
