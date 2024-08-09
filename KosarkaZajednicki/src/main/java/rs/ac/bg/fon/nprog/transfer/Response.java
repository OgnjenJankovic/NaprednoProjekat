package rs.ac.bg.fon.nprog.transfer;


import java.io.Serializable;

import rs.ac.bg.fon.nprog.transfer.util.ResponseStatus;
 

/**
 * Klase koja predstavlja serverski odgovor na klijentski zahtev.
 * Sadrži podatke, izuzetak i status odgovora koji definišu rezultat obrade zahteva.
 * Implementira interfejs Serializable kako bi omogućila serijalizaciju objekta prilikom
 * slanja putem mreže.
 * 
 * Atributi:
 * - data (Object): Sadrži podatke kao rezultat uspešne obrade zahteva.
 * - exc (Exception): Sadrži izuzetak ako je došlo do greške prilikom obrade zahteva.
 * - responseStatus (ResponseStatus): Status odgovora koji označava uspeh ili neuspeh obrade zahteva.
 * 
 * @author 2000o
 * @version 1.1.0
 */
public class Response implements Serializable{

	/**
	 * Sadrži podatke kao rezultat uspešne obrade zahteva.
	 * Može biti bilo koji objekat koji predstavlja rezultat komunikacije između servera i klijenta.
	 */
	private Object data;
	/**
	 * Sadrži izuzetak ako je došlo do greške prilikom obrade zahteva.
	 * Ako je obrada zahteva prošla bez grešaka, ovaj atribut će biti null.
	 */
    private Exception exc;
    /**
     * Status odgovora koji označava uspeh ili neuspeh obrade zahteva.
     * Moguće vrednosti su definisane u enumeraciji `ResponseStatus`, kao što su SUCCESS ili FAILURE.
     */
    private ResponseStatus responseStatus;

    /**
     * Konstruktor koji inicijalizuje objekat klase Response
     */
    public Response() {
    }

    /**
     * Konstruktor koji inicijalizuje objekat klase Response sa prosledjenim vrednostima svih atributa
     * 
     * @param data podatke kao rezultat uspešne obrade zahteva kao Object
     * @param exc izuzetak ako je došlo do greške prilikom obrade zahteva kao Exception
     * @param responseStatus Uspeh ili neuspeh obrade zahteva kao ResponseStatus
     */
    public Response(Object data, Exception exc, ResponseStatus responseStatus) {
        this.data = data;
        this.exc = exc;
        this.responseStatus = responseStatus;
    }

    /**
     * Vraća podatke koji su rezultat obrade zahteva.
     * 
     * @return Object koji predstavlja rezultat obrade zahteva.
     */
    public Object getData() {
        return data;
    }

    /**
     * Postavlja podatke kao rezultat obrade zahteva.
     * 
     * @param data Object koji predstavlja rezultat obrade zahteva.
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Metoda koja vraca izuzetak koji se desio prilikom izvrsavanja operacije.
     * @return Izuzetak koji se desio prilikom izvrsavanja operacije.
     */
    public Exception getException() {
        return exc;
    }

    /**
     * Metoda koja postavlja izuzetak koji se desio prilikom izvrsavanja operacije
     * @param exc Izuzetak koji se desio prilikom izvrsavanja operacije.
     */
    public void setException(Exception exc) {
        this.exc = exc;
    }

    /**
     * Vraća status odgovora.
     * 
     * @return ResponseStatus koji označava uspeh ili neuspeh obrade zahteva.
     */
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    /**
     * Postavlja status odgovora.
     * 
     * @param responseStatus ResponseStatus koji označava uspeh ili neuspeh obrade zahteva.
     */
    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
	
}
