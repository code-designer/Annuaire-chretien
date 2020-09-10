package org.epbomi.personne.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;
import org.epbomi.personne.controleur.Controleur;
import org.epbomi.personne.model.ModelData;

public class Frame extends JFrame{

	private static final long serialVersionUID = 2502531345501866471L;
	private JPanel container = new JPanel();
	private ModelData model;
	@SuppressWarnings("unused")
	private DataView dataView = new DataView();
	
	//Controleur
	private Controleur controleur;
	private HomeView homeView;
	
	/**
	 * Items pour le menu
	 */
	private JMenuBar menuBar = new JMenuBar();
	private JMenu fichier = new JMenu("Fichier"),
			edition = new JMenu("Edition"),
			aide = new JMenu("Aide");
	private JMenuItem add = new JMenuItem("Ajouter"),
			print = new JMenuItem("Imprimer"),
			export = new JMenuItem("Exporter"),
			exit = new JMenuItem("Quitter"),
			modify = new JMenuItem("Modifier"),
			delete = new JMenuItem("Supprimer"),
			search = new JMenuItem("Rechercher");
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public Frame(ModelData model)
	{
		this.model = model;
		controleur = new Controleur(model);
		homeView = new HomeView(controleur);
		
		//Titre de la fenetre
		this.setTitle("Annuaire");
		//ouverture en plein ecran
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//decoration du JFrame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon(this.getClass().getResource("/addressbook.png")).getImage());
		
		initMenu();
		
		this.container.setLayout(new BorderLayout());
		this.model.addObservers(homeView);
		this.container.add(homeView, BorderLayout.CENTER);
		this.setContentPane(this.container);
		
		//utilisation du look and feel du systeme
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
			this.pack();
		} catch (ClassNotFoundException | InstantiationException |IllegalAccessException | 
				UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//this.setVisible(true);
	}
	
	/**
	 * Initialisation du menu
	 */
	private void initMenu()
	{
		this.add.setIcon(new ImageIcon(this.getClass().getResource("/user-add-16.png")));
		this.add.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
		this.add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				InputView inputView = new InputView(controleur, null);
				inputView.addObservers(homeView);
				homeView.setPanel(inputView);
			}
			
		});
		this.fichier.add(add);
		
		this.print.setIcon(new ImageIcon(this.getClass().getResource("/print-icon-16.png")));
		this.print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK));
		this.print.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					RechercheView dialog = new RechercheView(model);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception evt) {
					evt.printStackTrace();
				}
			}
		
		});
		this.fichier.add(print);
		this.fichier.addSeparator();
		
		/**
		 * Exportation de la base de données
		 */
		this.export.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				saveFile();
			}
			
		});
		this.fichier.add(export);
		
		this.exit.setIcon(new ImageIcon(this.getClass().getResource("/exit-icon-16.png")));
		this.exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fermerApp();
			}
			
		});
		this.fichier.add(exit);
		
		this.menuBar.add(fichier);
		
		//Menu modifier
		this.modify.setIcon(new ImageIcon(this.getClass().getResource("/modify-icon.png")));
		this.modify.addActionListener(e ->{
			homeView.ajouterPanneauDeModification();
		});
		
		this.edition.add(modify);
		//Menu supprimer
		this.delete.setIcon(new ImageIcon(this.getClass().getResource("/user-delete-16.png")));
		this.delete.addActionListener(e ->{
			homeView.supprimerPersonne();
		});
		this.edition.add(delete);
		
		this.search.setIcon(new ImageIcon(this.getClass().getResource("/search-icon-16.png")));
		this.search.addActionListener(e ->{
			try {
				RechercheView dialog = new RechercheView(model);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception evt) {
				evt.printStackTrace();
			}
		});
		this.edition.add(search);
		
		this.menuBar.add(edition);
		this.menuBar.add(aide);
		this.setJMenuBar(menuBar);
		
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				fermerApp();
			}

			
		});
	}
	
	/**
	 * Fermer l'application
	 */
	private void fermerApp()
	{
		int rep = JOptionPane.showConfirmDialog(null, "Voulez-quittez l'application?", "Quitter", 
				JOptionPane.YES_NO_OPTION);
		if(rep == JOptionPane.YES_OPTION)
		{
			Frame.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			logger.trace("Fermerture de l'application");
			System.exit(0);
		}
		else
		{
			Frame.this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			logger.error("Echec de fermerture");
		}
	}
	
	/**
	 * Enregistrer un fichier
	 */
	private void saveFile()
	{		
		JFileChooser fc = new JFileChooser();
		int val = fc.showSaveDialog(this);
		
		if(val == JFileChooser.APPROVE_OPTION)
		{
			String name = fc.getSelectedFile().getName();
			String path = fc.getSelectedFile().getParentFile().getPath();
			String file = path + "\\" + name + ".xls";
			exportAsXls(new File(file));
		}
	}
	
	//Exporter un fichier sous forme excel
	private void exportAsXls(File file)
	{
		try {
			FileWriter fw = new FileWriter(file);
			
			fw.write("<?xml version = \"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>");
			fw.write("<data-set xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
			
			model.getAll().forEach(x ->{
				try {
					fw.write("<personne >"
								+"<code>"+ x.getCode()+" </code>"
								+"<nom>"+ x.getNom()+" </nom>"
								+"<prenoms>"+ x.getPrenoms()+" </prenoms>"
								+"<sexe>"+ x.getSexe()+" </sexe>"
								+"<date-naissance>"+ x.getDateDeNaissance().format(DateTimeFormatter.ofPattern("dd MM yyyy"))+" </date-naissance>"
								+"<lieu-naissance>"+ x.getLieuDeNaissance()+" </lieu-naissance>"
								+"<departement>"+ x.getMinistere().getDepartement()+" </departement>"
								+"<responsabilite>"+ x.getMinistere().getResponsabilite()+" </responsabilite>"
								+"<date-bapteme>"+ x.getMinistere().getDateBapteme().format(DateTimeFormatter.ofPattern("dd MM yyyy"))+" </date-bapteme>"
							+ "</personne>");
					
					
					
					fw.write("\n");
					logger.trace("Exportation des données de la base de données.");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("Echec d'exportation de données");
				}
			});
			
			fw.write("</data-set>");
			fw.close();
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Echec d'exportation de données");
		}
	}
	
	
}
