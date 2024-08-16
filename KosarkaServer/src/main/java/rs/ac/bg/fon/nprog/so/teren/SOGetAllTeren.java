package rs.ac.bg.fon.nprog.so.teren;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Teren;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

public class SOGetAllTeren extends AbstractSO{

	public SOGetAllTeren(DBBroker dbBroker) {
        super(dbBroker);
    }
	
	public SOGetAllTeren() {
        super();
    }
	
	private ArrayList<Teren> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Teren)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Teren!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> tereni = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Teren>) (ArrayList<?>) tereni;
    }

    public ArrayList<Teren> getLista() {
        return lista;
    }
	
}
