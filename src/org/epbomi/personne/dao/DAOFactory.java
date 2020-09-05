package org.epbomi.personne.dao;
import java.sql.Connection;

import org.epbomi.personne.model.Contact;
import org.epbomi.personne.model.Ministere;
import org.epbomi.personne.model.Personne;
import org.epbomi.personne.model.Profession;
import org.epbomi.personne.model.VieConjugale;

public class DAOFactory {
	protected final static Connection connect = DBConnection.getInstance();;
	
	public static DAO<Personne> getPersonneDAO()
	{
		return new PersonneDAO(connect);
	}
	
	public static DAO<Ministere> getMinistereDAO()
	{
		return new MinistereDAO(connect);
	}
	
	public static DAO<Profession> getProfessionDAO()
	{
		return new ProfessionDAO(connect);
	}
	
	public static DAO<Contact> getContactDAO()
	{
		return new ContactDAO(connect);
	}
	
	public static DAO<VieConjugale> getVieConjugaleDAO()
	{
		return new VieConjugaleDAO(connect);
	}
}
