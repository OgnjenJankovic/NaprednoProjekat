package rs.ac.bg.fon.nprog.so.termin;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Termin;
import rs.ac.bg.fon.nprog.so.AbstractSO;


public class SODeleteTermin extends AbstractSO{

	private DBBroker dbBroker;
	
	public SODeleteTermin(DBBroker dbBroker) {
		this.dbBroker = dbBroker;
    }
	
	public SODeleteTermin() {
        super();
    }
	
	@Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Termin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Termin!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
	
}
