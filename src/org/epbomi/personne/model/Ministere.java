package org.epbomi.personne.model;

import java.time.LocalDate;

public class Ministere extends AbstractModel {
	private int ministereID;
	private LocalDate dateConversion;
	private LocalDate dateBapteme;
	private Departement departement;
	private String responsabilite;
	private Eglise section;
	
	public Ministere() {
		this.ministereID = 0;
		this.departement = Departement.INCONNU;
		this.responsabilite = "AUCUNE";
		this.section = Eglise.INCONNU;
	}
	
	public Ministere(int ministereID, LocalDate dateConversion, LocalDate dateBapteme, Departement departement, String responsabilite,
			Eglise section)
	{
		this.ministereID = ministereID;
		this.dateConversion = dateConversion;
		this.dateBapteme = dateBapteme;
		this.departement = departement;
		this.responsabilite = responsabilite;
		this.section = section;
		
		if((this.dateConversion != null && this.dateBapteme != null))
			if(this.dateConversion.isAfter(dateBapteme))
				this.dateConversion = this.dateBapteme;
	}
	
	public int getMinistereID()
	{
		return ministereID;
	}

	public LocalDate getDateConversion() {
		return dateConversion;
	}

	public LocalDate getDateBapteme() {
		return dateBapteme;
	}

	public Departement getDepartement() {
		return departement;
	}

	public String getResponsabilite() {
		return this.responsabilite.isEmpty() ? this.responsabilite : this.responsabilite.toUpperCase();
	}

	public Eglise getSection() {
		return section;
	}
	
	public void setMinistereID(int id)
	{
		this.ministereID = id;
	}

	public void setDateConversion(LocalDate dateConversion) {
		LocalDate old = this.dateConversion;
		this.dateConversion = dateConversion;
		
		if(old != null)
			this.support.firePropertyChange("dateConversion", old, this.dateConversion);
	}

	public void setDateBapteme(LocalDate dataBapteme) {
		LocalDate old = this.dateBapteme;
		this.dateBapteme = dataBapteme;
		
		if(old != null)
			this.support.firePropertyChange("dataBapteme", old, this.dateBapteme);
	}

	public void setDepartement(Departement departement) {
		Departement old = this.departement;
		this.departement = departement;
		
		if(old != null && !old.equals(""))
			this.support.firePropertyChange("departement", old, this.departement);
	}

	public void setResponsabilite(String responsabilite) {
		String old = this.responsabilite;
		this.responsabilite = responsabilite;
		
		if(old != null && !old.equals(""))
			this.support.firePropertyChange("responsabilite", old, this.responsabilite);
	}

	public void setSection(Eglise section) {
		Eglise old = this.section;
		this.section = section;
		
		if(old != null)
			this.support.firePropertyChange("section", old, this.section);
	}
	
	
}
