package rs.ac.bg.fon.nprog.controller;

import rs.ac.bg.fon.nprog.db.DBBroker;
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

/**
 * Predstavlja klasu u okviru koje se pozivaju sve sistemske operacije.
 * Kontroler je zaduzen da izabere i kreira odgovarajucu sistemsku operaciju za zahtev koji je stigao.
 * Nakon toga, Kontroler prihvata i vraca rezultat te sistemske operacije.
 * Implementiran je pomocu Singlton paterna kako bi se osiguralo kreiranje samo jedne instance Kontrolera-a
 * 
 * @author OgnjenJankovic
 * @version 1.1.0
 */
public class ServerController {

	/**
	 * Instanca klase Kontroler koja ce se kreirati samo jednom prilikom prvog zahteva za nju. Svaki sledeci put
	 * kada je potreban Kontroler bice vracena prvobitno kreirana instanca.
	 */
	private static ServerController instance;
	/**
	 * Lista administratora sistema koja predstavlja sve administratore u bazi
	 */
    private ArrayList<Administrator> ulogovaniAdministratori = new ArrayList<>();
    
    /**
     * Privatni konstruktor koji osigurava da se instanca klase Kontroler moze napraviti jedino 
     * pozivom medote getInstance().
     */
    private ServerController() {
    }
    
    /**
     * Vraca instancu klase Kontroler. Ukoliko instanca ne postoji, kreira je.
     * 
     * @return Instanca klase Kontroler.
     */
    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }
    
    /**
     * Pokušava da prijavi datog administratora u sistem koristeći poslovnu logiku definisanu u klasi `SOLogin`.
     * 
     * @param administrator Objekat tipa `Administrator` koji pokušava da se prijavi.
     * @return Prijavljeni `Administrator` objekat nakon uspešne autentifikacije.
     * @throws Exception Ako prijava nije uspešna ili dođe do greške tokom izvršenja.
     */
    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }
    
    /**
     * Dodaje novog korisnika u sistem koristeći poslovnu logiku definisanu u klasi `SOAddKorisnik`.
     * 
     * @param korisnik Objekat tipa `Korisnik` koji treba da se doda.
     * @throws Exception Ako dodavanje korisnika nije uspešno ili dođe do greške tokom izvršenja.
     */
    public void addKorisnik(Korisnik korisnik) throws Exception {
        (new SOAddKorisnik()).templateExecute(korisnik);
    }
    
    /**
     * Dodaje novi termin u sistem koristeći poslovnu logiku definisanu u klasi `SOAddTermin`.
     * 
     * @param termin Objekat tipa `Termin` koji treba da se doda.
     * @throws Exception Ako dodavanje termina nije uspešno ili dođe do greške tokom izvršenja.
     */
    public void addTermin(Termin termin) throws Exception {
        (new SOAddTermin()).templateExecute(termin);
    }
    
    /**
     * Briše postojećeg korisnika iz sistema koristeći poslovnu logiku definisanu u klasi `SODeleteKorisnik`.
     * 
     * @param korisnik Objekat tipa `Korisnik` koji treba da se obriše.
     * @throws Exception Ako brisanje korisnika nije uspešno ili dođe do greške tokom izvršenja.
     */
    public void deleteKorisnik(Korisnik korisnik) throws Exception {
        (new SODeleteKorisnik()).templateExecute(korisnik);
    }
    
    /**
     * Briše postojeći termin iz sistema koristeći poslovnu logiku definisanu u klasi `SODeleteTermin`.
     * 
     * @param termin Objekat tipa `Termin` koji treba da se obriše.
     * @throws Exception Ako brisanje termina nije uspešno ili dođe do greške tokom izvršenja.
     */
    public void deleteTermin(Termin termin) throws Exception {
        (new SODeleteTermin()).templateExecute(termin);
    }
    
    /**
     * Ažurira podatke postojećeg korisnika u sistemu koristeći poslovnu logiku definisanu u klasi `SOUpdateKorisnik`.
     * 
     * @param korisnik Objekat tipa `Korisnik` sa ažuriranim podacima koji treba da se sačuva.
     * @throws Exception Ako ažuriranje korisnika nije uspešno ili dođe do greške tokom izvršenja.
     */
    public void updateKorisnik(Korisnik korisnik) throws Exception {
        (new SOUpdateKorisnik()).templateExecute(korisnik);
    }
    
    /**
     * Ažurira podatke postojećeg termina u sistemu koristeći poslovnu logiku definisanu u klasi `SOUpdateTermin`.
     * 
     * @param termin Objekat tipa `Termin` sa ažuriranim podacima koji treba da se sačuva.
     * @throws Exception Ako ažuriranje termina nije uspešno ili dođe do greške tokom izvršenja.
     */
    public void updateTermin(Termin termin) throws Exception {
        (new SOUpdateTermin()).templateExecute(termin);
    }
    
    /**
     * Preuzima sve korisnike iz sistema koristeći poslovnu logiku definisanu u klasi `SOGetAllKorisnik`.
     * 
     * @return Lista svih korisnika kao objekti tipa `Korisnik`.
     * @throws Exception Ako preuzimanje korisnika nije uspešno ili dođe do greške tokom izvršenja.
     */
    public ArrayList<Korisnik> getAllKorisnik() throws Exception {
        SOGetAllKorisnik so = new SOGetAllKorisnik();
        
        so.templateExecute(new Korisnik());
        return so.getLista();
    }
    
    /**
     * Preuzima sve termine koje je organizovao dati korisnik koristeći poslovnu logiku definisanu u klasi `SOGetAllTermin`.
     * 
     * @param k Objekat tipa `Korisnik` koji je organizator termina čiji se podaci preuzimaju.
     * @return Lista svih termina kao objekti tipa `Termin` organizovani od strane datog korisnika.
     * @throws Exception Ako preuzimanje termina nije uspešno ili dođe do greške tokom izvršenja.
     */
    public ArrayList<Termin> getAllTermin(Korisnik k) throws Exception {
        SOGetAllTermin so = new SOGetAllTermin();
        
        Termin t = new Termin();
        t.setKorisnikOrganizator(k);
        
        so.templateExecute(t);
        return so.getLista();
    }
    
    /**
     * Preuzima sve opštine iz sistema koristeći poslovnu logiku definisanu u klasi `SOGetAllOpstina`.
     * 
     * @return Lista svih opština kao objekti tipa `Opstina`.
     * @throws Exception Ako preuzimanje opština nije uspešno ili dođe do greške tokom izvršenja.
     */
    public ArrayList<Opstina> getAllOpstina() throws Exception {
        SOGetAllOpstina so = new SOGetAllOpstina();
        so.templateExecute(new Opstina());
        return so.getLista();
    }
    
    /**
     * Preuzima sve terene iz sistema koristeći poslovnu logiku definisanu u klasi `SOGetAllTeren`.
     * 
     * @return Lista svih terena kao objekti tipa `Teren`.
     * @throws Exception Ako preuzimanje terena nije uspešno ili dođe do greške tokom izvršenja.
     */
    public ArrayList<Teren> getAllTeren() throws Exception {
        SOGetAllTeren so = new SOGetAllTeren();
        so.templateExecute(new Teren());
        return so.getLista();
    }
    
    /**
     * Preuzima sve igrače povezane sa datim terminom koristeći poslovnu logiku definisanu u klasi `SOGetAllIgrac`.
     * 
     * @param termin Objekat tipa `Termin` za koji se preuzimaju igrači.
     * @return Lista svih igrača kao objekti tipa `Igrac` koji su povezani sa datim terminom.
     * @throws Exception Ako preuzimanje igrača nije uspešno ili dođe do greške tokom izvršenja.
     */
    public ArrayList<Igrac> getAllIgrac(Termin termin) throws Exception {
        SOGetAllIgrac so = new SOGetAllIgrac();
        
        Igrac igrac = new Igrac();
        igrac.setTermin(termin);
        
        so.templateExecute(igrac);
        return so.getLista();
    }
    
    /**
     * Preuzima sve gradove iz sistema koristeći poslovnu logiku definisanu u klasi `SOGetAllGrad`.
     * 
     * @return Lista svih gradova kao objekti tipa `Grad`.
     * @throws Exception Ako preuzimanje gradova nije uspešno ili dođe do greške tokom izvršenja.
     */
    public ArrayList<Grad> getAllGrad() throws Exception {
        SOGetAllGrad so = new SOGetAllGrad();
        so.templateExecute(new Grad());
        return so.getLista();
    }
    
    /**
     * Preuzima sve tipove iz sistema koristeći poslovnu logiku definisanu u klasi `SOGetAllTip`.
     * 
     * @return Lista svih tipova kao objekti tipa `Tip`.
     * @throws Exception Ako preuzimanje tipova nije uspešno ili dođe do greške tokom izvršenja.
     */
    public ArrayList<Tip> getAllTip() throws Exception {
        SOGetAllTip so = new SOGetAllTip();
        so.templateExecute(new Tip());
        return so.getLista();
    }
    
    /**
     * Metoda koja vraca listu administratora sistema, odnosno sve administratore iz baze podataka.
     *
     * @return Lista administratora sistema kao lista objekata klase Administrator
     */
    public ArrayList<Administrator> getUlogovaniAdministratori() {
        return ulogovaniAdministratori;
    }
    
    /**
     * Metoda koja postavlja listu administratora sistema 
     * 
     * @param ulogovaniAdministratori Lista administratora sistema kao lista objekata klase Administrator
     */
    public void setUlogovaniAdministratori(ArrayList<Administrator> ulogovaniAdministratori) {
        this.ulogovaniAdministratori = ulogovaniAdministratori;
    }
    
    /**
     * Odjavljuje datog administratora uklanjanjem iz liste trenutno prijavljenih administratora.
     * 
     * @param ulogovani Objekat tipa `Administrator` koji se odjavljuje.
     */
    public void logout(Administrator ulogovani) {
        ulogovaniAdministratori.remove(ulogovani);
    }
	
	
	
	
	
}
