package rs.ac.bg.fon.nprog.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Klasa koja predstavlja tip korisnika
 * 
 * Ova klasa implementira AbstractDomainObject u odnosu na tabelu tip u bazi.
 * 
 * Tip ima svoj ID koji ga jedinstveno identifikuje i naziv.
 * 
 * @author 2000o
 * @version 1.1.0
 */
public class Tip extends AbstractDomainObject{

	/**
	 * ID tipa kao Long
	 */
	private Long tipID;
	/**
	 * Naziv tipa kao String
	 */
	private String naziv;
	
	/**
	 * Vraca String koji predstavlja naziv tipa.
	 * @return Naziv tipa kao String.
	 */
	@Override
    public String toString() {
        return naziv;
    }
	
	/**
	 * Konstruktor koji inicijalizuje objekat klase Tip
	 */
	public Tip() {
	}

	/**
	 * Konstruktor koji inicijalizuje objekat klase Tip sa prosledjenim vrednostima svih atributa.
	 * 
	 * @param tipID ID tipa kao Long.
	 * @param naziv Naziv tipa kao String.
	 */
	public Tip(Long tipID, String naziv) {
		this.tipID = tipID;
		this.naziv = naziv;
	}
	
	/**
	 * Metoda koja vraca ID tipa
	 * 
	 * @return ID tipa kao Long
	 */
	public Long getTipID() {
		return tipID;
	}

	/**
	 * Metoda koja postavlja ID tipa.
	 * 
	 * @param tipID ID tipa kao Long.
	 * @throws IllegalArgumentException ako je tipa manji od 1
	 */
	public void setTipID(Long tipID) {
		if(tipID < 1) {
			throw new IllegalArgumentException("tipID ne sme biti manji od 1");
		}
		this.tipID = tipID;
	}

	/**
	 * Metoda koja vraca naziv tipa.
	 * 
	 * @return Naziv tipa kao String.
	 */
	public String getNaziv() {
		return naziv;
	}

	/**
	 * Metoda koja postavlja naziv tipa.
	 * 
	 * @param naziv Naziv tipa kao String.
	 * @throws NullPointerException ako je naziv null
	 */
	public void setNaziv(String naziv) {
		if(naziv == null) {
			throw new NullPointerException();
		}
		this.naziv = naziv;
	}

	@Override
    public String nazivTabele() {
        return " Tip ";
    }

    @Override
    public String alijas() {
        return " ti ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Tip t = new Tip(rs.getLong("TipID"),
                    rs.getString("Naziv"));

            lista.add(t);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String uslov() {
        return " TipID = " + tipID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }
	
	
}
