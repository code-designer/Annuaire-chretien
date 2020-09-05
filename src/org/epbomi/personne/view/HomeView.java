package org.epbomi.personne.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.epbomi.personne.controleur.Controleur;
import org.epbomi.personne.model.Personne;
import org.epbomi.personne.patterns.Observer;
import org.epbomi.personne.utils.Impression;

public class HomeView extends JPanel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3317181058948217486L;

	/**
	 * Conteneur pour afficher la JList et les details
	 */
	private JSplitPane splitPane = new JSplitPane();
	
	/**
	 * txtSearch pour input de recherche
	 */
	private JTextField txtSearch;
	
	/**
	 * searchbtn pour effectuer une recherche
	 */
	private JButton searchbtn;
	
	/**
	 * Panel pour afficher les details d'informations.
	 */
	private DataView dataView = new DataView();
	
	/**
	 * Instance du controleur
	 */
	private Controleur control;
	
	/**
	 * list pour afficher les nom et prenoms de personnes
	 */
	private JList<Personne> list;
	
	/**
	 * listModel pour hydrater la JList
	 */
	private DefaultListModel<Personne> listModel;
	
	/**
	 * sets,liste de personne obtenue du controleur
	 */
	private Set<Personne> sets;
	
	/**
	 * les items du menu et du sous menu
	 */
	JPopupMenu popMenu = new JPopupMenu();
	JMenuItem del = new JMenuItem("Supprimer"),
			modify = new JMenuItem("Modifier"),
			print = new JMenuItem("Imprimer");

	/**
	 * Controleur.
	 */
	public HomeView(Controleur control) {
		this.control = control;
		setPreferredSize(new Dimension(1000, 600));
		setLayout(new BorderLayout(0, 0));
		
		
		add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(300, 10));
		splitPane.setLeftComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		txtSearch = new JTextField();
		
		/**
		 * Ecouteur sur le clavier pour effectuer une recherche à chaque touche enfoncé
		 */
		txtSearch.addKeyListener(new KeyAdapter() {
			DefaultListModel<Personne> mod = new DefaultListModel<>();
			@Override
			public void keyReleased(KeyEvent arg0) {
				//on recupère la liste de personne depuis la JList
				((DefaultListModel<Personne>)list.getModel()).clear();
				
				//on recupère une liste filtré
				  List<Personne> lp = control.search(txtSearch.getText().toUpperCase()).stream().collect(Collectors.toList());
				//on effectue un tri
				  Collections.sort(lp, (p1, p2) -> p1.compareTo(p2));
				 //on hydrate la liste model avec une boucle forEach
				  lp.forEach(x ->{ mod.addElement(x); });
				  //on affiche le modele
				  list.setModel(mod);
				 
			}
		});
		
		txtSearch.setBorder(new EmptyBorder(0, 5, 0, 0));
		
		/**
		 * on definit le comportement de notre champ text de recherche.
		 * Lorsque le champ texte à le focus (focusedGain), s'il contient search... on l'efface autrement on selection le text 
		 * à l'interieur.
		 * lors de la perte du focus (focusLost), si le champ texte est vide on affiche à nouveau search...
		 */
		txtSearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				if(((JTextField)arg0.getSource()).getText().equals("Search..."))
				{
					((JTextField)arg0.getSource()).setFont(new Font("Arial", Font.PLAIN, 16));
					((JTextField)arg0.getSource()).setText("");
				}
				else
				{
					((JTextField)arg0.getSource()).selectAll();
				}
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				if(((JTextField)arg0.getSource()).getText().equals(""))
				{
					((JTextField)arg0.getSource()).setFont(new Font("Arial", Font.ITALIC, 16));
					((JTextField)arg0.getSource()).setText("Search...");
				}
			}
		});
		txtSearch.setFont(new Font("Arial", Font.ITALIC, 16));
		txtSearch.setText("Search...");
		panel_1.add(txtSearch, BorderLayout.CENTER);
		txtSearch.setColumns(10);
		/**
		 * on definit le bouton de recherche
		 */
		searchbtn = new JButton("");
		searchbtn.setIcon(new ImageIcon(HomeView.class.getResource("/user-search-icon.png")));
		searchbtn.setFocusPainted(false);
		searchbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		panel_1.add(searchbtn, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		list = new JList<>();
		list.setFont(new Font("Tahoma", Font.PLAIN, 16));
		listModel = new DefaultListModel<>();
		//on recupère les données depuis le controleur
		sets = control.getAll();
		// On hydrate le model de notre JList pour l'afficher
		sets.forEach(x -> listModel.addElement(x));
		list.setModel(listModel);
		
		//On selectionne le premier element de la Jlist qu'on affiche si celle-ci n'est pas vide
		  if(listModel.size() > 0) 
		  {
			  list.setSelectedIndex(0);
			  dataView.setPersonne(list.getSelectedValue());
		  }
		 
		/**
		 * On affiche les details d'une ligne de la JList lorsqu'elle est selectionné
		 */
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (e.getValueIsAdjusting() == false)
				{
					if(list.getSelectedIndex() > -1)
						dataView.setPersonne(list.getSelectedValue());
				}
			}
			
		});
		
		//on affiche la JList dans un scrollPane. cela permet d'afficher l'entete du tableau et un meilleur
		//visibilité du tableau entier
		scrollPane.setViewportView(list);
		
		//Affiche le panel de details des informations dans le splitPanel
		splitPane.setRightComponent(dataView);
		
		//definir une largeur pour le panel de gauche
		splitPane.setDividerLocation(250);
		
		//Ouvre le panneau pour les modifications des informations
		modify.setIcon(new ImageIcon(this.getClass().getResource("/modify-icon.png")));
		modify.addActionListener( e ->{
			splitPane.setRightComponent(new InputView(control, list.getSelectedValue()));
		});
		
		//Supprime une ligne d'information
		del.setIcon(new ImageIcon(this.getClass().getResource("/user-delete-16.png")));
		del.addActionListener(e ->{
			supprimerPersonne();
			
		});
		
		//Imprime toutes les informations de la BD
		print.setIcon(new ImageIcon(this.getClass().getResource("/print-icon-16.png")));
		print.addActionListener(e ->{
			if(list.getSelectedIndex() > -1)
			{
				Impression imp = new Impression(list.getSelectedValue());
				imp.print();
			}
		});
		
		//Definir le menu contextuel
		list.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent event) {
				// TODO Auto-generated method stub
				if(event.isPopupTrigger())
				{
					popMenu.add(modify);
					popMenu.add(del);
					popMenu.add(print);
					popMenu.show(list, event.getX(), event.getY());
				}
			}
			
		});
	}
	
	//Ajoute un panneau à la droite du splitPanel
	public void setPanel(JPanel panel)
	{
		this.splitPane.setRightComponent(panel);
	}
	
	//Ouvre un panneau de modification à la droite du splitPanel
	public void ajouterPanneauDeModification()
	{
		if(list.getSelectedIndex() > -1)
			splitPane.setRightComponent(new InputView(control, list.getSelectedValue()));
		else
			JOptionPane.showMessageDialog(null, "Veuillez selectionner une personne!");
	}
	
	/**
	 * Supprimer une personne
	 */
	public void supprimerPersonne()
	{
		if(list.getSelectedIndex() > -1)
		{
			int rep = JOptionPane.showConfirmDialog(null, "Etes-vous de vouloir supprimer " +
					list.getSelectedValue().toString() +" ?", "Suppression", JOptionPane.OK_CANCEL_OPTION);
			if(rep == JOptionPane.OK_OPTION)
			{
				if(!list.getSelectedValue().getPathImg().equals(Paths.get("Images/user_default.png")))
					list.getSelectedValue().getPathImg().toFile().delete();
				control.del(list.getSelectedValue());
			}
		}
	}

	
	/**
	 * Mettre à jour la JListe
	 */
	public void update()
	{
		DefaultListModel<Personne> mod = new DefaultListModel<Personne>();
		List<Personne> lp = new ArrayList<>();
		for (int i = 0; i < listModel.getSize(); i++)
		  	lp.add(listModel.get(i));
		  //Trier la JList
		  Collections.sort(lp, (p1, p2) -> p1.compareTo(p2));
		  lp.forEach(x ->{ mod.addElement(x); });
		  
		  listModel = mod;
		  list.setModel(listModel);
	}
	
	//Pattern observer

	@Override
	public void updateAdding(Personne p) {
		// TODO Auto-generated method stub
		listModel.addElement(p);
		update();
	}

	@Override
	public void updateUpdating(Personne p) {
		// TODO Auto-generated method stub
		for(int i = 0; i < listModel.getSize(); i++)
		{
			if(listModel.get(i).getPersonneID() == p.getPersonneID())
			{
				listModel.remove(i);
				listModel.add(i, p);
				break;
			}
		}
		update();
	}

	@Override
	public void updateDeleting(Personne p) {
		// TODO Auto-generated method stub
		listModel.removeElement(p);
		update();
	}

	@Override
	public void updateScreen() {
		// TODO Auto-generated method stub
		splitPane.setRightComponent(dataView);
		if(list.getSelectedIndex() > 0)
			dataView.setPersonne(list.getSelectedValue());
	}
	
}
