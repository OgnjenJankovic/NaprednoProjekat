package rs.ac.bg.fon.nprog.so.opstina;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Opstina;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

public class SOGetAllOpstina extends AbstractSO{

	private ArrayList<Opstina> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Opstina)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Opstina!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> opstine = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Opstina>) (ArrayList<?>) opstine;
    }

    public ArrayList<Opstina> getLista() {
        return lista;
    }
	
}
