package org.epbomi.personne.utils;

import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.MessageFormat;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JComponent;
import javax.swing.JTable;

import org.apache.log4j.Logger;
import org.epbomi.personne.model.Personne;

public class Impression {
	
	private PrinterJob job;
	private JComponent component;
	@SuppressWarnings("unused")
	private boolean mode;
	@SuppressWarnings("unused")
	private MessageFormat header, footer;
	private Personne perso;
	private Logger logger = Logger.getLogger(this.getClass());
	//contructeur	
	public Impression( JComponent component)
	{
		this.job = PrinterJob.getPrinterJob();
		this.component = component;
	}
	
	//Constructeur
	public Impression( Personne perso)
	{
		this.job = PrinterJob.getPrinterJob();
		this.perso = perso;
	}
	
	/**
	 * Imprimer une page
	 */
	public void print()
	{
		PageFormat pf = job.defaultPage();	
		//pf.setOrientation(PageFormat.PORTRAIT);
		//PageFormat psf = job.pageDialog(pf);
		
		//if(pf != psf)
		{
			job.setPrintable(new Imprimable(perso), pf);
			boolean doPrint = job.printDialog();
		
			if(doPrint)
			{
				try {
				job.print();
				logger.trace("Impression de données");
				} catch (PrinterException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
					logger.error("echec d'impression");
				}
			}
		}
	}
	
	/**
	 * Imprimer une JTable
	 * @param header MessageFormat, entête de page
	 * @param footer MessageFormat, pied de page
	 */
	public void printTable(MessageFormat header,MessageFormat footer)
	{
		if(!(component instanceof JTable))
			return;
		
		try {
			PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
			set.add(OrientationRequested.LANDSCAPE);
			((JTable)component).print(JTable.PrintMode.FIT_WIDTH, header, footer, true, set, false);
			logger.trace("Impression de données");
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Echec d'impression");
		}
	}
}
