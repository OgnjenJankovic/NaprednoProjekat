package rs.ac.bg.fon.nprog.so.login;

import rs.ac.bg.fon.nprog.controller.ServerController;
import rs.ac.bg.fon.nprog.db.DBBroker;
import rs.ac.bg.fon.nprog.domain.AbstractDomainObject;
import rs.ac.bg.fon.nprog.domain.Administrator;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.AbstractSO;

public class SOLogin extends AbstractSO{

	private DBBroker dbBroker;
	 
	public SOLogin(DBBroker dbBroker) {
		this.dbBroker = dbBroker;
    }
	
	public SOLogin() {
        super();
    }

	Administrator ulogovani;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
        }

        Administrator a = (Administrator) ado;

        for (Administrator administrator : ServerController.getInstance().getUlogovaniAdministratori()) {
            if (administrator.getUsername().equals(a.getUsername())) {
                throw new Exception("Ovaj administrator je vec ulogovan na sistem!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Administrator a = (Administrator) ado;

        ArrayList<Administrator> administratori
                = (ArrayList<Administrator>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Administrator administrator : administratori) {
            if (administrator.getUsername().equals(a.getUsername())
                    && administrator.getPassword().equals(a.getPassword())) {
                ulogovani = administrator;
                ServerController.getInstance().getUlogovaniAdministratori().add(administrator);
                return;
            }
        }

        throw new Exception("Ne postoji administrator sa tim kredencijalima.");

    }

    public Administrator getUlogovani() {
        return ulogovani;
    }
	
}
