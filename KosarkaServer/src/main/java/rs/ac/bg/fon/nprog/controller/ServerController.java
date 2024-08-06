package rs.ac.bg.fon.nprog.controller;

import rs.ac.bg.fon.nprog.domain.Administrator;
import rs.ac.bg.fon.nprog.domain.Grad;
import rs.ac.bg.fon.nprog.domain.Igrac;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Opstina;
import rs.ac.bg.fon.nprog.domain.Teren;
import rs.ac.bg.fon.nprog.domain.Termin;
import rs.ac.bg.fon.nprog.domain.Tip;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.so.grad.SOGetAllGrad;
import rs.ac.bg.fon.nprog.so.igrac.SOGetAllIgrac;
import rs.ac.bg.fon.nprog.so.korisnik.SOAddKorisnik;
import rs.ac.bg.fon.nprog.so.korisnik.SODeleteKorisnik;
import rs.ac.bg.fon.nprog.so.korisnik.SOGetAllKorisnik;
import rs.ac.bg.fon.nprog.so.korisnik.SOUpdateKorisnik;
import rs.ac.bg.fon.nprog.so.login.SOLogin;
import rs.ac.bg.fon.nprog.so.opstina.SOGetAllOpstina;
import rs.ac.bg.fon.nprog.so.teren.SOGetAllTeren;
import rs.ac.bg.fon.nprog.so.termin.SOAddTermin;
import rs.ac.bg.fon.nprog.so.termin.SODeleteTermin;
import rs.ac.bg.fon.nprog.so.termin.SOGetAllTermin;
import rs.ac.bg.fon.nprog.so.termin.SOUpdateTermin;
import rs.ac.bg.fon.nprog.so.tip.SOGetAllTip;

public class ServerController {

	private static ServerController instance;
    private ArrayList<Administrator> ulogovaniAdministratori = new ArrayList<>();
    
    private ServerController() {
    }
    
    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }
    
    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }
    
    public void addKorisnik(Korisnik korisnik) throws Exception {
        (new SOAddKorisnik()).templateExecute(korisnik);
    }
    
    public void addTermin(Termin termin) throws Exception {
        (new SOAddTermin()).templateExecute(termin);
    }
    
    public void deleteKorisnik(Korisnik korisnik) throws Exception {
        (new SODeleteKorisnik()).templateExecute(korisnik);
    }
    
    public void deleteTermin(Termin termin) throws Exception {
        (new SODeleteTermin()).templateExecute(termin);
    }
    
    public void updateKorisnik(Korisnik korisnik) throws Exception {
        (new SOUpdateKorisnik()).templateExecute(korisnik);
    }
    
    public void updateTermin(Termin termin) throws Exception {
        (new SOUpdateTermin()).templateExecute(termin);
    }
    
    public ArrayList<Korisnik> getAllKorisnik() throws Exception {
        SOGetAllKorisnik so = new SOGetAllKorisnik();
        
        so.templateExecute(new Korisnik());
        return so.getLista();
    }
    
    public ArrayList<Termin> getAllTermin(Korisnik k) throws Exception {
        SOGetAllTermin so = new SOGetAllTermin();
        
        Termin t = new Termin();
        t.setKorisnikOrganizator(k);
        
        so.templateExecute(t);
        return so.getLista();
    }
    
    public ArrayList<Opstina> getAllOpstina() throws Exception {
        SOGetAllOpstina so = new SOGetAllOpstina();
        so.templateExecute(new Opstina());
        return so.getLista();
    }
    
    public ArrayList<Teren> getAllTeren() throws Exception {
        SOGetAllTeren so = new SOGetAllTeren();
        so.templateExecute(new Teren());
        return so.getLista();
    }
    
    public ArrayList<Igrac> getAllIgrac(Termin termin) throws Exception {
        SOGetAllIgrac so = new SOGetAllIgrac();
        
        Igrac igrac = new Igrac();
        igrac.setTermin(termin);
        
        so.templateExecute(igrac);
        return so.getLista();
    }
    
    public ArrayList<Grad> getAllGrad() throws Exception {
        SOGetAllGrad so = new SOGetAllGrad();
        so.templateExecute(new Grad());
        return so.getLista();
    }
    
    public ArrayList<Tip> getAllTip() throws Exception {
        SOGetAllTip so = new SOGetAllTip();
        so.templateExecute(new Tip());
        return so.getLista();
    }
    
    public ArrayList<Administrator> getUlogovaniAdministratori() {
        return ulogovaniAdministratori;
    }
    
    public void setUlogovaniAdministratori(ArrayList<Administrator> ulogovaniAdministratori) {
        this.ulogovaniAdministratori = ulogovaniAdministratori;
    }
    
    public void logout(Administrator ulogovani) {
        ulogovaniAdministratori.remove(ulogovani);
    }
	
	
	
	
	
}
