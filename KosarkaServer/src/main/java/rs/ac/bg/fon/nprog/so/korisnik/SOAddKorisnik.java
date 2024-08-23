package rs.ac.bg.fon.nprog.so.korisnik;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

public class SOAddKorisnik extends AbstractSO{

	private DBBroker dbBroker;
	
	public SOAddKorisnik(DBBroker dbBroker) {
		this.dbBroker = dbBroker;
    }
	
	public SOAddKorisnik() {
        super();
    }
	
	@Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }

        Korisnik k = (Korisnik) ado;

        ArrayList<Korisnik> korisnici = (ArrayList<Korisnik>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Korisnik korisnik : korisnici) {
            if (korisnik.getEmail().equals(k.getEmail())) {
                throw new Exception("Korisnik sa tim emailom vec postoji!");
            }
            if (korisnik.getTelefon().equals(k.getTelefon())) {
                throw new Exception("Korisnik sa tim telefonom vec postoji!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
    	DBBroker.getInstance().insert(ado);
    }
	
}
