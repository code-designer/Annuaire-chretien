package org.epbomi.personne.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Set;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

public abstract class DAO<T> {
	protected Connection connect;
	protected Logger logger = Logger.getLogger(this.getClass());
	
	public DAO(Connection connect)
	{
		this.connect = connect;
	}
	
	public abstract boolean create(T obj);
	
	public abstract boolean delete(T obj);
	
	public abstract boolean update(T obj);
	
	public abstract T find(int id);
	
	public abstract Set<T> getList();
	
	protected int lastInsertId()
	{
		try
		{
			ResultSet res = connect.createStatement().executeQuery("SELECT last_insert_rowid() AS lastId;");
			return res.getInt("lastId");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Impossible de recuperer l'ID de la derniere insertion ("+
					this.getClass() + ") : "+e.getMessage());
		}
		return -1;
	}
}
