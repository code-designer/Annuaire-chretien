package org.epbomi.personne.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import org.epbomi.personne.model.Personne;
import org.epbomi.personne.utils.FileUtils;

public class DataView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6685632616534326724L;
	private JTextField txtNom;
	private JTextField txtPrenoms;
	private JTextField txtSexe;
	private JTextField txtDateDeNaissance;
	private JTextField txtLieuDeNaissance;
	private JTextField txtEthnie;
	private JTextField txtProfession;
	private JTextField txtStructure;
	private JTextField txtSecteur;
	private JTextField txtResponsabilite;
	private JTextField txtDepartement;
	private JTextField txtSection;
	private JTextField txtBapteme;
	private JTextField txtConversion;
	private JTextField textField_5;
	private JTextField txtMarie;
	private JTextField txtPartenaire;
	private JTextField txtDateUnion;
	private JTextField txtNombreEnfants;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField txtCel;
	private JTextField txtTel;
	private JTextField txtUrgence;
	private JTextField txtEmail;
	private JTextArea txtResidence;
	private JTextField txtCode;
	private JTextArea txtDescriptifActivite;
	private JLabel photolab;
	

	/**
	 * Create the panel.
	 */
	public DataView() {
		setPreferredSize(new Dimension(700, 600));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(0, 0, 5, 0, (Color) Color.RED));
		panel.setBackground(new Color(0, 0, 255));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("INFORMATIONS");
		lblNewLabel.setPreferredSize(new Dimension(118, 50));
		lblNewLabel.setBorder(null);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 20));
		panel.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		add(panel_2, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setMinimumSize(new Dimension(200, 10));
		panel_3.setBackground(new Color(250,250,250));
		panel_3.setBorder(new LineBorder(Color.BLUE, 1, true));
		panel_3.setPreferredSize(new Dimension(200, 250));
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setMinimumSize(new Dimension(200, 10));
		panel_3_1.setPreferredSize(new Dimension(200, 250));
		panel_3_1.setBorder(new LineBorder(Color.BLUE, 1, true));
		panel_3_1.setBackground(new Color(250, 250, 250));
		panel_3_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setMinimumSize(new Dimension(200, 10));
		panel_3_2.setPreferredSize(new Dimension(200, 250));
		panel_3_2.setBorder(new LineBorder(Color.BLUE, 1, true));
		panel_3_2.setBackground(new Color(250, 250, 250));
		panel_3_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3_2_1 = new JPanel();
		panel_3_2_1.setMinimumSize(new Dimension(200, 10));
		panel_3_2_1.setPreferredSize(new Dimension(200, 250));
		panel_3_2_1.setBorder(new LineBorder(Color.BLUE, 1, true));
		panel_3_2_1.setBackground(new Color(250, 250, 250));
		panel_3_2_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3_1_1 = new JPanel();
		panel_3_1_1.setPreferredSize(new Dimension(200, 250));
		panel_3_1_1.setBorder(new LineBorder(Color.BLUE, 1, true));
		panel_3_1_1.setBackground(new Color(250, 250, 250));
		panel_3_1_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3_1_1_1 = new JPanel();
		panel_3_1_1_1.setMinimumSize(new Dimension(200, 10));
		panel_3_1_1_1.setPreferredSize(new Dimension(200, 250));
		panel_3_1_1_1.setBorder(new LineBorder(Color.BLUE, 1, true));
		panel_3_1_1_1.setBackground(new Color(250, 250, 250));
		panel_3_1_1_1.setLayout(new BorderLayout(0, 0));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3_2_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_3_1, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3_1_1_1, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
						.addComponent(panel_3_1_1, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
					.addGap(23))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3_1, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
						.addComponent(panel_3_1_1, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3_1_1_1, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
						.addComponent(panel_3_2_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
						.addComponent(panel_3_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE))
					.addGap(49))
		);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.BLUE);
		panel_7.setPreferredSize(new Dimension(10, 30));
		panel_3_1_1_1.add(panel_7, BorderLayout.NORTH);
		
		JLabel lblNewLabel_5 = new JLabel("CONTACTS");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setForeground(Color.WHITE);
		panel_7.add(lblNewLabel_5);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(250, 250, 250));
		panel_3_1_1_1.add(panel_12, BorderLayout.CENTER);
		panel_12.setLayout(new BoxLayout(panel_12, BoxLayout.Y_AXIS));
		
		txtCel = new JTextField();
		txtCel.setToolTipText("Num\u00E9ro de t\u00E9l\u00E9phone");
		txtCel.setOpaque(false);
		txtCel.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCel.setText("C\u00E9l");
		txtCel.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_12.add(txtCel);
		txtCel.setColumns(10);
		
		txtTel = new JTextField();
		txtTel.setToolTipText("Num\u00E9ro de t\u00E9l\u00E9phone");
		txtTel.setOpaque(false);
		txtTel.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTel.setText("C\u00E9l 2");
		txtTel.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_12.add(txtTel);
		txtTel.setColumns(10);
		
		txtUrgence = new JTextField();
		txtUrgence.setToolTipText("Contact en cas d'urgence");
		txtUrgence.setOpaque(false);
		txtUrgence.setFont(new Font("Arial", Font.PLAIN, 16));
		txtUrgence.setText("Urgence");
		txtUrgence.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_12.add(txtUrgence);
		txtUrgence.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setToolTipText("adresse electronique");
		txtEmail.setOpaque(false);
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		txtEmail.setText("email");
		txtEmail.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_12.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtResidence = new JTextArea();
		txtResidence.setPreferredSize(new Dimension(4, 50));
		txtResidence.setToolTipText("lieu de r\u00E9sidence");
		txtResidence.setOpaque(false);
		txtResidence.setFont(new Font("Arial", Font.PLAIN, 16));
		txtResidence.setText("R\u00E9sidence");
		txtResidence.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_12.add(txtResidence);
		txtResidence.setColumns(10);
		
		photolab = new JLabel("");
		photolab.setMinimumSize(new Dimension(200, 0));
		photolab.setBorder(new EmptyBorder(3, 3, 3, 2));
		photolab.setBackground(new Color(250, 250, 250));
		photolab.setHorizontalTextPosition(SwingConstants.CENTER);
		photolab.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3_1_1.add(photolab, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.BLUE);
		panel_6.setPreferredSize(new Dimension(10, 30));
		panel_3_2_1.add(panel_6, BorderLayout.NORTH);
		
		JLabel lblNewLabel_4 = new JLabel("ETAT MATRIMONIAL");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setForeground(Color.WHITE);
		panel_6.add(lblNewLabel_4);
		
		JPanel panel_11 = new JPanel();
		panel_11.setOpaque(false);
		panel_11.setBackground(new Color(250, 250, 250));
		panel_3_2_1.add(panel_11, BorderLayout.CENTER);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.Y_AXIS));
		
		txtMarie = new JTextField();
		txtMarie.setToolTipText("Situation matrimoniale");
		txtMarie.setOpaque(false);
		txtMarie.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMarie.setText("Mari\u00E9");
		txtMarie.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_11.add(txtMarie);
		txtMarie.setColumns(10);
		
		txtPartenaire = new JTextField();
		txtPartenaire.setToolTipText("Conjoint");
		txtPartenaire.setOpaque(false);
		txtPartenaire.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPartenaire.setText("Partenaire");
		txtPartenaire.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_11.add(txtPartenaire);
		txtPartenaire.setColumns(10);
		
		txtDateUnion = new JTextField();
		txtDateUnion.setToolTipText("Date d'union (dot ou mariage)");
		txtDateUnion.setOpaque(false);
		txtDateUnion.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDateUnion.setText("date union");
		txtDateUnion.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_11.add(txtDateUnion);
		txtDateUnion.setColumns(10);
		
		txtNombreEnfants = new JTextField();
		txtNombreEnfants.setToolTipText("Nombre d'enfant");
		txtNombreEnfants.setOpaque(false);
		txtNombreEnfants.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombreEnfants.setText("nombre enfants");
		txtNombreEnfants.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_11.add(txtNombreEnfants);
		txtNombreEnfants.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setOpaque(false);
		textField_10.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_10.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_11.add(textField_10);
		textField_10.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setOpaque(false);
		textField_11.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_11.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_11.add(textField_11);
		textField_11.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.BLUE);
		panel_5.setPreferredSize(new Dimension(10, 30));
		panel_3_2.add(panel_5, BorderLayout.NORTH);
		
		JLabel lblNewLabel_3 = new JLabel("MINISTERE");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setForeground(Color.WHITE);
		panel_5.add(lblNewLabel_3);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(250, 250, 250));
		panel_3_2.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.Y_AXIS));
		
		txtResponsabilite = new JTextField();
		txtResponsabilite.setToolTipText("Responsabilit\u00E9");
		txtResponsabilite.setOpaque(false);
		txtResponsabilite.setFont(new Font("Arial", Font.PLAIN, 16));
		txtResponsabilite.setText("Responsabilit\u00E9");
		txtResponsabilite.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_10.add(txtResponsabilite);
		txtResponsabilite.setColumns(10);
		
		txtDepartement = new JTextField();
		txtDepartement.setToolTipText("Departement");
		txtDepartement.setOpaque(false);
		txtDepartement.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDepartement.setText("Departement");
		txtDepartement.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_10.add(txtDepartement);
		txtDepartement.setColumns(10);
		
		txtSection = new JTextField();
		txtSection.setToolTipText("Section");
		txtSection.setOpaque(false);
		txtSection.setFont(new Font("Arial", Font.PLAIN, 16));
		txtSection.setText("Section");
		txtSection.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_10.add(txtSection);
		txtSection.setColumns(10);
		
		txtBapteme = new JTextField();
		txtBapteme.setToolTipText("Date de bapt\u00EAme");
		txtBapteme.setOpaque(false);
		txtBapteme.setFont(new Font("Arial", Font.PLAIN, 16));
		txtBapteme.setText("Bapt\u00EAme");
		txtBapteme.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_10.add(txtBapteme);
		txtBapteme.setColumns(10);
		
		txtConversion = new JTextField();
		txtConversion.setToolTipText("Date de conversion");
		txtConversion.setOpaque(false);
		txtConversion.setFont(new Font("Arial", Font.PLAIN, 16));
		txtConversion.setText("Conversion");
		txtConversion.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_10.add(txtConversion);
		txtConversion.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setOpaque(false);
		textField_5.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_5.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_10.add(textField_5);
		textField_5.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.BLUE);
		panel_4.setPreferredSize(new Dimension(10, 30));
		panel_3_1.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_2 = new JLabel("PROFESSION");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_2);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(250,250,250));
		panel_3_1.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.Y_AXIS));
		
		txtProfession = new JTextField();
		txtProfession.setToolTipText("Profession");
		txtProfession.setOpaque(false);
		txtProfession.setFont(new Font("Arial", Font.PLAIN, 16));
		txtProfession.setBorder(new EmptyBorder(0, 10, 0, 0));
		txtProfession.setText("Profession");
		panel_9.add(txtProfession);
		txtProfession.setColumns(10);
		
		txtStructure = new JTextField();
		txtStructure.setToolTipText("Structure");
		txtStructure.setOpaque(false);
		txtStructure.setFont(new Font("Arial", Font.PLAIN, 16));
		txtStructure.setBorder(new EmptyBorder(0, 10, 0, 0));
		txtStructure.setText("Structure");
		panel_9.add(txtStructure);
		txtStructure.setColumns(10);
		
		txtSecteur = new JTextField();
		txtSecteur.setToolTipText("Secteur");
		txtSecteur.setOpaque(false);
		txtSecteur.setFont(new Font("Arial", Font.PLAIN, 16));
		txtSecteur.setBorder(new EmptyBorder(0, 10, 0, 0));
		txtSecteur.setText("Secteur");
		panel_9.add(txtSecteur);
		txtSecteur.setColumns(10);
		
		txtCode = new JTextField();
		txtCode.setToolTipText("Code");
		txtCode.setOpaque(false);
		txtCode.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCode.setBorder(new EmptyBorder(0, 10, 0, 0));
		txtCode.setText("Code");
		panel_9.add(txtCode);
		txtCode.setColumns(10);
		
		txtDescriptifActivite = new JTextArea();
		txtDescriptifActivite.setToolTipText("Descriptif activit\u00E9");
		txtDescriptifActivite.setOpaque(false);
		txtDescriptifActivite.setBorder(new EmptyBorder(0, 10, 0, 0));
		txtDescriptifActivite.setPreferredSize(new Dimension(4, 50));
		txtDescriptifActivite.setRows(3);
		txtDescriptifActivite.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDescriptifActivite.setText("Descriptif activit\u00E9");
		panel_9.add(txtDescriptifActivite);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLUE);
		panel_1.setPreferredSize(new Dimension(10, 30));
		panel_3.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel_1 = new JLabel("IDENTITE");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel_8 = new JPanel();
		panel_8.setOpaque(false);
		panel_3.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.Y_AXIS));
		
		txtNom = new JTextField();
		txtNom.setToolTipText("Nom");
		txtNom.setOpaque(false);
		txtNom.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNom.setBorder(new EmptyBorder(0, 10, 0, 0));
		txtNom.setText("Nom");
		panel_8.add(txtNom);
		txtNom.setColumns(10);
		
		txtPrenoms = new JTextField();
		txtPrenoms.setToolTipText("Pr\u00E9noms");
		txtPrenoms.setOpaque(false);
		txtPrenoms.setFont(new Font("Arial", Font.PLAIN, 16));
		txtPrenoms.setBorder(new EmptyBorder(0, 10, 0, 0));
		txtPrenoms.setText("Pr\u00E9noms");
		panel_8.add(txtPrenoms);
		txtPrenoms.setColumns(10);
		
		txtSexe = new JTextField();
		txtSexe.setToolTipText("Sexe");
		txtSexe.setOpaque(false);
		txtSexe.setFont(new Font("Arial", Font.PLAIN, 16));
		txtSexe.setBorder(new EmptyBorder(0, 10, 0, 0));
		txtSexe.setText("Sexe");
		panel_8.add(txtSexe);
		txtSexe.setColumns(10);
		
		txtDateDeNaissance = new JTextField();
		txtDateDeNaissance.setToolTipText("Date de naissance");
		txtDateDeNaissance.setOpaque(false);
		txtDateDeNaissance.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDateDeNaissance.setBorder(new EmptyBorder(0, 10, 0, 0));
		txtDateDeNaissance.setText("Date de naissance");
		panel_8.add(txtDateDeNaissance);
		txtDateDeNaissance.setColumns(10);
		
		txtLieuDeNaissance = new JTextField();
		txtLieuDeNaissance.setToolTipText("Lieu de naissance");
		txtLieuDeNaissance.setOpaque(false);
		txtLieuDeNaissance.setFont(new Font("Arial", Font.PLAIN, 16));
		txtLieuDeNaissance.setBorder(new EmptyBorder(0, 10, 0, 0));
		txtLieuDeNaissance.setText("Lieu de naissance");
		panel_8.add(txtLieuDeNaissance);
		txtLieuDeNaissance.setColumns(10);
		
		txtEthnie = new JTextField();
		txtEthnie.setToolTipText("Ethnie");
		txtEthnie.setOpaque(false);
		txtEthnie.setFont(new Font("Arial", Font.PLAIN, 16));
		txtEthnie.setBorder(new EmptyBorder(0, 10, 0, 0));
		txtEthnie.setText("Ethnie");
		panel_8.add(txtEthnie);
		txtEthnie.setColumns(10);
		panel_2.setLayout(gl_panel_2);

	}
	
	/**
	 * Hydrater le formulaire d'affichage
	 * @param p Personne
	 */
	public void setPersonne(Personne p)
	{
		this.txtCode.setText(p.getCode());
		this.txtNom.setText(p.getNom());
		this.txtPrenoms.setText(p.getPrenoms());
		this.txtSexe.setText(p.getSexe().toString());
		this.txtDateDeNaissance.setText(p.getDateDeNaissance().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
		this.txtLieuDeNaissance.setText(p.getLieuDeNaissance());
		this.txtEthnie.setText(p.getEthnie().toString());
		//
		this.txtProfession.setText(p.getProfession().getProfession());
		this.txtStructure.setText(p.getProfession().getStructure());
		this.txtSecteur.setText(p.getProfession().getSecteur().toString());
		this.txtDescriptifActivite.setText(p.getProfession().getDescriptifActivite());
		
		this.txtResponsabilite.setText(p.getMinistere().getResponsabilite());
		this.txtDepartement.setText(p.getMinistere().getDepartement().toString());
		if(p.getMinistere().getDateConversion() != null)
			this.txtConversion.setText(p.getMinistere().getDateConversion().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		else
			this.txtConversion.setText("Inconnu");
		
		if(p.getMinistere().getDateBapteme() != null)
			this.txtBapteme.setText(p.getMinistere().getDateBapteme().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		else
			this.txtBapteme.setText("Non baptisé");
		
		this.txtSection.setText(p.getMinistere().getSection().toString());
		
		this.txtMarie.setText(p.getVieConjugale().getSituation().toString());
		this.txtPartenaire.setText(p.getVieConjugale().getConjoint());
		if(p.getVieConjugale().getDateUnion() != null)
			this.txtDateUnion.setText(p.getVieConjugale().getDateUnion().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		else
			this.txtDateUnion.setText("NEANT");
		this.txtNombreEnfants.setText(String.valueOf(p.getVieConjugale().getNombreEnfants()));
		
		this.txtCel.setText(p.getContact().getCel());
		this.txtTel.setText(p.getContact().getTel());
		this.txtUrgence.setText(p.getContact().getUrgence());
		this.txtEmail.setText(p.getContact().getEmail());
		this.txtResidence.setText(p.getContact().getResidence());
		
		if(p.getPathImg() != null)
			this.photolab.setIcon(new ImageIcon(FileUtils.resizeImage(p.getPathImg().toString(), 200)));
		else
			this.photolab.setIcon(new ImageIcon(FileUtils.resizeImage("Images/unknown.png", 200)));
	}
}
