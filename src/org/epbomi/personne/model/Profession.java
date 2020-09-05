package org.epbomi.personne.model;

public class Profession extends AbstractModel{
	private int professionID;
	private Secteur secteur;
	private String profession;
	private String structure;
	private String descriptifActivite;
	
	public Profession() {
		professionID = 0;
		secteur = Secteur.INCONNU;
		profession = "AUCUNE";
		structure = "NEANT";
		descriptifActivite = "NEANT";
	}
	
	public Profession(int professionID, Secteur secteur, String profession, String structure, String descriptifActivite)
	{
		this.professionID = professionID;
		this.secteur = secteur;
		this.profession = profession;
		this.structure = structure;
		this.descriptifActivite = descriptifActivite;
	}
	
	public int getProfessionID()
	{
		return professionID;
	}
	
	public Secteur getSecteur() {
		return secteur;
	}

	public String getProfession() {
		return this.profession.isEmpty() ? this.profession : this.profession.toUpperCase();
	}

	public String getStructure() {
		return this.structure.isEmpty() ? this.structure : this.structure.toUpperCase();
	}

	public String getDescriptifActivite() {
		return this.descriptifActivite.isEmpty() ? this.descriptifActivite : this.descriptifActivite.toUpperCase();
	}
	
	public void setProfessionID(int id)
	{
		this.professionID = id;
	}
	
	public void setSecteur(Secteur secteur) {
		Secteur old = this.secteur;
		this.secteur = secteur;
		
		if(old != null)
			this.support.firePropertyChange("secteur", old, this.secteur);
	}

	public void setProfession(String profession) {
		String old = this.profession;
		this.profession = profession;
		
		if(old != null && !old.equals(""))
			this.support.firePropertyChange("profession", old, this.profession);
	}

	public void setStructure(String structure) {
		String old = this.structure;
		this.structure = structure;
		
		if(old != null && !old.equals(""))
			this.support.firePropertyChange("structure", old, this.structure);
	}

	public void setDescriptifActivite(String descriptifActivite) {
		String old = this.descriptifActivite;
		this.descriptifActivite = descriptifActivite;
		
		if(old != null && !old.equals(""))
			this.support.firePropertyChange("descriptifActivite", old, this.descriptifActivite);
	}
	
	
}
