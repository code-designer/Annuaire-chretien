package org.epbomi.personne.model;

import java.time.LocalDate;

public class VieConjugale {
	private int vieConjugaleID;
	private Statut situation;
	private String conjoint;
	private LocalDate dateUnion;
	private int nombreEnfants;
	
	public VieConjugale() {
		this.vieConjugaleID = 0;
		this.situation = Statut.CELIBATAIRE;
		this.conjoint = "NEANT";
		this.dateUnion = null;
		this.nombreEnfants = 0;
	}
	
	public VieConjugale(int vieConjugaleID, Statut situation, String conjoint, LocalDate dateUnion, int nombreEnfants)
	{
		this.vieConjugaleID = vieConjugaleID;
		this.situation = situation;
		this.conjoint = conjoint;
		this.dateUnion = dateUnion;
		this.nombreEnfants = nombreEnfants;
	}

	public int getVieConjugaleID() {
		return vieConjugaleID;
	}

	public Statut getSituation() {
		return situation;
	}

	public String getConjoint() {
		return this.conjoint.isEmpty() ? this.conjoint : this.conjoint.toUpperCase();
	}

	public LocalDate getDateUnion() {
		return dateUnion;
	}

	public int getNombreEnfants() {
		return nombreEnfants;
	}

	public void setVieConjugaleID(int vieConjugaleID) {
		this.vieConjugaleID = vieConjugaleID;
	}

	public void setSituation(Statut situation) {
		this.situation = situation;
	}

	public void setConjoint(String conjoint) {
		this.conjoint = conjoint;
	}

	public void setDateUnion(LocalDate dateUnion) {
		this.dateUnion = dateUnion;
	}

	public void setNombreEnfants(int nombreEnfants) {
		this.nombreEnfants = nombreEnfants;
	}
	
	
	
}
