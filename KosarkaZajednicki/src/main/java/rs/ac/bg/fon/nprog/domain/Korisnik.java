package rs.ac.bg.fon.nprog.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Korisnik extends AbstractDomainObject{

	private Long korisnikID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private Tip tipKorisnika;

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

	public Korisnik(Long korisnikID, String ime, String prezime, String email, String telefon, Tip tipKorisnika) {
		super();
		this.korisnikID = korisnikID;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.telefon = telefon;
		this.tipKorisnika = tipKorisnika;
	}

	public Korisnik() {
    }

    @Override
    public String nazivTabele() {
        return " Korisnik ";
    }

    @Override
    public String alijas() {
        return " k ";
    }

    @Override
    public String join() {
        return "JOIN TIP T ON (T.TIPID = K.TIPID)";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
        	Tip t = new Tip(rs.getLong("TipID"),
                    rs.getString("Naziv"));
        	
            Korisnik k = new Korisnik(rs.getLong("KorisnikID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Email"), rs.getString("Telefon"),
                    t);

            lista.add(k);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Email, Telefon, TipID) ";
    }

    @Override
    public String uslov() {
        return " KorisnikID = " + korisnikID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + email + "', '" + telefon + "', "+tipKorisnika.getTipID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "Email = '" + email + "', Telefon = '" + telefon + "', TipID = "+tipKorisnika.getTipID();
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(Long korisnikID) {
    	if(korisnikID<0) {
    		throw new IllegalArgumentException("korisnikID ne sme biti manji od nula");
    	}
        this.korisnikID = korisnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
    	if(ime==null) {
    		throw new NullPointerException("ime ne sme biti null");
    	}
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
    	if(prezime==null) {
    		throw new NullPointerException("prezime ne sme biti null");
    	}
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
    	if(email==null) {
    		throw new NullPointerException("email ne sme biti null");
    	}
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
    	if(telefon==null) {
    		throw new NullPointerException("telefon ne sme biti null");
    	}
        this.telefon = telefon;
    }
    
   public Tip getTipKorisnika() {
	   return tipKorisnika;
   }

   public void setTipKorisnika(Tip tipKorisnika) {
	   if(tipKorisnika==null) {
   		throw new NullPointerException("tipKorisnika ne sme biti null");
   	}
       this.tipKorisnika = tipKorisnika;
   }
	
	
}
