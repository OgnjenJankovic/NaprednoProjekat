package rs.ac.bg.fon.nprog.forme;

import rs.ac.bg.fon.nprog.FormKorisnik.FormNoviKorisnik;  
import rs.ac.bg.fon.nprog.FormKorisnik.FormPretragaKorisnika;
import rs.ac.bg.fon.nprog.FormTermin.FormPretragaTermina;
import rs.ac.bg.fon.nprog.controller.ClientController;
import rs.ac.bg.fon.nprog.domain.Administrator;
import rs.ac.bg.fon.nprog.domain.Igrac;
import rs.ac.bg.fon.nprog.domain.Korisnik;
import rs.ac.bg.fon.nprog.domain.Teren;
import rs.ac.bg.fon.nprog.domain.Termin;
import rs.ac.bg.fon.nprog.json.reports.JsonReport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import rs.ac.bg.fon.nprog.models.TableModelIgraci;
import rs.ac.bg.fon.nprog.session.Session;

public class MainForm extends javax.swing.JFrame {

    private Administrator ulogovani;
    private int brojSati;
    private double ukupnaCena;

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();
        setLocationRelativeTo(null);
        this.ulogovani = Session.getInstance().getUlogovani();
        lblUlogovani.setText("Ulogovani administrator: " + ulogovani);
        setTitle("Klijentska forma");
        txtBrojSati.setEditable(false);
        txtUkupnaCena.setEditable(false);
        popuniKorisnike();
        popuniTerene();
        tblIgraci.setModel(new TableModelIgraci());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lblUlogovani = new javax.swing.JLabel();
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
        btnSacuvaj = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu6 = new javax.swing.JMenu();
        miNoviKorisnik = new javax.swing.JMenuItem();
        miPretragaKorisnika = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        miPretragaTermina = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        miOdjava = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblUlogovani.setText("Ulogovani");

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
        txtDatumVremePocetka.setText("10.05.2024 18:00");
        txtDatumVremePocetka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDatumVremePocetkaKeyReleased(evt);
            }
        });

        txtDatumVremeKraja.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd.MM.yyyy HH:mm"))));
        txtDatumVremeKraja.setText("10.05.2024 20:00");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
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

        btnSacuvaj.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnSacuvaj.setText("Sacuvaj termin");
        btnSacuvaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajActionPerformed(evt);
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
                            .addComponent(txtUkupnaCena))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(btnSacuvaj, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSacuvaj, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu6.setText("Korisnik");

        miNoviKorisnik.setText("Novi korisnik");
        miNoviKorisnik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miNoviKorisnikActionPerformed(evt);
            }
        });
        jMenu6.add(miNoviKorisnik);

        miPretragaKorisnika.setText("Pretraga korisnika");
        miPretragaKorisnika.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPretragaKorisnikaActionPerformed(evt);
            }
        });
        jMenu6.add(miPretragaKorisnika);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Termin");

        miPretragaTermina.setText("Pretraga termina");
        miPretragaTermina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPretragaTerminaActionPerformed(evt);
            }
        });
        jMenu7.add(miPretragaTermina);

        jMenuBar1.add(jMenu7);

        jMenu8.setText("Odjava");

        miOdjava.setText("Odjavi se");
        miOdjava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miOdjavaActionPerformed(evt);
            }
        });
        jMenu8.add(miOdjava);

        jMenuBar1.add(jMenu8);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUlogovani)
                .addContainerGap(662, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUlogovani)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void miNoviKorisnikActionPerformed(java.awt.event.ActionEvent evt) {                                               
        new FormNoviKorisnik(this, true).setVisible(true);
    }                                              

    private void miPretragaKorisnikaActionPerformed(java.awt.event.ActionEvent evt) {                                                    
        new FormPretragaKorisnika(this, true).setVisible(true);
    }                                                   

    private void miPretragaTerminaActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        new FormPretragaTermina(this, true).setVisible(true);
    }                                                 

    private void miOdjavaActionPerformed(java.awt.event.ActionEvent evt) {                                         

        int result = JOptionPane.showConfirmDialog(this, "Da li ste sigurni da zelite da "
                + "se odjavite?", "Konfirmacija", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.NO_OPTION) {
            return;
        }

        if (result == JOptionPane.YES_OPTION) {
            try {
            	/*
            	 *  DODAJE SE VREME ODJAVE ADMINISTRATORA U MAPU ODJAVA
            	 */
                Map<Administrator, LocalDateTime> odjaveAdministratora = new HashMap<>();
                odjaveAdministratora.put(ulogovani, LocalDateTime.now());
                JsonReport.setOdjaveAdministratora(odjaveAdministratora);

                JsonReport.generateReport(); 
                //GENERISANJE IZVESTAJA
            	
                ClientController.getInstance().logout(ulogovani);
                new LoginForma().setVisible(true);
                Session.getInstance().setUlogovani(null);
                this.dispose();
            } catch (Exception ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    private void cmbTerenItemStateChanged(java.awt.event.ItemEvent evt) {                                          

        if (cmbTeren.getSelectedItem() != null) {
            if (!txtBrojSati.getText().isEmpty()) {
                Teren teren = (Teren) cmbTeren.getSelectedItem();
                txtUkupnaCena.setText(String.valueOf(teren.getCenaPoSatu() * brojSati));
                ukupnaCena = teren.getCenaPoSatu() * brojSati;
            }
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

        Igrac i = new Igrac(null, -1, napomena, korisnikIgrac);

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

    private void btnSacuvajActionPerformed(java.awt.event.ActionEvent evt) {                                           

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
            Date datumVremePocetka = sdf.parse(txtDatumVremePocetka.getText());
            Date datumVremeKraja = sdf.parse(txtDatumVremeKraja.getText());
            Korisnik korisnikOrganizator = (Korisnik) cmbOrganizator.getSelectedItem();
            Teren teren = (Teren) cmbTeren.getSelectedItem();

            TableModelIgraci tm = (TableModelIgraci) tblIgraci.getModel();

            Termin t;
            
            if(korisnikOrganizator.getTipKorisnika().getNaziv().equals("Premium")){
                t = new Termin(null, datumVremePocetka, datumVremeKraja, brojSati, (ukupnaCena - (ukupnaCena/100)*10),
                    teren, korisnikOrganizator, ulogovani, tm.getLista());
            }else{
                t = new Termin(null, datumVremePocetka, datumVremeKraja, brojSati, ukupnaCena,
                    teren, korisnikOrganizator, ulogovani, tm.getLista());
            }

            ClientController.getInstance().addTermin(t);
            txtBrojSati.setText("");
            brojSati = 0;
            txtUkupnaCena.setText("");
            ukupnaCena = 0;
            txtDatumVremeKraja.setText("");
            txtDatumVremePocetka.setText("");
            txtNapomena.setText("");
            tm.getLista().clear();
            tm.fireTableDataChanged();
            JOptionPane.showMessageDialog(this, "Uspesno sacuvan termin!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
//            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }                                          

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnDodajIgraca;
    private javax.swing.JButton btnIzracunaj;
    private javax.swing.JButton btnObrisiIgraca;
    private javax.swing.JButton btnSacuvaj;
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
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblUlogovani;
    private javax.swing.JMenuItem miNoviKorisnik;
    private javax.swing.JMenuItem miOdjava;
    private javax.swing.JMenuItem miPretragaKorisnika;
    private javax.swing.JMenuItem miPretragaTermina;
    private javax.swing.JTable tblIgraci;
    private javax.swing.JTextField txtBrojSati;
    private javax.swing.JFormattedTextField txtDatumVremeKraja;
    private javax.swing.JFormattedTextField txtDatumVremePocetka;
    private javax.swing.JTextArea txtNapomena;
    private javax.swing.JTextField txtUkupnaCena;
    // End of variables declaration                   

    public void popuniKorisnike() {
        try {
            ArrayList<Korisnik> korisnici = ClientController.getInstance().getAllKorisnik();

            cmbIgrac.removeAllItems();
            cmbOrganizator.removeAllItems();

            for (Korisnik korisnik : korisnici) {
                cmbIgrac.addItem(korisnik);
                cmbOrganizator.addItem(korisnik);
            }

        } catch (Exception ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

}











