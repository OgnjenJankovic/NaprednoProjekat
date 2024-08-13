package rs.ac.bg.fon.nprog.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Igrac extends AbstractDomainObject{

	private Termin termin;
    private int rbIgraca;
    private String napomena;
    private Korisnik korisnikIgrac;

    public Igrac(Termin termin, int rbIgraca, String napomena, Korisnik korisnik) {
        this.termin = termin;
        this.rbIgraca = rbIgraca;
        this.napomena = napomena;
        this.korisnikIgrac = korisnik;
    }

    public Igrac() {
    }

    @Override
    public String nazivTabele() {
        return " Igrac ";
    }

    @Override
    public String alijas() {
        return " i ";
    }

    @Override
    public String join() {
        return " JOIN KORISNIK KIGRAC ON (KIGRAC.KORISNIKID = I.KORISNIKIGRACID) "
                + "JOIN TERMIN TER ON (TER.TERMINID = I.TERMINID) "
                + "JOIN TEREN T ON (T.TERENID = TER.TERENID) "
                + "JOIN OPSTINA O ON (O.OPSTINAID = T.OPSTINAID) "
                + "JOIN KORISNIK K ON (K.KORISNIKID = TER.korisnikOrganizatorID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = TER.ADMINISTRATORID)";
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
            
            Korisnik kigrac = new Korisnik(rs.getLong("kigrac.KorisnikID"),
                    rs.getString("kigrac.Ime"), rs.getString("kigrac.Prezime"),
                    rs.getString("kigrac.Email"), rs.getString("kigrac.Telefon"),tip);
            
            Igrac i = new Igrac(ter, rs.getInt("rbIgraca"), rs.getString("napomena"), kigrac);

            lista.add(i);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (terminID, rbIgraca, napomena, korisnikIgracID) ";
    }

    @Override
    public String uslov() {
        return " terminID = " + termin.getTerminID();
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + termin.getTerminID() + ", " + rbIgraca + ", "
                + "'" + napomena + "', " + korisnikIgrac.getKorisnikID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return " WHERE TER.TERMINID = " + termin.getTerminID();
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
    	if(termin==null) {
    		throw new NullPointerException("termin ne sme biti null");
    	}
        this.termin = termin;
    }

    public int getRbIgraca() {
        return rbIgraca;
    }

    public void setRbIgraca(int rbIgraca) {
    	if(rbIgraca<1) {
    		throw new IllegalArgumentException("rbIgraca ne sme biti manji od jedan");
    	}
        this.rbIgraca = rbIgraca;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
    	if(napomena==null) {
    		throw new NullPointerException("napomena ne sme biti null");
    	}
        this.napomena = napomena;
    }

    public Korisnik getKorisnikIgrac() {
        return korisnikIgrac;
    }

    public void setKorisnikIgrac(Korisnik korisnikIgrac) {
    	if(korisnikIgrac==null) {
    		throw new NullPointerException("korisnikIgrac ne sme biti null");
    	}
        this.korisnikIgrac = korisnikIgrac;
    }

	@Override
	public int hashCode() {
		return Objects.hash(rbIgraca);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Igrac other = (Igrac) obj;
		return rbIgraca == other.rbIgraca;
	}

	@Override
	public String toString() {
		return "Igrac [termin=" + termin + ", rbIgraca=" + rbIgraca + ", napomena=" + napomena + ", korisnikIgrac="
				+ korisnikIgrac + "]";
	}
	
    
    
}
