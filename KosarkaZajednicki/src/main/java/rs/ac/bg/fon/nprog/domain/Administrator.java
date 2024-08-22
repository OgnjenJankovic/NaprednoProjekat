package rs.ac.bg.fon.nprog.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Klasa koja predstavlja administratora sistema
 * 
 * Ova klasa implementira AbstractDomainObject u odnosu na tabelu administrator u bazi
 * 
 * Administrator ima svoj ID koji je jedinstven identifikator, kao i ime, prezime, username i password
 * 
 * @author OgnjenJankovic
 * @version 1.1.0
 */
public class Administrator extends AbstractDomainObject{

	/**
	 * ID administratora kao Long
	 */
	private Long administratorID;
	/**
	 * Ime administratora kao String 
	 */
    private String ime;
    /**
     * Prezime administratora kao String
     */
    private String prezime;
    /**
     * Username administratora kao String 
     */
    private String username;
    /**
     * Password administratora kao String
     */
    private String password;

    /**
     * Metoda koja poredi dva administratora po njihovim ID-u i vraca true ili false
     * 
     * @param obj Objekat sa kojim se vrsi uporedjivanje
     * @return
     * <ul>
     * <li>true - ako su oba objekata klase Administrator i imaju isti ID.</li>
     * <li>false - u svim ostalim slucajevima.</li>
     * </ul>
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Administrator other = (Administrator) obj;
        if (!Objects.equals(this.administratorID, other.administratorID)) {
            return false;
        }
        return true;
    }

    /**
     * Konstruktor koji inicijalizuje objekat klase Administrator
     */
    public Administrator() {
    }

    /**
     * Konstruktor koji inicijalizuje objekat klase Administrator sa prosledjenim vrednostima svih atributa
     * 
     * @param administratorID ID administratora kao Long
     * @param ime Ime administratora kao String
     * @param prezime Prezime administratora kao String
     * @param username Username administratora kao String
     * @param password Password administratora kao String 
     */
    public Administrator(Long administratorID, String ime, String prezime, String username, String password) {
        this.administratorID = administratorID;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    /**
     * Metoda koja vraca ID administratora
     * 
     * @return ID administratora kao Long
     */
    public Long getAdministratorID() {
        return administratorID;
    }

    /**
     * Metoda koja postavlja ID administratora
     * 
     * @param administratorID ID administratora kao Long
     * @throws IllegalArgumentException ako je administratorID manji od 1
     */
    public void setAdministratorID(Long administratorID) {
    	if(administratorID < 1) {
    		throw new IllegalArgumentException("AdministratorID ne sme biti manji od jedan");
    	}
        this.administratorID = administratorID;
    }

    /**
     * Metoda koja vraca username administratora
     * 
     * @return Username administratora kao String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Metoda koja postavlja username administratora
     * 
     * @param username Username administratora kao String 
     * @throws NullPointerException ako je username null
     */
    public void setUsername(String username) {
    	if(username == null) {
    		throw new NullPointerException("Username ne sme biti null");
    	}
        this.username = username;
    }

    /**
     * Metoda koja vraca password administratora
     * 
     * @return password administratora kao String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Metoda koja postavlja password administratora
     * 
     * @param password Password administratora kao String 
     * @throws NullPointerException ako je password null
     */
    public void setPassword(String password) {
    	if(password == null) {
    		throw new NullPointerException("Password ne sme biti null");
    	}
        this.password = password;
    }

    /**
     * Metoda koja vraca ime administratora
     * 
     * @return ime administratora kao String
     */
    public String getIme() {
        return ime;
    }

    /**
     * Metoda koja postavlja ime administratora
     * 
     * @param ime Ime administratora kao String 
     * @throws NullPointerException ako je ime null
     */
    public void setIme(String ime) {
    	if(ime == null) {
    		throw new NullPointerException("Ime ne sme biti null");
    	}
        this.ime = ime;
    }

    /**
     * Metoda koja vraca prezime administratora
     * 
     * @return prezime administratora kao String
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Metoda koja postavlja prezime administratora
     * 
     * @param prezime Prezime administratora kao String 
     * @throws NullPointerException ako je prezime null
     */
    public void setPrezime(String prezime) {
    	if(prezime == null) {
    		throw new NullPointerException("Prezime ne sme biti null");
    	}
        this.prezime = prezime;
    }

    /**
     * Metoda koja vraca String koji predstavlja ime i prezime administratora
     * 
     * @return Ime i prezime administratora kao String 
     */
    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String nazivTabele() {
        return " administrator ";
    }

    @Override
    public String alijas() {
        return " a ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));

            lista.add(a);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Username, Password) ";
    }

    @Override
    public String uslov() {
        return " AdministratorID = " + administratorID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + username + "', '" + password + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "Username = '" + username + "', Password = '" + password + "' ";
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
