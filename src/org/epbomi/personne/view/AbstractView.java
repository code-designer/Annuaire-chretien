package org.epbomi.personne.view;

import java.awt.Color;
import java.beans.PropertyChangeEvent;

import javax.swing.JPanel;


public abstract class AbstractView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6403605352837958229L;
	protected Color fond = Color.WHITE;
	
	public AbstractView()
	{
		this.setBackground(fond);
	}
	
	public void modelPropertyChange(final PropertyChangeEvent event)
	{
		
	}
}
