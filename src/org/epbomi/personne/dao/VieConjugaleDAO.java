package org.epbomi.personne.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.swing.JOptionPane;

import org.epbomi.personne.model.Statut;
import org.epbomi.personne.model.VieConjugale;

public class VieConjugaleDAO extends DAO<VieConjugale> {

	public VieConjugaleDAO(Connection connect) {
		super(connect);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(VieConjugale obj) {
		// TODO Auto-generated method stub
		int i = 1;
		try
		{
			PreparedStatement pst = connect.prepareStatement("INSERT INTO vieconjugale (vieconjugale_id, situation, "
					+ "conjoint, date_union, nombre_enfants) VALUES (?,?,?,?,?);");
			pst.setInt(i++, obj.getVieConjugaleID());
			pst.setObject(i++, obj.getSituation());
			pst.setString(i++, obj.getConjoint());
			pst.setObject(i++, obj.getDateUnion());
			pst.setInt(i++, obj.getNombreEnfants());
			int rep = pst.executeUpdate();
			
			if(rep > 0)
				return true;
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Impossible d'acceder à la table vieconjugale\n"+ e.getMessage());
		}
		return false;
	}

	@Override
	public boolean delete(VieConjugale obj) {
		// TODO Auto-generated method stub
		try
		{
			PreparedStatement pst = connect.prepareStatement("DELETE FROM vieconjugale WHERE vieconjugale_id = ?;");
			pst.setObject(1, obj.getVieConjugaleID());
			int rep = pst.executeUpdate();
			
			if(rep > 0)
				return true;
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Impossible d'acceder à la table vieconjugale\n"+ e.getMessage());
		}
		return false;
	}

	@Override
	public boolean update(VieConjugale obj) {
		// TODO Auto-generated method stub
		int i = 1;
		try
		{
			PreparedStatement pst = connect.prepareStatement("UPDATE vieconjugale SET situation = ?, conjoint = ?, "
					+ "date_union = ?, nombre_enfants = ? WHERE vieconjugale_id = ?");
			pst.setObject(i++, obj.getSituation());
			pst.setString(i++, obj.getConjoint());
			pst.setObject(i++, obj.getDateUnion());
			pst.setInt(i++, obj.getNombreEnfants());
			pst.setInt(i++, obj.getVieConjugaleID());
			int rep = pst.executeUpdate();
			
			if(rep > 0)
				return true;
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Impossible d'acceder à la table vieconjugale\n"+ e.getMessage());
		}
		return false;
	}

	@Override
	public VieConjugale find(int id) {
		// TODO Auto-generated method stub
		VieConjugale vc = new VieConjugale();
		try
		{
			PreparedStatement pst = connect.prepareStatement("SELECT * FROM vieconjugale WHERE vieconjugale_id = ?");
			pst.setInt(1, id);
			ResultSet res = pst.executeQuery();
			
			if(res.next())
			{
				Statut st =  Statut.toEnum(res.getString("situation"));
				LocalDate du = res.getString("date_union") != null ? LocalDate.parse(res.getString("date_union"), 
						DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
				vc = new VieConjugale(id, st, res.getString("conjoint"), du, res.getInt("nombre_enfants"));
				return vc;
			}
			
			
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Impossible d'acceder à la table vie_conjugale\n"+ e.getMessage());
		}
		return vc;
	}

	@Override
	public Set<VieConjugale> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
