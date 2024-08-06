package rs.ac.bg.fon.nprog.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Grad extends AbstractDomainObject{

	private Long gradID;
    private String naziv;
	
	
	
	public Grad() {
	}
	
	

	public Grad(Long gradID, String naziv) {
		this.gradID = gradID;
		this.naziv = naziv;
	}



	public Long getGradID() {
		return gradID;
	}



	public void setGradID(Long gradID) {
		this.gradID = gradID;
	}



	public String getNaziv() {
		return naziv;
	}



	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

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
	
	
	
}
