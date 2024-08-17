package rs.ac.bg.fon.nprog.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Klasa koja predstavlja teren na kojem se organizuje termin
 * 
 * Ova klasa implementira AbstractDomainObject u odnosu na tabelu teren u bazi.
 * 
 * Teren ima svoj ID koji ga jedinstveno identifikuje,naziv,adresu,opis,cenu po satu, opstinu i grad.
 * 
 * 
 * 
 * @author 2000o
 * @version 1.1.0
 */
public class Teren extends AbstractDomainObject{

	/**
	 * ID terena kao Long
	 */
	private Long terenID;
	/**
	 * Naziv terena kao String
	 */
    private String naziv;
    /**
	 * Adresa terena kao String
	 */
    private String adresa;
    /**
	 * Opis terena kao String
	 */
    private String opis;
    /**
	 * Cena po satu kao Double
	 */
    private double cenaPoSatu;
    /**
     * Opstina na kojoj je teren kao objekat klase Opstina
     */
    private Opstina opstina;
    /**
     * Grad na kojoj je teren kao objekat klase Grad
     */
    private Grad grad;

    
    /**
	 * Vraca String koji predstavlja sve informacije o terenu
	 * @return Sve informacije o terenu kao String.
	 */
    @Override
	public String toString() {
		return "Teren [terenID=" + terenID + ", naziv=" + naziv + ", adresa=" + adresa + ", opis=" + opis
				+ ", cenaPoSatu=" + cenaPoSatu + ", opstina=" + opstina + ", grad=" + grad + "]";
	}

	
    /**
     * Konstruktor koji inicijalizuje objekat klase Teren sa prosledjenim vrednostima svih atributa.
     * 
     * @param terenID ID terena kao Long
     * @param naziv naziv terene kao String
     * @param adresa adresa terena kao String 
     * @param opis opis terena kao String
     * @param cenaPoSatu cena po satu kao Double
     * @param opstina Opstina kao objekat klase Opsitna
     * @param grad Grad kao objekat klase Grad
     */
    public Teren(Long terenID, String naziv, String adresa, String opis, double cenaPoSatu, Opstina opstina,
			Grad grad) {
		super();
		this.terenID = terenID;
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.cenaPoSatu = cenaPoSatu;
		this.opstina = opstina;
		this.grad = grad;
	}

    /**
	 * Konstruktor koji inicijalizuje objekat klase Teren
	 */
	public Teren() {
    }

    @Override
    public String nazivTabele() {
        return " Teren ";
    }

    @Override
    public String alijas() {
        return " t ";
    }

    @Override
    public String join() {
        return " JOIN OPSTINA O ON (O.OPSTINAID = T.OPSTINAID)"
                + " JOIN GRAD G ON (G.GRADID = T.GRADID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Opstina o = new Opstina(rs.getLong("OpstinaID"),
                    rs.getString("o.Naziv"));
            
            Grad g = new Grad(rs.getLong("GradID"), rs.getString("g.Naziv"));
            
            Teren t = new Teren(rs.getLong("terenID"), rs.getString("naziv"), 
                    rs.getString("adresa"), rs.getString("opis"), 
                    rs.getDouble("cenaPoSatu"), o, g);

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
        return " terenID = " + terenID;
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
	 * Metoda koja vraca ID terena
	 * 
	 * @return ID terena kao Long
	 */
    public Long getTerenID() {
        return terenID;
    }

    /**
	 * Metoda koja postavlja ID terena.
	 * 
	 * @param terenID ID terena kao Long.
	 * @throws IllegalArgumentException ako je terenID manji od 0
	 */
    public void setTerenID(Long terenID) {
    	if(terenID<0) {
    		throw new IllegalArgumentException("terenID ne sme biti manji od nule");
    	}
        this.terenID = terenID;
    }

    /**
	 * Metoda koja vraca naziv terena.
	 * 
	 * @return Naziv terena kao String.
	 */
    public String getNaziv() {
        return naziv;
    }

    /**
	 * Metoda koja postavlja naziv terena.
	 * 
	 * @param naziv Naziv terena kao String.
	 * @throws NullPointerException ako je naziv null
	 */
    public void setNaziv(String naziv) {
    	if(naziv==null) {
    		throw new NullPointerException("Naziv ne sme biti null");
    	}
        this.naziv = naziv;
    }

    /**
	 * Metoda koja vraca adresu terena.
	 * 
	 * @return Adresa terena kao String.
	 */
    public String getAdresa() {
        return adresa;
    }

    /**
	 * Metoda koja postavlja adresu terena.
	 * 
	 * @param adresu Adresu terena kao String.
	 * @throws NullPointerException ako je adresa null
	 */
    public void setAdresa(String adresa) {
    	if(adresa==null) {
    		throw new NullPointerException("adresa ne sme biti null");
    	}
        this.adresa = adresa;
    }

    /**
	 * Metoda koja vraca opis terena.
	 * 
	 * @return Opis terena kao String.
	 */
    public String getOpis() {
        return opis;
    }

    /**
	 * Metoda koja postavlja opis terena.
	 * 
	 * @param opis Opis terena kao String.
	 * @throws NullPointerException ako je opis null
	 */
    public void setOpis(String opis) {
    	if(opis==null) {
    		throw new NullPointerException("opis ne sme biti null");
    	}
        this.opis = opis;
    }

    /**
	 * Metoda koja vraca cenu po satu 
	 * 
	 * @return cenu po satu kao Double
	 */
    public double getCenaPoSatu() {
        return cenaPoSatu;
    }

    /**
	 * Metoda koja postavlja cenu po satu.
	 * 
	 * @param cenaPoSatu cena po satu kao Double.
	 * @throws IllegalArgumentException ako je cenaPoSatu manji od 0
	 */
    public void setCenaPoSatu(double cenaPoSatu) {
    	if(cenaPoSatu<0) {
    		throw new IllegalArgumentException("cenaPoSatu ne sme biti manji od nule");
    	}
        this.cenaPoSatu = cenaPoSatu;
    }

    /**
	 * Metoda koja vraca opstinu
	 * 
	 * @return opstinu kao objekat klase Tip.
	 */
    public Opstina getOpstina() {
        return opstina;
    }

    /**
  	 * Metoda koja postavlja opstinu.
  	 * 
  	 * @param opstina Opstina kao objekat klase Opstina.
  	 * @throws NullPointerException ako je opstina null
  	 */
    public void setOpstina(Opstina opstina) {
    	if(opstina==null) {
    		throw new NullPointerException("opstina ne sme biti null");
    	}
        this.opstina = opstina;
    }

    /**
	 * Metoda koja vraca grad
	 * 
	 * @return grad kao objekat klase Grad.
	 */
    public Grad getGrad() {
        return grad;
    }

    /**
  	 * Metoda koja postavlja grad.
  	 * 
  	 * @param grad Grad kao objekat klase Grad.
  	 * @throws NullPointerException ako je grad null
  	 */
    public void setGrad(Grad grad) {
        this.grad = grad;
    }


    /**
     * Metoda koja poredi dva terena po njihovim ID-u i vraca true ili false
     * 
     * @param obj Objekat sa kojim se vrsi uporedjivanje
     * @return
     * <ul>
     * <li>true - ako su oba objekata klase Teren i imaju isti ID.</li>
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
		Teren other = (Teren) obj;
		return Objects.equals(terenID, other.terenID);
	}
	
    
    
}
