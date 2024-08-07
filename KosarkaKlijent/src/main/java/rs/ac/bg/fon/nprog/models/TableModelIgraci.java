package rs.ac.bg.fon.nprog.models;

import rs.ac.bg.fon.nprog.controller.ClientController;
import rs.ac.bg.fon.nprog.domain.Igrac;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Termin;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class TableModelIgraci extends AbstractTableModel {

    private ArrayList<Igrac> lista;
    private String[] kolone = {"Rb", "Korisnik", "Napomena"};
    private int rb = 0;

    public TableModelIgraci() {
        lista = new ArrayList<>();
    }

    public TableModelIgraci(Termin termin) {
        try {
            lista = ClientController.getInstance().getAllIgrac(termin);
        } catch (Exception ex) {
            Logger.getLogger(TableModelIgraci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Igrac i = lista.get(row);

        switch (column) {
            case 0:
                return i.getRbIgraca();
            case 1:
                return i.getKorisnikIgrac();
            case 2:
                return i.getNapomena();

            default:
                return null;
        }
    }

    public void dodajIgraca(Igrac i) {
        rb = lista.size();
        i.setRbIgraca(++rb);
        lista.add(i);
        fireTableDataChanged();
    }

    public void obrisiIgraca(int row) {
        lista.remove(row);

        rb = 0;
        for (Igrac igrac : lista) {
            igrac.setRbIgraca(++rb);
        }

        fireTableDataChanged();
    }

    public ArrayList<Igrac> getLista() {
        return lista;
    }

    public boolean postojiIgrac(Korisnik korisnikIgrac) {
        for (Igrac igrac : lista) {
            if(igrac.getKorisnikIgrac().getKorisnikID().equals(korisnikIgrac.getKorisnikID())){
                return true;
            }
        }
        return false;
    }

}
