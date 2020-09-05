package org.epbomi.personne.model;

public class Contact extends AbstractModel{
	
	private int contactID = 0;
	/**
	 * Numéro de téléphone mobile
	 */
	private String cel;
	
	/**
	 * Deuxième numéro de téléphonie mobile
	 */
	private String tel;
	
	/**
	 * Numéro à appeler en cas d'urgence, le conjoint ou un proche parent
	 */
	private String urgence;
	
	/**
	 * Adresse postale
	 */
	private String postal;
	
	/**
	 * Adresse de messagerie electronique
	 */
	private String email;
	
	/**
	 * Indication du lieu de residence
	 */
	private String residence;
	
	public Contact() {
		this.contactID = 0;
		this.cel = "NEANT";
		this.tel = "NEANT";
		this.urgence = "NEANT";
		this.postal = "AUCUNE";
		this.email = "PAS DE MAIL";
		this.residence = "SDF";
	}
	
	public Contact(int id, String cel, String tel, String urgence, String postal, String email, String residence)
	{
		this.contactID = id;
		this.cel = cel;
		this.tel = tel;
		this.urgence = urgence;
		this.postal = postal;
		this.email = email;
		this.residence = residence;
	}
	
	public int getContactID() {
		return contactID;
	}
	
	public String getCel() {
		return cel;
	}

	public String getTel() {
		return tel;
	}

	public String getUrgence() {
		return urgence;
	}

	public String getPostal() {
		return this.postal.isEmpty() ? this.postal : this.postal.toUpperCase();
	}

	public String getEmail() {
		return this.email.isEmpty() ? this.email : this.email.toUpperCase();
	}

	public String getResidence() {
		return residence;
	}
	
	public void setContactID(int id)
	{
		this.contactID = id;
	}
	
	public void setCel(String cel) {
		String old = this.cel;
		this.cel = cel;
		
		if(old != null && !old.equals(""))
			this.support.firePropertyChange("cel", old, this.cel);
	}

	public void setTel(String tel) {
		String old = this.tel;
		this.tel = tel;
		
		if(old != null && !old.equals(""))
			this.support.firePropertyChange("tel", old, this.tel);
	}

	public void setUrgence(String urgence) {
		String old = this.urgence;
		this.urgence = urgence;
		
		if(old != null && !old.equals(""))
			this.support.firePropertyChange("urgence", old, this.urgence);
	}

	public void setPostal(String postal) {
		String old = this.postal;
		this.postal = postal;
		
		if(old != null && !old.equals(""))
			this.support.firePropertyChange("postal", old, this.postal);
	}

	public void setEmail(String email) {
		String old = this.email;
		this.email = email;
		
		if(old != null && !old.equals(""))
			this.support.firePropertyChange("email", old, this.email);
	}

	public void setResidence(String residence) {
		String old = this.residence;
		this.residence = residence;
		
		if(old != null && !old.equals(""))
			this.support.firePropertyChange("residence", old, this.residence);
	};
	
	
}
