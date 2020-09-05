package org.epbomi.personne.model;

import java.nio.file.Path;
import java.time.LocalDate;

/**
 * represente les informations relative à une personne
 * 
 * @author bigoh
 * @version 1.0
 */
public class Personne extends AbstractModel implements Comparable<Personne> {
	/**
	 * Code identifiant unique propre à une personne
	 * 
	 * @see Personne#getId()
	 */
	private int personneID = 0;
	
	/**
	 * code de dime
	 */
	private String code = "";
	
	/**
	 * Nom de personne
	 * 
	 * @see Personne#getNom()
	 */
	private String nom = "";
	
	/**
	 * Prénoms de personne
	 * 
	 * @see Personne#getPrenoms()
	 */
	private String prenoms = "";
	
	/**
	 * la nature du sexe de personne
	 * 
	 * @see Personne#getSexe()
	 */
	private Sexe sexe = Sexe.INCONNU;
	
	/**
	 * la date de naissance de personne
	 * 
	 * @see Personne#getDateDeNaissance()
	 */
	private LocalDate dateDeNaissance = null;
	
	/**
	 * lieu de naissance de personne
	 * 
	 * @see Personne#getLieuDeNaissance()
	 */
	private String lieuDeNaissance = "";
	
	/**
	 * Groupe ethnique
	 * 
	 * @see Personne#getEthnie()
	 */
	private Ethnie ethnie = Ethnie.INCONNU;
	
	/**
	 * nationalité de personne
	 * 
	 * @see Personne#getNationalite()
	 */
	private String nationalite = "";
	
	/**
	 * Chemin d'accès du fichier Image
	 * 
	 * @see Personne#getPathImg()
	 */
	private Path pathImg = null;
	/**
	 * les contacts de personne
	 * 
	 * @see Personne#getContact()
	 */
	private Contact contact = null;
	
	/**
	 * la vie conjugale de personne
	 * 
	 * @see Personne#getVieConjugale()
	 */
	private VieConjugale vieConjugale = null;
	
	/**
	 * la vie professionnelle de personne
	 * 
	 * @see Personne#getProfession()
	 */
	private Profession profession = null;
	
	/**
	 * la vie spirituelle de personne
	 * 
	 * @see Personne#getMinistere()
	 */
	private Ministere ministere = null;
	
	/**
	 * variable pour inverser le nom
	 * 
	 * @see Personne#isInversion()
	 */
	private boolean inversion;
	/**
	 * Constructeur par defaut
	 */
	public Personne() {
		this.personneID = 0;
		this.code = "";
		this.nom = "";
		this.prenoms = "";
		this.sexe = Sexe.INCONNU;
		this.dateDeNaissance = null;
		this.lieuDeNaissance = "";
		this.ethnie = Ethnie.INCONNU;
		this.nationalite = "";
		this.pathImg = null;
		
		this.ministere = new Ministere();
		this.profession = new Profession();
		this.vieConjugale = new VieConjugale();
		this.contact = new Contact();
	};
	
	public Personne(int personneID, String code, String nom, String prenoms, Sexe sexe, LocalDate dateDeNaissance,
			String lieuDeNaissance, Ethnie ethnie, String nationalite, Path pathImg) {
		super();
		this.personneID = personneID;
		this.code = code;
		this.nom = nom;
		this.prenoms = prenoms;
		this.sexe = sexe;
		this.dateDeNaissance = dateDeNaissance;
		this.lieuDeNaissance = lieuDeNaissance;
		this.ethnie = ethnie;
		this.nationalite = nationalite;
		this.pathImg = pathImg;
		
		this.ministere = new Ministere();
		this.profession = new Profession();
		this.vieConjugale = new VieConjugale();
		this.contact = new Contact();
		
	}

	/**
	 * retourne l'identifiant de personne
	 * 
	 * @return int
	 */
	public int getPersonneID() {
		return personneID;
	}
	
	/**
	 * Retourne le code de personne
	 * 
	 * @return String
	 */
	public String getCode()
	{
		return this.code.isEmpty() ? this.code : this.code.toUpperCase();
	}

	/**
	 * retourne le nom de personne
	 * 
	 * @return String
	 */
	public String getNom() {
		return this.nom.isEmpty() ? this.nom : this.nom.toUpperCase();
	}


	/**
	 * retourne les prenoms de personne
	 * 
	 * @return String
	 */
	public String getPrenoms() {
		return this.prenoms.isEmpty() ? this.prenoms : this.prenoms.toUpperCase();
	}


	/**
	 * retourne le sexe de personne
	 * 
	 * @return Sexe
	 */
	public Sexe getSexe() {
		return sexe;
	}


	/**
	 * retourne la date de naissance
	 * 
	 * @return LocalDate
	 */
	public LocalDate getDateDeNaissance() {
		return dateDeNaissance;
	}


	/**
	 * retourne le lieu de naissance de personne
	 * 
	 * @return string
	 */
	public String getLieuDeNaissance() {
		return this.lieuDeNaissance.isEmpty() ? this.lieuDeNaissance : this.lieuDeNaissance.toUpperCase();
	}


	/**
	 * retourne le chemin du fichier photo
	 * 
	 * @return Path
	 */
	public Path getPathImg() {
		return pathImg;
	}


	/**
	 * retourne les differents contacts
	 * 
	 * @return Contact
	 */
	public Contact getContact() {
		return contact;
	}


	/**
	 * retourne la vie conjugale de personne
	 * 
	 * @return VieConjugale
	 */
	public VieConjugale getVieConjugale() {
		return vieConjugale;
	}


	/**
	 * retourne la profession
	 * 
	 * @return Profession
	 */
	public Profession getProfession() {
		return profession;
	}


	/**
	 * retourne l'etat spirituel de personne
	 * 
	 * @return
	 */
	public Ministere getMinistere() {
		return ministere;
	}



	/**
	 * @return
	 */
	public boolean isInversion() {
		return inversion;
	}

	
	/**
	 * @return
	 */
	public Ethnie getEthnie() {
		return ethnie;
	}



	/**
	 * @return
	 */
	public String getNationalite() {
		return nationalite;
	}

	/**
	 * @param id
	 */
	public void setPersonneID(int id) {
		this.personneID = id;
	}

	public void setCode(String code)
	{
		String old = this.code;
		this.code = code;
		
		if(old != null && old.equals(""))
			this.support.firePropertyChange("Code", old, this.code);
	}

	/**
	 * @param nom
	 */
	public void setNom(String nom) {
		String old = this.nom;
		this.nom = nom;
		
		if(old != null && !old.equals(""))
			this.support.firePropertyChange("nom", old, this.nom);
	}



	/**
	 * @param prenoms
	 */
	public void setPrenoms(String prenoms) {
		String old = this.prenoms;
		this.prenoms = prenoms;
		
		if(old != null && !old.equals(""))
			this.support.firePropertyChange("prenoms", old, this.prenoms);
	}



	/**
	 * @param sexe
	 */
	public void setSexe(Sexe sexe) {
		Sexe old = this.sexe;
		this.sexe = sexe;
		
		if(old != Sexe.INCONNU)
			this.support.firePropertyChange("sexe", old, this.sexe);
	}



	/**
	 * @param dateDeNaissance
	 */
	public void setDateDeNaissance(LocalDate dateDeNaissance) {
		LocalDate old = this.dateDeNaissance;
		this.dateDeNaissance = dateDeNaissance;
		
		if(old != null)
			this.support.firePropertyChange("dateDeNaissance", old, this.dateDeNaissance);
	}



	/**
	 * @param lieuDeNaissance
	 */
	public void setLieuDeNaissance(String lieuDeNaissance) {
		String old = this.lieuDeNaissance;
		this.lieuDeNaissance = lieuDeNaissance;
		
		if(old != null && !old.equals(""))
			this.support.firePropertyChange("lieuDeNaissance", old, this.lieuDeNaissance);
	}



	/**
	 * @param pathImg
	 */
	public void setPathImg(Path pathImg) {
		Path old = this.pathImg;
		this.pathImg = pathImg;
		
		if(old != null)
			this.support.firePropertyChange("pathImg", old, this.pathImg);
	}



	/**
	 * @param contact
	 */
	public void setContact(Contact contact) {
		Contact old = this.contact;
		this.contact = contact;
		
		if(old != null)
			this.support.firePropertyChange("contact", old, this.contact);
	}



	/**
	 * @param vieConjugale
	 */
	public void setVieConjugale(VieConjugale vieConjugale) {
		VieConjugale old = this.vieConjugale;
		this.vieConjugale = vieConjugale;
		
		if(old != null)
			this.support.firePropertyChange("vieConjugale", old, this.vieConjugale);
	}



	/**
	 * @param profession
	 */
	public void setProfession(Profession profession) {
		Profession old = this.profession;
		this.profession = profession;
		
		if(old != null)
			this.support.firePropertyChange("profession", old, this.profession);
	}



	/**
	 * @param ministere
	 */
	public void setMinistere(Ministere ministere) {
		Ministere old = this.ministere;
		this.ministere = ministere;
		
		if(old != null)
			this.support.firePropertyChange("ministere", old, this.ministere);
	}


	/**
	 * @param ethnie
	 */
	public void setEthnie(Ethnie ethnie) {
		Ethnie old = this.ethnie;
		this.ethnie = ethnie;
		
		if(old != null && !old.equals(Ethnie.INCONNU))
			this.support.firePropertyChange("ethnie", old, this.ethnie);
	}



	/**
	 * @param nationalite
	 */
	public void setNationalite(String nationalite) {
		String old = this.nationalite;
		this.nationalite = nationalite;
		
		if(old != null && !old.equals(""))
			this.support.firePropertyChange("nationalite", old, this.nationalite);
	}



	/**
	 * @param inversion
	 */
	public void setInversion(boolean inversion) {
		this.inversion = inversion;
	}


	public String toString()
	{
		return (inversion) ? this.prenoms+ " "+this.nom : this.nom+ " "+this.prenoms;
	}

	@Override
	public int compareTo(Personne p) {
		// TODO Auto-generated method stub
		return this.toString().compareTo(p.toString());
	}


}
