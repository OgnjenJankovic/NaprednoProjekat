package rs.ac.bg.fon.nprog.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Termin extends AbstractDomainObject{

	private Long terminID;
    private Date datumVremePocetka;
    private Date datumVremeKraja;
    private int brojSati;
    private double ukupnaCena;
    private Teren teren;
    private Korisnik korisnikOrganizator;
    private Administrator administrator;
    private ArrayList<Igrac> igraci;

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

    public Long getTerminID() {
        return terminID;
    }

    public void setTerminID(Long terminID) {
    	if(terminID<1) {
    		throw new IllegalArgumentException("terminID ne sme biti manji od jedan");
    	}
        this.terminID = terminID;
    }

    public Date getDatumVremePocetka() {
        return datumVremePocetka;
    }

    public void setDatumVremePocetka(Date datumVremePocetka) {
    	if(datumVremePocetka==null && datumVremePocetka.after(datumVremeKraja)) {
    		throw new NullPointerException("datumVremePocetka ne sme biti null ni posle datumVremePocetka");
    	}
    	
        this.datumVremePocetka = datumVremePocetka;
    }

    public Date getDatumVremeKraja() {
        return datumVremeKraja;
    }

    public void setDatumVremeKraja(Date datumVremeKraja) {
    	if(datumVremeKraja==null && datumVremeKraja.before(datumVremePocetka)) {
    		throw new NullPointerException("datumVremeKraja ne sme biti null ni pre datumVremePocetka");
    	}
    	
        this.datumVremeKraja = datumVremeKraja;
    }

    public int getBrojSati() {
        return brojSati;
    }

    public void setBrojSati(int brojSati) {
    	if(brojSati<0) {
    		throw new IllegalArgumentException("brojSati ne sme biti manja od nule");
    	}
        this.brojSati = brojSati;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
    	if(ukupnaCena<0) {
    		throw new IllegalArgumentException("ukupnaCena ne sme biti manja od nule");
    	}
        this.ukupnaCena = ukupnaCena;
    }

    public Teren getTeren() {
        return teren;
    }

    public void setTeren(Teren teren) {
    	if(teren==null) {
    		throw new NullPointerException("teren ne sme biti null");
    	}
        this.teren = teren;
    }

    public Korisnik getKorisnikOrganizator() {
        return korisnikOrganizator;
    }

    public void setKorisnikOrganizator(Korisnik korisnikOrganizator) {
    	if(korisnikOrganizator == null) {
    		throw new NullPointerException("Korisnik organizator ne sme biti null");
    	}
        this.korisnikOrganizator = korisnikOrganizator;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
    	if(administrator==null) {
    		throw new NullPointerException("administrator ne sme biti null");
    	}
        this.administrator = administrator;
    }

    public ArrayList<Igrac> getIgraci() {
        return igraci;
    }

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
