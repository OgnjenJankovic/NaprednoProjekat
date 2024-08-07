package rs.ac.bg.fon.nprog.controller;

import rs.ac.bg.fon.nprog.domain.Administrator;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Igrac;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Opstina;
import rs.ac.bg.fon.nprog.domain.Teren;
import rs.ac.bg.fon.nprog.domain.Termin;
import rs.ac.bg.fon.nprog.domain.Tip;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.session.Session;
import rs.ac.bg.fon.nprog.transfer.Request;
import rs.ac.bg.fon.nprog.transfer.Response;
import rs.ac.bg.fon.nprog.transfer.util.ResponseStatus;
import rs.ac.bg.fon.nprog.transfer.util.Operation;

public class ClientController {

	private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) sendRequest(Operation.LOGIN, administrator);
    }
    
    public void logout(Administrator ulogovani) throws Exception {
        sendRequest(Operation.LOGOUT, ulogovani);
    }

    public void addKorisnik(Korisnik korisnik) throws Exception {
        sendRequest(Operation.ADD_KORISNIK, korisnik);
    }

    public void addTermin(Termin termin) throws Exception {
        sendRequest(Operation.ADD_TERMIN, termin);
    }

    public void deleteKorisnik(Korisnik korisnik) throws Exception {
        sendRequest(Operation.DELETE_KORISNIK, korisnik);
    }

    public void deleteTermin(Termin termin) throws Exception {
        sendRequest(Operation.DELETE_TERMIN, termin);
    }

    public void updateKorisnik(Korisnik korisnik) throws Exception {
        sendRequest(Operation.UPDATE_KORISNIK, korisnik);
    }

    public void updateTermin(Termin termin) throws Exception {
        sendRequest(Operation.UPDATE_TERMIN, termin);
    }

    public ArrayList<Korisnik> getAllKorisnik() throws Exception {
        return (ArrayList<Korisnik>) sendRequest(Operation.GET_ALL_KORISNIK, null);
    }

    public ArrayList<Termin> getAllTermin() throws Exception {
        return (ArrayList<Termin>) sendRequest(Operation.GET_ALL_TERMIN, null);
    }

    public ArrayList<Opstina> getAllOpstina() throws Exception {
        return (ArrayList<Opstina>) sendRequest(Operation.GET_ALL_OPSTINA, null);
    }
    
    public ArrayList<Grad> getAllGrad() throws Exception {
        return (ArrayList<Grad>) sendRequest(Operation.GET_ALL_GRAD, null);
    }
    
    public ArrayList<Tip> getAllTip() throws Exception {
        return (ArrayList<Tip>) sendRequest(Operation.GET_ALL_TIP, null);
    }

    public ArrayList<Teren> getAllTeren() throws Exception {
        return (ArrayList<Teren>) sendRequest(Operation.GET_ALL_TEREN, null);
    }

    public ArrayList<Igrac> getAllIgrac(Termin termin) throws Exception {
        return (ArrayList<Igrac>) sendRequest(Operation.GET_ALL_IGRAC, termin);
    }
    
    public ArrayList<Termin> getAllTermin(Korisnik k) throws Exception {
        return (ArrayList<Termin>) sendRequest(Operation.GET_ALL_TERMIN, k);
    }
    

    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();

        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getException();
        } else {
            return response.getData();
        }

    }
	
	
	
}
