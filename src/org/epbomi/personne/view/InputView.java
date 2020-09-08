package org.epbomi.personne.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import org.epbomi.personne.controleur.Controleur;
import org.epbomi.personne.model.Contact;
import org.epbomi.personne.model.Departement;
import org.epbomi.personne.model.Eglise;
import org.epbomi.personne.model.Ethnie;
import org.epbomi.personne.model.Ministere;
import org.epbomi.personne.model.Personne;
import org.epbomi.personne.model.Profession;
import org.epbomi.personne.model.Secteur;
import org.epbomi.personne.model.Sexe;
import org.epbomi.personne.model.Statut;
import org.epbomi.personne.model.VieConjugale;
import org.epbomi.personne.patterns.Observable;
import org.epbomi.personne.patterns.Observer;
import org.epbomi.personne.utils.DateUtils;

public class InputView extends JPanel implements Observable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1615434423361932739L;
	private JTextField codeField;
	private JTextField nomField;
	private JTextField prenomsField;
	private JTextField lieuField;
	private JTextField nationaliteField;
	private JTextField photoField;
	private JTextField partenaireField;
	private JTextField responsabiliteField;
	private JTextField professionField;
	private JTextField structureField;
	private JTextField postalField;
	private JLayeredPane layeredPane;
	private CardLayout cl;
	private JRadioButton firstrbtn;
	private JRadioButton secondrbtn;
	private JButton nextBtn;
	private JButton previousBtn;
	private JSpinner dateSpinner;
	private JSpinner dateNaissanceSpin;
	private JComboBox<Sexe> sexCombo;
	private JSpinner enfantSpinner;
	
	@SuppressWarnings("unused")
	private Controleur control;
	private JComboBox<Statut> situationCombo;
	private JSpinner conversionSpin;
	private JSpinner baptemeSpin;
	private JComboBox<Eglise> sectionCombo;
	private JComboBox<Secteur> secteurCombo;
	private JFormattedTextField urgenceField;
	private JFormattedTextField phone2Field;
	private JFormattedTextField phoneField;
	private JTextArea descriptifArea;
	private JFormattedTextField emailField;
	private JTextArea residenceArea;
	private JComboBox<Departement> departementCombo;
	
	private List<Observer> obs = new ArrayList<>();
	private JComboBox<Ethnie> ethnieCombo;

	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public InputView(Controleur control)
	{
		init(control, null);
	}
	
	public InputView(Controleur control, Personne p)
	{
		init(control, p);
	}
	
	public void init(Controleur control, Personne p) {
		this.control = control;
		setBackground(new Color(255, 255, 255));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		nextBtn = new JButton("Suivant");
		nextBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(layeredPane, "ministryPan");
				secondrbtn.setSelected(true);
				
				if(((JButton)e.getSource()).getText().equals("Terminer"))
				{
					if(InputView.this.isValidForm())
					{
						Ministere m = new Ministere(0, DateUtils.toLocalDate((Date)conversionSpin.getModel().getValue()),
								DateUtils.toLocalDate((Date)baptemeSpin.getModel().getValue()),
								(Departement)departementCombo.getSelectedItem(),responsabiliteField.getText(),
								(Eglise)sectionCombo.getSelectedItem());
						
						Profession pr = new Profession(0,(Secteur)secteurCombo.getSelectedItem(), professionField.getText(),
								structureField.getText(), descriptifArea.getText());
						
						VieConjugale vc = new VieConjugale(0, (Statut)situationCombo.getSelectedItem(),
								partenaireField.getText(), DateUtils.toLocalDate((Date)dateSpinner.getModel()
										.getValue()), (Integer)enfantSpinner.getValue());
						
						Contact c = new Contact(0, phoneField.getText(), phone2Field.getText(), urgenceField.getText(),
								postalField.getText(), emailField.getText(), residenceArea.getText());
						
						Personne pp = new Personne(0,codeField.getText(), nomField.getText(), prenomsField.getText(),
								(Sexe)sexCombo.getSelectedItem(), DateUtils.toLocalDate((Date)dateNaissanceSpin.getModel().getValue()), lieuField.getText(), 
								(Ethnie)ethnieCombo.getSelectedItem(), nationaliteField.getText(), Paths.get(photoField.getText()));
						
						pp.setMinistere(m);
						pp.setProfession(pr);
						pp.setVieConjugale(vc);
						pp.setContact(c);
						
						if(p != null)
						{
							pp.setPersonneID(p.getPersonneID() > 0 ? p.getPersonneID() : 0);
							
							if(p.getMinistere() != null)
								pp.getMinistere().setMinistereID(p.getMinistere().getMinistereID() > 0 ? 
										p.getMinistere().getMinistereID() : 0);
							
							if(p.getProfession() != null)
								pp.getProfession().setProfessionID(p.getProfession().getProfessionID() > 0 ?
										p.getProfession().getProfessionID() : 0);
							
							if(p.getVieConjugale() != null)
							{
								pp.getVieConjugale().setVieConjugaleID(p.getVieConjugale().getVieConjugaleID() > 0 ?
										p.getVieConjugale().getVieConjugaleID() : 0);
							}
							
							if(p.getContact() != null)
							{
								pp.getContact().setContactID(p.getContact().getContactID() > 0 ?
										p.getContact().getContactID() : 0);
							}
						}
						
						if(control.save(pp))
							if(pp.getPersonneID() == 0)
							{
								int rep = JOptionPane.showConfirmDialog(null, pp.toString() + " a bien été ajouté.\n"
										+ "Voulez-vous continuer?","Ajouter",JOptionPane.YES_NO_OPTION);
								if(rep == JOptionPane.YES_OPTION)
								{
									cl.show(layeredPane, "identityPan");
									codeField.setText("");
									nomField.setText("");
									prenomsField.setText("");
									lieuField.setText("");
									ethnieCombo.getModel().setSelectedItem(Ethnie.INCONNU);
									nationaliteField.setText("");
									photoField.setText("");
									structureField.setText("");
									partenaireField.setText("");
									responsabiliteField.setText("");
									professionField.setText("");
									postalField.setText("");
									situationCombo.setSelectedIndex(0);
									sectionCombo.setSelectedIndex(0);
									sexCombo.setSelectedIndex(0);
									secteurCombo.setSelectedIndex(0);
									departementCombo.setSelectedIndex(0);
									nextBtn.setText("Suivant");
								}
								else
								{
									updateObserverForScreen();
								}
								
							}
							else
							{
								JOptionPane.showMessageDialog(null, pp.toString() + " a bien été mis à jour");
								updateObserverForScreen();
							}
						
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Veuillez verifier tous les champs!", "Attention", 
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
				
				((JButton)e.getSource()).setText("Terminer");
				
			}
		});
		panel.add(nextBtn, BorderLayout.EAST);
		
		previousBtn = new JButton("Pr\u00E9c\u00E9dent");
		previousBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		previousBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(layeredPane, "identityPan");
				firstrbtn.setSelected(true);
				
				nextBtn.setText("Suivant");
			}
		});
		panel.add(previousBtn, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel.add(panel_1, BorderLayout.CENTER);
		
		firstrbtn = new JRadioButton("");
		firstrbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(layeredPane, "identityPan");				
				nextBtn.setText("Suivant");
			}
		});
		firstrbtn.setOpaque(false);
		panel_1.add(firstrbtn);
		
		secondrbtn = new JRadioButton("");
		secondrbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(layeredPane, "ministryPan");
				//secondrbtn.setSelected(true);
				
				nextBtn.setText("Terminer");
			}
		});
		secondrbtn.setOpaque(false);
		panel_1.add(secondrbtn);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(firstrbtn);
		bg.add(secondrbtn);
		
		firstrbtn.setSelected(true);
		
		layeredPane = new JLayeredPane();
		add(layeredPane, BorderLayout.CENTER);
		cl =new CardLayout(0, 0);
		layeredPane.setLayout(cl);
		
		JPanel idenPan = new JPanel();
		idenPan.setOpaque(false);
		idenPan.setName("identitypan");
		layeredPane.add(idenPan, "identityPan");
		idenPan.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(0, 0, 5, 0, (Color) Color.RED));
		panel_2.setBackground(new Color(0, 0, 255));
		idenPan.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("IDENTITE/MATRIMONIAL");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setOpaque(false);
		idenPan.add(panel_3, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_4.setOpaque(false);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setOpaque(false);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4_1, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_4_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
						.addComponent(panel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_4_1.setLayout(new BoxLayout(panel_4_1, BoxLayout.Y_AXIS));
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Situation matrimoniale", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_13.setMaximumSize(new Dimension(32767, 60));
		panel_13.setBackground(new Color(255, 255, 255));
		panel_4_1.add(panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		situationCombo = new JComboBox<>();
		situationCombo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		situationCombo.setModel(new DefaultComboBoxModel<Statut>(Statut.values()));
		situationCombo.setBackground(new Color(255, 255, 255));
		panel_13.add(situationCombo);
		situationCombo.setMaximumSize(new Dimension(32767, 50));
		
		
		if(p != null && p.getVieConjugale()!= null && p.getVieConjugale().getSituation() != null)
		{
			situationCombo.getModel().setSelectedItem(p.getVieConjugale().getSituation());
		}
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalStrut_2.setMaximumSize(new Dimension(32767, 10));
		panel_4_1.add(verticalStrut_2);
		
		partenaireField = new JTextField();
		partenaireField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		partenaireField.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Partenaire", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		partenaireField.setMaximumSize(new Dimension(2147483647, 50));
		panel_4_1.add(partenaireField);
		partenaireField.setColumns(10);
		if(p != null && !p.getVieConjugale().getConjoint().isEmpty()) 
		{
			partenaireField.setText(p.getVieConjugale().getConjoint());
		}
		
		
		situationCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(((Statut)situationCombo.getSelectedItem()).equals(Statut.CELIBATAIRE) ||
						((Statut)situationCombo.getSelectedItem()).equals(Statut.VEUF) ||
						((Statut)situationCombo.getSelectedItem()).equals(Statut.INCONNU))
				{
					partenaireField.setEnabled(false);
					dateSpinner.setEnabled(false);
				}
				else
				{
					partenaireField.setEnabled(true);
					dateSpinner.setEnabled(true);
				}
			}
			
		});
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalStrut_3.setMaximumSize(new Dimension(32767, 10));
		panel_4_1.add(verticalStrut_3);
		
		JPanel panel_14 = new JPanel();
		panel_14.setOpaque(false);
		panel_14.setMaximumSize(new Dimension(32767, 60));
		panel_14.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Date union", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4_1.add(panel_14);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		SimpleDateFormat model1 = new SimpleDateFormat("dd-MMM-yyyy");
		dateSpinner = new JSpinner(new SpinnerDateModel());
		dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, model1.toPattern()));
		dateSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_14.add(dateSpinner, BorderLayout.CENTER);
		dateSpinner.setToolTipText("Date union");
		dateSpinner.setMaximumSize(new Dimension(32767, 50));
		if(p != null && p.getVieConjugale().getDateUnion() != null)
		{
			//dateSpinner.getModel().setValue(p.getVieConjugale().getDateUnion());
			dateSpinner.getModel().setValue(DateUtils.toDate(p.getVieConjugale().getDateUnion()));
		}
		
		
		  if(((Statut)situationCombo.getSelectedItem()).equals(Statut.CELIBATAIRE) ||
		  ((Statut)situationCombo.getSelectedItem()).equals(Statut.VEUF) ||
		  ((Statut)situationCombo.getSelectedItem()).equals(Statut.VEUF)) 
		  {
			  partenaireField.setEnabled(false); 
			  dateSpinner.setEnabled(false); 
		  }
		 
		
		Component verticalStrut_3_1 = Box.createVerticalStrut(20);
		verticalStrut_3_1.setMaximumSize(new Dimension(32767, 10));
		panel_4_1.add(verticalStrut_3_1);
		
		JPanel panel_15 = new JPanel();
		panel_15.setOpaque(false);
		panel_15.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nombre d'enfants", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_15.setMaximumSize(new Dimension(32767, 60));
		panel_4_1.add(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		enfantSpinner = new JSpinner();
		enfantSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_15.add(enfantSpinner, BorderLayout.CENTER);
		enfantSpinner.setToolTipText("Nombre enfants");
		enfantSpinner.setMaximumSize(new Dimension(32767, 50));
		if(p != null && p.getVieConjugale().getNombreEnfants() > 0)
		{
			enfantSpinner.getModel().setValue(p.getVieConjugale().getNombreEnfants());
		}
		
		codeField = new JTextField();
		codeField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		codeField.setMaximumSize(new Dimension(2147483647, 50));
		codeField.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Code", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		codeField.setColumns(10);
		if(p != null && !p.getCode().isEmpty()) {codeField.setText(p.getCode());}
		
		nomField = new JTextField();
		nomField.setMaximumSize(new Dimension(2147483647, 50));
		nomField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nomField.setColumns(10);
		nomField.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nom", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		if(p != null && !p.getNom().isEmpty()) {nomField.setText(p.getNom());}
		
		prenomsField = new JTextField();
		prenomsField.setMaximumSize(new Dimension(2147483647, 50));
		prenomsField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		prenomsField.setColumns(10);
		prenomsField.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Pr\u00E9noms", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		if(p != null &&!p.getPrenoms().isEmpty()) {prenomsField.setText(p.getPrenoms());}
		
		lieuField = new JTextField();
		lieuField.setMaximumSize(new Dimension(2147483647, 50));
		lieuField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lieuField.setColumns(10);
		lieuField.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Lieu de naissance", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		if(p != null && !p.getLieuDeNaissance().isEmpty()) {lieuField.setText(p.getLieuDeNaissance());}
		//if(p != null && !p.getEthnie().equals(Ethnie.INCONNU)) {ethnieCombo.setText(p.getEthnie());}
		
		nationaliteField = new JTextField();
		nationaliteField.setMaximumSize(new Dimension(2147483647, 50));
		nationaliteField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nationaliteField.setColumns(10);
		nationaliteField.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nationalite", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		if(p != null && !p.getNationalite().isEmpty()) {nationaliteField.setText(p.getNationalite());}
		
		JPanel panel_5 = new JPanel();
		panel_5.setMaximumSize(new Dimension(32767, 40));
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JButton addPhoto = new JButton("Ajouter photo");
		addPhoto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png","bmp"));
				int val = chooser.showOpenDialog(InputView.this);
				if(val == JFileChooser.APPROVE_OPTION)
				{
					File file = chooser.getSelectedFile();
					photoField.setText(file.getAbsolutePath());
				}
			}
		});
		panel_5.add(addPhoto, BorderLayout.WEST);
		
		photoField = new JTextField();
		photoField.setEditable(false);
		photoField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(photoField);
		photoField.setColumns(10);
		if(p != null && !p.getPathImg().toString().isEmpty()) {photoField.setText(p.getPathImg().toString());}
		
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		panel_4.add(codeField);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setMaximumSize(new Dimension(32767, 10));
		panel_4.add(verticalStrut);
		panel_4.add(nomField);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setMaximumSize(new Dimension(32767, 10));
		panel_4.add(verticalStrut_1);
		panel_4.add(prenomsField);
		
		Component verticalStrut_1_1 = Box.createVerticalStrut(20);
		verticalStrut_1_1.setMaximumSize(new Dimension(32767, 10));
		panel_4.add(verticalStrut_1_1);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		panel_6.setMaximumSize(new Dimension(32767, 60));
		panel_6.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Sexe", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		sexCombo = new JComboBox<>();
		sexCombo.setModel(new DefaultComboBoxModel<Sexe>(Sexe.values()));
		sexCombo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_6.add(sexCombo);
		if(p != null && p.getSexe() != null)
		{
			sexCombo.getModel().setSelectedItem(p.getSexe());
		}
		
		Component verticalStrut_1_1_1 = Box.createVerticalStrut(20);
		verticalStrut_1_1_1.setMaximumSize(new Dimension(32767, 10));
		panel_4.add(verticalStrut_1_1_1);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(255, 255, 255));
		panel_12.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Date de naissance", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_12.setMaximumSize(new Dimension(32767, 60));
		panel_4.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		SimpleDateFormat model = new SimpleDateFormat("dd-MMM-yyyy");
		dateNaissanceSpin = new JSpinner(new SpinnerDateModel());
		dateNaissanceSpin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateNaissanceSpin.setEditor(new JSpinner.DateEditor(dateNaissanceSpin, model.toPattern()));
		panel_12.add(dateNaissanceSpin, BorderLayout.CENTER);
		panel_4.add(lieuField);
		if(p != null && p.getDateDeNaissance() != null) { 
			dateNaissanceSpin.getModel().setValue(DateUtils.toDate(p.getDateDeNaissance()));
		}
		
		Component verticalStrut_1_1_1_1 = Box.createVerticalStrut(20);
		verticalStrut_1_1_1_1.setMaximumSize(new Dimension(32767, 10));
		panel_4.add(verticalStrut_1_1_1_1);
		
		JPanel panel_17 = new JPanel();
		panel_17.setOpaque(false);
		panel_17.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Ethnie", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_17.setMinimumSize(new Dimension(10, 60));
		panel_17.setPreferredSize(new Dimension(10, 60));
		panel_17.setMaximumSize(new Dimension(32767, 60));
		panel_4.add(panel_17);
		panel_17.setLayout(new BorderLayout(0, 0));
		
		ethnieCombo = new JComboBox<>();
		ethnieCombo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ethnieCombo.setModel(new DefaultComboBoxModel<>(Ethnie.values()));
		if(p != null && p.getEthnie() != null)
		{
			ethnieCombo.getModel().setSelectedItem(p.getEthnie());
		}
		panel_17.add(ethnieCombo, BorderLayout.CENTER);
		
		Component verticalStrut_1_1_1_1_1 = Box.createVerticalStrut(20);
		verticalStrut_1_1_1_1_1.setMaximumSize(new Dimension(32767, 10));
		panel_4.add(verticalStrut_1_1_1_1_1);
		panel_4.add(nationaliteField);
		
		Component verticalStrut_1_1_1_1_1_1 = Box.createVerticalStrut(20);
		verticalStrut_1_1_1_1_1_1.setMaximumSize(new Dimension(32767, 10));
		panel_4.add(verticalStrut_1_1_1_1_1_1);
		panel_4.add(panel_5);
		panel_3.setLayout(gl_panel_3);
		
		JPanel minPan = new JPanel();
		minPan.setOpaque(false);
		minPan.setName("ministryPan");
		layeredPane.add(minPan, "ministryPan");
		minPan.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new MatteBorder(0, 0, 5, 0, (Color) Color.RED));
		panel_7.setBackground(new Color(0, 0, 255));
		minPan.add(panel_7, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("MINISTERE / PROFESSION/CONTACT");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		panel_7.add(lblNewLabel_1);
		
		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		minPan.add(panel_8, BorderLayout.CENTER);
		
		JPanel panel_9 = new JPanel();
		panel_9.setOpaque(false);
		
		JPanel panel_9_1 = new JPanel();
		panel_9_1.setOpaque(false);
		GroupLayout gl_panel_8 = new GroupLayout(panel_8);
		gl_panel_8.setHorizontalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(panel_9_1, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
					.addGap(12))
		);
		gl_panel_8.setVerticalGroup(
			gl_panel_8.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_8.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_8.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_9_1, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
						.addComponent(panel_9, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel_9_1.setLayout(new BoxLayout(panel_9_1, BoxLayout.Y_AXIS));
		
		descriptifArea = new JTextArea();
		descriptifArea.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Descriptif activit\u00E9", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		descriptifArea.setMaximumSize(new Dimension(2147483647, 110));
		panel_9_1.add(descriptifArea);
		if(p != null && !p.getProfession().getDescriptifActivite().isEmpty()) 
		{
			descriptifArea.setText(p.getProfession().getDescriptifActivite());
		}
		
		Component verticalStrut_4_2_1 = Box.createVerticalStrut(20);
		verticalStrut_4_2_1.setMaximumSize(new Dimension(32767, 10));
		panel_9_1.add(verticalStrut_4_2_1);
		
		MaskFormatter phone = null;
		try {
			phone = new MaskFormatter("## ## ## ##");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		phoneField = new JFormattedTextField(phone);
		phoneField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phoneField.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "T\u00E9l\u00E9phone 1", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		phoneField.setMaximumSize(new Dimension(2147483647, 50));
		panel_9_1.add(phoneField);
		if(p != null && !p.getContact().getCel().isEmpty()) {
			phoneField.setText(p.getContact().getCel());
		}
		
		Component verticalStrut_4_2_1_1_1_1_1_1 = Box.createVerticalStrut(20);
		verticalStrut_4_2_1_1_1_1_1_1.setMaximumSize(new Dimension(32767, 10));
		panel_9_1.add(verticalStrut_4_2_1_1_1_1_1_1);
		
		phone2Field = new JFormattedTextField(phone);
		phone2Field.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phone2Field.setMaximumSize(new Dimension(2147483647, 50));
		phone2Field.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "T\u00E9l\u00E9phone 2", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9_1.add(phone2Field);
		if(p != null && !p.getContact().getTel().isEmpty()) {
			phone2Field.setText(p.getContact().getTel());
			}
		
		Component verticalStrut_4_2_1_1_1_1_1 = Box.createVerticalStrut(20);
		verticalStrut_4_2_1_1_1_1_1.setMaximumSize(new Dimension(32767, 10));
		panel_9_1.add(verticalStrut_4_2_1_1_1_1_1);
		
		urgenceField = new JFormattedTextField(phone);
		urgenceField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		urgenceField.setMaximumSize(new Dimension(2147483647, 50));
		urgenceField.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Numero en cas d'urgence", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9_1.add(urgenceField);
		if(p != null && !p.getContact().getUrgence().isEmpty()) {
			urgenceField.setText(p.getContact().getUrgence());
			}
		
		Component verticalStrut_4_2_1_1_1_1 = Box.createVerticalStrut(20);
		verticalStrut_4_2_1_1_1_1.setMaximumSize(new Dimension(32767, 10));
		panel_9_1.add(verticalStrut_4_2_1_1_1_1);
		
		postalField = new JTextField();
		postalField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		postalField.setMaximumSize(new Dimension(2147483647, 50));
		postalField.setColumns(10);
		postalField.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Adresse postale", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9_1.add(postalField);
		if(p != null && !p.getContact().getPostal().isEmpty()) {postalField.setText(p.getContact().getPostal());}
		
		Component verticalStrut_4_2_1_1_1 = Box.createVerticalStrut(20);
		verticalStrut_4_2_1_1_1.setMaximumSize(new Dimension(32767, 10));
		panel_9_1.add(verticalStrut_4_2_1_1_1);
		
		emailField = new JFormattedTextField();
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailField.setMaximumSize(new Dimension(2147483647, 50));
		emailField.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Email", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9_1.add(emailField);
		if(p != null && !p.getContact().getEmail().isEmpty()) {emailField.setText(p.getContact().getEmail());}
		
		Component verticalStrut_4_2_1_1 = Box.createVerticalStrut(20);
		verticalStrut_4_2_1_1.setMaximumSize(new Dimension(32767, 10));
		panel_9_1.add(verticalStrut_4_2_1_1);
		
		residenceArea = new JTextArea();
		residenceArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		residenceArea.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "R\u00E9sidence", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		residenceArea.setMaximumSize(new Dimension(2147483647, 110));
		panel_9_1.add(residenceArea);
		if(p != null && !p.getContact().getResidence().isEmpty()) {residenceArea.setText(p.getContact().getResidence());}
		
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
		
		JPanel panel_10 = new JPanel();
		panel_10.setOpaque(false);
		panel_10.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Date de conversion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.setMaximumSize(new Dimension(32767, 60));
		panel_9.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		SimpleDateFormat model2 = new SimpleDateFormat("dd-MMM-yyyy");
		conversionSpin = new JSpinner(new SpinnerDateModel());
		conversionSpin.setEditor(new JSpinner.DateEditor(conversionSpin, model2.toPattern()));
		conversionSpin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_10.add(conversionSpin, BorderLayout.CENTER);
		if(p != null && p.getMinistere().getDateConversion() != null)
		{
			conversionSpin.getModel().setValue(DateUtils.toDate(p.getMinistere().getDateConversion()));
		}
		
		Component verticalStrut_4_2 = Box.createVerticalStrut(20);
		verticalStrut_4_2.setMaximumSize(new Dimension(32767, 10));
		panel_9.add(verticalStrut_4_2);
		
		JPanel panel_10_1 = new JPanel();
		panel_10_1.setOpaque(false);
		panel_10_1.setMaximumSize(new Dimension(32767, 60));
		panel_10_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Date de bapt\u00EAme", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9.add(panel_10_1);
		panel_10_1.setLayout(new BorderLayout(0, 0));
		
		SimpleDateFormat model3 = new SimpleDateFormat("dd-MMM-yyyy");
		baptemeSpin = new JSpinner(new SpinnerDateModel());
		baptemeSpin.setEditor(new JSpinner.DateEditor(baptemeSpin, model3.toPattern()));
		baptemeSpin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_10_1.add(baptemeSpin, BorderLayout.CENTER);
		if(p != null && p.getMinistere().getDateBapteme() != null)
		{
			baptemeSpin.getModel().setValue(DateUtils.toDate(p.getMinistere().getDateBapteme()));
		}
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		verticalStrut_4.setMaximumSize(new Dimension(32767, 10));
		panel_9.add(verticalStrut_4);
		
		JPanel panel_16 = new JPanel();
		panel_16.setOpaque(false);
		panel_16.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Departement", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_16.setMaximumSize(new Dimension(32767, 60));
		panel_9.add(panel_16);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		departementCombo = new JComboBox<>();
		departementCombo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		departementCombo.setModel(new DefaultComboBoxModel<>(Departement.values()));
		panel_16.add(departementCombo);
		if(p != null && p.getMinistere().getDepartement() != null)
		{
			departementCombo.getModel().setSelectedItem(p.getMinistere().getDepartement());
		}
		
		Component verticalStrut_4_1 = Box.createVerticalStrut(20);
		verticalStrut_4_1.setMaximumSize(new Dimension(32767, 10));
		panel_9.add(verticalStrut_4_1);
		
		responsabiliteField = new JTextField();
		responsabiliteField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		responsabiliteField.setMaximumSize(new Dimension(2147483647, 50));
		responsabiliteField.setColumns(10);
		responsabiliteField.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Responsabilit\u00E9", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9.add(responsabiliteField);
		if(p != null && !p.getMinistere().getResponsabilite().isEmpty()) {responsabiliteField.setText(p.getMinistere().getResponsabilite());}
		
		Component verticalStrut_4_1_2 = Box.createVerticalStrut(20);
		verticalStrut_4_1_2.setMaximumSize(new Dimension(32767, 10));
		panel_9.add(verticalStrut_4_1_2);
		
		JPanel panel_11 = new JPanel();
		panel_11.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_11.setOpaque(false);
		panel_11.setMaximumSize(new Dimension(32767, 60));
		panel_11.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Section", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.add(panel_11);
		panel_11.setLayout(new BorderLayout(0, 0));
		
		sectionCombo = new JComboBox<>();
		sectionCombo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sectionCombo.setModel(new DefaultComboBoxModel<>(Eglise.values()));
		panel_11.add(sectionCombo, BorderLayout.CENTER);
		if(p != null && p.getMinistere().getSection() != null)
		{
			sectionCombo.getModel().setSelectedItem(p.getMinistere().getSection());
		}
		
		Component verticalStrut_4_1_1_1_1 = Box.createVerticalStrut(20);
		verticalStrut_4_1_1_1_1.setMaximumSize(new Dimension(32767, 10));
		panel_9.add(verticalStrut_4_1_1_1_1);
		
		JPanel panel_11_1 = new JPanel();
		panel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_11_1.setOpaque(false);
		panel_11_1.setMaximumSize(new Dimension(32767, 60));
		panel_11_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Secteur d'activit\u00E9", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9.add(panel_11_1);
		panel_11_1.setLayout(new BorderLayout(0, 0));
		
		secteurCombo = new JComboBox<>();
		secteurCombo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		secteurCombo.setModel(new DefaultComboBoxModel<>(Secteur.values()));
		panel_11_1.add(secteurCombo, BorderLayout.CENTER);
		if(p != null && p.getProfession().getSecteur() != null)
		{
			secteurCombo.getModel().setSelectedItem(p.getProfession().getSecteur());
		}
		
		Component verticalStrut_4_1_1_1 = Box.createVerticalStrut(20);
		verticalStrut_4_1_1_1.setMaximumSize(new Dimension(32767, 10));
		panel_9.add(verticalStrut_4_1_1_1);
		
		professionField = new JTextField();
		professionField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		professionField.setMaximumSize(new Dimension(2147483647, 50));
		professionField.setColumns(10);
		professionField.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Profession", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9.add(professionField);
		if(p != null && !p.getProfession().getProfession().isEmpty()) {professionField.setText(p.getProfession().getProfession());}
		
		Component verticalStrut_4_1_1 = Box.createVerticalStrut(20);
		verticalStrut_4_1_1.setMaximumSize(new Dimension(32767, 10));
		panel_9.add(verticalStrut_4_1_1);
		
		structureField = new JTextField();
		structureField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		structureField.setMaximumSize(new Dimension(2147483647, 50));
		structureField.setColumns(10);
		structureField.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Structure", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_9.add(structureField);
		panel_8.setLayout(gl_panel_8);
		if(p != null && !p.getProfession().getStructure().isEmpty()) {structureField.setText(p.getProfession().getStructure());}

	}
	
	private boolean isValidForm()
	{
		if(this.codeField.getText().isEmpty() )
			return false;
		
		if(this.nomField.getText().isEmpty() )
			return false;
		
		if(this.prenomsField.getText().isEmpty() )
			return false;
		
		return true;
	}

	@Override
	public void addObservers(Observer obs) {
		// TODO Auto-generated method stub
		if(obs != null)
			this.obs.add(obs);
	}

	@Override
	public void delObserver(Observer obs) {
		// TODO Auto-generated method stub
		if(obs != null)
			this.obs.remove(obs);
	}

	@Override
	public void updateObserverForAdding(Personne p) {
		// TODO Auto-generated method stub
		obs.forEach(o -> o.updateAdding(p));
	}

	@Override
	public void updateObserverForUpdating(Personne p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateObserverForDeleting(Personne p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateObserverForScreen() {
		// TODO Auto-generated method stub
		obs.forEach(o -> o.updateScreen());
	}
	
}
