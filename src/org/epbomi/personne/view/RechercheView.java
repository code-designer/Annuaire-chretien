package org.epbomi.personne.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.log4j.Logger;
import org.epbomi.personne.model.Departement;
import org.epbomi.personne.model.Eglise;
import org.epbomi.personne.model.Ethnie;
import org.epbomi.personne.model.ModelData;
import org.epbomi.personne.model.Personne;
import org.epbomi.personne.model.Secteur;
import org.epbomi.personne.model.Sexe;
import org.epbomi.personne.model.Statut;
import org.epbomi.personne.utils.Impression;

public class RechercheView extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7684600931515872018L;
	private DefaultTableModel model = new DefaultTableModel();
	private JTable table = new JTable(model);
	private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(model);
	private JComboBox<Sexe> sexCombo;
	private JComboBox<Departement> departementCombo;
	private JComboBox<Eglise> sectionCombo;
	private JComboBox<Secteur> secteurCombo;
	private JComboBox<Statut> statutCombo;
	
	private Set<Personne> source = null;
	private RowFilter<TableModel, Object> rf ;
	private List<RowFilter<TableModel, Object>> rfs;
	
	private RowFilter<TableModel, Object> sexFilter = null, statutFilter = null, departementFilter = null,
				activiteFilter = null, sectionFilter = null, ethnieFilter = null;
	private JComboBox<Ethnie> ethnieCombo;
	private Logger logger = Logger.getLogger(this.getClass());
	
	
	/**
	 * Create the dialog.
	 */
	public RechercheView(ModelData modeldata) {
		this.source = modeldata.getAll();
		setBounds(100, 100, 1000, 500);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton printButton = new JButton("Imprimer");
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Impression printer = new Impression(table);
				printer.printTable(new MessageFormat("LISTE GENEREE DE "+table.getRowCount()+ " PERSONNE(S)"), 
						new MessageFormat(" Eglise Annexe Angré 2 * P-{0}"));
			}
		});
		panel.add(printButton);
		
		JButton cancelButton = new JButton("Annuler");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RechercheView.this.setVisible(false);
			}
		});
		panel.add(cancelButton);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(225, 10));
		panel_1.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Sexe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setMaximumSize(new Dimension(32767, 60));
		panel_2.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		
		sexCombo = new JComboBox<>();
		sexCombo.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent e) {
				
				Sexe sex = (Sexe)e.getItem();

				
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					if(!sex.equals(Sexe.INCONNU))
					{
						sexFilter = RowFilter.regexFilter("^"+Pattern.quote(sex.toString())+"$", 1);
					}
					else
					{
						sexFilter = null;
					}
				}
				addFilter();
			}
		});
		sexCombo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sexCombo.setModel(new DefaultComboBoxModel<>(Sexe.values()));
		panel_3.add(sexCombo, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Departement", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setMaximumSize(new Dimension(32767, 60));
		panel_2.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		departementCombo = new JComboBox<>();
		departementCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//int j = -1;
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					Departement depart = (Departement)e.getItem();
					if(!depart.equals(Departement.INCONNU))
					{
						departementFilter = RowFilter.regexFilter("^" + Pattern.quote(depart.toString()) + "$",2);
					}
					else
					{
						departementFilter = null;
					}
					addFilter();
				}
			}
		});
		departementCombo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		departementCombo.setModel(new DefaultComboBoxModel<>(Departement.values()));
		panel_4.add(departementCombo, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Section", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setMaximumSize(new Dimension(32767, 60));
		panel_2.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		sectionCombo = new JComboBox<>();
		sectionCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					Eglise section = (Eglise)e.getItem();
					if(!section.equals(Eglise.INCONNU))
					{
						sectionFilter =  RowFilter.regexFilter("^" + Pattern.quote(section.toString()) + "$",5);
					}
					else
					{
						sectionFilter = null;
					}
					addFilter();
				}
			}
		});
		sectionCombo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sectionCombo.setModel(new DefaultComboBoxModel<>(Eglise.values()));
		panel_5.add(sectionCombo, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Secteur d'activit\u00E9", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setMaximumSize(new Dimension(32767, 60));
		panel_2.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		secteurCombo = new JComboBox<>();
		secteurCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					Secteur secteur = (Secteur)e.getItem();
					if(!secteur.equals(Secteur.INCONNU))
					{
						activiteFilter = RowFilter.regexFilter("^" + Pattern.quote(secteur.toString()) + "$",6);
					}
					else
					{
						activiteFilter = null;
					}
					addFilter();
				}
			}
		});
		secteurCombo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		secteurCombo.setModel(new DefaultComboBoxModel<>(Secteur.values()));
		panel_6.add(secteurCombo, BorderLayout.CENTER);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Statut", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setMaximumSize(new Dimension(32767, 60));
		panel_2.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		statutCombo = new JComboBox<>();
		statutCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					Statut statut = (Statut)e.getItem();
					if(!statut.equals(Statut.INCONNU))
					{
						statutFilter = RowFilter.regexFilter("^" + Pattern.quote(statut.toString()) +"$" ,3);
					}
					else
					{
						statutFilter = null;
					}
					addFilter();
				}
			}
		});
		statutCombo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		statutCombo.setModel(new DefaultComboBoxModel<>(Statut.values()));
		panel_7.add(statutCombo, BorderLayout.CENTER);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Ethnie", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setMaximumSize(new Dimension(32767, 60));
		panel_2.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		ethnieCombo = new JComboBox<>();
		ethnieCombo.addItemListener(new ItemListener() {
			

			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					Ethnie ethnie = (Ethnie)e.getItem();
					if(!ethnie.equals(Ethnie.INCONNU))
					{
						ethnieFilter = RowFilter.regexFilter("^" + Pattern.quote(ethnie.toString()) +"$" ,7);
					}
					else
					{
						ethnieFilter = null;
					}
					addFilter();
				}
			}
		});
		ethnieCombo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ethnieCombo.setModel(new DefaultComboBoxModel<>(Ethnie.values()));
		panel_9.add(ethnieCombo, BorderLayout.CENTER);
		
		JPanel panel_8 = new JPanel();
		panel_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_8.setForeground(new Color(0, 0, 0));
		panel_8.setBackground(new Color(0, 0, 255));
		panel_8.setBorder(new MatteBorder(0, 0, 5, 0, (Color) Color.RED));
		panel_1.add(panel_8, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("GENERATION DE LISTE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 5, 0, 5));
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		//table = new JTable();
		//table.setModel(model);
		table.setRowSorter(rowSorter);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		String[] headers = {"Nom & prénoms","Sexe","Departement","Statut","Contact","Section","Activité","Ethnie"};
		model.setColumnIdentifiers(headers);
		
		hydrateModel(source);
		
		table.removeColumn(table.getColumnModel().getColumn(5));
		table.removeColumn(table.getColumnModel().getColumn(5));
		table.removeColumn(table.getColumnModel().getColumn(5));
		table.getColumnModel().getColumn(0).setMinWidth(230);
		table.setRowHeight(25);
		
		logger.trace("Lancement de la recherche");
	}
	
	public void hydrateModel(Set<Personne> data)
	{
		data.forEach(x -> model.addRow(ajouterLigne(x)));
	}
	
	private Object[] ajouterLigne(Personne p)
	{
		return new Object[] {p.toString(), p.getSexe(), p.getMinistere().getDepartement(), 
				p.getVieConjugale().getSituation(),p.getContact().getCel(),p.getMinistere().getSection(),
				p.getProfession().getSecteur(), p.getEthnie()};
	}
	
	private void addFilter()
	{
		rfs = new ArrayList<RowFilter<TableModel, Object>>();
		int i = 0;
		
		if(sexFilter != null)
			rfs.add(i++, sexFilter);
		
		if(statutFilter != null)
			rfs.add(i++, statutFilter);
		
		if(departementFilter != null)
			rfs.add(i++, departementFilter);
		
		if(sectionFilter != null)
			rfs.add(i++, sectionFilter);
		
		if(activiteFilter != null)
			rfs.add(i++, activiteFilter);
		
		if(ethnieFilter != null)
			rfs.add(i++, ethnieFilter);
		
		if(rfs != null)
		{
			rf = RowFilter.andFilter(rfs);
			rowSorter.setRowFilter(rf);
		}
		else
			rowSorter.setRowFilter(null);
	}
	
}
