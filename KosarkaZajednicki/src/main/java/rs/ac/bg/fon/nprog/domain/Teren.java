package rs.ac.bg.fon.nprog.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Teren extends AbstractDomainObject{

	private Long terenID;
    private String naziv;
    private String adresa;
    private String opis;
    private double cenaPoSatu;
    private Opstina opstina;
    private Grad grad;

    

    @Override
	public String toString() {
		return "Teren [terenID=" + terenID + ", naziv=" + naziv + ", adresa=" + adresa + ", opis=" + opis
				+ ", cenaPoSatu=" + cenaPoSatu + ", opstina=" + opstina + ", grad=" + grad + "]";
	}

	

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

    public Long getTerenID() {
        return terenID;
    }

    public void setTerenID(Long terenID) {
    	if(terenID<0) {
    		throw new IllegalArgumentException("terenID ne sme biti manji od nule");
    	}
        this.terenID = terenID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
    	if(naziv==null) {
    		throw new NullPointerException("Naziv ne sme biti null");
    	}
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
    	if(adresa==null) {
    		throw new NullPointerException("adresa ne sme biti null");
    	}
        this.adresa = adresa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
    	if(opis==null) {
    		throw new NullPointerException("opis ne sme biti null");
    	}
        this.opis = opis;
    }

    public double getCenaPoSatu() {
        return cenaPoSatu;
    }

    public void setCenaPoSatu(double cenaPoSatu) {
    	if(cenaPoSatu<0) {
    		throw new IllegalArgumentException("cenaPoSatu ne sme biti manji od nule");
    	}
        this.cenaPoSatu = cenaPoSatu;
    }

    public Opstina getOpstina() {
        return opstina;
    }

    public void setOpstina(Opstina opstina) {
    	if(opstina==null) {
    		throw new NullPointerException("opstina ne sme biti null");
    	}
        this.opstina = opstina;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }
	
}
