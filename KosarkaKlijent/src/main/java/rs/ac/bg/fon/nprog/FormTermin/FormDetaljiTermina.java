package rs.ac.bg.fon.nprog.FormTermin;

import rs.ac.bg.fon.nprog.controller.ClientController;
import rs.ac.bg.fon.nprog.domain.Igrac;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Teren;
import rs.ac.bg.fon.nprog.domain.Termin;
import rs.ac.bg.fon.nprog.forme.MainForm;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.nprog.models.TableModelIgraci;

public class FormDetaljiTermina extends javax.swing.JDialog {

    private Termin termin;
    private int brojSati;
    private double ukupnaCena;

    public FormDetaljiTermina(JDialog parent, boolean modal, Termin termin) {
        super(parent, modal);
        initComponents();
        this.termin = termin;
        setLocationRelativeTo(null);
        setTitle("Detalji termina");
        cmbOrganizator.setEnabled(false);
        cmbOrganizator.getModel().setSelectedItem(termin.getKorisnikOrganizator());
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        txtDatumVremePocetka.setText(sdf.format(termin.getDatumVremePocetka()));
        txtDatumVremeKraja.setText(sdf.format(termin.getDatumVremeKraja()));
        brojSati = termin.getBrojSati();
        ukupnaCena = termin.getUkupnaCena();
        txtBrojSati.setText(String.valueOf(brojSati));
        txtUkupnaCena.setText(String.valueOf(ukupnaCena));
        popuniTerene();
        popuniKorisnike();
        tblIgraci.setModel(new TableModelIgraci(termin));
        txtBrojSati.setEditable(false);
        txtUkupnaCena.setEditable(false);

        if (!termin.getDatumVremePocetka().after(new Date())) {
            txtDatumVremeKraja.setEditable(false);
            txtDatumVremePocetka.setEditable(false);
            txtNapomena.setEditable(false);
            cmbIgrac.setEnabled(false);
            cmbTeren.setEnabled(false);
            btnDodajIgraca.setEnabled(false);
            btnObrisiIgraca.setEnabled(false);
            btnIzmeni.setEnabled(false);
            btnIzracunaj.setEnabled(false);
            btnObrisi.setEnabled(false);
            JOptionPane.showMessageDialog(this, "Ovaj termin je zavrsen, ne mozete da ga menjate!");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnIzracunaj = new javax.swing.JButton();
        txtDatumVremePocetka = new javax.swing.JFormattedTextField();
        txtDatumVremeKraja = new javax.swing.JFormattedTextField();
        txtBrojSati = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbTeren = new javax.swing.JComboBox();
        cmbOrganizator = new javax.swing.JComboBox();
        txtUkupnaCena = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbIgrac = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNapomena = new javax.swing.JTextArea();
        btnDodajIgraca = new javax.swing.JButton();
        btnObrisiIgraca = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblIgraci = new javax.swing.JTable();
        btnZatvori = new javax.swing.JButton();
        btnObrisi = new javax.swing.JButton();
        btnIzmeni = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Termin"));

        jLabel1.setText("Datum i vreme pocetka:");

        jLabel2.setText("Datum i vreme kraja:");

        jLabel3.setText("Broj sati:");

        btnIzracunaj.setText("Izracunaj broj sati");
        btnIzracunaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzracunajActionPerformed(evt);
            }
        });

        txtDatumVremePocetka.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm"))));
        txtDatumVremePocetka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDatumVremePocetkaKeyReleased(evt);
            }
        });

        txtDatumVremeKraja.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm"))));
        txtDatumVremeKraja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDatumVremeKrajaKeyReleased(evt);
            }
        });

        jLabel4.setText("Teren:");

        jLabel5.setText("Organizator:");

        jLabel6.setText("Ukupna cena (din):");

        cmbTeren.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbTeren.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTerenItemStateChanged(evt);
            }
        });

        cmbOrganizator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Igraci"));

        jLabel7.setText("Korisnik:");

        jLabel8.setText("Napomena:");

        cmbIgrac.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtNapomena.setColumns(20);
        txtNapomena.setRows(5);
        jScrollPane1.setViewportView(txtNapomena);

        btnDodajIgraca.setText("Dodaj igraca");
        btnDodajIgraca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajIgracaActionPerformed(evt);
            }
        });

        btnObrisiIgraca.setText("Obrisi igraca");
        btnObrisiIgraca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiIgracaActionPerformed(evt);
            }
        });

        tblIgraci.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblIgraci);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnDodajIgraca, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(btnObrisiIgraca, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbIgrac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbIgrac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(btnDodajIgraca))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnObrisiIgraca)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnZatvori.setText("Zatvori");
        btnZatvori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZatvoriActionPerformed(evt);
            }
        });

        btnObrisi.setText("Obrisi termin");
        btnObrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiActionPerformed(evt);
            }
        });

        btnIzmeni.setText("Izmeni termin");
        btnIzmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnIzracunaj, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDatumVremePocetka))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDatumVremeKraja)
                            .addComponent(txtBrojSati)
                            .addComponent(cmbTeren, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbOrganizator, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUkupnaCena)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnZatvori, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnObrisi, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnIzmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnIzmeni, btnObrisi, btnZatvori});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDatumVremePocetka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDatumVremeKraja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIzracunaj)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBrojSati, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbTeren, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbOrganizator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtUkupnaCena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnZatvori)
                    .addComponent(btnObrisi)
                    .addComponent(btnIzmeni))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void btnZatvoriActionPerformed(java.awt.event.ActionEvent evt) {                                           
        this.dispose();
    }                                          

    private void btnObrisiActionPerformed(java.awt.event.ActionEvent evt) {                                          

        int result = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da "
                + "obrisete ovaj termin?", "Konfirmacija", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.NO_OPTION) {
            return;
        }

        if (result == JOptionPane.YES_OPTION) {
            try {
                ClientController.getInstance().deleteTermin(termin);
                FormPretragaTermina fp = (FormPretragaTermina) getParent();
                fp.refreshTable();
                JOptionPane.showMessageDialog(this, "Uspesno obrisan termin.");
                this.dispose();
            } catch (Exception ex) {
                Logger.getLogger(FormDetaljiTermina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }                                         

    private void btnIzmeniActionPerformed(java.awt.event.ActionEvent evt) {                                          

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date datumVremePocetka = sdf.parse(txtDatumVremePocetka.getText());
            Date datumVremeKraja = sdf.parse(txtDatumVremeKraja.getText());
            Teren teren = (Teren) cmbTeren.getSelectedItem();

            TableModelIgraci tm = (TableModelIgraci) tblIgraci.getModel();

            termin.setBrojSati(brojSati);
            termin.setDatumVremeKraja(datumVremeKraja);
            termin.setDatumVremePocetka(datumVremePocetka);
            termin.setTeren(teren);
            termin.setUkupnaCena(ukupnaCena);
            termin.setIgraci(tm.getLista());

            ClientController.getInstance().updateTermin(termin);
            FormPretragaTermina fp = (FormPretragaTermina) getParent();
            fp.refreshTable();
            JOptionPane.showMessageDialog(this, "Uspesno izmenjen termin.");
            this.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }

    }                                         

    private void btnIzracunajActionPerformed(java.awt.event.ActionEvent evt) {                                             

        if (txtDatumVremePocetka.getText().isEmpty() || txtDatumVremeKraja.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Morate popuniti datum vreme pocetka i kraja!");
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date datumVremePocetka = sdf.parse(txtDatumVremePocetka.getText());
            Date datumVremeKraja = sdf.parse(txtDatumVremeKraja.getText());

            long diffInMillies = datumVremeKraja.getTime() - datumVremePocetka.getTime();
            long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            if (diff < 1) {
                JOptionPane.showMessageDialog(this, "Datum i vreme pocetka mora biti "
                        + "pre datuma i vremena kraja!");
                return;
            }

            SimpleDateFormat sdfValidacija = new SimpleDateFormat("dd.MM.yyyy");
            if (!sdfValidacija.format(datumVremeKraja).equals(sdfValidacija.format(datumVremePocetka))) {
                JOptionPane.showMessageDialog(this, "Datum i vreme pocetka i kraja "
                        + "moraju biti isti!");
                return;
            }

            txtBrojSati.setText(String.valueOf(diff));

            brojSati = Integer.parseInt(txtBrojSati.getText());

            Teren teren = (Teren) cmbTeren.getSelectedItem();
            txtUkupnaCena.setText(String.valueOf(teren.getCenaPoSatu() * brojSati));
            ukupnaCena = teren.getCenaPoSatu() * brojSati;

        } catch (Exception ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                            

    private void txtDatumVremePocetkaKeyReleased(java.awt.event.KeyEvent evt) {                                                 
        txtBrojSati.setText("");
        brojSati = 0;
        txtUkupnaCena.setText("");
        ukupnaCena = 0;
    }                                                

    private void txtDatumVremeKrajaKeyReleased(java.awt.event.KeyEvent evt) {                                               
        txtBrojSati.setText("");
        brojSati = 0;
        txtUkupnaCena.setText("");
        ukupnaCena = 0;
    }                                              

    private void cmbTerenItemStateChanged(java.awt.event.ItemEvent evt) {                                          

        if (cmbTeren.getSelectedItem() != null) {
            if (!txtBrojSati.getText().isEmpty()) {
                Teren teren = (Teren) cmbTeren.getSelectedItem();
                txtUkupnaCena.setText(String.valueOf(teren.getCenaPoSatu() * brojSati));
                ukupnaCena = teren.getCenaPoSatu() * brojSati;
            }
        }
    }                                         

    private void btnDodajIgracaActionPerformed(java.awt.event.ActionEvent evt) {                                               

        Korisnik korisnikIgrac = (Korisnik) cmbIgrac.getSelectedItem();
        Korisnik korisnikOrganizator = (Korisnik) cmbOrganizator.getSelectedItem();

        if (korisnikIgrac.getKorisnikID().equals(korisnikOrganizator.getKorisnikID())) {
            JOptionPane.showMessageDialog(this, "Ovaj korisnik je organizator!");
            return;
        }

        String napomena = txtNapomena.getText();

        if (napomena.isEmpty()) {
            napomena = "/";
        }

        Igrac i = new Igrac(termin, -1, napomena, korisnikIgrac);

        TableModelIgraci tm = (TableModelIgraci) tblIgraci.getModel();

        if (tm.postojiIgrac(korisnikIgrac)) {
            JOptionPane.showMessageDialog(this, "Vec ste uneli ovog igraca za ovaj termin!");
            return;
        }

        tm.dodajIgraca(i);
    }                                              

    private void btnObrisiIgracaActionPerformed(java.awt.event.ActionEvent evt) {                                                

        int row = tblIgraci.getSelectedRow();

        if (row >= 0) {
            TableModelIgraci tm = (TableModelIgraci) tblIgraci.getModel();
            tm.obrisiIgraca(row);
        }
    }                                               


    // Variables declaration - do not modify                     
    private javax.swing.JButton btnDodajIgraca;
    private javax.swing.JButton btnIzmeni;
    private javax.swing.JButton btnIzracunaj;
    private javax.swing.JButton btnObrisi;
    private javax.swing.JButton btnObrisiIgraca;
    private javax.swing.JButton btnZatvori;
    private javax.swing.JComboBox cmbIgrac;
    private javax.swing.JComboBox cmbOrganizator;
    private javax.swing.JComboBox cmbTeren;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblIgraci;
    private javax.swing.JTextField txtBrojSati;
    private javax.swing.JFormattedTextField txtDatumVremeKraja;
    private javax.swing.JFormattedTextField txtDatumVremePocetka;
    private javax.swing.JTextArea txtNapomena;
    private javax.swing.JTextField txtUkupnaCena;
    // End of variables declaration                   

    private void popuniTerene() {
        try {
            ArrayList<Teren> tereni = ClientController.getInstance().getAllTeren();

            cmbTeren.removeAllItems();

            for (Teren teren : tereni) {
                cmbTeren.addItem(teren);
            }

        } catch (Exception ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void popuniKorisnike() {
        try {
            ArrayList<Korisnik> korisnici = ClientController.getInstance().getAllKorisnik();

            cmbIgrac.removeAllItems();

            for (Korisnik korisnik : korisnici) {
                cmbIgrac.addItem(korisnik);
            }

        } catch (Exception ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
