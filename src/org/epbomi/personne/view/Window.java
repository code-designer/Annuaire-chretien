package org.epbomi.personne.view;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.epbomi.personne.model.ModelData;
import org.epbomi.personne.utils.FileUtils;

public class Window {
	private static Logger logger = Logger.getLogger(Window.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Properties props = new Properties();
		try {
			props.load(Window.class.getResourceAsStream("/properties/log4j.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PropertyConfigurator.configure(props);
		
		//creer un dossier d'image au lancement du jar
		//pour y copier une image par defaut
		File imgFolder = new File("Images");
		if(!imgFolder.exists() || !imgFolder.isDirectory())
		{
			imgFolder.mkdir();
			InputStream inputStream = Window.class.getResourceAsStream("/unknown.png");
			FileUtils.streamtoImage(inputStream, Paths.get("Images/unknown.png"));
		}
		
		logger.trace("Lancement de l'application");
		
		ModelData model =  new ModelData();
		
		SwingUtilities.invokeLater(() -> {
			Frame frame = new Frame(model);
			frame.setVisible(true);
		});
		
	}

}
