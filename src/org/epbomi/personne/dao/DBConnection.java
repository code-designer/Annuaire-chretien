package org.epbomi.personne.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.epbomi.personne.utils.FileUtils;

public class DBConnection {
	private static Connection connect;
	public static Logger logger = Logger.getLogger(DBConnection.class);
	
	public static Connection getInstance2()
	{
		try
		{
			if(connect == null)
			{
				Class.forName("org.sqlite.JDBC");
				//connect = DriverManager.getConnection("jdbc:sqlite::resource:database.db");
				connect = DriverManager.getConnection("jdbc:sqlite:database.db");
				logger.trace("Connexion � la base de donn�es");
			}
		}
		catch(SQLException | ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Impossible de se connecter � la base de donn�es.\n"
					+ "source erreur : "+e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
			logger.error("Echec de connexion � la base de donn�es");
			System.exit(1);
		}
		
		return connect;
	}
	
	public static Connection getInstance()
	{
		try
		{
			if(connect == null)
			{
				Class.forName("org.sqlite.JDBC");
				//connect = DriverManager.getConnection("jdbc:sqlite::resource:database.db");
				connect = DriverManager.getConnection("jdbc:sqlite:database.db");
				logger.trace("Connexion � la base de donn�es");
				createDatabaseTable(connect, FileUtils.getSQLFileContent(DBConnection.class.getResourceAsStream("/database.sql")));
			}
		}
		catch(SQLException | ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Impossible de se connecter � la base de donn�es.\n"
					+ "source erreur : "+e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
			logger.error("Echec de connexion � la base de donn�es");
			System.exit(1);
		}
		
		return connect;
	}
	
	public static void createDatabaseTable(Connection connect, String sql)
	{
		try {
			connect.createStatement().executeUpdate(sql);
			logger.trace("Configuration de la base de donn�es");
		}
		catch(Exception e)
		{
			logger.error("Impossible de cr�er les tables");
		}
	}
}
