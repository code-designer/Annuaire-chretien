package org.epbomi.personne.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class FileUtils {
	public static String PathAnn = System.getProperty("user.home")+ File.separator + ".annuaire"+ File.separator;
	public static String MyDoc = System.getProperty("user.home")+ File.separator + "Documents"+File.separator;
	private static final String pathImg = "Images"+File.separator+"unknown.png";
	private static final Logger logger = Logger.getLogger(FileUtils.class);
	
	/**
	 * Copie un fichier
	 * @param source Path
	 * @param destination Path
	 * @return Path
	 */
	public static Path copy(Path source, Path destination)
	{
			try {
				if(source != null || source != Paths.get(MyDoc+pathImg))
					return Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
				else
					return Paths.get(MyDoc+pathImg);
			} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error("Copy de fichier impossible. "+ FileUtils.class + ": "+ FileUtils.class.getName());
			}
			return null;
	}
	
	/**
	 * Transforme un InputStream en fichier
	 * @param stream InputStream
	 * @param path Path
	 */
	public static void streamtoFile(InputStream stream, Path path)
	{
			try(FileOutputStream fos = new FileOutputStream(path.toFile()))
			{
				int read = 0;
				byte[] buffer = new byte[1024];
				while((read = stream.read()) != -1)
				{
					fos.write(buffer, 0, read);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
	}
	
	/**
	 * Transforme un InputStream en image
	 * @param stream, InputStream
	 * @param path, Path
	 */
	public static void streamtoImage(InputStream stream, Path path)
	{
		try {
			BufferedImage image = ImageIO.read(stream);
			ImageIO.write(image, "png", path.toFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Retourne l'extension d'un fichier
	 * @param fileName String
	 * @return String
	 */
	public static String getExtension(String fileName)
	{
		String extension = "";
		
		if(!fileName.isEmpty())
		{
			extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		
		return extension;
	}
	
	/**
	 * Redimension une image
	 * @param url String
	 * @param size Integer
	 * @return type Image
	 */
	public static Image resizeImage(String url, int size)
	{
		Image img = null;
		try {
			if(url != null && url != "")
				img = ImageIO.read(new File(url));
			return img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return img;
		}	
	}
	
	/**
	 * Copier le contenu d'un fichier de type InputStream
	 * @param file InputStream
	 * @return String
	 */
	public static String getSQLFileContent(InputStream file)
	{
		if(file != null)
		{
			BufferedReader reader = null;
			try
		    {
				reader = new BufferedReader(new InputStreamReader (file));
				if(reader != null)
				{
					StringBuilder sb = new StringBuilder();
					String line = "";
					while((line = reader.readLine()) != null)
						sb.append(line);
					return sb.toString();
				}
				
		    }
			catch(IOException e)
			{
				logger.error("Impossible de copier le contenu du fichier: "+e.getMessage());
			}
			finally
			{
				try {
					if(reader != null) reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
