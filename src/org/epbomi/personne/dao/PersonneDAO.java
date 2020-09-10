package org.epbomi.personne.dao;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.epbomi.personne.model.Contact;
import org.epbomi.personne.model.Ethnie;
import org.epbomi.personne.model.Ministere;
import org.epbomi.personne.model.Personne;
import org.epbomi.personne.model.Profession;
import org.epbomi.personne.model.Sexe;
import org.epbomi.personne.model.VieConjugale;
import org.epbomi.personne.utils.FileUtils;

public class PersonneDAO extends DAO<Personne> {
	public static Logger logger = Logger.getLogger(PersonneDAO.class);
	public PersonneDAO(Connection connect) {
		super(connect);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Personne obj) {
		// TODO Auto-generated method stub
		
		try
		{
			connect.setAutoCommit(false);
			int i = 1;	
			PreparedStatement pst = connect.prepareStatement("INSERT INTO personne (code, nom, prenoms, sexe, "
					+ "date_de_naissance, lieu_de_naissance, ethnie, nationalite, pathimg) "
					+ "VALUES (?,?,?,?,?,?,?,?,?);");
			pst.setString(i++, obj.getCode());
			pst.setString(i++, obj.getNom());
			pst.setString(i++, obj.getPrenoms());
			pst.setObject(i++, obj.getSexe());
			pst.setObject(i++, obj.getDateDeNaissance());
			pst.setString(i++, obj.getLieuDeNaissance());
			pst.setObject(i++, obj.getEthnie());
			pst.setString(i++, obj.getNationalite());
			
			if(Files.isRegularFile(obj.getPathImg()))
				pst.setObject(i++, FileUtils.copy(obj.getPathImg(), Paths.get(FileUtils.MyDoc+"Images"+File.separator+
						"user_" + obj.getCode() +"." + FileUtils.getExtension(obj.getPathImg().toString()))));
			else
				pst.setObject(i++, Paths.get(FileUtils.MyDoc+"Images"+File.separator+"unknown.png"));
				
			int rep = pst.executeUpdate();
			pst.close();
			
			connect.setAutoCommit(true);
			
			int id = this.lastInsertId();
			
			obj.getMinistere().setMinistereID(id);
			obj.getProfession().setProfessionID(id);
			obj.getVieConjugale().setVieConjugaleID(id);
			obj.getContact().setContactID(id);
			
			DAO<Profession> prof = DAOFactory.getProfessionDAO();
			prof.create(obj.getProfession());
			
			DAO<Ministere> min = DAOFactory.getMinistereDAO();
			min.create(obj.getMinistere());
			
			DAO<VieConjugale> vc = DAOFactory.getVieConjugaleDAO();
			vc.create(obj.getVieConjugale());
			
			DAO<Contact> c = DAOFactory.getContactDAO();
			c.create(obj.getContact());
			
			if(rep > 0)
			{
				logger.trace("Ajout de "+ obj.toString() +" dans la base de données");
				return true;
			}
		}
		catch(SQLException e)
		{
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "Impossible d'acceder à la table personne.\n"+ e.getMessage());
		}
		logger.error("Echec d'ajout d'information");
		return false;
	}

	@Override
	public boolean delete(Personne obj) {
		// TODO Auto-generated method stub
		try
		{
			PreparedStatement pst = connect.prepareStatement("PRAGMA foreign_keys = ON;");
			pst.execute();
			pst = connect.prepareStatement("DELETE FROM personne WHERE personne_id = ?;");
			pst.setInt(1, obj.getPersonneID());
			int rep = pst.executeUpdate();
			pst.close();
			
			if(rep > 0)
			{
				logger.trace("Suppression d'une ligne de " + obj.toString() + " de la base de données");
				return true;
			}
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Suppression impossible dans la table personne.\n"+ e.getMessage());
			logger.error("Echec de suppression d'information");
		}
		return false;
	}

	@Override
	public boolean update(Personne obj) {
		// TODO Auto-generated method stub
		int i = 1;
		try
		{
			PreparedStatement pst = connect.prepareStatement("UPDATE personne SET code = ?, nom = ?, prenoms = ?,"
					+ " sexe = ?, date_de_naissance = ?, lieu_de_naissance = ?, ethnie = ?, nationalite = ?, pathimg = ?"
					+ " WHERE personne_id = ?;");
			pst.setString(i++, obj.getCode());
			pst.setString(i++, obj.getNom());
			pst.setString(i++, obj.getPrenoms());
			pst.setObject(i++, obj.getSexe());
			pst.setObject(i++, obj.getDateDeNaissance());
			pst.setString(i++, obj.getLieuDeNaissance());
			pst.setObject(i++, obj.getEthnie());
			pst.setString(i++, obj.getNationalite());
			
			if(Files.isRegularFile(obj.getPathImg()))
				pst.setObject(i++, FileUtils.copy(obj.getPathImg(), Paths.get(FileUtils.MyDoc+"Images"+File.separator+
						"user_" + obj.getCode() +"." + FileUtils.getExtension(obj.getPathImg().toString()))));
			else
				pst.setObject(i++, Paths.get(FileUtils.MyDoc+"Images"+File.separator+"unknown.png"));
			
			//pst.setString(i++, obj.getPathImg().toString());
			pst.setInt(i++, obj.getPersonneID());
			int rep = pst.executeUpdate();
			pst.close();
			
			DAO<Profession> prof = DAOFactory.getProfessionDAO();
			prof.update(obj.getProfession());
			
			DAO<Ministere> min = DAOFactory.getMinistereDAO();
			min.update(obj.getMinistere());
			
			DAO<VieConjugale> vc = DAOFactory.getVieConjugaleDAO();
			vc.update(obj.getVieConjugale());
			
			DAO<Contact> c = DAOFactory.getContactDAO();
			c.update(obj.getContact());
			
			if(rep > 0)
			{
				logger.trace("Mise à jour des informations de " + obj.toString());
				return true;
			}
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Modification impossible dans la table personne.\n"+ this.getClass()+": "+e.getMessage());
		}
		logger.error("Echec de mise à jour");
		return false;
	}

	@Override
	public Personne find(int id) {
		// TODO Auto-generated method stub
		Personne p = null;
		try
		{
			PreparedStatement pst = connect.prepareStatement("SELECT * FROM personne WHERE personne_id = ?;");
			pst.setInt(1, id);
			ResultSet res = pst.executeQuery();
			if(res.next())
			{
				Sexe sx = Sexe.toEnum(res.getString("sexe"));
				Ethnie eth = Ethnie.toEnum(res.getString("ethnie"));
				LocalDate ld = LocalDate.parse(res.getString("date_de_naissance"),
						DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				Path path = (res.getString("pathimg") != null) ? Paths.get(res.getString("pathimg")) : null;
				p = new Personne(res.getInt("personne_id"),res.getString("code"),res.getString("nom"), 
						res.getString("prenoms"),sx, ld, res.getString("lieu_de_naissance"), eth,
						res.getString("nationalite"),path);
				
				DAO<Ministere> minDAO = DAOFactory.getMinistereDAO();
				Ministere min = minDAO.find(id);
				
				DAO<Profession> profDAO = DAOFactory.getProfessionDAO();
				Profession prof = profDAO.find(id);
				
				DAO<VieConjugale> vieDAO = DAOFactory.getVieConjugaleDAO();
				VieConjugale vie = vieDAO.find(id);
				
				DAO<Contact> conDAO = DAOFactory.getContactDAO();
				Contact con = conDAO.find(id);
				
				p.setMinistere(min);
				p.setProfession(prof);
				p.setVieConjugale(vie);
				p.setContact(con);
				
				return p;
				
			}
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Modification impossible dans la table personne.\n"+ e.getMessage());
		}
		logger.error("Impossible de recuperer l'information de l'id: "+id);
		return p;
	}

	@Override
	public Set<Personne> getList() {
		// TODO Auto-generated method stub
		Set<Personne> list = new TreeSet<Personne>();
		try
		{
			ResultSet res = connect.createStatement().executeQuery("SELECT * FROM personne;");
			
			while(res.next())
			{
				Personne perso = this.find(res.getInt("personne_id"));
				if(perso != null)
					list.add(perso);
			}
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Impossible d'obtenir la liste dans la table personne.\n"+ e.getMessage());
			logger.error("Impossible de recuperer la liste");
		}
		logger.trace("Recuperation de la liste de données");
		return list;
	}

}
