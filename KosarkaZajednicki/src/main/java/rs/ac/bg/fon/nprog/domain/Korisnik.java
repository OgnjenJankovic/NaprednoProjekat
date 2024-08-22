package rs.ac.bg.fon.nprog.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Klasa koja predstavlja korisnika termina.
 * 
 * Ova klasa implementira klasu AbstractDomainObject u odnosu na tabelu korisnik u bazi
 * 
 * Korisnik ima ID koji je jedinstven identifikator,ime i prezime, email, broj telefona i tipKorisnika
 * 
 * @author OgnjenJankovic
 * @version 1.1.0
 */
public class Korisnik extends AbstractDomainObject{

	/**
	 * ID korisnika kao Long
	 */
	private Long korisnikID;
	/**
	 * Ime korisnika kao String
	 */
    private String ime;
    /**
     * Prezime korisnika kao String
     */
    private String prezime;
    /**
     * Email korisnika kao String
     */
    private String email;
    /**
     * Telefon korisnika kao String
     */
    private String telefon;
    /**
     * Tip korisnika kao objekat klase Tip
     */
    private Tip tipKorisnika;

    /**
	 * Vraca String koji predstavlja ime i prezime korisnika
	 * @return Ime i prezime korisnika kao String.
	 */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    /**
	 * Konstruktor koji inicijalizuje objekat klase Korisnik sa prosledjenim vrednostima svih atributa.
	 * 
	 * @param korisnikID ID korisnika kao Long.
	 * @param ime Ime korisnika kao String.
	 * @param prezime Prezime korisnika kao String.
	 * @param email Email korisnika kao String.
	 * @param telefon Telefon korisnika kao String.
	 * @param tipKorisnika Tip korisnika kao objekat klase Tip.
	 */
	public Korisnik(Long korisnikID, String ime, String prezime, String email, String telefon, Tip tipKorisnika) {
		super();
		this.korisnikID = korisnikID;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.telefon = telefon;
		this.tipKorisnika = tipKorisnika;
	}

	/**
	 * Konstruktor koji inicijalizuje objekat klase Korisnik
	 */
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

    /**
	 * Metoda koja vraca ID korisnika
	 * 
	 * @return ID korisnika kao Long
	 */
    public Long getKorisnikID() {
        return korisnikID;
    }

    /**
	 * Metoda koja postavlja ID korisnika.
	 * 
	 * @param korisnikID ID korisnika kao Long.
	 * @throws IllegalArgumentException ako je korisnikID manji od 1
	 */
    public void setKorisnikID(Long korisnikID) {
    	if(korisnikID<1) {
    		throw new IllegalArgumentException("korisnikID ne sme biti manji od jedan");
    	}
        this.korisnikID = korisnikID;
    }

    /**
	 * Metoda koja vraca ime korisnika.
	 * 
	 * @return ime korisnika kao String.
	 */
    public String getIme() {
        return ime;
    }

    /**
	 * Metoda koja postavlja ime korisnika.
	 * 
	 * @param ime Ime korisnika kao String.
	 * @throws NullPointerException ako je ime null
	 */
    public void setIme(String ime) {
    	if(ime==null) {
    		throw new NullPointerException("ime ne sme biti null");
    	}
        this.ime = ime;
    }

    /**
	 * Metoda koja vraca prezime korisnika.
	 * 
	 * @return prezime korisnika kao String.
	 */
    public String getPrezime() {
        return prezime;
    }

    /**
   	 * Metoda koja postavlja prezime korisnika.
   	 * 
   	 * @param prezime Prezime korisnika kao String.
   	 * @throws NullPointerException ako je prezime null
   	 */
    public void setPrezime(String prezime) {
    	if(prezime==null) {
    		throw new NullPointerException("prezime ne sme biti null");
    	}
        this.prezime = prezime;
    }

    /**
	 * Metoda koja vraca email korisnika.
	 * 
	 * @return email korisnika kao String.
	 */
    public String getEmail() {
        return email;
    }

    /**
   	 * Metoda koja postavlja email korisnika.
   	 * 
   	 * @param email Email korisnika kao String.
   	 * @throws NullPointerException ako je email null
   	 */
    public void setEmail(String email) {
    	if(email==null) {
    		throw new NullPointerException("email ne sme biti null");
    	}
        this.email = email;
    }

    /**
	 * Metoda koja vraca telefon korisnika.
	 * 
	 * @return telefon korisnika kao String.
	 */
    public String getTelefon() {
        return telefon;
    }

    /**
   	 * Metoda koja postavlja email korisnika.
   	 * 
   	 * @param email Email korisnika kao String.
   	 * @throws NullPointerException ako je email null
   	 */
    public void setTelefon(String telefon) {
    	if(telefon==null) {
    		throw new NullPointerException("telefon ne sme biti null");
    	}
        this.telefon = telefon;
    }
    
    /**
	 * Metoda koja vraca tip korisnika.
	 * 
	 * @return tip korisnika kao objekat klase Tip.
	 */
   public Tip getTipKorisnika() {
	   return tipKorisnika;
   }

   /**
  	 * Metoda koja postavlja tip korisnika.
  	 * 
  	 * @param tipKorisnika Tip korisnika kao objekat klase Tip.
  	 * @throws NullPointerException ako je tipKorisnika null
  	 */
   public void setTipKorisnika(Tip tipKorisnika) {
	   if(tipKorisnika==null) {
   		throw new NullPointerException("tipKorisnika ne sme biti null");
   	}
       this.tipKorisnika = tipKorisnika;
   }



   /**
    * Metoda koja poredi dva korisnika po ID-u i vraca true ili false
    * 
    * @param obj Objekat sa kojim se vrsi uporedjivanje
    * @return
    * <ul>
    * <li>true - ako su oba objekata klase Korisnik i imaju isti ID.</li>
    * <li>false - u svim ostalim slucajevima.</li>
    * </ul>
    */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korisnik other = (Korisnik) obj;
		return Objects.equals(korisnikID, other.korisnikID);
	}
	
   
	
}
