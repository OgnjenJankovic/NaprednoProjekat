package rs.ac.bg.fon.nprog.transfer.util;

/**
 * Klasa koja sadrzi konstante koje predstavljaju nazive sistemskih operacija koje se izvrsavaju na serveru
 * 
 * Sve operacije su definisane kao javne staticke finalne celobrojne promenljive.
 * 
 * Ove konstante se koriste za identifikaciju operacija prilikom komunikacije klijenata sa serverom.
 * Svaka operacija ima jedinstvenu vrednost.
 * 
 * @author OgnjenJankovic
 * @version 1.1.0
 */
public interface Operation {

	/**
	 * Konstanta koja predstavlja operaciju za prijavljivanje administratora
	 */
	public static final int LOGIN = 0;
	/**
	 * Konstanta koja predstavlja operaciju za odjavljivanje administratora
	 */
    public static final int LOGOUT = 1;
    /**
     * Konstanta koja predstavlja operaciju za dodavanje korisnika
     */
    public static final int ADD_KORISNIK = 2;
    /**
     * Konstanta koja predstavlja operaciju za brisanje korisnika
     */
    public static final int DELETE_KORISNIK = 3;
    /**
     * Konstanta koja predstavlja operaciju za apdejtovanje korisnika
     */
    public static final int UPDATE_KORISNIK = 4;
    /**
     * Konstanta koja predstavlja operaciju za vracanje liste korisnika
     */
    public static final int GET_ALL_KORISNIK = 5;

    /**
     * Konstanta koja predstavlja operaciju za dodavanje termina
     */
    public static final int ADD_TERMIN = 6;
    /**
     * Konstanta koja predstavlja operaciju za brisanje termina
     */
    public static final int DELETE_TERMIN = 7;
    /**
     * Konstanta koja predstavlja operaciju za apdejtovanje termina
     */
    public static final int UPDATE_TERMIN = 8;
    /**
     * Konstanta koja predstavlja operaciju za vracanje liste termina
     */
    public static final int GET_ALL_TERMIN = 9;

    /**
     * Konstanta koja predstavlja operaciju za vracanje liste igraca
     */
    public static final int GET_ALL_IGRAC = 10;

    /**
     * Konstanta koja predstavlja operaciju za vracanje liste terena
     */
    public static final int GET_ALL_TEREN = 11;

    /**
     * Konstanta koja predstavlja operaciju za vracanje liste opstina
     */
    public static final int GET_ALL_OPSTINA = 12;
    
    /**
     * Konstanta koja predstavlja operaciju za vracanje liste gradova
     */
    public static final int GET_ALL_GRAD = 13;
    
    /**
     * Konstanta koja predstavlja operaciju za vracanje liste tipova
     */
    public static final int GET_ALL_TIP = 14;
	
	
	
}
