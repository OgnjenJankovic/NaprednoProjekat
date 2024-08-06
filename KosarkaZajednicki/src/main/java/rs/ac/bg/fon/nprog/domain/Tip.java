package rs.ac.bg.fon.nprog.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class Tip extends AbstractDomainObject{

	private Long tipID;
	private String naziv;
	
	@Override
    public String toString() {
        return naziv;
    }
	
	public Tip() {
	}

	public Tip(Long tipID, String naziv) {
		this.tipID = tipID;
		this.naziv = naziv;
	}
	

	public Long getTipID() {
		return tipID;
	}

	public void setTipID(Long tipID) {
		this.tipID = tipID;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Override
    public String nazivTabele() {
        return " Tip ";
    }

    @Override
    public String alijas() {
        return " ti ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Tip t = new Tip(rs.getLong("TipID"),
                    rs.getString("Naziv"));

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
        return " TipID = " + tipID;
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
