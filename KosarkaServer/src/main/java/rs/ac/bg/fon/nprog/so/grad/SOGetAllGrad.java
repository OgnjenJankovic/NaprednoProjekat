package rs.ac.bg.fon.nprog.so.grad;

import rs.ac.bg.fon.nprog.db.DBBroker; 
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Opstina;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

public class SOGetAllGrad extends AbstractSO{

	public SOGetAllGrad(DBBroker dbBroker) {
        super(dbBroker);
    }
	
	public SOGetAllGrad() {
        super();
    }
	
	private ArrayList<Grad> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Grad)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Grad!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> gradovi = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Grad>) (ArrayList<?>) gradovi;
    }

    public ArrayList<Grad> getLista() {
        return lista;
    }
	
	
}
