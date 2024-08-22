package rs.ac.bg.fon.nprog.transfer;

import java.io.Serializable;

/**
 * Klasa koja predstavlja klijentski zahtev.
 * 
 * Klasa Request se sastoji od operation koju je potrebno izvrsiti i data parametra koji se odnosi na odredjenu operaciju iz klase Operacije.
 * Data je objekat koji klijent salje kao deo zahteva
 * 
 * Zahtev se salje sa klijenata ka serveru radi izvrsavanja odredjene operacije.
 * Takodje, implementira interfejs Serializable kako bi objekat mogao da se prenosi preko mreze
 * 
 * @author OgnjenJankovic
 * @version 1.1.0
 */
public class Request implements Serializable {

	/**
	 * Operacija zahteva.
	 * 
	 * Ova vrednost predstavlja redni broj operacije koja se zeli izvrsiti
	 * Vrednost operacije se uzima iz klase Operation.
	 */
	private int operation;
	
	/**
	 * Parametar zahteva.
	 * 
	 * Objekat koji klijent salje kao deo zahteva
	 */
    private Object data;

    /**
     * Konstruktor koji inicijalizuje objekat klase Request
     */
    public Request() {
    }

    /**
     * Konstruktor koji inicijalizuje objekat klase Request sa prosledjenim vrednostima svih atributa
     * 
     * @param operation Operacija zahteva kao int
     * @param data Parametar zahteva kao Object
     */
    public Request(int operation, Object data) {
        this.operation = operation;
        this.data = data;
    }

    /**
     * Metoda koja predstavlja parametar zahteva.
     * 
     * @return parametar Parametar zahteva kao objekat klase Object
     */
    public Object getData() {
        return data;
    }

    /**
     * Metoda koja vraca operaciju zahteva
     * 
     * @return Operacija zahteva kao finalna int vrednost iz klase Operation.
     */
    public int getOperation() {
        return operation;
    }

    /**
     * Metoda koja predstavlja operaciju zahteva.
     * @return operacija Operacija zahteva kao finalna int vrednost iz klase Operacije.
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Metoda koja postavlja operaciju zahteva.
     * @param operation Operacija zahteva kao finalna int vrednost iz klase Operacije.
     */
    public void setOperation(int operation) {
        this.operation = operation;
    }
	
	
}
