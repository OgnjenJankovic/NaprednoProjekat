package rs.ac.bg.fon.nprog.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Klasa koja predstavlja opstinu u kojem se organizuje termin
 * 
 * Ova klasa implementira AbstractDomainObject u odnosu na tabelu opstina u bazi.
 * 
 * Opstina ima svoj ID koji ga jedinstveno identifikuje i naziv.
 * 
 * @author OgnjenJankovic
 * @version 1.1.0
 */
public class Opstina extends AbstractDomainObject{

	/**
	 * ID opstine kao Long
	 */
	private Long opstinaID;
	/**
	 * Naziv opstine kao String
	 */
    private String naziv;

    /**
	 * Vraca String koji predstavlja naziv opstine.
	 * @return Naziv opstine kao String.
	 */
    @Override
    public String toString() {
        return naziv;
    }

    /**
	 * Konstruktor koji inicijalizuje objekat klase Opstina sa prosledjenim vrednostima svih atributa.
	 * 
	 * @param opstinaID ID opstine kao Long.
	 * @param naziv Naziv opstine kao String.
	 */
    public Opstina(Long opstinaID, String naziv) {
        this.opstinaID = opstinaID;
        this.naziv = naziv;
    }

    /**
	 * Konstruktor koji inicijalizuje objekat klase Opstina
	 */
    public Opstina() {
    }
    
    @Override
    public String nazivTabele() {
        return " Opstina ";
    }

    @Override
    public String alijas() {
        return " o ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Opstina o = new Opstina(rs.getLong("OpstinaID"),
                    rs.getString("Naziv"));

            lista.add(o);
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
        return " OpstinaID = " + opstinaID;
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

    /**
	 * Metoda koja vraca ID opstine
	 * 
	 * @return ID opstine kao Long
	 */
    public Long getOpstinaID() {
        return opstinaID;
    }

    /**
	 * Metoda koja postavlja ID opstine.
	 * 
	 * @param opstinaID ID opstine kao Long.
	 * @throws IllegalArgumentException ako je opstinaID manji od 1
	 */
    public void setOpstinaID(Long opstinaID) {
    	if(opstinaID < 1) {
    		throw new IllegalArgumentException("OpstinaID ne sme biti manja od 1");
    	}
        this.opstinaID = opstinaID;
    }

    /**
	 * Metoda koja vraca naziv opstine.
	 * 
	 * @return Naziv opstine kao String.
	 */
    public String getNaziv() {
        return naziv;
    }

    /**
	 * Metoda koja postavlja naziv opstine.
	 * 
	 * @param naziv Naziv opstine kao String.
	 * @throws NullPointerException ako je naziv null
	 */

    public void setNaziv(String naziv) {
    	if(naziv == null) {
    		throw new NullPointerException("Naziv ne sme biti null");
    	}
        this.naziv = naziv;
    }

	

	/**
     * Metoda koja poredi dve opstine po njihovim ID-u i vraca true ili false
     * 
     * @param obj Objekat sa kojim se vrsi uporedjivanje
     * @return
     * <ul>
     * <li>true - ako su oba objekata klase Opstina i imaju isti ID.</li>
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
		Opstina other = (Opstina) obj;
		return Objects.equals(opstinaID, other.opstinaID);
	}
	
	
	
}
