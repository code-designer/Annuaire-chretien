package org.epbomi.personne.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import javax.swing.JOptionPane;

import org.epbomi.personne.model.Profession;
import org.epbomi.personne.model.Secteur;

public class ProfessionDAO extends DAO<Profession> {

	public ProfessionDAO(Connection connect) {
		super(connect);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Profession obj) {
		// TODO Auto-generated method stub
		int i = 1;
		try
		{
			PreparedStatement pst = connect.prepareStatement("INSERT INTO profession ("
					+ "secteur, profession, structure, descriptif_activite) VALUES (?,?,?,?);");
			pst.setObject(i++, obj.getSecteur());
			pst.setString(i++, obj.getProfession());
			pst.setString(i++, obj.getStructure());
			pst.setString(i++, obj.getDescriptifActivite());
			int rep = pst.executeUpdate();
			
			if(rep > 0)
				return true;
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Impossible d'acceder à la table Profession\n"+ e.getMessage());
		}
		return false;
	}

	@Override
	public boolean delete(Profession obj) {
		// TODO Auto-generated method stub
		try
		{
			PreparedStatement pst = connect.prepareStatement("DELETE FROM profession WHERE profession_id = ?;");
			pst.setInt(1, obj.getProfessionID());
			int rep = pst.executeUpdate();
			
			if(rep > 0)
				return true;
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Impossible d'acceder à la table Profession\n"+ e.getMessage());
		}
		return false;
	}

	@Override
	public boolean update(Profession obj) {
		// TODO Auto-generated method stub
		int i = 1;
		try
		{
			PreparedStatement pst = connect.prepareStatement("UPDATE profession SET secteur = ?, profession = ?,"
					+ "structure = ?, descriptif_activite = ? WHERE profession_id = ?");
			pst.setObject(i++, obj.getSecteur());
			pst.setObject(i++, obj.getProfession());
			pst.setString(i++, obj.getStructure());
			pst.setString(i++, obj.getDescriptifActivite());
			pst.setInt(i++, obj.getProfessionID());
			int rep = pst.executeUpdate();
			
			if(rep > 0)
				return true;
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Impossible d'acceder à la table Profession\n"+ e.getMessage());
		}
		return false;
	}

	@Override
	public Profession find(int id) {
		// TODO Auto-generated method stub
		Profession p =  new Profession();
		try
		{
			PreparedStatement pst = connect.prepareStatement("SELECT * FROM profession WHERE profession_id = ?;");
			pst.setInt(1, id);
			ResultSet res = pst.executeQuery();
			
			if(res.next())
			{
				Secteur sct = Secteur.toEnum(res.getString("secteur"));
				p = new Profession(id, sct, res.getString("profession"), res.getString("structure"),
						res.getString("descriptif_activite"));
				return p;
			}
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Impossible d'acceder à la table Profession\n"+ e.getMessage());
		}
		return p;
	}

	@Override
	public Set<Profession> getList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
