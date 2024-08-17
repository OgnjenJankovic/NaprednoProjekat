package rs.ac.bg.fon.nprog.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Klasa koja predstavlja grad u kojem se organizuje termin
 * 
 * Ova klasa implementira AbstractDomainObject u odnosu na tabelu grad u bazi.
 * 
 * Grad ima svoj ID koji ga jedinstveno identifikuje i naziv.
 * 
 * @author 2000o
 * @version 1.1.0
 */
public class Grad extends AbstractDomainObject{

	/**
	 * ID grada kao Long
	 */
	private Long gradID;
	/**
	 * Naziv grada kao String
	 */
    private String naziv;
	
	
	/**
	 * Konstruktor koji inicijalizuje objekat klase Grad
	 */
	public Grad() {
	}
	
	
	/**
	 * Konstruktor koji inicijalizuje objekat klase Grad sa prosledjenim vrednostima svih atributa.
	 * 
	 * @param gradID ID grada kao Long.
	 * @param naziv Naziv grada kao String.
	 */
	public Grad(Long gradID, String naziv) {
		this.gradID = gradID;
		this.naziv = naziv;
	}


	/**
	 * Metoda koja vraca ID grada
	 * 
	 * @return ID grada kao Long
	 */
	public Long getGradID() {
		return gradID;
	}


	/**
	 * Metoda koja postavlja ID grada.
	 * 
	 * @param gradID ID grada kao Long.
	 * @throws IllegalArgumentException ako je gradID manji od 1
	 */
	public void setGradID(Long gradID) {
		if(gradID < 1) {
			throw new IllegalArgumentException("GradID ne sme biti manji od jedan");
		}
		this.gradID = gradID;
	}


	/**
	 * Metoda koja vraca naziv grada.
	 * 
	 * @return Naziv grada kao String.
	 */
	public String getNaziv() {
		return naziv;
	}


	/**
	 * Metoda koja postavlja naziv grada.
	 * 
	 * @param naziv Naziv grada kao String.
	 * @throws NullPointerException ako je naziv null
	 */
	public void setNaziv(String naziv) {
		if(naziv == null) {
			throw new NullPointerException();
		}
		this.naziv = naziv;
	}

	/**
	 * Vraca String koji predstavlja naziv grada.
	 * @return Naziv grada kao String.
	 */
	@Override
	public String toString() {
		return naziv;
	}


	@Override
    public String nazivTabele() {
        return " Grad ";
    }

    @Override
    public String alijas() {
        return " g ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Grad g = new Grad(rs.getLong("GradID"),
                    rs.getString("Naziv"));

            lista.add(g);
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
        return " GradID = " + gradID;
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


	@Override
	public int hashCode() {
		return Objects.hash(gradID);
	}


	/**
     * Metoda koja poredi dva grada po njihovim ID-u i vraca true ili false
     * 
     * @param obj Objekat sa kojim se vrsi uporedjivanje
     * @return
     * <ul>
     * <li>true - ako su oba objekata klase Grad i imaju isti ID.</li>
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
		Grad other = (Grad) obj;
		return Objects.equals(gradID, other.gradID);
	}
	
	
	
}
