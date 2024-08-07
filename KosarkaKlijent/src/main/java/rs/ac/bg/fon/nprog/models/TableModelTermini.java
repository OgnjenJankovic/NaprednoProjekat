package rs.ac.bg.fon.nprog.models;

import rs.ac.bg.fon.nprog.controller.ClientController;
import rs.ac.bg.fon.nprog.domain.Igrac;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Termin;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class TableModelTermini extends AbstractTableModel implements Runnable {

	   private ArrayList<Termin> lista;
	    private String[] kolone = {"ID", "Organizator", "Teren", "Datum i vreme pocetka",
	        "Datum i vreme kraja", "Broj sati", "Ukupna cena"};
	    private String parametar = "";

	    public TableModelTermini(Korisnik k) {
	        try {
	            lista = ClientController.getInstance().getAllTermin(k);
	        } catch (Exception ex) {
	            Logger.getLogger(TableModelTermini.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }
	    
	    public TableModelTermini() {
	        try {
	            lista = ClientController.getInstance().getAllTermin();
	        } catch (Exception ex) {
	            Logger.getLogger(TableModelTermini.class.getName()).log(Level.SEVERE, null, ex);
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
	        Termin t = lista.get(row);
	        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");

	        switch (column) {
	            case 0:
	                return t.getTerminID();
	            case 1:
	                return t.getKorisnikOrganizator();
	            case 2:
	                return t.getTeren();
	            case 3:
	                return sdf.format(t.getDatumVremePocetka());
	            case 4:
	                return sdf.format(t.getDatumVremeKraja());
	            case 5:
	                return t.getBrojSati();
	            case 6:
	                return t.getUkupnaCena() + "din";

	            default:
	                return null;
	        }
	    }

	    public Termin getSelectedTermin(int row) {
	        return lista.get(row);
	    }

	    @Override
	    public void run() {
	        try {
	            while (!Thread.currentThread().isInterrupted()) {
	                Thread.sleep(10000);
	                refreshTable();
	            }
	        } catch (InterruptedException ex) {
	            Logger.getLogger(TableModelTermini.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }

	    public void setParametar(String parametar) {
	        this.parametar = parametar;
	        refreshTable();
	    }

	    public void refreshTable() {
	        try {
	            lista = ClientController.getInstance().getAllTermin();
	            if (!parametar.equals("")) {
	                ArrayList<Termin> novaLista = new ArrayList<>();
	                for (Termin t : lista) {
	                    if (t.getKorisnikOrganizator().getIme().toLowerCase().contains(parametar.toLowerCase())
	                            || t.getKorisnikOrganizator().getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
	                        novaLista.add(t);
	                    }
	                }
	                lista = novaLista;
	            }

	            fireTableDataChanged();

	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }


	}
