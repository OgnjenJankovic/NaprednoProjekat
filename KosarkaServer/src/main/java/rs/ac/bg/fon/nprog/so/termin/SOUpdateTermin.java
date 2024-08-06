package rs.ac.bg.fon.nprog.so.termin;

import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Igrac;
import rs.ac.bg.fon.nprog.domain.Termin;
import rs.ac.bg.fon.nprog.so.AbstractSO;

public class SOUpdateTermin extends AbstractSO{

	@Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Termin)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Termin!");
        }

        Termin t = (Termin) ado;

        if (t.getBrojSati() < 1) {
            throw new Exception("Morate izracunati broj sati i ukupnu cenu!");
        }

        if (t.getIgraci().size() < 2 || t.getIgraci().size() > 10) {
            throw new Exception("Broj igraca mora biti izmedju 2 i 10!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        DBBroker.getInstance().update(ado);

        Termin t = (Termin) ado;
        
        DBBroker.getInstance().delete(t.getIgraci().get(0));

        for (Igrac igrac : t.getIgraci()) {
            DBBroker.getInstance().insert(igrac);
        }

        
    }
	
}
