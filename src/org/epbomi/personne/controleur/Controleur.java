package org.epbomi.personne.controleur;

import java.util.Set;

import org.epbomi.personne.model.ModelData;
import org.epbomi.personne.model.Personne;
import org.epbomi.personne.model.Statut;

public class Controleur extends AbstractControleur {
	
	/**
	 * ModelData contient les données de notre base de données
	 */
	private ModelData model;
	
	/**
	 * Constructeur
	 * @param model
	 */
	public Controleur(ModelData model) 
	{
		super();
		this.model = model;
	}
	
	/**
	 * Enregistre ou mettre à jour les données d'une personne dans la base de données.
	 * si id = 0, on fait un enregistrement sinon un mis à jour.
	 * @param p, de type Personne
	 * @return boolean
	 */
	public boolean save(Personne p)
	{
		controle(p);
		return this.model.savePersonne(p);
	}
	
	/**
	 * Supprimer les données d'une personne de la base de données
	 * @param p, de type Personne
	 */
	public void del(Personne p)
	{
		this.model.deletePersonne(p);
	}
	
	/**
	 * Retourne les informations relatives au filtre donné en parametre
	 * @param filter pour critère de filtration
	 * @return Set<Personne> liste de personnes
	 */
	public Set<Personne> search(String filter)
	{
		return model.findPersonne(filter);
	}
	
	/**
	 * Retourne toutes les informations contenues dans la base de données.
	 * @return Set<Personne> liste de personnes
	 */
	public Set<Personne> getAll()
	{
		return this.model.getAll();
	}
	
	/**
	 * Opere certaines verification sur une personne
	 * @param p
	 */
	private void controle(Personne p)
	{
		if(!p.getVieConjugale().getSituation().equals(Statut.FIANCE) && 
				!p.getVieConjugale().getSituation().equals(Statut.MARIE))
			p.getVieConjugale().setDateUnion(null);
		
		if(p.getMinistere().getResponsabilite().trim().toUpperCase().equals("NON BAPTISE") ||
				p.getMinistere().getResponsabilite().trim().toUpperCase().equals("NOUVEAU CHRETIEN"))
			p.getMinistere().setDateBapteme(null);
		
		if(p.getProfession().getProfession().isEmpty())
			p.getProfession().setProfession("NEANT");
		else
			p.getProfession().getProfession().toUpperCase();
		
		if(p.getProfession().getStructure().isEmpty())
			p.getProfession().setStructure("NEANT");
		else
			p.getProfession().getStructure().toUpperCase();
		
		if(p.getProfession().getDescriptifActivite().isEmpty())
			p.getProfession().setDescriptifActivite("NEANT");
		else
			p.getProfession().getDescriptifActivite().toUpperCase();
		
		if(p.getMinistere().getResponsabilite().isEmpty())
			p.getMinistere().setResponsabilite("NEANT");
		else
			p.getMinistere().getResponsabilite().toUpperCase();
		
		if(p.getVieConjugale().getConjoint().isEmpty())
			p.getVieConjugale().setConjoint("NEANT");
		else
			p.getVieConjugale().getConjoint().toUpperCase();
		
		if(p.getLieuDeNaissance().isEmpty())
			p.setLieuDeNaissance("NEANT");
		else
			p.getLieuDeNaissance().toUpperCase();
		
		if(p.getContact().getCel().isEmpty())
			p.getVieConjugale().setConjoint("00 00 00 00");
		
		if(p.getContact().getTel().isEmpty())
			p.getVieConjugale().setConjoint("00 00 00 00");
		
		if(p.getContact().getUrgence().isEmpty())
			p.getContact().setUrgence("00 00 00 00");
	}
	
}
