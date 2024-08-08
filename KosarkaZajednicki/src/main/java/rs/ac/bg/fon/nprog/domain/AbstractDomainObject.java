package rs.ac.bg.fon.nprog.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *Abstraktna klasa koja daje specifikaciju operacija koje su neophodne za rad sa generickim upitima prilikom komunikacije sa bazom podatada
 *
 *Predstavlja visi nivo apstrakcije u odnosu na sve domenske klase.
 *
 *Takodje, nasledjuje interfejs Serializable kako bi objekat mogao da se prenosi preko mreze
 *
 *@author 2000o
 *@version 1.1.0
 */
public abstract class AbstractDomainObject implements Serializable{

	/**
	 * Vraca naziv tabele u bazi podataka
	 * 
	 * @return Naziv tabele kao String
	 */
	public abstract String nazivTabele();
	
	/**
	 * Vraca alijas tabele iz baze podataka
	 * 
	 * @return Alijas tabele kao String 
	 */
    public abstract String alijas();
    
    /**
     * Kombinuje tabele na specifican nacin i vraca rezultat u formi Stringa
     * 
     * @return String koji predstavlja rezultat kombinacije
     */
    public abstract String join();
    
    /**
     * Vraca listu objekata koja predstavlja ucitane redove iz baze podataka nakon izvrsnja upita.
     * Prolazi se po redovima, ucitavaju se podaci iz svakog reda baze podataka i postaljaju
     * na vrednosti atributa objekata koji se dodaje u listu.
     * 
     * @param rs Objekat koji sadrzi podatke iz baze podataka nakon izvrsavanja upita.
     * @return Lista objekata ucitanih na osnovu vrednosti u bazi podataka nakon izvrsavanja upita.
     * @throws SQLException ako dodje do greske prilikom ucitavanja podataka iz baze
     */
    public abstract ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException;
    
    /**
     * Vraća string koji predstavlja imena kolona koje će biti uključene u SQL INSERT upit.
     * 
     * @return String sa imenima kolona za INSERT upit.
     */
    public abstract String koloneZaInsert();
    
    /**
     * Vraća string koji predstavlja uslov (WHERE klauzulu) za SQL upit.
     * 
     * @return String koji definiše uslov za SQL upit.
     */
    public abstract String uslov();
    
    /**
     * Vraća string koji predstavlja vrednosti koje će biti uključene u SQL INSERT upit.
     * 
     * @return String sa vrednostima za INSERT upit.
     */
    public abstract String vrednostiZaInsert();
    
    /**
     * Vraća string koji predstavlja vrednosti i njihove odgovarajuće kolone za SQL UPDATE upit.
     * 
     * @return String sa vrednostima i kolonama za UPDATE upit.
     */
    public abstract String vrednostiZaUpdate();
    
    /**
     * Vraća string koji predstavlja uslov (WHERE klauzulu) za SQL SELECT upit.
     * 
     * @return String koji definiše uslov za SELECT upit.
     */
    public abstract String uslovZaSelect();
	
	
	
}








