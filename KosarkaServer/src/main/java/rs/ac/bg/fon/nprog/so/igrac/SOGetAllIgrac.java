package rs.ac.bg.fon.nprog.so.igrac;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Igrac;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

public class SOGetAllIgrac extends AbstractSO{

	public SOGetAllIgrac(DBBroker dbBroker) {
        super(dbBroker);
    }
	
	public SOGetAllIgrac() {
        super();
    }
	
	private ArrayList<Igrac> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Igrac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Igrac!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> igraci = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Igrac>) (ArrayList<?>) igraci;
    }

    public ArrayList<Igrac> getLista() {
        return lista;
    }

	
}
