package org.epbomi.personne.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.epbomi.personne.model.Departement;
import org.epbomi.personne.model.Eglise;
import org.epbomi.personne.model.Ministere;

public class MinistereDAO extends DAO<Ministere> {

	public MinistereDAO(Connection connect) {
		super(connect);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Ministere obj) {
		// TODO Auto-generated method stub
		int i = 1;
		try
		{
			PreparedStatement pst = connect.prepareStatement("INSERT INTO ministere (ministere_id, date_conversion, date_bapteme,"
					+ "departement, responsabilite, section ) VALUES (?,?,?,?,?,?);");
			pst.setObject(i++, obj.getMinistereID());
			pst.setObject(i++, obj.getDateConversion());
			pst.setObject(i++, obj.getDateBapteme());
			pst.setObject(i++, obj.getDepartement());
			pst.setString(i++, obj.getResponsabilite());
			pst.setObject(i++, obj.getSection());
			
			int rep = pst.executeUpdate();
			if(rep > 0)
				return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Ministere obj) {
		// TODO Auto-generated method stub
		try
		{
			PreparedStatement pst = connect.prepareStatement("DELETE FROM ministere WHERE ministere_id = ?");
			pst.setInt(1, obj.getMinistereID());
			int rep = pst.executeUpdate();
			
			if(rep > 0)
				return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Ministere obj) {
		// TODO Auto-generated method stub
		int i = 1;
		try
		{
			PreparedStatement pst = connect.prepareStatement("UPDATE ministere SET date_conversion = ?,"
					+ "date_bapteme = ?, departement = ?, responsabilite = ?, section = ? WHERE ministere_id = ?");
			pst.setObject(i++, obj.getDateConversion());
			pst.setObject(i++, obj.getDateBapteme());
			pst.setObject(i++, obj.getDepartement());
			pst.setString(i++, obj.getResponsabilite());
			pst.setObject(i++, obj.getSection());
			pst.setInt(i++, obj.getMinistereID());
			int rep = pst.executeUpdate();
			
			if(rep > 0)
				return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Ministere find(int id) {
		// TODO Auto-generated method stub
		Ministere m = new Ministere();
		try
		{
			PreparedStatement pst = connect.prepareStatement("SELECT * FROM ministere WHERE ministere_id = ?;");
			pst.setInt(1, id);
			ResultSet res = pst.executeQuery();
			if(res.next())
			{
				LocalDate dc = res.getString("date_conversion") != null ?LocalDate.parse(res.getString("date_conversion"), 
						DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
				
				LocalDate db = res.getString("date_bapteme") != null ? LocalDate.parse(res.getString("date_bapteme"), 
						DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
				
				Eglise sct = Eglise.toEnum(res.getString("section"));
				
				Departement dept = Departement.toEnum(res.getString("departement"));
				
				 m = new Ministere(id, dc, db, dept, res.getString("responsabilite"), sct);
				 
				 return m;
			}
			
		}
		catch(SQLException | IllegalArgumentException  e)
		{
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public Set<Ministere> getList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
