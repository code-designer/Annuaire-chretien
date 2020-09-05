package org.epbomi.personne.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import javax.swing.JOptionPane;

import org.epbomi.personne.model.Contact;

public class ContactDAO extends DAO<Contact> {

	public ContactDAO(Connection connect) {
		super(connect);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Contact obj) {
		// TODO Auto-generated method stub
		int i = 1;
		try
		{
			PreparedStatement pst = connect.prepareStatement("INSERT INTO contact (contact_id, cel, tel, urgence, "
					+ "postal, email,residence) VALUES (?, ?, ?, ?, ?, ?, ?);");
			pst.setInt(i++, obj.getContactID());
			pst.setString(i++, obj.getCel());
			pst.setString(i++, obj.getTel());
			pst.setString(i++, obj.getUrgence());
			pst.setString(i++, obj.getPostal());
			pst.setString(i++, obj.getEmail());
			pst.setString(i++, obj.getResidence());
			

			int rep = pst.executeUpdate();
			if(rep > 0)
				return true;
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Insertion impossible la table contact: "+e.getMessage());
		}
		return false;
	}

	@Override
	public boolean delete(Contact obj) {
		// TODO Auto-generated method stub
		try
		{
			PreparedStatement pst = connect.prepareStatement("DELETE FROM contact WHERE contact_id = ?;");
			pst.setInt(1, obj.getContactID());
			int rep = pst.executeUpdate();
			
			if(rep > 0)
				return true;
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Impossible de supprimer cette ligne de la table contact: "+
		e.getMessage());
		}
		return false;
	}

	@Override
	public boolean update(Contact obj) {
		// TODO Auto-generated method stub
		int i = 1;
		try
		{
			PreparedStatement pst = connect.prepareStatement("UPDATE contact set cel = ?, tel = ?, urgence = ?,"
					+ "postal = ?, email = ?, residence = ? WHERE contact_id = ?");
			pst.setString(i++, obj.getCel());
			pst.setString(i++, obj.getTel());
			pst.setString(i++, obj.getUrgence());
			pst.setString(i++, obj.getPostal());
			pst.setString(i++, obj.getEmail());
			pst.setString(i++, obj.getResidence());
			pst.setInt(i++, obj.getContactID());
			
			int rep = pst.executeUpdate(); 
			
			if(rep > 0)
				return true;
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Impossible de mettre à jour la table contact: "+e.getMessage());
		}
		return false;
	}

	@Override
	public Contact find(int id) {
		// TODO Auto-generated method stub
		Contact c = new Contact();
		try
		{
			PreparedStatement pstate = connect.prepareStatement("SELECT * FROM contact where contact_id = ?");
			pstate.setInt(1, id);
			ResultSet res = pstate.executeQuery();
			if(res.next())
			{
				c = new Contact(id, res.getString("cel"), res.getString("tel"),
						res.getString("urgence"), res.getString("postal"),res.getString("email"), 
						res.getString("residence"));
				return c;
			}
			
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Impossible d'acceder à la table Contact\n"+ e.getMessage());
		}
		return c;
	}

	@Override
	public Set<Contact> getList() {
		// TODO Auto-generated method stub
		return null;
	}

		
}
