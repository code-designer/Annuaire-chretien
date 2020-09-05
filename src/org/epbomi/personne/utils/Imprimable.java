package org.epbomi.personne.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.time.format.DateTimeFormatter;

import javax.swing.JComponent;

import org.epbomi.personne.model.Personne;

public class Imprimable implements Printable {
		
	@SuppressWarnings("unused")
	private JComponent component;
	private Personne p;
	
	public Imprimable(JComponent component) {
		this.component = component;
	}
	
	public Imprimable(Personne p)
	{
		this.p = p;
	}
	

	@Override
	public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
		// TODO Auto-generated method stub
		if(pageIndex > 0)
			return Printable.NO_SUCH_PAGE;
		
			printFile( g, pf, pageIndex);
				
		return Printable.PAGE_EXISTS;
	}
	
	@SuppressWarnings("unused")
	private void printFile(Graphics g, PageFormat pf, int pageIndex)
	{
		double xStart = pf.getImageableX();
		double yStart = pf.getImageableY();
		
		double xWidth = pf.getImageableWidth();
		double yHeight = pf.getImageableHeight();
		
		Font title = new Font("Tahoma", Font.PLAIN, 16);
		Font font = new Font("Tahoma", Font.PLAIN, 12);
		FontMetrics metric = g.getFontMetrics(font);
		int lineHeight = metric.getHeight();
		
		int lineNumbersPerPage = (int)yHeight/lineHeight;
		
		int startX = (int)xStart;
		int startY = (int)yStart;
		int spaceY = 3;
		int decalage = 150;
		
		startY += lineHeight;
		String titre = "FICHE DE RENSEIGNEMENT";
		g.setFont(title);
		g.drawString(titre, startX, startY);
		
		g.setFont(font);
		startY += 30;
		g.setColor(new Color(0,0,255));
		g.fillRect(startX, startY, (int)xWidth, lineHeight);
		startY += lineHeight;
		g.setColor(Color.WHITE);
		g.drawString("IDENTIFICATION", startX, startY - spaceY);
		startY += lineHeight;
		g.setColor(Color.BLACK);
		g.drawString("Code : ", startX, startY);
		g.drawString(p.getCode(), startX + 150, startY);
		
		g.drawImage(FileUtils.resizeImage(p.getPathImg().toString(), 100), startX + (int)xWidth - 100, startY, 100, 100, null);
		startY += lineHeight;
		g.drawString("Nom : " , startX, startY);
		g.drawString(p.getNom(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Prénoms : ", startX, startY);
		g.drawString(p.getPrenoms(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Sexe : ", startX, startY);
		g.drawString(p.getSexe().toString(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Date de naissance : " , startX, startY);
		g.drawString(p.getDateDeNaissance().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Lieu de naissance : " , startX, startY);
		g.drawString(p.getLieuDeNaissance(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Ethnie : ", startX, startY);
		g.drawString(p.getEthnie().toString(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Nationalite : ", startX, startY);
		g.drawString(p.getNationalite(), startX + decalage, startY);
		
		startY += 10;
		g.setColor(new Color(0,0,255));
		g.fillRect(startX, startY, (int)xWidth, lineHeight);
		startY += lineHeight;
		g.setColor(Color.WHITE);
		g.drawString("PROFESSION", startX, startY - spaceY);
		startY += lineHeight;
		g.setColor(Color.BLACK);
		g.drawString("Profession : ", startX, startY);
		g.drawString(p.getProfession().getProfession(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Structure : ", startX, startY);
		g.drawString(p.getProfession().getStructure(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Secteur d'activité : ", startX, startY);
		g.drawString(p.getProfession().getSecteur().toString(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Descritif activité : ", startX, startY);
		g.drawString(p.getProfession().getDescriptifActivite(), startX + decalage, startY);
		
		startY += 10;
		g.setColor(new Color(0,0,255));
		g.fillRect(startX, startY, (int)xWidth, lineHeight);
		startY += lineHeight;
		g.setColor(Color.WHITE);
		g.drawString("SITUATION MATRIMONIALE", startX, startY - spaceY);
		startY += lineHeight;
		g.setColor(Color.BLACK);
		g.drawString("Statut : ", startX, startY);
		g.drawString(p.getVieConjugale().getSituation().toString(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Conjoint : ", startX, startY);
		g.drawString(p.getVieConjugale().getConjoint(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Date d'union : ", startX, startY);
		g.drawString(p.getVieConjugale().getDateUnion().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Nombre d'enfants : ", startX, startY);
		g.drawString(String.valueOf(p.getVieConjugale().getNombreEnfants()), startX + decalage, startY);
		
		startY += 10;
		g.setColor(new Color(0,0,255));
		g.fillRect(startX, startY, (int)xWidth, lineHeight);
		startY += lineHeight;
		g.setColor(Color.WHITE);
		g.drawString("MINISTERE", startX, startY - spaceY);
		startY += lineHeight;
		g.setColor(Color.BLACK);
		g.drawString("Responsabilité : ", startX, startY);
		g.drawString(p.getMinistere().getResponsabilite(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Departement : ", startX, startY);
		g.drawString(p.getMinistere().getDepartement().toString(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Date de baptême : ", startX, startY);
		g.drawString(p.getMinistere().getDateBapteme().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Section : ", startX, startY);
		g.drawString(p.getMinistere().getSection().toString(), startX + decalage, startY);
		
		startY += 10;
		g.setColor(new Color(0,0,255));
		g.fillRect(startX, startY, (int)xWidth, lineHeight);
		startY += lineHeight;
		g.setColor(Color.WHITE);
		g.drawString("CONTACTS", startX, startY - spaceY);
		startY += lineHeight;
		g.setColor(Color.BLACK);
		g.drawString("Téléphone 1 : ", startX, startY);
		g.drawString(p.getContact().getCel(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Téléphone 2 : ", startX, startY);
		g.drawString(p.getContact().getTel(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Contact en cas d'urgence : ", startX, startY);
		g.drawString(p.getContact().getUrgence(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Adresse postale : ", startX, startY);
		g.drawString(p.getContact().getPostal(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Email : ", startX, startY);
		g.drawString(p.getContact().getEmail(), startX + decalage, startY);
		startY += lineHeight;
		g.drawString("Residence : ", startX, startY);
		g.drawString(p.getContact().getResidence(), startX + decalage, startY);
	}
	
}
