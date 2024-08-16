package rs.ac.bg.fon.nprog.so.korisnik;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.so.AbstractSO;

public class SODeleteKorisnik extends AbstractSO{
	
	public SODeleteKorisnik(DBBroker dbBroker) {
        super(dbBroker);
    }
	
	public SODeleteKorisnik() {
        super();
    }
	
	@Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Korisnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Korisnik!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
	
	

}
