package rs.ac.bg.fon.nprog.so.tip;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Opstina;
import rs.ac.bg.fon.nprog.domain.Tip;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

public class SOGetAllTip extends AbstractSO{

	private ArrayList<Tip> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Tip)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Tip!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> tip = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Tip>) (ArrayList<?>) tip;
    }

    public ArrayList<Tip> getLista() {
        return lista;
    }
	
}
