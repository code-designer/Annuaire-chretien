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
				logger.trace("Connexion à la base de données");
			}
		}
		catch(SQLException | ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Impossible de se connecter à la base de données.\n"
					+ "source erreur : "+e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
			logger.error("Echec de connexion à la base de données");
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
				logger.trace("Connexion à la base de données");
				createDatabaseTable(connect, FileUtils.getSQLFileContent(DBConnection.class.getResourceAsStream("/database.sql")));
			}
		}
		catch(SQLException | ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Impossible de se connecter à la base de données.\n"
					+ "source erreur : "+e.getMessage(),"Erreur", JOptionPane.ERROR_MESSAGE);
			logger.error("Echec de connexion à la base de données");
			System.exit(1);
		}
		
		return connect;
	}
	
	public static void createDatabaseTable(Connection connect, String sql)
	{
		try {
			connect.createStatement().executeUpdate(sql);
			logger.trace("Configuration de la base de données");
		}
		catch(Exception e)
		{
			logger.error("Impossible de créer les tables");
		}
	}
}
