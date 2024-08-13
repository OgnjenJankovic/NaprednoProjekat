package rs.ac.bg.fon.nprog.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Klasa koja predstavlja termin na kojem igraju igraci
 * 
 * Ova klasa implementira AbstractDomainObject u odnosu na tabelu tremin u bazi.
 * 
 * Termin ima svoj ID koji ga jedinstveno identifikuje,datum pocetka i kraja termina, broj sati,
 * ukupna cena, Teren na kojem se organizuje, organizatora termina, administratora i listu igraca
 * 
 * @author 2000o
 * @version 1.1.0
 */
public class Termin extends AbstractDomainObject{

	/**
	 * ID termina kao Long
	 */
	private Long terminID;
	/**
	 * Pocetak termina kao Date
	 */
    private Date datumVremePocetka;
    /**
	 * Kraj termina kao Date
	 */
    private Date datumVremeKraja;
    /**
	 * Broj sati kao int
	 */
    private int brojSati;
    /**
	 * Ukupna cena kao Double
	 */
    private double ukupnaCena;
    /**
	 * Teren na kojem se igra kao objekat klase Teren
	 */
    private Teren teren;
    /**
     * Organizator termina kao objekat klase Korisnik
     */
    private Korisnik korisnikOrganizator;
    /**
     * Administrator sistema kao objekat klase Administrator
     */
    private Administrator administrator;
    /**
     * Lista stavki koje cine Igraci kao ArrayList<Igrac>
     */
    private ArrayList<Igrac> igraci;

    /**
     * Konstruktor koji inicijalizuje objekat klase Termin sa prosledjenim vrednostima svih atributa.
     * 
     * @param terminID termin kao Long
     * @param datumVremePocetka datum pocetka termina kao Date
     * @param datumVremeKraja datum kraja termina kao Date
     * @param brojSati broj sati kao int
     * @param ukupnaCena ukupna cena kao Double
     * @param teren Teren na kojem se igra termin kao objekat klase Teren
     * @param korisnikOrganizator Organizator termina kao objekat klase Korisnik
     * @param administrator Administrator sistema kao objekat klase Administrator
     * @param igraci Igraci kao lista ArrayList
     */
    public Termin(Long terminID, Date datumVremePocetka, Date datumVremeKraja, int brojSati, double ukupnaCena, Teren teren, Korisnik korisnikOrganizator, Administrator administrator, ArrayList<Igrac> igraci) {
        this.terminID = terminID;
        this.datumVremePocetka = datumVremePocetka;
        this.datumVremeKraja = datumVremeKraja;
        this.brojSati = brojSati;
        this.ukupnaCena = ukupnaCena;
        this.teren = teren;
        this.korisnikOrganizator = korisnikOrganizator;
        this.administrator = administrator;
        this.igraci = igraci;
    }

    /**
	 * Konstruktor koji inicijalizuje objekat klase Termin
	 */
    public Termin() {
    }

    @Override
    public String nazivTabele() {
        return " Termin ";
    }

    @Override
    public String alijas() {
        return " ter ";
    }

    @Override
    public String join() {
        return " JOIN TEREN T ON (T.TERENID = TER.TERENID) "
                + "JOIN OPSTINA O ON (O.OPSTINAID = T.OPSTINAID) "
                + "JOIN KORISNIK K ON (K.KORISNIKID = TER.korisnikOrganizatorID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = TER.ADMINISTRATORID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("a.Ime"), rs.getString("a.Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            Tip tip = new Tip(rs.getLong("TipID"),
                    rs.getString("Naziv"));
            
            Korisnik korisnikOrganizator = new Korisnik(rs.getLong("KorisnikID"),
                    rs.getString("k.Ime"), rs.getString("k.Prezime"),
                    rs.getString("Email"), rs.getString("Telefon"),tip);

            Opstina o = new Opstina(rs.getLong("OpstinaID"),
                    rs.getString("Naziv"));
            
            Grad g = new Grad(rs.getLong("GradID"), rs.getString("Naziv"));

            Teren t = new Teren(rs.getLong("terenID"), rs.getString("naziv"),
                    rs.getString("adresa"), rs.getString("opis"),
                    rs.getDouble("cenaPoSatu"), o, g);

            Termin ter = new Termin(rs.getLong("terminID"), rs.getTimestamp("datumVremePocetka"),
                    rs.getTimestamp("datumVremeKraja"), rs.getInt("brojSati"),
                    rs.getDouble("ukupnaCena"), t, korisnikOrganizator, a, null);

            lista.add(ter);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVremePocetka, datumVremeKraja, brojSati, ukupnaCena, "
                + "terenID, korisnikOrganizatorID, administratorID) ";
    }

    @Override
    public String uslov() {
        return " terminID = " + terminID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVremePocetka.getTime()) + "', "
                + "'" + new Timestamp(datumVremeKraja.getTime()) + "', "
                + " " + brojSati + ", " + ukupnaCena + ", "
                + teren.getTerenID() + ", " + korisnikOrganizator.getKorisnikID()
                + ", " + administrator.getAdministratorID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumVremePocetka = '" + new Timestamp(datumVremePocetka.getTime()) + "', "
                + "datumVremeKraja = '" + new Timestamp(datumVremeKraja.getTime()) + "', "
                + "brojSati = " + brojSati + ", ukupnaCena = " + ukupnaCena + ", "
                + " terenID = " + teren.getTerenID();
    }

    @Override
    public String uslovZaSelect() {
        if (korisnikOrganizator != null) {
            return " WHERE K.KORISNIKID = " + korisnikOrganizator.getKorisnikID();
        }
        return "";
    }

    /**
	 * Metoda koja vraca ID termina
	 * 
	 * @return ID termina kao Long
	 */
    public Long getTerminID() {
        return terminID;
    }

    /**
	 * Metoda koja postavlja ID termina.
	 * 
	 * @param terminID ID termina kao Long.
	 * @throws IllegalArgumentException ako je terminID manji od 0
	 */
    public void setTerminID(Long terminID) {
    	if(terminID<1) {
    		throw new IllegalArgumentException("terminID ne sme biti manji od jedan");
    	}
        this.terminID = terminID;
    }

    /**
     * Metoda koja vraca datum i vreme pocetka termina
     * 
     * @return Datum i vreme pocetka termina
     */
    public Date getDatumVremePocetka() {
        return datumVremePocetka;
    }
    
    /**
     * Metoda koja predstavlja datum i vreme pocetka termina
     * 
     * @param datumVremePocetka Datum i vreme pocetka termina kao Date.
     */
    public void setDatumVremePocetka(Date datumVremePocetka) {
    	if(datumVremePocetka==null && datumVremePocetka.after(datumVremeKraja)) {
    		throw new NullPointerException("datumVremePocetka ne sme biti null ni posle datumVremePocetka");
    	}
    	
        this.datumVremePocetka = datumVremePocetka;
    }

    /**
     * Metoda koja vraca datum i vreme kraja termina
     * 
     * @return Datum i vreme kraja termina
     */
    public Date getDatumVremeKraja() {
        return datumVremeKraja;
    }

    /**
     * Metoda koja predstavlja datum i vreme kraja termina
     * 
     * @param datumVremeKraja Datum i vreme kraja termina kao Date.
     */
    public void setDatumVremeKraja(Date datumVremeKraja) {
    	if(datumVremeKraja==null && datumVremeKraja.before(datumVremePocetka)) {
    		throw new NullPointerException("datumVremeKraja ne sme biti null ni pre datumVremePocetka");
    	}
    	
        this.datumVremeKraja = datumVremeKraja;
    }

    /**
	 * Metoda koja vraca broj sati termina
	 * 
	 * @return Broj sati kao int
	 */
    public int getBrojSati() {
        return brojSati;
    }

    /**
	 * Metoda koja postavlja broj sati termina
	 * 
	 * @param brojSati Broj sati kao int
	 * @throws IllegalArgumentException ako je brojSati manji od 0
	 */
    public void setBrojSati(int brojSati) {
    	if(brojSati<0) {
    		throw new IllegalArgumentException("brojSati ne sme biti manja od nule");
    	}
        this.brojSati = brojSati;
    }

    /**
	 * Metoda koja vraca ukupnu cenu termina
	 * 
	 * @return Ukupna cena kao Double
	 */
    public double getUkupnaCena() {
        return ukupnaCena;
    }

    /**
	 * Metoda koja postavlja ukupanu cenu termina
	 * 
	 * @param ukupnaCena Ukupna cena kao Double
	 * @throws IllegalArgumentException ako je ukupnaCena manja od 0
	 */
    public void setUkupnaCena(double ukupnaCena) {
    	if(ukupnaCena<0) {
    		throw new IllegalArgumentException("ukupnaCena ne sme biti manja od nule");
    	}
        this.ukupnaCena = ukupnaCena;
    }

    /**
	 * Metoda koja vraca teren.
	 * 
	 * @return teren  kao objekat klase Teren.
	 */
    public Teren getTeren() {
        return teren;
    }

    /**
  	 * Metoda koja postavlja teren
  	 * 
  	 * @param teren Teren kao objekat klase Teren.
  	 * @throws NullPointerException ako je teren null
  	 */
    public void setTeren(Teren teren) {
    	if(teren==null) {
    		throw new NullPointerException("teren ne sme biti null");
    	}
        this.teren = teren;
    }

    /**
	 * Metoda koja vraca organizatora.
	 * 
	 * @return korisnikOrganizator  kao objekat klase Korisnik.
	 */
    public Korisnik getKorisnikOrganizator() {
        return korisnikOrganizator;
    }

    /**
  	 * Metoda koja postavlja organizatora
  	 * 
  	 * @param korisnikOrganizator Organizator kao objekat klase Korisnik.
  	 * @throws NullPointerException ako je korisnikOrganizator null
  	 */
    public void setKorisnikOrganizator(Korisnik korisnikOrganizator) {
    	if(korisnikOrganizator == null) {
    		throw new NullPointerException("Korisnik organizator ne sme biti null");
    	}
        this.korisnikOrganizator = korisnikOrganizator;
    }

    /**
	 * Metoda koja vraca administratora.
	 * 
	 * @return administratora  kao objekat klase Administrator.
	 */
    public Administrator getAdministrator() {
        return administrator;
    }

    /**
  	 * Metoda koja postavlja administratora
  	 * 
  	 * @param administrator Administrator kao objekat klase Administrator.
  	 * @throws NullPointerException ako je administrator null
  	 */
    public void setAdministrator(Administrator administrator) {
    	if(administrator==null) {
    		throw new NullPointerException("administrator ne sme biti null");
    	}
        this.administrator = administrator;
    }

    /**
     * Metoda koja vraca listu igraca
     * 
     * @return Lista igraca kao List objekata koji implementiraju AbstractDomainObject
     */
    public ArrayList<Igrac> getIgraci() {
        return igraci;
    }

    /**
     * Metoda koja postalja listu igraca
     * 
     * @param igraci Lista igraca kao List objekata koji implementiraju  AbstractDomainObject
     */
    public void setIgraci(ArrayList<Igrac> igraci) {
    	if(igraci==null) {
    		throw new NullPointerException("igraci ne sme biti null");
    	}
        this.igraci = igraci;
    }

	@Override
	public int hashCode() {
		return Objects.hash(terminID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Termin other = (Termin) obj;
		return Objects.equals(terminID, other.terminID);
	}
	
    
	
	
}
