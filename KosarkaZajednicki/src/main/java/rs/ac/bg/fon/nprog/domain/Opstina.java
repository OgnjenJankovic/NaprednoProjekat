package rs.ac.bg.fon.nprog.domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
        this.opstinaID = opstinaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
	
}
