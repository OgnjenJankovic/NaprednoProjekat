package rs.ac.bg.fon.nprog.models;

import rs.ac.bg.fon.nprog.controller.ClientController;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class TableModelKorisnici extends AbstractTableModel implements Runnable {

    private ArrayList<Korisnik> lista;
   private String[] kolone = {"ID", "Ime", "Prezime", "Email", "Telefon", "Tip"};
   private String parametar = "";

   public TableModelKorisnici() {
       try {
           lista = ClientController.getInstance().getAllKorisnik();
       } catch (Exception ex) {
           Logger.getLogger(TableModelKorisnici.class.getName()).log(Level.SEVERE, null, ex);
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
       Korisnik k = lista.get(row);

       switch (column) {
           case 0:
               return k.getKorisnikID();
           case 1:
               return k.getIme();
           case 2:
               return k.getPrezime();
           case 3:
               return k.getEmail();
           case 4:
               return k.getTelefon();
           case 5: 
               return k.getTipKorisnika();
           default:
               return null;
       }
   }

   public Korisnik getSelectedKorisnik(int row) {
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
           Logger.getLogger(TableModelKorisnici.class.getName()).log(Level.SEVERE, null, ex);
       }
   }

   public void setParametar(String parametar) {
       this.parametar = parametar;
       refreshTable();
   }

   public void refreshTable() {
       try {
           lista = ClientController.getInstance().getAllKorisnik();
           if (!parametar.equals("")) {
               ArrayList<Korisnik> novaLista = new ArrayList<>();
               for (Korisnik k : lista) {
                   if (k.getIme().toLowerCase().contains(parametar.toLowerCase())
                           || k.getPrezime().toLowerCase().contains(parametar.toLowerCase())) {
                       novaLista.add(k);
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
