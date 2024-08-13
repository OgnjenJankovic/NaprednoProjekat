package rs.ac.bg.fon.nprog.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Opstina extends AbstractDomainObject{

	private Long opstinaID;
    private String naziv;

    @Override
    public String toString() {
        return naziv;
    }

    public Opstina(Long opstinaID, String naziv) {
        this.opstinaID = opstinaID;
        this.naziv = naziv;
    }

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

    public Long getOpstinaID() {
        return opstinaID;
    }

    public void setOpstinaID(Long opstinaID) {
    	if(opstinaID < 1) {
    		throw new IllegalArgumentException("OpstinaID ne sme biti manja od 1");
    	}
        this.opstinaID = opstinaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
    	if(naziv == null) {
    		throw new NullPointerException("Naziv ne sme biti null");
    	}
        this.naziv = naziv;
    }

	@Override
	public int hashCode() {
		return Objects.hash(opstinaID);
	}

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
